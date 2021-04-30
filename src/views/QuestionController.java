/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.itextpdf.text.BaseColor;
import entities.question;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ZoomEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.Questionservice;
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
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import org.controlsfx.control.Notifications;
import utils.DataSource;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author BJI
 */
public class QuestionController implements Initializable {
   private Connection Cox;

    
    public int index = -1;
    private FileChooser filechooser;
    private File file;
    @FXML
    private TextField QuestionField;
    private TextField TypeField;
    @FXML
    private Button parcourir;
    @FXML
    private TableView<question> Table_Question;
    @FXML
    private TableColumn<question, String> question;
    @FXML
    private TableColumn<question, String> type;
    @FXML
    private TableColumn<question, String> image;
    @FXML
    private Button Modifier_Produit;
    @FXML
    private TextField ImageField;
    @FXML
    private TableColumn<?, ?> idquestion;
    private TextField search;
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private TextField searchh;
    @FXML
    private Button stat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AfficherTable();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
       combobox.getItems().addAll("Rate", "yes/non") ;
    

    }
                

    @FXML
    private void Parcourir_image(ActionEvent event) {
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
    private void getSelected(MouseEvent event) {
        index = Table_Question.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        QuestionField.setText((String) question.getCellData(index));
       // TypeField.setText(String.valueOf(type.getCellData(index)));
        ImageField.setText((String) image.getCellData(index));

    }

    @FXML
    private void dut(ZoomEvent event) {
    }

    @FXML
    private void Ajouter_Question(ActionEvent event) {
        question Q = new question(QuestionField.getText(), ImageField.getText(), combobox.getValue());
        Questionservice Ps = new Questionservice();
        Ps.Ajouter(Q);
        System.out.println("Produit ajoutééé");
        try {
            AfficherTable();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Modifier_Question(ActionEvent event) {
        question Q = new question(Table_Question.getSelectionModel().getSelectedItem().getId(), QuestionField.getText(), ImageField.getText(), combobox.getValue());
        Questionservice Ps = new Questionservice();
        Ps.Modifier(Q);
        System.out.println("Produit ajoutééé");
        try {
            AfficherTable();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Supprimer_Question(ActionEvent event) {
        Questionservice Ps = new Questionservice();
        Ps.Supprimer(Table_Question.getSelectionModel().getSelectedItem().getId());
        try {
            AfficherTable();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<question> getlist() throws SQLException {
        Questionservice Ps = new Questionservice();
        ObservableList<question> listquestion = FXCollections.observableArrayList(Ps.Afficher());
        return listquestion;
    }
    

    public  void AfficherTable() throws SQLException {
        ObservableList<question> list = getlist();
//     int fromIndex =1* rowsPerPage;
//       int toIndex = Math.min(fromIndex + rowsPerPage, list.size());
        Table_Question.setItems(list);
        idquestion.setCellValueFactory(new PropertyValueFactory<>("id"));
        question.setCellValueFactory(new PropertyValueFactory<>("question"));

        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));

    }

    @FXML
    private void jPDF(ActionEvent event) throws SQLException {
//                ObservableList<question> list = getlist();
                        Cox = DataSource.getInstance().getConn();


          try {
       Document doc = new Document();
       PdfWriter.getInstance(doc,new FileOutputStream("C:\\xampp\\yosr.pdf"));
       doc.open();
       
      
       
       doc.add(new Paragraph(" "));
       Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
       Paragraph p = new Paragraph("Your list of Reports", font);
       p.setAlignment(Element.ALIGN_CENTER);
       doc.add(p);
       doc.add(new Paragraph(" "));
       doc.add(new Paragraph(" "));
       
       PdfPTable tabpdf = new PdfPTable(5);
       tabpdf.setWidthPercentage(100);
       
       PdfPCell cell;
       cell = new PdfPCell(new Phrase("Object", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("Area", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("Subject", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       
       
       Statement st = Cox.createStatement();
       ResultSet rs=st.executeQuery("SELECT question,type,image from question");
       while(rs.next()){
           cell = new PdfPCell(new Phrase(rs.getString("question"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(rs.getString("type"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(rs.getString("image"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
         
       }
       
       doc.add(tabpdf);
       JOptionPane.showMessageDialog(null, "Success !!");
       doc.close();
       Desktop.getDesktop().open(new File("C:\\Users\\BJI\\Desktop\\photopidev"));
       
       Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("Your document has been saved as PDF !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
        } catch (DocumentException | HeadlessException | IOException e) {
            System.out.println("ERROR PDF");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void rechercher(KeyEvent event) throws SQLException {
            ObservableList<question> list = getlist();
        FilteredList<question> filteredData = new FilteredList<>(list, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchh.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(p -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (p.getQuestion().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (p.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<question> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        //  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(Table_Question.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        Table_Question.setItems(sortedData);

        
    }
    
//  public void stat(Stage stage) throws Exception {
//     // Parent root = FXMLLoader.load(getClass().getResource("/views/Sidebar.fxml"));
//           Parent root = FXMLLoader.load(getClass().getResource("/views/stat.fxml"));
//
//        
//        Scene scene = new Scene(root);
//        
//        stage.setScene(scene);
//        stage.show();
//
//               
//               
//}

    @FXML
    private void stat(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("/views/stat.fxml"));

        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        
        mainStage.setScene(scene);
        mainStage.show();
    }

}
