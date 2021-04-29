/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Villa;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ZoomEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.villaService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class VillaFXMLController implements Initializable {

    @FXML
    private TextField NomField;
    @FXML
    private TextField AdresseField;
    
    @FXML
    private TextField PriceField;
    @FXML
    private TextField ImageField;
    @FXML
    private TextField DescriptionField;
    @FXML
    private DatePicker Datedebut;
    @FXML
    private DatePicker Datefin;
    @FXML
    private Button parcourir;
    @FXML
    private TableView<Villa> Table_Villa;
    @FXML
    private TableColumn<?, ?> nomVilla;
    @FXML
    private TableColumn<?, ?> adresseVilla;
    @FXML
    private TableColumn<?, ?> priceVilla;
    @FXML
    private TableColumn<?, ?> imageVilla;
    @FXML
    private TableColumn<?, ?> descriptionVilla;
    @FXML
    private TableColumn<?, ?> datedebutVilla;
    @FXML
    private TableColumn<?, ?> datefinVilla;
    private javafx.scene.image.Image image;
    private FileChooser filechooser;
    private File file;
    private ImageView img1;
    int index = -1;
    @FXML
    private Button Ajouter_villa;
    @FXML
    private Button Supprimer_villa;
    @FXML
    private Button Modifier_villa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AfficherTable();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }    
    double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch (Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        } else {
            return 0;
        }
    }

    @FXML
    private void getSelected(MouseEvent event) {
        index = Table_Villa.getSelectionModel().getSelectedIndex();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (index <= -1) {
            return;
        }
        NomField.setText(String.valueOf(nomVilla.getCellData(index)));
        AdresseField.setText(String.valueOf(adresseVilla.getCellData(index)));
        PriceField.setText(String.valueOf(priceVilla.getCellData(index)));
        ImageField.setText(String.valueOf(imageVilla.getCellData(index)));
        DescriptionField.setText(String.valueOf(descriptionVilla.getCellData(index)));
        Datedebut.setValue(LocalDate.parse(datedebutVilla.getCellData(index).toString(), formatter));
        Datefin.setValue(LocalDate.parse(datefinVilla.getCellData(index).toString(), formatter));
        
    }

    @FXML
    private void dut(ZoomEvent event) {
    }

    @FXML
    private void Ajouter_Villa(ActionEvent event) {
        villaService ts = new villaService();
        Double prix = ParseDouble(PriceField.getText());
        Date date_d = (Date.valueOf(Datedebut.getValue()));
        Date date_f = (Date.valueOf(Datefin.getValue()));
        Villa t = new Villa(NomField.getText(), AdresseField.getText(), prix, ImageField.getText(), DescriptionField.getText(), date_d, date_f);

        ts.insert(t);
        System.out.println("villa ajoutéé");
        try {
            AfficherTable();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Supprimer_Villa(ActionEvent event) throws SQLException {
         villaService hs = new villaService();
        hs.delete(Table_Villa.getSelectionModel().getSelectedItem().getId());
        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("VillaFXML.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        AfficherTable();
    }

    @FXML
    private void Parcourir_Villa(ActionEvent event) {
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
    private void Modifier_Villa(ActionEvent event) {
        try {

            Date date_d = (Date.valueOf(Datedebut.getValue()));
            Date date_f = (Date.valueOf(Datefin.getValue()));
            int id = Table_Villa.getSelectionModel().getSelectedItem().getId();
            Villa newV = new Villa(id, NomField.getText(), AdresseField.getText(), ParseDouble(PriceField.getText()), ImageField.getText(), DescriptionField.getText(), date_d, date_f);
            villaService ts = new villaService();
            ts.update(newV);
            AfficherTable();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public static ObservableList<Villa> getlistVilla() throws SQLException {
        villaService hs = new villaService();
        ObservableList<Villa> listvilla = FXCollections.observableArrayList(hs.readAll());
        return listvilla;
    }

    public void AfficherTable() throws SQLException {
        ObservableList<Villa> list = getlistVilla();
        Table_Villa.setItems(list);
        //idHotel.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomVilla.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresseVilla.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        priceVilla.setCellValueFactory(new PropertyValueFactory<>("price"));
        imageVilla.setCellValueFactory(new PropertyValueFactory<>("image"));
        descriptionVilla.setCellValueFactory(new PropertyValueFactory<>("description"));
        datedebutVilla.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datefinVilla.setCellValueFactory(new PropertyValueFactory<>("datefin"));

    }
    
}
