/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.question;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import services.Questionservice;

/**
 * FXML Controller class
 *
 * @author BJI
 */
public class FrontRController implements Initializable {

  private final List<question> quesList = new ArrayList<>();
   Questionservice ps = new Questionservice();
    @FXML
    private VBox gridd;
    /**
     * Initixalizes the controller class.
     */
    @Override
     
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
    quesList.addAll(ps.Afficher());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < quesList.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/views/reponse.fxml"));
                AnchorPane anchorPane = fxmlloader.load();

               ReponseController AA = fxmlloader.getController();
               //  AA.setData(produit.get(0));
                AA.setData(quesList.get(i));
                
                
                
               

                gridd.getChildren().addAll(anchorPane);
              //  GridPane.setMargin(anchorPane,Insets(35));
           
               // GridPane.setMargin(anchorPane, new javafx.geometry.Insets(35));
               // GridPane.setPadding(new Insets(133, 10, 111, 10))
                }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
private static int idd;
    private question Q;

    public static int getIdd(int id) {
        idd = id;
        return idd;
    }
}
