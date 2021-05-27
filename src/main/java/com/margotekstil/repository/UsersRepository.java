/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.repository;

import com.margotekstil.model.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Aleksandra
 */


@Repository("usersRepository")
public interface UsersRepository extends JpaRepository<Users, Integer> {

    List<Users> findAllBy();
    Users findFirstByEmail(String email);
    Users findFirstByIme(String ime);
   Users findFirstByEmailAndPassword(String email, String lozinka);
   // Notifikacije findFirstByOpstinaAndToken(  String opstina, String token);
    
   
}
