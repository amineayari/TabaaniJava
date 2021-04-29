/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava.entities;

import java.sql.Date;

/**
 *
 * @author Abirn
 */
public class Reclamation {
    
    private int id;
    private String sujet;
    private String description;
    private Date date;
    private User user_id;
   
    
  // private User idU ;
    public Reclamation() {
    }

    public Reclamation(String sujet, String description, Date date) {
        this.sujet = sujet;
        this.description = description;
        this.date = date;
    }
    

    public Reclamation(int id, String sujet, String description, Date date) {
        this.id = id;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
    }

    public Reclamation(int id, String sujet, String description, Date date, User user_id) {
        this.id = id;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.user_id = user_id;
    }

    public Reclamation(String sujet, String description, Date date, User user_id) {
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.user_id = user_id;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", sujet=" + sujet + ", description=" + description + ", date=" + date + ", user_id=" + user_id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
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
        final Reclamation other = (Reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
