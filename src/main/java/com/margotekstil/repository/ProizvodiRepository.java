/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.repository;

import com.margotekstil.model.Proizvodi;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Aleksandra
 */
@Repository("proizvodiRepository")
public interface ProizvodiRepository extends JpaRepository<Proizvodi, Integer> {

    Page<Proizvodi> findByKategorijaOrderByActiveDescImeAsc(String kategorija,Pageable pageable);
  Page<Proizvodi> findByActiveOrderByImeAsc(boolean active,Pageable pageable);
    
    
    List<Proizvodi> findAllBy();
   
    List<Proizvodi> findAllByKategorija(String kategorija);
 
    Proizvodi findFirstById(int id);

    @Modifying
    @Query(value = "SELECT * FROM proizvodi LIMIT :koliko", nativeQuery = true)
    List<Proizvodi> findFirstFew(@Param("koliko") Integer koliko);

      //  @Modifying
    @Query(value = "SELECT DISTINCT  kategorija FROM margotekstil.proizvodi where active=true", nativeQuery = true)
    List<String> findListaKategorija();

}
