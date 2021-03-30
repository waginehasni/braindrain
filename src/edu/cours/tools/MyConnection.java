/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cours.tools;

import edu.cours.entities.Cours;
import edu.cours.entities.Reservation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class MyConnection {
  public String url="jdbc:mysql://localhost:3306/Pi";  
  public String login="root";  
  public String pwd=""; 
  public Connection cn;
  private static MyConnection instance;

    public MyConnection() {
   
      try {
         cn= DriverManager.getConnection(url, login,pwd);
         System.out.println("Connexion etablie");
      } catch (SQLException ex) {
        System.out.println("Erreur de Connexion ");
        System.out.println(ex.getMessage());
        
        
         
      }
    }
  
  
  public Connection getConnection(){
  
  return cn;
  
 }
  
 public static MyConnection getInstance(){
 
 if(instance == null){
     
     instance = new MyConnection();
 }
 return instance;
 
 
 }
 Connection conn = null;
    public static Connection ConnectDb(){
        try {
            
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pi","root","");
           // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
 public static ObservableList<Cours> getDataCours(){
        Connection conn = ConnectDb();
        ObservableList<Cours> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from cours");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Cours(rs.getInt("numCours") ,rs.getInt("numReservation"), rs.getString("nomCours"), rs.getString("nomCoach"), rs.getString("type"), rs.getInt("prix")));               
            }
        } catch (Exception e) {
        }
        return list;
    } 
   public static ObservableList<Reservation> getDataReservarion(){
        Connection conn = ConnectDb();
        ObservableList<Reservation> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from reservation");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Reservation(rs.getInt("numReservation") ,rs.getInt("numSalles"), rs.getString("specialite"), rs.getDate("date"),rs.getString("horraire"), rs.getString("duree")));               
            }
        } catch (Exception e) {
        }
        return list;
    } 
  
}
