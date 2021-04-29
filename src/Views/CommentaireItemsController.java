/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entite.Commentaire;
import Entite.Reaction;
import Service.CommentaireService;
import Service.ReactionService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author MEGA-PC
 */
public class CommentaireItemsController implements Initializable {

    @FXML
    private Label Commentaire;
    @FXML
    private Label datelabel;
    @FXML
    private Label likelabel;
    @FXML
    private Label dislikelibel;

    private Commentaire comment;
    @FXML
    private ImageView supprimerButton;
    /**
     * Initializes the controller class.
     */
    public void getcomment(Commentaire c){
        comment = c;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void setcommentaire(Commentaire c){
        ReactionService rs = new ReactionService();
        
        int nb1=rs.Affichernbdislike(c.getId());
        int nb2=rs.Affichernblike(c.getId());
        
        likelabel.setText(String.valueOf (nb2));
        dislikelibel.setText(String.valueOf (nb1));
        Commentaire.setText(c.getCommentaire());
         datelabel.setText(c.getDateajout().toString());
    }

    @FXML
    private void likebutton(ActionEvent event) {
        
       
        
        Reaction r=new Reaction(comment, 101, 1);
        ReactionService rs = new ReactionService();
        rs.Setrecation(r);
        
        int nb1=rs.Affichernbdislike(comment.getId());
        int nb2=rs.Affichernblike(comment.getId());
        
        likelabel.setText(String.valueOf (nb2));
        dislikelibel.setText(String.valueOf (nb1));
        
    }

    @FXML
    private void dislikebutton(ActionEvent event) {
        Reaction r=new Reaction(comment, 101, 0);
        ReactionService rs = new ReactionService();
        rs.Setrecation(r);
         
        int nb1=rs.Affichernbdislike(comment.getId());
        int nb2=rs.Affichernblike(comment.getId());
        
        likelabel.setText(String.valueOf (nb2));
        dislikelibel.setText(String.valueOf (nb1));
        
    }

    private void supprimerButton(ActionEvent event) {
        
        CommentaireService c = new CommentaireService();
        c.Supprimer(comment.getId());
        
    }

    @FXML
    private void supprimerButton(MouseEvent event) {
    }

}
