/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author House_Info
 */
public class Promotion {
    private int id;
    private double pourcentage;
    private double prix;
    private Date date_debut;
    private Date date_fin;

    public Promotion(int id) {
        this.id = id;
    }

    public Promotion(int id, double pourcentage, double prix) {
        this.id = id;
        this.pourcentage = pourcentage;
        this.prix = prix;
        
    }

    public Promotion() {
    }

    public Promotion(double pourcentage,double prix, Date date_debut, Date date_fin) {
        this.pourcentage = pourcentage;
        this.prix = prix;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Promotion(double pourcentage,double prix) {
        this.pourcentage = pourcentage;
        this.prix = prix;
       
    }

    public Promotion(int id, double pourcentage,double prix, Date date_debut, Date date_fin) {
        this.id = id;
        this.pourcentage = pourcentage;
        this.prix = prix;
        
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
public Promotion(double pourcentage,double prix, Date date_debut, Date date_fin,int id) {
       
        this.pourcentage = pourcentage;
        this.prix = prix;
        
        this.date_debut = date_debut;
        this.date_fin = date_fin;
         this.id = id;
    }

    public Promotion(int aInt, String string, String string0, String string1, Date date, Date date0, String string2, String string3, int aInt0, int aInt1, int aInt2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Double pourcentage) {
        this.pourcentage = pourcentage;
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

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", prix=" + prix + ", pourcentage=" + pourcentage + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
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
        final Promotion other = (Promotion) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.prix) != Double.doubleToLongBits(other.prix)) {
            return false;
        }
        if (Double.doubleToLongBits(this.pourcentage) != Double.doubleToLongBits(other.pourcentage)) {
            return false;
        }
        if (!Objects.equals(this.date_debut, other.date_debut)) {
            return false;
        }
        if (!Objects.equals(this.date_fin, other.date_fin)) {
            return false;
        }
        return true;
    }
    
    
    
}
