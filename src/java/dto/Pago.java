package dto;
// Generated 15/06/2016 09:53:16 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
     private float montoPago;
     private float montoAprobado;
     private String numeroCuenta;
     private String revisor;
     private String observacionGestor;
     private String observacionRevisor;
     private String informacionRevision;
     private int pagado;
     private Set comprobantePagos = new HashSet(0);

    public Pago() {
    }

	
    public Pago(Gestor gestor, PromesaPago promesaPago, Quincena quincena, Date fechaDeposito, Date fechaRegistro, int estatus, float montoPago, float montoAprobado, String numeroCuenta, String revisor, int pagado) {
        this.gestor = gestor;
        this.promesaPago = promesaPago;
        this.quincena = quincena;
        this.fechaDeposito = fechaDeposito;
        this.fechaRegistro = fechaRegistro;
        this.estatus = estatus;
        this.montoPago = montoPago;
        this.montoAprobado = montoAprobado;
        this.numeroCuenta = numeroCuenta;
        this.revisor = revisor;
        this.pagado = pagado;
    }
    public Pago(Gestor gestor, PromesaPago promesaPago, Quincena quincena, Date fechaDeposito, Date fechaRegistro, int estatus, float montoPago, float montoAprobado, String numeroCuenta, String revisor, String observacionGestor, String observacionRevisor, String informacionRevision, int pagado, Set comprobantePagos) {
       this.gestor = gestor;
       this.promesaPago = promesaPago;
       this.quincena = quincena;
       this.fechaDeposito = fechaDeposito;
       this.fechaRegistro = fechaRegistro;
       this.estatus = estatus;
       this.montoPago = montoPago;
       this.montoAprobado = montoAprobado;
       this.numeroCuenta = numeroCuenta;
       this.revisor = revisor;
       this.observacionGestor = observacionGestor;
       this.observacionRevisor = observacionRevisor;
       this.informacionRevision = informacionRevision;
       this.pagado = pagado;
       this.comprobantePagos = comprobantePagos;
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
    public float getMontoPago() {
        return this.montoPago;
    }
    
    public void setMontoPago(float montoPago) {
        this.montoPago = montoPago;
    }
    public float getMontoAprobado() {
        return this.montoAprobado;
    }
    
    public void setMontoAprobado(float montoAprobado) {
        this.montoAprobado = montoAprobado;
    }
    public String getNumeroCuenta() {
        return this.numeroCuenta;
    }
    
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    public String getRevisor() {
        return this.revisor;
    }
    
    public void setRevisor(String revisor) {
        this.revisor = revisor;
    }
    public String getObservacionGestor() {
        return this.observacionGestor;
    }
    
    public void setObservacionGestor(String observacionGestor) {
        this.observacionGestor = observacionGestor;
    }
    public String getObservacionRevisor() {
        return this.observacionRevisor;
    }
    
    public void setObservacionRevisor(String observacionRevisor) {
        this.observacionRevisor = observacionRevisor;
    }
    public String getInformacionRevision() {
        return this.informacionRevision;
    }
    
    public void setInformacionRevision(String informacionRevision) {
        this.informacionRevision = informacionRevision;
    }
    public int getPagado() {
        return this.pagado;
    }
    
    public void setPagado(int pagado) {
        this.pagado = pagado;
    }
    public Set getComprobantePagos() {
        return this.comprobantePagos;
    }
    
    public void setComprobantePagos(Set comprobantePagos) {
        this.comprobantePagos = comprobantePagos;
    }




}


