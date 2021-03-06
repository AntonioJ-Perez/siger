package dto;
// Generated 15/06/2015 12:32:14 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Gestiones generated by hbm2java
 */
@Entity
@Table(name="gestiones"
    ,catalog="sigerbd"
)
public class Gestiones  implements java.io.Serializable {


     private Integer idGestion;
     private Creditos creditos;
     private Date fecha;

    public Gestiones() {
    }

	
    public Gestiones(Creditos creditos) {
        this.creditos = creditos;
    }
    public Gestiones(Creditos creditos, Date fecha) {
       this.creditos = creditos;
       this.fecha = fecha;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_gestion", unique=true, nullable=false)
    public Integer getIdGestion() {
        return this.idGestion;
    }
    
    public void setIdGestion(Integer idGestion) {
        this.idGestion = idGestion;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="creditos_id_credito", nullable=false)
    public Creditos getCreditos() {
        return this.creditos;
    }
    
    public void setCreditos(Creditos creditos) {
        this.creditos = creditos;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha", length=19)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }




}


