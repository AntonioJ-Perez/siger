package dto;
// Generated 19/08/2015 01:24:43 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * EstadoRepublica generated by hbm2java
 */
public class EstadoRepublica  implements java.io.Serializable {


     private Integer idEstado;
     private String nombre;
     private Set direccions = new HashSet(0);
     private Set municipios = new HashSet(0);

    public EstadoRepublica() {
    }

	
    public EstadoRepublica(String nombre) {
        this.nombre = nombre;
    }
    public EstadoRepublica(String nombre, Set direccions, Set municipios) {
       this.nombre = nombre;
       this.direccions = direccions;
       this.municipios = municipios;
    }
   
    public Integer getIdEstado() {
        return this.idEstado;
    }
    
    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set getDireccions() {
        return this.direccions;
    }
    
    public void setDireccions(Set direccions) {
        this.direccions = direccions;
    }
    public Set getMunicipios() {
        return this.municipios;
    }
    
    public void setMunicipios(Set municipios) {
        this.municipios = municipios;
    }

    @Override
    public String toString(){
        return nombre;
    }
}

