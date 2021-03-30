/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cours.test;

 
import edu.cours.entities.Cours;
import edu.cours.entities.Reservation;
import edu.cours.services.CoursCRUD;
import edu.cours.services.ReservationCRUD;
import edu.cours.tools.MyConnection;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class MainClass {
    public static void main(String[] args)  {
        
       //  CoursCRUD ccd = new CoursCRUD();
      // Cours c2 = new Cours (852,11,"hand","hamza","prive",77);
        //  ccd.getAll().forEach(e->System.out.println(e));
      //  ccd.ajouterCours(c2);
         //ccd.supprimerCours(33);
        // ccd.supprimer(c2);
         // ccd.modifiercours(c2); 
          
         // ccd.TrouveravecNomCours("football ").forEach(e->{System.out.println(e);});
       // ccd.trierCoursparNumCours().forEach(e->{System.out.println(e);});  

      
      /******************************/
   ReservationCRUD scd = new ReservationCRUD();
    String str ="2021-07-30";
   Date date1 = Date.valueOf(str);
     
    Reservation s1 = new Reservation(99,888,"football",date1,"15h","16h");
    
    
            
       //scd.ajouterReservation(s1);
        //scd.supprimerSalle(27);
       //scd.supprimer(s1);
       
      
       //scd.modifierReservation( s1);
     
     scd.listReservationbynum1().forEach(e->{System.out.println(e);});
     // scd.TrouveravecSpecialite("tenniss").forEach(e->{System.out.println(e);});
     // scd.trierreservationdate().forEach(e->{System.out.println(e);}); 

       
 }
}