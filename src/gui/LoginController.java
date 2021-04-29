/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava.gui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import pidevjava.entities.Admin;
import pidevjava.entities.User;
import pidevjava.services.AdminService;
import pidevjava.services.UserService;
import pidevjava.utils.BCrypt;
import pidevjava.utils.MyCnx;
import pidevjava.utils.NavigationEntreInterfaces;
import pidevjava.utils.Notification;
import tray.notification.NotificationType;
/**
 * FXML Controller class
 *
 * @author Abirn
 */
public class LoginController implements Initializable {

    @FXML
    private Button back_btn;
    @FXML
    private Button oublie_btn;
    @FXML
    private Button ggl_btn;
    @FXML
    private Button twitter_btn;
    @FXML
    private Button fb_btn;
    @FXML
    private Button login_btn;
    @FXML
    private JFXTextField login_txt;
    @FXML
    private JFXPasswordField pwd_login_txt;

    UserService us = new UserService();
    AdminService as = new AdminService();
    @FXML
    private JFXRadioButton admin_rb;
    @FXML
    private JFXRadioButton user_rb;
    private boolean admin;
    private boolean user;
    
    public static int userid;
    public static User usr;
    
       
    private String appId = "453969385698256";
    private String appSecret = "dbc21d2f9d8a136099ad6c68399f1751";
    private String redirectUrl = "http://localhost/";
    private String state = "2707";
    private String rectidrectUrlEncode = "http%3A%2F%2Flocalhost%2F";
    private String authentification = "http://www.facebook.com/v8.0/dialog/oauth?client_id=" + appId + "&redirect_uri=" + rectidrectUrlEncode + "&state" + state;
    ;
    private String graph = "https://graph.facebook.com/v8.0/oauth/access_token?client_id=" + appId + "&redirect_uri=" + rectidrectUrlEncode + "&client_secret=" + appSecret + "&code=";
    public String profileUrl;
    private String prof;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "test", "/pidevjava/gui/AccueilPage.fxml");
    }

    @FXML
    private void OnOublie(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "test", "/pidevjava/gui/RecupMDP.fxml");
        
        Notification notif = new Notification();
        notif.notification("Récuperer mot de passe","Code envoyer avec succée",NotificationType.SUCCESS);
    }

    @FXML
    private void gglLogin(ActionEvent event) {
    }

    @FXML
    private void twitterLogin(ActionEvent event) {
    }

    @FXML
    private void fbLogin(ActionEvent event) throws IOException {

      WebView webView = new WebView();
        WebEngine eg = webView.getEngine();
        eg.load(authentification);
        Pane wView = new Pane();
        wView.getChildren().add(webView);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(wView));
        stage.show();
        eg.locationProperty().addListener((obs, oldlocation, newlocation) -> {
            if (newlocation != null && newlocation.startsWith("http://localhost")) {
                try {
                    int codeOffset = newlocation.indexOf("code");
                    String code = newlocation.substring(codeOffset + "code=".length());
                    String graph1 = graph + code;
                    System.out.println(graph1);
                    DefaultFacebookClient facebookClient = new DefaultFacebookClient(Version.LATEST);
                    FacebookClient.AccessToken accessToken = facebookClient.obtainUserAccessToken(appId, appSecret, "http://localhost/", code);
                    String access_token = accessToken.getAccessToken();
                    FacebookClient fbClient = new DefaultFacebookClient(access_token, Version.LATEST);
                    fbClient.createClientWithAccessToken(access_token);
                    JsonObject profile_pic = fbClient.fetchObject("me/picture", JsonObject.class, Parameter.with("redirect", "false"));
                    String fields = "name,first_name,last_name,birthday,email,gender";
                    com.restfb.types.User user = fbClient.fetchObject("me", com.restfb.types.User.class, Parameter.with("fields", fields));
                    System.out.println(user);
                    System.out.println(user.getName());
                    System.out.println(user.getFirstName());
                    System.out.println(user.getLastName());
                    System.out.println(user.getBirthday());
                    System.out.println(user.getGender());
                    int si = profile_pic.toString().indexOf("url\":\"");
                    int ei = profile_pic.toString().indexOf("\",\"");
                    profileUrl = profile_pic.toString().substring(si + 6, ei);
                    System.out.println(profileUrl);
                    prof = profileUrl;

                    //Ajout de l'utilisateur(client seulement car le coach doit enter son domaine
                    if ((us.getUserByEmail(user.getEmail()))== null) {
                        //régler le genre
                        String genre = "";
                        if (user.getGender().equals("female")) {
                            genre = "Female";
                        } else if (user.getGender().equals("male")) {
                            genre = "Male";
                        }
                        //regler le bday
                        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
                        java.util.Date date;
                        try {
                            date = sdf1.parse(user.getBirthday());
                            java.sql.Date bday = new java.sql.Date(date.getTime());
                            System.out.println(bday);
                            System.out.println(user.getBirthday().getClass());
                            User clt = new User(user.getLastName(), user.getFirstName(), user.getEmail(),"C:/Users/Abirn/OneDrive/Documents/NetBeansProjects/PiDevJava/src/pidevjava/gui/Images" , genre, bday);
                            UserService cs = new UserService();
                            cs.ajouterUser(clt);
                            us.loggedIn(us.getUserByEmail(user.getEmail()));
                        } catch (ParseException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //fermer la page fb
                        stage.close();
                        //envoyer la pdp et afficher le side bar
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidevjava/gui/Sidebar.fxml"));
                            Parent root = (Parent) loader.load();
                            SidebarController sb = loader.getController();
                            System.out.println("controller ");
                            sb.getPdp(profileUrl);
                            sb.getText("codeeee");
                            System.out.println("image passé");
                            Stage stage2 = new Stage();
                            stage2.setScene(new Scene(root));
                            stage2.show();
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        us.loggedIn(us.getUserByEmail(user.getEmail()));
                        stage.close();
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/slowlifejava/gui/users/Sidebar.fxml"));
                            Parent root = (Parent) loader.load();
                            SidebarController sb = loader.getController();
                            System.out.println("controller ");
                            sb.getPdp(profileUrl);
                            sb.getText("codeeee");
                            System.out.println("image passé");
                            Stage stage2 = new Stage();
                            stage2.setScene(new Scene(root));
                            stage2.show();
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);

                }
            }

        }
        );
        
    }

    @FXML
    private void OnLogin(ActionEvent event) throws IOException {
        if(user){
        try {
            if (validateInputs()) {
                UserService us = new UserService();
                String email = login_txt.getText();
                String mdp = pwd_login_txt.getText();
                User u = us.searchByPseudoPassU(email, mdp);
                System.out.println(u);
                if (u != null && (pwd_login_txt.getText().equals(u.getPassword()))) {
                    us.loggedIn(u);
                    NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "Sidebar", "/pidevjava/gui/Sidebar.fxml");
                    System.out.println(u.getImage());
                    //API Notification lors de l'ajout d'un evenement
//                    Notifications notificationBuilder = Notifications.create()
//                            .title("Welcome  " + login_txt.getText())
//                            .text("Bienvenue à Tabaani Travel Agency")
//                            .hideAfter(javafx.util.Duration.seconds(4))
//                            .position(Pos.TOP_CENTER);
//                    notificationBuilder.show();
            
        
                     userid=u.getId();
                     usr=u;
                     
                       Notification notif = new Notification();
              notif.notification("Login","Bienvenue à Tabaani Travel Agency",NotificationType.SUCCESS);
                } else {
                    Alert alert2 = new Alert(Alert.AlertType.WARNING);
                    alert2.setTitle("Erreur");
                    alert2.setContentText("Veuillez vérifier votre email ou mot de passe");
                    alert2.setHeaderText(null);
                    alert2.show();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else if (admin){
             try {
            if (validateInputs()) {
                String email = login_txt.getText();
                String mdp = pwd_login_txt.getText();
                Admin a = as.searchByPseudoPassU(email, mdp);
                System.out.println(a);
                if (a != null &&(pwd_login_txt.getText().equals( a.getPassword()))) {
                    as.loggedIn(a);
                    NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
                    nav.navigate(event, "Sidebar", "/pidevjava/gui/Back.fxml");
                    System.out.println(a.getImage());
                    //API Notification lors de l'ajout d'un evenement
//                    Notifications notificationBuilder = Notifications.create()
//                            .title("Welcome  " + login_txt.getText())
//                            .text("Bienvenue à Tabaani Travel Agency")
//                            .hideAfter(javafx.util.Duration.seconds(4))
//                            .position(Pos.TOP_CENTER);
//                    notificationBuilder.show();
                    
                        Notification notif = new Notification();
              notif.notification("Login","Bienvenue à Tabaani Travel Agency",NotificationType.SUCCESS);
                   
                } else {
                    Alert alert2 = new Alert(Alert.AlertType.WARNING);
                    alert2.setTitle("Erreur");
                    alert2.setContentText("Veuillez vérifier votre email ou mot de passe");
                    alert2.setHeaderText(null);
                    alert2.show();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    }

    private boolean validateInputs() throws SQLException {

        if (login_txt.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez saisir votre email");
            alert1.setHeaderText("Controle de saisie");
            alert1.show();
            return false;
        }
        if (pwd_login_txt.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez saisir votre mot de passe");
            alert1.setHeaderText("Controle de saisie");
            alert1.show();
            return false;
        }
        return true;

    }

    @FXML
    private void loginAdmin(ActionEvent event) throws IOException {
        user=false;
        admin=true; 
    }

    @FXML
    private void loginUser(ActionEvent event) throws IOException {
        user=true;
        admin=false; 
    }
}
