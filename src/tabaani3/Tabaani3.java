/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani3;

import entite.Promotion;
import entite.Voyage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.PromotionService;
import services.VoyageService;

/**
 *
 * @author House_Info
 */
public class Tabaani3 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
//
//        Parent root = FXMLLoader.load(getClass().getResource("/views/Voyage.fxml"));
// Parent root = FXMLLoader.load(getClass().getResource("/views/Sidebar.fxml"));
//Parent root = FXMLLoader.load(getClass().getResource("/views/AfficherProduits.fxml"));
//Parent root = FXMLLoader.load(getClass().getResource("/views/AfficherProduits.fxml"));
//Parent root = FXMLLoader.load(getClass().getResource("/views/AfficherPromoFront.fxml"));
 Parent root = FXMLLoader.load(getClass().getResource("/views/Back.fxml"));

        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
   

    }}

