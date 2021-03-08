/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pi.services;

import esprit.pi.entities.Offres;
import esprit.pi.entities.Sponsors;
import esprit.pi.tools.MyConnection;
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
 * @author asus
 */

public class OffresCRUD {
    Connection cnx = MyConnection.getInstance().getCnx();
      public void ajouterOffres(Offres of){
        String requete="INSERT INTO Offres (nomOffre,type,description)"+  "VALUES (?,?,?)";
    
        try {
            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);            
           
           
            pst.setString(1, of.getNomOffre());
            pst.setString(2, of.getType());
            pst.setString(3, of.getDescription());
            
            pst.executeUpdate();
            System.out.println("Offre ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      }
        

    public void supprimerOffres(Offres of,int y) {
        try {
            String requete = "DELETE FROM offres where idOffre=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, y);
            pst.executeUpdate();
            System.out.println("Offre supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
     public void supprimerOffres2(Offres of) {
        try {
            String requete = "DELETE FROM offres where nomOffre=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, of.getNomOffre());
            pst.executeUpdate();
            System.out.println("Offre supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

  
//    public void updateOffres(Offres of) {
//        try {
//            String requete = "UPDATE offres SET nomOffre=? WHERE idOffre=?";
//            PreparedStatement pst;
//            pst = MyConnection.getInstance().getCnx()
//                    .prepareStatement(requete);
//            pst.setString(1, of.getNomOffre());
//            pst.setInt(2, of.getIdOffre());
//            pst.executeUpdate();
//            System.out.println("Offre modifié");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
    public void  updateOffres(Offres t,int y) {
        try {
            String requete = "UPDATE offres SET nomOffre='"+t.getNomOffre()
                    +  "',type='"+t.getType()
                    +"',description='"+t.getDescription()
                    + "' WHERE idOffre=" + y; 
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

    
//    public List<Offres> displayOffres() {
//         List<Offres> OffresList = new ArrayList<>();
//        try {
//            String requete = "SELECT * FROM offres";
//            Statement st;
//             st = MyConnection.getInstance().getCnx()
//                     .createStatement();
//            ResultSet rs =  st.executeQuery(requete);
//            while(rs.next())
//            {
//                Offres  of = new Offres();
//                of.setIdOffre(rs.getInt("idOffre"));
//                of.setNomOffre(rs.getString("nomOffre"));
//                of.setType(rs.getString("Type"));
//                of.setDescription(rs.getString("description"));
//                OffresList.add(of);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return OffresList;
//    }
    
    
     public List<Offres> listOffresid() {
        List<Offres> offresList = new ArrayList<>();
        try {
            String requete = "select * from offres";
            Statement ab = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = ab.executeQuery(requete);
            while(rs.next()){
                Offres f = new Offres();
                f.setIdOffre(rs.getInt(1));
                f.setNomOffre(rs.getString(2));
                f.setType(rs.getString(3));
                f.setDescription(rs.getString(4));
                
                offresList.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return offresList;
    }
    
    
    
    
    
    
    
    
    
    
    public ArrayList<Offres> TrierParId() {
       ArrayList<Offres> listOffres = new ArrayList<>();
       try {
        
           String requete= "select * from offres ORDER BY idOffre DESC"; 
           PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
           
           
           ResultSet res = pst.executeQuery(requete);
        Offres fr = null;
        while (res.next()) {
            fr = new Offres(res.getInt(1),res.getString(2),res.getString(3),res.getString(4));
            listOffres.add(fr);
            
        } 
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                 return listOffres ;
    
}

     
    public List<Offres> RechercherParType(String x) {
        ArrayList<Offres> listOffresTypeX = new ArrayList<>();
        try {
          String req = "Select * from offres";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Offres fr = new Offres();
               fr.setIdOffre(rs.getInt(1));
                fr.setNomOffre(rs.getString(2));
                fr.setType(rs.getString(3));
                fr.setDescription(rs.getString(4));
                if (fr.getType().compareTo(x)==0){
                    listOffresTypeX.add(fr);
                }
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if  (listOffresTypeX.isEmpty()) {
            System.out.println("Il y a aucun offre de ce type");
        }
        return listOffresTypeX;
    }
    
    
    // AFFICHER NOMBRE TOTAL DES OFFRES

    public int nbOffresTotal() {
        int nbtotal = 0;
        try {
          String req = "Select * from offres";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                nbtotal = nbtotal +1;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if  (nbtotal==0) {
            System.out.println("Il y a aucun offre");
        }
        return nbtotal;
    }
    public int deleteOffres(int idOffre) throws SQLException {
        int i = 0;
       
            Statement ste = cnx.createStatement();
            String sql = "delete from offres where idOffre=" + idOffre;
            i = ste.executeUpdate(sql);
       
        return i;
    }
    
      // nombre total des offres selon type
        public int nbOffresTypeX(String x) {
        int nb=0;
        try {
          String req = "Select * from offres";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Offres of = new Offres();
                of.setIdOffre(rs.getInt(1));
                of.setNomOffre(rs.getString(2));
                of.setType(rs.getString(3));
                of.setDescription(rs.getString(4));
                
                if(of.getType().compareTo(x)==0){
                    nb=nb+1;
                }  
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if  (nb==0) {
            System.out.println("Il y a aucun offre de ce type");
        }
        return nb;
    }

    public void listOffresid(Offres of1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
     
    
    
    
 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}   
        
        
        
        
        
        
        
        
        
        
    


