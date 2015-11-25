package dto;
// Generated 25/11/2015 11:44:49 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Devolucion generated by hbm2java
 */
public class Devolucion  implements java.io.Serializable {


     private Integer idDevolucion;
     private Date fecha;
     private int idCredito;
     private int idConceptoDevolucion;
     private String observaciones;
     private String solicitante;
     private String revisor;
     private int estatus;

    public Devolucion() {
    }

	
    public Devolucion(Date fecha, int idCredito, int idConceptoDevolucion, String solicitante, int estatus) {
        this.fecha = fecha;
        this.idCredito = idCredito;
        this.idConceptoDevolucion = idConceptoDevolucion;
        this.solicitante = solicitante;
        this.estatus = estatus;
    }
    public Devolucion(Date fecha, int idCredito, int idConceptoDevolucion, String observaciones, String solicitante, String revisor, int estatus) {
       this.fecha = fecha;
       this.idCredito = idCredito;
       this.idConceptoDevolucion = idConceptoDevolucion;
       this.observaciones = observaciones;
       this.solicitante = solicitante;
       this.revisor = revisor;
       this.estatus = estatus;
    }
   
    public Integer getIdDevolucion() {
        return this.idDevolucion;
    }
    
    public void setIdDevolucion(Integer idDevolucion) {
        this.idDevolucion = idDevolucion;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public int getIdCredito() {
        return this.idCredito;
    }
    
    public void setIdCredito(int idCredito) {
        this.idCredito = idCredito;
    }
    public int getIdConceptoDevolucion() {
        return this.idConceptoDevolucion;
    }
    
    public void setIdConceptoDevolucion(int idConceptoDevolucion) {
        this.idConceptoDevolucion = idConceptoDevolucion;
    }
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public String getSolicitante() {
        return this.solicitante;
    }
    
    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }
    public String getRevisor() {
        return this.revisor;
    }
    
    public void setRevisor(String revisor) {
        this.revisor = revisor;
    }
    public int getEstatus() {
        return this.estatus;
    }
    
    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }




}


