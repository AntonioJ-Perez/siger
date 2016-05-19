package dto;
// Generated 17/05/2016 10:10:16 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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
     private String exterior;
     private String interior;
     private BigDecimal latitud;
     private BigDecimal longitud;
     private Set impresions = new HashSet(0);

    public Direccion() {
    }

	
    public Direccion(Colonia colonia, EstadoRepublica estadoRepublica, Municipio municipio, Sujeto sujeto, String calle, String exterior, BigDecimal latitud, BigDecimal longitud) {
        this.colonia = colonia;
        this.estadoRepublica = estadoRepublica;
        this.municipio = municipio;
        this.sujeto = sujeto;
        this.calle = calle;
        this.exterior = exterior;
        this.latitud = latitud;
        this.longitud = longitud;
    }
    public Direccion(Colonia colonia, EstadoRepublica estadoRepublica, Municipio municipio, Sujeto sujeto, String calle, String exterior, String interior, BigDecimal latitud, BigDecimal longitud, Set impresions) {
       this.colonia = colonia;
       this.estadoRepublica = estadoRepublica;
       this.municipio = municipio;
       this.sujeto = sujeto;
       this.calle = calle;
       this.exterior = exterior;
       this.interior = interior;
       this.latitud = latitud;
       this.longitud = longitud;
       this.impresions = impresions;
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
    public String getExterior() {
        return this.exterior;
    }
    
    public void setExterior(String exterior) {
        this.exterior = exterior;
    }
    public String getInterior() {
        return this.interior;
    }
    
    public void setInterior(String interior) {
        this.interior = interior;
    }
    public BigDecimal getLatitud() {
        return this.latitud;
    }
    
    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }
    public BigDecimal getLongitud() {
        return this.longitud;
    }
    
    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }
    public Set getImpresions() {
        return this.impresions;
    }
    
    public void setImpresions(Set impresions) {
        this.impresions = impresions;
    }




}


