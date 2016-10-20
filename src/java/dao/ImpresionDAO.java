/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Impresion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Eduardo
 */
public interface ImpresionDAO {
  
  public boolean insertar(Impresion impresion);
  
  public boolean editar(Impresion impresion);
  
  public List<Impresion> buscarPorCredito(int idCredito);
  
  public List<Impresion> buscarPorBloque(int idBloque);
  
  public List<Impresion> buscarPorFechaImpresion(Date fechaImpresion);
  
  public List<Impresion> buscarPorTipoImpresion(int tipoImpresion, int idDespacho);
  
  public Number contarImpresionesPorDespacho(int idDespacho);
  
  public Number calcularVisitasDomiciliariasPorGestor(int idDespacho, int idUsuario);
  
  public Number calcularVisitasPorEstado(int idDespacho, int idEstado);
  
  public Number calcularCorreoPorEstado(int idDespacho, int idEstado);
  
  public List<Impresion> buscarPorDireccion(int idDireccion);
}
