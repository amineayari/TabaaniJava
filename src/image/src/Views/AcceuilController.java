/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AcceuilController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button voyage_btn;
    @FXML
    private Button heb_btn;
    @FXML
    private Button transport_btn;
    @FXML
    private Button prom_btn;
    @FXML
    private Button reserv_btn;
    @FXML
    private Button sondage_btn;
    @FXML
    private Button prod_btn;
    @FXML
    private Button cmd_btn;
    @FXML
    private Button forum_btn;
    @FXML
    private Button reclam_btn;
    @FXML
    private AnchorPane ap;
    @FXML
    private ImageView user_image;
    @FXML
    private Button profile_btn;
    @FXML
    private Button mdp_btn;
    @FXML
    private Button logout;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void voyage(ActionEvent event) {
    }

    @FXML
    private void hebergement(ActionEvent event) {
    }

    @FXML
    private void transport(ActionEvent event) {
    }

    @FXML
    private void promotion(ActionEvent event) {
    }

    @FXML
    private void reservation(ActionEvent event) {
    }

    @FXML
    private void sondage(ActionEvent event) {
    }

    @FXML
    private void produit(ActionEvent event) throws IOException {
//        FXMLLoader fxmlloader = new FXMLLoader();
//                fxmlloader.setLocation(getClass().getResource("/Views/Mainwindow.fxml"));
//               produit = fxmlloader.load();
//              System.out.println("aaaaaaa");
    }

    @FXML
    private void commande(ActionEvent event) {
    }

    @FXML
    private void forum(ActionEvent event) {
    }

    @FXML
    private void reclamation(ActionEvent event) {
    }

    @FXML
    private void GererProfile(ActionEvent event) {
    }

    @FXML
    private void mdp(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }
    
}
