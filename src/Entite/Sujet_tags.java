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
public class Sujet_tags {
    private Sujet sujet;
    private Tags tags;

    
    
    public Sujet_tags(Sujet sujet, Tags tags) {
        this.sujet = sujet;
        this.tags = tags;
    }

    public Sujet getSujet() {
        return sujet;
    }

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }
    
    
    
}
