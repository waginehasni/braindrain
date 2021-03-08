/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author infoevo
 */
public class MenuController implements Initializable {

    @FXML
    private Button utilisateur;
    @FXML
    private Button evenement;
    private Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gestionutilisateur(ActionEvent event) throws IOException {
        Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("ajouterutilisateur.fxml"));;
//////////////////////////////////////////////////////////////////
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

    @FXML
    private void gestionevenement(ActionEvent event) throws IOException {
        Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("evenement.fxml"));;
//////////////////////////////////////////////////////////////////
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }
    
}
