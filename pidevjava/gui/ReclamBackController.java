/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava.gui;

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
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import pidevjava.entities.Reclamation;
import pidevjava.entities.User;
import pidevjava.services.ReclamationService;
import pidevjava.utils.Mailing;
import pidevjava.utils.MyCnx;
import pidevjava.utils.Notification;
import tray.notification.NotificationType;

/**
 * FXML Controller class
 *
 * @author Abirn
 */
public class ReclamBackController implements Initializable {

    @FXML
    private TableView<Reclamation> list_tbl;
    @FXML
    private TableColumn<Reclamation, String> collSujet;
    @FXML
    private TableColumn<Reclamation, String> Desc_coll;
    @FXML
    private TableColumn<Reclamation, LocalDate> date_coll;
    @FXML
    private ImageView rech_btn;
    @FXML
    private JFXTextField rech_txt;
    @FXML
    private Button delete_btn;
    @FXML
    private Button pdf_btn1;

    ReclamationService rs = new ReclamationService();
    private static User usr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Afficher();
    }

    @FXML
    private void OnSelect(MouseEvent event) {
    }

    @FXML
    private void Rechercher(MouseEvent event) {
        ObservableList<Reclamation> reclList = FXCollections.observableArrayList();
        for (Reclamation r : rs.RechercheReclamations2(rech_txt.getText())) {
            reclList.add(r);
        }
        collSujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        date_coll.setCellValueFactory(new PropertyValueFactory<>("date"));
        Desc_coll.setCellValueFactory(new PropertyValueFactory<>("description"));
        list_tbl.setItems(reclList);
    }

    @FXML
    private void OnSupp(ActionEvent event) throws IOException {

        Reclamation rl = list_tbl.getSelectionModel().getSelectedItems().get(0);
        rs.supprimerReclamation(rl.getId());
        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Back.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Afficher();
        Notification notif = new Notification();
        notif.notification("Reclamation","Réclamation supprimé avec succée",NotificationType.SUCCESS);
    }

    @FXML
    private void pdf(ActionEvent event) throws SQLException {

        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("C:/test/Reclamation.pdf"));
            doc.open();
            doc.add(new Paragraph(" "));
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
            Paragraph p = new Paragraph("Liste des réclamations ", font);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));

            PdfPTable tabpdf = new PdfPTable(3);
            tabpdf.setWidthPercentage(100);

            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Sujet", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Description", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Date", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);


            String req = "SELECT sujet, description, date FROM reclamation order by date ASC";

            PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cell = new PdfPCell(new Phrase(rs.getString("sujet"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("description"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("date"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

               
            }
            doc.add(tabpdf);
            JOptionPane.showMessageDialog(null, "PDF file created succefully!");
            doc.close();
            Notification notif = new Notification();
        notif.notification("PDF","PDF téléchargé avec succée",NotificationType.SUCCESS);
            Desktop.getDesktop().open(new File("C:/test/Reclamation.pdf"));
        } catch (DocumentException | HeadlessException | IOException e) {
            System.out.println("PDF ERROR");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
        }
    }

    public void Afficher() {

        ObservableList<Reclamation> adList = FXCollections.observableArrayList();
        for (Reclamation a : rs.displayReclamations()) {
            adList.add(a);
        }
        collSujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        date_coll.setCellValueFactory(new PropertyValueFactory<>("date"));
        Desc_coll.setCellValueFactory(new PropertyValueFactory<>("description"));
//        userid_coll.setCellValueFactory(new PropertyValueFactory<>("user_id"));
      
        list_tbl.setItems(adList);

    }
}
