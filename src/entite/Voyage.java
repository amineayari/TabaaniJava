/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Date;
import java.util.Objects;

/**
 * combobox
 *
 * @author House_Info
 */
public class Voyage {

    private int id;
    private int promotion_id;
    private String destination;
    private String image;
    private String description;
    private Double prix;
    private Date date_debut;
    private Date date_fin;
    private String categorie;
    private Double lat;
    private Double lng;
    private String programme;
    private String inclut;
    private String ninclut;
    

    public Voyage() {
    }

    public Voyage(int id) {
        this.id = id;
    }

    public Voyage(int id, String destination, String image, String description, double prix, String categorie, String programme, String inclut, String ninclut, double lat, double lng) {
        this.id = id;
        this.destination = destination;
        this.image = image;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
        this.programme = programme;
        this.inclut = inclut;
        this.ninclut = ninclut;
        this.lat = lat;
        this.lng = lng;
    }

  
    
    
//     list.add( rs.getString(8),rs.getDouble(9), rs.getDouble(10),rs.getString(11),rs.getString(12),rs.getString(13)));
 public Voyage(int id, String destination, String image, String description, double prix,Date date_debut,Date date_fin,
         String categorie,int promotion_id,double lng,double lat, String programme, String inclut, String ninclut ) {
        this.id = id;
        this.destination = destination;
        this.image = image;
        this.description = description;
        this.prix = prix;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.categorie = categorie;
        this.promotion_id = promotion_id;
        this.lng = lng;
        this.lat = lat;
        this.programme = programme;
        this.inclut = inclut;
        this.ninclut = ninclut;
        
 }

    public Voyage(String destination, String description,String image, double prix, Date date_debut, Date date_fin, String categorie, String programme, String inclut, String ninclut, double lat, double lng) {
       
        this.destination = destination;
        this.image = image;
        this.description = description;
        this.prix = prix;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.categorie = categorie;
        this.programme = programme;
        this.inclut = inclut;
        this.ninclut = ninclut;
        this.lat = lat;
        this.lng = lng;
    }

    public Voyage(int aInt, String string, String string0, String string1, double aDouble, Date date, Date date0, String string2, String string3, String string4, String string5, double aDouble0, double aDouble1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Voyage(String destination, String image, String description, double prix, Date date_debut, Date date_fin, String categorie, double lat, double lng, String programme, String inclut, String ninclut) {
        this.destination = destination;
        this.image = image;
        this.description = description;
        this.prix = prix;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.categorie = categorie;
        this.lat = lat;
        this.lng = lng;
        this.programme = programme;
        this.inclut = inclut;
        this.ninclut = ninclut;
    }

   //Voyage v1 = new Voyage(idDestination.getText(), idImage.getText(),idDescription.getText(),idPrix.getText(), Date.valueOf(idDatedeb.getValue()), Date.valueOf(idDateFin.getValue()),idCategorie.getText(),idProgramme.getText(),idInclut.getText(),idNinclut.getText(),Integer.parseInt(idLat.getText()),Integer.parseInt(idLong.getText()));

    public Voyage(String text, String text0, String text1, String text2, Date valueOf, Date valueOf0, String text3, String text4, String text5, String text6, int parseInt, int parseInt0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   


 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPromotion_id() {
        return promotion_id;
    }

    public void setPromotion_id(int promotion_id) {
        this.promotion_id = promotion_id;
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

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }


    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getInclut() {
        return inclut;
    }

    public void setInclut(String inclut) {
        this.inclut = inclut;
    }

    public String getNinclut() {
        return ninclut;
    }

    public void setNinclut(String ninclut) {
        this.ninclut = ninclut;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Voyage{" + "id=" + id + ", promotion_id=" + promotion_id + ", destination=" + destination + ", image=" + image + ", description=" + description + ", prix=" + prix + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", categorie=" + categorie + ", programme=" + programme + ", inclut=" + inclut + ", ninclut=" + ninclut + ", lat=" + lat + ", lng=" + lng + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Voyage other = (Voyage) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.promotion_id != other.promotion_id) {
            return false;
        }
        if (Double.doubleToLongBits(this.prix) != Double.doubleToLongBits(other.prix)) {
            return false;
        }
        if (Double.doubleToLongBits(this.lat) != Double.doubleToLongBits(other.lat)) {
            return false;
        }
        if (Double.doubleToLongBits(this.lng) != Double.doubleToLongBits(other.lng)) {
            return false;
        }
        if (!Objects.equals(this.destination, other.destination)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.date_debut, other.date_debut)) {
            return false;
        }
        if (!Objects.equals(this.date_fin, other.date_fin)) {
            return false;
        }
        if (!Objects.equals(this.categorie, other.categorie)) {
            return false;
        }
        if (!Objects.equals(this.programme, other.programme)) {
            return false;
        }
        if (!Objects.equals(this.inclut, other.inclut)) {
            return false;
        }
        if (!Objects.equals(this.ninclut, other.ninclut)) {
            return false;
        }
        return true;
    }

    
    
    
    
    
   
}
