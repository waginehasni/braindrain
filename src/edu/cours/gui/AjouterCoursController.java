/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cours.gui;

import edu.cours.entities.Cours;
import edu.cours.services.CoursCRUD;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AjouterCoursController implements Initializable {

    @FXML
    private TextField tnumc;
    @FXML
    private ComboBox<String> tcombo;
    @FXML
    private TextField tnumr;
    @FXML
    private TextField tnomc;
    @FXML
    private TextField tnomco;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             tcombo.getItems().addAll("Prsentiel","distenciel");
    }    

    @FXML
    private void ajouterCours(ActionEvent event) throws IOException {
        
                          CoursCRUD C = new CoursCRUD();
       int i =Integer.parseInt(tnumc.getText());
         int j=Integer.parseInt(tnumr.getText());
      
        C.ajouterCours(new Cours(i,j,tnomc.getText(),tnomco.getText(),tcombo.getValue()));
        
        
         

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Gestion Des cours.fxml"));
 
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("ajout cours");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void CLOSE(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
    

