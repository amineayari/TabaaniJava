/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author user
 */
public class Transport {
    private int id;
    private String description;
    private String disponibilite;
    private Double price;
    private String type;

    public Transport() {
    }

    public Transport(String description, String disponibilite, Double price, String type) {
        this.description = description;
        this.disponibilite = disponibilite;
        this.price = price;
        this.type = type;
    }

    
    public Transport(int id,String description, String disponibilite, Double price, String type) {
        this.id=id;
        this.description = description;
        this.disponibilite = disponibilite;
        this.price = price;
        this.type = type;
    }

    @Override
    public String toString() {
        return "transport{" + "id=" + id + ", description=" + description + ", disponibilite=" + disponibilite + ", price=" + price + ", type=" + type + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}