package dto;
// Generated 20/04/2016 12:24:33 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Devolucion generated by hbm2java
 */
public class Devolucion  implements java.io.Serializable {


     private Integer idDevolucion;
     private ConceptoDevolucion conceptoDevolucion;
     private Credito credito;
     private MotivoDevolucion motivoDevolucion;
     private Date fecha;
     private String observaciones;
     private String solicitante;
     private String revisor;
     private int estatus;

    public Devolucion() {
    }

	
    public Devolucion(ConceptoDevolucion conceptoDevolucion, Credito credito, MotivoDevolucion motivoDevolucion, Date fecha, String solicitante, int estatus) {
        this.conceptoDevolucion = conceptoDevolucion;
        this.credito = credito;
        this.motivoDevolucion = motivoDevolucion;
        this.fecha = fecha;
        this.solicitante = solicitante;
        this.estatus = estatus;
    }
    public Devolucion(ConceptoDevolucion conceptoDevolucion, Credito credito, MotivoDevolucion motivoDevolucion, Date fecha, String observaciones, String solicitante, String revisor, int estatus) {
       this.conceptoDevolucion = conceptoDevolucion;
       this.credito = credito;
       this.motivoDevolucion = motivoDevolucion;
       this.fecha = fecha;
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
    public ConceptoDevolucion getConceptoDevolucion() {
        return this.conceptoDevolucion;
    }
    
    public void setConceptoDevolucion(ConceptoDevolucion conceptoDevolucion) {
        this.conceptoDevolucion = conceptoDevolucion;
    }
    public Credito getCredito() {
        return this.credito;
    }
    
    public void setCredito(Credito credito) {
        this.credito = credito;
    }
    public MotivoDevolucion getMotivoDevolucion() {
        return this.motivoDevolucion;
    }
    
    public void setMotivoDevolucion(MotivoDevolucion motivoDevolucion) {
        this.motivoDevolucion = motivoDevolucion;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
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


