/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author BJI
 */
public class Reponse {
    int id;
    question question;
    String reponse;

    public Reponse() {
    }

    public Reponse(int id, question question, String reponse) {
        this.id = id;
        this.question = question;
        this.reponse = reponse;
    }

    public Reponse(question question, String reponse) {
        this.question = question;
        this.reponse = reponse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public question getQuestion() {
        return question;
    }

    public void setQuestion(question question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", question=" + question + ", reponse=" + reponse + '}';
    }
    
    
}
