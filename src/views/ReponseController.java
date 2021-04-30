/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

//import com.itextpdf.text.Image;
import entities.Reponse;
import entities.question;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.Questionservice;
import services.Reponseservice;


/**
 * FXML Controller class
 *
 * @author BJI
 */
public class ReponseController implements Initializable {
 private question question;
    @FXML
    private ImageView imagelab;
    @FXML
    private Label questionlab;
    @FXML
    private ComboBox<String> comboR;
    @FXML
    private Button buttonR;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                  comboR.getItems().addAll("1","2","3","4","5");

        // TODO
    }    
    public void setData(question Q) {
        question = Q;
        questionlab.setText(Q.getQuestion());
       
      Image image = new Image(getClass().getResourceAsStream(Q.getImage()));
     imagelab.setImage(image);
      FrontRController.getIdd(Q.getId()) ;
    }

    @FXML
    private void REPONDRE(ActionEvent event) {
         Reponse R = new Reponse(question,comboR.getValue());
        Reponseservice Ps = new Reponseservice();
        Ps.Ajouter(R);

    } 
}
