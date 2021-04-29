/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

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
import javax.sound.midi.Soundbank;
import utils.DataSource;

/**
 *
 * @author admin
 */
public class ProduitService implements IService<Produit> {

    private Statement Ste;
    private PreparedStatement Pst;
    private ResultSet Rs;
    private Connection Cox;

    public ProduitService() {
        Cox = DataSource.getInstance().getConn();
    }
    public float Rating (Produit P ) {
        int nb =0 ; 
        System.out.println("aaaaaaaaaaaa" + P.getAvis());
         String Reqq = "select nbavis from produit where id=" +  P.getId()+ "";
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Reqq);
            while (Rs.next()) {
                nb = Rs.getInt("nbavis") ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        float X = 0 ; 
         String Req = "select avis from produit where id=" +  P.getId()+ "";
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                X = Rs.getFloat("avis") ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
      //  System.out.println("aviskdim" + X);
     //   System.out.println("nbavis" + nb );
        System.out.println("avis" + P.getAvis());
        X = ( ( (X * nb) + P.getAvis() ) / (nb + 1 ) )   ; 
        
        nb ++ ; 
        System.out.println("avis" + X);
       
     String requeteUpdate = "UPDATE  `produit` set `avis`='" + X + "' where `produit`.`id`='" + P.getId() + "' ";
        try {
             Ste = Cox.createStatement();
             Ste.executeUpdate(requeteUpdate);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        

            String requeteUpdate2 = "UPDATE  `produit` set `nbavis`='" + nb + "' where `produit`.`id`='" + P.getId() + "' ";
        try {
             Ste = Cox.createStatement();
             Ste.executeUpdate(requeteUpdate2);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  X ;
             
    }
    @Override
    public void Ajouter(Produit P) {
        String Req = "insert into produit (nom,prix,image,quantite,nbavis) values (?,?,?,?,1)";
        try {
            Pst = Cox.prepareStatement(Req);
            Pst.setString(1, P.getNom());
            Pst.setDouble(2, P.getPrix());
            Pst.setString(3, P.getImage());
            Pst.setInt(4, P.getQuantite());
            Pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Produit> Afficher() {
        String Req = "select * from produit";
        List<Produit> list = new ArrayList<>();
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                list.add(new Produit(Rs.getInt("id"), Rs.getString("nom"), (float) Rs.getDouble("prix"), Rs.getString("image"), Rs.getInt("quantite"), (float) Rs.getDouble("avis")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Produit TrouverById(int id) {
        Produit P = null;
        String Req = "select * from produit where id=" + id + "";
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                P = new Produit(Rs.getInt("id"), Rs.getString("nom"), (float) Rs.getDouble("prix"), Rs.getString("image"), Rs.getInt("quantite"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return P;
    }

    @Override
    public void Modifier(Produit P) {
     String requeteUpdate = "UPDATE  `produit` set `nom`='" + P.getNom() + "',`prix`='" + P.getPrix() + "',`quantite`='" + P.getQuantite() + "',`image`='" + P.getImage() + "' where `produit`.`id`='" + P.getId() + "' ";

        try {
            Ste = Cox.createStatement();
             Ste.executeUpdate(requeteUpdate);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    @Override
    public void Supprimer(int id) {
        String Req = " delete from produit where id="+id+" ";
        try {
            Ste = Cox.createStatement();
            Ste.execute(Req);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
