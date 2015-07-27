package dto;
// Generated 27/07/2015 11:04:14 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Empresa generated by hbm2java
 */
public class Empresa  implements java.io.Serializable {


     private Integer idEmpresa;
     private Sujeto sujeto;
     private String nombreCorto;
     private Set productos = new HashSet(0);
     private Set creditos = new HashSet(0);

    public Empresa() {
    }

	
    public Empresa(Sujeto sujeto, String nombreCorto) {
        this.sujeto = sujeto;
        this.nombreCorto = nombreCorto;
    }
    public Empresa(Sujeto sujeto, String nombreCorto, Set productos, Set creditos) {
       this.sujeto = sujeto;
       this.nombreCorto = nombreCorto;
       this.productos = productos;
       this.creditos = creditos;
    }
   
    public Integer getIdEmpresa() {
        return this.idEmpresa;
    }
    
    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    public Sujeto getSujeto() {
        return this.sujeto;
    }
    
    public void setSujeto(Sujeto sujeto) {
        this.sujeto = sujeto;
    }
    public String getNombreCorto() {
        return this.nombreCorto;
    }
    
    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }
    public Set getProductos() {
        return this.productos;
    }
    
    public void setProductos(Set productos) {
        this.productos = productos;
    }
    public Set getCreditos() {
        return this.creditos;
    }
    
    public void setCreditos(Set creditos) {
        this.creditos = creditos;
    }




}


