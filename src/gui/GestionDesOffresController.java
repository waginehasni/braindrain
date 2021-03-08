/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import esprit.pi.entities.Offres;
import esprit.pi.services.OffresCRUD;
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
import javafx.scene.control.ComboBox;
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
public class GestionDesOffresController implements Initializable {

    @FXML
    private TableView<Offres> table_offres;
    @FXML
    private TableColumn<Offres, String> fnom;
    @FXML
    private TableColumn<Offres, String> ftype;
    @FXML
    private TableColumn<Offres, String> fdescription;
    @FXML
    private TextField ftnom;
    @FXML
    private TextField ftdescription;
    @FXML
    private ComboBox<String> fttype;
    @FXML
    private Button btnajouter;
    @FXML
    private TextField rechercher;
    @FXML
    private TextField tfrecherche;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fttype.getItems().addAll("produit alimentaire", "vetement", "materiel", "bon d'achat");
        AfficherOffre();
    }
    @FXML
    private void AfficherOffre() {
        ObservableList<Offres> ofList = FXCollections.observableArrayList();
        fnom.setCellValueFactory(new PropertyValueFactory<>("nomOffre"));
        ftype.setCellValueFactory(new PropertyValueFactory<>("type"));
        fdescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        OffresCRUD rt = new OffresCRUD();
        List old = rt.listOffresid();
        ofList.addAll(old);
        table_offres.setItems(ofList);
        table_offres.refresh();
    }

    @FXML
    private void ajouterF(ActionEvent event) throws IOException {
//        OffresCRUD rt = new OffresCRUD();
//        rt.ajouterOffres(new Offres(ftnom.getText(),fttype.getValue(),ftdescription.getText()));
//        JOptionPane.showMessageDialog(null, "Offre ajouté");
//        AfficherOffre();
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("ajoutoffres.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }
    @FXML
    private void modifierF(ActionEvent event) {
        Offres test;
        test = (Offres) table_offres.getSelectionModel().getSelectedItem();
        OffresCRUD rt = new OffresCRUD();
        rt.updateOffres(new Offres(ftnom.getText(), fttype.getValue(), ftdescription.getText()), test.getIdOffre());
        AfficherOffre();
    }
    @FXML
    private void supprimerF(ActionEvent event) throws SQLException {

        Offres test = (Offres) table_offres.getSelectionModel().getSelectedItem();
        OffresCRUD rt = new OffresCRUD();
        rt.supprimerOffres(test, test.getIdOffre());
        AfficherOffre();
    }
    @FXML
    private void recherchety(){
                    try {
                   ObservableList<Offres> ofList = FXCollections.observableArrayList();
        fnom.setCellValueFactory(new PropertyValueFactory<>("nomOffre"));
        ftype.setCellValueFactory(new PropertyValueFactory<>("type"));
        fdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        /////////////////////////////////////////////////////////////////////////////
        OffresCRUD rt = new OffresCRUD();
        List old = rt.RechercherParType(tfrecherche.getText());
        ofList.addAll(old);
        table_offres.setItems(ofList);
        table_offres.refresh();
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
            
    Node root = this.table_offres;
           job.printPage(root);
           job.endJob();
            
       
    
        
        
    }}}
















