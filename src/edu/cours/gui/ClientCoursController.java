/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cours.gui;

import edu.cours.entities.Cours;
import edu.cours.entities.Reservation;
import edu.cours.services.CoursCRUD;
import edu.cours.services.ReservationCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ClientCoursController implements Initializable {

    @FXML
    private TableView<Reservation> table_reservation;
   
    @FXML
    private TableColumn<Reservation, Integer> ftnums;
    @FXML
    private TableColumn<Reservation, String> ftspecialite;
    @FXML
    private TableColumn<Reservation, Date> ftdate;
    @FXML
    private TableColumn<Reservation, String> fthorraire;
    @FXML
    private TableColumn<Reservation, String> ftduree;
    @FXML
  private TableView<Cours> table_cours;
   
    @FXML
    private TableColumn<Cours,String> ftnomcours;
    @FXML
    private TableColumn<Cours,String> ftnomcoach;
    @FXML
    private TableColumn<Cours,String> fttype;
    @FXML
  
    private Button tpayer;
    
 ObservableList<Cours> abList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Cours,Integer> ftprix;
     private Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     AfficherCours1();
     AfficherReservation1();
        // TODO
    }    

   

    
     public void AfficherReservation1() {
        ObservableList<Reservation> abList = FXCollections.observableArrayList();
       
       ftnums.setCellValueFactory(new PropertyValueFactory<>("numSalles"));
        ftspecialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
      ftdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        fthorraire.setCellValueFactory(new PropertyValueFactory<>("horraire"));
      ftduree.setCellValueFactory(new PropertyValueFactory<>("duree"));

       ReservationCRUD rt = new ReservationCRUD();
        //List old = rt.listAbonnement();
        List old = rt.listReservationbynum();
        abList.addAll(old);
        table_reservation.setItems(abList);
       table_reservation.refresh();
    }
     
     public void AfficherCours1() {
        ObservableList<Cours> abList = FXCollections.observableArrayList();
      
        ftnomcours.setCellValueFactory(new PropertyValueFactory<>("nomCours"));
      ftnomcoach.setCellValueFactory(new PropertyValueFactory<>("nomCoach"));
        fttype.setCellValueFactory(new PropertyValueFactory<>("type"));
          ftprix.setCellValueFactory(new PropertyValueFactory<>("prix"));

       CoursCRUD rt = new CoursCRUD();
        List old = rt. listCoursbynumc1();
        
        abList.addAll(old);
        table_cours.setItems(abList);
       table_cours.refresh();
    }
    @FXML
    private void CLOSE(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void payer(ActionEvent event) throws IOException {
         Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("Paiement.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

    @FXML
    private void selection(MouseEvent event) {
    }

    @FXML
    private void res(Event event) {
        
     AfficherReservation1();
    }

    @FXML
    private void cou(Event event) {
        AfficherCours1();
    }
    
}
