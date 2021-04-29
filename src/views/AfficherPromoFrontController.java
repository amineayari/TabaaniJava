/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Promotion;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

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
import services.PromotionService;

/**
 * FXML Controller class
 *
 * @author House_Info
 */
public class AfficherPromoFrontController implements Initializable {

    private final List<Promotion> promo = new ArrayList<>();
    PromotionService ps = new PromotionService();
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
        promo.addAll(ps.afficher());
        
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < promo.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/views/Test2.fxml"));
                AnchorPane anchorPane = fxmlloader.load();

                Test2Controller AA = fxmlloader.getController();
                AA.setData(promo.get(i),ps.getImageFromVoyage(promo.get(i).getId()));
                System.out.println(ps.getImageFromVoyage(promo.get(i).getId()));
                if (column == 3) {
                    
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherPromoFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
