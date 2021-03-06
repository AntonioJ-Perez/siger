package dto;
// Generated 15/06/2015 12:32:14 PM by Hibernate Tools 4.3.1


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
 * Clientes generated by hbm2java
 */
@Entity
@Table(name="clientes"
    ,catalog="sigerbd"
)
public class Clientes  implements java.io.Serializable {


     private Integer idCliente;
     private Sujetos sujetos;
     private String numeroCliente;
     private String curp;
     private String numeroSeguroSocial;
     private Set creditoses = new HashSet(0);
     private Set contactoses = new HashSet(0);

    public Clientes() {
    }

	
    public Clientes(Sujetos sujetos) {
        this.sujetos = sujetos;
    }
    public Clientes(Sujetos sujetos, String numeroCliente, String curp, String numeroSeguroSocial, Set creditoses, Set contactoses) {
       this.sujetos = sujetos;
       this.numeroCliente = numeroCliente;
       this.curp = curp;
       this.numeroSeguroSocial = numeroSeguroSocial;
       this.creditoses = creditoses;
       this.contactoses = contactoses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_cliente", unique=true, nullable=false)
    public Integer getIdCliente() {
        return this.idCliente;
    }
    
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sujetos_id_sujeto", nullable=false)
    public Sujetos getSujetos() {
        return this.sujetos;
    }
    
    public void setSujetos(Sujetos sujetos) {
        this.sujetos = sujetos;
    }

    
    @Column(name="numero_cliente", length=25)
    public String getNumeroCliente() {
        return this.numeroCliente;
    }
    
    public void setNumeroCliente(String numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    
    @Column(name="curp", length=20)
    public String getCurp() {
        return this.curp;
    }
    
    public void setCurp(String curp) {
        this.curp = curp;
    }

    
    @Column(name="numero_seguro_social", length=20)
    public String getNumeroSeguroSocial() {
        return this.numeroSeguroSocial;
    }
    
    public void setNumeroSeguroSocial(String numeroSeguroSocial) {
        this.numeroSeguroSocial = numeroSeguroSocial;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="clientes")
    public Set getCreditoses() {
        return this.creditoses;
    }
    
    public void setCreditoses(Set creditoses) {
        this.creditoses = creditoses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="clientes")
    public Set getContactoses() {
        return this.contactoses;
    }
    
    public void setContactoses(Set contactoses) {
        this.contactoses = contactoses;
    }




}


