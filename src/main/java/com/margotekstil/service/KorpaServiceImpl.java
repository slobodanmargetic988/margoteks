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
import com.margotekstil.repository.KorpaProizvodiRepository;
import com.margotekstil.repository.KorpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aleksandra
 */
@Service
public class KorpaServiceImpl implements KorpaService {

    @Autowired
    private KorpaRepository korpaRepository;

    @Autowired
    private KorpaProizvodiRepository korpaproizvodiRepository;

    @Override
    public List<Korpa> findAllBy() {
        return korpaRepository.findAllBy();
    }

    @Override
    public Korpa findFirstById(int id) {
        return korpaRepository.findFirstById(id);
    }

    @Override
    public void save(Korpa korpa) {
        korpaRepository.save(korpa);

    }
  @Override
    public void saveAndFlush(Korpa korpa) {
        korpaRepository.saveAndFlush(korpa);

    }

 /*   public Integer dodajProizvod(Integer proizvodId, Integer kolicina, Users user) {
        Integer addedQuantity = kolicina;

        Proizvodi proizvod = korpaRepository.findFirstById(proizvodId);

        KorpaProizvodi korpaProizvod = korpaproizvodiRepository.findFirstByKorpaAndProizvod(korpa, proizvod);

        if (korpaProizvod != null) {
            addedQuantity = korpaProizvod.getKolicina() + kolicina;
            korpaProizvod.setKolicina(addedQuantity);
        } else {
            korpaProizvod = new KorpaProizvodi();
            korpaProizvod.setKolicina(kolicina);
            korpaProizvod.setProizvod(proizvod);
        }

        korpaRepository.save(korpaProizvod);

        return addedQuantity;
    }*/

}
