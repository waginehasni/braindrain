package services;

import entities.Reclamation;
import interfaces.IReclamation;
import tools.DataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReclamationCRUD implements IReclamation<Reclamation> {


    @Override
    public void ajouterReclamation(Reclamation r) {
        try {
            String requete = "INSERT INTO `reclamation`(`idUser`, `titre`, `description`, `etat`, `dateReclamation`, `image`) VALUES (?,?,?,?,?,?)";
            //String requete = "INSERT INTO reclamation(idUser,titre,description,etat,dateReclamation,image)" +" VALUES ('11','"+"','"+"'"+r.getTitre()+"','"+r.getDescription()+"','actif','"+new Date(r.getDateReclamation().getTime())+"','"+r.getImage()+"')";
            PreparedStatement st =  DataSource.getInstance().getConnection().prepareStatement(requete) ;
            st.setInt(1, 12);
            st.setString(2, r.getTitre());
            st.setString(3, r.getDescription());
            st.setString(4, "actif");
            st.setDate(5, new Date(r.getDateReclamation().getTime()));
            st.setString(6, r.getImage());
            st.executeUpdate();
            System.out.println("Reclamation ajoutée");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public void supprimerReclamation(int id) {
        try {
            String requete = "DELETE FROM reclamation where id=?";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete) ;
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Réclamation supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    @Override
    public void updateReclamation(Reclamation r) {
        try {
            String requete = "UPDATE reclamation SET titre=?,description=?,image=? WHERE id=?";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete) ;
            pst.setString(1, r.getTitre());
            pst.setString(2, r.getDescription());            
            pst.setString(3, r.getImage());
            pst.setInt(4, r.getId());
            pst.executeUpdate();
            System.out.println("Réclamation modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void updateReclamationRepondre(Reclamation r) {
        try {
            String requete = "UPDATE reclamation SET id=?,idUser=?,titre=?,description=?,dateReponse=?,etat=?,reponse=?,dateReclamation=?,satisfaction=? WHERE id=?";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete) ;
            pst.setInt(1, r.getId());
            pst.setInt(2, r.getIdUser());
            pst.setString(3, r.getTitre());
            pst.setString(4, r.getDescription());
            pst.setDate(5, new Date(r.getDateReponse().getTime()));
            pst.setString(6, r.getEtat());
            pst.setString(7, r.getReponse());
            pst.setDate(8, new Date(r.getDateReclamation().getTime()));    
            System.out.println(r.getSatisfaction());
            pst.setString(9, r.getSatisfaction());
            pst.setInt(10, r.getId());
            pst.executeUpdate();
            System.out.println("Réclamation modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void updateReclamationSatisfaction(Reclamation r) {
        try {
            System.out.println(r.getSatisfaction());
            String requete = "UPDATE `reclamation` SET `satisfaction`=? WHERE id=?";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete) ;
            pst.setString(1, r.getSatisfaction());
            pst.setInt(2, r.getId());
            pst.executeUpdate();
            //System.out.println("Réclamation modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Reclamation> displayReclamations() {
        List<Reclamation> recList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reclamation where etat = 'cloturé'";
            PreparedStatement st = DataSource.getInstance().getConnection().prepareStatement(requete) ;
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));
                r.setIdUser(rs.getInt("idUser"));
                //r.setTitre(rs.getString(2));
                r.setTitre(rs.getString("titre"));
                r.setDescription(rs.getString("description"));
                r.setEtat(rs.getString("etat"));
                r.setReponse(rs.getString("reponse"));
                r.setDateReponse(rs.getDate("dateReponse"));
                r.setDateReclamation(rs.getDate("dateReclamation"));
                
                recList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return recList;
    }
    @Override
    public  ObservableList<Reclamation> chercherReclamationActif(String titre){
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        try {
            String requete= "select * from reclamation where etat = 'actif' and titre like '"+titre+"%' ORDER BY id ASC";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            ResultSet res = pst.executeQuery(requete);
            Reclamation reclamation = null;
            while (res.next()) {
                
                reclamation = new Reclamation(res.getInt(1),res.getInt(2), res.getString(3), res.getString(4),res.getString(6),res.getString(7),res.getDate(5),res.getDate(8),res.getString(10),res.getString(9));
                list.add(reclamation);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public  ObservableList<Reclamation> chercherReclamationcloture(String titre){
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        try {
            String requete= "select * from reclamation where etat = 'cloturé' and titre like '"+titre+"%' ORDER BY id ASC";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            ResultSet res = pst.executeQuery(requete);
            Reclamation reclamation = null;
            while (res.next()) {
                reclamation = new Reclamation(res.getInt(1),res.getInt(2), res.getString(3), res.getString(4),res.getString(6),res.getString(7),res.getDate(5),res.getDate(8),res.getString(10),res.getString(9));
                list.add(reclamation);

            }
        } catch (Exception e) {
        }
        return list;
    }
    @Override
    public Reclamation FindReclamationById(int id) {
        Reclamation reclamation = null;
        try {
            String requete = "select * from reclamation WHERE id='" + id + "' ";

            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);

            ResultSet res = pst.executeQuery(requete);
            while (res.next()) {
                reclamation = new Reclamation(res.getInt(1), res.getString(2), res.getString(3));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AbonnementCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reclamation;
    }
    @Override
    public ArrayList<Reclamation> TrierParTitre() {
        ArrayList<Reclamation> listReclamations = new ArrayList<>();
        try {
            String requete= "select * from reclamation ORDER BY titre DESC";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            ResultSet res = pst.executeQuery(requete);
            Reclamation reclamation = null;
            while (res.next()) {
                reclamation = new Reclamation(res.getInt(1), res.getString(2), res.getString(3));
                listReclamations.add(reclamation);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AbonnementCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listReclamations ;

    }
   public static ObservableList<Reclamation> getAll(){
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        try {
            String requete= "select * from reclamation where etat = 'actif' ORDER BY id ASC";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            ResultSet res = pst.executeQuery(requete);
            Reclamation reclamation = null;
            while (res.next()) {
                reclamation = new Reclamation(res.getInt(1),res.getInt(2), res.getString(3), res.getString(4),res.getString(9),res.getString(6),res.getString(7),res.getDate(5),res.getDate(8),res.getString(9));
                list.add(reclamation);

            }
        } catch (Exception e) {
        }
        return list;
    }
   public static ObservableList<Reclamation> getAllCloture(){
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        try {
            String requete= "select * from reclamation where etat = 'cloturé' and idUser=11 ORDER BY id ASC";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            ResultSet res = pst.executeQuery(requete);
            Reclamation reclamation = null;
            while (res.next()) {
                System.out.println(res.getString(9));
                reclamation = new Reclamation(res.getInt(1),res.getInt(2), res.getString(3), res.getString(4),res.getString(6),res.getString(7),res.getDate(5),res.getDate(8),res.getString(10),res.getString(9));
                System.out.println(reclamation.getSatisfaction());
                list.add(reclamation);

            }
        } catch (Exception e) {
        }
        return list;
    }
    public static ObservableList<Reclamation> getAllClotureAdmin(){
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        try {
            String requete= "select * from reclamation where etat like '%cloturé%' ORDER BY id ASC";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            ResultSet res = pst.executeQuery(requete);
            Reclamation reclamation = null;
            while (res.next()) {
                System.out.println(res.getString(9));
                reclamation = new Reclamation(res.getInt(1),res.getInt(2), res.getString(3), res.getString(4),res.getString(6),res.getString(7),res.getDate(5),res.getDate(8),res.getString(10),res.getString(9));
                System.out.println(reclamation.getSatisfaction());
                list.add(reclamation);

            }
        } catch (Exception e) {
        }
        return list;
    }
   
   public static int countSatsifiedClients(){
       try {
            String requete= "SELECT COUNT(*) FROM `reclamation` WHERE satisfaction like 'satisfait'";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            ResultSet res = pst.executeQuery(requete);            
               //  System.out.println("0 : "+res.getInt(1));
             while (res.next()) {
                 return res.getInt(1) ;               
             }
       } catch (Exception e) {
        }
       return 0 ;
       
   }
   public static int countNotSatsifiedClients(){
       try {
            String requete= "SELECT COUNT(*) FROM `reclamation` WHERE satisfaction like 'non satisfait'";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            ResultSet res = pst.executeQuery(requete);            
               //  System.out.println("0 : "+res.getInt(1));
             while (res.next()) {
                 return res.getInt(1) ;               
             }
       } catch (Exception e) {
        }
       return 0 ;
       
   }
   public static int countAllReclamations(){
       try {
            String requete= "SELECT COUNT(*) FROM `reclamation` WHERE etat = 'cloturé'";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            ResultSet res = pst.executeQuery(requete);            
               //  System.out.println("0 : "+res.getInt(1));
             while (res.next()) {
                 return res.getInt(1) ;               
             }
       } catch (Exception e) {
        }
       return 0 ;
       
   }
   public void saveImagePath(){
       
   }
}
