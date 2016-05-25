/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CampanaDAO;
import dao.CreditoDAO;
import dao.GestionDAO;
import dao.HistorialDAO;
import dao.MarcajeDAO;
import dao.PagoDAO;
import dao.PromesaPagoDAO;
import dto.Campana;
import dto.Credito;
import dto.Gestion;
import dto.Pago;
import dto.PromesaPago;
import impl.CampanaIMPL;
import impl.CreditoIMPL;
import impl.GestionIMPL;
import impl.HistorialIMPL;
import impl.MarcajeIMPL;
import impl.PagoIMPL;
import impl.PromesaPagoIMPL;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import util.constantes.Marcajes;
import util.log.Logs;

/**
 *
 * @author Eduardo
 */
@ManagedBean(name = "cuentasGestorBean")
@SessionScoped
public class CuentasGestorBean implements Serializable {

  // LLAMADA A OTROS BEANS
  ELContext elContext = FacesContext.getCurrentInstance().getELContext();
  IndexBean indexBean = (IndexBean) elContext.getELResolver().getValue(elContext, null, "indexBean");
  CreditoActualBean creditoActualBean = (CreditoActualBean) elContext.getELResolver().getValue(elContext, null, "creditoActualBean");

  // VARIABLES DE CLASE
  private boolean habilitaCampanas;
  private List<CreditoCampana> listaCreditosCampanas;
  List<Credito> creditosCampana;
  private CreditoCampana seleccion;
  private final CreditoDAO creditoDao;
  private final CampanaDAO campanaDao;
  private final GestionDAO gestionDao;
  private final PagoDAO pagoDao;
  private final PromesaPagoDAO promesaPagoDao;
  private final MarcajeDAO marcajeDao;
  private final HistorialDAO historialDao;
  private int posicion;

  //CONSTRUCTOR
  public CuentasGestorBean() {
    habilitaCampanas = false;
    posicion = 0;
    seleccion = new CreditoCampana();
    listaCreditosCampanas = new ArrayList();
    creditosCampana = new ArrayList();
    creditoDao = new CreditoIMPL();
    campanaDao = new CampanaIMPL();
    gestionDao = new GestionIMPL();
    pagoDao = new PagoIMPL();
    promesaPagoDao = new PromesaPagoIMPL();
    marcajeDao = new MarcajeIMPL();
    historialDao = new HistorialIMPL();
  }

  // METODO QUE CARGA LAS CAMPAÑAS DESPUES DE INICIAR SESION
  public void cargar() {
    cambiarCampanas();
    obtenerListas();
    habilitaCampanas = true;
  }

  // METODO QUE OBTIENE LAS LISTAS INICIALES
  public final void obtenerListas() {
    List<Campana> listaCampanas = campanaDao.buscarTodas();
    for (int i = 0; i < (listaCampanas.size()); i++) {
      List<Credito> listaCredito = creditoDao.buscarCreditosPorCampanaGestor(listaCampanas.get(i).getIdCampana(), indexBean.getUsuario().getIdUsuario());
      if (!listaCredito.isEmpty()) {
        CreditoCampana c = new CreditoCampana();
        c.setIdCampana(listaCampanas.get(i).getIdCampana());
        c.setNombreCampana(listaCampanas.get(i).getNombre());
        c.setCuentasEnCampana(listaCredito.size());
        int[] arreglo = checarCampana(listaCredito);
        c.setNuevasEnCampana(arreglo[0]);
        c.setProgresoCampana(arreglo[1]);
        c.setVerdes(arreglo[2]);
        c.setAmarillas(arreglo[3]);
        c.setRojas(arreglo[4]);
        listaCreditosCampanas.add(c);
      }
    }
  }

  //METODO QUE CALCULA LOS CREDITOS QUE CAMBIARON DE CAMPAÑA HOY
  public int[] checarCampana(List<Credito> creditos) {
    int[] arreglo = new int[5];
    int cambios = 0;
    int progreso = 0;
    int verdes = 0;
    int amarillas = 0;
    int rojas = 0;
    for (int i = 0; i < (creditos.size()); i++) {
      if (historialDao.verificarCampioCampañaCredito(creditos.get(i).getIdCredito())) {
        cambios = cambios + 1;
      }
      if (gestionDao.buscarGestionHoy(creditos.get(i).getIdCredito())) {
        progreso = progreso + 1;
      }
      int dias = gestionDao.checarDiasSinGestionar(creditos.get(i).getIdCredito()).intValue();
      if (dias <= 3) {
        verdes = verdes + 1;
      }
      if ((dias > 3) && (dias < 7)) {
        amarillas = amarillas + 1;
      }
      if (dias >= 7) {
        rojas = rojas + 1;
      }
    }
    arreglo[0] = cambios;
    arreglo[1] = progreso;
    arreglo[2] = verdes;
    arreglo[3] = amarillas;
    arreglo[4] = rojas;
    return arreglo;
  }

  // METODO QUE VERIFICA SI LA CAMPAÑA EN LA LISTA ES LA CAMPAÑA QUE SE ESTA GESTIONANDO
  public String verificarCampanaEnCurso(CreditoCampana campana) {
    if (campana == seleccion) {
      return "En curso";
    } else {
      return "";
    }
  }

  // METODO QUE CAMBIA LAS CUENTAS DE CAMPAÑA SEGUN CORRESPONDA
  public final void cambiarCampanas() {
    // SE OBTIENE TODOS LOS CREDITOS ASIGNADOS A ESTE GESTOR
    List<Credito> creditosEnGestion = creditoDao.buscarCreditosPorGestor(indexBean.getUsuario().getIdUsuario());
    for (int i = 0; i < (creditosEnGestion.size()); i++) {
      Credito c = creditosEnGestion.get(i);
      int campana = c.getCampana().getIdCampana();
      int campanaActual = campana;
      List<Gestion> gestionesCredito = gestionDao.buscarGestionesCredito(c.getIdCredito());
      // CASO 7: EL CREDITO TIENE UNA PROMESA DE PAGO PARA EL DIA DE HOY
      if (promesaPagoDao.buscarPromesasHoy(c.getIdCredito())) {
        campana = 7;
      } // CASO 8: EL CREDITO TIENE UNA PROMESA DE PAGO POR CUMPLIRSE EN 4 DIAS
      else if (promesaPagoDao.buscarPromesasAnticipaFecha(c.getIdCredito())) {
        campana = 8;
      } // CASO 9: EL CREDITO TIENE UNA PROMESA DE PAGO EN UN PLAZO MAYOR A 5 DIAS
      else if (promesaPagoDao.buscarPromesasPorCumplirse(c.getIdCredito())) {
        campana = 9;
      } // CASO 13: EL CREDITO CUMPLIO SU PROMESA DE PAGO Y TIENE OTRA PARA EL DIA DE HOY
      else if ((promesaPagoDao.buscarPromesasHoy(c.getIdCredito())) && (buscarPromesaCumplida(c.getIdCredito()))) {
        campana = 13;
      } // CASO 14: EL CREDITO CUMPLIO SU PROMESA DE PAGO Y TIENE OTRA EN 4 DIAS
      else if ((promesaPagoDao.buscarPromesasAnticipaFecha(c.getIdCredito())) && (buscarPromesaCumplida(c.getIdCredito()))) {
        campana = 14;
      } // CASO 15: EL CREDITO CUMPLIO SU PROMESA DE PAGO Y TIENE OTRA EN UN PLAZO MAYOR A 5 DIAS
      else if ((promesaPagoDao.buscarPromesasPorCumplirse(c.getIdCredito())) && (buscarPromesaCumplida(c.getIdCredito()))) {
        campana = 15;
      } // CASO 12: EL CREDITO CUMPLIO SU PROMESA DE PAGO HOY
      else if (buscarPromesaCumplida(c.getIdCredito())) {
        campana = 12;
      } // CASO 10: EL CREDITO REPORTA UN PAGO EL DIA DE HOY PERO ES POR UNA CANTIDAD MENOR
      else if (buscarConvenioIncompleto(c.getIdCredito())) {
        campana = 10;
      } // CASO 11: EL CREDITO TIENE UNA PROMESAS DE PAGO PERO NO HA REPORTADO NINGUN PAGO
      else if (buscarPromesaIncumplida(c.getIdCredito())) {
        campana = 11;
      } // CASO 3: ULTIMA GESTION TIENE CONTACTO (CALIFICACION 2) Y HA REALIZADO PAGOS
      else if ((!gestionesCredito.isEmpty()) && (gestionesCredito.get(gestionesCredito.size() - 1).getDescripcionGestion().getCalificacion() != null) && (gestionesCredito.get(gestionesCredito.size() - 1).getDescripcionGestion().getCalificacion() == 2) && (!pagoDao.buscarPagosPorCredito(c.getIdCredito()).isEmpty())) {
        campana = 3;
      } // CASO 2: HA TENIDO CONTACTO (CALIFICACION 2)
      else if ((!gestionesCredito.isEmpty()) && (buscarHaTenidoContacto(gestionesCredito))) {
        campana = 2;
      } // CASO 6: NO HAY CONTACTO PERO HA REALIZADO PAGOS
      else if ((sinContactoTotal(gestionesCredito)) && (!pagoDao.buscarPagosPorCredito(c.getIdCredito()).isEmpty())) {
        campana = 6;
      } // CASO 4: ULTIMA GESTION NO TIENE CONTACTO (CALIFICACION 3) PERO HA TENIDO GESTIONES CON CONTACTO (CALIFICACION 2)
      else if ((!gestionesCredito.isEmpty()) && (gestionesCredito.get(gestionesCredito.size() - 1).getDescripcionGestion().getCalificacion() != null) && (gestionesCredito.get(gestionesCredito.size() - 1).getDescripcionGestion().getCalificacion() == 3) && (buscarHaTenidoContacto(gestionesCredito))) {
        campana = 4;
      } // CASO 5: EN LAS ULTIMAS 7 GESTIONES NO HA TENIDO CONTACTO
      else if (sinContactoTotal(gestionesCredito)) {
        campana = 5;
      } // CASO 16: EL MARCAJE DE LAS CUENTAS ES LOCALIZACION
      else if (c.getMarcaje() == marcajeDao.buscarMarcajePorId(Marcajes.LOCALIZACION)) {
        campana = 16;
      } else {
        campana = campanaActual;
      }
      if (campana != campanaActual) {
        Campana camp;
        camp = campanaDao.buscarPorId(campana);
        c.setCampana(camp);
        if (creditoDao.editar(c)) {
          if (historialDao.insertarHistorial(c.getIdCredito(), "Automatico. Cambio de campaña.")) {
            Logs.log.info("Credito " + c.getNumeroCredito() + " cambio de campaña.");
          } else {
            Logs.log.error("El cambio de campaña no fue registrado en el historial");
          }
        } else {
          Logs.log.error("Credito " + c.getNumeroCredito() + " no cambio de campaña");
        }
      }
    }
  }

  // METODOS PARA VERIFICAR CAMPAÑAS
  // METODO QUE VERIFICA SI EN TODAS LAS GESTIONES HA HABIDO UNA CON CONTACTO
  public boolean buscarHaTenidoContacto(List<Gestion> gestiones) {
    boolean ok = false;
    if (!gestiones.isEmpty()) {
      for (int i = 0; i < (gestiones.size()); i++) {
        if (gestiones.get(i).getDescripcionGestion().getCalificacion() != null) {
          if (gestiones.get(i).getDescripcionGestion().getCalificacion() == 2) {
            ok = true;
            break;
          }
        }
      }
    }
    return ok;
  }

  // METODO QUE VERIFICA SI EN LAS ULTIMAS 7 O EN TODAS LAS GESTIONES NO HA EXISTIDO CONTACTO
  public boolean sinContactoTotal(List<Gestion> gestiones) {
    if (!gestiones.isEmpty()) {
      if (gestiones.size() >= 7) {
        List<Gestion> lista = gestiones.subList(gestiones.size() - 8, gestiones.size() - 1);
        return !buscarHaTenidoContacto(lista);
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  // METODO QUE VERIFICA SI UN CONVENIO HA SIDO INCUMPLIDO
  public boolean buscarConvenioIncompleto(int idCredito) {
    boolean ok = false;
    Pago p = pagoDao.buscarPagoHoy(idCredito);
    if (p != null) {
      PromesaPago promesa = new PromesaPago();
      List<PromesaPago> promesas = promesaPagoDao.promesasPagoHoy(idCredito);
      for (int i = 0; i < (promesas.size()); i++) {
        if (promesas.get(i).getIdPromesaPago() == p.getPromesaPago().getIdPromesaPago().intValue()) {
          promesa = promesas.get(i);
          break;
        }
      }
      if (p.getMontoPago() < promesa.getCantidadPrometida()) {
        ok = true;
      }
    }
    return ok;
  }

  // METODO QUE VERIFICA SI SE HA CUMPLIDO UNA PROMESA DE PAGO
  public boolean buscarPromesaCumplida(int idCredito) {
    boolean ok = false;
    Pago p = pagoDao.buscarPagoHoy(idCredito);
    if (p != null) {
      PromesaPago promesa = new PromesaPago();
      List<PromesaPago> promesas = promesaPagoDao.promesasPagoHoy(idCredito);
      for (int i = 0; i < (promesas.size()); i++) {
        if (promesas.get(i).getIdPromesaPago() == p.getPromesaPago().getIdPromesaPago().intValue()) {
          promesa = promesas.get(i);
          break;
        }
      }
      if (p.getMontoPago() == promesa.getCantidadPrometida()) {
        ok = true;
      }
    }
    return ok;
  }

  // METODO QUE VERIFICA SI UN CREDITO INCUMPLIO SU PROMESA DE PAGO
  public boolean buscarPromesaIncumplida(int idCredito) {
    boolean ok = false;
    if ((promesaPagoDao.buscarPromesasHoy(idCredito)) && (pagoDao.buscarPagoHoy(idCredito) == null)) {
      ok = true;
    } else {
      List<PromesaPago> promesas = promesaPagoDao.buscarPromesasAnterioresCredito(idCredito);
      for (int i = 0; i < (promesas.size()); i++) {
        if (pagoDao.buscarPagoFechaCredito(promesas.get(i).getFechaPrometida(), idCredito) == null) {
          ok = true;
          break;
        }
      }
    }
    return ok;
  }

  // METODO QUE OBTIENE LA LISTA DE CREDITOS SEGUN LA CAMPAÑA ELEGIDA
  public void preparaCampana() {
    posicion = 0;
    creditosCampana = creditoDao.buscarCreditosPorCampanaGestor(seleccion.getIdCampana(), indexBean.getUsuario().getIdUsuario());
    if (!creditosCampana.isEmpty()) {
      for (int i = 0; i < (creditosCampana.size()); i++) {
        if (!gestionDao.buscarGestionHoy(creditosCampana.get(i).getIdCredito())) {
          posicion = i;
          break;
        }
      }
      creditoActualBean.setCreditoActual(creditosCampana.get(posicion));
      try {
        FacesContext.getCurrentInstance().getExternalContext().redirect("vistaCampanaActual.xhtml");
      } catch (IOException ioe) {
        Logs.log.error("No se pudo redirigir a la vista de la campaña actual.");
        Logs.log.error(ioe);
      }
    }
  }

  // GETTERS & SETTERS
  public List<CreditoCampana> getListaCreditosCampanas() {
    return listaCreditosCampanas;
  }

  public void setListaCreditosCampanas(List<CreditoCampana> listaCreditosCampanas) {
    this.listaCreditosCampanas = listaCreditosCampanas;
  }

  public CreditoCampana getSeleccion() {
    return seleccion;
  }

  public void setSeleccion(CreditoCampana seleccion) {
    this.seleccion = seleccion;
  }

  public List<Credito> getCreditosCampana() {
    return creditosCampana;
  }

  public void setCreditosCampana(List<Credito> creditosCampana) {
    this.creditosCampana = creditosCampana;
  }

  public int getPosicion() {
    return posicion;
  }

  public void setPosicion(int posicion) {
    this.posicion = posicion;
  }

  public boolean isHabilitaCampanas() {
    return habilitaCampanas;
  }

  public void setHabilitaCampanas(boolean habilitaCampanas) {
    this.habilitaCampanas = habilitaCampanas;
  }

  // CLASE MIEMBRO PARA PODER LLENAR LA TABLA
  public static class CreditoCampana {

    // VARIABLES DE CLASE
    private int idCampana;
    private String nombreCampana;
    private int cuentasEnCampana;
    private int nuevasEnCampana;
    private int progresoCampana;
    private int verdes;
    private int amarillas;
    private int rojas;

    // CONSTRUCTOR
    public CreditoCampana() {
    }

    // GETTERS & SETTERS
    public int getIdCampana() {
      return idCampana;
    }

    public void setIdCampana(int idCampana) {
      this.idCampana = idCampana;
    }

    public String getNombreCampana() {
      return nombreCampana;
    }

    public void setNombreCampana(String nombreCampana) {
      this.nombreCampana = nombreCampana;
    }

    public int getCuentasEnCampana() {
      return cuentasEnCampana;
    }

    public void setCuentasEnCampana(int cuentasEnCampana) {
      this.cuentasEnCampana = cuentasEnCampana;
    }

    public int getProgresoCampana() {
      return progresoCampana;
    }

    public void setProgresoCampana(int progresoCampana) {
      this.progresoCampana = progresoCampana;
    }

    public int getNuevasEnCampana() {
      return nuevasEnCampana;
    }

    public void setNuevasEnCampana(int nuevasEnCampana) {
      this.nuevasEnCampana = nuevasEnCampana;
    }

    public int getVerdes() {
      return verdes;
    }

    public void setVerdes(int verdes) {
      this.verdes = verdes;
    }

    public int getAmarillas() {
      return amarillas;
    }

    public void setAmarillas(int amarillas) {
      this.amarillas = amarillas;
    }

    public int getRojas() {
      return rojas;
    }

    public void setRojas(int rojas) {
      this.rojas = rojas;
    }

  }

}