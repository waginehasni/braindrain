/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.services;

import com.pidev.models.rating;
import com.pidev.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author infoevo
 */
public class ServiceRating {
    private Statement st;
    private ResultSet rs;
    Connection cnx = DataSource.getInstance().getCnx();
    public ServiceRating()
    {
           DataSource cs= DataSource.getInstance();
        try {
            st= cs.getCnx().createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
    public void Ajouterrating(rating r) {
        try {
            String req = "INSERT INTO rating (nom,rating) VALUES (?,?)";

            PreparedStatement st = cnx.prepareStatement(req);
            
            
            st.setString(1, r.getNom());
            st.setString(2, r.getRating());

            st.executeUpdate();
            System.out.println("rating ajouté !!");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Supprimerrating(String nom) {
        try {
            String req = "DELETE FROM rating WHERE rating.`nom` = ? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, nom);
            st.executeUpdate();
            System.out.println("rating supprimé !!");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<rating> getAllrating() {
        ArrayList<rating> listN = new ArrayList<rating>();
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery("Select * from rating");
            while (rs.next()) {
                System.out.println("nom " + rs.getString(1) + "contenu  " + rs.getString(2));
                listN.add(new rating(
                       
                        rs.getString("nom"),
                        
                         rs.getString("rating")
                ));
            }
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(rating.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listN;
    }
    
    
}
