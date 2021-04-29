/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Hotel;
import entite.Transport;
import entite.Voyage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DataSource;

/**
 *
 * @author user
 */
public class hotelService implements IService <Hotel>{

    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public hotelService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Hotel h) {

        String req = "insert into hotel (nom,adresse,price,image,description,datedebut,datefin) values "
                + "('" + h.getNom() + "','" + h.getAdresse() + "','" + h.getPrice() + "','" + h.getImage() + "','" + h.getDescription() + "','" + h.getDatedebut() + "','" + h.getDatefin() + "')";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void insertwithPromoandwithoutTransp(Hotel h) {

        String req = "insert into hotel (nom,adresse,price,image,description,datedebut,datefin,id_promo) values "
                + "('" + h.getNom() + "','" + h.getAdresse() + "','" + h.getPrice() + "','" + h.getImage() + "','" + h.getDescription() + "','" + h.getDatedebut() + "','" + h.getDatefin() + "','" + h.getId_promo() + "')";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void insertwithtransportandwithpromo(Hotel h) {

        String req = "insert into hotel (nom,adresse,price,image,description,datedebut,datefin,id_promo,id_transport) values "
                + "('" + h.getNom() + "','" + h.getAdresse() + "','" + h.getPrice() + "','" + h.getImage() + "','" + h.getDescription() + "','" + h.getDatedebut() + "','" + h.getDatefin() + "','" + h.getId_promo() + "','" + h.getId_transport() + "')";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void insertwithtransportandwithoutpromo(Hotel h) {

        String req = "insert into hotel (nom,adresse,price,image,description,datedebut,datefin,id_transport) values "
                + "('" + h.getNom() + "','" + h.getAdresse() + "','" + h.getPrice() + "','" + h.getImage() + "','" + h.getDescription() + "','" + h.getDatedebut() + "','" + h.getDatefin() + "','" + h.getId_transport() + "')";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void insertwithpromoandwithoutTransp(Hotel h) {

        String req = "insert into hotel (nom,adresse,price,image,description,datedebut,datefin,id_promo) values "
                + "('" + h.getNom() + "','" + h.getAdresse() + "','" + h.getPrice() + "','" + h.getImage() + "','" + h.getDescription() + "','" + h.getDatedebut() + "','" + h.getDatefin() + "','" + h.getId_promo() + "')";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    

    @Override
    public void update(Hotel h) {
        
        String requeteUpdate = "UPDATE  `hotel` set `nom`='" + h.getNom() + "',`adresse`='" + h.getAdresse() + "',`price`='" + h.getPrice() + "',`image`='" + h.getImage() + "',`description`='" + h.getDescription() + "',`datedebut`='" + h.getDatedebut() + "',`datefin`='" + h.getDatefin() + "',`id_promo`='" + h.getId_promo()+ "' where `hotel`.`id`='" + h.getId() + "' ";

        try {
            ste = conn.createStatement();
             ste.executeUpdate(requeteUpdate);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String Req = " delete from hotel where id="+id+" ";
        try {
            ste = conn.createStatement();
            ste.execute(Req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List readAll() {
        String req = "select * from hotel order by price ASC";

        List<Hotel> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Hotel(rs.getInt("id"),rs.getString("nom"), rs.getString("adresse"), rs.getDouble("price"), rs.getString("image"), rs.getString("description"), rs.getDate("datedebut"), rs.getDate("datefin"), rs.getInt("id_promo"),rs.getInt("id_transport")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    public List listVoyage(int id) {
        String req = "SELECT voyage.categorie,voyage.description,voyage.destination,voyage.programme,voyage.image, voyage.prix,voyage.date_debut,voyage.date_fin FROM hotel INNER JOIN voyage ON hotel.id=voyage.id_hotel where hotel.id ='" + id + "' ";

        List<Voyage> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Voyage(rs.getString("categorie"), rs.getString("description"), rs.getString("destination"), rs.getString("programme"),rs.getString("image"), rs.getDouble("prix"), rs.getDate("date_debut"), rs.getDate("date_fin")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    public List listTransport(int id) {
        String req = "SELECT transport.description,transport.disponibilite,transport.price,transport.type FROM hotel INNER JOIN transport ON hotel.id_transport=transport.id where hotel.id ='" + id + "' ";

        List<Transport> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Transport(rs.getString("description"), rs.getString("disponibilite"), rs.getDouble("price"), rs.getString("type")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    public List readidPromo() {
        String req = "select id from promotion";

        List list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(rs.getInt("id"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    public List readidTransport() {
        String req = "select id from transport";

        List list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(rs.getInt("id"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public Hotel readById(int id) {
        Hotel h = new Hotel();
        String Req = "select * from hotel where id=" + id + "";
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(Req);
            while (rs.next()) {
                h = new Hotel(rs.getString("nom"),rs.getString("adresse"),rs.getDouble("price"),rs.getString("image"),  rs.getString("description"), rs.getDate("datedebut"),rs.getDate("datefin"), rs.getInt("id_promo"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return h;
    }
    
    
}
