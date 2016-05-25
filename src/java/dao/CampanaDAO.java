/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Campana;
import java.util.List;

/**
 *
 * @author Eduardo
 */
public interface CampanaDAO {
  public List<Campana> buscarTodas();
  public Campana buscarPorId(int idCampana);
  public Campana buscarCampanaDelCredito(int idCredito);
}