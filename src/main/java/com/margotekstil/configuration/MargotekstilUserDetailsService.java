package com.margotekstil.configuration;

import com.margotekstil.configuration.MargotekstilUserPrincipal;
import com.margotekstil.model.Users;
import com.margotekstil.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class MargotekstilUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 
        Users user = usersRepository.findFirstByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
 
        return new MargotekstilUserPrincipal(user);
    }



}
