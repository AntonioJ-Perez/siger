package dto;
// Generated 22/02/2016 10:19:54 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private Integer idUsuario;
     private Despacho despacho;
     private String nombre;
     private String paterno;
     private String materno;
     private String nombreLogin;
     private String password;
     private int perfil;
     private String correo;
     private String imagenPerfil;
     private Set administrativos = new HashSet(0);
     private Set gestors = new HashSet(0);
     private Set gestions = new HashSet(0);

    public Usuario() {
    }

	
    public Usuario(Despacho despacho, String nombre, String paterno, String nombreLogin, String password, int perfil, String correo) {
        this.despacho = despacho;
        this.nombre = nombre;
        this.paterno = paterno;
        this.nombreLogin = nombreLogin;
        this.password = password;
        this.perfil = perfil;
        this.correo = correo;
    }
    public Usuario(Despacho despacho, String nombre, String paterno, String materno, String nombreLogin, String password, int perfil, String correo, String imagenPerfil, Set administrativos, Set gestors, Set gestions) {
       this.despacho = despacho;
       this.nombre = nombre;
       this.paterno = paterno;
       this.materno = materno;
       this.nombreLogin = nombreLogin;
       this.password = password;
       this.perfil = perfil;
       this.correo = correo;
       this.imagenPerfil = imagenPerfil;
       this.administrativos = administrativos;
       this.gestors = gestors;
       this.gestions = gestions;
    }
   
    public Integer getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    public Despacho getDespacho() {
        return this.despacho;
    }
    
    public void setDespacho(Despacho despacho) {
        this.despacho = despacho;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPaterno() {
        return this.paterno;
    }
    
    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }
    public String getMaterno() {
        return this.materno;
    }
    
    public void setMaterno(String materno) {
        this.materno = materno;
    }
    public String getNombreLogin() {
        return this.nombreLogin;
    }
    
    public void setNombreLogin(String nombreLogin) {
        this.nombreLogin = nombreLogin;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public int getPerfil() {
        return this.perfil;
    }
    
    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getImagenPerfil() {
        return this.imagenPerfil;
    }
    
    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }
    public Set getAdministrativos() {
        return this.administrativos;
    }
    
    public void setAdministrativos(Set administrativos) {
        this.administrativos = administrativos;
    }
    public Set getGestors() {
        return this.gestors;
    }
    
    public void setGestors(Set gestors) {
        this.gestors = gestors;
    }
    public Set getGestions() {
        return this.gestions;
    }
    
    public void setGestions(Set gestions) {
        this.gestions = gestions;
    }




}


