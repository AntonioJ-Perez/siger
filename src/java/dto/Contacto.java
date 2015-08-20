package dto;
// Generated 19/08/2015 01:24:43 PM by Hibernate Tools 4.3.1




/**
 * Contacto generated by hbm2java
 */
public class Contacto  implements java.io.Serializable {


     private Integer idContacto;
     private Cliente cliente;
     private Sujeto sujeto;
     private String observaciones;

    public Contacto() {
    }

	
    public Contacto(Cliente cliente, Sujeto sujeto) {
        this.cliente = cliente;
        this.sujeto = sujeto;
    }
    public Contacto(Cliente cliente, Sujeto sujeto, String observaciones) {
       this.cliente = cliente;
       this.sujeto = sujeto;
       this.observaciones = observaciones;
    }
   
    public Integer getIdContacto() {
        return this.idContacto;
    }
    
    public void setIdContacto(Integer idContacto) {
        this.idContacto = idContacto;
    }
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Sujeto getSujeto() {
        return this.sujeto;
    }
    
    public void setSujeto(Sujeto sujeto) {
        this.sujeto = sujeto;
    }
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }




}


