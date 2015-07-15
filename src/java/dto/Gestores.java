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
 * Gestores generated by hbm2java
 */
@Entity
@Table(name="gestores"
    ,catalog="sigerbd"
)
public class Gestores  implements java.io.Serializable {


     private Integer idGestor;
     private Usuarios usuarios;
     private String extension;
     private Set creditoses = new HashSet(0);

    public Gestores() {
    }

	
    public Gestores(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    public Gestores(Usuarios usuarios, String extension, Set creditoses) {
       this.usuarios = usuarios;
       this.extension = extension;
       this.creditoses = creditoses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_gestor", unique=true, nullable=false)
    public Integer getIdGestor() {
        return this.idGestor;
    }
    
    public void setIdGestor(Integer idGestor) {
        this.idGestor = idGestor;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_usuario", nullable=false)
    public Usuarios getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    
    @Column(name="extension", length=15)
    public String getExtension() {
        return this.extension;
    }
    
    public void setExtension(String extension) {
        this.extension = extension;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="gestores")
    public Set getCreditoses() {
        return this.creditoses;
    }
    
    public void setCreditoses(Set creditoses) {
        this.creditoses = creditoses;
    }




}

