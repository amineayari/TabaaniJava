/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava.gui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import pidevjava.entities.Admin;
import pidevjava.services.AdminService;
import pidevjava.utils.Mailing;
import pidevjava.utils.NavigationEntreInterfaces;
import pidevjava.utils.Notification;
import static pidevjava.utils.PatternEmail.validate;
import tray.notification.NotificationType;

/**
 * FXML Controller class
 *
 * @author Abirn
 */
public class AjoutAdminController implements Initializable {

    @FXML
    private ImageView import_btn;
    @FXML
    private Button back_btn;
    @FXML
    private ImageView image_vi;
    @FXML
    private JFXTextField name_txt;
    @FXML
    private JFXTextField fname_txt;
    @FXML
    private JFXDatePicker bday_dtp;
    @FXML
    private JFXRadioButton homme_rb;
    @FXML
    private ToggleGroup genre_tg;
    @FXML
    private JFXRadioButton female_rb;
    @FXML
    private JFXTextField email_txt;
    @FXML
    private JFXTextField num_txt;
    @FXML
    private JFXPasswordField pwd_txt;
    @FXML
    private JFXPasswordField confirm_txt;
    @FXML
    private Button confirm_btn1;
    private FileChooser filechooser;
    private File file;
    private String filePath;

    private int code = 1000;
    private Date birthday;

    private String gender = "";
    AdminService adm = new AdminService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void chooseImage(MouseEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        filechooser = new FileChooser();
        filechooser.setTitle("Open Image");
        this.file = filechooser.showOpenDialog(stage);
        String userDirectoryString = System.getProperty("user.home") + "\\Images";
        File userDirectory = new File(userDirectoryString);
        if (!(userDirectory.canRead())) {
            userDirectory = new File("c:/");
        }
        filechooser.setInitialDirectory(userDirectory);
        this.file = filechooser.showOpenDialog(stage);

        try {
            BufferedImage bi = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bi, null);
            image_vi.setImage(image);
            filePath = file.getAbsolutePath();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "test", "/pidevjava/gui/Back.fxml");

    }

    @FXML
    private void EnvoyerCode(ActionEvent event) throws IOException {
        int num = Integer.parseInt(num_txt.getText());
        String genre = "";
        LocalDate bday = bday_dtp.getValue();
        if (validateInputs()) {
            birthday = (Date.valueOf(bday_dtp.getValue()));
            if (homme_rb.isSelected()) {
                gender = "Male";
            } else if (female_rb.isSelected()) {
                gender = "Female";
            }
            Admin ad = new Admin(name_txt.getText(), fname_txt.getText(), gender, num, email_txt.getText(), pwd_txt.getText(), birthday, filePath);
            adm.ajouterAdmin(ad);
            
            try {
                javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Back.fxml"));
                Scene sceneview = new Scene(tableview);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(sceneview);
                window.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
            Notification notif = new Notification();
        notif.notification("Admin","Admin ajouté avec succée",NotificationType.SUCCESS);
            
            String toEmail = email_txt.getText();
            String subject = "Ajout Admin";
            String body = "Bonjour Mme/Mr " + name_txt.getText() + " vous venez d'étre un administrateur de notre Application Tabaani."
                    + " \n Voici votre mot de passe est " + pwd_txt.getText() + " veuilliez le changer le plutot possible ";
            Mailing m = new Mailing();
            m.sendEmail(toEmail, subject, body);

        }
    }

    private boolean validateInputs() {
        if (name_txt.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez saisir votre Prenom");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        } else if ((fname_txt.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre Nom");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if ((email_txt.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre email");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if ((confirm_txt.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez confirmer votre mot de passe");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if ((pwd_txt.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre mot de passe");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if (!(pwd_txt.getText().equals(confirm_txt.getText()))) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez vérifier votre mot de passe");
            alert2.setHeaderText(null);
            alert2.show();
            return false;

        } else if (pwd_txt.getText().length() < 6) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Mot de passe doit dépasser les 6 caractères");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if (!(validate(email_txt.getText()))) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez vérifier votre email");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if (bday_dtp.getValue().isAfter(LocalDate.now())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez vérifier votre date de naissance");
            alert2.setHeaderText(null);
            alert2.show();
            return false;

        } else if (!(homme_rb.isSelected()) && !(female_rb.isSelected())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez entrer votre genre");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if (num_txt.getText().length() != 8) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Le numéro doit étre égale à 8 caractères");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        }
        return true;
    }

}
