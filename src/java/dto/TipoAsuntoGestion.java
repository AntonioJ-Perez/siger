package dto;
// Generated 15/06/2016 09:53:16 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TipoAsuntoGestion generated by hbm2java
 */
public class TipoAsuntoGestion  implements java.io.Serializable {


     private Integer idTipoAsuntoGestion;
     private int claveAsunto;
     private String asunto;
     private String abreviatura;
     private Set asuntoGestions = new HashSet(0);

    public TipoAsuntoGestion() {
    }

	
    public TipoAsuntoGestion(int claveAsunto, String asunto) {
        this.claveAsunto = claveAsunto;
        this.asunto = asunto;
    }
    public TipoAsuntoGestion(int claveAsunto, String asunto, String abreviatura, Set asuntoGestions) {
       this.claveAsunto = claveAsunto;
       this.asunto = asunto;
       this.abreviatura = abreviatura;
       this.asuntoGestions = asuntoGestions;
    }
   
    public Integer getIdTipoAsuntoGestion() {
        return this.idTipoAsuntoGestion;
    }
    
    public void setIdTipoAsuntoGestion(Integer idTipoAsuntoGestion) {
        this.idTipoAsuntoGestion = idTipoAsuntoGestion;
    }
    public int getClaveAsunto() {
        return this.claveAsunto;
    }
    
    public void setClaveAsunto(int claveAsunto) {
        this.claveAsunto = claveAsunto;
    }
    public String getAsunto() {
        return this.asunto;
    }
    
    public void setAsunto(String asunto) {
        this.asunto = asunto;
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




}


