/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.repository;

import com.margotekstil.model.ResetTokeni;
import com.margotekstil.model.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Aleksandra
 */


@Repository("resetTokeniRepository")
public interface ResetTokeniRepository extends JpaRepository<ResetTokeni, Integer> {

    List<ResetTokeni> findAllBy();
    ResetTokeni findFirstByVrednost(String vrednost);
   // Notifikacije findFirstByOpstinaAndToken(  String opstina, String token);
    
   
}
