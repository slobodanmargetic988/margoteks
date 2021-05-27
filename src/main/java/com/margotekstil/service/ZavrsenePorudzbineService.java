/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.service;

import com.margotekstil.model.Korpa;
import com.margotekstil.model.KorpaProizvodi;
import com.margotekstil.model.Proizvodi;
import com.margotekstil.model.Users;
import com.margotekstil.model.ZavrsenePorudzbine;
import java.util.List;

/**
 *
 * @author Aleksandra
 */
public interface ZavrsenePorudzbineService {

    List<ZavrsenePorudzbine> findAllBy();


    void save(ZavrsenePorudzbine zavrsenePorudzbine);
    void delete(ZavrsenePorudzbine zavrsenePorudzbine);
    ZavrsenePorudzbine findFirstById(Integer id);
  List<ZavrsenePorudzbine> findAllByUser(Users user);
   void saveAndFlush(ZavrsenePorudzbine zavrsenePorudzbine);
}
