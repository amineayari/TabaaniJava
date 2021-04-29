/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Sujet;
import Entite.Tags;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author MEGA-PC
 */
public class TagsService {
    
    private Statement Ste;
    private PreparedStatement Pst;
    private ResultSet Rs;
    private Connection Cox;

    public TagsService() {
        Cox = DataSource.getInstance().getConn();
    }
    
    public Tags TrouverById(int id) {
       Tags t = new Tags();
        String Req = "select * from tags where id=" + id + "";
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                t = new Tags(Rs.getInt("id"),Rs.getString("tag"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    
    
    
    
}
