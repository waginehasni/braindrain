/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.gui;

import com.pidev.models.evenement;
import com.pidev.services.Service_Evenement;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeRegExp.test;

/**
 * FXML Controller class
 *
 * @author infoevo
 */
public class EvenementController implements Initializable {

    @FXML
    private AnchorPane comborole;
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
    private TableColumn<evenement, String> colspecialite;
    @FXML
    private TextField tfnumsalle;
    @FXML
    private TextField tfnomoffre;
    @FXML
    private TextField tfspecialite;
    @FXML
    private Button btajouter;
    @FXML
    private Button btmodifier;
    @FXML
    private Button btsupprimer;
    @FXML
    private Button btntridate;
    @FXML
    private DatePicker tfdatedebut;
    @FXML
    private DatePicker tfdatefin;
    private Stage primaryStage;
    @FXML
    private Button btretour;
    @FXML
    private Button btnvideo;
    @FXML
    private TextField tfnom;
    @FXML
    private TableColumn<evenement, String> colnom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDate();
        // TODO
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
    private void ajouter(ActionEvent event) {
         Service_Evenement rt = new Service_Evenement();
        rt.Ajouterevent(new evenement(Integer.parseInt(tfnumsalle.getText()), tfnomoffre.getText(), Date.valueOf(tfdatedebut.getValue()), Date.valueOf(tfdatefin.getValue()), tfspecialite.getText(),tfnom.getText()));
        JOptionPane.showMessageDialog(null, "evenement ajouté");
        loadDate();
    }

    @FXML
    private void modifier(ActionEvent event) {
        evenement test = (evenement) tab.getSelectionModel().getSelectedItem();
        Service_Evenement rt = new Service_Evenement();
        rt.ModiferEvenement(test.getId_evenement(),new evenement(Integer.parseInt(tfnumsalle.getText()), tfnomoffre.getText(), Date.valueOf(tfdatedebut.getValue()), Date.valueOf(tfdatefin.getValue()), tfspecialite.getText(),tfnom.getText()));
        JOptionPane.showMessageDialog(null, "evenement modifié");
        loadDate();
    }

    @FXML
    private void supprimer(ActionEvent event) {
        evenement test = (evenement) tab.getSelectionModel().getSelectedItem();
        Service_Evenement rt = new Service_Evenement();
        rt.SupprimerEvenement(test.getId_evenement());
        JOptionPane.showMessageDialog(null, "evenement supprimé");
        loadDate();
    }
     @FXML
     private void retour(ActionEvent event) throws IOException{
         Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("menu.fxml"));;
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
     }
     @FXML
     public void trierDate(ActionEvent event){
        ObservableList<evenement> recList = FXCollections.observableArrayList();
        colnumsalle.setCellValueFactory(new PropertyValueFactory<>("num_salle"));
        colnomoffre.setCellValueFactory(new PropertyValueFactory<>("nom_offre"));
        coldatedebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        coldatefin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        colspecialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Service_Evenement rt = new Service_Evenement();
        List old = rt.trierevenementDateDebut();
        recList.addAll(old);
        tab.setItems(recList);
        tab.refresh();
    }

    

    @FXML
    private void getSelected(MouseEvent event) {
            evenement test = (evenement) tab.getSelectionModel().getSelectedItem();

         int index = tab.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    tfnumsalle.setText(colnumsalle.getCellData(index).toString());
    tfnomoffre.setText(colnomoffre.getCellData(index));
    String d1= test.getDate_debut().toString();
    LocalDate ss = LocalDate.parse(d1);
    tfdatedebut.setValue(ss);
    String d2= test.getDate_debut().toString();
    LocalDate aa = LocalDate.parse(d2);
    tfdatefin.setValue(aa);
    tfspecialite.setText(colspecialite.getCellData(index));
    tfnom.setText(colnom.getCellData(index));
    }

    @FXML
    private void video(ActionEvent event) throws IOException {
        Desktop.getDesktop().open(new File("wijou.wmv"));
    }
    
    
}
