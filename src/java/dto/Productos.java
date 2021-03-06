package dto;
// Generated 15/06/2015 12:32:14 PM by Hibernate Tools 4.3.1


import dto.Empresas;
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

/**
 * Productos generated by hbm2java
 */
@Entity
@Table(name="productos"
    ,catalog="sigerbd"
)
public class Productos  implements java.io.Serializable {


     private Integer idProducto;
     private Empresas empresas;
     private String nombre;
     private String descripcion;
     private Set creditoses = new HashSet(0);
     private Set subproductoses = new HashSet(0);

    public Productos() {
    }

	
    public Productos(Empresas empresas, String nombre) {
        this.empresas = empresas;
        this.nombre = nombre;
    }
    public Productos(Empresas empresas, String nombre, String descripcion, Set creditoses, Set subproductoses) {
       this.empresas = empresas;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.creditoses = creditoses;
       this.subproductoses = subproductoses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_producto", unique=true, nullable=false)
    public Integer getIdProducto() {
        return this.idProducto;
    }
    
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="empresas_id_empresa", nullable=false)
    public Empresas getEmpresas() {
        return this.empresas;
    }
    
    public void setEmpresas(Empresas empresas) {
        this.empresas = empresas;
    }

    
    @Column(name="nombre", nullable=false, length=45)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="descripcion", length=200)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="productos")
    public Set getCreditoses() {
        return this.creditoses;
    }
    
    public void setCreditoses(Set creditoses) {
        this.creditoses = creditoses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="productos")
    public Set getSubproductoses() {
        return this.subproductoses;
    }
    
    public void setSubproductoses(Set subproductoses) {
        this.subproductoses = subproductoses;
    }




}


