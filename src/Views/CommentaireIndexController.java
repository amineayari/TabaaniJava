/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entite.Commentaire;
import Entite.Sujet;
import Service.CommentaireService;

import Service.Mailing;

import Service.SujetService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author MEGA-PC
 */
public class CommentaireIndexController implements Initializable {

    private static int idd;
    private Sujet s;
    private SujetService ss = new SujetService();
    @FXML
    private Label sujet;
    @FXML
    private Text description;
    @FXML
    private VBox listcommentaire;
    @FXML
    private TextArea commentaireTextArea;
    @FXML
    private Button ajoutercommentaire;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       s =ss.TrouverById(idd);
        
       sujet.setText(s.getSujet());
       description.setText(s.getDescription());
        try {
            AfficherTable();
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireIndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CommentaireIndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }    
    
    
      public ObservableList<Commentaire> getlist() throws SQLException {
          CommentaireService Ps = new  CommentaireService();
        ObservableList<Commentaire>listCommentaire = FXCollections.observableArrayList(Ps.Afficherbysujet(idd));
        return listCommentaire;
    }
      
    public static int getIdd(int id) {
        idd = id;
        return idd;
    }
    
     public void AfficherTable() throws SQLException, IOException {
        ObservableList<Commentaire> list = getlist();
        System.out.println(list.size());
        listcommentaire.getChildren().clear();
          for(int i=0; i<list.size ();i++){
         
            FXMLLoader fxmlloader= new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("/Views/CommentaireItems.fxml"));
            Pane anchorPane = fxmlloader.load();
            CommentaireItemsController commentaireItemsController = fxmlloader.getController();
            commentaireItemsController.setcommentaire(list.get(i)); 
            commentaireItemsController.getcomment(list.get(i));

            // commentaireItemsController.getid(list.get(i).getId());
            listcommentaire.getChildren().add(anchorPane);    
            }           
              //  GridPane.setMargin(anchorPane, new Insets(35));
    }

    @FXML
    private void ajoutercommentaire(ActionEvent event) throws SQLException, IOException {
       // Commentaire c = new Commentaire();
       
       Commentaire c = new Commentaire( s, 101, commentaireTextArea.getText(), commentaireTextArea.getText(), java.sql.Date.valueOf(java.time.LocalDate.now()));
        CommentaireService Ps = new CommentaireService();
       int test= Ps.Ajouter2(c);
        AfficherTable();
        if(test==1){
        Notifications notificationBuilder = Notifications.create()
                     .title("Commentaire")
                     .text("Commentaire Ajouter")
                     .hideAfter(javafx.util.Duration.seconds(4))
                     .position(Pos.TOP_CENTER);
             notificationBuilder.show();
         //   Mailing m = new Mailing();
         //   m.sendEmail("ahmed.louhaichi@esprit.tn", s.getSujet() , commentaireTextArea.getText());
             System.out.println("ajout");
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("This message was blocked because a bad word was found. If you believe this word should not be blocked, please message support.");
            alert.showAndWait(); 
        }
    }
    
}
