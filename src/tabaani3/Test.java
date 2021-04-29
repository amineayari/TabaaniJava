/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani3;

import entite.Promotion;
import entite.Voyage;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import services.PromotionService;
import services.VoyageService;

/**
 *
 * @author House_Info
 */
public class Test {

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws ParseException {
//        PromotionService as = new PromotionService();
////        Date d1=new Date(02-02-2021);
//        Date d2=new Date(0);
//////        Date d3=new Date(120,1,1);
////// Date d4=new Date(555555555);
//String x="1992-03-05";
//
//        Promotion p1 = new Promotion(788, 222,d2.valueOf(x),d2.valueOf(x));
//////        Promotion p2= new Promotion(22,33,d2,d2);
////        
//        as.ajouter(p1);
//        System.out.println(d3);
//        System.out.println(d3);
//        as.ajouter(p1);
//as.supprimer(new Promotion(54));
//as.modifier (new Promotion(53, 30, 500));
//       System.out.println("//////////////// trie ////////////////");
//     as.triPrix().forEach(System.out::println);
//        System.out.println("***************** recherche *****************");
//        as.RecherchePourcentage(15.0).forEach(System.out::println);
        // TODO code application logic here
////        java.util.Date date=new java.util.Date();
////        java.sql.Date d=new java.sql.Date(date.getTime());
       VoyageService voy = new VoyageService();
////Voyage v1=new Voyage("sousse", "bkj", "bkjjk", 300,d,d, "tunis", "bjhb", "bjjjbj", "hbjnb ", 12.54, 13.24);
////voy.ajouter(v1);
////voy.afficher().forEach(System.out::println);
voy.modifier(new Voyage(40, "tunis", "bkj", "bkj jk", 900, "tunis", "bjhb", "bjjjbj", "hbjnb ", 12.54, 13.24));
////////   voy.supprimer (new Voyage(25));
////    }

}
}
