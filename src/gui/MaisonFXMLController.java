/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Maison;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.ZoomEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.maisonService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MaisonFXMLController implements Initializable {

    @FXML
    private TextField NomField;
    @FXML
    private TextField AdresseField;
    @FXML
    private TextField PriceField;
    @FXML
    private TextField DescriptionField;
    @FXML
    private TextField ImageField;

    @FXML
    private DatePicker Datedebut;
    @FXML
    private DatePicker Datefin;

    private javafx.scene.image.Image image;
    private FileChooser filechooser;
    private File file;
    private ImageView img1;
    int index = -1;
    @FXML
    private Button parcourir;
    @FXML
    private TableView<Maison> Table_maison;
    @FXML
    private TableColumn<?, ?> idMaison;
    @FXML
    private TableColumn<?, ?> nomMaison;
    @FXML
    private TableColumn<?, ?> adresseMaison;
    @FXML
    private TableColumn<?, ?> priceMaison;
    @FXML
    private TableColumn<?, ?> imageMaison;
    @FXML
    private TableColumn<?, ?> descriptionMaison;
    @FXML
    private TableColumn<?, ?> datedebutMaison;
    @FXML
    private TableColumn<?, ?> datefinMaison;
    @FXML
    private Button Modifier_maison;
    @FXML
    private Button Ajouter_maison;
    @FXML
    private Button Supprimer_maison;
    private TextField IdField;

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

    @FXML
    private void dut(ZoomEvent event) {
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
    private void Ajouter_Maison(ActionEvent event) {
        maisonService ms = new maisonService();
        Double prix = ParseDouble(PriceField.getText());
        Date date_d = (Date.valueOf(Datedebut.getValue()));
        Date date_f = (Date.valueOf(Datefin.getValue()));
        Maison m = new Maison(NomField.getText(), AdresseField.getText(), prix, ImageField.getText(), DescriptionField.getText(), date_d, date_f);

        ms.insert(m);
        System.out.println("maison ajoutéé");
        try {
            AfficherTable();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void Modifier_Maison(ActionEvent event) {
        Maison Prod = new Maison();
        Prod.setId(Table_maison.getSelectionModel().getSelectedItem().getId());
        Prod.setNom(NomField.getText());
        Prod.setAdresse(AdresseField.getText());
        Prod.setPrice(ParseDouble(PriceField.getText()));
        Prod.setImage(ImageField.getText());
        Prod.setDescription(DescriptionField.getText());
        Date date_d = (Date.valueOf(Datedebut.getValue()));
        Prod.setDatedebut(date_d);
        Date date_f = (Date.valueOf(Datefin.getValue()));
        Prod.setDatefin(date_f);
        maisonService ps = new maisonService();
        ps.update(Prod);
        try {
            AfficherTable();
        } catch (SQLException ex) {
            Logger.getLogger(MaisonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Parcourir_Maison(ActionEvent event) {
        Stage primaryStage = new Stage();
        primaryStage.onShowingProperty();
        primaryStage.setTitle("selectionner une image !!!");
        filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files ", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        parcourir.setOnAction(e -> {
            file = filechooser.showOpenDialog(primaryStage);
            if (file != null) {
                String s = file.getAbsolutePath();
                String F = file.toURI().toString();
                ImageField.setText(F);
                image = new javafx.scene.image.Image(file.toURI().toString(), 150, 100, true, true);
                img1.setImage(image);

            } else {
                JOptionPane.showMessageDialog(null, "Impossible d'ajouter");
            }
        });

    }

    @FXML
    public void getSelected() {
        index = Table_maison.getSelectionModel().getSelectedIndex();
        Maison selected = Table_maison.getSelectionModel().getSelectedItem();

        NomField.setText(String.valueOf(nomMaison.getCellData(index)));
        AdresseField.setText(String.valueOf(adresseMaison.getCellData(index)));
        PriceField.setText(String.valueOf(priceMaison.getCellData(index)));
        ImageField.setText(String.valueOf(imageMaison.getCellData(index)));
        DescriptionField.setText(String.valueOf(descriptionMaison.getCellData(index)));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date_d = LocalDate.parse(selected.getDatedebut().toString(), formatter);
        Datedebut.setValue(date_d);
        LocalDate date_f = LocalDate.parse(selected.getDatefin().toString(), formatter);
        Datefin.setValue(date_f);
    }

    @FXML
    private void Supprimer_Maison(ActionEvent event) throws SQLException {
        maisonService ms = new maisonService();
        ms.delete(Table_maison.getSelectionModel().getSelectedItem().getId());
        AfficherTable();
    }

    public static ObservableList<Maison> getlistMaison() throws SQLException {
        maisonService ms = new maisonService();
        ObservableList<Maison> listmaison = FXCollections.observableArrayList(ms.readAll());
        return listmaison;
    }

    public void AfficherTable() throws SQLException {
        ObservableList<Maison> list = getlistMaison();
        Table_maison.setItems(list);
        nomMaison.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresseMaison.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        priceMaison.setCellValueFactory(new PropertyValueFactory<>("price"));
        descriptionMaison.setCellValueFactory(new PropertyValueFactory<>("description"));
        datedebutMaison.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datefinMaison.setCellValueFactory(new PropertyValueFactory<>("datefin"));

    }

}
