/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.repository;

import com.margotekstil.model.ColorPaleta;
import com.margotekstil.model.Photo;
import com.margotekstil.model.Proizvodi;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Aleksandra
 */


@Repository("colorPaletaRepository")
public interface ColorPaletaRepository extends JpaRepository<ColorPaleta, Integer> {

    List<ColorPaleta> findAllBy();
    Page<ColorPaleta> findAllBy(Pageable pageable);
//    Page<ColorPaleta> findByProizvodIsNullAndGlavnazaproizvodIsNullAndActive(boolean active,Pageable pageable);
    
    
    ColorPaleta findFirstById(Integer id);
    
  
   
}
