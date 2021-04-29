/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author admin
 */
public class Produit {
    private int Id ; 
    private String Nom ;
    private float Prix ; 
    private String Image ; 
    private int Quantite ; 
    private float Avis ; 
    private int nbavis ; 

    public Produit() {
    }

    public Produit(String Nom, float Prix, String Image, int Quantite) {
        this.Nom = Nom;
        this.Prix = Prix;
        this.Image = Image;
        this.Quantite = Quantite;
    }

    public Produit(int Id, String Nom, float Prix, String Image, int Quantite) {
        this.Id = Id;
        this.Nom = Nom;
        this.Prix = Prix;
        this.Image = Image;
        this.Quantite = Quantite;
    }

    public Produit(int Id, String Nom, float Prix, String Image, int Quantite, float Avis) {
        this.Id = Id;
        this.Nom = Nom;
        this.Prix = Prix;
        this.Image = Image;
        this.Quantite = Quantite;
        this.Avis = Avis;
    }

    public Produit(int Id, String Nom, float Prix, String Image, int Quantite, float Avis, int nbavis) {
        this.Id = Id;
        this.Nom = Nom;
        this.Prix = Prix;
        this.Image = Image;
        this.Quantite = Quantite;
        this.Avis = Avis;
        this.nbavis = nbavis;
    }
    
   
    public int getId() {
        return Id;
    }

    public String getNom() {
        return Nom;
    }

    public float getPrix() {
        return Prix;
    }

    public String getImage() {
        return Image;
    }

    public int getQuantite() {
        return Quantite;
    }

    public float getAvis() {
        return Avis;
    }
      public int getNbavis() {
        return nbavis;
    }
    public void setId(int Id) {
        this.Id = Id;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }

    public void setAvis(float Avis) {
        this.Avis = Avis;
    }
    
       public void setNbavis(int nbavis) {
        this.nbavis = nbavis;
    }
       


    @Override
    public String toString() {
        return "Produit{" + "Id=" + Id + ", Nom=" + Nom + ", Prix=" + Prix + ", Image=" + Image + ", Quantite=" + Quantite + ", Avis=" + Avis + '}';
    }

    
    
    
}
