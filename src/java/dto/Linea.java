package dto;
// Generated 22/03/2016 03:28:07 PM by Hibernate Tools 4.3.1



/**
 * Linea generated by hbm2java
 */
public class Linea  implements java.io.Serializable {


     private int idLinea;
     private Credito credito;
     private String telefono;

    public Linea() {
    }

    public Linea(int idLinea, Credito credito, String telefono) {
       this.idLinea = idLinea;
       this.credito = credito;
       this.telefono = telefono;
    }
   
    public int getIdLinea() {
        return this.idLinea;
    }
    
    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }
    public Credito getCredito() {
        return this.credito;
    }
    
    public void setCredito(Credito credito) {
        this.credito = credito;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }




}


