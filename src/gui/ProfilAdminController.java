/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava.gui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import pidevjava.entities.Admin;
import pidevjava.entities.User;
import pidevjava.services.AdminService;
import pidevjava.services.UserService;
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
public class ProfilAdminController implements Initializable {

    @FXML
    private Button modif_btn;
    @FXML
    private ImageView image_vi;
    @FXML
    private ImageView import_btn;
    @FXML
    private Button supp_btn;
    @FXML
    private JFXTextField name_txt;
    @FXML
    private JFXTextField fname_txt;
    @FXML
    private JFXDatePicker bday_dtp;
    @FXML
    private JFXRadioButton homme_rb;
    @FXML
    private ToggleGroup genre_tg1;
    @FXML
    private JFXRadioButton femme_rb;
    @FXML
    private ToggleGroup genre_tg;
    @FXML
    private JFXTextField email_txt;
    @FXML
    private JFXTextField num_txt1;

    private BorderPane bp;
    AdminService as = new AdminService();

    private FileChooser filechooser;
    private File file;
    private String filePath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Admin log;
            log = as.getUserlogged();
            String dom = "";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(log.getBirthday().toString(), formatter);

            name_txt.setText(log.getAdminname());
            fname_txt.setText(log.getLastname());
            email_txt.setText(log.getEmail());
            num_txt1.setText(String.valueOf(log.getTel()));
            bday_dtp.setValue(localDate);
            if ((log.getGender()).equals("Male")) {
                homme_rb.setSelected(true);
            } else if ((log.getGender()).equals("Female")) {
                femme_rb.setSelected(true);
            }
            if (homme_rb.isSelected()) {
                femme_rb.setSelected(false);
            } else if (femme_rb.isSelected()) {
                homme_rb.setSelected(false);
            }
            InputStream stream;
            try {
                stream = new FileInputStream(log.getImage());
                Image image = new Image(stream);
                image_vi.setImage(image);
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void OnModif(ActionEvent event) throws SQLException {

        Admin admin = as.getUserlogged();
        int id_log = admin.getId();
        int num = Integer.parseInt(num_txt1.getText());
        String genre = "";
        LocalDate bday = bday_dtp.getValue();

        if (homme_rb.isSelected()) {
            genre = "Male";
            femme_rb.setSelected(false);
        } else if (femme_rb.isSelected()) {
            genre = "Female";
            homme_rb.setSelected(false);
        }
        //verifier userlogged. getEmail if == email.txt rien faire else envoyer mail au  nouvel et ancien email
        if (!(email_txt.getText().equals(admin.getEmail()))) {
            Mailing m = new Mailing();
            String subject = "Changement d'Email";
            String body1 = "Bonjour Mme/mr " + admin.getAdminname() + "\n"
                    + "Vous avez changé votre adresse email, vous receverez donc un mail dans la nouvelle adresse. \n"
                    + "Cordialement";
            String body2 = "Bonjour Mme/mr " + admin.getAdminname() + "\n"
                    + "Vous avez changé votre adresse email, et ceci est la nouvelle adresse. \n"
                    + "Cordialement";
        }
        if (validateInputs()) {

            Admin a = new Admin(id_log, name_txt.getText(), fname_txt.getText(), genre, num, email_txt.getText(), (Date.valueOf(bday)), filePath);
            AdminService as = new AdminService();
            as.updateAdmin(a);
            as.updateAdminL(a);

           Notification notif = new Notification();
        notif.notification("Profil","Profil Supprimé avec succée",NotificationType.SUCCESS);

        }
    }

    @FXML
    private void chooseImage(MouseEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        filechooser = new FileChooser();
        filechooser.setTitle("Open Image");
        this.file = filechooser.showOpenDialog(stage);
        String userDirectoryString = System.getProperty("admin.home") + "\\Images";
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
    private void supprimerCompte(ActionEvent event) throws SQLException, IOException {

        Admin admin = as.getUserlogged();
        int id_log = admin.getId();
        String toEmail = admin.getEmail();
        String subject = "Désactivation Compte";
        String body = "Bonjour Mmr/Mr " + admin.getAdminname() + "\n"
                + "Vous avez choisi de désactiver votre compte.\n"
                + "Si un jour vous changeriez d'avis nous serons très heureux de vous avoir parmi nous encore une fois \n"
                + ""
                + "A très bientot.";

        Mailing m = new Mailing();
        m.sendEmail(toEmail, subject, body);
        as.supprimerAdmin(admin.getId());
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "Login", "/pidevjava/gui/Login.fxml");
        AdminService as = new AdminService();
        as.loggedOut();
        
        Notification notif = new Notification();
        notif.notification("Profil","Profil modifié avec succée",NotificationType.SUCCESS);
    }

    @FXML
    private void homme(ActionEvent event) {
        homme_rb.setSelected(false);
    }

    @FXML
    private void femme(ActionEvent event) {
        femme_rb.setSelected(false);
    }

    private void LoadPage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            System.out.println("Error de transition " + ex);
        }
        bp.setCenter(root);

    }

    private boolean validateInputs() {
        if (name_txt.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez saisir votre nom");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        } else if (fname_txt.getText().isEmpty()) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre prenom");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if (email_txt.getText().isEmpty()) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre email");
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

        } else if (!(homme_rb.isSelected()) && !(femme_rb.isSelected())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez entrer votre genre");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        }

        return true;
    }

}
