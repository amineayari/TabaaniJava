/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import entite.Hotel;
import entite.Transport;
import java.io.IOException;
import java.sql.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.hotelService;
import service.transportService;


/**
 *
 * @author user
 */
public class Project extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       
        Parent root=FXMLLoader.load(getClass().getResource("/gui/frontTransport.fxml"));        
        Scene scene = new Scene(root);
     
        primaryStage.setScene(scene);
        primaryStage.show();
        /*transportService ts=new transportService();
        transport h=new transport(14,"test2", "non", 52.0, "test2");
        ts.update(h);*/
        /*java.util.Date date=new java.util.Date();
        java.sql.Date d=new java.sql.Date(date.getTime());
        //hotel h=new hotel("test", "test", 0.0, "test", "test",d,d);
        hotel hu=new hotel(11, "test1", "test1", 500.0, "test1", "test1", d, d);
        hotelService hs=new hotelService();
        hs.delete(hu.getId());*/
        
      
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
}

    
    
