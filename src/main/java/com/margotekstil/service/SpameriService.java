/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.service;

import com.margotekstil.model.Spameri;
import java.util.List;

/**
 *
 * @author Aleksandra
 */
public interface SpameriService {

    List<Spameri> findAllBy();
    
void save(Spameri spameri);
    Spameri findByIpadresa(String ipadresa);

}
