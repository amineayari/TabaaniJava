/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

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
 * @author admin
 */
public class EssaiiController implements Initializable {

    @FXML
    private BarChart<String, Float> Barchar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     ResultSet Rs;
     Connection Cox;
     Cox = DataSource.getInstance().getConn();
     String query = "SELECT produit_id, COUNT(*) AS nb FROM commande_produit GROUP BY produit_id";
   //  String Req = "select * from produit";

        XYChart.Series<String,Float> series = new XYChart.Series<>() ; 
        try { 
            Rs = Cox.createStatement().executeQuery(query) ;
            while (Rs.next()) {
                series.getData().add(new XYChart.Data<>(Rs.getString(1), Rs.getFloat(2))) ;
            }
            Barchar.getData().add(series) ; 
        } catch (SQLException ex) {
            Logger.getLogger(EssaiiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }    

    @FXML
    private void PDF(ActionEvent event) throws DocumentException {
         Document doc = new Document();
        
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\admin\\Desktop\\Stat.pdf"));
            doc.open();
            
            Image img = Image.getInstance("C:\\Users\\admin\\Documents\\NetBeansProjects\\GestionProduit\\src\\image\\logotabaani.png");
            
            float documentWidth = doc.getPageSize().getWidth() - doc.leftMargin() - doc.rightMargin();
            float documentHeight = doc.getPageSize().getHeight() - doc.topMargin() - doc.bottomMargin();
            img.scaleToFit(100, 100);
            
            WritableImage image = Barchar.snapshot(new SnapshotParameters(), null);
            File file = new File("chart.png");
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            Image img2 = Image.getInstance("C:\\Users\\admin\\Documents\\NetBeansProjects\\GestionProduit\\chart.png");
            img2.scaleToFit(documentWidth, documentHeight);
            
         

            doc.add(img);
            Font f=new Font(FontFamily.TIMES_ROMAN,50.0f,Font.UNDERLINE);
            
            Paragraph p = new Paragraph("les commandes par produit :",f); 
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
            
            doc.add(img2);
            
            doc.newPage();
            
  
            
            
            doc.close();
            Desktop.getDesktop().open(new File ("C:\\Users\\admin\\Desktop\\Stat.pdf"));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EssaiiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadElementException ex) {
            Logger.getLogger(EssaiiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EssaiiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    

