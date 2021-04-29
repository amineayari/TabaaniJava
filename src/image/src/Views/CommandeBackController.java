/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Service.CommandeService;
import Service.ProduitService;
import entite.Commande;
import entite.Produit;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class CommandeBackController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private TableView<Commande> Table_Commande;
    @FXML
    private TableColumn<Commande, Integer> id;
    @FXML
    private TableColumn<?, ?> nbproduit;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> methodepaiement;
    @FXML
    private TableColumn<Commande, String> etat;
    @FXML
    private TableColumn<?, ?> prixtotale;
    @FXML
    private ComboBox<String> EtatComande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          EtatComande.getItems().addAll("Acceptée", "Refusée" , "Livrée") ;            
        try {
            AfficherTableC();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(CommandeBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
       etat.setCellFactory(TextFieldTableCell.forTableColumn());
     //   id.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    private void ttest2(InputMethodEvent event) {
    }

    @FXML
    private void test(KeyEvent event) {
    }

    @FXML
    private void Search(KeyEvent event) {
    }

    @FXML
    private void LettersOnly(KeyEvent event) {
    }

    public ObservableList<Commande> getlist() throws SQLException {
        CommandeService Ps = new CommandeService();
        ObservableList<Commande> listproduit = FXCollections.observableArrayList(Ps.Afficher());
        return listproduit;
    }

    public void AfficherTableC() throws SQLException {
        ObservableList<Commande> list = getlist();

        Table_Commande.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nbproduit.setCellValueFactory(new PropertyValueFactory<>("nbproduit"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        methodepaiement.setCellValueFactory(new PropertyValueFactory<>("methodepaiement"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        prixtotale.setCellValueFactory(new PropertyValueFactory<>("prixtotale"));
      //  etat.setCellFactory(TextFieldTableCell.forTableColumn());

    }

//
//    private void EditStatusRec(CellEditEvent edittedCell) {               
//        Commande p =Table_Commande.getSelectionModel().getSelectedItem();
//     //   p.getEtat(Double.parseDouble(edittedCell.getNewValue().toString()));
//        p.setEtat(edittedCell.getNewValue().toString()) ;
//        CommandeService ps = new CommandeService();
//        ps.Modifier(p) ;
//        
//    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
          CommandeService Ps = new CommandeService();
          Commande C = Ps.TrouverById(Table_Commande.getSelectionModel().getSelectedItem().getId()) ; 
          C.setEtat(EtatComande.getValue());
          Ps.Modifier(C);     
          AfficherTableC();

    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
          CommandeService Ps = new CommandeService();
          Ps.Supprimer(Table_Commande.getSelectionModel().getSelectedItem().getId());
          JOptionPane.showMessageDialog(null, "Are you sure ? :(");
          AfficherTableC();
    }
   
    @FXML
    private void aaaaaaaa(CellEditEvent<Commande, String> event) {
    }

}
