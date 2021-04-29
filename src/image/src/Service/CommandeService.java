/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entite.Commande;
import entite.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author admin
 */
public class CommandeService implements IService<Commande>{

    private Statement Ste;
    private PreparedStatement Pst;
    private ResultSet Rs;
    private Connection Cox;
    private ProduitCommande PC = new ProduitCommande();
    
    public CommandeService() {
        Cox = DataSource.getInstance().getConn();
    }

    @Override
    public void Ajouter(Commande T) {
        int max = 0 ; 
        String Req = "insert into commande (nbproduit,date,methodedepaiement,etat,user_id) values (?,?,?,'en cours',?)";
        try {
            Pst = Cox.prepareStatement(Req);
            Pst.setInt(1, T.getNbproduit());
            Pst.setDate(2, T.getDate());
            Pst.setString(3, T.getMethodepaiement());
            Pst.setInt(4, T.getUser().getId());
            
            
            Pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        max = BigId() ; 
      
        String Reqq = "insert into commande_produit (commande_id,produit_id) values (?,?)";
        try {
            Pst = Cox.prepareStatement(Req);
            Pst.setInt(1,max);
     //       Pst.setInt(2, );            
            Pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public void Ajouter2(Commande T, int Produitid) {
        int max = 0 ; 
        String Req = "insert into commande (nbproduit,date,methodedepaiement,etat,prixtotale) values (?,?,?,'en cours',?)";
        try {
            Pst = Cox.prepareStatement(Req);
            Pst.setInt(1, T.getNbproduit());
            Pst.setDate(2, T.getDate());
            Pst.setString(3, T.getMethodepaiement());
            Pst.setFloat(4, T.getPrixtotale());
            
                        
            Pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        max = BigId() ; 
      
        String Reqq = "insert into commande_produit (commande_id,produit_id) values (?,?)";
        try {
            Pst = Cox.prepareStatement(Reqq);
            Pst.setInt(1,max);
            Pst.setInt(2,Produitid);            
            Pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    @Override
    public List<Commande> Afficher() {
        String Req = "select * from commande";
        List<Commande> list = new ArrayList<>();
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                list.add(new Commande(Rs.getInt("id"), Rs.getInt("nbproduit"), Rs.getDate("date"), Rs.getString("methodedepaiement"), Rs.getString("etat"), Rs.getFloat("prixtotale")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Commande TrouverById(int id) {
        Commande C = null;
        String Req = "select * from commande where id=" + id + "";
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                C = new Commande(Rs.getInt("id"), Rs.getInt("nbproduit"), Rs.getDate("date"), Rs.getString("methodedepaiement"), Rs.getString("etat"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return C;
    }

    @Override
    public void Modifier(Commande T) {
         String requeteUpdate = "UPDATE  `commande` set `nbproduit`='" + T.getNbproduit() + "',`date`='" + T.getDate() + "',`methodedepaiement`='" + T.getMethodepaiement() + "',`etat`='" + T.getEtat() + "' where `commande`.`id`='" + T.getId() + "' ";

        try {
            Ste = Cox.createStatement();
             Ste.executeUpdate(requeteUpdate);
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
      
    }

    @Override
    public void Supprimer(int id) {
               String Req = " delete from commande where id="+id+" ";
        try {
            Ste = Cox.createStatement();
            Ste.execute(Req);
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int BigId()
    {    
        int max = 0 ; 
      String Select = "SELECT MAX(id) FROM commande" ;
       try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Select);
            while (Rs.next()) {
               max = Rs.getInt("MAX(id)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
      //  System.out.println(max);
        return max ; 
    }
    
     public void modifierChamp(String champ,String Valeur,int id){
     try{
       
        String requete = "UPDATE commande  SET "+champ+"=?"
                    + " WHERE id=?";
            PreparedStatement pst = Cox.prepareStatement(requete);
           
            pst.setString(1,Valeur);
            pst.setInt(2,id);
            pst.executeUpdate();
       } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public void Ajouter2(Commande C) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
