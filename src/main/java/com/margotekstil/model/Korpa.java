/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Aleksandra
 */
@Entity
@Table(name = "korpa")
//@EntityListeners(AuditingEntityListener.class)
public class Korpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;



    @OneToMany(mappedBy="korpa",fetch = FetchType.EAGER)
    private List<KorpaProizvodi> korpaproizvodi;

     @OneToOne(mappedBy = "korpa",cascade = CascadeType.ALL,fetch = FetchType.EAGER)   
    private ZavrsenePorudzbine zavrsenaPorudzbina;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<KorpaProizvodi> getKorpaproizvodi() {
        return korpaproizvodi;
    }

    public void setKorpaproizvodi(List<KorpaProizvodi> korpaproizvodi) {
        this.korpaproizvodi = korpaproizvodi;
    }

    public ZavrsenePorudzbine getZavrsenaPorudzbina() {
        return zavrsenaPorudzbina;
    }

    public void setZavrsenaPorudzbina(ZavrsenePorudzbine zavrsenaPorudzbina) {
        this.zavrsenaPorudzbina = zavrsenaPorudzbina;
    }


   

}
