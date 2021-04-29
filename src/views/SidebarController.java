/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import utils.NavigationEntreInterfaces;


/**
 * FXML Controller class
 *
 * @author Abirn
 */
public class SidebarController implements Initializable {

    @FXML
    private BorderPane bp;
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
    private AnchorPane ap;
    @FXML
    private ImageView user_image;
    @FXML
    private Button profile_btn;
    @FXML
    private Button mdp_btn;
    @FXML
    private Button logout;
    @FXML
    private Button voyage;
    @FXML
    private AnchorPane pp;
    @FXML
    private Button sondage_btn4;
    @FXML
    private Button sondage_btn1;
    @FXML
    private Button sondage_btn2;
    @FXML
    private Button sondage_btn3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void hebergement(ActionEvent event) {
    }

    @FXML
    private void transport(ActionEvent event) {
    }

    private void promotion(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "test","/views/promo.fxml");
    }

    @FXML
    private void reservation(ActionEvent event) {
    }

    @FXML
    private void sondage(ActionEvent event) {
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
    
    
    private void LoadPage(String page) {
        
        try {
            
            AnchorPane pane=FXMLLoader.load(getClass().getResource(page + ".fxml"));
            pp.getChildren().setAll(pane);
            
            
        } catch (IOException ex) {
            System.out.println("Error de transition " + ex);
        }
       
    }

   

    @FXML
    private void appel(ActionEvent event) throws IOException  {
          LoadPage("/views/AfficherProduits");
    }
    
    public BorderPane getBp() {
        return bp;
    }

    public void setBp(BorderPane bp) {
        this.bp = bp;
    }

    @FXML
    private void app(ActionEvent event) throws IOException {
           LoadPage("/views/AfficherPromoFront");
    }


   
    
    }

