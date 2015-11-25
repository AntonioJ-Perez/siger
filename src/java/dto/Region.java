package dto;
// Generated 25/11/2015 11:44:49 AM by Hibernate Tools 4.3.1



/**
 * Region generated by hbm2java
 */
public class Region  implements java.io.Serializable {


     private Integer idRegion;
     private Colonia colonia;
     private EstadoRepublica estadoRepublica;
     private Municipio municipio;
     private Zona zona;

    public Region() {
    }

	
    public Region(Zona zona) {
        this.zona = zona;
    }
    public Region(Colonia colonia, EstadoRepublica estadoRepublica, Municipio municipio, Zona zona) {
       this.colonia = colonia;
       this.estadoRepublica = estadoRepublica;
       this.municipio = municipio;
       this.zona = zona;
    }
   
    public Integer getIdRegion() {
        return this.idRegion;
    }
    
    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }
    public Colonia getColonia() {
        return this.colonia;
    }
    
    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
    }
    public EstadoRepublica getEstadoRepublica() {
        return this.estadoRepublica;
    }
    
    public void setEstadoRepublica(EstadoRepublica estadoRepublica) {
        this.estadoRepublica = estadoRepublica;
    }
    public Municipio getMunicipio() {
        return this.municipio;
    }
    
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    public Zona getZona() {
        return this.zona;
    }
    
    public void setZona(Zona zona) {
        this.zona = zona;
    }




}


