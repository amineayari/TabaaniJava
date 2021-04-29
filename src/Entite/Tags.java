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
public class Tags {
    private int id;
    private String tag;

      public Tags(int id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public Tags() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

  
    
}
