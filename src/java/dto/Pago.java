package dto;
// Generated 20/04/2016 12:24:33 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Pago generated by hbm2java
 */
public class Pago  implements java.io.Serializable {


     private Integer idPago;
     private Gestor gestor;
     private PromesaPago promesaPago;
     private Quincena quincena;
     private Date fechaDeposito;
     private Date fechaRegistro;
     private int estatus;
     private float monto;
     private String numeroCuenta;
     private String nombreComprobante;
     private String revisor;
     private String observaciones;

    public Pago() {
    }

	
    public Pago(Gestor gestor, PromesaPago promesaPago, Quincena quincena, Date fechaDeposito, Date fechaRegistro, int estatus, float monto, String numeroCuenta, String nombreComprobante, String revisor) {
        this.gestor = gestor;
        this.promesaPago = promesaPago;
        this.quincena = quincena;
        this.fechaDeposito = fechaDeposito;
        this.fechaRegistro = fechaRegistro;
        this.estatus = estatus;
        this.monto = monto;
        this.numeroCuenta = numeroCuenta;
        this.nombreComprobante = nombreComprobante;
        this.revisor = revisor;
    }
    public Pago(Gestor gestor, PromesaPago promesaPago, Quincena quincena, Date fechaDeposito, Date fechaRegistro, int estatus, float monto, String numeroCuenta, String nombreComprobante, String revisor, String observaciones) {
       this.gestor = gestor;
       this.promesaPago = promesaPago;
       this.quincena = quincena;
       this.fechaDeposito = fechaDeposito;
       this.fechaRegistro = fechaRegistro;
       this.estatus = estatus;
       this.monto = monto;
       this.numeroCuenta = numeroCuenta;
       this.nombreComprobante = nombreComprobante;
       this.revisor = revisor;
       this.observaciones = observaciones;
    }
   
    public Integer getIdPago() {
        return this.idPago;
    }
    
    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }
    public Gestor getGestor() {
        return this.gestor;
    }
    
    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }
    public PromesaPago getPromesaPago() {
        return this.promesaPago;
    }
    
    public void setPromesaPago(PromesaPago promesaPago) {
        this.promesaPago = promesaPago;
    }
    public Quincena getQuincena() {
        return this.quincena;
    }
    
    public void setQuincena(Quincena quincena) {
        this.quincena = quincena;
    }
    public Date getFechaDeposito() {
        return this.fechaDeposito;
    }
    
    public void setFechaDeposito(Date fechaDeposito) {
        this.fechaDeposito = fechaDeposito;
    }
    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }
    
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public int getEstatus() {
        return this.estatus;
    }
    
    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
    public float getMonto() {
        return this.monto;
    }
    
    public void setMonto(float monto) {
        this.monto = monto;
    }
    public String getNumeroCuenta() {
        return this.numeroCuenta;
    }
    
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    public String getNombreComprobante() {
        return this.nombreComprobante;
    }
    
    public void setNombreComprobante(String nombreComprobante) {
        this.nombreComprobante = nombreComprobante;
    }
    public String getRevisor() {
        return this.revisor;
    }
    
    public void setRevisor(String revisor) {
        this.revisor = revisor;
    }
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }




}


