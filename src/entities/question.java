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
public class question {
    int id;
    String question;
    String image;
    String type;

    public question() {
    }

    public question(int id, String question, String image, String type) {
        this.id = id;
        this.question = question;
        this.image = image;
        this.type = type;
    }

    public question(String question, String image, String type) {
        this.question = question;
        this.image = image;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "question{" + "id=" + id + ", question=" + question + ", image=" + image + ", type=" + type + '}';
    }
    
    
}
