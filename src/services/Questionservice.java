/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

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
public class Questionservice implements Iservice<question> {

    private Statement Ste;
    private PreparedStatement Pst;
    private ResultSet Rs;
    private Connection Cox;

    public Questionservice() {
        Cox = DataSource.getInstance().getConn();
    }

    @Override
    public void Ajouter(question Q) {
        String Req = "insert into question (question,image,type) values (?,?,?)";
        try {
            Pst = Cox.prepareStatement(Req);
            Pst.setString(1, Q.getQuestion());
            Pst.setString(2, Q.getImage());
            Pst.setString(3, Q.getType());
            Pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Questionservice.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<question> Afficher() {
        String Req = "select * from question";
        List<question> list = new ArrayList<>();
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                list.add(new question(Rs.getInt("id"), Rs.getString("question"), Rs.getString("image"), Rs.getString("type")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Questionservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public question TrouverById(int id) {
        question P = null;
        String Req = "select * from produit where id=" + id + "";
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                P = new question(Rs.getInt("id"), Rs.getString("question"), Rs.getString("image"), Rs.getString("type"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Questionservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return P;
    }

    @Override
    public void Modifier(question Q) {
     String requeteUpdate = "UPDATE  `question` set `question`='" + Q.getQuestion() + "',`image`='" + Q.getImage() + "',`type`='" + Q.getType() +  "' where `question`.`id`='" + Q.getId() + "' ";

        try {
            Ste = Cox.createStatement();
             Ste.executeUpdate(requeteUpdate);
        } catch (SQLException ex) {
            Logger.getLogger(Questionservice.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    @Override
    public void Supprimer(int id) {
        String Req = " delete from question where id="+id+" ";
        try {
            Ste = Cox.createStatement();
            Ste.execute(Req);
        } catch (SQLException ex) {
            Logger.getLogger(Questionservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
}
