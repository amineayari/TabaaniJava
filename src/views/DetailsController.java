/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXTextArea;
import entite.Voyage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.VoyageService;

/**
 * FXML Controller class
 *
 * @author House_Info
 */
public class DetailsController implements Initializable {

    @FXML
    private ImageView imagelab;
    @FXML
    private Label Nomlab;
    private Label Prixlab;
    @FXML
    private Button Commander;
    
private static int idd;
    private Voyage Voy;

    public static int getIdd(int id) {
        idd = id;
        return idd;
    }
    private Label proglab;
    @FXML
    private JFXTextArea labarea;
    @FXML
    private JFXTextArea idNinclut;
    @FXML
    private JFXTextArea idInclut;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VoyageService vs=new VoyageService();
      Voy=vs.TrouverById(idd);
       Image image = new Image(getClass().getResourceAsStream(Voy.getImage()));
        imagelab.setImage(image);
         Nomlab.setText(Voy.getDestination());
        
        labarea.setText(Voy.getProgramme());
        idInclut.setText(Voy.getInclut());
         idNinclut.setText(Voy.getNinclut());
        
        
    }    
    
    
}
