package entite;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author user
 */
public class Hotel {
    private int id;
    private String nom;
    private String adresse;
    private double price;
    private String image;
    private String description;
    private Date datedebut;
    private Date datefin;
    private int id_promo;
    private Voyage voyage;
    private int id_transport;

    public Hotel(int id, String nom, String adresse, double price, String image, String description, Date datedebut, Date datefin, int id_promo, int id_transport) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.price = price;
        this.image = image;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.id_promo = id_promo;
        this.id_transport = id_transport;
    }
    

    public Hotel(String nom, String adresse, double price, String image, String description, Date datedebut, Date datefin, int id_promo, Voyage voyage) {
        this.nom = nom;
        this.adresse = adresse;
        this.price = price;
        this.image = image;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.id_promo = id_promo;
        this.voyage = voyage;
    }

    public Hotel(int id, String nom, String adresse, double price, String image, String description, Date datedebut, Date datefin, int id_promo) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.price = price;
        this.image = image;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.id_promo = id_promo;
    }

    public int getId_transport() {
        return id_transport;
    }

    public void setId_transport(int id_transport) {
        this.id_transport = id_transport;
    }
    

    public Hotel(int id, String nom, String adresse, double price, String image, String description, Date datedebut, Date datefin, int id_promo, Voyage voyage) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.price = price;
        this.image = image;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.id_promo = id_promo;
        this.voyage = voyage;
    }
    

    public Hotel() {
    }

    public Hotel(String nom, String adresse, double price, String image, String description, Date datedebut, Date datefin, int id_promo) {
      
        this.nom = nom;
        this.adresse = adresse;
        this.price = price;
        this.image = image;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.id_promo = id_promo;
    }
    public Hotel(String nom, String adresse, double price, String image, String description, Date datedebut, Date datefin, int id_promo,int id_voyage) {
      
        this.nom = nom;
        this.adresse = adresse;
        this.price = price;
        this.image = image;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.id_promo = id_promo;
        this.voyage.setId(id_voyage);
    }

    public int getId_promo() {
        return id_promo;
    }

    public void setId_promo(int id_promo) {
        this.id_promo = id_promo;
    }
    

    public Hotel(int id, String nom, String adresse, double price, String image, String description, Date datedebut, Date datefin) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.price = price;
        this.image = image;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }
    

    public Hotel(String nom, String adresse, double price, String description, Date datedebut, Date datefin) {
        this.nom = nom;
        this.adresse = adresse;
        this.price = price;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hotel other = (Hotel) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
    

    public Hotel(String nom, String adresse, double price, String image, String description) {
        this.nom = nom;
        this.adresse = adresse;
        this.price = price;
        this.image = image;
        this.description = description;
    }
    

    public Hotel(String nom, String adresse, double price, String image, String description, Date datedebut, Date datefin) {
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
        return "hotel{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", price=" + price + ", image=" + image + ", description=" + description + ", datedebut=" + datedebut + ", datefin=" + datefin + '}';
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