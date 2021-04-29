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
public class Voyage {

    private int id;
    private String destination;
    private String image;
    private String description;
    private String programme;
    private String categorie;
    private double prix;
    private Date datedebut;
    private Date datefin;
    private Hotel hotel;

    public Voyage(String categorie, String description, String destination,String programme,String image, double prix, Date datedebut, Date datefin) {
        this.categorie = categorie;
        this.description = description;
        this.destination = destination;
        this.programme = programme;
        this.image=image;
        this.prix = prix;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    

    public Voyage(int id, String destination, String image, String description, double prix, Date datedebut, Date datefin) {
        this.id = id;
        this.destination = destination;
        this.image = image;
        this.description = description;
        this.prix = prix;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    

    public Voyage(int id, String destination, String image, String description, double prix, Date datedebut, Date datefin, Hotel hotel) {
        this.id = id;
        this.destination = destination;
        this.image = image;
        this.description = description;
        this.prix = prix;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.hotel = hotel;
    }
    

    
}
