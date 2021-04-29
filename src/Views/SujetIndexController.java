/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import Entite.Sujet;
import Service.SujetService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author MEGA-PC
 */
public class SujetIndexController implements Initializable {

    @FXML
    private ScrollPane ListSujet;
    @FXML
    private VBox listesujetvbox;
    @FXML
    private Button newSujet;
    @FXML
    private TextField recherchefiled;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AfficherTable();
        } catch (SQLException ex) {
            Logger.getLogger(SujetIndexController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SujetIndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
      public ObservableList<Sujet> getlist() throws SQLException {
        SujetService Ps = new SujetService();
        ObservableList<Sujet>listSujet = FXCollections.observableArrayList(Ps.Afficher());
        return listSujet;
    }
      
       public ObservableList<Sujet> getlistrecherche() throws SQLException {
        SujetService Ps = new SujetService();
        ObservableList<Sujet>listSujet = FXCollections.observableArrayList(Ps.recherche(recherchefiled.getText()));
        return listSujet;
    }
    
     public void AfficherTable() throws SQLException, IOException {
        ObservableList<Sujet> list = getlist();
    
          for(int i=0; i<list.size ();i++){
              FXMLLoader fxmlloader= new FXMLLoader();
               fxmlloader.setLocation(getClass().getResource("/Views/SujetItemFXML.fxml"));
                Pane anchorPane = fxmlloader.load();
                
                SujetItemFXMLController sujetItemController = fxmlloader.getController();
                sujetItemController.afficheritem(list.get(i),list.get(i).getId());
                 sujetItemController.getid(list.get(i).getId());
                listesujetvbox.getChildren().add(anchorPane);
                   
            }           
              //  GridPane.setMargin(anchorPane, new Insets(35));
    }

    @FXML
    private void newSujet(ActionEvent event) throws IOException, Throwable {
        
       
       // SidebarController.afficherformsujet();
        /* FXMLLoader fxmlloader= new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("Sidebar.fxml"));
        Pane anchorPane = fxmlloader.load();
        pidevjava.gui.SidebarController sidebar = fxmlloader.getController();
        sidebar.afficherformsujet();
                      */
    }

    private void recherche(KeyEvent event) throws SQLException, IOException {
             ObservableList<Sujet> list = getlistrecherche();
     listesujetvbox.getChildren().clear();
          for(int i=0; i<list.size ();i++){
              FXMLLoader fxmlloader= new FXMLLoader();
               fxmlloader.setLocation(getClass().getResource("/Views/SujetItemFXML.fxml"));
                Pane anchorPane = fxmlloader.load();
                
                SujetItemFXMLController sujetItemController = fxmlloader.getController();
                sujetItemController.afficheritem(list.get(i),list.get(i).getId());
                 sujetItemController.getid(list.get(i).getId());
                listesujetvbox.getChildren().add(anchorPane);
                   
            }
  
    }
    

    @FXML
    private void recherche2(ActionEvent event) {
    /*    
        ObservableList<Sujet> list = getlistrecherche();
     listesujetvbox.getChildren().clear();
          for(int i=0; i<list.size ();i++){
              FXMLLoader fxmlloader= new FXMLLoader();
               fxmlloader.setLocation(getClass().getResource("/Views/SujetItemFXML.fxml"));
                Pane anchorPane = fxmlloader.load();
                
                SujetItemFXMLController sujetItemController = fxmlloader.getController();
                sujetItemController.afficheritem(list.get(i).getSujet(),list.get(i).getId());
                 sujetItemController.getid(list.get(i).getId());
                listesujetvbox.getChildren().add(anchorPane);
                   
            } */
    }
}