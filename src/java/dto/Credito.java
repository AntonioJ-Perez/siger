package dto;
// Generated 18/08/2015 11:07:25 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Credito generated by hbm2java
 */
public class Credito  implements java.io.Serializable {


     private Integer idCredito;
     private Cliente cliente;
     private Empresa empresa;
     private Gestor gestor;
     private Producto producto;
     private Subproducto subproducto;
     private String numeroCredito;
     private Date fechaInicio;
     private Date fechaFin;
     private Date fechaQuebranto;
     private Float monto;
     private Float mensualidad;
     private Float tasaInteres;
     private Integer diasMora;
     private String numeroCuenta;
     private int tipoCredito;
     private Set lineas = new HashSet(0);
     private Set creditoRemesas = new HashSet(0);
     private Set gestions = new HashSet(0);
     private Set autos = new HashSet(0);

    public Credito() {
    }

	
    public Credito(Cliente cliente, Empresa empresa, Gestor gestor, Producto producto, String numeroCredito, int tipoCredito) {
        this.cliente = cliente;
        this.empresa = empresa;
        this.gestor = gestor;
        this.producto = producto;
        this.numeroCredito = numeroCredito;
        this.tipoCredito = tipoCredito;
    }
    public Credito(Cliente cliente, Empresa empresa, Gestor gestor, Producto producto, Subproducto subproducto, String numeroCredito, Date fechaInicio, Date fechaFin, Date fechaQuebranto, Float monto, Float mensualidad, Float tasaInteres, Integer diasMora, String numeroCuenta, int tipoCredito, Set lineas, Set creditoRemesas, Set gestions, Set autos) {
       this.cliente = cliente;
       this.empresa = empresa;
       this.gestor = gestor;
       this.producto = producto;
       this.subproducto = subproducto;
       this.numeroCredito = numeroCredito;
       this.fechaInicio = fechaInicio;
       this.fechaFin = fechaFin;
       this.fechaQuebranto = fechaQuebranto;
       this.monto = monto;
       this.mensualidad = mensualidad;
       this.tasaInteres = tasaInteres;
       this.diasMora = diasMora;
       this.numeroCuenta = numeroCuenta;
       this.tipoCredito = tipoCredito;
       this.lineas = lineas;
       this.creditoRemesas = creditoRemesas;
       this.gestions = gestions;
       this.autos = autos;
    }
   
    public Integer getIdCredito() {
        return this.idCredito;
    }
    
    public void setIdCredito(Integer idCredito) {
        this.idCredito = idCredito;
    }
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Empresa getEmpresa() {
        return this.empresa;
    }
    
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    public Gestor getGestor() {
        return this.gestor;
    }
    
    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }
    public Producto getProducto() {
        return this.producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Subproducto getSubproducto() {
        return this.subproducto;
    }
    
    public void setSubproducto(Subproducto subproducto) {
        this.subproducto = subproducto;
    }
    public String getNumeroCredito() {
        return this.numeroCredito;
    }
    
    public void setNumeroCredito(String numeroCredito) {
        this.numeroCredito = numeroCredito;
    }
    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public Date getFechaQuebranto() {
        return this.fechaQuebranto;
    }
    
    public void setFechaQuebranto(Date fechaQuebranto) {
        this.fechaQuebranto = fechaQuebranto;
    }
    public Float getMonto() {
        return this.monto;
    }
    
    public void setMonto(Float monto) {
        this.monto = monto;
    }
    public Float getMensualidad() {
        return this.mensualidad;
    }
    
    public void setMensualidad(Float mensualidad) {
        this.mensualidad = mensualidad;
    }
    public Float getTasaInteres() {
        return this.tasaInteres;
    }
    
    public void setTasaInteres(Float tasaInteres) {
        this.tasaInteres = tasaInteres;
    }
    public Integer getDiasMora() {
        return this.diasMora;
    }
    
    public void setDiasMora(Integer diasMora) {
        this.diasMora = diasMora;
    }
    public String getNumeroCuenta() {
        return this.numeroCuenta;
    }
    
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    public int getTipoCredito() {
        return this.tipoCredito;
    }
    
    public void setTipoCredito(int tipoCredito) {
        this.tipoCredito = tipoCredito;
    }
    public Set getLineas() {
        return this.lineas;
    }
    
    public void setLineas(Set lineas) {
        this.lineas = lineas;
    }
    public Set getCreditoRemesas() {
        return this.creditoRemesas;
    }
    
    public void setCreditoRemesas(Set creditoRemesas) {
        this.creditoRemesas = creditoRemesas;
    }
    public Set getGestions() {
        return this.gestions;
    }
    
    public void setGestions(Set gestions) {
        this.gestions = gestions;
    }
    public Set getAutos() {
        return this.autos;
    }
    
    public void setAutos(Set autos) {
        this.autos = autos;
    }




}


