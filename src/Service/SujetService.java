/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Sujet;
import java.util.List;
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
 * @author MEGA-PC
 */
public class SujetService implements IService<Sujet> {

    private Statement Ste;
    private PreparedStatement Pst;
    private ResultSet Rs;
    private Connection Cox;

    public SujetService() {
        Cox = DataSource.getInstance().getConn();
    }

    @Override
    public void Ajouter(Sujet s) {
        String Req = "insert into Sujet (user_id,sujet,description,image,nbvues) values (?,?,?,?,?)";
        try {
            Pst = Cox.prepareStatement(Req);
            Pst.setInt(1, s.getUser_id());
            Pst.setString(2, s.getSujet());
            Pst.setString(3, s.getDescription());
            Pst.setString(4, s.getImage());
            Pst.setInt(5, s.getNbveus());
            Pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Sujet> Afficher() {
        String Req = "select * from Sujet";
        List<Sujet> list = new ArrayList<>();
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                list.add(new Sujet(Rs.getInt("id"), Rs.getInt("user_id"), Rs.getString("sujet"), Rs.getString("description"), Rs.getString("image"), Rs.getInt("nbvues")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Sujet TrouverById(int id) {
        Sujet s = null;
        String Req = "select * from Sujet where id=" + id + "";
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                s = new Sujet(Rs.getInt("id"), Rs.getInt("user_id"), Rs.getString("sujet"), Rs.getString("description"), Rs.getString("image"), Rs.getInt("nbvues"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    
    public Sujet getTags(int id) {
        Sujet s = null;
        String Req = "select * from sujet_tags where sujet_id=" + id + "";
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                s = new Sujet(Rs.getInt("id"), Rs.getInt("user_id"), Rs.getString("sujet"), Rs.getString("description"), Rs.getString("image"), Rs.getInt("nbvues"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    @Override
    public void Modifier(Sujet s) {
        String Req = "update Sujet set sujet='" + s.getSujet() + "',description='" + s.getDescription() + "',image='" + s.getImage() + "',nbvues=" + 5 + " where id=" + s.getId() + " ";
        //String requeteUpdate = "UPDATE  `produit` set `nom`='" + P.getNom() + "',`prix`='" + P.getPrix() + "',`quantite`='" + P.getQuantite() + "',`image`='" + P.getImage() + "' where `produit`.`id`='" + P.getId() + "' ";

        try {
            Ste = Cox.createStatement();
            Ste.executeUpdate(Req);
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Supprimer(int id) {
        String Req = " delete from Sujet where id=" + id + " ";
        try {
            Ste = Cox.createStatement();
            Ste.execute(Req);
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addnbrvue(int id) {

        int nb = 0;
        String Req = "select * from Sujet where id=" + id + "";
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                nb = Rs.getInt("nbvues");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        nb++;
        String Req2 = "update Sujet set nbvues=" + nb + " where id=" + id + " ";
        try {
            Ste = Cox.createStatement();
            Ste.executeUpdate(Req2);
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Sujet> recherche(String s) {
        String Req = "select * from Sujet where sujet like '%" + s + "%'";
        List<Sujet> list = new ArrayList<>();
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                list.add(new Sujet(Rs.getInt("id"), Rs.getInt("user_id"), Rs.getString("sujet"), Rs.getString("description"), Rs.getString("image"), Rs.getInt("nbvues")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
