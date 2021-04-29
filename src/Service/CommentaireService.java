/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Commentaire;
import Entite.Sujet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author MEGA-PC
 */
public class CommentaireService implements IService<Commentaire> {

    private Statement Ste;
    private PreparedStatement Pst;
    private ResultSet Rs;
    private Connection Cox;

    public CommentaireService() {
        Cox = DataSource.getInstance().getConn();
    }

    @Override
    public void Ajouter(Commentaire t) {
        loadConfigs();
        System.out.println(filterText(t.getCommentaire()));
        String Req = "insert into commentaire (sujet_id,user_id,commentaire,filtredcomment,dateajout) values (?,?,?,?,?)";
        try {
            Pst = Cox.prepareStatement(Req);
            Pst.setInt(1, t.getSujet().getId());
            Pst.setInt(2, t.getUser_id());
            Pst.setString(3, t.getCommentaire());
            Pst.setString(4, t.getFiltredcomment());
            Pst.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
            Pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int Ajouter2(Commentaire t) {

        loadConfigs();
        if (filterText(t.getCommentaire()) == 0) {

            return 0;
        } else {

            try {
                String Req = "insert into commentaire (sujet_id,user_id,commentaire,filtredcomment,dateajout) values (?,?,?,?,?)";
                Pst = Cox.prepareStatement(Req);
                Pst.setInt(1, t.getSujet().getId());
                Pst.setInt(2, t.getUser_id());
                Pst.setString(3, t.getCommentaire());
                Pst.setString(4, t.getFiltredcomment());
                Pst.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
                Pst.executeUpdate();
                return 1;
            } catch (SQLException ex) {
                Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 1;
    }

    @Override
    public List<Commentaire> Afficher() {
        /* String Req = "select * from commentaire left join forum on ";
          
        List<Commentaire> list = new ArrayList<>();
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                list.add(new Commentaire(Rs.getInt("id"), Rs.getInt("user_id"),  Rs.getString("sujet"),Rs.getString("description"), Rs.getString("image"), Rs.getInt("nbvues")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Commentaire> Afficherbysujet(int id_sujet) {
        String Req2 = "select * from Sujet where id = " + id_sujet + "";
        //Sujet sujet ;
        Sujet sujet = new Sujet();
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req2);
            while (Rs.next()) {
                // list.add(new Commentaire(Rs.getInt("id"), Rs.getInt("user_id"),  Rs.getString("sujet"),Rs.getString("description"), Rs.getString("image"), Rs.getInt("nbvues")));
                sujet = new Sujet(Rs.getInt("id"), Rs.getInt("user_id"), Rs.getString("sujet"), Rs.getString("description"), Rs.getString("image"), Rs.getInt("nbvues"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }

        String Req = "select * from commentaire where sujet_id = " + id_sujet + "";

        List<Commentaire> list = new ArrayList<>();
        try {
            Ste = Cox.createStatement();
            Rs = Ste.executeQuery(Req);
            while (Rs.next()) {
                list.add(new Commentaire(Rs.getInt("id"), sujet, Rs.getInt("user_id"), Rs.getString("commentaire"), Rs.getString("filtredcomment"), Rs.getDate("dateajout")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Commentaire TrouverById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Modifier(Commentaire t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Supprimer(int id) {
         String Req = " delete from commentaire where id="+id+" ";
        try {
            Ste = Cox.createStatement();
            Ste.execute(Req);
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static Map<String, String[]> words = new HashMap<>();

    static int largestWordLength = 0;

    public static void loadConfigs() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://docs.google.com/spreadsheets/d/1hIEi2YG3ydav1E06Bzf2mQbGZ12kh2fe4ISgLg_UBuM/export?format=csv").openConnection().getInputStream()));
            String line = "";
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                counter++;
                String[] content = null;
                try {
                    content = line.split(",");
                    if (content.length == 0) {
                        continue;
                    }
                    String word = content[0];
                    String[] ignore_in_combination_with_words = new String[]{};
                    if (content.length > 1) {
                        ignore_in_combination_with_words = content[1].split("_");
                    }

                    if (word.length() > largestWordLength) {
                        largestWordLength = word.length();
                    }
                    words.put(word.replaceAll(" ", ""), ignore_in_combination_with_words);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            System.out.println("Loaded " + counter + " words to filter out");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Iterates over a String input and checks whether a cuss word was found in
     * a list, then checks if the word should be ignored (e.g. bass contains the
     * word *ss).
     *
     * @param input
     * @return
     */
    public static ArrayList<String> badWordsFound(String input) {
        if (input == null) {
            return new ArrayList<>();
        }

        // don't forget to remove leetspeak, probably want to move this to its own function and use regex if you want to use this 
        input = input.replaceAll("1", "i");
        input = input.replaceAll("!", "i");
        input = input.replaceAll("3", "e");
        input = input.replaceAll("4", "a");
        input = input.replaceAll("@", "a");
        input = input.replaceAll("5", "s");
        input = input.replaceAll("7", "t");
        input = input.replaceAll("0", "o");
        input = input.replaceAll("9", "g");

        ArrayList<String> badWords = new ArrayList<>();
        input = input.toLowerCase().replaceAll("[^a-zA-Z]", "");

        // iterate over each letter in the word
        for (int start = 0; start < input.length(); start++) {
            // from each letter, keep going to find bad words until either the end of the sentence is reached, or the max word length is reached. 
            for (int offset = 1; offset < (input.length() + 1 - start) && offset < largestWordLength; offset++) {
                String wordToCheck = input.substring(start, start + offset);
                if (words.containsKey(wordToCheck)) {
                    // for example, if you want to say the word bass, that should be possible.
                    String[] ignoreCheck = words.get(wordToCheck);
                    boolean ignore = false;
                    for (int s = 0; s < ignoreCheck.length; s++) {
                        if (input.contains(ignoreCheck[s])) {
                            ignore = true;
                            break;
                        }
                    }
                    if (!ignore) {
                        badWords.add(wordToCheck);
                    }
                }
            }
        }

        for (String s : badWords) {
            System.out.println(s + " qualified as a bad word in a username");
        }
        return badWords;

    }

    public static int filterText(String input) {
        ArrayList<String> badWords = badWordsFound(input);
        if (badWords.size() > 0) {
            return 0;
        }
        return 1;
    }

}
