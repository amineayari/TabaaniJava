/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entite.Commande;
import entite.Produit;

/**
 *
 * @author admin
 */
public class ProduitCommande {
    private Produit P ; 
    private Commande C ; 

    public ProduitCommande() {
    }

    public ProduitCommande(Produit P, Commande C) {
        this.P = P;
        this.C = C;
    }

    public Produit getP() {
        return P;
    }

    public Commande getC() {
        return C;
    }

    public void setP(Produit P) {
        this.P = P;
    }

    public void setC(Commande C) {
        this.C = C;
    }
    
    
}
