package beans;

import dao.EstadoRepublicaDAO;
import dao.GestorDAO;
import dao.MunicipioDAO;
import dto.Colonia;
import dto.EstadoRepublica;
import dto.Gestor;
import dto.Municipio;
import impl.EstadoRepublicaIMPL;
import impl.GestorIMPL;
import impl.MunicipioIMPL;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Pablo
 */
@ManagedBean
@SessionScoped
public class ZonasVistaBean implements Serializable {

  ELContext elContext = FacesContext.getCurrentInstance().getELContext();
  /**
   * Inclusion del bean de Zona.
   */
  ZonaBean zona = (ZonaBean) elContext.getELResolver().getValue(elContext, null, "zona");

  /**
   * Todos los estadosRep que se listarán en la vista.
   */
  private List<EstadoRepublica> estadosRep;
  /**
   * Todos los municipios que se desplegarán en la vista y que corresponden a
   * los del estado desplegado.
   */
  private List<Municipio> mpiosVisibles;
  /**
   * Todas las colonias que se desplegarán en la vista y que corresponden a los
   * del municipio desplegado.
   */
  private List<Colonia> coloniasVisibles;

  /**
   * Lista con las coincidencias encontradas que se desplegará cuando el usuario
   * introduzca un criterio de búsqueda de municipios en la vista.
   */
  private List<Municipio> mpiosAutoCompletados;
  /**
   * Lista con las coincidencias encontradas que se desplegará cuando el usuario
   * introduzca un criterio de búsqueda de colonias en la vista.
   */
  private List<Colonia> coloniasAutoCompletadas;

  /**
   * Objeto DAO para realizar las operaciones en la base de datos que
   * corresponden con la tabla Municipio.
   */
  private final MunicipioDAO mpioDao;

  /**
   * Estado que actualmente se despliega en la vista.
   */
  private EstadoRepublica edoRepVisible;
  /**
   * Número que corresponde al orden en la lista en el que se encuentra el
   * estado que actualmente se despliega en la vista.
   */
  private int idEdoVisible;

  private List<Municipio> mpiosDelEstadoRepSelec;
  private List<Colonia> coloniasDelEstadoRepSelec;

  private boolean mpiosDeshabilitados;
  private boolean coloniasDeshabilitadas;

  private Gestor gestorAsignado;
  private List<Gestor> gestores;

  private boolean switchMpios;
  private boolean switchColonias;
  private boolean switchColoniasDisabled;
  private int acPanColoniasActiveIndex;
  private int acPanMpiosActiveIndex;

  public ZonasVistaBean() {
    zona = new ZonaBean();
    EstadoRepublicaDAO estadoRepDao = new EstadoRepublicaIMPL();
    mpioDao = new MunicipioIMPL();

    estadosRep = estadoRepDao.buscarTodo();

    coloniasVisibles = new ArrayList<>();

    mpiosDelEstadoRepSelec = new ArrayList<>();
    mpiosVisibles = new ArrayList<>();

    edoRepVisible = new EstadoRepublica();

    mpiosDeshabilitados = true;
    coloniasDeshabilitadas = true;

    GestorDAO gestorDao = new GestorIMPL();
    gestores = gestorDao.buscarTodo();

    switchMpios = false;
    switchColonias = switchMpios;
    switchColoniasDisabled = true;

    acPanColoniasActiveIndex = -1;
    acPanMpiosActiveIndex = -1;
  }

  public void onEstadosChange() {
    System.out.println("onEstadosChange().");
    this.mpiosDelEstadoRepSelec = this.mpioDao.buscarMunicipiosPorEstado(this.edoRepVisible.getIdEstado());
  }

  public List<EstadoRepublica> getEstadosRep() {
    return estadosRep;
  }

  public void setEstadosRep(List<EstadoRepublica> estadosRep) {
    this.estadosRep = estadosRep;
  }

  public List<Municipio> getMpiosVisibles() {
    mpiosVisibles = mpioDao.buscarMunicipiosPorEstado(zona.getEdosRepSeleccionados().get(idEdoVisible).getIdEstado());
    return mpiosVisibles;
  }

  public void setMpiosVisibles(List<Municipio> mpiosVisibles) {
    this.mpiosVisibles = mpiosVisibles;
  }

  public List<Colonia> getColoniasVisibles() {
    return coloniasVisibles;
  }

  public void setColoniasVisibles(List<Colonia> coloniasVisibles) {
    this.coloniasVisibles = coloniasVisibles;
  }

  public int getIdEdoVisible() {
    return idEdoVisible;
  }

  public void setIdEdoVisible(int idEdoVisible) {
    this.idEdoVisible = idEdoVisible;
  }

  public EstadoRepublica getEdoRepVisible() {
    return edoRepVisible;
  }

  public void setEdoRepVisible(EstadoRepublica edoRepVisible) {
    this.edoRepVisible = edoRepVisible;
  }

  public List<Municipio> getMpiosDelEstadoRepSelec() {
    return mpiosDelEstadoRepSelec;
  }

  public void setMpiosDelEstadoRepSelec(List<Municipio> mpiosDelEstadoRepSelec) {
    this.mpiosDelEstadoRepSelec = mpiosDelEstadoRepSelec;
  }

  public void onMpiosChange() {
    System.out.println("onMpiosChange().");
//    System.out.println("#################### Municipios seleccionados en total:");
//    for (int i = 0; i < this.zona.getMpiosSeleccionados().size(); i++) {
//      System.out.println(this.zona.getMpiosSeleccionados().get(i));
//    }
  }

  public List<Colonia> getColoniasDelEstadoRepSelec() {
    return coloniasDelEstadoRepSelec;
  }

  public void setColoniasDelEstadoRepSelec(List<Colonia> coloniasDelEstadoRepSelec) {
    this.coloniasDelEstadoRepSelec = coloniasDelEstadoRepSelec;
  }

  public void onMostrarMpiosChange() {
    if (this.switchMpios == false) {
      this.switchColonias = false;
      this.switchColoniasDisabled = true;
      this.acPanMpiosActiveIndex = -1;
    } else {
      this.switchColoniasDisabled = false;
    }
    this.mpiosDeshabilitados = !this.switchMpios;

    System.out.println("onMostrarMpiosChange(). - municipios " + (mpiosDeshabilitados == true ? "DEShabilitados" : "Habilitados."));

    System.out.println("\n|#################### Municipios seleccionados del estadoRep actual:");
    for (Municipio mpio : this.mpiosDelEstadoRepSelec) {
      System.out.println(mpio);
    }
    System.out.println("|_#################### #################### ####################_|");

    System.out.println("\n|#################### Municipios seleccionados en total:");
    for (int i = 0; i < this.zona.getMpiosSeleccionados().size(); i++) {
      System.out.println(this.zona.getMpiosSeleccionados().get(i));
    }
    System.out.println("|_#################### #################### ####################_| ");
  }

  public void onMostrarColoniasChange() {
    if (this.switchColonias == false) {
      this.coloniasDeshabilitadas = true;
      this.acPanColoniasActiveIndex = -1;
    } else {
      this.coloniasDeshabilitadas = false;
    }
    System.out.println("onMostrarColoniasChange(). - colonias " + (coloniasDeshabilitadas == true ? "DEShabilitados" : "Habilitadas."));
  }

  public boolean isMpiosDeshabilitados() {
    return mpiosDeshabilitados;
  }

  public void setMpiosDeshabilitados(boolean mpiosDeshabilitados) {
    this.mpiosDeshabilitados = mpiosDeshabilitados;
  }

  public boolean isColoniasDeshabilitadas() {
    return coloniasDeshabilitadas;
  }

  public void setColoniasDeshabilitadas(boolean coloniasDeshabilitadas) {
    this.coloniasDeshabilitadas = coloniasDeshabilitadas;
  }

  public void onGestorAsignadoChange() {
    System.out.println("onGestorAsignadoChange().");
  }

  public Gestor getGestorAsignado() {
    return gestorAsignado;
  }

  public void setGestorAsignado(Gestor gestorAsignado) {
    this.gestorAsignado = gestorAsignado;
  }

  public List<Gestor> getGestores() {
    return gestores;
  }

  public void setGestores(List<Gestor> gestores) {
    this.gestores = gestores;
  }

  public boolean isSwitchMpios() {
    return switchMpios;
  }

  public void setSwitchMpios(boolean switchMpios) {
    this.switchMpios = switchMpios;
  }

  public boolean isSwitchColonias() {
    return switchColonias;
  }

  public void setSwitchColonias(boolean switchColonias) {
    this.switchColonias = switchColonias;
  }

  public boolean isSwitchColoniasDisabled() {
    return switchColoniasDisabled;
  }

  public void setSwitchColoniasDisabled(boolean switchColoniasDisabled) {
    this.switchColoniasDisabled = switchColoniasDisabled;
  }

  public int getAcPanColoniasActiveIndex() {
    return acPanColoniasActiveIndex;
  }

  public void setAcPanColoniasActiveIndex(int acPanColoniasActiveIndex) {
    this.acPanColoniasActiveIndex = acPanColoniasActiveIndex;
  }

  public int getAcPanMpiosActiveIndex() {
    return acPanMpiosActiveIndex;
  }

  public void setAcPanMpiosActiveIndex(int acPanMpiosActiveIndex) {
    this.acPanMpiosActiveIndex = acPanMpiosActiveIndex;
  }

  public ZonaBean getZona() {
    return zona;
  }

  public void setZona(ZonaBean zona) {
    this.zona = zona;
  }

}
