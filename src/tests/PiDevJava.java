/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava.tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pidevjava.entities.User;
import pidevjava.services.UserService;

/**
 *
 * @author Abirn
 */
public class PiDevJava extends Application {

    public static Stage stage = null;

    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/pidevjava/gui/Statistiques.fxml"));
//       Parent root = FXMLLoader.load(getClass().getResource("/pidevjava/gui/AdminCrud.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/pidevjava/gui/ReclamatinCrud.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/pidevjava/gui/Back.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/pidevjava/gui/AccueilPage.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/pidevjava/gui/Sidebar.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/pidevjava/gui/ResetPWD.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/pidevjava/gui/AjoutAdmin.fxml"));
     
Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

//        java.util.Date date=new java.util.Date();
//        java.sql.Date d=new java.sql.Date(date.getTime());
//
//        UserService us = new UserService();
//        User p = new User("May", "sa", "Female", 12345678, "mayasa.nefzi@esprit.tn", "azerty","");
//        us.ajouterUser(p);
//        System.out.println(p);
                
                
                
        
    }

}
