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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Aleksandra
 */
@Entity
@Table(name = "proizvodi")
//@EntityListeners(AuditingEntityListener.class)
public class Proizvodi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "ime")
    private String ime;

    @Column(name = "cena")
    private double cena;

    @Column(name = "opis")
    private String opis;

    @Column(name = "pdv")
    private double pdv;

    @Column(name = "kilaza")
    private double kilaza;
    
    @Column(name = "dimenzije")
    private String dimenzije;
    
    @OneToMany(mappedBy = "proizvod",fetch = FetchType.EAGER)
    private List<Photo> photos;

    @OneToMany(mappedBy = "proizvod",fetch = FetchType.LAZY)
    private List<ColorPaleta> boje;
    
    @Column(name = "keywords")
    private String keywords;

    @Column(name = "active")
    private Boolean active;
    @Column(name = "kategorija")
    private String kategorija;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "slicni_proizvodi",
            joinColumns = {
                @JoinColumn(name = "proizvod_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "slicni_proizvod_id")}
    )
    private List<Proizvodi> slicniproizvodi;
    
    
@OneToOne(mappedBy = "glavnazaproizvod")   
    private Photo glavnaslika;

    public List<ColorPaleta> getBoje() {
        return boje;
    }

    public void setBoje(List<ColorPaleta> boje) {
        this.boje = boje;
    }

    public Photo getGlavnaslika() {
        return glavnaslika;
    }

    public void setGlavnaslika(Photo glavnaslika) {
        this.glavnaslika = glavnaslika;
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

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getPdv() {
        return pdv;
    }

    public void setPdv(double pdv) {
        this.pdv = pdv;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public List<Proizvodi> getSlicniproizvodi() {
        return slicniproizvodi;
    }

    public void setSlicniproizvodi(List<Proizvodi> slicniproizvodi) {
        this.slicniproizvodi = slicniproizvodi;
    }

    public double getKilaza() {
        return kilaza;
    }

    public void setKilaza(double kilaza) {
        this.kilaza = kilaza;
    }

    public String getDimenzije() {
        return dimenzije;
    }

    public void setDimenzije(String dimenzije) {
        this.dimenzije = dimenzije;
    }

    public void setTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
