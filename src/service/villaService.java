/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Villa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DataSource;

/**
 *
 * @author user
 */
public class villaService implements IService<Villa>{

    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public villaService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Villa v) {

        String req = "insert into villa (nom,adresse,price,image,description,datedebut,datefin) values "
                + "('" + v.getNom() + "','" + v.getAdresse() + "','" + v.getPrice() + "','" + v.getImage() + "','" + v.getDescription() + "','" + v.getDatedebut() + "','" + v.getDatefin() + "')";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Villa v) {
        String requeteUpdate = "UPDATE  `villa` set `nom`='" + v.getNom() + "',`adresse`='" + v.getAdresse() + "',`price`='" + v.getPrice() + "',`image`='" + v.getImage() + "',`description`='" + v.getDescription() + "',`datedebut`='" + v.getDatedebut() + "',`datefin`='" + v.getDatefin() + "' where id=" + v.getId()+ " ";

        try {
            ste = conn.createStatement();
             ste.executeUpdate(requeteUpdate);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String Req = " delete from villa where id="+id+" ";
        try {
            ste = conn.createStatement();
            ste.execute(Req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List readAll() {
        String req = "select * from villa";

        List<Villa> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Villa(rs.getString("nom"), rs.getString("adresse"), rs.getDouble("price"), rs.getString("image"), rs.getString("description"), rs.getDate("datedebut"), rs.getDate("datefin")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public Villa readById(int id) {
        Villa v = new Villa();
        String Req = "select * from villa where id=" + id + "";
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(Req);
            while (rs.next()) {
                v = new Villa(rs.getString("nom"),rs.getString("adresse"),rs.getDouble("price"),rs.getString("image"),  rs.getString("description"), rs.getDate("datedebut"),rs.getDate("datefin"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return v;
    }

}
