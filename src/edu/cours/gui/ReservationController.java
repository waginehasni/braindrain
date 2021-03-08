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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ReservationController implements Initializable {

    @FXML
    private TextField tnumres;
    @FXML
    private TextField tnums;
    @FXML
    private TextField tspec;
    @FXML
    private Button ajouter;
    @FXML
    private DatePicker tdate;
    @FXML
    private TextField tho;
    @FXML
    private TextField tdu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


  
    @FXML
    private void ajouterReservation(ActionEvent event) throws IOException {
        
        
                          ReservationCRUD C = new ReservationCRUD();
       int i =Integer.parseInt(tnumres.getText());
         int j=Integer.parseInt(tnums.getText());
            
      
        C.ajouterReservation(new Reservation(i,j,tspec.getText(),Date.valueOf(tdate.getValue()),tho.getText(),tdu.getText()));
        
        
         

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Gestion Reservation.fxml"));
 
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("ajout reservaation");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
      @FXML
    private void CLOSE(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    
}
