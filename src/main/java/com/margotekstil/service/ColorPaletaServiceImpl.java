/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.service;

import com.margotekstil.model.ColorPaleta;
import com.margotekstil.repository.ColorPaletaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aleksandra
 */
@Service
public class ColorPaletaServiceImpl implements ColorPaletaService {

    @Autowired
    private ColorPaletaRepository colorPaletaRepository;

    @Override
    public List<ColorPaleta> findAllBy() {
        return colorPaletaRepository.findAllBy();
    }
    
  @Override
    public Page<ColorPaleta> findAllBy(Pageable pageable) {
        return colorPaletaRepository.findAllBy(pageable);
    }
    
//     @Override
//    public Page<ColorPaleta> findByProizvodIsNullAndGlavnazaproizvodIsNullAndActive(boolean active, Pageable pageable) {
//        return colorPaletaRepository.findByProizvodIsNullAndGlavnazaproizvodIsNullAndActive(active,  pageable);
//    }
//    
    
  @Override
    public void save(ColorPaleta colorPaleta){
        colorPaletaRepository.save(colorPaleta);
    }
    
 
  @Override
    public ColorPaleta findFirstById(Integer id){
    return colorPaletaRepository.findFirstById(id);
    
    }
}
