/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.gui;

import com.pidev.models.evenement;
import com.pidev.services.Service_Evenement;
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
 * @author infoevo
 */
public class ListeeventController implements Initializable {

    @FXML
    private TableView<evenement> tab;
    @FXML
    private TableColumn<evenement, Integer> colnumsalle;
    @FXML
    private TableColumn<evenement, String> colnomoffre;
    @FXML
    private TableColumn<evenement, Date> coldatedebut;
    @FXML
    private TableColumn<evenement, Date> coldatefin;
    @FXML
    private TableColumn<evenement, Date> colspecialite;
    @FXML
    private TableColumn<evenement, String> colnom;
    @FXML
    private Button btnnote;
    
    private Stage primaryStage;
    
       

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         loadDate();
    }   
     private void loadDate(){
    ObservableList<evenement> abList = FXCollections.observableArrayList();
        colnumsalle.setCellValueFactory(new PropertyValueFactory<>("num_salle"));
        colnomoffre.setCellValueFactory(new PropertyValueFactory<>("nom_offre"));
        coldatedebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        coldatefin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        colspecialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));

        Service_Evenement rt = new Service_Evenement();
        List old = rt.listeventid();
        abList.addAll(old);
        tab.setItems(abList);
        tab.refresh();
    }


    @FXML
    private void note(ActionEvent event) throws IOException {
         Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("Utilisateur.fxml"));;
//////////////////////////////////////////////////////////////////
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }
    
}
