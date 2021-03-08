/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import esprit.pi.entities.Sponsors;
import esprit.pi.services.SponsorsCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class GestionDesSponsorsController implements Initializable {

    @FXML
    private TextField ftsociete;
    @FXML
    private TextField ftbudget;
    @FXML
    private TextField ftnum;
    @FXML
    private TableColumn<Sponsors, String> fnum;
    @FXML
    private TableColumn<Sponsors, String> fbudget;
    @FXML
    private TableColumn<Sponsors, String> fsociete;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private TextField rechercher;
    @FXML
    private TableView<Sponsors> table_sponsors;
    private Stage primaryStage;
    private Node table_offres;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AfficherSponsor();
    }
    @FXML
    private void AfficherSponsor() {
        ObservableList<Sponsors> sList = FXCollections.observableArrayList();
        fnum.setCellValueFactory(new PropertyValueFactory<>("numtelephone"));
        fbudget.setCellValueFactory(new PropertyValueFactory<>("budget"));
        fsociete.setCellValueFactory(new PropertyValueFactory<>("societe"));
       

        SponsorsCRUD rt = new SponsorsCRUD();
        //List old = rt.listAbonnement();
        List old = rt.listSponsorsid();
        sList.addAll(old);
        table_sponsors.setItems(sList);
        table_sponsors.refresh();
    }
    @FXML
    private void ajouterS(ActionEvent event) throws IOException {
//        SponsorsCRUD rt = new SponsorsCRUD();
//        rt.ajouterSponsors(new Sponsors(ftsociete.getText(),Float.parseFloat(ftbudget.getText()),Integer.parseInt(ftnum.getText())));
//        JOptionPane.showMessageDialog(null, "Abonnement Envoyeé");
//        AfficherSponsor();
   Parent rootRec2 = FXMLLoader.load(getClass().getResource("ajoutsponsors.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();

    }
    @FXML
    private void modifierS(ActionEvent event) {
        Sponsors test;
        test = (Sponsors) table_sponsors.getSelectionModel().getSelectedItem();
        SponsorsCRUD rt = new SponsorsCRUD();
        rt.updateSponsors(new Sponsors(ftsociete.getText(),Float.parseFloat(ftbudget.getText()),Integer.parseInt(ftnum.getText())),test.getIdSponsor());
        AfficherSponsor();
    }
    @FXML
    private void supprimerS(ActionEvent event) throws SQLException {

        Sponsors test = (Sponsors) table_sponsors.getSelectionModel().getSelectedItem();
        SponsorsCRUD rt = new SponsorsCRUD();
        rt.supprimerSponsors(test);
        AfficherSponsor();
    }

    @FXML
    private void recherchetyp(){
                    try {
                   ObservableList<Sponsors> sList = FXCollections.observableArrayList();
       fnum.setCellValueFactory(new PropertyValueFactory<>("numtelephone"));
        fbudget.setCellValueFactory(new PropertyValueFactory<>("budget"));
        fsociete.setCellValueFactory(new PropertyValueFactory<>("societe"));
        /////////////////////////////////////////////////////////////////////////////
        SponsorsCRUD rt = new SponsorsCRUD();
        List old = rt.RechercherParSociete(rechercher.getText());
        sList.addAll(old);
        table_sponsors.setItems(sList);
        table_sponsors.refresh();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }   
    }

    
    @FXML
    private void BPDF(ActionEvent event) {
    System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
            
    Node root = this.table_sponsors;
           job.printPage(root);
           job.endJob();
            
       
    
        
        
    }}
    
    
    
    
    
    
    
    
    
    
    
}
    

