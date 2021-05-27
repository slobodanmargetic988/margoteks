/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.service;

import com.margotekstil.model.Korpa;
import java.util.List;

/**
 *
 * @author Aleksandra
 */
public interface KorpaService {

    List<Korpa> findAllBy();

    Korpa findFirstById(int id);

    void save(Korpa korpa);
void saveAndFlush(Korpa korpa);
}
