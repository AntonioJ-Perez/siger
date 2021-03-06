package dto;
// Generated 15/06/2015 12:32:14 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Creditos generated by hbm2java
 */
@Entity
@Table(name="creditos"
    ,catalog="sigerbd"
)
public class Creditos  implements java.io.Serializable {


     private Integer idCredito;
     private Clientes clientes;
     private Empresas empresas;
     private Gestores gestores;
     private Productos productos;
     private Subproductos subproductos;
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
     private Set lineases = new HashSet(0);
     private Set creditosRemesases = new HashSet(0);
     private Set gestioneses = new HashSet(0);
     private Set autoses = new HashSet(0);

    public Creditos() {
    }

	
    public Creditos(Clientes clientes, Empresas empresas, Gestores gestores, Productos productos, String numeroCredito, int tipoCredito) {
        this.clientes = clientes;
        this.empresas = empresas;
        this.gestores = gestores;
        this.productos = productos;
        this.numeroCredito = numeroCredito;
        this.tipoCredito = tipoCredito;
    }
    public Creditos(Clientes clientes, Empresas empresas, Gestores gestores, Productos productos, Subproductos subproductos, String numeroCredito, Date fechaInicio, Date fechaFin, Date fechaQuebranto, Float monto, Float mensualidad, Float tasaInteres, Integer diasMora, String numeroCuenta, int tipoCredito, Set lineases, Set creditosRemesases, Set gestioneses, Set autoses) {
       this.clientes = clientes;
       this.empresas = empresas;
       this.gestores = gestores;
       this.productos = productos;
       this.subproductos = subproductos;
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
       this.lineases = lineases;
       this.creditosRemesases = creditosRemesases;
       this.gestioneses = gestioneses;
       this.autoses = autoses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_credito", unique=true, nullable=false)
    public Integer getIdCredito() {
        return this.idCredito;
    }
    
    public void setIdCredito(Integer idCredito) {
        this.idCredito = idCredito;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="clientes_id_cliente", nullable=false)
    public Clientes getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="empresas_id_empresa", nullable=false)
    public Empresas getEmpresas() {
        return this.empresas;
    }
    
    public void setEmpresas(Empresas empresas) {
        this.empresas = empresas;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="gestores_id_gestor", nullable=false)
    public Gestores getGestores() {
        return this.gestores;
    }
    
    public void setGestores(Gestores gestores) {
        this.gestores = gestores;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="productos_id_producto", nullable=false)
    public Productos getProductos() {
        return this.productos;
    }
    
    public void setProductos(Productos productos) {
        this.productos = productos;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="subproductos_id_subproducto")
    public Subproductos getSubproductos() {
        return this.subproductos;
    }
    
    public void setSubproductos(Subproductos subproductos) {
        this.subproductos = subproductos;
    }

    
    @Column(name="numero_credito", nullable=false, length=30)
    public String getNumeroCredito() {
        return this.numeroCredito;
    }
    
    public void setNumeroCredito(String numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_inicio", length=10)
    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_fin", length=10)
    public Date getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_quebranto", length=10)
    public Date getFechaQuebranto() {
        return this.fechaQuebranto;
    }
    
    public void setFechaQuebranto(Date fechaQuebranto) {
        this.fechaQuebranto = fechaQuebranto;
    }

    
    @Column(name="monto", precision=12, scale=0)
    public Float getMonto() {
        return this.monto;
    }
    
    public void setMonto(Float monto) {
        this.monto = monto;
    }

    
    @Column(name="mensualidad", precision=12, scale=0)
    public Float getMensualidad() {
        return this.mensualidad;
    }
    
    public void setMensualidad(Float mensualidad) {
        this.mensualidad = mensualidad;
    }

    
    @Column(name="tasa_interes", precision=12, scale=0)
    public Float getTasaInteres() {
        return this.tasaInteres;
    }
    
    public void setTasaInteres(Float tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    
    @Column(name="dias_mora")
    public Integer getDiasMora() {
        return this.diasMora;
    }
    
    public void setDiasMora(Integer diasMora) {
        this.diasMora = diasMora;
    }

    
    @Column(name="numero_cuenta", length=30)
    public String getNumeroCuenta() {
        return this.numeroCuenta;
    }
    
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    
    @Column(name="tipo_credito", nullable=false)
    public int getTipoCredito() {
        return this.tipoCredito;
    }
    
    public void setTipoCredito(int tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="creditos")
    public Set getLineases() {
        return this.lineases;
    }
    
    public void setLineases(Set lineases) {
        this.lineases = lineases;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="creditos")
    public Set getCreditosRemesases() {
        return this.creditosRemesases;
    }
    
    public void setCreditosRemesases(Set creditosRemesases) {
        this.creditosRemesases = creditosRemesases;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="creditos")
    public Set getGestioneses() {
        return this.gestioneses;
    }
    
    public void setGestioneses(Set gestioneses) {
        this.gestioneses = gestioneses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="creditos")
    public Set getAutoses() {
        return this.autoses;
    }
    
    public void setAutoses(Set autoses) {
        this.autoses = autoses;
    }




}


