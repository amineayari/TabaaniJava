/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava.services;

import pidevjava.entities.Reclamation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pidevjava.entities.User;
import pidevjava.utils.MyCnx;


/**
 *
 * @author Abirn
 */
public class ReclamationService {

    public void ajouterReclamation(Reclamation rec) {

        try {
            String req = "INSERT INTO reclamation (sujet,description,date,user_id)"
                    + "VALUES (?,?,?,?)";

            PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);

            pst.setString(1, rec.getSujet());
            pst.setString(2, rec.getDescription());
            pst.setDate(3, rec.getDate());
             pst.setInt(4, rec.getUser_id().getId());

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Reclamation added");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateReclamation(Reclamation rec) {
        try {
            String req = "UPDATE reclamation SET sujet=? , description=? ,date=? WHERE id=?";

            PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);

            pst.setString(1, rec.getSujet());
            pst.setString(2, rec.getDescription());
            pst.setDate(3, rec.getDate());
            pst.setInt(4, rec.getId());

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Reclamation updated");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerReclamation(int id) {
        try {
            String req = "DELETE FROM reclamation WHERE id=?";

            PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);
            pst.setInt(1, id);

            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Reclamation deleted");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Reclamation> displayReclamations() {

        List<Reclamation> reclamList = new ArrayList<>();
        try {
      
          
      String req = "SELECT * FROM reclamation ORDER BY date ASC ";

            Statement st = MyCnx.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                Reclamation r = new Reclamation();

                r.setId(rs.getInt("id"));
                r.setSujet(rs.getString("sujet"));
                r.setDescription(rs.getString("description"));
                r.setDate(rs.getDate("date"));

                reclamList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamList;
    }

     public List<Reclamation> displayReclamations2(int id) {

        List<Reclamation> reclamList = new ArrayList<>();
        try {
      
          
         String req="SELECT reclamation.id, reclamation.sujet, reclamation.description, reclamation.date FROM reclamation INNER JOIN user ON user.id = reclamation.user_id WHERE user.id = '"+id+"'";

            Statement st = MyCnx.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                Reclamation r = new Reclamation();

                r.setId(rs.getInt("id"));
                r.setSujet(rs.getString("sujet"));
                r.setDescription(rs.getString("description"));
                r.setDate(rs.getDate("date"));

                reclamList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamList;
    }

    public List<Reclamation> RechercheReclamations(String rech,int id) {

        List<Reclamation> reclamationList = new ArrayList<>();
        try {
            String req = "SELECT * FROM reclamation WHERE sujet LIKE '%" + rech + "%' and  user_id= "+id;
            Statement st = MyCnx.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Reclamation rec = new Reclamation();
                rec.setId(rs.getInt("id"));
                rec.setSujet(rs.getString("sujet"));
                rec.setDescription(rs.getString("description"));
                rec.setDate(rs.getDate("date"));

                reclamationList.add(rec);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamationList;
    }
    
    public List<Reclamation> RechercheReclamations2(String rech) {

        List<Reclamation> reclamationList = new ArrayList<>();
        try {
            String req = "SELECT * FROM reclamation WHERE sujet LIKE '%" + rech + "%'  ";
            Statement st = MyCnx.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Reclamation rec = new Reclamation();
                rec.setId(rs.getInt("id"));
                rec.setSujet(rs.getString("sujet"));
                rec.setDescription(rs.getString("description"));
                rec.setDate(rs.getDate("date"));

                reclamationList.add(rec);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamationList;
    }
}
