/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.service;

import com.margotekstil.model.Spameri;
import com.margotekstil.repository.SpameriRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aleksandra
 */
@Service
public class SpameriServiceImpl implements SpameriService {

    @Autowired
    private SpameriRepository spameriRepository;

    @Override
    public List<Spameri> findAllBy() {
        return spameriRepository.findAllBy();
    }
  
  
    
  @Override
    public void save(Spameri spameri){
        spameriRepository.save(spameri);
    }
    
 
  @Override
    public Spameri findByIpadresa(String ipadresa){
    return spameriRepository.findByIpadresa(ipadresa);
    
    }
}
