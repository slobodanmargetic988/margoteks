/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.service;

import com.margotekstil.model.Users;
import java.util.List;

/**
 *
 * @author Aleksandra
 */
public interface UsersService {

    List<Users> findAllBy();

    Users findFirstByEmail(String Email);
  Users findFirstByIme(String ime);
    Users findFirstByEmailAndPassword(String email, String lozinka);

    void save(Users user);
        void saveAndFlush(Users user);

}
