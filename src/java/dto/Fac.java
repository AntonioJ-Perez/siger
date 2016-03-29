package dto;
// Generated 22/03/2016 03:28:07 PM by Hibernate Tools 4.3.1



/**
 * Fac generated by hbm2java
 */
public class Fac  implements java.io.Serializable {


     private Integer idFac;
     private Actualizacion actualizacion;
     private Integer mes;
     private Integer anio;
     private Float facPor;
     private String facMes;

    public Fac() {
    }

	
    public Fac(Actualizacion actualizacion) {
        this.actualizacion = actualizacion;
    }
    public Fac(Actualizacion actualizacion, Integer mes, Integer anio, Float facPor, String facMes) {
       this.actualizacion = actualizacion;
       this.mes = mes;
       this.anio = anio;
       this.facPor = facPor;
       this.facMes = facMes;
    }
   
    public Integer getIdFac() {
        return this.idFac;
    }
    
    public void setIdFac(Integer idFac) {
        this.idFac = idFac;
    }
    public Actualizacion getActualizacion() {
        return this.actualizacion;
    }
    
    public void setActualizacion(Actualizacion actualizacion) {
        this.actualizacion = actualizacion;
    }
    public Integer getMes() {
        return this.mes;
    }
    
    public void setMes(Integer mes) {
        this.mes = mes;
    }
    public Integer getAnio() {
        return this.anio;
    }
    
    public void setAnio(Integer anio) {
        this.anio = anio;
    }
    public Float getFacPor() {
        return this.facPor;
    }
    
    public void setFacPor(Float facPor) {
        this.facPor = facPor;
    }
    public String getFacMes() {
        return this.facMes;
    }
    
    public void setFacMes(String facMes) {
        this.facMes = facMes;
    }




}


