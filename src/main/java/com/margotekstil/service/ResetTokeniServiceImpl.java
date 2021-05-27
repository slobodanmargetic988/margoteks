/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.service;

import com.margotekstil.model.ResetTokeni;
import com.margotekstil.model.Users;
import com.margotekstil.repository.ResetTokeniRepository;
import com.margotekstil.repository.UsersRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aleksandra
 */
@Service
public class ResetTokeniServiceImpl implements ResetTokeniService {

    @Autowired
    private ResetTokeniRepository resetTokeniRepository;

    @Override
    public List<ResetTokeni> findAllBy() {
        return resetTokeniRepository.findAllBy();
    }

    @Override
    public ResetTokeni findFirstByVrednost(String vrednost) {
        return resetTokeniRepository.findFirstByVrednost(vrednost); 

    }

    @Override
    public void save(ResetTokeni resetTokeni) {
        resetTokeniRepository.save(resetTokeni);
    }
   @Override
    public void delete(ResetTokeni resetTokeni) {
        resetTokeniRepository.delete(resetTokeni);
    }

   
}
