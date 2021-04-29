/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava.services;

import pidevjava.entities.User;
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
public class UserService {

    public void changePassword(String mdp, String email) throws SQLException {

//        String hachedMdp = BCrypt.hashpw(mdp, BCrypt.gensalt());
        String req = "UPDATE user SET password = ?  WHERE email = ?";
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

    public void ajouterUser(User usr) {
//        String hachedMdp = BCrypt.hashpw(usr.getPassword(), BCrypt.gensalt());
        try {
            String req = "INSERT INTO user (name,fname,email,password,num,birthday,gender,image)"
                    + "VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);

            pst.setString(1, usr.getName());
            pst.setString(2, usr.getFname());
            pst.setString(3, usr.getEmail());
            pst.setString(4,usr.getPassword());
            pst.setInt(5, usr.getNum());
            pst.setDate(6, usr.getBirthday());
            pst.setString(7, usr.getGender());
            pst.setString(8, usr.getImage());

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User added");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateUser(User usr) {
        try {
            String req = "UPDATE user SET name=?,fname=?,email=?,num=?,birthday=?,gender=?,image=? WHERE id=?";
            PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);

            pst.setString(1, usr.getName());
            pst.setString(2, usr.getFname());
            pst.setString(3, usr.getEmail());
            pst.setInt(4, usr.getNum());
            pst.setDate(5, usr.getBirthday());
            pst.setString(6, usr.getGender());
            pst.setString(7, usr.getImage());
            pst.setInt(8, usr.getId());

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User updated");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void updateUserL(User usr) {
        try {
            String req = "UPDATE logged SET name=?,fname=?,email=?,num=?,birthday=?,gender=?,image=? WHERE id=?";
            PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);

            pst.setString(1, usr.getName());
            pst.setString(2, usr.getFname());
            pst.setString(3, usr.getEmail());
            pst.setInt(4, usr.getNum());
            pst.setDate(5, usr.getBirthday());
            pst.setString(6, usr.getGender());
            pst.setString(7, usr.getImage());
            pst.setInt(8, usr.getId());

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User updated");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimerUser(int id) {
        try {
            String req = "DELETE FROM user WHERE id=?";
            PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);
            pst.setInt(1, id);
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Utilisateur Supprimé");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    

    public List<User> displayUsers() {

        List<User> userList = new ArrayList<>();
        try {
            String req = "SELECT * FROM user ORDER BY birthday DESC";
            Statement st = MyCnx.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User u = new User();

                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setFname(rs.getString("fname"));
                u.setGender(rs.getString("gender"));
                u.setNum(rs.getInt("num"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setBirthday(rs.getDate("birthday"));
                u.setImage(rs.getString("image"));

                userList.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userList;
    }

    public List<User> RechercheUsers(String rech) {

        List<User> userList = new ArrayList<>();
        try {
            String req = "SELECT * FROM user WHERE name LIKE '%" + rech + "%' OR fname LIKE '%" + rech + "%' ";
            Statement st = MyCnx.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setFname(rs.getString("fname"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setImage(rs.getString("image"));
                u.setGender(rs.getString("gender"));
                u.setNum(rs.getInt("num"));
                u.setBirthday(rs.getDate("birthday"));

                userList.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userList;
    }

    public void loggedIn(User u) {
//        String hachedMdp = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
        try {
            String req = "INSERT INTO logged (id,name,fname,email,password,num,birthday,gender,image)"
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);
            pst.setInt(1, u.getId());
            pst.setString(2, u.getName());
            pst.setString(3, u.getFname());
            pst.setString(4, u.getEmail());
            pst.setString(5, u.getPassword());
            pst.setInt(6, u.getNum());
            pst.setDate(7, u.getBirthday());
            pst.setString(8, u.getGender());
            pst.setString(9, u.getImage());
            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User logged in");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public User getUserById(int id) throws SQLException {
        User u = null;
        String req = "SELECT * FROM user WHERE id = ?";
        PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            u = new User();
            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setFname(rs.getString("fname"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setImage(rs.getString("image"));
            u.setGender(rs.getString("gender"));
            u.setNum(rs.getInt("num"));
            u.setBirthday(rs.getDate("birthday"));

        }
        return u;
    }

    public User searchByPseudoPassU(String email, String mdp) throws SQLException {
        User u = null;
        String req = "SELECT * FROM user WHERE email = ?";
        PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            u = new User();
            u = UserService.this.getUserById(rs.getInt("id"));
            if (BCrypt.checkpw(u.getPassword(), BCrypt.hashpw(mdp, BCrypt.gensalt()))) {
                return u;
            }
        }
        return u;
    }

    public User getUserByEmail(String email) throws SQLException {
        User u = null;
        String req = "SELECT * FROM user WHERE email = ?";
        PreparedStatement pst = MyCnx.getInstance().getConnection().prepareStatement(req);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            u = new User();

            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setFname(rs.getString("fname"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setGender(rs.getString("gender"));
            u.setImage(rs.getString("image"));
            u.setNum(rs.getInt("num"));
            u.setBirthday(rs.getDate("birthday"));

            System.out.println("Utilisateur trouvé !");
            System.out.println(u);

        }
        return u;
    }

    public User getUserlogged() throws SQLException {

        User u = null;

        String req = "SELECT * FROM logged ";
        Statement st = MyCnx.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            u = new User();

            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setFname(rs.getString("fname"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setGender(rs.getString("gender"));
            u.setImage(rs.getString("image"));
            u.setNum(rs.getInt("num"));
            u.setBirthday(rs.getDate("birthday"));

            System.out.println("Utilisateur trouvé !");
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
                System.out.println("User logged out");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
