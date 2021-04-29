/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entite.Sujet;
import Service.SujetService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author MEGA-PC
 */
public class ForumViewController implements Initializable {
    
    private FileChooser filechooser;
    private File file;
    int index = -1;

    @FXML
    private Button parcourir;
    @FXML
    private Button Modifier_Sujet;
    @FXML
    private TextField ImageField;
    @FXML
    private TextField sujetField;
    @FXML
    private TextField descriptionField;
    @FXML
    private Button Ajouter_Sujet;
    @FXML
    private TableView<Sujet> tableSujet;
    @FXML
    private TableColumn<?, ?> idSujet;
    @FXML
    private TableColumn<?, ?> iduserSujet;
    @FXML
    private TableColumn<?, ?> sujetSujet;
    @FXML
    private TableColumn<?, ?> descriptionSujet;
    @FXML
    private TableColumn<?, ?> imageSujet;
    @FXML
    private TableColumn<?, ?> nbvuesSujet;
    @FXML
    private Button Supprimer_Sujet;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private VBox table;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            AfficherTable();
        } catch (SQLException ex) {
            Logger.getLogger(ForumViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ForumViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Parcourir_Sujet(ActionEvent event) {
        
        Stage primaryStage = new Stage();
        primaryStage.onShowingProperty();
        primaryStage.setTitle("selectionner une image !!!");
        filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files ", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        parcourir.setOnAction(e -> {
            file = filechooser.showOpenDialog(primaryStage);
            if (file != null) {
                //String s = file.getAbsolutePath();
                String F = file.toURI().toString();
                ImageField.setText(F);
           //     image = new javafx.scene.image.Image(file.toURI().toString(), 150, 100, true, true);
           //     img1.setImage(image);

            } else {
                JOptionPane.showMessageDialog(null, "Impossible d'ajouter");
            }
        });
        
    }

    @FXML
    private void getSelected(MouseEvent event) {
        
        index = tableSujet.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        sujetField.setText((String) sujetSujet.getCellData(index));
        descriptionArea.setText( String.valueOf (descriptionSujet.getCellData(index)));
        //nbvuesSujet.setText( String.valueOf (quantiteproduit.getCellData(index)));
        ImageField.setText((String) imageSujet.getCellData(index));
    }

    @FXML
    private void dut(ZoomEvent event) {
    }

    @FXML
    private void Ajouter_Sujet(ActionEvent event) throws IOException {
        Sujet P = new Sujet( 101, sujetField.getText(),descriptionArea.getText(), ImageField.getText(), 0);
        SujetService Ps = new SujetService();
        Ps.Ajouter(P);
        System.out.println("Sujet ajoutééé");
          try { 
            AfficherTable() ;
        } catch (SQLException ex) {
            Logger.getLogger(ForumViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void Modifier_Sujet(ActionEvent event) throws IOException {
     Sujet s = new Sujet() ; 
     s.setId(tableSujet.getSelectionModel().getSelectedItem().getId());
     s.setSujet(sujetField.getText());
     s.setDescription(descriptionArea.getText());
     s.setImage(ImageField.getText());
     SujetService ps = new SujetService() ; 
     ps.Modifier(s);
           try { 
            AfficherTable() ;
        } catch (SQLException ex) {
            Logger.getLogger(ForumViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*  Produit P = new Produit(NomField.getText(), (float) Integer.parseInt(PrixField.getText()), ImageField.getText(), Integer.parseInt(QuantiteField.getText()));
        ProduitService Ps = new ProduitService();
        Ps.Modifier(P);
        System.out.println("Produit modifieee");
          try { 
            AfficherTable() ;
        } catch (SQLException ex) {
            Logger.getLogger(MainwindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
       
    }

    @FXML
    private void Supprimer_Sujet(ActionEvent event) throws SQLException, IOException {
        
         SujetService st= new SujetService();
        st.Supprimer(tableSujet.getSelectionModel().getSelectedItem().getId());
        AfficherTable();
    }
    
    public ObservableList<Sujet> getlist() throws SQLException {
        SujetService Ps = new SujetService();
        ObservableList<Sujet>listSujet = FXCollections.observableArrayList(Ps.Afficher());
        return listSujet;
    }
    
    public void AfficherTable() throws SQLException, IOException {
        ObservableList<Sujet> list = getlist();
  
        tableSujet.setItems(list);
        idSujet.setCellValueFactory(new PropertyValueFactory<>("id"));
         iduserSujet.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        sujetSujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        descriptionSujet.setCellValueFactory(new PropertyValueFactory<>("description"));
        imageSujet.setCellValueFactory(new PropertyValueFactory<>("image"));
         nbvuesSujet.setCellValueFactory(new PropertyValueFactory<>("nbveus"));
         
         
         
          for(int i=0; i<list.size ();i++){
              FXMLLoader fxmlloader= new FXMLLoader();
               fxmlloader.setLocation(getClass().getResource("/Views/SujetItemFXML.fxml"));
                Pane anchorPane = fxmlloader.load();
                
                SujetItemFXMLController sujetItemController = fxmlloader.getController();
                sujetItemController.afficheritem(list.get(i),list.get(i).getId());
                table.getChildren().add(anchorPane);
            }  
                
              //  GridPane.setMargin(anchorPane, new Insets(35));
    }
    
}
