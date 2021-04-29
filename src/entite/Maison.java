/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Date;

/**
 *
 * @author user
 */
public class Maison {
    private int id;
    private String nom;
    private String adresse;
    private double price;
    private String image;
    private String description;
    private Date datedebut;
    private Date datefin;

    public Maison() {
    }

    public Maison(int id, String nom, String adresse, double price, String image, String description, Date datedebut, Date datefin) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.price = price;
        this.image = image;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }
    

    public Maison(String nom, String adresse, double price, String image, String description, Date datedebut, Date datefin) {
        this.nom = nom;
        this.adresse = adresse;
        this.price = price;
        this.image = image;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    @Override
    public String toString() {
        return "maison{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", price=" + price + ", image=" + image + ", description=" + description + ", datedebut=" + datedebut + ", datefin=" + datefin + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }
    
    
}