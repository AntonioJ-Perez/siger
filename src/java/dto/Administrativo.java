package dto;
// Generated 27/07/2015 11:04:14 AM by Hibernate Tools 4.3.1




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


