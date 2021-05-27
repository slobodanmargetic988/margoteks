/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.service;

import com.margotekstil.model.Users;
import com.margotekstil.repository.UsersRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aleksandra
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Users> findAllBy() {
        return usersRepository.findAllBy();
    }

    @Override
    public Users findFirstByEmail(String Email) {
        return usersRepository.findFirstByEmail(Email);
    }
    @Override
    public Users findFirstByIme(String ime) {
        return usersRepository.findFirstByIme(ime);
    }

    @Override
    public Users findFirstByEmailAndPassword(String email, String lozinka) {
        return usersRepository.findFirstByEmailAndPassword(email, lozinka);
    }

    @Override
    public void save(Users user) {
      
        usersRepository.save(user);
    }
      @Override
    public void saveAndFlush(Users user) {
      
        usersRepository.saveAndFlush(user);
    }


}
