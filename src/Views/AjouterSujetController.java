/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entite.Sujet;
import Service.SujetService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author MEGA-PC
 */

public class AjouterSujetController implements Initializable {

    @FXML
    private Button parcourir;
    @FXML
    private Button Ajouter_Sujet;
    @FXML
    private TextField ImageField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private TextField sujetField;
    @FXML
    private Button liste_sujet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }    

    @FXML
    private void Parcourir_Sujet(ActionEvent event) {
    }

    @FXML
    private void Ajouter_Sujet(ActionEvent event) {
        Sujet P = new Sujet( 101, sujetField.getText(),descriptionArea.getText(), ImageField.getText(), 0);
        SujetService Ps = new SujetService();
        Ps.Ajouter(P);
        System.out.println("Sujet ajoutééé");      
    }

    @FXML
    private void supprimerCompte(ActionEvent event) {
    }
    
}
