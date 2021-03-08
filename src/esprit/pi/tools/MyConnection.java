/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pi.tools;


import esprit.pi.entities.Offres;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;


/**
 *
 * @author asus
 */
public class MyConnection {

   
public String url="jdbc:mysql://localhost:3306/gestion offres";  
 String login="root";
    String pwd="";
    Connection cnx;
    public static MyConnection instance;
    
    private MyConnection() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
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
            
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion offres","root","");
           // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
     public static ObservableList<Offres> getDataOffres(){
        Connection conn = ConnectDb();
        ObservableList<Offres> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from offres");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Offres(rs.getInt("idOffre") ,rs.getString("nomOffre"), rs.getString("type"),rs.getString("description")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
      public static ObservableList<Offres> getDataSearchOffres(){
        Connection conn = ConnectDb();
        ObservableList<Offres> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from offres where type=?");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Offres(rs.getString("nomOffre"),rs.getString("type"),rs.getString("description")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
     
//          public static ObservableList<Reservation> getDataReservation(){
//        Connection conn = ConnectDb();
//        ObservableList<Reservation> list = FXCollections.observableArrayList();
//        try {
//            PreparedStatement ps = conn.prepareStatement("select * from reservation");
//            ResultSet rs = ps.executeQuery();
//            
//            while (rs.next()){   
//                list.add(new Reservation(rs.getInt(1),rs.getInt(2),rs.getInt(3)));              
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }

    public Connection getConnection() {
         return cnx ;

    }





}
  
