/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;


import java.sql.Date;

/**
 *
 * @author MEGA-PC
 */
public class Commentaire {
    private int id;
    private Sujet sujet;
    private int user_id;
    private String commentaire;
    private String filtredcomment;
    private Date dateajout;

    public Commentaire() {
    }

    public Commentaire(Sujet sujet, int user_id, String commentaire, String filtredcomment, Date dateajout) {
        this.sujet = sujet;
        this.user_id = user_id;
        this.commentaire = commentaire;
        this.filtredcomment = filtredcomment;
        this.dateajout = dateajout;
    }

    public Commentaire(int id, Sujet sujet, int user_id, String commentaire, String filtredcomment, Date dateajout) {
        this.id = id;
        this.sujet = sujet;
        this.user_id = user_id;
        this.commentaire = commentaire;
        this.filtredcomment = filtredcomment;
        this.dateajout = dateajout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sujet getSujet() {
        return sujet;
    }

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getFiltredcomment() {
        return filtredcomment;
    }

    public void setFiltredcomment(String filtredcomment) {
        this.filtredcomment = filtredcomment;
    }

    public Date getDateajout() {
        return dateajout;
    }

    public void setDateajout(Date dateajout) {
        this.dateajout = dateajout;
    }
    
    
}
