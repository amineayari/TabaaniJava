/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entite.Promotion;
import entite.Voyage;
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
import views.VoyageController;

/**
 *
 * @author House_Info
 */
public class VoyageService implements Iservice <Voyage> {
     private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public VoyageService() {
        conn = DataSource.getInstance().getConn();
    }

    
  

    
    @Override
    public void ajouter(Voyage v) {
         try {
             
        String requete = "INSERT INTO voyage (id,destination,image,description,prix,date_debut,date_fin,categorie,programme,inclut,ninclut,lat,lng) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst1 = conn.prepareStatement(requete);
            pst1.setInt(1,v.getId());
            pst1.setString(2,v.getDestination());
            pst1.setString(3,v.getImage());
            pst1.setString(4,v.getDescription());
            pst1.setDouble(5, v.getPrix());
            pst1.setDate(6, v.getDate_debut());
            pst1.setDate(7, v.getDate_fin());
            pst1.setString(8, v.getCategorie());
            pst1.setString(9, v.getProgramme());
            pst1.setString(10, v.getInclut());
            pst1.setString(11, v.getNinclut());
            pst1.setDouble(12, v.getLat());
            pst1.setDouble(13, v.getLng());
            
            pst1.executeUpdate();
            System.out.println("Voyage ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      }

    
    public void supprimer(Voyage v) {
           try {
            String requete = "DELETE FROM voyage WHERE id=?";//
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, v.getId());
            pst.executeUpdate();
            System.out.println("Le voyage est supprimé avec succés ! \n");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression \n" + ex.getMessage());
        }
    }

    @Override
    public void modifier(Voyage v) {
      try {
            String requete = "UPDATE `voyage` SET destination=?,image=?,description=?,prix=?,date_debut=?,date_fin=?,categorie=?,programme=?,inclut=?,ninclut=?,lat=?,lng=? WHERE id= ?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(13, v.getId());
             pst.setString(1,v.getDestination());
            pst.setString(2,v.getImage());
            pst.setString(3,v.getDescription());
            pst.setDouble(4, v.getPrix());
            pst.setDate(5, v.getDate_debut());
            pst.setDate(6, v.getDate_fin());
            pst.setString(7, v.getCategorie());
            pst.setString(8, v.getProgramme());
            pst.setString(9,v.getInclut());
            pst.setString(10,v.getNinclut());
            pst.setDouble(11,v.getLat());
            pst.setDouble(12,v.getLng());

            pst.executeUpdate();
            System.out.println("Le Voyage est modifiée avec succés \n ");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification \n " + ex.getMessage());
        }
    }
   
    
    @Override
    public List<Voyage> afficher() {
             String req = "select * from voyage";

        List<Voyage> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Voyage(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getDate(6), rs.getDate(7), rs.getString(8),rs.getInt(9),rs.getDouble(10), rs.getDouble(11),rs.getString(12),rs.getString(13),rs.getString(14)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void supprimer(int t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 public void modifierChamp(String champ,String Valeur,int id){
     try{
        
        String requete = "UPDATE voyage  SET "+champ+"=?"
                    + " WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            
            pst.setString(1,Valeur);
            pst.setInt(2,id);
            pst.executeUpdate();
       } catch (SQLException ex) {
            Logger.getLogger(VoyageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
      public Voyage TrouverById(int id) {
        Voyage P = null;
        String Req = "select * from voyage where id=" + id + "";
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(Req);

            while (rs.next()) {
                P = new Voyage(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getDate(6), rs.getDate(7), rs.getString(8),rs.getInt(9),rs.getDouble(10), rs.getDouble(11),rs.getString(12),rs.getString(13),rs.getString(14));
             }
        } catch (SQLException ex) {
            Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return P;
    }

//public double calculPromo(double p){
  //  Voyage.get
//}
}