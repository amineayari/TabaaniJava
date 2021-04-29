/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Service.ProduitService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import entite.Produit;
import javafx.geometry.Insets;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AfficherProduitsController implements Initializable {

    private final List<Produit> produit = new ArrayList<>();
    ProduitService ps = new ProduitService();
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane produits;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produit.addAll(ps.Afficher());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < produit.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/Views/test.fxml"));
                AnchorPane anchorPane = fxmlloader.load();

                TestController AA = fxmlloader.getController();
               //  AA.setData(produit.get(0));
                AA.setData(produit.get(i));
                if (column == 4) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
              //  GridPane.setMargin(anchorPane,Insets(35));
           
               // GridPane.setMargin(anchorPane, new javafx.geometry.Insets(35));
               // GridPane.setPadding(new Insets(133, 10, 111, 10))
                }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void amine(){
  
    }

}
