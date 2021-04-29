/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Voyage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author user
 */
public class VoyageController implements Initializable {

    @FXML
    private ImageView imagelab;
    @FXML
    private Label categorie;
    @FXML
    private Label destination;
    @FXML
    private Label Dated;
    @FXML
    private Label Datef;
    @FXML
    private Label prix;
    @FXML
    private Label description;
    @FXML
    private Label programme;
    @FXML
    private Label id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Voyage v) {
        id.setText(String.valueOf(v.getId()));
        categorie.setText(v.getCategorie());
        destination.setText(String.valueOf(v.getDestination()));
        description.setText(String.valueOf(v.getDescription()));
        programme.setText(String.valueOf(v.getProgramme()));
        Dated.setText(String.valueOf(v.getDatedebut()));
        Datef.setText(String.valueOf(v.getDatefin()));
        prix.setText(String.valueOf(v.getPrix()));
        Image image = new Image(getClass().getResourceAsStream(v.getImage()));
        imagelab.setImage(image);
    }
    
}
