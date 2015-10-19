package dto;
// Generated 19/10/2015 12:46:44 PM by Hibernate Tools 4.3.1


import dto.Gestor;
import dto.Despacho;
import java.util.HashSet;
import java.util.Set;

/**
 * Zona generated by hbm2java
 */
public class Zona  implements java.io.Serializable {


     private Integer idZona;
     private Despacho despacho;
     private Gestor gestor;
     private String nombre;
     private Set regions = new HashSet(0);

    public Zona() {
    }

	
    public Zona(Despacho despacho, Gestor gestor) {
        this.despacho = despacho;
        this.gestor = gestor;
    }
    public Zona(Despacho despacho, Gestor gestor, String nombre, Set regions) {
       this.despacho = despacho;
       this.gestor = gestor;
       this.nombre = nombre;
       this.regions = regions;
    }
   
    public Integer getIdZona() {
        return this.idZona;
    }
    
    public void setIdZona(Integer idZona) {
        this.idZona = idZona;
    }
    public Despacho getDespacho() {
        return this.despacho;
    }
    
    public void setDespacho(Despacho despacho) {
        this.despacho = despacho;
    }
    public Gestor getGestor() {
        return this.gestor;
    }
    
    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set getRegions() {
        return this.regions;
    }
    
    public void setRegions(Set regions) {
        this.regions = regions;
    }




}


