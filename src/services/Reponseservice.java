/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reponse;
import entities.question;
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
 * @author BJI
 */
public class Reponseservice implements Iservice<Reponse> {
     private Statement Ste;
    private PreparedStatement Pst;
    private ResultSet Rs;
    private Connection Cox;

    public Reponseservice() {
        Cox = DataSource.getInstance().getConn();
    }

    @Override   
    public void Ajouter(Reponse R) {
        String Req = "insert into Reponse (question_id,reponse) values (?,?)";
        try {
            Pst = Cox.prepareStatement(Req);
            Pst.setInt(1, R.getQuestion().getId());
            Pst.setString(2, R.getReponse());
          
            Pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Reponseservice.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Reponse> Afficher() {
//        String Req = "select * from reponse";
//        List<Reponse> list = new ArrayList<>();
//        try {
//            Ste = Cox.createStatement();
//            Rs = Ste.executeQuery(Req);
//            while (Rs.next()) {
//                list.add(new Reponse(Rs.getInt("id"), Rs.getInt("question.getId"), Rs.getString("reponse")));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Reponseservice.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return null;
    }
public List <Reponse> afficher2(int idquestion){
    question q =new question();
    String Req2 = "select * from question where id = "+idquestion+"";
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req2);
            while (Rs.next()) {
                    q=new question(Rs.getInt("id"), Rs.getString("question"), Rs.getString("image"), Rs.getString("type"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Questionservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    String Req = "select * from reponse";
        List<Reponse> list = new ArrayList<>();
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                list.add(new Reponse(Rs.getInt("id"), q, Rs.getString("reponse")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reponseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
}
    @Override
    public Reponse TrouverById(int id) {
//        Reponse R = null;
//        String Req = "select * from reponse where id=" + id + "";
//        try {
//            Ste = Cox.createStatement();
//            Rs = Ste.executeQuery(Req);
//            while (Rs.next()) {
//                R = new Reponse(Rs.getInt("id"), Rs.getInt("question"), Rs.getString("reponse"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Reponseservice.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return null;
    }

    @Override
    public void Modifier(Reponse R) {
     String requeteUpdate = "UPDATE  `reponse` set `question_id`='" + R.getQuestion() + "',`reponse`='" + R.getReponse() +  "' where `question`.`id`='" + R.getId() + "' ";

        try {
            Ste = Cox.createStatement();
             Ste.executeUpdate(requeteUpdate);
        } catch (SQLException ex) {
            Logger.getLogger(Reponseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    @Override
    public void Supprimer(int id) {
        String Req = " delete from reponse where id="+id+" ";
        try {
            Ste = Cox.createStatement();
            Ste.execute(Req);
        } catch (SQLException ex) {
            Logger.getLogger(Reponseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
