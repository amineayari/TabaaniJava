/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Transport;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author user
 */
public class TransportController implements Initializable {

    @FXML
    private ImageView imagelab;
    
    @FXML
    private Label prix;
    @FXML
    private Label description;
    @FXML
    private Label disponibilite;
    @FXML
    private Label type;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Transport t) {
        description.setText(String.valueOf(t.getDescription()));
        disponibilite.setText(String.valueOf(t.getDisponibilite()));
        prix.setText(String.valueOf(t.getPrice()));
        type.setText(String.valueOf(t.getType()));
       //Image image = new Image(getClass().getResourceAsStream(h.getImage()));
       //imagelab.setImage(image);
    }
}
