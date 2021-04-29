/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava.gui;

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
import pidevjava.entities.User;
import pidevjava.services.UserService;
import pidevjava.utils.NavigationEntreInterfaces;

/**
 * FXML Controller class
 *
 * @author Abirn
 */
public class SidebarController implements Initializable {

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
    private ImageView user_image;
    @FXML
    private Button profile_btn;
    @FXML
    private Button mdp_btn;
    @FXML
    private Button logout;

   

    UserService us = new UserService();
    @FXML
    private Button comm_btn;
    @FXML
    private Button forum_btn;
    @FXML
    private Button recla_btn;
    @FXML
    private AnchorPane ap;
   
    private String pdp_fb;
    private String code;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            User log = us.getUserlogged();
            InputStream stream;
            
            try {
                if(log.getImage()!=null){
                stream = new FileInputStream(log.getImage());
                Image image = new Image(stream);
                user_image.setImage(image);
                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
       
        } catch (SQLException ex) {
            Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void LoadPage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            System.out.println();
        }
        bp.setCenter(root);
    }

    public BorderPane getBp() {
        return bp;
    }

    public void setBp(BorderPane bp) {
        this.bp = bp;
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
    private void produit(ActionEvent event) {
    }

    @FXML
    private void GererProfile(ActionEvent event){
        LoadPage("/pidevjava/gui/Profile");

    }

    @FXML
    private void mdp(ActionEvent event) {
      
        LoadPage("/pidevjava/gui/ResetPWD");
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        us.loggedOut();
        NavigationEntreInterfaces nav= new NavigationEntreInterfaces();
        nav.navigate(event, "Login", "/pidevjava/gui/Login.fxml");
        
    }
   
    @FXML
    private void reclamation(ActionEvent event) {
        LoadPage("/pidevjava/gui/ReclamatinCrud");
    }

    @FXML
    private void comd(ActionEvent event) {
    }

    @FXML
    private void forum(ActionEvent event) {
    }

public void getPdp(String passedpdp) {
        this.pdp_fb = passedpdp;
    }
    public void getText(String passedCode){
        this.code=passedCode;
    }


}
