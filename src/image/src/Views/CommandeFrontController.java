/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Service.CommandeService;
import Service.Mailling;
import Service.ProduitService;
import entite.Commande;
import entite.Produit;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class CommandeFrontController implements Initializable {
    private static int idd;
    private Produit Prod;
    public static float prix ; 

    public static int getIdd(int id) {
        idd = id;
        return idd;
    }
    ProduitService ps = new ProduitService() ; 
    CommandeService Cs = new CommandeService() ;
    @FXML
    private ImageView image;
    @FXML
    private Button Coommander;
    @FXML
    private TextField nbField;
    @FXML
    private DatePicker dataField;
    @FXML
    private ComboBox<String> methodeField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        methodeField.getItems().addAll("Espece", "Chaque","En ligne") ;                       
       Prod = ps.TrouverById(idd) ;
        // TODO
        Image img = new Image(getClass().getResourceAsStream(Prod.getImage()));
        image.setImage(img);
    }    

    @FXML
    private void Commander(ActionEvent event) throws IOException {
     //  Commande C = new Commande(Integer.parseInt(nbField.getText()), Date.valueOf(dataField.getValue()), methodeField.getValue()) ;
       Commande C1= new Commande(Integer.parseInt(nbField.getText()), Date.valueOf(dataField.getValue()), methodeField.getValue(),Integer.parseInt(nbField.getText())*Prod.getPrix()) ;
     //  Commande C2 = new Commande(Integer.parseInt(nbField.getText()), Date.valueOf(dataField.getValue()), methodeField.getValue(), Integer.parseInt(nbField.getText())*Prod.getPrix()) ;
     //   System.out.println("aaaaaaaaaaaaa" + Prod.getId());
//     Commande C  = new Commande(1, Date.valueOf(dataField.getValue()), "aaa", 22) ;

        Cs.Ajouter2(C1,Prod.getId());
        Prod.setQuantite(Prod.getQuantite() -Integer.parseInt(nbField.getText()) );
     
        ps.Modifier(Prod);
        System.out.println("Commande ajoutééé");
        prix = C1.getPrixtotale() ; 
        Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("ta commande a ete passe").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
          if(Prod.getQuantite() < 10){
        Mailling M = new Mailling() ; 
        M.sendEmail("amine.ayari@esprit.tn",Prod.getNom(), "va epuiser");
        }
         
          if ("En ligne".equals(C1.getMethodepaiement())){
              
        Parent root = FXMLLoader.load(getClass().getResource("/Views/Paiement.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
          }
              
    }
    
    
}
