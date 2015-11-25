package dto;
// Generated 25/11/2015 11:44:49 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Gestion generated by hbm2java
 */
public class Gestion  implements java.io.Serializable {


     private Integer idGestion;
     private Credito credito;
     private Usuario usuario;
     private Date fecha;
     private String tipoGestion;
     private String lugarGestion;
     private String asuntoGestion;
     private String descripcionGestion;
     private String tipoSujetoGestion;
     private String sujetoGestion;
     private String informacionInstitucion;
     private String gestion;

    public Gestion() {
    }

	
    public Gestion(Credito credito, Usuario usuario, String tipoGestion, String lugarGestion, String asuntoGestion, String descripcionGestion, String tipoSujetoGestion, String sujetoGestion, String informacionInstitucion) {
        this.credito = credito;
        this.usuario = usuario;
        this.tipoGestion = tipoGestion;
        this.lugarGestion = lugarGestion;
        this.asuntoGestion = asuntoGestion;
        this.descripcionGestion = descripcionGestion;
        this.tipoSujetoGestion = tipoSujetoGestion;
        this.sujetoGestion = sujetoGestion;
        this.informacionInstitucion = informacionInstitucion;
    }
    public Gestion(Credito credito, Usuario usuario, Date fecha, String tipoGestion, String lugarGestion, String asuntoGestion, String descripcionGestion, String tipoSujetoGestion, String sujetoGestion, String informacionInstitucion, String gestion) {
       this.credito = credito;
       this.usuario = usuario;
       this.fecha = fecha;
       this.tipoGestion = tipoGestion;
       this.lugarGestion = lugarGestion;
       this.asuntoGestion = asuntoGestion;
       this.descripcionGestion = descripcionGestion;
       this.tipoSujetoGestion = tipoSujetoGestion;
       this.sujetoGestion = sujetoGestion;
       this.informacionInstitucion = informacionInstitucion;
       this.gestion = gestion;
    }
   
    public Integer getIdGestion() {
        return this.idGestion;
    }
    
    public void setIdGestion(Integer idGestion) {
        this.idGestion = idGestion;
    }
    public Credito getCredito() {
        return this.credito;
    }
    
    public void setCredito(Credito credito) {
        this.credito = credito;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getTipoGestion() {
        return this.tipoGestion;
    }
    
    public void setTipoGestion(String tipoGestion) {
        this.tipoGestion = tipoGestion;
    }
    public String getLugarGestion() {
        return this.lugarGestion;
    }
    
    public void setLugarGestion(String lugarGestion) {
        this.lugarGestion = lugarGestion;
    }
    public String getAsuntoGestion() {
        return this.asuntoGestion;
    }
    
    public void setAsuntoGestion(String asuntoGestion) {
        this.asuntoGestion = asuntoGestion;
    }
    public String getDescripcionGestion() {
        return this.descripcionGestion;
    }
    
    public void setDescripcionGestion(String descripcionGestion) {
        this.descripcionGestion = descripcionGestion;
    }
    public String getTipoSujetoGestion() {
        return this.tipoSujetoGestion;
    }
    
    public void setTipoSujetoGestion(String tipoSujetoGestion) {
        this.tipoSujetoGestion = tipoSujetoGestion;
    }
    public String getSujetoGestion() {
        return this.sujetoGestion;
    }
    
    public void setSujetoGestion(String sujetoGestion) {
        this.sujetoGestion = sujetoGestion;
    }
    public String getInformacionInstitucion() {
        return this.informacionInstitucion;
    }
    
    public void setInformacionInstitucion(String informacionInstitucion) {
        this.informacionInstitucion = informacionInstitucion;
    }
    public String getGestion() {
        return this.gestion;
    }
    
    public void setGestion(String gestion) {
        this.gestion = gestion;
    }




}


