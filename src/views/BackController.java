/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author House_Info
 */
public class BackController implements Initializable {

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
    private Button cmd_btn1;
    @FXML
    private Button forum_btn2;
    @FXML
    private Button recla;
    @FXML
    private Button user_btn;
    @FXML
    private Button admin_btn;
    @FXML
    private Button statistique_btn;
    @FXML
    private AnchorPane app;
    @FXML
    private ImageView user_image;
    @FXML
    private Button profile_btn;
    @FXML
    private Button mdp_btn;
    @FXML
    private Button logout;

    private String pdp_fb;
    private String code;
    @FXML
    private AnchorPane pp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void voyage(ActionEvent event) throws IOException {
        LoadPage("/views/Voyage");
    }

    @FXML
    private void hebergement(ActionEvent event) {
    }

    @FXML
    private void transport(ActionEvent event) {
    }

    @FXML
    private void reservation(ActionEvent event) {
    }

    @FXML
    private void sondage(ActionEvent event) {
    }

    @FXML
    private void produit(ActionEvent event) {
    }

    @FXML
    private void cmd(ActionEvent event) {
    }

    @FXML
    private void forum(ActionEvent event) {
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException {

    }

    @FXML
    private void users(ActionEvent event) throws IOException {

    }

    @FXML
    private void admin(ActionEvent event) throws IOException {

    }

    @FXML
    private void statistiques(ActionEvent event) throws IOException {

    }

    @FXML
    private void GererProfile(ActionEvent event) {

    }

    @FXML
    private void mdp(ActionEvent event) {

    }

    @FXML
    private void logout(ActionEvent event) throws IOException {

    }

    private void LoadPage(String page) {

        try {

            AnchorPane pane = FXMLLoader.load(getClass().getResource(page + ".fxml"));
            pp.getChildren().setAll(pane);

        } catch (IOException ex) {
            System.out.println("Error de transition " + ex);
        }

    }

    @FXML
    private void appel(ActionEvent event) throws IOException {
        LoadPage("/views/promo");
    }

}
