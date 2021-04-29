/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class Commande {
    private int Id ; 
    private int Nbproduit ; 
    private Date Date ; 
    private String Methodepaiement ; 
    private String Etat ; 
    private float Prixtotale ; 
    private User user ; 
    
    public Commande() {
    }

    public Commande(int Id, int Nbproduit, Date Date, String Methodepaiement, String Etat, float Prixtotale, User user) {
        this.Id = Id;
        this.Nbproduit = Nbproduit;
        this.Date = Date;
        this.Methodepaiement = Methodepaiement;
        this.Etat = Etat;
        this.Prixtotale = Prixtotale;
        this.user = user;
    }

    public Commande(int Id, int Nbproduit, Date Date, String Methodepaiement, String Etat, float Prixtotale) {
        this.Id = Id;
        this.Nbproduit = Nbproduit;
        this.Date = Date;
        this.Methodepaiement = Methodepaiement;
        this.Etat = Etat;
        this.Prixtotale = Prixtotale;
    }

    public Commande(int Nbproduit, Date Date, String Methodepaiement, float Prixtotale) {
        this.Nbproduit = Nbproduit;
        this.Date = Date;
        this.Methodepaiement = Methodepaiement;
        this.Prixtotale = Prixtotale;
    }

    public Commande(int Nbproduit, Date Date, String Methodepaiement, String Etat) {
        this.Nbproduit = Nbproduit;
        this.Date = Date;
        this.Methodepaiement = Methodepaiement;
        this.Etat = Etat;
    }

    public Commande(int Nbproduit, Date Date, String Methodepaiement) {
        this.Nbproduit = Nbproduit;
        this.Date = Date;
        this.Methodepaiement = Methodepaiement;
    }

    public Commande(int Id, int Nbproduit, Date Date, String Methodepaiement, String Etat) {
        this.Id = Id;
        this.Nbproduit = Nbproduit;
        this.Date = Date;
        this.Methodepaiement = Methodepaiement;
        this.Etat = Etat;
    }

    public int getId() {
        return Id;
    }

    public int getNbproduit() {
        return Nbproduit;
    }

    public Date getDate() {
        return Date;
    }

    public String getMethodepaiement() {
        return Methodepaiement;
    }

    public String getEtat() {
        return Etat;
    }

    public float getPrixtotale() {
        return Prixtotale;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setNbproduit(int Nbproduit) {
        this.Nbproduit = Nbproduit;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public void setMethodepaiement(String Methodepaiement) {
        this.Methodepaiement = Methodepaiement;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public void setPrixtotale(float Prixtotale) {
        this.Prixtotale = Prixtotale;
    }

    public void getEtat(String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
        
    
}
