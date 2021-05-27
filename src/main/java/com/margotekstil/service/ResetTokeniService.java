/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.service;

import com.margotekstil.model.ResetTokeni;
import com.margotekstil.model.Users;
import java.util.List;

/**
 *
 * @author Aleksandra
 */
public interface ResetTokeniService {

    List<ResetTokeni> findAllBy();

    ResetTokeni findFirstByVrednost(String vrednost);

    void save(ResetTokeni eesetTokeni);
  void delete(ResetTokeni eesetTokeni);
}
