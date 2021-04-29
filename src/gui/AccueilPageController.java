/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import pidevjava.utils.NavigationEntreInterfaces;

/**
 * FXML Controller class
 *
 * @author Abirn
 */
public class AccueilPageController implements Initializable {

    @FXML
    private Button log_btn;
    @FXML
    private Button reg_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void pageLogin(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "test", "/pidevjava/gui/Login.fxml");
    }

    @FXML
    private void pageRegister(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "test", "/pidevjava/gui/Register.fxml");
    }

}
