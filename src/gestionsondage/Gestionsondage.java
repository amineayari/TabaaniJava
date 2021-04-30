/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionsondage;

import entities.question;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.Mailing;
import services.Questionservice;

/**
 *
 * @author BJI
 */
public class Gestionsondage extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
     // Parent root = FXMLLoader.load(getClass().getResource("/views/Sidebar.fxml"));
           Parent root = FXMLLoader.load(getClass().getResource("/views/stat.fxml"));

        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
      // Mailing.sendMail("yosr.aroui@esprit.tn");


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
