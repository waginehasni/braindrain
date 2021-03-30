/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.services;

/**
 *
 * @author infoevo
 */
import com.pidev.models.evenement;
import com.pidev.models.fos_user;
import com.pidev.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Service_Evenement {
    private Statement st;
    private ResultSet rs;
    Connection cnx = DataSource.getInstance().getCnx();
    public Service_Evenement()
    {
           DataSource cs= DataSource.getInstance();
        try {
            st= cs.getCnx().createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
public void Ajouterevent(evenement e) {
        try {
            String req = "INSERT INTO evenement (id_evenement,num_salle,nom_offre,date_debut,date_fin,specialite,nom) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, e.getId_evenement());
            st.setInt(2, e.getNum_salle());
            //st.setString(6, u.getSalt());
            st.setString(3, e.getNom_offre());
            //st.setDate(8, u.getLast_login());
            //st.setDate(10, u.getPassword_requested_at());
            st.setDate(4, e.getDate_debut());
            st.setDate(5, e.getDate_fin());
            st.setString(6, e.getSpecialite());
            st.setString(7, e.getNom());

            st.executeUpdate();
            System.out.println("evenement ajouté !!");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ModiferEvenement(int id, evenement e) {
        try {
            System.out.println("-------"+e.getId_evenement());
            String req = "UPDATE evenement SET id_evenement = ?, num_salle= ?, nom_offre=?, date_debut=?,date_fin=?,specialite=?,nom=?  " 
                    + " WHERE id_evenement = " + id + ";";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, e.getId_evenement());
            st.setInt(2, e.getNum_salle());
            st.setString(3, e.getNom_offre());
            st.setDate(4, e.getDate_debut());
            st.setDate(5, e.getDate_fin());
            st.setString(6, e.getSpecialite());
            st.setString(7, e.getNom());
            st.executeUpdate();
            System.out.println("evenement modilfer !!");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

 public void SupprimerEvenement(int id) {
        try {
            String req = "DELETE FROM evenement WHERE evenement.`id_evenement` = ? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("evenement supprimé !!");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

  
   public void registerEvenement(evenement e) {
        try {
            String req = " INSERT INTO evenement (id_evenement,num_salle,nom_offre,date_debut,date_fin,specialite,nom) VALUES (?,?,?,?,?,?,?)\";\n" +"";

            PreparedStatement st = cnx.prepareStatement(req);
             st.setInt(1, e.getId_evenement());
            st.setInt(2, e.getNum_salle());
            st.setString(3, e.getNom_offre());
            st.setDate(4, e.getDate_debut());
            st.setDate(5, e.getDate_fin());
            st.setString(6, e.getSpecialite());
            st.setString(7, e.getSpecialite());
            st.executeUpdate();
            System.out.println("evenement Ajouté !!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  
  
  
   
   public List<evenement> listeventid() {
        List<evenement> eventList = new ArrayList<>();
        try {
            String requete = "select * from evenement";
             st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                evenement a = new evenement();
                a.setId_evenement(rs.getInt(1));
                a.setNum_salle(rs.getInt(2));
                a.setNom_offre(rs.getString(3));
                a.setDate_debut(rs.getDate(4));
                 a.setDate_fin(rs.getDate(5));
                
                a.setSpecialite(rs.getString(6));
                a.setNom(rs.getString(7));
                eventList.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return eventList;
    }
   
   
   public List<evenement> getAllEvenement() {
        ArrayList<evenement> listN = new ArrayList<evenement>();
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery("Select * from evenement");
            while (rs.next()) {
                System.out.println("id_evenement " + rs.getString(1) + "contenu  " + rs.getString(4));
                listN.add(new evenement(
                        rs.getInt("id_evenement"),
                        rs.getInt("num_salle"),
                        rs.getString("nom_offre"),
                        rs.getDate("date_debut"),
                        rs.getDate("date_fin"),
                  
                        rs.getString("specialite"),
                         rs.getString("nom")
                ));
            }
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(fos_user.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listN;
    }
   
    
 

    public evenement AfficherEvenement(String specialite) {
        List<evenement> list = new ArrayList<>();
evenement e = new evenement(); 
        try {
            String requete = "Select * from evenement where evenement.`specialite`='"+specialite+"'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) 
            {
                 e.setId_evenement(rs.getInt("id_evenement"));
                        e.setNum_salle(rs.getInt("num_salle"));
                        e.setNom_offre(rs.getString("nom_offre"));
                        e.setDate_debut(rs.getDate("date_debut"));
                        e.setDate_fin(rs.getDate("date_fin"));
                     
                        e.setSpecialite(rs.getString("specialite"));
                        e.setSpecialite(rs.getString("nom"));

                               
            }
        } catch (SQLException ex) 
        {
            System.err.println(ex.getMessage());
        }

        return e;
    }
public List<evenement> trierevenementDateDebut() {
        ArrayList<evenement> listAbonnementTypeX = new ArrayList<>();
        try {
          String req = "Select * from evenement";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                evenement a = new evenement();
                a.setNum_salle(rs.getInt(2));
                a.setNom_offre(rs.getString(3));
                a.setDate_debut(rs.getDate(4));
                a.setDate_fin(rs.getDate(5));
                a.setSpecialite(rs.getString(6));
                a.setNom(rs.getString(7));
                listAbonnementTypeX.add(a);
                Collections.sort(listAbonnementTypeX,evenementComparatorDateDebut);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if  (listAbonnementTypeX.isEmpty()) {
            System.out.println("Liste evenement Vide");
        }
        return listAbonnementTypeX;
    }
public static Comparator<evenement> evenementComparatorDateDebut = (evenement s1, evenement s2) -> {
        Date d1 = s1.getDate_debut();
        Date d2 = s2.getDate_debut();
        return d1.compareTo(d2);
    };
    
}
