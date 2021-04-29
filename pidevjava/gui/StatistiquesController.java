/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava.gui;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import pidevjava.services.UserService;
import pidevjava.utils.MyCnx;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Abirn
 */
public class StatistiquesController implements Initializable {

    @FXML
    private JFXComboBox<String> stat_cmb;
    UserService us = new UserService();
    @FXML
//    private PieChart pieChart;
    private PieChart pie_age;
    ObservableList<PieChart.Data> pieageData;
    ObservableList<PieChart.Data> piegenreData;

//    private ObservableList data;
//    Connection conn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        buildData();
//        pieChart.getData().addAll(data);
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Age");
        list.add("Genre");

        stat_cmb.setItems(list);

    }

    public void ageStat() throws SQLException {

        pieageData = FXCollections.observableArrayList();
        String req = "SELECT YEAR(birthday) AS year, COUNT(*) AS nb FROM user GROUP BY YEAR(birthday)";
        Statement st = MyCnx.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            pieageData.add(new PieChart.Data((rs.getString("year")), rs.getInt("nb")));
        }
        pie_age.setData(pieageData);
        pie_age.setTitle("Les utilisateurs par age");
        pie_age.getData().forEach(data -> {
            String percentage = String.format("%.2f%%", (data.getPieValue() / 100));
            Tooltip toolTip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), toolTip);
        });
    }
    
    public void genreStat() throws SQLException {
        piegenreData = FXCollections.observableArrayList();
        String req = "SELECT gender, COUNT(*) AS nb FROM user GROUP BY gender";
        Statement st = MyCnx.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            piegenreData.add(new PieChart.Data((rs.getString("gender")), rs.getInt("nb")));
        }
        pie_age.setData(piegenreData);
        pie_age.setTitle("Les utilisateurs par genre");
        pie_age.getData().forEach(data -> {
            String percentage = String.format("%.2f%%", (data.getPieValue() / 100));
            Tooltip toolTip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), toolTip);
        });
}
    
    @FXML
    private void selon(ActionEvent event)  throws SQLException {
        
        if (stat_cmb.getValue().equals("Age")) {
            ageStat();
        
        } else if(stat_cmb.getValue().equals("Genre")) {
            genreStat();
        }
    }

//    public void buildData() {
//        conn = MyCnx.getInstance().getConnection();
//
//        data = FXCollections.observableArrayList();
//        try {
//            //SQL FOR SELECTING NATIONALITY OF CUSTOMER
//            String SQL = "SELECT COUNT(id), "
//                    + "gender FROM user "
//                    + "GROUP BY gender";
//
//            ResultSet rs = conn.createStatement().executeQuery(SQL);
//            while (rs.next()) {
//                //adding data on piechart data
//                data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
//            }
//        } catch (Exception e) {
//            System.out.println("Error on DB connection");
//            return;
//        }
//    }
}
