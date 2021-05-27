/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.repository;

import com.margotekstil.model.Korpa;
import com.margotekstil.model.Users;
import com.margotekstil.model.ZavrsenePorudzbine;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Aleksandra
 */


@Repository("zavrsenePorudzbineRepository")
public interface ZavrsenePorudzbineRepository extends JpaRepository<ZavrsenePorudzbine, Integer> {

    List<ZavrsenePorudzbine> findAllBy();
    
     ZavrsenePorudzbine findFirstById(int id);
    
  List<ZavrsenePorudzbine> findAllByUser(Users user);
   
}
