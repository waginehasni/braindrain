/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cours.services;

import edu.cours.entities.Cours;
import edu.cours.entities.Reservation;
import edu.cours.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class ReservationCRUD {
     Connection cn = MyConnection.getInstance().getConnection();
 
   
    public void ajouterReservation(Reservation r){
        String requete="INSERT INTO reservation (numReservation,numSalles,specialite,date,horraire,duree)" + "VALUES (?,?,?,?,?,?)";
    
        try {
          PreparedStatement pst =   MyConnection.getInstance().getConnection().prepareStatement(requete);            
             
        
           
            pst.setInt(1, r.getNumReservation());
            pst.setInt(2, r.getNumSalles());
            pst.setString(3, r.getSpecialite());
            pst.setDate(4, r.getDate());
             
            pst.setString(5 , r.getHorraire());
            pst.setString(6 , r.getDuree());
            
            pst.executeUpdate();
            System.out.println("reservation ajoutée");
        
        
        
        } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);  
        }
    } 
    
       
       
       
        public List<Reservation> listReservationbynum() {
        List<Reservation> ReservationList = new ArrayList<>();
        try {
            String requete = "select * from reservation";
          
            PreparedStatement ab =   MyConnection.getInstance().getConnection().prepareStatement(requete);
            ResultSet rs = ab.executeQuery(requete);
            while(rs.next()){
                Reservation r = new Reservation();
                r.setNumReservation(rs.getInt(1));
                r.setNumSalles(rs.getInt(2));
                r.setSpecialite(rs.getString(3));
                r.setDate(rs.getDate(4));
                r.setHorraire(rs.getString(5));
                r.setDuree(rs.getString(6));
                ReservationList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ReservationList;
    }
     
       
       
       
       
       
       
       
   
     public void modifierReservation (Reservation r) {
        try {
            String requete = "UPDATE reservation SET numReservation='"+r.getNumReservation()
                    + "',numSalles='"+r.getNumSalles()+ "',specialite='"+r.getSpecialite()
                    +"',date='"+r.getDate()+ "',horraire='"+r.getHorraire()+ "',duree='"+r.getDuree()
                    + "' WHERE numReservation=" + r.getNumReservation();
             PreparedStatement pst =   MyConnection.getInstance().getConnection().prepareStatement(requete);              
            int rowsUpdated = pst.executeUpdate(requete);
            if (rowsUpdated > 0) {
            }
           System.out.println("Reservation modifiée");
       } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimerReservation(int numReservation) {
        try {
            String requete= "DELETE FROM `reservation` WHERE `reservation`.`numReservation` = ? ";
            PreparedStatement pst =  new MyConnection().cn.prepareStatement(requete);                 
            pst.setInt(1, numReservation);
            pst.executeUpdate();
             System.out.println("Reservation supprimée");
      } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       public int deleteReservation(int numReservation) throws SQLException {
        int i = 0;
      
            Statement ste = cn.createStatement();
            String sql = "delete from reservation where numReservation=" + numReservation;
            i = ste.executeUpdate(sql);
        
        return i;
    }
    public void supprimer(Reservation reservation) {
        try {
            String requete= "DELETE FROM `reservation` WHERE `reservation`.`numReservation` = ? ";
     PreparedStatement pst =   MyConnection.getInstance().getConnection().prepareStatement(requete);                      
            pst.setInt(1, reservation.getNumReservation());
            pst.executeUpdate();
             System.out.println("Reservation supprimée");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public List<Reservation> trierReservationparNumeroSalle() {
         ArrayList<Reservation> listReservaion = new ArrayList<>();
         try {
            String requete = "Select * from reservation";
               PreparedStatement pst =   MyConnection.getInstance().getConnection().prepareStatement(requete); 
            ResultSet res = pst.executeQuery(requete);
            Reservation re=null;
            while(res.next()){
                re = new Reservation(res.getInt(1),res.getInt(2),res.getString(3),res.getDate(4),res.getString(5),res.getString(6));
                listReservaion.add(re);
            }
             Collections.sort(listReservaion, numSallesComparator);
             
             
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
         return listReservaion;
    }
   public static Comparator<Reservation> numSallesComparator = new Comparator<Reservation>() {

        @Override
	public int compare(Reservation r1, Reservation r2) {
            
            
            int numSalles1 = r1.getNumSalles();
         int numSalles2 = r2.getNumSalles();


           return numSalles1-numSalles2;

	  
    }
    };
    
    
    
    public ArrayList<Reservation> TrouveravecSpecialite(String specialite)  {
    ArrayList<Reservation> listReservation = new ArrayList<>();   
        try {
          String requete= "select * from reservation WHERE specialite='" + specialite + "' ";

          PreparedStatement pst =   MyConnection.getInstance().getConnection().prepareStatement(requete);             

             ResultSet res = pst.executeQuery("select * from reservation   WHERE specialite='" + specialite + "' ");
          Reservation com = null;
         if (res.next()) {
 
              com = new Reservation(res.getInt(1),res.getInt(2),res.getString(3),  res.getDate(4), res.getString(5),res.getString(6));

              listReservation.add(com);
            
        }
          else {
                   System.out.println("Cours non trouvé"); 
                  }
          
   
        
      } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listReservation ;
 
    } 
    
    
}
