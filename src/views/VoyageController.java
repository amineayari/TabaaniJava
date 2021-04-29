/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Promotion;
import entite.Voyage;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import services.PromotionService;
import services.VoyageService;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author House_Info
 */
public class VoyageController implements Initializable {

    @FXML
    private TextField cherch;
    @FXML
    private TextField idDestination;
    @FXML
    private TableView<Voyage> tableid;
    @FXML
    private TableColumn<Voyage, Integer> colId;
    @FXML
    private TableColumn<Voyage, String> colDestination;
    @FXML
    private TableColumn<Voyage, String> colImage;
    @FXML
    private TableColumn<Voyage, String> colDescription;
    @FXML
    private TableColumn<Voyage, Double> colPrix;
    @FXML
    private TableColumn<Voyage, Date> colDateDeb;
    @FXML
    private TableColumn<Voyage, Date> colDatefin;
    @FXML
    private TableColumn<Voyage, String> colCategorie;
    @FXML
    private TableColumn<Voyage, Integer> colPromotion;
    @FXML
    private TableColumn<Voyage, Double> colLng;
    @FXML
    private TableColumn<Voyage, Double> colLat;
    @FXML
    private TableColumn<Voyage, String> colProgramme;
    @FXML
    private TableColumn<Voyage, String> colInclut;
    @FXML
    private TableColumn<Voyage, String> colNoninclut;
    @FXML
    private Button ajout;
    @FXML
    private DatePicker idDateFin;
    @FXML
    private DatePicker idDatedeb;
    @FXML
    private TextField idInclut;
    @FXML
    private AnchorPane idProgramme;
    @FXML
    private TextField idNinclut;
    @FXML
    private TextField idLat;
    @FXML
    private TextField idLong;
    @FXML
    private TextField idPrix;
    @FXML
    private TextField idDescription;
    @FXML
    private TextField idImage;

    private FileChooser filechooser;
    private File file;
    private String filePath;
    ObservableList<Voyage> activs = FXCollections.observableArrayList();
    FilteredList<Voyage> filter = new FilteredList<>(activs, e -> true);
    SortedList<Voyage> sort = new SortedList<>(filter);
    @FXML
    private TextField idCategorie;
    @FXML
    private TableColumn colAddPromo;
    @FXML
    private TextField idProg;
    @FXML
    private Button idParc;
    @FXML
    private Button statbutt;
    @FXML
    private Button actualiser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<Voyage, Integer>("id"));
        colDestination.setCellValueFactory(new PropertyValueFactory<Voyage, String>("Destination"));

        colImage.setCellValueFactory(new PropertyValueFactory<Voyage, String>("Image"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Voyage, String>("Description"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Voyage, Double>("Prix"));

        colDateDeb.setCellValueFactory(new PropertyValueFactory<Voyage, Date>("date_debut"));
        colDatefin.setCellValueFactory(new PropertyValueFactory<Voyage, Date>("date_fin"));
        colCategorie.setCellValueFactory(new PropertyValueFactory<Voyage, String>("Categorie"));
        colPromotion.setCellValueFactory(new PropertyValueFactory<Voyage, Integer>("promotion_id"));

        colLng.setCellValueFactory(new PropertyValueFactory<Voyage, Double>("Lng"));
        colLat.setCellValueFactory(new PropertyValueFactory<Voyage, Double>("Lat"));
        colProgramme.setCellValueFactory(new PropertyValueFactory<Voyage, String>("Programme"));
        colInclut.setCellValueFactory(new PropertyValueFactory<Voyage, String>("Inclut"));
        colNoninclut.setCellValueFactory(new PropertyValueFactory<Voyage, String>("Non Inclut"));

        //****************************************************
        Callback<TableColumn<Voyage, String>, TableCell<Voyage, String>> cellFactory4 = (TableColumn<Voyage, String> param) -> {
            //make the table cell containing button
            final TableCell<Voyage, String> cell = new TableCell<Voyage, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                    //ensure that cell is created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        //create the action buton

                        final Button editbutton = new Button("Ajouter promo ");
                        editbutton.setOnAction((ActionEvent edit) -> {
                            if (getTableView().getItems().get(getIndex()) == null) {
                                System.out.println("**************");
                            } else {

                                Voyage selectedVoyage = getTableView().getItems().get(getIndex());

                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/promo.fxml"));

                                    Parent root = loader.load();
                                    PromoController Promocontroller = loader.getController();

                                    Promocontroller.setTextFieldIdVoyage(selectedVoyage.getId());
                                    Stage stage = new Stage();
                                    stage.setScene(new Scene(root));
                                    stage.show();

//                             
                                } catch (IOException ex) {
                                    Logger.getLogger(VoyageController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }

                        });
                        setGraphic(editbutton);
                        setText(null);

                    }
                }

            };
            return cell;
        };
        colAddPromo.setCellFactory(cellFactory4);

        //*****************************************************
        VoyageService t = new VoyageService();
        t.afficher().forEach(e -> activs.add(e));
        tableid.setItems(activs);
        tableid.setEditable(true);
        ContextMenu contextMenuPub = new ContextMenu();
        MenuItem DeleteItem = new MenuItem("Supprimer Voyage");

        colDestination.setCellFactory(TextFieldTableCell.forTableColumn());
        colDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        colCategorie.setCellFactory(TextFieldTableCell.forTableColumn());
        colProgramme.setCellFactory(TextFieldTableCell.forTableColumn());
        colInclut.setCellFactory(TextFieldTableCell.forTableColumn());
        colNoninclut.setCellFactory(TextFieldTableCell.forTableColumn());
        colPrix.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        colLat.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        colLng.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        DeleteItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Object item = tableid.getSelectionModel().getSelectedItem();
                Voyage p1 = (Voyage) item;
                VoyageService spub = new VoyageService();
                System.out.println(p1.toString());
                spub.supprimer(p1);
                JOptionPane.showMessageDialog(null, "Voyage supprimé");
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("Voyage.fxml"));
                      ajout.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(VoyageController.class.getName()).log(Level.SEVERE, null, ex);
                }
      
                
                //API Notification lors de supp
                Notifications notificationBuilder = Notifications.create()
                        .title("Notification")
                        .text("Voyage supprimé !")
                        .hideAfter(javafx.util.Duration.seconds(5))
                        .position(Pos.TOP_CENTER);
                notificationBuilder.show();
                
            }
        });
        contextMenuPub.getItems().add(DeleteItem);
        tableid.setContextMenu(contextMenuPub);

    }

    @FXML
    private void recherche() {
        cherch.setOnKeyReleased(e -> {
            cherch.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(Voyage -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (Voyage.getDestination().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false;
                    }
                });

            });
            sort.comparatorProperty().bind(tableid.comparatorProperty());
            tableid.setItems(sort);
        });
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        VoyageService vs = new VoyageService();
        if (idDestination.getText().isEmpty() || idPrix.getText().isEmpty() || idImage.getText().isEmpty() || idDescription.getText().isEmpty()
                || idProg.getText().isEmpty() || idInclut.getText().isEmpty() || idNinclut.getText().isEmpty() || idLat.getText().isEmpty() || idLong.getText().isEmpty() || idCategorie.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.showAndWait();
        } //else if(controleTextFieldNumerique(idPourcentage)||controleTextFieldNumerique(idPrix));
        else {

            Voyage v1 = new Voyage(idDestination.getText(), idDescription.getText(), idImage.getText(), Double.parseDouble(idPrix.getText()),
                    Date.valueOf(idDatedeb.getValue()), Date.valueOf(idDateFin.getValue()), idCategorie.getText(), idProg.getText(),
                    idInclut.getText(), idNinclut.getText(), Double.parseDouble(idLat.getText()), Double.parseDouble(idLong.getText()));

//            String destination, String image, String description, double prix, Date date_debut, Date date_fin, 
//                    String categorie, String programme, String inclut, String ninclut, double lat, double lng) 
//       
            vs.ajouter(v1);
            JOptionPane.showMessageDialog(null, "Voyage ajouté");
              Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("Voyage.fxml"));
                      ajout.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(VoyageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            //API Notification lors de l'ajout d'un evenement
            Notifications notificationBuilder = Notifications.create()
                    .title("Notification")
                    .text("Voyage ajouté !")
                    .hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.TOP_CENTER);
            notificationBuilder.show();
          
        }
 
    }

    private void modifier(ActionEvent event) {
        VoyageService vs = new VoyageService();
        Voyage v1 = new Voyage(idDestination.getText(), idImage.getText(), idDescription.getText(), idPrix.getText(), Date.valueOf(idDatedeb.getValue()),
                Date.valueOf(idDateFin.getValue()), idCategorie.getText(), idProg.getText(), idInclut.getText(), idNinclut.getText(),
                Integer.parseInt(idLat.getText()), Integer.parseInt(idLong.getText()));

        vs.modifier(v1);
        JOptionPane.showMessageDialog(null, "Promotion modifié");
          Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("Voyage.fxml"));
                      ajout.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(VoyageController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    //id,destination,image,description,prix,date_debut,date_fin,categorie,programme,inclut,ninclut,lat,lng
//Promotion p1 = new Promotion(Integer.parseInt(idPourcentage.getText()), Integer.parseInt(idPrix.getText()), Date.valueOf(idDatedeb.getValue()), Date.valueOf(idDateFin.getValue()));
//text_nom.getText(),type.getValue(),emplacement.getText(),Date.valueOf(date_deb.getValue()),Date.valueOf(date_fin.getValue()),Time.valueOf(temps_deb.getValue()),Time.valueOf(temps_fin.getValue()),Integer.parseInt(age_min.getText()),Integer.parseInt(age_min.getText()));

//    @FXML
//    private void Parcourir_Voyage(ActionEvent event) {
//        Stage primaryStage = new Stage();
//        primaryStage.onShowingProperty();
//        primaryStage.setTitle("Selectionner une image !!!");
//        FileChooser filechooser = new FileChooser();
//        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files ", ".png", ".jpg", "*.gif"),
//                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
//        parcourir.setOnAction(e -> {
//            file = filechooser.showOpenDialog(primaryStage);
//            if (file != null) {
//                //String s = file.getAbsolutePath();
//                String F = file.toURI().toString();
//                ImageField.setText(F);
//           //     image = new javafx.scene.image.Image(file.toURI().toString(), 150, 100, true, true);
//           //     img1.setImage(image);
//
//            } else {
//                JOptionPane.showMessageDialog(null, "Impossible d'ajouter");
//            }
//        });
//    }
// 
//    public void changeDestinationCellEvent(CellEditEvent edittedCell) {
//
//        Voyage v = tableid.getSelectionModel().getSelectedItem();
//        v.setDestination(edittedCell.getNewValue().toString());
//        VoyageService vs = new VoyageService();
//        vs.modifierChamp("destination", v.getDestination(), v.getId());
//    }
    @FXML
    private void handleMouseAction(MouseEvent event) {
    }

    @FXML
    private void loadIMG(ActionEvent event) {

        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {

            idImage.setText(selectedFile.getAbsolutePath());
            Image image = new Image(selectedFile.toURI().toString(), 50, 50, true, true);

            System.out.println(selectedFile.getName());
        } else {
            System.out.println("erruer files");
        }
    }

    public boolean controleTextFieldCellEdit(CellEditEvent edittedCell) {
        if (!edittedCell.getNewValue().toString().matches(".*[a-zA-Z].*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Veuillez saisir des lettres");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    @FXML
    public void modifDest(CellEditEvent edittedCell) {
        Voyage v = tableid.getSelectionModel().getSelectedItem();
        v.setDestination(edittedCell.getNewValue().toString());
        JOptionPane.showMessageDialog(null, "Destination modifiée avec succès");
        VoyageService vs = new VoyageService();
        vs.modifier(v);
        //API Notification lors de modification 
        Notifications notificationBuilder = Notifications.create()
                .title("Notification")
                .text("Destination modifiée !")
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.TOP_CENTER);
        notificationBuilder.show();
    }

    @FXML
    public void modifDescription(CellEditEvent edittedCell) {
        Voyage v = tableid.getSelectionModel().getSelectedItem();
        v.setDescription(edittedCell.getNewValue().toString());
        JOptionPane.showMessageDialog(null, "Description modifiée avec succès");
        VoyageService vs = new VoyageService();
        vs.modifier(v);
        //API Notification lors de modification d'un evenement
        Notifications notificationBuilder = Notifications.create()
                .title("Notification")
                .text("Description modifiée !")
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.TOP_CENTER);
        notificationBuilder.show();
    }

    @FXML
    public void modifCat(CellEditEvent edittedCell) {
        Voyage v = tableid.getSelectionModel().getSelectedItem();
        v.setCategorie(edittedCell.getNewValue().toString());
        JOptionPane.showMessageDialog(null, "Catégorie modifiée avec succès");
        VoyageService vs = new VoyageService();
        vs.modifier(v);
        //API Notification lors de modification d'un evenement
        Notifications notificationBuilder = Notifications.create()
                .title("Notification")
                .text("Catalogue modifiée !")
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.TOP_CENTER);
        notificationBuilder.show();
    }

    @FXML
    public void modifProg(CellEditEvent edittedCell) {
        Voyage v = tableid.getSelectionModel().getSelectedItem();
        v.setProgramme(edittedCell.getNewValue().toString());
        JOptionPane.showMessageDialog(null, "Programme modifiée avec succès");
        VoyageService vs = new VoyageService();
        vs.modifier(v);
        //API Notification lors de modification d'un evenement
        Notifications notificationBuilder = Notifications.create()
                .title("Notification")
                .text("Programme modifié !")
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.TOP_CENTER);
        notificationBuilder.show();
    }

    @FXML
    public void modifIn(CellEditEvent edittedCell) {
        Voyage v = tableid.getSelectionModel().getSelectedItem();
        v.setInclut(edittedCell.getNewValue().toString());
        JOptionPane.showMessageDialog(null, "Inclut modifiée avec succès");
        VoyageService vs = new VoyageService();
        vs.modifier(v);
        //API Notification lors de modification d'un evenement
        Notifications notificationBuilder = Notifications.create()
                .title("Notification")
                .text("Inclut modifié !")
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.TOP_CENTER);
        notificationBuilder.show();
    }

    @FXML
    public void modifNIn(CellEditEvent edittedCell) {
        Voyage v = tableid.getSelectionModel().getSelectedItem();
        v.setNinclut(edittedCell.getNewValue().toString());
        JOptionPane.showMessageDialog(null, "Non Inclut modifiée avec succès");
        VoyageService vs = new VoyageService();
        vs.modifier(v);
        //API Notification lors de modification d'un evenement
        Notifications notificationBuilder = Notifications.create()
                .title("Notification")
                .text("Non inclut modifié !")
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.TOP_CENTER);
        notificationBuilder.show();
    }

    @FXML
    public void changePrixCellEvent(CellEditEvent edittedCell) {

        Voyage p = tableid.getSelectionModel().getSelectedItem();
        p.setPrix(Double.parseDouble(edittedCell.getNewValue().toString()));
        VoyageService ps = new VoyageService();
        ps.modifierChamp("prix", p.getPrix().toString(), p.getId());
        JOptionPane.showMessageDialog(null, "Prix modifiée avec succès");
        //API Notification lors de modification d'un evenement
        Notifications notificationBuilder = Notifications.create()
                .title("Notification")
                .text("Prix modifié !")
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.TOP_CENTER);
        notificationBuilder.show();
    }

    @FXML
    public void changeLatCellEvent(CellEditEvent edittedCell) {

        Voyage p = tableid.getSelectionModel().getSelectedItem();
        p.setLat(Double.parseDouble(edittedCell.getNewValue().toString()));
        VoyageService ps = new VoyageService();
        ps.modifierChamp("lat", p.getLat().toString(), p.getId());
        JOptionPane.showMessageDialog(null, "Lat modifiée avec succès");
        //API Notification lors de modification d'un evenement
        Notifications notificationBuilder = Notifications.create()
                .title("Notification")
                .text("Latitude modifiée !")
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.TOP_CENTER);
        notificationBuilder.show();
    }

    @FXML
    public void changeLngCellEvent(CellEditEvent edittedCell) {

        Voyage p = tableid.getSelectionModel().getSelectedItem();
        p.setLng(Double.parseDouble(edittedCell.getNewValue().toString()));
        VoyageService ps = new VoyageService();
        ps.modifierChamp("lng", p.getLng().toString(), p.getId());
        JOptionPane.showMessageDialog(null, "Lng modifiée avec succès");
        //API Notification lors de modification d'un evenement
        Notifications notificationBuilder = Notifications.create()
                .title("Notification")
                .text("Longitude modifiée !")
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.TOP_CENTER);
        notificationBuilder.show();
    }

    @FXML
    void afficherstat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistic.fxml"));
        Parent root = loader.load();
        ajout.getScene().setRoot(root);
    }

}
