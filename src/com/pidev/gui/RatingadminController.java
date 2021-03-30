/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.gui;

import com.pidev.models.rating;
import com.pidev.services.ServiceRating;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author infoevo
 */
public class RatingadminController implements Initializable {

    @FXML
    private TableColumn<rating, String> colnom;
    @FXML
    private TableColumn<rating, String> colrating;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TableView<rating> tab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    } 
     private void loadDate(){
    ObservableList<rating> abList = FXCollections.observableArrayList();
        
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colrating.setCellValueFactory(new PropertyValueFactory<>("rating"));
                

         ServiceRating rt = new ServiceRating();
        List old = rt.getAllrating();
        abList.addAll(old);
        tab.setItems(abList);
        tab.refresh();
    }

    @FXML
    private void supprimer(ActionEvent event) {
         rating test = (rating) tab.getSelectionModel().getSelectedItem();
        ServiceRating rt = new ServiceRating();
        rt.Supprimerrating(test.getNom());
        JOptionPane.showMessageDialog(null, "rating supprimé");
        loadDate();
    }
    
}
