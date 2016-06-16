package dto;
// Generated 15/06/2016 09:53:16 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Actualizacion generated by hbm2java
 */
public class Actualizacion  implements java.io.Serializable {


     private Integer idActualizacion;
     private Credito credito;
     private Remesa remesa;
     private Integer mesesVencidos;
     private Float saldoVencido;
     private String estatus;
     private Date fechaUltimoPago;
     private Date fechaUltimoVencimientoPagado;
     private Set ajustes = new HashSet(0);
     private Set facs = new HashSet(0);

    public Actualizacion() {
    }

	
    public Actualizacion(Credito credito, Remesa remesa) {
        this.credito = credito;
        this.remesa = remesa;
    }
    public Actualizacion(Credito credito, Remesa remesa, Integer mesesVencidos, Float saldoVencido, String estatus, Date fechaUltimoPago, Date fechaUltimoVencimientoPagado, Set ajustes, Set facs) {
       this.credito = credito;
       this.remesa = remesa;
       this.mesesVencidos = mesesVencidos;
       this.saldoVencido = saldoVencido;
       this.estatus = estatus;
       this.fechaUltimoPago = fechaUltimoPago;
       this.fechaUltimoVencimientoPagado = fechaUltimoVencimientoPagado;
       this.ajustes = ajustes;
       this.facs = facs;
    }
   
    public Integer getIdActualizacion() {
        return this.idActualizacion;
    }
    
    public void setIdActualizacion(Integer idActualizacion) {
        this.idActualizacion = idActualizacion;
    }
    public Credito getCredito() {
        return this.credito;
    }
    
    public void setCredito(Credito credito) {
        this.credito = credito;
    }
    public Remesa getRemesa() {
        return this.remesa;
    }
    
    public void setRemesa(Remesa remesa) {
        this.remesa = remesa;
    }
    public Integer getMesesVencidos() {
        return this.mesesVencidos;
    }
    
    public void setMesesVencidos(Integer mesesVencidos) {
        this.mesesVencidos = mesesVencidos;
    }
    public Float getSaldoVencido() {
        return this.saldoVencido;
    }
    
    public void setSaldoVencido(Float saldoVencido) {
        this.saldoVencido = saldoVencido;
    }
    public String getEstatus() {
        return this.estatus;
    }
    
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    public Date getFechaUltimoPago() {
        return this.fechaUltimoPago;
    }
    
    public void setFechaUltimoPago(Date fechaUltimoPago) {
        this.fechaUltimoPago = fechaUltimoPago;
    }
    public Date getFechaUltimoVencimientoPagado() {
        return this.fechaUltimoVencimientoPagado;
    }
    
    public void setFechaUltimoVencimientoPagado(Date fechaUltimoVencimientoPagado) {
        this.fechaUltimoVencimientoPagado = fechaUltimoVencimientoPagado;
    }
    public Set getAjustes() {
        return this.ajustes;
    }
    
    public void setAjustes(Set ajustes) {
        this.ajustes = ajustes;
    }
    public Set getFacs() {
        return this.facs;
    }
    
    public void setFacs(Set facs) {
        this.facs = facs;
    }




}


