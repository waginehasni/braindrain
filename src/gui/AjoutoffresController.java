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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutoffresController implements Initializable {

    @FXML
    private TextField ftnom;
    @FXML
    private TextField ftdescription;
    @FXML
    private ComboBox<String> fttype;
    @FXML
    private Button ajout;
    private Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fttype.getItems().addAll("produit alimentaire", "vetement", "materiel", "bon d'achat");

    }

    @FXML
    private void ajouterF(ActionEvent event) throws IOException {
        try {
        OffresCRUD rt = new OffresCRUD();
        rt.ajouterOffres(new Offres(ftnom.getText(), fttype.getValue(), ftdescription.getText()));
        JOptionPane.showMessageDialog(null, "Offre ajouté");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void consulterF(ActionEvent event) throws IOException{
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("gestiondesoffres.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }
    }


