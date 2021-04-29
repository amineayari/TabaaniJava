/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entite.Promotion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;
import views.PromoController;

/**
 *
 * @author House_Info
 */
public class PromotionService implements Iservice<Promotion> {

    private Statement ste;
    private ResultSet rs;
  

    private final Connection conn;

    public PromotionService() {
        conn = DataSource.getInstance().getConn();
    }

    
    
    
    public double calculPrix(int id,int pourcentage) throws SQLException{
        double prix=0;
             String req1="SELECT prix FROM voyage WHERE id="+id+"";
    PreparedStatement pst1 = conn.prepareStatement(req1);
    ResultSet rs=pst1.executeQuery();
    while (rs.next()){
        prix=rs.getDouble("prix");
        
    }
    
    return prix-((prix*pourcentage)/100);
    }
    
  
    public void ajouter(Promotion p,int idV) {
   
        
    try {
        //ajouter id promo dans table voyage
   
        
        String requete = "INSERT INTO promotion (id,pourcentage,prix,date_debut,date_fin) VALUES (?,?,?,?,?)";
            PreparedStatement pst1 = conn.prepareStatement(requete);
            pst1.setInt(1,p.getId());
            pst1.setDouble(2, p.getPourcentage());
            pst1.setDouble(3,p.getPrix());
            pst1.setDate(4,p.getDate_debut());
            pst1.setDate(5, p.getDate_fin());
            pst1.executeUpdate();
            System.out.println("Promotion ajoutée !");
            
            
            int idPromoAajouter=0;
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM promotion ORDER BY id DESC LIMIT 1");
            while (rs.next()){
                idPromoAajouter=rs.getInt("id");
            }
            System.out.println(idPromoAajouter);
            
            String rqst="update  voyage set promotion_id=? where id=?";
          PreparedStatement pst = conn.prepareStatement(rqst);
          pst.setInt(1,idPromoAajouter);
            pst.setInt(2,idV);
             pst.executeUpdate();
          

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

  
    @Override
    public void supprimer(int id) {
        try {
            supprimerIdPromoFromVoyage(id);
            String requete = "DELETE FROM promotion WHERE id=?";//
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("La promotion est supprimée avec succés  ! \n");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression \n" + ex.getMessage());
        }
    }

    @Override
    public void modifier(Promotion t) {
        try {
            String requete = "UPDATE `promotion` SET pourcentage=?,prix=?, date_debut=?, date_fin=? WHERE id= ?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(5, t.getId());
            pst.setDouble(2, t.getPourcentage());
            pst.setDouble(1, t.getPrix());
            pst.setDate(3, t.getDate_debut());
            pst.setDate(4, t.getDate_fin());
            

            pst.executeUpdate();
            System.out.println("La promotion est modifiée avec succés \n ");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la modification \n " + ex.getMessage());
        }
    }

    @Override
    public List<Promotion> afficher() {
        String req = "select * from promotion";

        List<Promotion> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Promotion(rs.getInt("id"), rs.getDouble(2), rs.getDouble(3), rs.getDate(4), rs.getDate(5)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PromotionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    
    public static Comparator<Promotion> PComparator = (Promotion p1, Promotion  p2) -> {
        Double ev1 = p1.getPrix();
        Double ev2 = p2.getPrix();
        return ev1.compareTo(ev2);
    };
    public ArrayList<Promotion> triPrix() {
        ArrayList<Promotion> promotions = new ArrayList<>();
        String requete = "select * from promotion ";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                promotions.add(new Promotion( rs.getInt(1), rs.getDouble(2), rs.getDouble (3),rs.getDate (4),rs.getDate (5)));
            }
            Collections.sort(promotions,PComparator );
            Collections.reverse(promotions);
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
        }
        return promotions;
    } 
    

    
    
      public ArrayList<Promotion> RecherchePourcentage(Double x) {
        
        ArrayList<Promotion> promotions = new ArrayList<>();
        String requete = "select * from promotion where pourcentage ='"+x+"'";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                promotions.add(new Promotion( rs.getInt(1), rs.getDouble(2), rs.getDouble (3),rs.getDate (4),rs.getDate(5)));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
        }
        return promotions;
    }

      
      
      public void modifierChamp(String champ,String Valeur,int id){
     try{
        
        String requete = "UPDATE promotion  SET "+champ+"=?"
                    + " WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            
            pst.setString(1,Valeur);
            pst.setInt(2,id);
            pst.executeUpdate();
       } catch (SQLException ex) {
            Logger.getLogger(PromoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
//         public void modifierChampDate(String champ,Date Valeur,int id){
//     try{
//        
//        String requete = "UPDATE promotion  SET "+champ+"=?"
//                    + " WHERE id=?";
//            PreparedStatement pst = conn.prepareStatement(requete);
//            
//            pst.setDate(1,Valeur);
//            pst.setInt(2,id);
//            pst.executeUpdate();
//       } catch (SQLException ex) {
//            Logger.getLogger(PromoController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
//      
      
      public List<Promotion> recherche(String rech) {

        List<Promotion> List = new ArrayList<>();
        try {
            String req = "SELECT * FROM promotion WHERE pourcentage LIKE '%" + rech + "%'  ";
           
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
 
            while (rs.next()) {
                Promotion p = new Promotion();
                p.setId(rs.getInt("id"));
                p.setPourcentage(rs.getDouble("pourcentage"));
                p.setPrix(rs.getDouble("prix"));
                p.setDate_debut(rs.getDate("date_debut"));
                p.setDate_fin(rs.getDate("date_fin"));
           
                

                List.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return List;
    }
      
      
      
      public ArrayList<Promotion> RechercheP(Double x) {
        
        ArrayList<Promotion> promotions = new ArrayList<>();
        String requete = "select * from promotion where  ='"+x+"'";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                promotions.add(new Promotion( rs.getInt(1), rs.getString(2), rs.getString (3),rs.getString (4),rs.getDate (5),rs.getDate(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getInt(10),rs.getInt(11)));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
        }
        return promotions;
    }
 public void supprimerIdPromoFromVoyage (int id){
        try {
            
            String requete = "UPDATE voyage set promotion_id=NULL  WHERE promotion_id=?";//
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("La promotion est null  ! \n");
        } catch (SQLException ex) {
            System.out.println("erreur null\n" + ex.getMessage());
        }
    }
    public void supprimerClic(int id) {
        try {
               supprimerIdPromoFromVoyage(id);
            String requete = "DELETE FROM promotion WHERE id=?";//
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("La promotion est supprimée avec succés  ! \n");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression \n" + ex.getMessage());
        }
   
}

    @Override
    public void ajouter(Promotion t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String getImageFromVoyage(int id) throws SQLException{
        String path="";
                String req1="SELECT image FROM voyage WHERE id=(SELECT id FROM voyage WHERE promotion_id="+id+")";
    PreparedStatement pst1 = conn.prepareStatement(req1);
    ResultSet rs=pst1.executeQuery();
        System.out.println("hhhh");
    while (rs.next()){
        path=rs.getString("image");
        
    }
    return path;
    }
}
