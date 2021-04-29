/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava.gui;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import pidevjava.entities.User;
import pidevjava.services.UserService;
import pidevjava.utils.NavigationEntreInterfaces;

/**
 * FXML Controller class
 *
 * @author Abirn
 */
public class ConfirmController implements Initializable {

    @FXML
    private Button confirmer_txt;
    @FXML
    private Button back_btn;
    @FXML
    private JFXTextField codet_txt;
    private String name;
    private String fname;
    private String email;
    private String password;
    private String gender;
    private String image;
    private int num;
    private Date bday;
    
    private int code;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        getInformation(name, fname, email, password, image, num, bday, gender);
        getCode(code);
        // TODO
    }

    @FXML
    private void confirmerCode(ActionEvent event) throws IOException {
        
        if (codet_txt.getText().equals(String.valueOf(code))) {
            System.out.println("Code Confirmé");
            inscrit();
            NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
            nav.navigate(event, "test", "/pidevjava/gui/Login.fxml");
            
            

        } else {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur Code");
            alert1.setContentText("Veuillez vérifier le code");
            alert1.setHeaderText(null);
            alert1.show();
        }
    }

    public void getInformation(String passedNom, String passedPrenom, String passedEmail, String passedMdp, String passedImage, int passedNum, Date passedBday, String passedGenre) {
       
        this.name = passedNom;
        this.fname = passedPrenom;
        this.email = passedEmail;
        this.password = passedMdp;
        this.image = passedImage;
        this.gender = passedGenre;
        this.bday = passedBday;
        this.num = passedNum;
    }

    public void getCode(int passedCode) {
        this.code = passedCode;
    }

    public void inscrit() {
        
            User u = new User(name, fname,  gender, num,email, password, bday, image);
            UserService us = new UserService();
            us.ajouterUser(u);
        
        
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
         NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
            nav.navigate(event, "test", "/pidevjava/gui/Register.fxml");
    }

}
