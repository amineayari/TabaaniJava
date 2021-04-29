/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava.services;

import pidevjava.entities.Admin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Alert;
import pidevjava.utils.BCrypt;
import pidevjava.utils.MyCnx;

/**
 *
 * @author Abirn
 */
public class AdminService {

    public void changePassword(String mdp, String email) throws SQLException {

//        String hachedMdp = BCrypt.hashpw(mdp, BCrypt.gensalt());
        String req = "UPDATE admin SET password = ?  WHERE email = ?";
        PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);
        pst.setString(1, mdp);
        pst.setString(2, email);
        int rowUpdated = pst.executeUpdate();
        if (rowUpdated > 0) {
            System.out.println("Mdp modifié");
        } else {
            System.out.println("ERR");
        }
    }

    public void ajouterAdmin(Admin ad) {
//        String hachedMdp = BCrypt.hashpw(ad.getPassword(), BCrypt.gensalt());
        try {
            String req = "INSERT INTO admin (adminname,lastname,email,password,tel,birthday,gender,image)"
                    + "VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);
            pst.setString(1, ad.getAdminname());
            pst.setString(2, ad.getLastname());
            pst.setString(3, ad.getEmail());
            pst.setString(4, ad.getPassword());
            pst.setInt(5, ad.getTel());
            pst.setDate(6, ad.getBirthday());
            pst.setString(7, ad.getGender());
            pst.setString(8, ad.getImage());
            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Admin added");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateAdmin(Admin ad) {
        try {
            String req = "UPDATE admin SET adminname=?,lastname=?,email=?,tel=?,birthday=?,gender=?,image=? WHERE id=?";

            PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);
            
            pst.setString(1, ad.getAdminname());
            pst.setString(2, ad.getLastname());
            pst.setString(3, ad.getEmail());
            pst.setInt(4, ad.getTel());
            pst.setDate(5, ad.getBirthday());
            pst.setString(6, ad.getGender());
            pst.setString(7, ad.getImage());
            pst.setInt(8, ad.getId());
          

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Admin updated");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

     public void updateAdminL(Admin ad) {
        try {
            String req = "UPDATE logged SET name=?,fname=?,email=?,num=?,birthday=?,gender=?,image=? WHERE id=?";
            PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);

            pst.setString(1, ad.getAdminname());
            pst.setString(2, ad.getLastname());
            pst.setString(3, ad.getEmail());
            pst.setInt(4, ad.getTel());
            pst.setDate(5, ad.getBirthday());
            pst.setString(6, ad.getGender());
            pst.setString(7, ad.getImage());
            pst.setInt(8, ad.getId());

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Admin updated");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void supprimerAdmin(int id) {
        try {
            String req = "DELETE FROM admin WHERE id=?";

            PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);
            pst.setInt(1, id);

            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Admin deleted");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Admin> displayAdmins() {

        List<Admin> adminList = new ArrayList<>();
        try {
            String req = "SELECT * FROM admin ";
            Statement st = MyCnx.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                Admin ad = new Admin();

                ad.setId(rs.getInt("id"));
                ad.setAdminname(rs.getString("adminname"));
                ad.setLastname(rs.getString("lastname"));
                ad.setGender(rs.getString("gender"));
                ad.setTel(rs.getInt("tel"));
                ad.setEmail(rs.getString("email"));
                ad.setPassword(rs.getString("password"));
                ad.setBirthday(rs.getDate("birthday"));
                ad.setImage(rs.getString("image"));

                adminList.add(ad);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return adminList;
    }

    public List<Admin> RechercheAdmins(String rech) {

        List<Admin> adminList = new ArrayList<>();
        try {
            String req = "SELECT * FROM admin WHERE adminname LIKE '%" + rech + "%' OR lastname LIKE '%" + rech + "%' ";
            Statement st = MyCnx.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Admin ad = new Admin();
                ad.setId(rs.getInt("id"));
                ad.setAdminname(rs.getString("adminname"));
                ad.setLastname(rs.getString("lastname"));
                ad.setEmail(rs.getString("email"));
                ad.setPassword(rs.getString("password"));
                ad.setImage(rs.getString("image"));
                ad.setGender(rs.getString("gender"));
                ad.setTel(rs.getInt("tel"));
                ad.setBirthday(rs.getDate("birthday"));

                adminList.add(ad);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return adminList;
    }

    public void loggedIn(Admin ad) {
//        String hachedMdp = BCrypt.hashpw(ad.getPassword(), BCrypt.gensalt());
        try {
            String req = "INSERT INTO logged (id,name,fname,email,password,num,birthday,gender,image)"
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);
            pst.setInt(1, ad.getId());
            pst.setString(2, ad.getAdminname());
            pst.setString(3, ad.getLastname());
            pst.setString(4, ad.getEmail());
            pst.setString(5, ad.getPassword());
            pst.setInt(6, ad.getTel());
            pst.setDate(7, ad.getBirthday());
            pst.setString(8, ad.getGender());
            pst.setString(9, ad.getImage());
            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Admin logged in");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Admin getUserById(int id) throws SQLException {
        Admin a = null;
        String req = "SELECT * FROM admin WHERE id = ?";
        PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            a = new Admin();
            a.setId(rs.getInt("id"));
            a.setAdminname(rs.getString("adminname"));
            a.setLastname(rs.getString("lastname"));
            a.setEmail(rs.getString("email"));
            a.setPassword(rs.getString("password"));
            a.setImage(rs.getString("image"));
            a.setGender(rs.getString("gender"));
            a.setTel(rs.getInt("tel"));
            a.setBirthday(rs.getDate("birthday"));

        }
        return a;
    }

    public Admin searchByPseudoPassU(String email, String mdp) throws SQLException {
        Admin ad = null;
        String req = "SELECT * FROM admin WHERE email = ?";
        PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            ad = new Admin();
            ad = AdminService.this.getUserById(rs.getInt("id"));
            if (BCrypt.checkpw(ad.getPassword(), BCrypt.hashpw(mdp, BCrypt.gensalt()))) {
                return ad;
            }
        }
        return ad;
    }

    public Admin getUserByEmail(String email) throws SQLException {
        Admin u = null;
        String req = "SELECT * FROM admin WHERE email = ?";
        PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            u = new Admin();

            u.setId(rs.getInt("id"));
            u.setAdminname(rs.getString("adminname"));
            u.setLastname(rs.getString("lastname"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setGender(rs.getString("gender"));
            u.setImage(rs.getString("image"));
            u.setTel(rs.getInt("tel"));
            u.setBirthday(rs.getDate("birthday"));

            System.out.println("Admin trouvé !");
            System.out.println(u);

        }
        return u;
    }

    public Admin getUserlogged() throws SQLException {
        Admin u = null;
        String req = "SELECT * FROM logged ";
        Statement st = MyCnx.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            u = new Admin();
            u.setId(rs.getInt("id"));
            u.setAdminname(rs.getString("name"));
            u.setLastname(rs.getString("fname"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setGender(rs.getString("gender"));
            u.setImage(rs.getString("image"));
            u.setTel(rs.getInt("num"));
            u.setBirthday(rs.getDate("birthday"));

            System.out.println("Admin trouvé !");
            System.out.println(u);
        }
        return u;
    }

    public void loggedOut() {
        try {
            String req = "DELETE FROM logged ";
            PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Admin logged out");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
