/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pi.services;

import esprit.pi.entities.Offres;
import esprit.pi.entities.Sponsors;
import esprit.pi.tools.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author asus
 */
public class SponsorsCRUD {
    public void ajouterSponsors(Sponsors s){
        String requete="INSERT INTO Sponsors (societe,budget,numtelephone)" + "VALUES (?,?,?)";
    
        try {
          PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);                 // howa el boustaji eli yhez requete ywasalha  lel sever w men server yjib el rep   nasn3ou a travers el cn  +s
            ///singloton c'st un patron de conception maykhalinich naamel dima new haja il nous permet d'optenir une seule instance de la classe dans la memoire  
        
           
            
            pst.setString(1, s.getSociete());
            pst.setFloat(2, s.getBudget());
            pst.setInt(3, s.getNumtelephone());         
            
            
            
            pst.executeUpdate();
            System.out.println("Sponsor ajouté");
        
        
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
 
     
    public void supprimerSponsors(Sponsors s) {
        try {
            String requete = "DELETE FROM sponsors where idSponsor=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, s.getIdSponsor());
            pst.executeUpdate();
            System.out.println("Sponsor supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

  
//    public void updateSponsors(Sponsors s) {
//        try {
//            String requete = "UPDATE sponsors SET societe=? WHERE idSponsor=?";
//            PreparedStatement pst;
//            pst = MyConnection.getInstance().getCnx()
//                    .prepareStatement(requete);
//            pst.setString(1, s.getSociete());
//            pst.setInt(2, s.getIdSponsor());
//            pst.executeUpdate();
//            System.out.println("Sponsor modifié");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

     public void  updateSponsors(Sponsors t,int y) {
        try {
            String requete = "UPDATE sponsors SET societe='"+t.getSociete()
                    +  "',budget='"+t.getBudget()
                    +"',numtelephone='"+t.getNumtelephone()
                    + "' WHERE idSponsor=" + y; 
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            int rowsUpdated = pst.executeUpdate(requete);
            if (rowsUpdated > 0) {
                System.out.println("La modification a été éffectué avec succée ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
    
//    public List<Sponsors> displaySponsors() {
//         List<Sponsors> SponsorsList = new ArrayList<>();
//        try {
//            String requete = "SELECT * FROM sponsors";
//            Statement st;
//             st = MyConnection.getInstance().getCnx()
//                     .createStatement();
//            ResultSet rs =  st.executeQuery(requete);
//            while(rs.next()){
//                Sponsors  s = new Sponsors();
//                s.setIdSponsor(rs.getInt("idSponsor"));
//                s.setSociete(rs.getString("societe"));
//                s.setBudget(rs.getFloat("budget"));
//                s.setNumtelephone(rs.getInt("numtelephone"));
//                SponsorsList.add(s);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return SponsorsList;
//    }
    
       public List<Sponsors> listSponsorsid() {
        List<Sponsors> SponsorsList = new ArrayList<>();
        try {
            String requete = "select * from sponsors";
            Statement ab = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = ab.executeQuery(requete);
            while(rs.next()){
                Sponsors s = new Sponsors();
                s.setIdSponsor(rs.getInt(1));
                s.setSociete(rs.getString(2));
                s.setBudget(rs.getFloat(3));
                s.setNumtelephone(rs.getInt(4));
                
                SponsorsList.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return SponsorsList;
    }
    
    
     public List<Sponsors> RechercherParSociete(String x) {
        ArrayList<Sponsors> listSponsorsTypeX = new ArrayList<>();
        try {
          String req = "Select * from sponsors";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Sponsors s = new Sponsors();
                s.setIdSponsor(rs.getInt(1));
                s.setSociete(rs.getString(2));
                s.setBudget(rs.getFloat(3));
                s.setNumtelephone(rs.getInt(4));
                if (s.getSociete().compareTo(x)==0){
                    listSponsorsTypeX.add(s);
                }
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if  (listSponsorsTypeX.isEmpty()) {
            System.out.println("Il y a aucune societe de ce nom");
        }
        return listSponsorsTypeX;
    }
    
    
    
    

public ArrayList<Sponsors> TrierParBudget() {
       ArrayList<Sponsors> listSponsors = new ArrayList<>();
       try {
        
           String requete= "select * from sponsors ORDER BY budget DESC"; 
           PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
           
           
           ResultSet res = pst.executeQuery("select * from sponsors ORDER BY budget DESC");
        Sponsors sp = null;
        while (res.next()) {
            sp = new Sponsors(res.getInt(1),res.getString(2),res.getFloat(3),res.getInt(4));
            listSponsors.add(sp);
            
        }
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                 return listSponsors ;
    
}

   public Float budgetSponsors() {
        float nb=0;
        try {
          String req = "Select * from sponsors";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                nb=nb+rs.getFloat(3);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if  (nb==0) {
            System.out.println("Le budget est 0");
        }
        return nb;
    }
    

























}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

