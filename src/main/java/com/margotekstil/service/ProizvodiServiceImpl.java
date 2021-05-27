/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.service;

import com.margotekstil.model.Proizvodi;
import com.margotekstil.repository.ProizvodiRepository;
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
public class ProizvodiServiceImpl implements ProizvodiService {

    @Autowired
    private ProizvodiRepository proizvodiRepository;

    @Override
    public List<Proizvodi> findAllBy() {
        return proizvodiRepository.findAllBy();
    }
    
     @Override
    public List<Proizvodi> findAllByKategorija(String kategorija) {
        return proizvodiRepository.findAllByKategorija(kategorija);
    }

       @Override
    public   Proizvodi findFirstById(int id){
        return proizvodiRepository.findFirstById(id);
    }
  
       @Override
    public   void save(Proizvodi proizvod){
    proizvodiRepository.save(proizvod);
    }

    @Override
    public   List<Proizvodi> findFirstFew(Integer koliko) {
        return proizvodiRepository.findFirstFew(koliko);
    }
    
      
    @Override
    public   List<String> findListaKategorija(){
      return proizvodiRepository.findListaKategorija();
    
    }
    
       @Override
    public   Page<Proizvodi> findAllByActiveOrderByImeAsc(boolean active,Pageable pageable){
      return proizvodiRepository.findByActiveOrderByImeAsc(active, pageable);
    };
  
 @Override
    public  Page<Proizvodi> findByKategorijaOrderByActiveDescImeAsc(String kategorija,Pageable pageable){
       return proizvodiRepository.findByKategorijaOrderByActiveDescImeAsc( kategorija,pageable);
    };
    
}
