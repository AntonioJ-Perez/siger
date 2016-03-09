/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ConceptoDevolucionDAO;
import dao.ContactoDAO;
import dao.ConvenioPagoDAO;
import dao.CreditoDAO;
import dao.DireccionDAO;
import dao.EmailDAO;
import dao.GestionDAO;
import dao.GestorDAO;
import dao.HistorialDAO;
import dao.MotivoDevolucionDAO;
import dao.TelefonoDAO;
import dto.ConceptoDevolucion;
import dto.Contacto;
import dto.ConvenioPago;
import dto.Credito;
import dto.Devolucion;
import dto.Direccion;
import dto.Email;
import dto.Gestion;
import dto.Gestor;
import dto.Historial;
import dto.MotivoDevolucion;
import dto.Telefono;
import impl.ConceptoDevolucionIMPL;
import impl.ContactoIMPL;
import impl.ConvenioPagoIMPL;
import impl.CreditoIMPL;
import impl.DireccionIMPL;
import impl.EmailIMPL;
import impl.GestionIMPL;
import impl.GestorIMPL;
import impl.HistorialIMPL;
import impl.MotivoDevolucionIMPL;
import impl.TelefonoIMPL;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import util.constantes.Convenios;
import util.constantes.Devoluciones;
import util.constantes.Perfiles;
import util.constantes.TipoCreditos;

/**
 *
 * @author Eduardo
 */
@ManagedBean(name = "vistaCreditoBean")
@ViewScoped
public class VistaCreditoBean implements Serializable {

  // LLAMADA A OTROS BEANS
  ELContext elContext = FacesContext.getCurrentInstance().getELContext();
  IndexBean indexBean = (IndexBean) elContext.getELResolver().getValue(elContext, null, "indexBean");

  // VARIABLES DE CLASE
  private CuentasBean cuentasBean;
  private CuentasGestorBean cuentasGestorBean;
  private boolean adminVisible;
  private String observaciones;
  private String nombreDeudor;
  private String numeroCredito;
  private String numeroCreditos;
  private String calleNumero;
  private String coloniaMunicipio;
  private String estadoCP;
  private String telefono;
  private String correo;
  private String fechaInicio;
  private String fechaFin;
  private String fup;
  private String fvp;
  private float mensualidad;
  private int mesesVencidos;
  private float saldoVencido;
  private Credito creditoActual;
  private Gestor gestorSeleccionado;
  private Gestor gestorActual;
  private ConceptoDevolucion conceptoSeleccionado;
  private MotivoDevolucion motivoSeleccionado;
  private final CreditoDAO creditoDao;
  private final DireccionDAO direccionDAO;
  private final TelefonoDAO telefonoDAO;
  private final EmailDAO emailDAO;
  private final ContactoDAO contactoDAO;
  private final HistorialDAO historialDao;
  private final GestionDAO gestionDao;
  private final GestorDAO gestorDao;
  private final ConvenioPagoDAO convenioPagoDao;
  private final ConceptoDevolucionDAO conceptoDevolucionDao;
  private final MotivoDevolucionDAO motivoDevolucionDao;
  private List<Gestion> listaGestiones;
  private List<Gestor> listaGestores;
  private List<Credito> creditosRelacionados;
  private List<Credito> credsRelacionados;
  private List<Direccion> listaDirecciones;
  private List<Telefono> listaTelefonos;
  private List<Email> listaCorreos;
  private List<Contacto> listaContactos;
  private List<Historial> historial;
  private List<ConceptoDevolucion> listaConceptos;
  private List<MotivoDevolucion> listaMotivos;

  public VistaCreditoBean() {
    conceptoSeleccionado = new ConceptoDevolucion();
    motivoSeleccionado = new MotivoDevolucion();
    creditoActual = new Credito();
    gestorSeleccionado = new Gestor();
    gestorActual = new Gestor();
    creditoDao = new CreditoIMPL();
    direccionDAO = new DireccionIMPL();
    telefonoDAO = new TelefonoIMPL();
    emailDAO = new EmailIMPL();
    contactoDAO = new ContactoIMPL();
    historialDao = new HistorialIMPL();
    gestionDao = new GestionIMPL();
    gestorDao = new GestorIMPL();
    convenioPagoDao = new ConvenioPagoIMPL();
    conceptoDevolucionDao = new ConceptoDevolucionIMPL();
    motivoDevolucionDao = new MotivoDevolucionIMPL();
    listaMotivos = new ArrayList();
    creditosRelacionados = new ArrayList();
    credsRelacionados = new ArrayList();
    listaDirecciones = new ArrayList();
    historial = new ArrayList();
    listaGestiones = new ArrayList();
    listaGestores = new ArrayList();
    listaGestores = gestorDao.buscarPorDespacho(indexBean.getUsuario().getDespacho().getIdDespacho());
    listaConceptos = conceptoDevolucionDao.obtenerConceptos();
    obtenerDatos();
  }

  // METODO QUE OBTENDRA TODOS LOS DATOS PRIMARIOS SEGUN EL CREDITO SELECCIONADO EN LA VISTA cuentas.xhtml
  private void obtenerDatos() {
    if (indexBean.getUsuario().getPerfil() == Perfiles.GESTOR) {
      adminVisible = false;
      cuentasGestorBean = (CuentasGestorBean) elContext.getELResolver().getValue(elContext, null, "cuentasGestorBean");
      creditoActual = cuentasGestorBean.getCreditoActual();

    } else {
      adminVisible = true;
      cuentasBean = (CuentasBean) elContext.getELResolver().getValue(elContext, null, "cuentasBean");
      creditoActual = cuentasBean.getCreditoSeleccionado();
    }
    // OBTIENE LA CADENA CON EL NUMERO DE CREDITO
    numeroCredito = creditoActual.getNumeroCredito();
    // BUSCAMOS EL GESTOR ACTUAL DEL CREDITO
    gestorActual = gestorDao.buscarGestorDelCredito(creditoActual.getIdCredito());
    // OBTENEMOS EL ID DEL SUJETO PARA TODAS LAS OPERACIONES
    int idSujeto = creditoActual.getDeudor().getSujeto().getIdSujeto();
    // OBTIENE LA CADENA CON EL NOMBRE DEL DEUDOR
    nombreDeudor = creditoActual.getDeudor().getSujeto().getNombreRazonSocial();
    // OBTENER LA PRIMER DIRECCION DEL DEUDOR
    Direccion d;
    try {
      d = direccionDAO.buscarPorSujeto(idSujeto).get(0);
      calleNumero = d.getCalle() + ",";
      coloniaMunicipio = d.getColonia().getNombre() + ",  " + d.getMunicipio().getNombre() + ",";
      estadoCP = d.getEstadoRepublica().getNombre() + ",  C.P. " + d.getColonia().getCodigoPostal();
    } catch (Exception e) {
    }
    // OBTENEMOS EL NUMERO DE CREDITOS PARA ESTE CLIENTE
    numeroCreditos = Integer.toString(creditoDao.buscarCreditosRelacionados(creditoActual).size() + 1);
    // OBTENEMOS LAS DIFERENTES FECHAS QUE SE REQUIEREN
    try {
      // OBTENER EL PRIMER TELEFONO DEL DEUDOR
      Telefono tel;
      tel = telefonoDAO.buscarPorSujeto(idSujeto).get(0);
      String fon = tel.getNumero();
      telefono = "(" + fon.substring(0, (fon.length()) - 7) + ") - " + fon.substring((fon.length()) - 7, fon.length() - 4) + " - " + fon.substring(fon.length() - 4, fon.length());
      // OBTENER EL PRIMER CORREO DEL DEUDOR
      Email mail;
      mail = emailDAO.buscarPorSujeto(idSujeto).get(0);
      correo = mail.getDireccion();
      DateFormat df = new SimpleDateFormat("dd de MM del yyyy");
      fechaInicio = df.format(creditoActual.getFechaInicio());
      fechaFin = df.format(creditoActual.getFechaFin());
      fup = "";
      fvp = "";
    } catch (Exception e) {
    }
    mensualidad = creditoActual.getMensualidad();
    mesesVencidos = 3;
    saldoVencido = mensualidad * mesesVencidos;
    // OBTENEMOS LA LISTA DE GESTIONES PREVIAS
    obtenerGestionesAnteriores();
    // OBTENEMOS LA LISTA DE LAS DIRECCIONES DE ESTE DEUDOR, SI ES QUE EXISTE TAL LISTA
    listaDirecciones = direccionDAO.buscarPorSujeto(idSujeto);
    // OBTENEMOS LA LISTA DE CREDITOS RELACIONADOS
    creditosRelacionados = creditoDao.buscarCreditosRelacionados(creditoActual);
    // OBTENEMOS LA LISTA DE TELEFONOS DEL DEUDOR
    listaTelefonos = telefonoDAO.buscarPorSujeto(idSujeto);
    // OBTENEMOS LA LISTA DE CORREOS ELECTRONICOS DEL DEUDOR
    listaCorreos = emailDAO.buscarPorSujeto(idSujeto);
    // OBTENEMOS LA LISTA DE CONTACTOS DEL DEUDOR
    listaContactos = contactoDAO.buscarContactoPorSujeto(idSujeto);
    // OBTENEMOS EL HISTORIAL DEL CREDITO
    historial = historialDao.buscarHistorialPorIdCredito(creditoActual.getIdCredito());
  }

  // METODO PARA ABRIR EL DIALOGO
  public void confirmar() {
    RequestContext.getCurrentInstance().execute("PF('confirmacionDialog2').show();");
  }

  // METODO PARA CERRAR EL DIALOGO
  public void cerrar() {
    gestorSeleccionado = gestorActual;
    RequestContext.getCurrentInstance().update("barraDetalleCreditoForm:listaGestoresVistaCredito");
    RequestContext.getCurrentInstance().execute("PF('confirmacionDialog2').hide();");
  }

  // METODO QUE REASIGNARA AL GESTOR
  public void reasignarGestor() {
    FacesContext contexto = FacesContext.getCurrentInstance();
    // FINALIZAMOS EL CONVENIO EN CURSO DEL GESTOR ACTUAL PARA ESTE CREDITO
    ConvenioPago convenio = convenioPagoDao.buscarConvenioEnCursoCredito(creditoActual.getIdCredito());
    boolean ok;
    ConvenioPago c = convenio;
    c.setEstatus(Convenios.FINALIZADO);
    ok = convenioPagoDao.editar(c);
    // CAMBIAMOS EL GESTOR ASIGNADO ACTUALMENTE
    Credito cred = creditoActual;
    Gestor nuevoGestor = gestorDao.buscar(gestorSeleccionado.getIdGestor());
    cred.setGestor(nuevoGestor);
    ok = ok & (creditoDao.editar(cred));
    // ESCRIBIMOS EN EL HISTORIAL LA REASIGNACION
    Historial h = new Historial();
    h.setCredito(creditoActual);
    Date fecha = new Date();
    h.setFecha(fecha);
    String evento = "El administrador: " + indexBean.getUsuario().getNombreLogin() + " reasigno el credito del gestor " + gestorActual.getUsuario().getNombreLogin() + " al gestor " + nuevoGestor.getUsuario().getNombreLogin();
    h.setEvento(evento);
    ok = ok & (historialDao.insertarHistorial(creditoActual.getIdCredito(), evento));
    if (ok) {
      contexto.addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacion exitosa.", "Se reasigno el credito."));
      gestorActual = nuevoGestor;
    } else {
      contexto.addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error.", "No se reasigno el credito. Contacte al equipo de sistemas."));
    }
    cerrar();
  }

  // METODO QUE LE DA UNA ETIQUETA A LOS VALORES NUMERICOS DEL TIPO DE CREDITO
  public String etiquetarTipoCredito(int tipoCredito) {
    String tipo = null;
    if (tipoCredito == TipoCreditos.LINEA_TELEFONICA) {
      tipo = "Linea telefonica";
    }
    return tipo;
  }

  // METODO QUE OBTIENE LA LISTA DE GESTIONES
  public void obtenerGestionesAnteriores() {
    if (indexBean.getUsuario().getPerfil() == Perfiles.GESTOR) {
      listaGestiones = gestionDao.buscarGestionesCreditoGestor(indexBean.getUsuario().getIdUsuario(), creditoActual.getIdCredito());
    } else {
      listaGestiones = gestionDao.buscarGestionesCredito(creditoActual.getIdCredito());
    }
  }

  // METODO QUE OBTIENE LOS MOTIVOS DE DEVOLUCION DEPENDIENDO DEL CONCEPTO SELECCIONADO
  public void preparaMotivos(){
    conceptoSeleccionado = conceptoDevolucionDao.buscarPorId(conceptoSeleccionado.getIdConceptoDevolucion());
    listaMotivos = motivoDevolucionDao.obtenerMotivosPorConcepto(conceptoSeleccionado.getIdConceptoDevolucion());
  }
  
  // METODO QUE MANDA UN CREDITO A DEVOLUCION
  public void devolverCredito(){
    Devolucion d = new Devolucion();
    d.setCredito(creditoActual);
    // SI ES ADMIN QUE SE DEVUELVA DIRECTAMENTE
    d.setEstatus(Devoluciones.PENDIENTE);
    d.setObservaciones(observaciones);
    d.setConceptoDevolucion(conceptoSeleccionado);
  }
  
  // ***********************************************************************************************************************
  // ***********************************************************************************************************************
  // ***********************************************************************************************************************
  // GETTERS & SETTERS
  public String getNombreDeudor() {
    return nombreDeudor;
  }

  public void setNombreDeudor(String nombreDeudor) {
    this.nombreDeudor = nombreDeudor;
  }

  public String getNumeroCredito() {
    return numeroCredito;
  }

  public void setNumeroCredito(String numeroCredito) {
    this.numeroCredito = numeroCredito;
  }

  public String getNumeroCreditos() {
    return numeroCreditos;
  }

  public void setNumeroCreditos(String numeroCreditos) {
    this.numeroCreditos = numeroCreditos;
  }

  public String getCalleNumero() {
    return calleNumero;
  }

  public void setCalleNumero(String calleNumero) {
    this.calleNumero = calleNumero;
  }

  public String getColoniaMunicipio() {
    return coloniaMunicipio;
  }

  public void setColoniaMunicipio(String coloniaMunicipio) {
    this.coloniaMunicipio = coloniaMunicipio;
  }

  public String getEstadoCP() {
    return estadoCP;
  }

  public void setEstadoCP(String estadoCP) {
    this.estadoCP = estadoCP;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getFechaInicio() {
    return fechaInicio;
  }

  public void setFechaInicio(String fechaInicio) {
    this.fechaInicio = fechaInicio;
  }

  public String getFechaFin() {
    return fechaFin;
  }

  public void setFechaFin(String fechaFin) {
    this.fechaFin = fechaFin;
  }

  public String getFup() {
    return fup;
  }

  public void setFup(String fup) {
    this.fup = fup;
  }

  public String getFvp() {
    return fvp;
  }

  public void setFvp(String fvp) {
    this.fvp = fvp;
  }

  public float getMensualidad() {
    return mensualidad;
  }

  public void setMensualidad(float mensualidad) {
    this.mensualidad = mensualidad;
  }

  public float getSaldoVencido() {
    return saldoVencido;
  }

  public void setSaldoVencido(float saldoVencido) {
    this.saldoVencido = saldoVencido;
  }

  public Credito getCreditoActual() {
    return creditoActual;
  }

  public void setCreditoActual(Credito creditoActual) {
    this.creditoActual = creditoActual;
  }

  public Gestor getGestorSeleccionado() {
    return gestorSeleccionado;
  }

  public void setGestorSeleccionado(Gestor gestorSeleccionado) {
    this.gestorSeleccionado = gestorSeleccionado;
  }

  public Gestor getGestorActual() {
    return gestorActual;
  }

  public void setGestorActual(Gestor gestorActual) {
    this.gestorActual = gestorActual;
  }

  public List<Gestion> getListaGestiones() {
    return listaGestiones;
  }

  public void setListaGestiones(List<Gestion> listaGestiones) {
    this.listaGestiones = listaGestiones;
  }

  public List<Gestor> getListaGestores() {
    return listaGestores;
  }

  public void setListaGestores(List<Gestor> listaGestores) {
    this.listaGestores = listaGestores;
  }

  public List<Credito> getCreditosRelacionados() {
    return creditosRelacionados;
  }

  public void setCreditosRelacionados(List<Credito> creditosRelacionados) {
    this.creditosRelacionados = creditosRelacionados;
  }

  public List<Credito> getCredsRelacionados() {
    return credsRelacionados;
  }

  public void setCredsRelacionados(List<Credito> credsRelacionados) {
    this.credsRelacionados = credsRelacionados;
  }

  public List<Direccion> getListaDirecciones() {
    return listaDirecciones;
  }

  public void setListaDirecciones(List<Direccion> listaDirecciones) {
    this.listaDirecciones = listaDirecciones;
  }

  public List<Telefono> getListaTelefonos() {
    return listaTelefonos;
  }

  public void setListaTelefonos(List<Telefono> listaTelefonos) {
    this.listaTelefonos = listaTelefonos;
  }

  public List<Email> getListaCorreos() {
    return listaCorreos;
  }

  public void setListaCorreos(List<Email> listaCorreos) {
    this.listaCorreos = listaCorreos;
  }

  public List<Contacto> getListaContactos() {
    return listaContactos;
  }

  public void setListaContactos(List<Contacto> listaContactos) {
    this.listaContactos = listaContactos;
  }

  public List<Historial> getHistorial() {
    return historial;
  }

  public void setHistorial(List<Historial> historial) {
    this.historial = historial;
  }

  public int getMesesVencidos() {
    return mesesVencidos;
  }

  public void setMesesVencidos(int mesesVencidos) {
    this.mesesVencidos = mesesVencidos;
  }

  public boolean isAdminVisible() {
    return adminVisible;
  }

  public void setAdminVisible(boolean adminVisible) {
    this.adminVisible = adminVisible;
  }

  public List<ConceptoDevolucion> getListaConceptos() {
    return listaConceptos;
  }

  public void setListaConceptos(List<ConceptoDevolucion> listaConceptos) {
    this.listaConceptos = listaConceptos;
  }

  public ConceptoDevolucion getConceptoSeleccionado() {
    return conceptoSeleccionado;
  }

  public void setConceptoSeleccionado(ConceptoDevolucion conceptoSeleccionado) {
    this.conceptoSeleccionado = conceptoSeleccionado;
  }

  public MotivoDevolucion getMotivoSeleccionado() {
    return motivoSeleccionado;
  }

  public void setMotivoSeleccionado(MotivoDevolucion motivoSeleccionado) {
    this.motivoSeleccionado = motivoSeleccionado;
  }

  public List<MotivoDevolucion> getListaMotivos() {
    return listaMotivos;
  }

  public void setListaMotivos(List<MotivoDevolucion> listaMotivos) {
    this.listaMotivos = listaMotivos;
  }

  public String getObservaciones() {
    return observaciones;
  }

  public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
  }

}
