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
import pidevjava.entities.Admin;
import pidevjava.services.AdminService;
import pidevjava.utils.Mailing;
import pidevjava.utils.MyCnx;
import pidevjava.utils.NavigationEntreInterfaces;
import pidevjava.utils.Notification;
import tray.notification.NotificationType;

/**
 * FXML Controller class
 *
 * @author Abirn
 */
public class ListAdminsController implements Initializable {

    @FXML
    private TableView<Admin> list_tbl;
    @FXML
    private TableColumn<Admin, String> colImage;
    @FXML
    private TableColumn <Admin, String> nom_col;
    @FXML
    private TableColumn <Admin, String> prenom_col;
    @FXML
    private TableColumn <Admin, String> email_col;
    @FXML
    private TableColumn <Admin, LocalDate> bday_col;
    @FXML
    private TableColumn <Admin, String> genre_col;
    @FXML
    private TableColumn<?, ?> tel_col;
    @FXML
    private ImageView rech_btn;
    @FXML
    private JFXTextField rech_txt;
    @FXML
    private Button delete_btn;
    @FXML
    private Button pdf_btn1;
    @FXML
    private Button add_btn;
    
       AdminService as = new AdminService();

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
        ObservableList<Admin> adList = FXCollections.observableArrayList();
        for (Admin a : as.RechercheAdmins(rech_txt.getText())) {
            adList.add(a);
        }
        nom_col.setCellValueFactory(new PropertyValueFactory<>("adminname"));
        prenom_col.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        bday_col.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        genre_col.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tel_col.setCellValueFactory(new PropertyValueFactory<>("tel"));
     
        list_tbl.setItems(adList);
        
    }

    @FXML
    private void OnSupp(ActionEvent event) throws IOException {
        
        Admin adm = list_tbl.getSelectionModel().getSelectedItems().get(0);
        as.supprimerAdmin(adm.getId());
//        try {
//            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Back.fxml"));
//            Scene sceneview = new Scene(tableview);
//            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            window.setScene(sceneview);
//            window.show();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
        String toEmail = adm.getEmail();
        String subject = "Suppression de compte";
        String body = "Bonjour Mme/mr " + adm.getAdminname() + "\n"
                + "Nous sommes d??sol??s de vous informer que votre compte est supprim?? par decision de notre administration. \n"
                + ""
                + "Cordialement";
        Mailing m = new Mailing();
        m.sendEmail(toEmail, subject, body);
        Afficher();
        
        Notification notif = new Notification();
        notif.notification("Admin","Admin Supprim?? avec succ??e",NotificationType.SUCCESS);
    }

    @FXML
    private void pdf(ActionEvent event) throws SQLException {
     
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("C:/test/Admins.pdf"));
            doc.open();
            doc.add(new Paragraph(" "));
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
            Paragraph p = new Paragraph("Liste des administrateurs ", font);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));

            PdfPTable tabpdf = new PdfPTable(6);
            tabpdf.setWidthPercentage(100);

            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Name", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Family name", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Birthday", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Email", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Gender", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Phone number", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            

            String req="SELECT * FROM admin order by adminname ASC";
            
            PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cell = new PdfPCell(new Phrase(rs.getString("adminname"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("lastname"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("birthday"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);
                
                cell = new PdfPCell(new Phrase(rs.getString("email"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);
                
                cell = new PdfPCell(new Phrase(rs.getString("gender"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);
                
                cell = new PdfPCell(new Phrase(rs.getString("tel"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);
            }
            doc.add(tabpdf);
            JOptionPane.showMessageDialog(null, "PDF file created succefully!");
            doc.close();
            Notification notif = new Notification();
        notif.notification("PDF","PDF t??l??charg?? avec succ??e",NotificationType.SUCCESS);
            Desktop.getDesktop().open(new File("C:/test/Admins.pdf"));
        } catch (DocumentException | HeadlessException | IOException e) {
            System.out.println("PDF ERROR");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
        }
    }

   
    @FXML
    private void OnAjout(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "test", "/pidevjava/gui/AjoutAdmin.fxml");
    }
    
    
    public void Afficher() {
         
        ObservableList<Admin> adList = FXCollections.observableArrayList();
        for (Admin a : as.displayAdmins()) {
            adList.add(a);
        }
         colImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("adminname"));
        prenom_col.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        bday_col.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        genre_col.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tel_col.setCellValueFactory(new PropertyValueFactory<>("tel"));
        
        Callback<TableColumn<Admin, String>, TableCell<Admin, String>> cellImage
                = new Callback<TableColumn<Admin, String>, TableCell<Admin, String>>() {
            @Override
            public TableCell call(final TableColumn<Admin, String> param) {
                final TableCell<Admin, String> cell = new TableCell<Admin, String>() {
                    
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            if (item.isEmpty()) {
                                System.out.println("Empty item");
                            } else {
                                ImageView imagev = new ImageView();

                                InputStream stream;
                                try {
                                    stream = new FileInputStream(item);
                                    Image image = new Image(stream);
                                    imagev.setImage(image);
                                    imagev.setFitHeight(75);
                                    imagev.setFitWidth(75);
                                    setGraphic(imagev);
                                    setText(null);
                                } catch (FileNotFoundException ex) {
                                    System.out.println(ex.getMessage());
                                }
                            }
                        }
                    }
                };
                return cell;
            }
        };
        colImage.setCellFactory(cellImage);
        list_tbl.setItems(adList);

    }
}
