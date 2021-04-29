/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Commentaire;
import Entite.Reaction;
import Entite.Sujet;
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
public class ReactionService {

      private Statement Ste;
    private PreparedStatement Pst;
    private ResultSet Rs;
    private Connection Cox;
    
     public ReactionService() {
        Cox = DataSource.getInstance().getConn();
    }
    public void Setrecation(Reaction t) {
            
         String Req3 = "insert into reaction (commentaire_id,user_id,reaction) values (?,?,?)";
        try {
            Pst = Cox.prepareStatement(Req3);
            Pst.setInt(1, t.getCommentaire().getId());
            Pst.setInt(2, t.getUser_id());
            Pst.setInt(3, t.getReaction());
            Pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

    public int Affichernblike(int id) {
        String Req1 = "select * from reaction where commentaire_id = "+id+" and reaction = 1";
            //Sujet sujet ;
            Sujet sujet=new Sujet();
            int nb=0;
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req1);
            while (Rs.next()) {
               // list.add(new Commentaire(Rs.getInt("id"), Rs.getInt("user_id"),  Rs.getString("sujet"),Rs.getString("description"), Rs.getString("image"), Rs.getInt("nbvues")));
               nb++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nb;
    }
    
     public int Affichernbdislike(int id) {
         String Req1 = "select * from reaction where commentaire_id = "+id+" and reaction = 0";
            //Sujet sujet ;
            Sujet sujet=new Sujet();
            int nb=0;
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req1);
            while (Rs.next()) {
               // list.add(new Commentaire(Rs.getInt("id"), Rs.getInt("user_id"),  Rs.getString("sujet"),Rs.getString("description"), Rs.getString("image"), Rs.getInt("nbvues")));
               nb++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nb;
    }
    
 
    
}
