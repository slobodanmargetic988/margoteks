/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.service;

import com.margotekstil.model.Photo;
import com.margotekstil.model.Proizvodi;
import com.margotekstil.repository.PhotoRepository;
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
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public List<Photo> findAllBy() {
        return photoRepository.findAllBy();
    }
  @Override
    public Page<Photo> findAllBy(Pageable pageable) {
        return photoRepository.findAllBy(pageable);
    }
    
     @Override
    public Page<Photo> findByProizvodIsNullAndGlavnazaproizvodIsNullAndActive(boolean active, Pageable pageable) {
        return photoRepository.findByProizvodIsNullAndGlavnazaproizvodIsNullAndActive(active,  pageable);
    }
    
    
  @Override
    public void save(Photo photo){
        photoRepository.save(photo);
    }
    
 
  @Override
    public Photo findFirstById(Integer id){
    return photoRepository.findFirstById(id);
    
    }
}
