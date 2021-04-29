/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Promotion;
import entite.Voyage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.P;

/**
 * FXML Controller class
 *
 * @author House_Info
 */
public class Test2Controller implements Initializable {

    @FXML
    private ImageView imagelab1;
    @FXML
    private Label pourcentageLab;
    @FXML
    private Label Prixlab;
    @FXML
    private Button Commander;
    @FXML
    private Label dateFlab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public void setData(Promotion p ,String img) {
     //   Prod = P;
        pourcentageLab.setText(String.valueOf(p.getPourcentage()));
        Prixlab.setText(String.valueOf(p.getPrix()));
        dateFlab.setText(String.valueOf(p.getDate_fin()));
        Image image = new Image(getClass().getResourceAsStream(img));
        imagelab1.setImage(image);
        System.out.println(img);
       
    }
    @FXML
    private void Passer_Commande(ActionEvent event) {
    }
    
}
