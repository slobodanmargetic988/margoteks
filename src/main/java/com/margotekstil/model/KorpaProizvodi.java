/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Aleksandra
 */
@Entity
@Table(name = "korpa_proizvodi")
//@EntityListeners(AuditingEntityListener.class)
public class KorpaProizvodi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "kolicina")
    private int kolicina;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "korpa_id", nullable = false)
    private Korpa korpa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proizvod_id", nullable = false)
    private Proizvodi proizvod;
    
     @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boja", nullable = false)
    private ColorPaleta boja;
     
     

    public ColorPaleta getBoja() {
        return boja;
    }

    public void setBoja(ColorPaleta boja) {
        this.boja = boja;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Korpa getKorpa() {
        return korpa;
    }

    public void setKorpa(Korpa korpa) {
        this.korpa = korpa;
    }

    public Proizvodi getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvodi proizvod) {
        this.proizvod = proizvod;
    }

}
