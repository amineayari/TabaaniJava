/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit;

import Service.CommandeService;
import Service.Mailling;
import Service.ProduitService;
import entite.Commande;
import entite.Produit;
import java.io.IOException;
import java.sql.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.DataSource;

/**
 *
 * @author admin
 */
public class GestionProduit extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
////Graphique
        Parent root = FXMLLoader.load(getClass().getResource("/Views/Mainwindow.fxml"));
        //  Parent root=FXMLLoader.load(getClass().getResource("/Views/CommandeBack.fxml"));  
//  Parent root=FXMLLoader.load(getClass().getResource("/Views/Essaii.fxml"));  
//     //     Parent root=FXMLLoader.load(getClass().getResource("/Views/Acceuil.fxml"));  
//
//    // Commande c = new Commande(12, Date, STYLESHEET_MODENA, STYLESHEET_CASPIAN)
       Scene scene = new Scene(root);

       primaryStage.setScene(scene);
        primaryStage.show();
//     Produit P = new Produit(49, "aaa", 16, "aaaaaa", 12, 10) ; 
//     ProduitService Ps = new ProduitService() ; 
//     Ps.Rating(P);


//
//      String str = "1998-03-21" ;
//      long millis=System.currentTimeMillis();  
//       Date d = new Date(millis);
//
//      Commande C = new Commande(1222, d, "aaaaa", 121156) ;
//          CommandeService Cs = new CommandeService() ; 
//          System.out.println(Cs.BigId());
//        Cs.Ajouter2(C,2);
//        //  Cs.Modifier(C);
//        //   Cs.Supprimer(28);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
