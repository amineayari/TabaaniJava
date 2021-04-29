/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DataSource {
    
     private static String url ="jdbc:mysql://localhost:3306/tabaanif.v";
   
    private static Connection conn;
 
    static DataSource instance;
    
    public DataSource() {
        
        try {
            conn = DriverManager.getConnection(url, "root","");
            System.out.println(" connecté !!!!");

        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DataSource getInstance(){
        if(instance == null)
            instance = new DataSource();
        
        return instance;
    }

    public static Connection getConn() {
        return conn;
    }
}
