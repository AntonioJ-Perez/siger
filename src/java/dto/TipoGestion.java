package dto;
// Generated 22/03/2016 03:28:07 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TipoGestion generated by hbm2java
 */
public class TipoGestion  implements java.io.Serializable {


     private Integer idTipoGestion;
     private String nombre;
     private String abreviatura;
     private Set asuntoGestions = new HashSet(0);
     private Set dondeGestions = new HashSet(0);
     private Set gestions = new HashSet(0);

    public TipoGestion() {
    }

	
    public TipoGestion(String nombre) {
        this.nombre = nombre;
    }
    public TipoGestion(String nombre, String abreviatura, Set asuntoGestions, Set dondeGestions, Set gestions) {
       this.nombre = nombre;
       this.abreviatura = abreviatura;
       this.asuntoGestions = asuntoGestions;
       this.dondeGestions = dondeGestions;
       this.gestions = gestions;
    }
   
    public Integer getIdTipoGestion() {
        return this.idTipoGestion;
    }
    
    public void setIdTipoGestion(Integer idTipoGestion) {
        this.idTipoGestion = idTipoGestion;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getAbreviatura() {
        return this.abreviatura;
    }
    
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    public Set getAsuntoGestions() {
        return this.asuntoGestions;
    }
    
    public void setAsuntoGestions(Set asuntoGestions) {
        this.asuntoGestions = asuntoGestions;
    }
    public Set getDondeGestions() {
        return this.dondeGestions;
    }
    
    public void setDondeGestions(Set dondeGestions) {
        this.dondeGestions = dondeGestions;
    }
    public Set getGestions() {
        return this.gestions;
    }
    
    public void setGestions(Set gestions) {
        this.gestions = gestions;
    }




}


