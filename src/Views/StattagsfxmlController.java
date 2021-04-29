/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entite.Tags;
import Service.TagsService;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import utils.DataSource;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.WritableImage;
import utils.DataSource;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author MEGA-PC
 */
public class StattagsfxmlController implements Initializable {

    @FXML
      private BarChart<String, Float> Barchar;
    @FXML
    private JFXButton pdfbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResultSet Rs;
     Connection Cox;
     Cox = DataSource.getInstance().getConn();
     String query = "SELECT tags_id, COUNT(*) AS nb FROM sujet_tags GROUP BY tags_id";
   //  String Req = "select * from produit";
        
        Tags t = new Tags();
   
        XYChart.Series<String,Float> series = new XYChart.Series<>() ; 
        try { 
            Rs = Cox.createStatement().executeQuery(query) ;
            while (Rs.next()) {
                TagsService ts = new TagsService();
                t=ts.TrouverById(Integer.parseInt(Rs.getString(1))); 
                series.getData().add(new XYChart.Data<>(t.getTag(), Rs.getFloat(2))) ;
            }
            this.Barchar.getData().add(series); 
        } catch (SQLException ex) {
            Logger.getLogger(StattagsfxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void pdfbutton(ActionEvent event) throws DocumentException {
        
        Document doc = new Document();

        try {
            
            
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\MEGA-PC\\Desktop\\Stat.pdf"));
            doc.open();

            Image img = Image.getInstance("C:\\Users\\MEGA-PC\\Documents\\NetBeansProjects\\Forum\\src\\image\\logo.png");

            float documentWidth = doc.getPageSize().getWidth() - doc.leftMargin() - doc.rightMargin();
            float documentHeight = doc.getPageSize().getHeight() - doc.topMargin() - doc.bottomMargin();
            img.scaleToFit(100, 100);

            WritableImage image = Barchar.snapshot(new SnapshotParameters(), null);
            File file = new File("chart.png");
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            Image img2 = Image.getInstance("C:\\Users\\MEGA-PC\\Documents\\NetBeansProjects\\Forum\\chart.png");
            img2.scaleToFit(documentWidth, documentHeight);



            doc.add(img);
            Font f=new Font(FontFamily.TIMES_ROMAN,50.0f,Font.UNDERLINE);

            Paragraph p = new Paragraph("les Tags par Sujet :",f); 
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);

            doc.add(img2);

            doc.newPage();




            doc.close();
            Desktop.getDesktop().open(new File ("C:\\Users\\MEGA-PC\\Desktop\\Stat.pdf"));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(StattagsfxmlController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadElementException ex) {
            Logger.getLogger(StattagsfxmlController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StattagsfxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    

