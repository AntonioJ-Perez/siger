/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.DevolucionDAO;
import dao.HistorialDAO;
import dto.Devolucion;
import dto.Historial;
import impl.DevolucionIMPL;
import impl.HistorialIMPL;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import util.constantes.Devoluciones;

/**
 *
 * @author Eduardo
 */
@ManagedBean(name = "devolucionBean")
@ViewScoped
public class DevolucionBean implements Serializable {

  // LLAMADA A OTROS BEANS
  ELContext elContext = FacesContext.getCurrentInstance().getELContext();
  IndexBean indexBean = (IndexBean) elContext.getELResolver().getValue(elContext, null, "indexBean");

  // VARIABLES DE CLASE
  private final DevolucionDAO devolucionDao;
  private final HistorialDAO historialDao;
  public List<Devolucion> retiradosSeleccionados;
  public List<Devolucion> bandejaSeleccionados;
  public List<Devolucion> devolucionesSeleccionadas;
  public List<Devolucion> listaRetirados;
  public List<Devolucion> listaDevoluciones;
  public List<Devolucion> listaDevueltos;
  private final int idDespacho;
  private final String admin;

  // CONSTRUCTOR
  public DevolucionBean() {
    devolucionDao = new DevolucionIMPL();
    historialDao = new HistorialIMPL();
    retiradosSeleccionados = new ArrayList<>();
    bandejaSeleccionados = new ArrayList<>();
    devolucionesSeleccionadas = new ArrayList<>();
    listaRetirados = new ArrayList<>();
    listaDevoluciones = new ArrayList<>();
    listaDevueltos = new ArrayList<>();
    idDespacho = indexBean.getUsuario().getDespacho().getIdDespacho();
    admin = indexBean.getUsuario().getNombreLogin();
    obtenerListas();
  }

  // METODO PARA APROBAR UNA DEVOLUCION
  public void aprobar(List<Devolucion> lista) {
    int tam = lista.size();
    if (!lista.isEmpty()) {
      boolean ok = true;
      for (int i = 0; i < tam; i++) {
        lista.get(i).setEstatus(Devoluciones.DEVUELTO);
        String quien = indexBean.getUsuario().getNombreLogin();
        lista.get(i).setRevisor(quien);
        Historial h = new Historial();
        h.setCredito(lista.get(i).getCredito());
        h.setEvento("El administrador: " + admin + ", aprobo la devolucion del credito");
        h.setFecha(new Date());
        ok = devolucionDao.editar(lista.get(i)) && historialDao.insertar(h);
      }
      if (ok) {
        obtenerListas();
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa.", "Se aprobaron las devoluciones seleccionadas"));
      } else {
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error.", "No se pudieron aprobar las devoluciones. Contacte con el administrador de base de datos"));
      }
    } else {
      FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error.", "No ha seleccionado ninguna devolucion"));
    }
  }

  // METODO PARA RECHAZAR UNA DEVOLUCION
  public void rechazar(List<Devolucion> lista) {
    int tam = lista.size();
    if ((!lista.isEmpty()) && (tam >= 1)) {
      boolean ok = true;
      for (int i = 0; i < tam; i++) {
        Devolucion devolucion = devolucionDao.buscarDevolucionPorNumeroCredito(lista.get(i).getCredito().getNumeroCredito());
        Historial h = new Historial();
        h.setCredito(devolucion.getCredito());
        h.setEvento("El administrador: " + admin + ", rechazo la devolucion del credito");
        h.setFecha(new Date());
        ok = devolucionDao.eliminar(devolucion) && historialDao.insertar(h);
      }
      if (ok) {
        obtenerListas();
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa.", "Se rechazaron las devoluciones seleccionadas"));
      } else {
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error.", "No se pudieron rechazar las devoluciones. Contacte con el administrador de base de datos"));
      }
    } else {
      FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error.", "No ha seleccionado ninguna devolucion"));
    }
  }

  // METODO PARA PEDIR AL BANCO UNA CONSERVACION
  public void conservar(List<Devolucion> lista) {
    int tam = lista.size();
    if ((!lista.isEmpty()) && (tam >= 1)) {
      boolean ok = true;
      for (int i = 0; i < tam; i++) {
        Devolucion devolucion = devolucionDao.buscarDevolucionPorNumeroCredito(lista.get(i).getCredito().getNumeroCredito());
        devolucion.setEstatus(Devoluciones.ESPERA_CONSERVACION);
        devolucion.setSolicitante(indexBean.getUsuario().getNombreLogin());
        Historial h = new Historial();
        h.setCredito(devolucion.getCredito());
        h.setEvento("El administrador: " + admin + ", solicito la conservacion del credito");
        h.setFecha(new Date());
        ok = devolucionDao.editar(devolucion) && historialDao.insertar(h);
      }
      if (ok) {
        obtenerListas();
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa.", "Se han conservado los creditos seleccionados"));
      } else {
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error.", "No se pudieron aprobar las devoluciones. Contacte con el administrador de base de datos"));
      }
    } else {
      FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error.", "No ha seleccionado ninguna devolucion"));
    }
  }

  // METODO PARA REACTIVAR UN CREDITO QUE ESTUVO DEVUELTO PREVIAMENTE
  public void reactivar() {
    int tam = devolucionesSeleccionadas.size();
    if ((!devolucionesSeleccionadas.isEmpty()) && (tam >= 1)) {
      boolean ok = true;
      for (int i = 0; i < tam; i++) {
        Devolucion devolucion = devolucionDao.buscarDevolucionPorNumeroCredito(devolucionesSeleccionadas.get(i).getCredito().getNumeroCredito());
        Historial h = new Historial();
        h.setCredito(devolucion.getCredito());
        h.setEvento("El administrador: " + admin + ", reactivo el credito");
        h.setFecha(new Date());
        ok = devolucionDao.eliminar(devolucion) && historialDao.insertar(h);
      }
      if (ok) {
        obtenerListas();
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa.", "Se reactivaron los creditos seleccionados"));
      } else {
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error.", "No se pudieron reactivar los creditos. Contacte con el administrador de base de datos"));
      }
    } else {
      FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error.", "No ha seleccionado ningun credito"));
    }
  }

  public final void obtenerListas() {
    // OBTENER LOS CREDITOS PENDIENTES O EN ESPERA DE CONSERVACION
    listaDevoluciones = devolucionDao.bandejaDevolucionPorDespacho(idDespacho);
    // OBTENER LOS CREDITOS PENDIENTES O EN ESPERA DE CONSERVACION
    listaDevueltos = devolucionDao.devueltosPorDespacho(idDespacho);
    // OBTENER LOS CREDITOS PENDIENTES O EN ESPERA DE CONSERVACION
    listaRetirados = devolucionDao.retiradosBancoPorDespacho(idDespacho);
  }

  // METODO QUE LE DA UNA ETIQUETA A LOS VALORES NUMERICOS DEL ESTATUS DE PAGOS
  public String etiquetarEstatus(int estatus) {
    String estado = "";
    if (estatus == Devoluciones.CONSERVADO) {
      estado = "Conservado";
    }
    if (estatus == Devoluciones.DEVUELTO) {
      estado = "Devuelto";
    }
    if (estatus == Devoluciones.ESPERA_CONSERVACION) {
      estado = "Espera conservacion";
    }
    if (estatus == Devoluciones.PENDIENTE) {
      estado = "Pendiente";
    }
    return estado;
  }

  // ***********************************************************************************************************************
  // ***********************************************************************************************************************
  // ***********************************************************************************************************************
  // GETTERS & SETTERS
  public List<Devolucion> getRetiradosSeleccionados() {
    return retiradosSeleccionados;
  }

  public void setRetiradosSeleccionados(List<Devolucion> retiradosSeleccionados) {
    this.retiradosSeleccionados = retiradosSeleccionados;
  }

  public List<Devolucion> getBandejaSeleccionados() {
    return bandejaSeleccionados;
  }

  public void setBandejaSeleccionados(List<Devolucion> bandejaSeleccionados) {
    this.bandejaSeleccionados = bandejaSeleccionados;
  }

  public List<Devolucion> getListaRetirados() {
    return listaRetirados;
  }

  public void setListaRetirados(List<Devolucion> listaRetirados) {
    this.listaRetirados = listaRetirados;
  }

  public List<Devolucion> getListaDevoluciones() {
    return listaDevoluciones;
  }

  public void setListaDevoluciones(List<Devolucion> listaDevoluciones) {
    this.listaDevoluciones = listaDevoluciones;
  }

  public List<Devolucion> getListaDevueltos() {
    return listaDevueltos;
  }

  public void setListaDevueltos(List<Devolucion> listaDevueltos) {
    this.listaDevueltos = listaDevueltos;
  }

  public List<Devolucion> getDevolucionesSeleccionadas() {
    return devolucionesSeleccionadas;
  }

  public void setDevolucionesSeleccionadas(List<Devolucion> devolucionesSeleccionadas) {
    this.devolucionesSeleccionadas = devolucionesSeleccionadas;
  }

}
