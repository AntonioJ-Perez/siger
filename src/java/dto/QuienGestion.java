package dto;
// Generated 25/02/2016 03:00:35 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * QuienGestion generated by hbm2java
 */
public class QuienGestion  implements java.io.Serializable {


     private Integer idQuienGestion;
     private int idTipoQuienGestion;
     private String quien;
     private Set gestions = new HashSet(0);

    public QuienGestion() {
    }

	
    public QuienGestion(int idTipoQuienGestion, String quien) {
        this.idTipoQuienGestion = idTipoQuienGestion;
        this.quien = quien;
    }
    public QuienGestion(int idTipoQuienGestion, String quien, Set gestions) {
       this.idTipoQuienGestion = idTipoQuienGestion;
       this.quien = quien;
       this.gestions = gestions;
    }
   
    public Integer getIdQuienGestion() {
        return this.idQuienGestion;
    }
    
    public void setIdQuienGestion(Integer idQuienGestion) {
        this.idQuienGestion = idQuienGestion;
    }
    public int getIdTipoQuienGestion() {
        return this.idTipoQuienGestion;
    }
    
    public void setIdTipoQuienGestion(int idTipoQuienGestion) {
        this.idTipoQuienGestion = idTipoQuienGestion;
    }
    public String getQuien() {
        return this.quien;
    }
    
    public void setQuien(String quien) {
        this.quien = quien;
    }
    public Set getGestions() {
        return this.gestions;
    }
    
    public void setGestions(Set gestions) {
        this.gestions = gestions;
    }




}


