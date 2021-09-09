/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.service;

import com.margotekstil.model.ColorPaleta;
import com.margotekstil.model.Korpa;
import com.margotekstil.model.KorpaProizvodi;
import com.margotekstil.model.Proizvodi;
import java.util.List;

/**
 *
 * @author Aleksandra
 */
public interface KorpaProizvodiService {

    List<KorpaProizvodi> findAllBy();


    void save(KorpaProizvodi korpaProizvod);
    void delete(KorpaProizvodi korpaProizvod);
      KorpaProizvodi findFirstByKorpaAndProizvod(Korpa korpa,Proizvodi proizvod);
 void saveAndFlush(KorpaProizvodi korpaProizvod);
 
 KorpaProizvodi findFirstByKorpaAndProizvodAndBoja(Korpa korpa,Proizvodi proizvod,ColorPaleta boja);
  KorpaProizvodi findOne(Integer kproizvod_id);
 
}
