/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXTextArea;
import entite.Voyage;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author House_Info
 */
public class TestController implements Initializable {

    @FXML
    private Label Nomlab;
    @FXML
    private Label Prixlab;
    @FXML
    private Button Commander;
    @FXML
    private ImageView imagelab;
    private Voyage Voy;
    @FXML
    private JFXTextArea iddescrip;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Voyage P) {
         Voy = P;
        Nomlab.setText(P.getDestination());
        Prixlab.setText(String.valueOf(P.getPrix()));
        iddescrip.setText(String.valueOf(P.getDescription()));
//    
        Image image = new Image(getClass().getResourceAsStream(P.getImage()));
        imagelab.setImage(image);
          DetailsController.getIdd(Voy.getId());
    }

    @FXML
    private void Passer_Commande(ActionEvent event) throws IOException {
        int x = DetailsController.getIdd(Voy.getId());
        System.out.println(x);
        Parent root = FXMLLoader.load(getClass().getResource("/views/details.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

}
