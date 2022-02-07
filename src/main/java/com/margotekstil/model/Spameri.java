/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Aleksandra
 */
@Entity
@Table(name = "spameri")
//@EntityListeners(AuditingEntityListener.class)
public class Spameri {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "ipadresa")
    private String ipadresa;

     @Column(name = "brojac")
    private int brojac;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIpadresa() {
        return ipadresa;
    }

    public void setIpadresa(String ipadresa) {
        this.ipadresa = ipadresa;
    }

    public int getBrojac() {
        return brojac;
    }

    public void setBrojac(int brojac) {
        this.brojac = brojac;
    }
    
   
     
}
