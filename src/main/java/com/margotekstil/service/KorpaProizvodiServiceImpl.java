/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.service;

import com.margotekstil.model.Korpa;
import com.margotekstil.model.KorpaProizvodi;
import com.margotekstil.model.Proizvodi;
import com.margotekstil.repository.KorpaProizvodiRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aleksandra
 */
@Service
public class KorpaProizvodiServiceImpl implements KorpaProizvodiService {

    @Autowired
    private KorpaProizvodiRepository korpaProizvodiRepository;

    @Override
    public List<KorpaProizvodi> findAllBy() {
        return korpaProizvodiRepository.findAllBy();
    }

      @Override
    public void save(KorpaProizvodi korpaProizvodi) {

        korpaProizvodiRepository.save(korpaProizvodi);
    }
    @Override
    public void delete(KorpaProizvodi korpaProizvodi) {
        korpaProizvodiRepository.delete(korpaProizvodi);
    }

      @Override
    public KorpaProizvodi findFirstByKorpaAndProizvod(Korpa korpa, Proizvodi proizvod) {
       return korpaProizvodiRepository.findFirstByKorpaAndProizvod(korpa, proizvod);
    }
    
      @Override
    public void saveAndFlush(KorpaProizvodi korpaProizvodi) {

        korpaProizvodiRepository.saveAndFlush(korpaProizvodi);
    }
    
}
