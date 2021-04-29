/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entite.Sujet;
import Service.SujetService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MEGA-PC
 */
public class SujetItemFXMLController implements Initializable {
private Sujet sujet;
public int id;
    @FXML
    private Label Sujetitem;
    @FXML
    private Label nbvuelabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public int getid(int id){
        
        this.id = id;
        return id;
        
    }
    
    public void afficheritem(Sujet sujet,int id){
        Sujetitem.setText(sujet.getSujet());
        nbvuelabel.setText("nombre vue :"+String.valueOf(sujet.getNbveus()));
    }

    @FXML
    private void affichercommentaires(MouseEvent event) throws IOException {
   
        CommentaireIndexController.getIdd(id); 
        System.out.println(id);
        
        SujetService ss = new SujetService();
        ss.addnbrvue(id);
        
        Parent root = FXMLLoader.load(getClass().getResource("/Views/CommentaireIndex.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        
    }
    
}
