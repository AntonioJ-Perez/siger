package dto;
// Generated 27/07/2015 11:04:14 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Cliente generated by hbm2java
 */
public class Cliente  implements java.io.Serializable {


     private Integer idCliente;
     private Sujeto sujeto;
     private String numeroCliente;
     private String curp;
     private String numeroSeguroSocial;
     private Set creditos = new HashSet(0);
     private Set contactos = new HashSet(0);

    public Cliente() {
    }

	
    public Cliente(Sujeto sujeto) {
        this.sujeto = sujeto;
    }
    public Cliente(Sujeto sujeto, String numeroCliente, String curp, String numeroSeguroSocial, Set creditos, Set contactos) {
       this.sujeto = sujeto;
       this.numeroCliente = numeroCliente;
       this.curp = curp;
       this.numeroSeguroSocial = numeroSeguroSocial;
       this.creditos = creditos;
       this.contactos = contactos;
    }
   
    public Integer getIdCliente() {
        return this.idCliente;
    }
    
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    public Sujeto getSujeto() {
        return this.sujeto;
    }
    
    public void setSujeto(Sujeto sujeto) {
        this.sujeto = sujeto;
    }
    public String getNumeroCliente() {
        return this.numeroCliente;
    }
    
    public void setNumeroCliente(String numeroCliente) {
        this.numeroCliente = numeroCliente;
    }
    public String getCurp() {
        return this.curp;
    }
    
    public void setCurp(String curp) {
        this.curp = curp;
    }
    public String getNumeroSeguroSocial() {
        return this.numeroSeguroSocial;
    }
    
    public void setNumeroSeguroSocial(String numeroSeguroSocial) {
        this.numeroSeguroSocial = numeroSeguroSocial;
    }
    public Set getCreditos() {
        return this.creditos;
    }
    
    public void setCreditos(Set creditos) {
        this.creditos = creditos;
    }
    public Set getContactos() {
        return this.contactos;
    }
    
    public void setContactos(Set contactos) {
        this.contactos = contactos;
    }




}


