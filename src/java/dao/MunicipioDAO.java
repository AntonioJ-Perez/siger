/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Municipio;
import java.util.List;

/**
 *
 * @author Eduardo
 */
public interface MunicipioDAO {
    public List<Municipio> buscarMunicipiosPorEstado(int idEstado);
    public List<Municipio> buscarTodo();
    public Municipio buscar(int idMunicipio);
    public Municipio buscar(String cadena);
    public Municipio buscarPorId(int idMunicipio);
    public Municipio buscarPorNombresMunicipioEstado(String nombreMunicipio, String nombreEstado);
}