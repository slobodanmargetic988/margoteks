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
import com.margotekstil.repository.KorpaProizvodiRepository;
import com.margotekstil.repository.ZavrsenePorudzbineRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aleksandra
 */
@Service
public class ZavrsenePorudzbineServiceImpl implements ZavrsenePorudzbineService {

    @Autowired
    private ZavrsenePorudzbineRepository zavrsenePorudzbineRepository;

    @Override
    public List<ZavrsenePorudzbine> findAllBy() {
        return zavrsenePorudzbineRepository.findAllBy();
    }

      @Override
    public void save(ZavrsenePorudzbine zavrsenePorudzbine) {

        zavrsenePorudzbineRepository.save(zavrsenePorudzbine);
    }
    @Override
    public void delete(ZavrsenePorudzbine zavrsenePorudzbine) {
        zavrsenePorudzbineRepository.delete(zavrsenePorudzbine);
    }

      @Override
    public ZavrsenePorudzbine findFirstById(Integer id) {
       return zavrsenePorudzbineRepository.findFirstById(id);
    }
    
    
       @Override
    public  List<ZavrsenePorudzbine> findAllByUser(Users user){
      return zavrsenePorudzbineRepository.findAllByUser(user);
    }
       @Override
    public void saveAndFlush(ZavrsenePorudzbine zavrsenePorudzbine) {

        zavrsenePorudzbineRepository.saveAndFlush(zavrsenePorudzbine);
    }
}
