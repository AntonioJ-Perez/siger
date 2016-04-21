package dto;
// Generated 20/04/2016 12:24:33 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PromesaPago generated by hbm2java
 */
public class PromesaPago  implements java.io.Serializable {


     private Integer idPromesaPago;
     private ConvenioPago convenioPago;
     private Date fechaPrometida;
     private float cantidadPrometida;
     private Set pagos = new HashSet(0);

    public PromesaPago() {
    }

	
    public PromesaPago(ConvenioPago convenioPago, Date fechaPrometida, float cantidadPrometida) {
        this.convenioPago = convenioPago;
        this.fechaPrometida = fechaPrometida;
        this.cantidadPrometida = cantidadPrometida;
    }
    public PromesaPago(ConvenioPago convenioPago, Date fechaPrometida, float cantidadPrometida, Set pagos) {
       this.convenioPago = convenioPago;
       this.fechaPrometida = fechaPrometida;
       this.cantidadPrometida = cantidadPrometida;
       this.pagos = pagos;
    }
   
    public Integer getIdPromesaPago() {
        return this.idPromesaPago;
    }
    
    public void setIdPromesaPago(Integer idPromesaPago) {
        this.idPromesaPago = idPromesaPago;
    }
    public ConvenioPago getConvenioPago() {
        return this.convenioPago;
    }
    
    public void setConvenioPago(ConvenioPago convenioPago) {
        this.convenioPago = convenioPago;
    }
    public Date getFechaPrometida() {
        return this.fechaPrometida;
    }
    
    public void setFechaPrometida(Date fechaPrometida) {
        this.fechaPrometida = fechaPrometida;
    }
    public float getCantidadPrometida() {
        return this.cantidadPrometida;
    }
    
    public void setCantidadPrometida(float cantidadPrometida) {
        this.cantidadPrometida = cantidadPrometida;
    }
    public Set getPagos() {
        return this.pagos;
    }
    
    public void setPagos(Set pagos) {
        this.pagos = pagos;
    }




}


