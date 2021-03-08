/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.gui;

import com.pidev.models.fos_user;
import com.pidev.services.ServiceUser;
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
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
import java.util.List;
 * FXML Controller class
 *
 * @author infoevo
 */
public class AjouterutilisateurController implements Initializable {

    @FXML
    private AnchorPane comborole;
    @FXML
    private TableView<fos_user> tab;
    @FXML
    private TableColumn<fos_user, String> colname;
    @FXML
    private TableColumn<fos_user, String> colemail;
    @FXML
    private TableColumn<fos_user, String> colpassword;
    @FXML
    private TableColumn<fos_user, String> colrole;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfrole;
    @FXML
    private Button btajouter;
    @FXML
    private Button btmodifier;
    @FXML
    private TextField tfrecherche;
    @FXML
    private Button btsupprimer;
    private Stage primaryStage;
    @FXML
    private Button btvalider;
    @FXML
    private Button btactualiser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }   
    @FXML
     private void loadDate() {
        ObservableList<fos_user> abList = FXCollections.observableArrayList();
        colname.setCellValueFactory(new PropertyValueFactory<>("username"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colrole.setCellValueFactory(new PropertyValueFactory<>("roles"));

        ServiceUser rt = new ServiceUser();
        //List old = rt.listAbonnement();
        List old = rt.listuserid();
        abList.addAll(old);
        tab.setItems(abList);
        tab.refresh();
    }
   

    @FXML
    private void ajouter(ActionEvent event) {
        ServiceUser rt = new ServiceUser();
        rt.AjouterUser(new fos_user(tfname.getText(), tfmail.getText(),tfpassword.getText(),tfrole.getText() ));
        JOptionPane.showMessageDialog(null, "utilisateur Envoyé");
        loadDate();

    }

    @FXML
    private void modifier(ActionEvent event) {
         fos_user test = (fos_user) tab.getSelectionModel().getSelectedItem();
        ServiceUser rt = new ServiceUser();
        rt.ModiferUser(test.getId(),new fos_user(test.getId(),tfname.getText(), tfmail.getText(),tfpassword.getText(),tfrole.getText()));
        JOptionPane.showMessageDialog(null, "utilisateur modifié");
        loadDate();
    }

    @FXML
    private void supprimer(ActionEvent event) {
        fos_user test = (fos_user) tab.getSelectionModel().getSelectedItem();
        ServiceUser rt = new ServiceUser();
        rt.SupprimerUser(test.getId());
        JOptionPane.showMessageDialog(null, "utilisateur supprimé");
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
    private void recherchety(){
                    try {
                   ObservableList<fos_user> ofList = FXCollections.observableArrayList();
       colname.setCellValueFactory(new PropertyValueFactory<>("username"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colrole.setCellValueFactory(new PropertyValueFactory<>("roles")); 
        /////////////////////////////////////////////////////////////////////////////
        ServiceUser rt = new ServiceUser();
        List old = rt.AfficherUser(tfrecherche.getText());
        ofList.addAll(old);
        tab.setItems(ofList);
        tab.refresh();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }   
    }
//    private void print(ActionEvent event) {
//    System.out.println("To Printer!");
//         PrinterJob job = PrinterJob.createPrinterJob();
//           if(job != null){
//    Window primaryStage = null;
//           job.showPrintDialog(primaryStage); 
//            
//    Node root = this.tab;
//           job.printPage(root);
//           job.endJob();
            
       
    
        
//        
//    }
//    
//    }
}
