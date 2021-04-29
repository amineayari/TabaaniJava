/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava.gui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import pidevjava.entities.User;
import pidevjava.services.UserService;
import pidevjava.utils.Mailing;
import pidevjava.utils.NavigationEntreInterfaces;
import static pidevjava.utils.PatternEmail.validate;

/**
 * FXML Controller class
 *
 * @author Abirn
 */
public class RecupMDPController implements Initializable {

    @FXML
    private JFXTextField email_txt;
    @FXML
    private Button recuperer_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyer_mdp(ActionEvent event) throws IOException, SQLException {
        
        if (validate(email_txt.getText())) {
            UserService usr = new UserService();

            User user = new User();
            user = usr.getUserByEmail(email_txt.getText());
            if (user != null) {
                String plainpassword = getSaltString();
                System.out.println("Le nouveau mot de passe de " + user.getEmail() + "est " + plainpassword);

                usr.changePassword(plainpassword, email_txt.getText());
                Mailing m = new Mailing();
                String body="Bonjour Mme/mr "+ user.getName()+"\n"
                        + "Votre nouveau mot de passe est "+plainpassword;
                m.sendEmail(email_txt.getText(), "Récupérer mot de passe", body);
                email_txt.setVisible(false);
                System.out.println("Mot de passe envoyé par email");
            } else {
                System.out.println("Utilisateur introuvable");
            }

        }
        NavigationEntreInterfaces nav= new NavigationEntreInterfaces();
            nav.navigate(event, "Login", "/pidevjava/gui/Login.fxml");
    }
    
    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
