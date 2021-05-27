/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.service;

import com.margotekstil.model.Proizvodi;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Aleksandra
 */
public interface ProizvodiService {

    List<Proizvodi> findAllBy();

    Proizvodi findFirstById(int id);

    void save(Proizvodi proizvod);

    List<Proizvodi> findFirstFew(Integer koliko);

    List<Proizvodi> findAllByKategorija(String kategorija);

    
        List<String> findListaKategorija();
        
        Page<Proizvodi> findAllByActiveOrderByImeAsc(boolean active,Pageable pageable);
  
 Page<Proizvodi> findByKategorijaOrderByActiveDescImeAsc(String kategorija,Pageable pageable);
}
