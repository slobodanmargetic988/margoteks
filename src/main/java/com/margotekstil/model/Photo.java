/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Aleksandra
 */
@Entity
@Table(name = "photo")
//@EntityListeners(AuditingEntityListener.class)
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "file_name")
    private String filename;
    
    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "proizvod_id", nullable = true)
    private Proizvodi proizvod;
    
    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "glavna_slika", referencedColumnName = "id")
    private Proizvodi glavnazaproizvod;

    public Proizvodi getGlavnazaproizvod() {
        return glavnazaproizvod;
    }

    public void setGlavnazaproizvod(Proizvodi glavnazaproizvod) {
        this.glavnazaproizvod = glavnazaproizvod;
    }
    
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Proizvodi getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvodi proizvod) {
        this.proizvod = proizvod;
    }
    
    

}
