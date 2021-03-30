package services;

import entities.Abonnement;
import interfaces.IAbonnement;
import tools.DataSource;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AbonnementCRUD implements IAbonnement<Abonnement> {
    public AbonnementCRUD() {
    }

    @Override
    public void ajouterAbonnement(Abonnement a) {
        try {
            String req = "INSERT INTO abonnement(type,dateCreation,dateExpiration,validite) VALUES(?,?,?,?)" ;
            System.out.println("Here");
            PreparedStatement st = DataSource.getInstance().getConnection().prepareStatement(req) ;
            st.setString(1, a.getType());
            System.out.println(new Date(a.getDateCreation().getTime()));
            st.setDate(2, new Date(a.getDateCreation().getTime()));
            st.setDate(3, new Date(a.getDateExpiration().getTime()) );
            st.setInt(4, a.getValidite());
            st.executeUpdate();
            System.out.println("Abonnement ajoutée");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables.getMessage());
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimerAbonnement(int id) {
        try {
            String requete = "DELETE FROM abonnement where id=?";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete) ;
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Abonnement supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void updateAbonnement(Abonnement a) {
        try {
            String requete = "UPDATE abonnement SET type=?,dateCreation=?,dateExpiration=?,validite=? WHERE id=?";
            PreparedStatement st = DataSource.getInstance().getConnection().prepareStatement(requete) ;
            st.setString(1, a.getType());
            st.setDate(2,new Date(a.getDateCreation().getTime()));
            st.setDate(3, new Date(a.getDateExpiration().getTime()));
            st.setInt(4, a.getValidite());
            st.setInt(5, a.getId());
            st.executeUpdate();
            System.out.println("Abonnement modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Abonnement> displayAbonnements() {
        List<Abonnement> abonnementList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM abonnement ";
            PreparedStatement st = DataSource.getInstance().getConnection().prepareStatement(requete) ;
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Abonnement ab = new Abonnement();
                ab.setId(rs.getInt("id"));
                ab.setType(rs.getString("type"));
                ab.setDateCreation(rs.getDate("dateCreation"));
                ab.setDateExpiration(rs.getDate("dateExpiration"));
                ab.setValidite(rs.getInt("validite"));
                abonnementList.add(ab);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return abonnementList;
    }
    @Override
    public Abonnement FindAbonnementById(int id) {
        Abonnement abonnement = null;
        try {
            String requete = "select * from abonnement WHERE id='" + id + "' ";

            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);

            ResultSet res = pst.executeQuery(requete);
            while (res.next()) {
                abonnement = new Abonnement(res.getInt(1), res.getString(2), res.getDate(3), res.getDate(4), res.getInt(5));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AbonnementCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return abonnement;
    }
    @Override
    public ArrayList<Abonnement> TrierParValidite() {
        ArrayList<Abonnement> listAbonnements = new ArrayList<>();
        try {
            String requete= "select * from abonnement ORDER BY validite DESC";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            ResultSet res = pst.executeQuery(requete);
            Abonnement abonnement = null;
            while (res.next()) {
                abonnement = new Abonnement(res.getInt(1), res.getString(2), res.getDate(3), res.getDate(4), res.getInt(5));
                listAbonnements.add(abonnement);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AbonnementCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAbonnements ;
    }
public static ObservableList<Abonnement> getAll(){
        ObservableList<Abonnement> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM abonnement ";
            PreparedStatement st = DataSource.getInstance().getConnection().prepareStatement(requete) ;
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Abonnement ab = new Abonnement();
                ab.setId(rs.getInt("id"));
                ab.setType(rs.getString("type"));
                ab.setDateCreation(rs.getDate("dateCreation"));
                ab.setDateExpiration(rs.getDate("dateExpiration"));
                ab.setValidite(rs.getInt("validite"));
                list.add(ab);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    @Override
 public  ObservableList<Abonnement> chercherAbonnement(java.util.Date date){
        ObservableList<Abonnement> list = FXCollections.observableArrayList();
        //new Date(date.getTime());
        try {
            System.out.println(new Date(date.getTime()));
            String requete= "SELECT * FROM `abonnement` WHERE `dateCreation` like '%"+new Date(date.getTime())+"%' ORDER BY id ASC";
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(requete);
            ResultSet res = pst.executeQuery(requete);
            Abonnement abonnement = null;
            while (res.next()) {
                abonnement = new Abonnement(res.getInt("id"),res.getString("type"), res.getDate("dateCreation"), res.getDate("dateCreation"),res.getInt("validite"));
                list.add(abonnement);

            }
        } catch (Exception e) {
        }
        return list;
    }
 }
