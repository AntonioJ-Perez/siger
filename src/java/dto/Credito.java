package dto;
// Generated 25/02/2016 03:00:35 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Credito generated by hbm2java
 */
public class Credito  implements java.io.Serializable {


     private int idCredito;
     private Campana campana;
     private Despacho despacho;
     private Deudor deudor;
     private Gestor gestor;
     private Producto producto;
     private Subproducto subproducto;
     private String numeroCredito;
     private Date fechaInicio;
     private Date fechaFin;
     private Date fechaQuebranto;
     private Float monto;
     private Float mensualidad;
     private Float tasaInteres;
     private Integer diasMora;
     private String numeroCuenta;
     private int tipoCredito;
     private int marcaje;
     private Set devolucions = new HashSet(0);
     private Set historials = new HashSet(0);
     private Set actualizacions = new HashSet(0);
     private Set gestions = new HashSet(0);
     private Set autos = new HashSet(0);
     private Set convenioPagos = new HashSet(0);
     private Set lineas = new HashSet(0);

    public Credito() {
    }

	
    public Credito(Campana campana, Despacho despacho, Deudor deudor, Gestor gestor, Producto producto, String numeroCredito, int tipoCredito, int marcaje) {
        this.campana = campana;
        this.despacho = despacho;
        this.deudor = deudor;
        this.gestor = gestor;
        this.producto = producto;
        this.numeroCredito = numeroCredito;
        this.tipoCredito = tipoCredito;
        this.marcaje = marcaje;
    }
    public Credito(Campana campana, Despacho despacho, Deudor deudor, Gestor gestor, Producto producto, Subproducto subproducto, String numeroCredito, Date fechaInicio, Date fechaFin, Date fechaQuebranto, Float monto, Float mensualidad, Float tasaInteres, Integer diasMora, String numeroCuenta, int tipoCredito, int marcaje, Set devolucions, Set historials, Set actualizacions, Set gestions, Set autos, Set convenioPagos, Set lineas) {
       this.campana = campana;
       this.despacho = despacho;
       this.deudor = deudor;
       this.gestor = gestor;
       this.producto = producto;
       this.subproducto = subproducto;
       this.numeroCredito = numeroCredito;
       this.fechaInicio = fechaInicio;
       this.fechaFin = fechaFin;
       this.fechaQuebranto = fechaQuebranto;
       this.monto = monto;
       this.mensualidad = mensualidad;
       this.tasaInteres = tasaInteres;
       this.diasMora = diasMora;
       this.numeroCuenta = numeroCuenta;
       this.tipoCredito = tipoCredito;
       this.marcaje = marcaje;
       this.devolucions = devolucions;
       this.historials = historials;
       this.actualizacions = actualizacions;
       this.gestions = gestions;
       this.autos = autos;
       this.convenioPagos = convenioPagos;
       this.lineas = lineas;
    }
   
    public int getIdCredito() {
        return this.idCredito;
    }
    
    public void setIdCredito(int idCredito) {
        this.idCredito = idCredito;
    }
    public Campana getCampana() {
        return this.campana;
    }
    
    public void setCampana(Campana campana) {
        this.campana = campana;
    }
    public Despacho getDespacho() {
        return this.despacho;
    }
    
    public void setDespacho(Despacho despacho) {
        this.despacho = despacho;
    }
    public Deudor getDeudor() {
        return this.deudor;
    }
    
    public void setDeudor(Deudor deudor) {
        this.deudor = deudor;
    }
    public Gestor getGestor() {
        return this.gestor;
    }
    
    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }
    public Producto getProducto() {
        return this.producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Subproducto getSubproducto() {
        return this.subproducto;
    }
    
    public void setSubproducto(Subproducto subproducto) {
        this.subproducto = subproducto;
    }
    public String getNumeroCredito() {
        return this.numeroCredito;
    }
    
    public void setNumeroCredito(String numeroCredito) {
        this.numeroCredito = numeroCredito;
    }
    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public Date getFechaQuebranto() {
        return this.fechaQuebranto;
    }
    
    public void setFechaQuebranto(Date fechaQuebranto) {
        this.fechaQuebranto = fechaQuebranto;
    }
    public Float getMonto() {
        return this.monto;
    }
    
    public void setMonto(Float monto) {
        this.monto = monto;
    }
    public Float getMensualidad() {
        return this.mensualidad;
    }
    
    public void setMensualidad(Float mensualidad) {
        this.mensualidad = mensualidad;
    }
    public Float getTasaInteres() {
        return this.tasaInteres;
    }
    
    public void setTasaInteres(Float tasaInteres) {
        this.tasaInteres = tasaInteres;
    }
    public Integer getDiasMora() {
        return this.diasMora;
    }
    
    public void setDiasMora(Integer diasMora) {
        this.diasMora = diasMora;
    }
    public String getNumeroCuenta() {
        return this.numeroCuenta;
    }
    
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    public int getTipoCredito() {
        return this.tipoCredito;
    }
    
    public void setTipoCredito(int tipoCredito) {
        this.tipoCredito = tipoCredito;
    }
    public int getMarcaje() {
        return this.marcaje;
    }
    
    public void setMarcaje(int marcaje) {
        this.marcaje = marcaje;
    }
    public Set getDevolucions() {
        return this.devolucions;
    }
    
    public void setDevolucions(Set devolucions) {
        this.devolucions = devolucions;
    }
    public Set getHistorials() {
        return this.historials;
    }
    
    public void setHistorials(Set historials) {
        this.historials = historials;
    }
    public Set getActualizacions() {
        return this.actualizacions;
    }
    
    public void setActualizacions(Set actualizacions) {
        this.actualizacions = actualizacions;
    }
    public Set getGestions() {
        return this.gestions;
    }
    
    public void setGestions(Set gestions) {
        this.gestions = gestions;
    }
    public Set getAutos() {
        return this.autos;
    }
    
    public void setAutos(Set autos) {
        this.autos = autos;
    }
    public Set getConvenioPagos() {
        return this.convenioPagos;
    }
    
    public void setConvenioPagos(Set convenioPagos) {
        this.convenioPagos = convenioPagos;
    }
    public Set getLineas() {
        return this.lineas;
    }
    
    public void setLineas(Set lineas) {
        this.lineas = lineas;
    }




}


