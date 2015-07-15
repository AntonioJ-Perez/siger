package dto;
// Generated 15/06/2015 12:32:14 PM by Hibernate Tools 4.3.1


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CreditosRemesas generated by hbm2java
 */
@Entity
@Table(name="creditos_remesas"
    ,catalog="sigerbd"
)
public class CreditosRemesas  implements java.io.Serializable {


     private CreditosRemesasId id;
     private Creditos creditos;
     private Remesas remesas;

    public CreditosRemesas() {
    }

    public CreditosRemesas(CreditosRemesasId id, Creditos creditos, Remesas remesas) {
       this.id = id;
       this.creditos = creditos;
       this.remesas = remesas;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="remesasIdRemesa", column=@Column(name="remesas_id_remesa", nullable=false) ), 
        @AttributeOverride(name="creditosIdCredito", column=@Column(name="creditos_id_credito", nullable=false) ) } )
    public CreditosRemesasId getId() {
        return this.id;
    }
    
    public void setId(CreditosRemesasId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="creditos_id_credito", nullable=false, insertable=false, updatable=false)
    public Creditos getCreditos() {
        return this.creditos;
    }
    
    public void setCreditos(Creditos creditos) {
        this.creditos = creditos;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="remesas_id_remesa", nullable=false, insertable=false, updatable=false)
    public Remesas getRemesas() {
        return this.remesas;
    }
    
    public void setRemesas(Remesas remesas) {
        this.remesas = remesas;
    }




}

