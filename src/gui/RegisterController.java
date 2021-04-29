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
public class RegisterController implements Initializable {

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
    private JFXRadioButton femme_rb;
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

    private String gender = "";
    private Date birthday;
    @FXML
    private JFXRadioButton female_rb;

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
    private void EnvoyerCode(ActionEvent event) throws IOException {

        if (validateInputs()) {

            Time sentTime = new Time(System.currentTimeMillis());
            birthday = (Date.valueOf(bday_dtp.getValue()));
            if (homme_rb.isSelected()) {
                gender = "Male";
            } else if (female_rb.isSelected()) {
                gender = "Female";
            }
            String toEmail = email_txt.getText();
            String subject = "Confirmation Code";
            Random random = new Random();
            code = code + random.nextInt(8999);
            String body = "Bonjour Mme/Mr " + name_txt.getText() + " vous venez de vous inscrire dans notre Application Tabaani, merci de confirmer votre inscription."
                    + " \n Voici votre code de confirmation: " + code;
            Mailing m = new Mailing();
            m.sendEmail(toEmail, subject, body);
            if (validateInputs()) {
                sendCode();
            }
        }
        Notification notif = new Notification();
        notif.notification("Code de confirmation","Code envoyer avec succée",NotificationType.SUCCESS);
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "test", "/pidevjava/gui/AccueilPage.fxml");
    }

    private void sendCode() {
            int num = Integer.parseInt(num_txt.getText());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidevjava/gui/Confirm.fxml"));
            Parent root = (Parent) loader.load();
            ConfirmController cc = loader.getController();
            cc.getCode(code);
            cc.getInformation(name_txt.getText(), fname_txt.getText(), email_txt.getText(), pwd_txt.getText(), filePath, num, birthday, gender);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
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
        }
            else if (num_txt.getText().length()!= 8) {
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
