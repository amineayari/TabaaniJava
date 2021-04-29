/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Promotion;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.DoubleStringConverter;
import javax.swing.JOptionPane;
import services.PromotionService;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entite.Voyage;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.DateStringConverter;
import org.controlsfx.control.Notifications;
import services.VoyageService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author House_Info
 */
public class PromoController implements Initializable {

    private TextField des;
    @FXML
    private TextField idPourcentage;
    @FXML
    private TableView<Promotion> tableid;

    @FXML
    private TableColumn<Promotion, Integer> colid;
    @FXML
    private TableColumn<Promotion, Double> colpourc;
    @FXML
    private TableColumn<Promotion, Double> colprix;
    @FXML
    private TableColumn<Promotion, Date> colDateDeb;
    @FXML
    private TableColumn<Promotion, Date> colDatefin;
    @FXML
    private Button ajout;
    @FXML
    private DatePicker idDateFin;
    @FXML
    private DatePicker idDatedeb;
    ObservableList<Promotion> activs = FXCollections.observableArrayList();
    FilteredList<Promotion> filter = new FilteredList<>(activs, e -> true);
    SortedList<Promotion> sort = new SortedList<>(filter);

    @FXML
    private TextField idPrix;
    private TextField idPromo;
    @FXML
    private TextField cherch;
    @FXML
    private TextField idVoyage;
    @FXML
    private ImageView idLogo1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
        idVoyage.setVisible(false);
        // TODO
    }

    public void afficher() {
        colid.setCellValueFactory(new PropertyValueFactory<Promotion, Integer>("id"));
        colpourc.setCellValueFactory(new PropertyValueFactory<Promotion, Double>("pourcentage"));
        colprix.setCellValueFactory(new PropertyValueFactory<Promotion, Double>("prix"));
        colDateDeb.setCellValueFactory(new PropertyValueFactory<Promotion, Date>("date_debut"));
        colDatefin.setCellValueFactory(new PropertyValueFactory<Promotion, Date>("date_fin"));

        PromotionService t = new PromotionService();
        t.afficher().forEach(e -> activs.add(e));
        tableid.setItems(activs);
        tableid.setEditable(true);
        colpourc.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        colprix.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
//        colDateDeb.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));

        ContextMenu contextMenuPub = new ContextMenu();
        MenuItem DeleteItem = new MenuItem("Supprimer Promotion");

        DeleteItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Object item = tableid.getSelectionModel().getSelectedItem();
                Promotion p1 = (Promotion) item;
                PromotionService spub = new PromotionService();
                System.out.println(p1.toString());
                spub.supprimerClic(p1.getId());
                JOptionPane.showMessageDialog(null, "Promotion supprimé");
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("promo.fxml"));
                      ajout.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(VoyageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //API Notification lors de suppression d'un evenement
                Notifications notificationBuilder = Notifications.create()
                        .title("Notification")
                        .text("Promo supprimé !")
                        .hideAfter(javafx.util.Duration.seconds(5))
                        .position(Pos.TOP_CENTER);
                notificationBuilder.show();
            }

        });
        contextMenuPub.getItems().add(DeleteItem);
        tableid.setContextMenu(contextMenuPub);

    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {

        PromotionService ps = new PromotionService();
        if (idPourcentage.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.showAndWait();
        } else if (controleTextFieldNumerique(idPourcentage)); else {
            idPrix.setText("" + ps.calculPrix(Integer.parseInt(idVoyage.getText()), Integer.parseInt(idPourcentage.getText())));
            Promotion p1 = new Promotion(Integer.parseInt(idPourcentage.getText()), Double.parseDouble(idPrix.getText()), Date.valueOf(idDatedeb.getValue()), Date.valueOf(idDateFin.getValue()));
            ps.ajouter(p1, Integer.parseInt(idVoyage.getText()));

            JOptionPane.showMessageDialog(null, "Promotion ajouté");
              Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("promo.fxml"));
                      ajout.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(VoyageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            //API Notification lors de l'ajout d'un evenement
            Notifications notificationBuilder = Notifications.create()
                    .title("Notification")
                    .text("Promo ajouté !")
                    .hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.TOP_CENTER);
            notificationBuilder.show();
        }

    }

    private void modifier(ActionEvent event) {
        PromotionService ps = new PromotionService();
        Promotion p1 = new Promotion(Integer.parseInt(idPourcentage.getText()), Integer.parseInt(idPrix.getText()), Date.valueOf(idDatedeb.getValue()), Date.valueOf(idDateFin.getValue()), Integer.parseInt(idPromo.getText()));
        ps.modifier(p1);
        JOptionPane.showMessageDialog(null, "Promotion modifié");
          Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("promo.fxml"));
                      ajout.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(VoyageController.class.getName()).log(Level.SEVERE, null, ex);
                }
        //API Notification lors de modification d'un evenement
        Notifications notificationBuilder = Notifications.create()
                .title("Notification")
                .text("Promo modifiée !")
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.TOP_CENTER);
        notificationBuilder.show();
    }

    private void supprimer(ActionEvent event) {
        PromotionService ps = new PromotionService();

        Promotion p1 = new Promotion(Integer.parseInt(idPromo.getText()));
        ps.supprimer(Integer.parseInt(idPromo.getText()));
        JOptionPane.showMessageDialog(null, "Promotion supprimé");
          Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("promo.fxml"));
                      ajout.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(VoyageController.class.getName()).log(Level.SEVERE, null, ex);
                }
        //API Notification lors de supression d'un evenement
        Notifications notificationBuilder = Notifications.create()
                .title("Notification")
                .text("Promo supprimée !")
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.TOP_CENTER);
        notificationBuilder.show();

    }

////    @FXML
////    private void handleMouseAction(MouseEvent event) {
//////       Promotion p = tableid.getSelectionModel().getSelectedItem();
//////       idPromo.setText(p.getId().toString());
//////        idPourcentage.setText(p.getPourcentage()+"");
//////         idPrix.setText(p.getPrix()+"");
//////         idDatedeb.setValue(p.getDate_debut().toLocalDate());
//////          idDateFin.setValue(p.getDate_fin().toLocalDate());
////             
////             
////    }
    @FXML
    public void changePourcentageCellEvent(CellEditEvent edittedCell) {

        Promotion p = tableid.getSelectionModel().getSelectedItem();
        p.setPourcentage(Double.parseDouble(edittedCell.getNewValue().toString()));
        PromotionService ps = new PromotionService();
        ps.modifierChamp("pourcentage", p.getPourcentage().toString(), p.getId());
        //API Notification lors de modification d'un pourcentage
        Notifications notificationBuilder = Notifications.create()
                .title("Notification")
                .text("Pourcentage modifié !")
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.TOP_CENTER);
        notificationBuilder.show();
    }

    @FXML
    public void changePrixCellEvent(CellEditEvent edittedCell) {

        Promotion p = tableid.getSelectionModel().getSelectedItem();
        p.setPrix(Double.parseDouble(edittedCell.getNewValue().toString()));
        PromotionService ps = new PromotionService();
        ps.modifierChamp("prix", p.getPrix().toString(), p.getId());
        //API Notification lors de modification d'un evenement
        Notifications notificationBuilder = Notifications.create()
                .title("Notification")
                .text("Prix modifié !")
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.TOP_CENTER);
        notificationBuilder.show();
    }
//      @FXML
//   public void changeDateDebCellEvent(CellEditEvent edittedCell) throws ParseException{
//        
//        Promotion p =tableid.getSelectionModel().getSelectedItem();
//        String s  =((edittedCell.getNewValue().toString()));
//        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
//        Date date = (Date) format.parse(s);
//        
//        p.setDate_debut(new java.sql.Date(date.getTime()));
//        PromotionService ps = new PromotionService();
//       ps.modifierChampDate("date_debut",p.getDate_debut(),p.getId());
//    }

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

    @FXML
    private void recherche() {
        cherch.setOnKeyReleased(e -> {
            cherch.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(Promotion -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(Promotion.getId()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(Promotion.getDate_debut()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(Promotion.getDate_fin()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(Promotion.getPourcentage()).toLowerCase().contains(lowerCaseFilter)) {
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
    private void CreatePDF(ActionEvent event) throws SQLException, IOException, DocumentException {

        try {
            
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("C:/pdf/example.pdf"));
            doc.open();
            doc.add(new Paragraph(" "));
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
            Paragraph p = new Paragraph("Promotions ", font);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));

            PdfPTable tabpdf = new PdfPTable(4);
            tabpdf.setWidthPercentage(100);

            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Pourcentage", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Prix", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Date Debut", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Date Fin", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            Connection conn;
            conn = DataSource.getInstance().getConn();
            String req = "SELECT * FROM promotion";
            PreparedStatement pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cell = new PdfPCell(new Phrase(rs.getString("pourcentage"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("prix"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("date_debut"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("date_fin"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);
            }
            doc.add(tabpdf);
            JOptionPane.showMessageDialog(null, "Fichier PDF crée !");
            doc.close();
            Desktop.getDesktop().open(new File("C:/pdf/example.pdf"));
        } catch (DocumentException e) {
            System.out.println("ERROR PDF");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
        }

    }

//   void modifDate_deb(CellEditEvent edittedCell) {
//        if(edittedCell.getNewValue().toString().isEmpty()){
//            Alert a = new Alert(Alert.AlertType.ERROR);
//            a.setHeaderText(null);
//            a.setContentText("Veuillez remplir tous les champs");
//            a.showAndWait();
//        }
//         else if (controleTextFieldCellEdit(edittedCell));
//        else{  
//         Ev e = tabid.getSelectionModel().getSelectedItem();
//        e.setDate_dev(edittedCell.getNewValue().getValue());
//        JOptionPane.showMessageDialog(null,"Date modifiée avec succès");
//        GererEv g = new GererEv();
//        g.modifier(e);
//         }  
//    }
    void setTextFieldIdVoyage(int id) {
        idVoyage.setText(id + "");
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

//     void modifDate_deb(CellEditEvent edittedCell) {
//        if(edittedCell.getNewValue().toString().isEmpty()){
//            Alert a = new Alert(Alert.AlertType.ERROR);
//            a.setHeaderText(null);
//            a.setContentText("Veuillez remplir tous les champs");
//            a.showAndWait();
//        }
//         else if (controleTextFieldCellEdit(edittedCell));
//        else{  
//         Promotion p = tableid.getSelectionModel().getSelectedItem();
//        p.setDate_debut(edittedCell.getNewValue().getValue());
//        JOptionPane.showMessageDialog(null,"Date modifiée avec succès");
//        PromotionService ps = new PromotionService();
//        ps.modifier(p);
//         }  
//    }
}
