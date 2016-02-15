package dto;
// Generated 9/02/2016 02:44:04 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TipoQuienGestion generated by hbm2java
 */
public class TipoQuienGestion  implements java.io.Serializable {


     private Integer idTipoQuienGestion;
     private String tipo;
     private String descripcion;
     private Set gestions = new HashSet(0);

    public TipoQuienGestion() {
    }

	
    public TipoQuienGestion(String tipo, String descripcion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
    }
    public TipoQuienGestion(String tipo, String descripcion, Set gestions) {
       this.tipo = tipo;
       this.descripcion = descripcion;
       this.gestions = gestions;
    }
   
    public Integer getIdTipoQuienGestion() {
        return this.idTipoQuienGestion;
    }
    
    public void setIdTipoQuienGestion(Integer idTipoQuienGestion) {
        this.idTipoQuienGestion = idTipoQuienGestion;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set getGestions() {
        return this.gestions;
    }
    
    public void setGestions(Set gestions) {
        this.gestions = gestions;
    }




}

