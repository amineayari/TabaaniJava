/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class commande_produit {
   private Commande C ; 
   private Produit P ; 

    public commande_produit(Commande C, Produit P) {
        this.C = C;
        this.P = P;
    }

    public Commande getC() {
        return C;
    }

    public Produit getP() {
        return P;
    }

    public void setC(Commande C) {
        this.C = C;
    }

    public void setP(Produit P) {
        this.P = P;
    }
   
}
