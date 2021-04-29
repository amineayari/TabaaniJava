/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abirn
 */
public class MyCnx {

    private Connection cnx;
    private final String URL = "jdbc:mysql://127.0.0.1:3306/pidev";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private static MyCnx instance = null;

    private MyCnx() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(MyCnx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static MyCnx getInstance() {
        if (instance == null) {
            instance = new MyCnx();
        }
        return instance;
    }

    public Connection getConnection() {
        return cnx;
    }
}
