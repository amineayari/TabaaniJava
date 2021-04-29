/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Service.ProduitService;
//import com.restfb.types.Photo.Image;
import entite.Produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.ZoomEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static java.lang.Float.parseFloat;
import static java.util.Locale.filter;
import javafx.scene.input.KeyEvent;
import static jdk.nashorn.internal.objects.NativeString.search;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.input.InputMethodEvent;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class MainwindowController implements Initializable {

    @FXML
    private TextField NomField;
    @FXML
    private TextField PrixField;
    @FXML
    private TextField QuantiteField;
    @FXML
    private TextField ImageField;

    private javafx.scene.image.Image image;
    private FileChooser filechooser;
    private File file;
    private ImageView img1;
    int index = -1;
    private ObservableList<Produit> dataList = FXCollections.observableArrayList();
    //  private FilteredList<Produit> filter = new FilteredList<>(dataList, e -> true);
    //  private SortedList<Produit> sort = new SortedList<>(filter);
    @FXML
    private Button parcourir;
    @FXML
    private TableView<Produit> Table_Produit;
    @FXML
    private TableColumn<?, ?> idproduit;
    @FXML
    private TableColumn<?, ?> nomproduit;
    @FXML
    private TableColumn<?, ?> prixproduit;
    @FXML
    private TableColumn<?, ?> quantiteproduit;
    @FXML
    private TableColumn<?, ?> imageproduit;
    @FXML
    private TableColumn<?, ?> avisproduit;
    @FXML
    private Button Modifier_Produit;

    private TextField IdField;
    @FXML
    private TextField search;
    @FXML
    private Pagination pagination;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            AfficherTable();
        } catch (SQLException ex) {
            Logger.getLogger(MainwindowController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // combobox.getItems().removeAll(comboBox.getItems());
      //  Rechercher();

    }

    @FXML
    private void dut(ZoomEvent event) {
    }

    @FXML
    private void Ajouter_Produit(ActionEvent event) {
        Produit P = new Produit(NomField.getText(), parseFloat(PrixField.getText()), ImageField.getText(), Integer.parseInt(QuantiteField.getText()));
        ProduitService Ps = new ProduitService();
        Ps.Ajouter(P);
        System.out.println("Produit ajoutééé");
        try {
            AfficherTable();
        } catch (SQLException ex) {
            Logger.getLogger(MainwindowController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void Rechercher() {
//        combobox.getItems().addAll("Nom", "Prix");
//        ObservableList<Produit> data = null;
//        try {
//            data = getlist();
//        } catch (SQLException ex) {
//            Logger.getLogger(MainwindowController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        FilteredList<Produit> list = new FilteredList(data, p -> true);
//        search.textProperty().addListener((obs, oldValue, newValue) -> {
//            switch (combobox.getValue())//Switch on choiceBox value
//            {
//                case "Nom":
//                    list.setPredicate(p -> p.getNom().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by first name
//                    break;
//////                     case "Prix":
//////                    list.setPredicate(p ->String.valueOf(p.getPrix()).toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by first name
//////                    break;
//
//            }
//        });
//
//        combobox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
//                -> {//reset table and textfield when new choice is selected
//            if (newVal != null) {
//                search.setText("");
//            }
//        });

    }

    @FXML

    private void Modifier_Produit(ActionEvent event) {
        Produit Prod = new Produit();
        Prod.setId(Table_Produit.getSelectionModel().getSelectedItem().getId());
        Prod.setNom(NomField.getText());
        Prod.setPrix(parseFloat(PrixField.getText()));
        Prod.setImage(ImageField.getText());
        Prod.setQuantite(Integer.parseInt(QuantiteField.getText()));
        ProduitService ps = new ProduitService();
        ps.Modifier(Prod);
        try {
            AfficherTable();
        } catch (SQLException ex) {
            Logger.getLogger(MainwindowController.class.getName()).log(Level.SEVERE, null, ex);
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

    public ObservableList<Produit> getlist() throws SQLException {
        ProduitService Ps = new ProduitService();
        ObservableList<Produit> listproduit = FXCollections.observableArrayList(Ps.Afficher());
        return listproduit;
    }

    public void AfficherTable() throws SQLException {
        ObservableList<Produit> list = getlist();

        Table_Produit.setItems(list);
        idproduit.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomproduit.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prixproduit.setCellValueFactory(new PropertyValueFactory<>("prix"));
        imageproduit.setCellValueFactory(new PropertyValueFactory<>("image"));
        quantiteproduit.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        avisproduit.setCellValueFactory(new PropertyValueFactory<>("avis"));

    }

    @FXML
    private void Parcourir_Produit(ActionEvent event) {
     /*   Stage primaryStage = new Stage();
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
        });*/
     
    }

    @FXML
    public void getSelected() {
        index = Table_Produit.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        NomField.setText((String) nomproduit.getCellData(index));
        PrixField.setText(String.valueOf(prixproduit.getCellData(index)));
        QuantiteField.setText(String.valueOf(quantiteproduit.getCellData(index)));
        ImageField.setText((String) imageproduit.getCellData(index));

    }

    @FXML
    private void Supprimer_Produit(ActionEvent event) throws SQLException {
        ProduitService st = new ProduitService();
        st.Supprimer(Table_Produit.getSelectionModel().getSelectedItem().getId());
        JOptionPane.showMessageDialog(null, "Are you sure ? :(");
        AfficherTable();
    }

    @FXML
    private void Search(KeyEvent event) throws SQLException {

//         idproduit.setCellValueFactory(new PropertyValueFactory<>("id"));
//        nomproduit.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        prixproduit.setCellValueFactory(new PropertyValueFactory<>("prix"));
//        imageproduit.setCellValueFactory(new PropertyValueFactory<>("image"));
//        quantiteproduit.setCellValueFactory(new PropertyValueFactory<>("quantite"));
//        avisproduit.setCellValueFactory(new PropertyValueFactory<>("avis"));
//        
//            ObservableList<Produit> list = getlist();
//            FilteredList<Produit> filteredData = new FilteredList<>(list, b -> true);
//		
//		// 2. Set the filter Predicate whenever the filter changes.
//		search.textProperty().addListener((observable, oldValue, newValue) -> {
//			filteredData.setPredicate(p -> {
//				// If filter text is empty, display all persons.
//								
//				if (newValue == null || newValue.isEmpty()) {
//					return true;
//				}				
//				// Compare first name and last name of every person with filter text.
//				String lowerCaseFilter = newValue.toLowerCase();
//				
//				if (p.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
//					return true; // Filter matches first name.
//				} 			
//				     else  
//				    	 return false; // Does not match.
//			});
//		});
//		
//		// 3. Wrap the FilteredList in a SortedList. 
//		SortedList<Produit> sortedData = new SortedList<>(filteredData);
//		
//		// 4. Bind the SortedList comparator to the TableView comparator.
//		// 	  Otherwise, sorting the TableView would have no effect.
//		sortedData.comparatorProperty().bind(Table_Produit.comparatorProperty());
//		
//		// 5. Add sorted (and filtered) data to the table.
//		//Table_Produit.setItems(sortedData);
//                
//                 Table_Produit.setItems(sortedData);
//       
    }

    @FXML
    private void LettersOnly(KeyEvent event) throws SQLException {

//        idproduit.setCellValueFactory(new PropertyValueFactory<>("id"));
//        nomproduit.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        prixproduit.setCellValueFactory(new PropertyValueFactory<>("prix"));
//        imageproduit.setCellValueFactory(new PropertyValueFactory<>("image"));
//        quantiteproduit.setCellValueFactory(new PropertyValueFactory<>("quantite"));
//        avisproduit.setCellValueFactory(new PropertyValueFactory<>("avis"));
//
//        ObservableList<Produit> list = getlist();
//        FilteredList<Produit> filteredData = new FilteredList<>(list, b -> true);
//
//        // 2. Set the filter Predicate whenever the filter changes.
//        search.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(p -> {
//                // If filter text is empty, display all persons.
//
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                // Compare first name and last name of every person with filter text.
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (p.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches first name.
//                } else {
//                    return false; // Does not match.
//                }
//            });
//        });
//
//        // 3. Wrap the FilteredList in a SortedList. 
//        SortedList<Produit> sortedData = new SortedList<>(filteredData);
//
//        // 4. Bind the SortedList comparator to the TableView comparator.
//        // 	  Otherwise, sorting the TableView would have no effect.
//        sortedData.comparatorProperty().bind(Table_Produit.comparatorProperty());
//
//        // 5. Add sorted (and filtered) data to the table.
//        //Table_Produit.setItems(sortedData);
//        Table_Produit.setItems(sortedData);
    }

    @FXML
    private void ttest2(InputMethodEvent event) throws SQLException {
//        ObservableList<Produit> list = getlist();
//        FilteredList<Produit> filteredData = new FilteredList<>(list, b -> true);
//
//        // 2. Set the filter Predicate whenever the filter changes.
//        search.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(p -> {
//                // If filter text is empty, display all persons.
//
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                // Compare first name and last name of every person with filter text.
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (p.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches first name.
//                } else if (String.valueOf(p.getPrix()).indexOf(lowerCaseFilter) != -1) {
//                    return true;
//                } else {
//                    return false; // Does not match.
//                }
//            });
//        });
//
//        // 3. Wrap the FilteredList in a SortedList. 
//        SortedList<Produit> sortedData = new SortedList<>(filteredData);
//
//        // 4. Bind the SortedList comparator to the TableView comparator.
//        // 	  Otherwise, sorting the TableView would have no effect.
//        sortedData.comparatorProperty().bind(Table_Produit.comparatorProperty());
//
//        // 5. Add sorted (and filtered) data to the table.
//        Table_Produit.setItems(sortedData);
    }

    @FXML
    private void test(KeyEvent event) throws SQLException {
        ObservableList<Produit> list = getlist();
        FilteredList<Produit> filteredData = new FilteredList<>(list, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(p -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (p.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(p.getPrix()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Produit> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(Table_Produit.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        Table_Produit.setItems(sortedData);
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/Views/Essaii.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void Front(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/AfficherProduits.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void CommandeBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/CommandeBack.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

}
