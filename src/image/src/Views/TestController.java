/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Service.ProduitService;
import entite.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class TestController implements Initializable {

    @FXML
    private Label Nomlab;
    @FXML
    private Label Prixlab;
    @FXML
    private Label Quantitelab;
    @FXML
    private ImageView imagelab;

    private Produit Prod;
    @FXML
    private Button Commander;
    @FXML
    private Rating rate;
    @FXML
    private Label avis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Produit P) {
        Prod = P;
        Nomlab.setText(P.getNom());
        Prixlab.setText(String.valueOf(P.getPrix()));
        Quantitelab.setText(String.valueOf(P.getQuantite()));
        avis.setText(String.valueOf(P.getAvis()));
        Image image = new Image(getClass().getResourceAsStream(P.getImage()));
        imagelab.setImage(image);
        rate.setRating(P.getAvis());
        CommandeFrontController.getIdd(Prod.getId());
    }

    @FXML
    private void Passer_Commande(ActionEvent event) throws IOException {
        int x = CommandeFrontController.getIdd(Prod.getId());
        System.out.println(x);
        Parent root = FXMLLoader.load(getClass().getResource("/Views/CommandeFront.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void rating(ActionEvent event) {
//         Alert ops = new Alert(Alert.AlertType.ERROR);
//            ops.setHeaderText(null);
//            ops.setContentText("Please fill in the empty fields");
//            ops.showAndWait();
        int x = CommandeFrontController.getIdd(Prod.getId());
        float Y = 0 ; 
        ProduitService Ps = new ProduitService();
        Produit P = Ps.TrouverById(x);
        P.setAvis((float) rate.getRating());
        // System.out.println((int) rate.getRating());
        System.out.println("jdyyyyyyyyyyyyyyyd" + P.getAvis());
        Y = Ps.Rating(P);
        Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("merci pour voter").graphic(null).hideAfter(javafx.util.Duration.seconds(1))
               .position(Pos.CENTER)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
       avis.setText(String.valueOf(Y));
       rate.setRating(Y);
    }
}
