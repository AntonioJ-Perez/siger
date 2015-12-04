package dto;
// Generated 2/12/2015 01:29:58 PM by Hibernate Tools 4.3.1



/**
 * Direccion generated by hbm2java
 */
public class Direccion  implements java.io.Serializable {


     private Integer idDireccion;
     private Colonia colonia;
     private EstadoRepublica estadoRepublica;
     private Municipio municipio;
     private Sujeto sujeto;
     private String calle;
     private String tipo;

    public Direccion() {
    }

	
    public Direccion(Colonia colonia, EstadoRepublica estadoRepublica, Municipio municipio, Sujeto sujeto, String calle) {
        this.colonia = colonia;
        this.estadoRepublica = estadoRepublica;
        this.municipio = municipio;
        this.sujeto = sujeto;
        this.calle = calle;
    }
    public Direccion(Colonia colonia, EstadoRepublica estadoRepublica, Municipio municipio, Sujeto sujeto, String calle, String tipo) {
       this.colonia = colonia;
       this.estadoRepublica = estadoRepublica;
       this.municipio = municipio;
       this.sujeto = sujeto;
       this.calle = calle;
       this.tipo = tipo;
    }
   
    public Integer getIdDireccion() {
        return this.idDireccion;
    }
    
    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
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
    public Sujeto getSujeto() {
        return this.sujeto;
    }
    
    public void setSujeto(Sujeto sujeto) {
        this.sujeto = sujeto;
    }
    public String getCalle() {
        return this.calle;
    }
    
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }




}


