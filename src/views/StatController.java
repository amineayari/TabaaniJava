/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.question;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import services.Questionservice;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author BJI
 */
public class StatController implements Initializable {

    @FXML
    private BarChart<String, String> stat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    ResultSet Rs;
     Connection Cox;
     Cox = DataSource.getInstance().getConn();
     String query = "SELECT question,type FROM question ";
   //  String Req = "select * from produit";

        XYChart.Series<String,String> series = new XYChart.Series<>() ;
        try {
            Rs = Cox.createStatement().executeQuery(query) ;
            while (Rs.next()) {
                series.getData().add(new XYChart.Data<>(Rs.getString(1), Rs.getString(2))) ;
            }
            stat.getData().add(series) ;
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public ObservableList<question> getlist() throws SQLException {
        Questionservice Ps = new Questionservice();
        ObservableList<question> listquestion = FXCollections.observableArrayList(Ps.Afficher());
        return listquestion;
    }
}
