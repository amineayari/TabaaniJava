/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import Views.SujetItemFXMLController;
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Abirn
 */
public class SidebarController implements Initializable {
    
     Node node;

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
    @FXML
    private AnchorPane afficherSujet;
    @FXML
    private Button newSujet;
    @FXML
    private Button afficherstatbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             node = (Node)FXMLLoader.load(getClass().getResource("/Views/SujetIndex.fxml"));
         } catch (IOException ex) {
             Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
         }
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
    private void commande(ActionEvent event) {
    }

    public void setAfficherSujet(AnchorPane afficherSujet) {
        this.afficherSujet = afficherSujet;
    }

    public AnchorPane getAfficherSujet() {
        return afficherSujet;
    }

    @FXML
    private void forum(ActionEvent event) throws IOException, InterruptedException {
        
       // afficherSujet.getChildren().setAll(FXMLLoader("SujetItemFXML.fxml"));
    
            node = (Node)FXMLLoader.load(getClass().getResource("/Views/SujetIndex.fxml"));
            afficherSujet.getChildren().setAll(node);    
            
           //  node = (Node)FXMLLoader.load(getClass().getResource("/Views/AjouterSujet.fxml"));
              //afficherSujet.getChildren().setAll(node); 
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
         node = (Node)FXMLLoader.load(getClass().getResource("/Views/AjouterSujet.fxml"));
         afficherSujet.getChildren().setAll(node);    
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
    
    
    public void afficherformsujet() throws IOException, Throwable {
          
        Node node1;
        node = (Node)FXMLLoader.load(getClass().getResource("/Views/AjouterSujet.fxml"));
        afficherSujet.getChildren().setAll(node);    
         
    }

    @FXML
    private void newSujet(ActionEvent event) throws IOException {
         node = (Node)FXMLLoader.load(getClass().getResource("/Views/AjouterSujet.fxml"));
        afficherSujet.getChildren().setAll(node);    
    }

    @FXML
    private void afficherstatbutton(ActionEvent event) throws IOException {
         node = (Node)FXMLLoader.load(getClass().getResource("/Views/Stattagsfxml.fxml"));
        afficherSujet.getChildren().setAll(node);  
    }
}
