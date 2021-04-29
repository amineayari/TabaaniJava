/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Voyage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.VoyageService;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import entite.Voyage;
import javafx.geometry.Insets;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author House_Info
 */
public class AfficherProduitsController implements Initializable {

    private final List<Voyage> produit = new ArrayList<>();
    VoyageService ps = new VoyageService();
    @FXML
    private AnchorPane produits;
    @FXML
    private GridPane grid;
    @FXML
    private ScrollPane tableid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        produit.addAll(ps.afficher());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < produit.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/views/Test.fxml"));
                AnchorPane anchorPane = fxmlloader.load();

                TestController AA = fxmlloader.getController();
                AA.setData(produit.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
