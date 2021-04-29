/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava.gui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pidevjava.entities.Admin;
import pidevjava.services.AdminService;

/**
 * FXML Controller class
 *
 * @author Abirn
 */
public class AdminCrudController implements Initializable {

    @FXML
    private JFXTextField email_txt;
    @FXML
    private JFXTextField num_txt;
    @FXML
    private RadioButton male_rb;
    @FXML
    private ToggleGroup gender_tg;
    @FXML
    private RadioButton female_rb;
    @FXML
    private JFXDatePicker bday_dtp;
    @FXML
    private TableColumn<?, ?> adname_col;
    @FXML
    private TableColumn<?, ?> lastname_col;
    @FXML
    private TableColumn<?, ?> bday_col;
    @FXML
    private TableColumn<?, ?> email_col;
    @FXML
    private TableColumn<?, ?> gender_col;
    @FXML
    private TableColumn<?, ?> tel_col;
    @FXML
    private Button ajouter_btn;
    @FXML
    private Button modif_btn;
    @FXML
    private Button Supp_btn;
    @FXML
    private JFXPasswordField mdp_txt;
    @FXML
    private JFXPasswordField confirm_txt;
    @FXML
    private JFXTextField rech_txt;
    @FXML
    private Button rech_btn;
    @FXML
    private JFXTextField adname_txt;
    @FXML
    private JFXTextField lname_txt;
    @FXML
    private TableView<Admin> admin_table;

    AdminService adm = new AdminService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Afficher();
    }    

    @FXML
    private void Select(MouseEvent event) {
        
        Admin selected = admin_table.getSelectionModel().getSelectedItem();
        adname_txt.setText(String.valueOf(selected.getAdminname()));
        lname_txt.setText(String.valueOf(selected.getLastname()));
        email_txt.setText(String.valueOf(selected.getEmail()));
        mdp_txt.setText(String.valueOf(selected.getPassword()));
        num_txt.setText(String.valueOf(selected.getTel()));

        if (String.valueOf(selected.getGender()).equals("Female")) {
            female_rb.setSelected(true);
        } else if (String.valueOf(selected.getGender()).equals("Male")) {
            male_rb.setSelected(true);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(selected.getBirthday().toString(), formatter);
        bday_dtp.setValue(localDate);
    }

    @FXML
    private void AjouterUser(ActionEvent event) {
        
        String genre = null;
        
        if (controleTextFieldNonNumerique(adname_txt) || controleTextFieldNonNumerique(lname_txt) ||controleTextFieldNumerique(num_txt)); 
        else {
        if (male_rb.isSelected()) {
            genre = "Male";
        } else if (female_rb.isSelected()) {
            genre = "Female";
        }
        int tel = Integer.parseInt(num_txt.getText());
        Date bday = (Date.valueOf(bday_dtp.getValue()));

        Admin a = new Admin(adname_txt.getText(), lname_txt.getText(), genre, tel, email_txt.getText(), mdp_txt.getText(), bday);
        AdminService adm = new AdminService();
        adm.ajouterAdmin(a);
        Afficher();
        }
    }

    @FXML
    private void ModifierUser(ActionEvent event) {
        
        String genre = null;
        if (male_rb.isSelected()) {
            genre = "Male";
            female_rb.setSelected(false);
        } else if (female_rb.isSelected()) {
            genre = "Female";
            male_rb.setSelected(false);
        }
        int tel = Integer.parseInt(num_txt.getText());
        Date bday = (Date.valueOf(bday_dtp.getValue()));

        Admin a = new Admin();
        Admin c2 = admin_table.getSelectionModel().getSelectedItem();
        int id_c = c2.getId();
        Admin c3 = new Admin(id_c, adname_txt.getText(), lname_txt.getText(), genre, tel, email_txt.getText(), mdp_txt.getText(), bday);
        adm.updateAdmin(c3);
      
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Are you sure ?");
        alert.showAndWait();
        
        Afficher();
        
    }

    @FXML
    private void SuppUser(ActionEvent event) {
        
        // adm.supprimerAdmin(admin_table.getSelectionModel().getSelectedItems().get(0));
        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AdminCrud.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Afficher();
    }
    
    public void Afficher() {

        ObservableList<Admin> adminList = FXCollections.observableArrayList();
        for (Admin ad : adm.displayAdmins()) {
            adminList.add(ad);
        }
        adname_col.setCellValueFactory(new PropertyValueFactory<>("adminname"));
        lastname_col.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        //image_col.setCellValueFactory(new PropertyValueFactory<>("image"));
        gender_col.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tel_col.setCellValueFactory(new PropertyValueFactory<>("tel"));
        bday_col.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        admin_table.setItems(adminList);

    }

    @FXML
    private void recherche(ActionEvent event) {
        
         ObservableList<Admin> userList = FXCollections.observableArrayList();
        for (Admin u : adm.RechercheAdmins(rech_txt.getText())) {
            userList.add(u);
        }
        adname_col.setCellValueFactory(new PropertyValueFactory<>("adminname"));
        lastname_col.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        bday_col.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        gender_col.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tel_col.setCellValueFactory(new PropertyValueFactory<>("tel"));
        //colImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        admin_table.setItems(userList);
    }
    
       //controle de saisie
    public boolean controleTextFieldNonNumerique(TextField textField) {
        if (!textField.getText().matches(".*[a-zA-Z].*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Veuillez saisir des lettres");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    public boolean controleTextFieldNumerique(TextField textField) {
        if (textField.getText().matches(".*[a-zA-Z].*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Veuillez saisir des chiffres");
            alert.showAndWait();
            return true;
        }
        return false;
    }
}
