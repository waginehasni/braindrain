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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class GestionDesCoursController implements Initializable {

    @FXML
    private TextField tnumc;
    @FXML
    private TextField tnumr;
    @FXML
    private TextField tnomc;
    @FXML
    private TextField tnomco;
    @FXML
    private ComboBox<String> tcombo;
    @FXML
    private TableView<Cours> table_cours;
    @FXML
    private TableColumn<Cours,Integer> ftnumcours;
    @FXML
    private TableColumn<Cours,Integer> ftnumreservation;
    @FXML
    private TableColumn<Cours,String> ftnomcours;
    @FXML
    private TableColumn<Cours,String> ftnomcoach;
    @FXML
    private TableColumn<Cours,String> fttype;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private TextField trecherher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         tcombo.getItems().addAll("Prsentiel","distenciel");
        AfficherCours();
    }  
     private void AfficherCours() {
        ObservableList<Cours> abList = FXCollections.observableArrayList();
        ftnumcours.setCellValueFactory(new PropertyValueFactory<>("numCours"));
       ftnumreservation.setCellValueFactory(new PropertyValueFactory<>("numReservation"));
        ftnomcours.setCellValueFactory(new PropertyValueFactory<>("nomCours"));
      ftnomcoach.setCellValueFactory(new PropertyValueFactory<>("nomCoach"));
        fttype.setCellValueFactory(new PropertyValueFactory<>("type"));
     

       CoursCRUD rt = new CoursCRUD();
        //List old = rt.listAbonnement();
        List old = rt.listCoursbynumc() ;
        abList.addAll(old);
        table_cours.setItems(abList);
       table_cours.refresh();
    }

    @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void ajouterCours(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ajouter Cours.fxml"));
        AnchorPane page = (AnchorPane) fxmlLoader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Gestion cours");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
        
        table_cours.refresh();

    }

    @FXML
    private void supprimercours(ActionEvent event) {
         Cours test = (Cours) table_cours.getSelectionModel().getSelectedItem();
       CoursCRUD rt = new CoursCRUD();
        rt.supprimerCours(test.getNumCours());
       AfficherCours();
    }

    @FXML
    private void modifiercours(ActionEvent event) {
          Cours test = (Cours) table_cours.getSelectionModel().getSelectedItem();
       CoursCRUD rt = new CoursCRUD();
         int i = Integer.parseInt(tnumc.getText());
            int j = Integer.parseInt(tnumr.getText());
        rt.modifiercours(new Cours(  i,j, tnomc.getText(),tnomco.getText(),tcombo.getValue()));
           AfficherCours();
    }

    @FXML
    private void rechercher(ActionEvent event) {
    }

    @FXML
    private void CLOSE(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
}
