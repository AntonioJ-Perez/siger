package dto;
// Generated 27/07/2015 11:04:14 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Sujeto generated by hbm2java
 */
public class Sujeto  implements java.io.Serializable {


     private Integer idSujeto;
     private String nombreRazonSocial;
     private String rfc;
     private int eliminado;
     private Set direccions = new HashSet(0);
     private Set emails = new HashSet(0);
     private Set empresas = new HashSet(0);
     private Set clientes = new HashSet(0);
     private Set telefonos = new HashSet(0);
     private Set contactos = new HashSet(0);

    public Sujeto() {
    }

	
    public Sujeto(String nombreRazonSocial, int eliminado) {
        this.nombreRazonSocial = nombreRazonSocial;
        this.eliminado = eliminado;
    }
    public Sujeto(String nombreRazonSocial, String rfc, int eliminado, Set direccions, Set emails, Set empresas, Set clientes, Set telefonos, Set contactos) {
       this.nombreRazonSocial = nombreRazonSocial;
       this.rfc = rfc;
       this.eliminado = eliminado;
       this.direccions = direccions;
       this.emails = emails;
       this.empresas = empresas;
       this.clientes = clientes;
       this.telefonos = telefonos;
       this.contactos = contactos;
    }
   
    public Integer getIdSujeto() {
        return this.idSujeto;
    }
    
    public void setIdSujeto(Integer idSujeto) {
        this.idSujeto = idSujeto;
    }
    public String getNombreRazonSocial() {
        return this.nombreRazonSocial;
    }
    
    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }
    public String getRfc() {
        return this.rfc;
    }
    
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
    public int getEliminado() {
        return this.eliminado;
    }
    
    public void setEliminado(int eliminado) {
        this.eliminado = eliminado;
    }
    public Set getDireccions() {
        return this.direccions;
    }
    
    public void setDireccions(Set direccions) {
        this.direccions = direccions;
    }
    public Set getEmails() {
        return this.emails;
    }
    
    public void setEmails(Set emails) {
        this.emails = emails;
    }
    public Set getEmpresas() {
        return this.empresas;
    }
    
    public void setEmpresas(Set empresas) {
        this.empresas = empresas;
    }
    public Set getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Set clientes) {
        this.clientes = clientes;
    }
    public Set getTelefonos() {
        return this.telefonos;
    }
    
    public void setTelefonos(Set telefonos) {
        this.telefonos = telefonos;
    }
    public Set getContactos() {
        return this.contactos;
    }
    
    public void setContactos(Set contactos) {
        this.contactos = contactos;
    }




}

