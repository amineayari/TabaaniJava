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
import pidevjava.entities.Admin;
import pidevjava.entities.User;
import pidevjava.services.AdminService;
import pidevjava.utils.NavigationEntreInterfaces;

/**
 * FXML Controller class
 *
 * @author Abirn
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
    

    AdminService as = new AdminService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            Admin log = as.getUserlogged();
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
            System.out.println("error de transition " + ex);
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
    private void cmd(ActionEvent event) {
    }

    @FXML
    private void forum(ActionEvent event) {
    }

    @FXML
    private void reclamation(ActionEvent event)throws IOException {
        LoadPage("/pidevjava/gui/ReclamBack");
    }

    @FXML
    private void users(ActionEvent event)throws IOException {
        LoadPage("/pidevjava/gui/ListUser");
    }

    @FXML
    private void admin(ActionEvent event) throws IOException {
        LoadPage("/pidevjava/gui/ListAdmins");
    }

    @FXML
    private void statistiques(ActionEvent event)throws IOException {
        LoadPage("/pidevjava/gui/Statistiques");
    }

    @FXML
    private void GererProfile(ActionEvent event) {
              LoadPage("/pidevjava/gui/ProfilAdmin");
    }

    @FXML
    private void mdp(ActionEvent event) {
          LoadPage("/pidevjava/gui/ResetAdmin");
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        as.loggedOut();
        NavigationEntreInterfaces nav= new NavigationEntreInterfaces();
        nav.navigate(event, "Login", "/pidevjava/gui/Login.fxml");
    }
    
}
