/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Transport;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.transportService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.NavigationEntreInterfaces;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BackTransportController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button voyage_btn;
    @FXML
    private Button heb_btn;
    @FXML
    private Button transport_btn;
    @FXML
    private Button prom_btn;
    @FXML
    private Button reserv_btn;
    @FXML
    private Button sondage_btn;
    @FXML
    private Button prod_btn;
    @FXML
    private Button cmd_btn;
    @FXML
    private Button forum_btn;
    @FXML
    private Button reclam_btn;
    @FXML
    private AnchorPane Transports;
    @FXML
    private AnchorPane ap;
    @FXML
    private GridPane grid;
    @FXML
    private TextField ImageField;
    @FXML
    private TextField chercher;
    @FXML
    private TextField DescriptionField;
    @FXML
    private TextField DisponibiliteField;
    @FXML
    private TableView<Transport> Table_transport;
    @FXML
    private TableColumn<?, ?> descriptionTransport;
    @FXML
    private TableColumn<?, ?> disponibiliteTransport;
    @FXML
    private TableColumn<?, ?> priceTransport;
    @FXML
    private TableColumn<?, ?> typeTransport;
    @FXML
    private Button Modifier_Transport;
    @FXML
    private TextField PriceField;
    @FXML
    private TextField TypeField;
    @FXML
    private ImageView user_image;
    @FXML
    private Button profile_btn;
    @FXML
    private Button mdp_btn;
    @FXML
    private Button logout;
    int index = -1;
    FilteredList<Transport> filter = new FilteredList<>(getlistASC(), e -> true);
    SortedList<Transport> sort = new SortedList<>(filter);

    public BackTransportController() throws SQLException {
    }

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
        // TODO
    }

    public void AfficherTable() throws SQLException {
        ObservableList<Transport> list = getlistASC();

        Table_transport.setItems(list);
        //idTransport.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionTransport.setCellValueFactory(new PropertyValueFactory<>("description"));
        disponibiliteTransport.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
        priceTransport.setCellValueFactory(new PropertyValueFactory<>("price"));
        typeTransport.setCellValueFactory(new PropertyValueFactory<>("type"));

    }

    public static ObservableList<Transport> getlistASC() throws SQLException {
        transportService ts = new transportService();
        ObservableList<Transport> listTransport = FXCollections.observableArrayList(ts.readAll());
        return listTransport;
    }

    public void AfficherTableASC() throws SQLException {
        ObservableList<Transport> list = getlistASC();
        Table_transport.setItems(list);
        //idTransport.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionTransport.setCellValueFactory(new PropertyValueFactory<>("description"));
        disponibiliteTransport.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
        priceTransport.setCellValueFactory(new PropertyValueFactory<>("price"));
        typeTransport.setCellValueFactory(new PropertyValueFactory<>("type"));

    }

    @FXML
    private void Hebergement(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "Hebergement", "/gui/BackHotel.fxml");
    }

    @FXML
    private void Transport(ActionEvent event) {
    }


    @FXML
    private void Chercher(ActionEvent event) {
        chercher.setOnKeyReleased(e -> {
            chercher.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(t -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (t.getType().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false;
                    }
                });

            });
            sort.comparatorProperty().bind(Table_transport.comparatorProperty());
            Table_transport.setItems(sort);
        });

    }

    @FXML
    private void getSelected(MouseEvent event) {
        index = Table_transport.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        DescriptionField.setText(String.valueOf(descriptionTransport.getCellData(index)));
        DisponibiliteField.setText(String.valueOf(disponibiliteTransport.getCellData(index)));
        PriceField.setText(String.valueOf(priceTransport.getCellData(index)));
        TypeField.setText(String.valueOf(typeTransport.getCellData(index)));
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
    private void Ajouter_Transport(ActionEvent event) throws SQLException {
         transportService ts = new transportService();

        if (controleTextFieldNonNumerique(DescriptionField) || controleTextFieldNonNumerique(DisponibiliteField) || controleTextFieldNonNumerique(TypeField)); else {
            Transport t = new Transport(DescriptionField.getText(), DisponibiliteField.getText(), ParseDouble(PriceField.getText()), TypeField.getText());

            ts.insert(t);
            System.out.println("Transport ajoutééé");
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Transport");
            tr.setMessage("Created succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
        }
        AfficherTableASC();

        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("BackTransport.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
            AfficherTableASC();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        AfficherTableASC();
    }

    public boolean controleTextFieldNonNumerique(TextField textField) {
        if (!textField.getText().matches(".*[a-zA-Z].*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Veuillez saisir des lettres");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    @FXML
    private void Modifier_Transport(ActionEvent event) throws SQLException {
        
        Transport t = new Transport();
        if (controleTextFieldNonNumerique(DescriptionField) || controleTextFieldNonNumerique(DisponibiliteField) || controleTextFieldNonNumerique(TypeField)); else {
            t.setId(Table_transport.getSelectionModel().getSelectedItem().getId());
            t.setDescription(DescriptionField.getText());
            t.setDisponibilite(DisponibiliteField.getText());
            t.setPrice(ParseDouble(PriceField.getText()));
            t.setType(TypeField.getText());
            transportService ts = new transportService();
            ts.update(t);
            AfficherTableASC();

            try {
                javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("BackTransport.fxml"));
                Scene sceneview = new Scene(tableview);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(sceneview);
                window.show();
                AfficherTableASC();
                TrayNotification tr = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tr.setAnimationType(type);
                tr.setTitle("Transport");
                tr.setMessage("Updated succefully");
                tr.setNotificationType(NotificationType.SUCCESS);
                tr.showAndDismiss(Duration.millis(5000));
                AfficherTableASC();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            AfficherTableASC();
        }
    }

    @FXML
    private void Supprimer_Transport(ActionEvent event) throws SQLException {
        transportService ts = new transportService();
        ts.delete(Table_transport.getSelectionModel().getSelectedItem().getId());
        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("BackTransport.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
            AfficherTableASC();
            TrayNotification tr=new TrayNotification();
            AnimationType type=AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Transport");
            tr.setMessage("Deleted succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
            AfficherTableASC();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        AfficherTableASC();
    }

}
