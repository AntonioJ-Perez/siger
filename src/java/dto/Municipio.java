package dto;
// Generated 15/06/2016 09:53:16 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Municipio generated by hbm2java
 */
public class Municipio  implements java.io.Serializable {


     private Integer idMunicipio;
     private EstadoRepublica estadoRepublica;
     private String nombre;
     private Set colonias = new HashSet(0);
     private Set direccions = new HashSet(0);
     private Set regions = new HashSet(0);

    public Municipio() {
    }

	
    public Municipio(EstadoRepublica estadoRepublica, String nombre) {
        this.estadoRepublica = estadoRepublica;
        this.nombre = nombre;
    }
    public Municipio(EstadoRepublica estadoRepublica, String nombre, Set colonias, Set direccions, Set regions) {
       this.estadoRepublica = estadoRepublica;
       this.nombre = nombre;
       this.colonias = colonias;
       this.direccions = direccions;
       this.regions = regions;
    }
   
    public Integer getIdMunicipio() {
        return this.idMunicipio;
    }
    
    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }
    public EstadoRepublica getEstadoRepublica() {
        return this.estadoRepublica;
    }
    
    public void setEstadoRepublica(EstadoRepublica estadoRepublica) {
        this.estadoRepublica = estadoRepublica;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set getColonias() {
        return this.colonias;
    }
    
    public void setColonias(Set colonias) {
        this.colonias = colonias;
    }
    public Set getDireccions() {
        return this.direccions;
    }
    
    public void setDireccions(Set direccions) {
        this.direccions = direccions;
    }
    public Set getRegions() {
        return this.regions;
    }
    
    public void setRegions(Set regions) {
        this.regions = regions;
    }




}


