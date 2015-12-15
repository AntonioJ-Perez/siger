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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import util.constantes.Constantes;

/**
 * Este bean maneja las variables que controlan los componentes asociados
 * directamente con la vista. Los datos relacionados directamente con la zona se
 * encuentran en la clase ZonaBean.
 *
 * Si se asigna al atributo eager el valor true y el bean tiene alcance
 * ApplicationScoped, se instanciará el objeto <strong> al iniciar la aplicación
 * </strong>, reemplazando la carga "lazy" que por defecto ocurre.
 *
 * @author Pablo
 */
@ManagedBean(name = "zonasVistaBean")
@ViewScoped
public class ZonasVistaBean implements Serializable {

  private final ELContext elContext = FacesContext.getCurrentInstance().getELContext();
  /**
   * Inclusion del bean de Zona.
   */
  private ZonaBean zona = (ZonaBean) elContext.getELResolver().getValue(elContext, null, "zonaBean");

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
  
  EstadoRepublicaDAO estadoRepDao;

  private List<Colonia> coloniasDelEstadoRepSelec;

  private boolean mpiosDeshabilitados;
  private boolean coloniasDeshabilitadas;

  private Gestor gestorAsignado;
  private List<Gestor> gestores;

  private int acPanColoniasActiveIndex;
  private int acPanMpiosActiveIndex;

  private final String lugarSinSeleccion;
  private final String seleccionCompleta;
  private String tituloDialogo;

  private final String gestorSinSeleccion;

  public ZonasVistaBean() {
    zona = new ZonaBean();
    EstadoRepublicaDAO estadoRepDao = new EstadoRepublicaIMPL();
    mpioDao = new MunicipioIMPL();

    estadosRep = estadoRepDao.buscarTodo();

    coloniasVisibles = new ArrayList<>();

    mpiosVisibles = new ArrayList<>();

    edoRepVisible = new EstadoRepublica();

    mpiosDeshabilitados = true;
    coloniasDeshabilitadas = true;

    GestorDAO gestorDao = new GestorIMPL();
    gestores = gestorDao.buscarTodo();

    acPanColoniasActiveIndex = -1;
    acPanMpiosActiveIndex = -1;

    lugarSinSeleccion = Constantes.LUGAR_SIN_SELECCION;
    seleccionCompleta = Constantes.LUGAR_SELECCION_COMPLETA;

    tituloDialogo = "Si se despliega este título, algo anda mal...";  // linea de prueba

    gestorSinSeleccion = Constantes.GESTOR_SIN_SELECCION;
  }

  /**
   * Método utilizado para llenar la lista de municipios visibles (aptos para
   * seleccionar) con base en el estado seleccionado en la vista.
   */
  public void onEstadosChange() {
    System.out.println("\n|---------------------onEstadosChange().-----------------------------¬");
    System.out.println("Por seleccionar datos del estado:"
            + "\nthis.edoRepVisible.getNombre() = " + this.edoRepVisible.getNombre()
            + "\nthis.edoRepVisible.getIdEstado() = " + this.edoRepVisible.getIdEstado());
    System.out.println("L_____________________________________________________________________");

    if (this.edoRepVisible.getNombre().equals(this.lugarSinSeleccion)) {
      this.mpiosVisibles.clear();
    } else {
      this.mpiosVisibles = this.mpioDao.buscarMunicipiosPorEstado(this.edoRepVisible.getIdEstado());
    }

    this.acPanColoniasActiveIndex = -1;
    this.coloniasDeshabilitadas = true;
  }

  public List<EstadoRepublica> getEstadosRep() {
    return estadosRep;
  }

  public void setEstadosRep(List<EstadoRepublica> estadosRep) {
    this.estadosRep = estadosRep;
  }

  public List<Municipio> getMpiosVisibles() {
    return this.mpiosVisibles;
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
    return mpiosVisibles;
  }

  public void setMpiosDelEstadoRepSelec(List<Municipio> mpiosDelEstadoRepSelec) {
    this.mpiosVisibles = mpiosDelEstadoRepSelec;
  }

  public void onMpiosChange() {
    System.out.println("\n|###### onMpiosChange(). Municipios seleccionados en total: #######");
    for (Municipio mpio : this.zona.getMpiosSeleccionados()) {
      System.out.println(mpio);
    }
    System.out.println("|_#################### #################### ####################_| ");
  }

  public List<Colonia> getColoniasDelEstadoRepSelec() {
    return coloniasDelEstadoRepSelec;
  }

  public void setColoniasDelEstadoRepSelec(List<Colonia> coloniasDelEstadoRepSelec) {
    this.coloniasDelEstadoRepSelec = coloniasDelEstadoRepSelec;
  }

  public void onMostrarMpiosChange() {
    if (this.acPanMpiosActiveIndex == -1) {
      this.mpiosDeshabilitados = true;
      this.coloniasDeshabilitadas = true;
      this.acPanColoniasActiveIndex = -1;

    } else {
      this.mpiosDeshabilitados = false;
      
      if (this.mpiosVisibles.isEmpty()) {
        onEstadosChange();
      }
    }

    System.out.println("\n|#################### onMostrarMpiosChange(). - municipios "
            + (mpiosDeshabilitados == true ? "DEShabilitados" : "Habilitados.")
            + " ############# \n########## Municipios visibles del estadoRep actual:"); // linea de debug

    for (Municipio mpio : this.mpiosVisibles) {
      System.out.println(mpio);
    }
    System.out.println("|_#################### #################### #################### ####################_|"); // linea de debug

  }

  public void onMostrarColoniasChange(String evento) {
    if (this.acPanColoniasActiveIndex == -1) {
      this.coloniasDeshabilitadas = true;
    } else {
      this.coloniasDeshabilitadas = false;
    }
    System.out.println("onMostrarColoniasChange(" + evento + "). - colonias " 
            + (coloniasDeshabilitadas == true ? "DEShabilitados" : "Habilitadas."));

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

  public void onAceptar() {
//    FacesContext context = FacesContext.getCurrentInstance();
//    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Botón aceptar", "Probando la acción del botón aceptar"));
  }

  public Municipio getMpioPorNombre(String nombre) {
    for (Municipio mpioIterador : this.mpiosVisibles) {
      if (mpioIterador.getNombre().equals(nombre)) {
        return mpioIterador;
      }
    }
    return null;
  }

  public void onEventOccurs(String tipoEvento) {
    FacesContext context = FacesContext.getCurrentInstance();

    switch (tipoEvento) {
      default:
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Evento <" + tipoEvento + ">",
                "Se hizo una llamada con un tipo de evento NO válido."));
        break;
    }

  }

  public void onZonasDisplay(int opcion) {
    switch (opcion) {
      case 1:
        this.tituloDialogo = "Crear nueva zona.";
        // Lógica de la creación

        break;
      case 2:
        this.tituloDialogo = "Modificar zona existente.";
        // Lógica de la modificación

        break;
    }

    FacesContext context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
            "actionListener onZonasDisplay()",
            "Por definir este comportamiento al desplegar el form de Zonas :P"));
  }

  public String getLugarSinSeleccion() {
    return lugarSinSeleccion;
  }

  public String getSeleccionCompleta() {
    return seleccionCompleta;
  }

  public String getTituloDialogo() {
    return tituloDialogo;
  }

  public void setTituloDialogo(String tituloDialogo) {
    this.tituloDialogo = tituloDialogo;
  }

  public String getGestorSinSeleccion() {
    return gestorSinSeleccion;
  }

}
