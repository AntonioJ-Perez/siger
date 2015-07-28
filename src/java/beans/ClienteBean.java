/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ClienteDAO;
import dao.SujetoDAO;
import dto.Cliente;
import dto.Sujeto;
import impl.ClienteIMPL;
import impl.SujetoIMPL;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import util.constantes.Sujetos;
import util.log.Logs;

/**
 *
 * @author brionvega
 */
@ManagedBean
@ViewScoped
public class ClienteBean {
    // Cliente y Sujeto, porque un cliente es un sujeto:
    private Cliente cliente;
    private Sujeto sujeto;
    
    // Clases para acceso a datos
    private ClienteDAO clienteDao;
    private SujetoDAO sujetoDao;
    
    // Atributos de cliente:
    private String numeroCliente;
    private String curp;
    private String numeroSeguroSocial;
    
    // Atributos de sujeto:
    private String nombreRazonSocial;
    private String rfc;
    private int eliminado;
    
    // Construyendo...
    public ClienteBean() {
        cliente = new Cliente();
        clienteDao = new ClienteIMPL();
        sujeto = new Sujeto();
        sujetoDao = new SujetoIMPL();
        eliminado = Sujetos.ACTIVO;
    }

    // Agregar un nuevo cliente
    public void agregar() {
        // Primero agrega el sujeto, si se agregó correctamente entonces agrega cliente
        int idSujeto = agregarSujeto();
        
        if(idSujeto > 0) {
            Logs.log.info("Se agregó objeto: Sujeto; con id = " + idSujeto);
            if (agregarCliente()) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Operación Exitosa",
                        "Se agregó un nuevo cliente: " + nombreRazonSocial));
                limpiarEntradas(); // Sin albur...
                Logs.log.info("Se agregó objeto: Cliente; asociado a Sujeto: " + idSujeto);
                RequestContext.getCurrentInstance().execute("PF('dlgAgregarCliente').hide();");
            } else {
                sujetoDao.eliminarEnSerio(sujeto);
                Logs.log.error("No se pudo agregar objeto: Cliente");
            }
        } else {
            Logs.log.error("No se pudo agregar objeto: Sujeto.");
        }
    }
    
    private int agregarSujeto() {
        // primero agregará un sujeto...
        sujeto.setNombreRazonSocial(nombreRazonSocial);
        sujeto.setRfc(rfc);
        sujeto.setEliminado(eliminado);
        
        return sujetoDao.insertar(sujeto);
        
    }
    
    private boolean agregarCliente() {
        cliente.setNumeroCliente(numeroCliente);
        cliente.setCurp(curp);
        cliente.setNumeroSeguroSocial(numeroSeguroSocial);
        cliente.setSujeto(sujeto);
        
        return clienteDao.insertar(cliente);
    }
    
    private void limpiarEntradas() {
        numeroCliente = null;
        numeroSeguroSocial = null;
        curp = null;
        nombreRazonSocial = null;
        rfc = null;
    }
    
    public boolean nombreEsValido() {
        return (nombreRazonSocial != null) && (!nombreRazonSocial.equals(""))
                && (nombreRazonSocial.length() <= Sujetos.LONGITUD_NOMBRE)
                && (!nombreRazonSocial.matches("[.*\\s*]*"));
    }

    public String getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(String numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNumeroSeguroSocial() {
        return numeroSeguroSocial;
    }

    public void setNumeroSeguroSocial(String numeroSeguroSocial) {
        this.numeroSeguroSocial = numeroSeguroSocial;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public int getEliminado() {
        return eliminado;
    }

    public void setEliminado(int eliminado) {
        this.eliminado = eliminado;
    }
    
    
}
