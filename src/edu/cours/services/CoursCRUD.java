/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cours.services;

import com.sun.webkit.LoadListenerClient;
 
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
public class CoursCRUD {
    Connection cn = MyConnection.getInstance().getConnection();

   
    public void ajouterCours(Cours c){
        String requete="INSERT INTO Cours (numCours,numReservation,nomCours,nomCoach,type)" + "VALUES (?,?,?,?,?)";
    
        try {
          PreparedStatement pst =   MyConnection.getInstance().getConnection().prepareStatement(requete);            
             
        
           
            pst.setInt(1, c.getNumCours());
            pst.setInt(2, c.getNumReservation());
            pst.setString(3, c.getNomCours());
            pst.setString(4, c.getNomCoach());
             
            pst.setString(5 , c.getType());
            
            pst.executeUpdate();
            System.out.println("Cours ajouté");
        
        
        
        } catch (SQLException ex) {
            Logger.getLogger(CoursCRUD.class.getName()).log(Level.SEVERE, null, ex);  
        }
    } 
     public java.util.List<Cours> getAll() {
        ArrayList<Cours> listCours = new ArrayList<>();
        try {
          String requete= "GETALL FROM `cours` WHERE `cours`.`numCours` = ? ";

            PreparedStatement pst =   MyConnection.getInstance().getConnection().prepareStatement(requete);  //GETINSTANCE est utilisée pour renvoyer un objet Signature qui implémente l'algorithme de signature spécifié              
            ResultSet rs = pst.executeQuery("Select * from cours"); //EXECUTEQUERY  Exécute l'instruction SQL donnée et renvoie un seul objet SQLServerResultSet
            while (rs.next()) {
                listCours.add(new Cours(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                       
                        rs.getString(5)

                ));
                           
            }
          pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(CoursCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCours;
    }
       
       
     
        public List<Cours> listCoursbynumc() {
        List<Cours> CoursList = new ArrayList<>();
        try {
            String requete = "select * from cours";
          
            PreparedStatement ab =   MyConnection.getInstance().getConnection().prepareStatement(requete);
            ResultSet rs = ab.executeQuery(requete);
            while(rs.next()){
                Cours r = new Cours();
                r.setNumCours(rs.getInt(1));
                r.setNumReservation(rs.getInt(2));
                r.setNomCours(rs.getString(3));
                r.setNomCoach(rs.getString(4));
                r.setType(rs.getString(5));
             
                CoursList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return CoursList;
    }  
       
       
       
       
       
       
       
       
       
   
     public void modifiercours (Cours e) {
        try {
            String requete = "UPDATE cours SET numCours='"+e.getNumCours()
                    + "',numReservation='"+e.getNumReservation()+ "',nomCours='"+e.getNomCours()
                    +"',nomCoach='"+e.getNomCoach()+ "',type='"+e.getType()
                    + "' WHERE numCours=" + e.getNumCours();
             PreparedStatement pst =   MyConnection.getInstance().getConnection().prepareStatement(requete);              
            int rowsUpdated = pst.executeUpdate(requete);
            if (rowsUpdated > 0) {
            }
           System.out.println("Cours modifié");
       } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimerCours(int numCours) {
        try {
            String requete= "DELETE FROM `cours` WHERE `cours`.`numCours` = ? ";
            PreparedStatement pst =  new MyConnection().cn.prepareStatement(requete);                 
            pst.setInt(1, numCours);
            pst.executeUpdate();
             System.out.println("Cours supprimé");
      } catch (SQLException ex) {
            Logger.getLogger(CoursCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int deleteCours(int numCours) throws SQLException {
        int i = 0;
      
            Statement ste = cn.createStatement();
            String sql = "delete from cours where numCours=" + numCours;
            i = ste.executeUpdate(sql);
        
        return i;
    }
    
    
    public void supprimer(Cours cours) {
        try {
            String requete= "DELETE FROM `cours` WHERE `cours`.`numCours` = ? ";
     PreparedStatement pst =   MyConnection.getInstance().getConnection().prepareStatement(requete);                      
            pst.setInt(1, cours.getNumCours());
            pst.executeUpdate();
             System.out.println("Cours supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(CoursCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Cours> TrouveravecNomCours(String nomCours)  {
    ArrayList<Cours> listCours = new ArrayList<>();   
        try {
          String requete= "select * from cours WHERE nomCours='" + nomCours + "' ";

          PreparedStatement pst =   MyConnection.getInstance().getConnection().prepareStatement(requete);             

          ResultSet res = pst.executeQuery("select * from cours   WHERE nomCours='" + nomCours + "' ");
          Cours com = null;
         if (res.next()) {
 
              com = new Cours(res.getInt(1),res.getInt(2),res.getString(3),  res.getString(4), res.getString(5));

              listCours.add(com);
            
        }
          else {
                   System.out.println("Cours non trouvé"); 
                  }
          
   
        
      } catch (SQLException ex) {
            Logger.getLogger(CoursCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCours ;
 
    } 
    
    
    
        public List<Cours> trierCoursparNumCours() {
         ArrayList<Cours> listCours = new ArrayList<>();
         try {
            String requete = "Select * from cours";
               PreparedStatement pst =   MyConnection.getInstance().getConnection().prepareStatement(requete); 
            ResultSet res = pst.executeQuery(requete);
          Cours re=null;
            while(res.next()){
                re = new Cours(res.getInt(1),res.getInt(2),res.getString(3),res.getString(4),res.getString(5));
                listCours.add(re);
            }
             Collections.sort(listCours, NouCoursComparator);
             
             
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
         return listCours;
    }
   public static Comparator<Cours> NouCoursComparator = new Comparator<Cours>() {

        @Override
	public int compare(Cours r1, Cours r2) {
            
            
         int numCours1 = r1.getNumCours();
       int numCours2 = r2.getNumCours();


           return numCours1-numCours2;

	  
    }
    };
    
           
         
         
         
    
}
    
   
    
    
    
    
 

    

