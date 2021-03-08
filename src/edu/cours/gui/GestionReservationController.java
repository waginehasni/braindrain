/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cours.gui;

import edu.cours.entities.Reservation;
import edu.cours.services.ReservationCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class GestionReservationController implements Initializable {

    @FXML
    private DatePicker tdate;
    @FXML
    private TextField tnumr;
    @FXML
    private TextField tnums;
    @FXML
    private TextField tspc;
    @FXML
    private TextField thorraire;
    @FXML
    private TextField tduree;
    @FXML
    private TableView<Reservation> table_reservation;
    @FXML
    private TableColumn<Reservation, Integer> ftnumres;
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
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private TextField trechercher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AfficherReservation();
    }    
   private void AfficherReservation() {
        ObservableList<Reservation> abList = FXCollections.observableArrayList();
        ftnumres.setCellValueFactory(new PropertyValueFactory<>("numReservation"));
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
    @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void ajouterReservation(ActionEvent event) throws IOException {
//        ReservationCRUD rt = new ReservationCRUD();
//          int i = Integer.parseInt(tnumr.getText());
//            int j = Integer.parseInt(tnums.getText());
//        
//        
//        rt.ajouterReservation(new Reservation(  i,j, tspc.getText(), Date.valueOf(tdate.getValue()),thorraire.getText(),tduree.getText()));
//        JOptionPane.showMessageDialog(null, "Reservation Envoyeé");
//      AfficherReservation();
FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ajouter Reservation.fxml"));
        AnchorPane page = (AnchorPane) fxmlLoader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Gestion reservation");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
       

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();
        
        table_reservation.refresh();
    }

    @FXML
    private void supprimerReservation(ActionEvent event) {
       Reservation test = (Reservation) table_reservation.getSelectionModel().getSelectedItem();
        ReservationCRUD rt = new ReservationCRUD();
        rt.supprimerReservation(test.getNumReservation());
       AfficherReservation();
    }

    @FXML
    private void modifierreservation(ActionEvent event) {
        Reservation test = (Reservation) table_reservation.getSelectionModel().getSelectedItem();
        ReservationCRUD rt = new ReservationCRUD();
         int i = Integer.parseInt(tnumr.getText());
            int j = Integer.parseInt(tnums.getText());
        rt.modifierReservation(new Reservation(  i,j, tspc.getText(), Date.valueOf(tdate.getValue()),thorraire.getText(),tduree.getText()));
           AfficherReservation();
    }

    @FXML
    private void rechercher(ActionEvent event) {
    }

    @FXML
    private void CLOSE(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
}
