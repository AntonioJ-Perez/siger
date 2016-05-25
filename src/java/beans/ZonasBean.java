/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.EstadoRepublicaDAO;
import dao.GestorDAO;
import dao.MunicipioDAO;
import dao.RegionDAO;
import dao.ZonaDAO;
import dto.EstadoRepublica;
import dto.Gestor;
import dto.Municipio;
import dto.Region;
import dto.Zona;
import impl.EstadoRepublicaIMPL;
import impl.GestorIMPL;
import impl.MunicipioIMPL;
import impl.RegionIMPL;
import impl.ZonaIMPL;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 * La reestructuracion de este modulo es un honor que me ha concedido el Ing.
 * Pablo landeros. Hombre respetable y siempre fiel a sus convicciones. Que el
 * exito lo ilumine a donde quiera que vaya
 *
 * In memoriam @author Pablo
 *
 * @author Eduardo
 */
@ManagedBean(name = "zonasBean")
@ViewScoped
public class ZonasBean implements Serializable {

  // LLAMADA A OTROS BEANS
  public final ELContext elContext = FacesContext.getCurrentInstance().getELContext();
  public final IndexBean indexBean = (IndexBean) elContext.getELResolver().getValue(elContext, null, "indexBean");

  // VARIBALES DE CLASE
  private String nombreZona;
  private List<Municipio> detalleZona;
  private List<Municipio> municipiosSeleccionados;
  private List<Municipio> listaMunicipios;
  private List<Zona> listaZonas;
  private List<Gestor> listaGestores;
  private final EstadoRepublicaDAO estadoRepublicaDao;
  private final MunicipioDAO municipioDao;
  private final RegionDAO regionDao;
  private final ZonaDAO zonaDao;
  private final GestorDAO gestorDao;
  private Gestor gestorSeleccionado;
  private Zona zonaSeleccionada;
  private List<Municipio> municipiosPorEliminar;

  // CONSTRUCTOR
  public ZonasBean() {
    listaZonas = new ArrayList();
    listaGestores = new ArrayList();
    listaMunicipios = new ArrayList();
    detalleZona = new ArrayList();
    municipiosSeleccionados = new ArrayList();
    estadoRepublicaDao = new EstadoRepublicaIMPL();
    municipioDao = new MunicipioIMPL();
    regionDao = new RegionIMPL();
    zonaDao = new ZonaIMPL();
    gestorDao = new GestorIMPL();
    gestorSeleccionado = new Gestor();
    zonaSeleccionada = new Zona();
    municipiosPorEliminar = new ArrayList();
    obtenerListas();
  }

  //  METODO QUE CARGA LA LISTA DE MUNICIPIOS
  public final void obtenerListas() {
    listaMunicipios = new ArrayList();
    listaZonas = zonaDao.buscarPorDespacho(indexBean.getUsuario().getDespacho().getIdDespacho());
    listaGestores = gestorDao.buscarPorDespacho(indexBean.getUsuario().getDespacho().getIdDespacho());
    List<EstadoRepublica> estados = estadoRepublicaDao.buscarTodo();
    for (int i = 0; i < (estados.size()); i++) {
      List<Municipio> municipios = municipioDao.buscarMunicipiosPorEstado(estados.get(i).getIdEstado());
      for (int j = 0; j < (municipios.size()); j++) {
        listaMunicipios.add(municipios.get(j));
      }
    }
    List<Integer> ids = regionDao.buscarMunicipiosRegion(indexBean.getUsuario().getDespacho().getIdDespacho());
    for (int i = 0; i < (listaMunicipios.size()); i++) {
      for (int j = 0; j < (ids.size()); j++) {
        if (listaMunicipios.get(i).getIdMunicipio() == ids.get(j).intValue()) {
          listaMunicipios.remove(i);
        }
      }
    }
  }

// METODO QUE ACTUALIZARA LA VISTA
  public void actualizaVista() {
    RequestContext.getCurrentInstance().update("nuevaZonaForm");
    RequestContext.getCurrentInstance().update("listaZonasForm");
    RequestContext.getCurrentInstance().update("detalleZonaForm");
  }

  // METODO QUE CREA LA ZONA
  public void crearZona() {
    boolean ok = false;
    if (nombreZona.equals("")) {
      FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error.", "Debe indicar un nombre para la zona."));
    } else if (municipiosSeleccionados.isEmpty()) {
      FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error.", "Debe seleccionar al menos un municipio."));
    } else if (validarNombreZona()) {
      FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error.", "Ya existe una zona con el nombre propuesto."));
    } else if (!validarGestorZona()) {
      FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error.", "El gestor seleccionado ya tiene asignada una zona."));
    } else {
      Zona z = new Zona();
      z.setDespacho(indexBean.getUsuario().getDespacho());
      z.setGestor(gestorDao.buscar(gestorSeleccionado.getIdGestor()));
      z.setNombre(nombreZona);
      z = zonaDao.insertar(z);
      if (z != null) {
        for (int i = 0; i < (municipiosSeleccionados.size()); i++) {
          Municipio m = municipioDao.buscarPorId(municipiosSeleccionados.get(i).getIdMunicipio());
          Region r = new Region();
          r.setEstadoRepublica(m.getEstadoRepublica());
          r.setMunicipio(m);
          r.setZona(z);
          if (regionDao.insertar(r) != null) {
            ok = true;
          }
        }
        if (ok) {
          FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa.", "Se agrego la zona al sistema."));
          cancelar();
        } else {
          FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error.", "No se agrego la zona. Contacte al equipo de sistemas"));
        }
      } else {
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error.", "No se agrego la zona. Contacte al equipo de sistemas"));
      }
    }
  }

  // METODO QUE ELIMINA LA ZONA SELECCIONADA
  public void eliminarZona(Zona zona) {
    List<Region> regiones = regionDao.buscarPorZona(zona.getIdZona());
    boolean ok = true;
    for (int i = 0; i < (regiones.size()); i++) {
      ok = regionDao.eliminar(regiones.get(i));
      if (!ok) {
        break;
      }
    }
    if (ok) {
      ok = zonaDao.eliminar(zona);
      if (ok) {
        obtenerListas();
        actualizaVista();
        RequestContext.getCurrentInstance().execute("PF('dlgZonas').hide();");
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa.", "Se elimino la zona."));
      } else {
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error.", "No se pudo eliminar la zona. Contacte al equipo de sistemas"));
      }
    } else {
      FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error.", "No se pudo eliminar la zona. Contacte al equipo de sistemas"));
    }
  }

  // METODO QUE ABRE LA TABLA DEL DETALLE DE LA ZONA SELECCIONADA
  public void abrirDetalleZona(Zona zona) {
    detalleZona = regionDao.buscarMunicipiosZona(zona.getIdZona());
    zonaSeleccionada = zona;
    actualizaVista();
    RequestContext.getCurrentInstance().execute("PF('detalleZonaDialog').show();");
  }

  // METODO QUE LIMPIA TODOS LOS CONTROLES
  public void cancelar() {
    nombreZona = "";
    municipiosSeleccionados.clear();
    obtenerListas();
    actualizaVista();
    RequestContext.getCurrentInstance().execute("PF('dlgNuevaZona').hide();");
  }

// METODO QUE VALIDA QUE EL NOMBRE DE LA ZONA NO EXISTA
  public boolean validarNombreZona() {
    boolean ok = true;
    List<String> nombres = zonaDao.buscarNombresZonas(indexBean.getUsuario().getDespacho().getIdDespacho());
    if (nombres.size() > 0) {
      for (int i = 0; i < (nombres.size()); i++) {
        ok = nombreZona.equals(nombres.get(i));
      }
    } else {
      ok = true;
    }
    return ok;
  }

  // METODO QUE VALIDA QUE LA ZONA SE LE ASIGNE A UN GESTOR QUE NO TENGA ZONA
  public boolean validarGestorZona() {
    boolean ok = true;
    List<Integer> ids = zonaDao.buscarIdsGestoresZonas();
    for (int i = 0; i < (ids.size()); i++) {
      if ((gestorSeleccionado.getIdGestor()) == ids.get(i).intValue()) {
        ok = false;
        break;
      }
    }
    return ok;
  }

  // METODO QUE ELIMINA EL MUNICIPIO SELECCIONADO
  public void eliminarMunicipios() {
    if (municipiosPorEliminar.isEmpty()) {
      FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error.", "No se ha seleccionado ningun municipio."));
    } else {
      System.out.println("MUNICIPIOS POR ELIMINAR: " + municipiosPorEliminar.size());
      /*
      for (int i = 0; i < (detalleZona.size()); i++) {
        System.out.println("ENTRO AL FOR");
        System.out.println("DETALLE ZONA " + (i + 1));
        if (detalleZona.get(i) == municipioPorEliminar) {
          System.out.println("SON IGUALES");
          Region r = new Region();
          r.setMunicipio(municipioPorEliminar);
          r.setEstadoRepublica(municipioPorEliminar.getEstadoRepublica());
          r.setZona(zonaSeleccionada);
          if (regionDao.eliminar(r)) {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa.", "Se elimino el municipio " + municipioPorEliminar.getNombre() + " de la zona."));
          } else {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error.", "No se pudo eliminar el municipio. Contacte al equipo de sistemas"));
          }
        }
      }
      System.out.println("DETALLE ZONA VACIO");
      */
    }
  }

  // GETTERS & SETTERS
  public String getNombreZona() {
    return nombreZona;
  }

  public void setNombreZona(String nombreZona) {
    this.nombreZona = nombreZona;
  }

  public List<Gestor> getListaGestores() {
    return listaGestores;
  }

  public void setListaGestores(List<Gestor> listaGestores) {
    this.listaGestores = listaGestores;
  }

  public Gestor getGestorSeleccionado() {
    return gestorSeleccionado;
  }

  public void setGestorSeleccionado(Gestor gestorSeleccionado) {
    this.gestorSeleccionado = gestorSeleccionado;
  }

  public List<Municipio> getMunicipiosSeleccionados() {
    return municipiosSeleccionados;
  }

  public void setMunicipiosSeleccionados(List<Municipio> municipiosSeleccionados) {
    this.municipiosSeleccionados = municipiosSeleccionados;
  }

  public List<Municipio> getListaMunicipios() {
    return listaMunicipios;
  }

  public void setListaMunicipios(List<Municipio> listaMunicipios) {
    this.listaMunicipios = listaMunicipios;
  }

  public List<Zona> getListaZonas() {
    return listaZonas;
  }

  public void setListaZonas(List<Zona> listaZonas) {
    this.listaZonas = listaZonas;
  }

  public Zona getZonaSeleccionada() {
    return zonaSeleccionada;
  }

  public void setZonaSeleccionada(Zona zonaSeleccionada) {
    this.zonaSeleccionada = zonaSeleccionada;
  }

  public List<Municipio> getDetalleZona() {
    return detalleZona;
  }

  public void setDetalleZona(List<Municipio> detalleZona) {
    this.detalleZona = detalleZona;
  }

  public List<Municipio> getMunicipiosPorEliminar() {
    return municipiosPorEliminar;
  }

  public void setMunicipiosPorEliminar(List<Municipio> municipiosPorEliminar) {
    this.municipiosPorEliminar = municipiosPorEliminar;
  }

}
