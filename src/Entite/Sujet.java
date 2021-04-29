/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author MEGA-PC
 */
public class Sujet {
    private int id;
    private int user_id;
    private String sujet;
    private String description;
    private String image;
    private int nbvues;

    public Sujet(int user_id, String sujet, String description, String image, int nbvues) {
        this.user_id = user_id;
        this.sujet = sujet;
        this.description = description;
        this.image = image;
        this.nbvues = nbvues;
    }

    public Sujet() {
    }

    public Sujet(int id, int user_id, String sujet, String description, String image, int nbvues) {
        this.id = id;
        this.user_id = user_id;
        this.sujet = sujet;
        this.description = description;
        this.image = image;
        this.nbvues = nbvues;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNbveus() {
        return nbvues;
    }

    public void setNbveus(int nbvues) {
        this.nbvues = nbvues;
    }

    @Override
    public String toString() {
        return "Sujet{" + "id=" + id + ", user_id=" + user_id + ", sujet=" + sujet + ", description=" + description + ", image=" + image + ", nbveus=" + nbvues + '}';
    }
    
}
