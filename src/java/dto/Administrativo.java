package dto;
// Generated 4/11/2015 10:38:46 AM by Hibernate Tools 4.3.1



/**
 * Administrativo generated by hbm2java
 */
public class Administrativo  implements java.io.Serializable {


     private Integer idAdministrativo;
     private Usuario usuario;

    public Administrativo() {
    }

    public Administrativo(Usuario usuario) {
       this.usuario = usuario;
    }
   
    public Integer getIdAdministrativo() {
        return this.idAdministrativo;
    }
    
    public void setIdAdministrativo(Integer idAdministrativo) {
        this.idAdministrativo = idAdministrativo;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }




}


