/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import esprit.pi.entities.Offres;
import esprit.pi.entities.Sponsors;
import esprit.pi.services.OffresCRUD;
import esprit.pi.services.SponsorsCRUD;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutsponsorsController implements Initializable {

    @FXML
    private TextField ftsociete;
    @FXML
    private TextField ftnum;
    @FXML
    private Button ajouts;
    @FXML
    private Button btnconsulter;
    @FXML
    private TextField ftbudget;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterS(ActionEvent event) throws IOException {
        try {
            SponsorsCRUD rt = new SponsorsCRUD();
        rt.ajouterSponsors(new Sponsors(ftsociete.getText(),Float.parseFloat(ftbudget.getText()),Integer.parseInt(ftnum.getText())));
        JOptionPane.showMessageDialog(null, "Sponsor ajouté");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void consulterS(ActionEvent event) throws IOException{
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("gestion des sponsors.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }
}
