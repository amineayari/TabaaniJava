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
public class Reaction {
    private int id;
    private Commentaire commentaire;
    private int user_id;
    private int reaction;

    public Reaction(int id, Commentaire commentaire, int user_id, int reaction) {
        this.id = id;
        this.commentaire = commentaire;
        this.user_id = user_id;
        this.reaction = reaction;
    }

    public Reaction(Commentaire commentaire, int user_id, int reaction) {
        this.commentaire = commentaire;
        this.user_id = user_id;
        this.reaction = reaction;
    }

    public Reaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Commentaire getCommentaire() {
        return  commentaire;
    }

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getReaction() {
        return reaction;
    }

    public void setReaction(int reaction) {
        this.reaction = reaction;
    }
    
    
}
