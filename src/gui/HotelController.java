/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Hotel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.NavigationEntreInterfaces;

/**
 * FXML Controller class
 *
 * @author user
 */
public class HotelController implements Initializable {

    @FXML
    private ImageView imagelab;
    @FXML
    private Label nom;
    @FXML
    private Label adresse;
    @FXML
    private Label Dated;
    @FXML
    private Label Datef;
    @FXML
    private Label prix;
    @FXML
    private Label description;
    @FXML
    private Button b_voyage;
    @FXML
    private Label id;

    public static int id_hotel;
    @FXML
    private Button b_transport;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void setData(Hotel h) {

        id.setText(String.valueOf(h.getId()));
        nom.setText(h.getNom());
        adresse.setText(String.valueOf(h.getAdresse()));
        Dated.setText(String.valueOf(h.getDatedebut()));
        Datef.setText(String.valueOf(h.getDatefin()));
        prix.setText(String.valueOf(h.getPrice()));
        description.setText(String.valueOf(h.getDescription()));
        Image image = new Image(getClass().getResourceAsStream(h.getImage()));
        imagelab.setImage(image);
    }

    @FXML
    private void List_Voyage(ActionEvent event) throws IOException {

        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "test", "/gui/listVoyage.fxml");
        id_hotel = Integer.parseInt(id.getText().toString());

    }

    @FXML
    private void List_Transport(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "test", "/gui/listTransport.fxml");
        id_hotel = Integer.parseInt(id.getText().toString());
    }

}
