package gui;

import entities.Reclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> tvReclamations;
    private TableColumn<Reclamation, Integer> colId;
    @FXML
    private TableColumn<entities.Reclamation, String> colTitre;
    @FXML
    private TableColumn<entities.Reclamation, String> colDescription;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfTitre;
    @FXML
    private TextArea taDescription;
    @FXML
    private TableColumn<?, ?> cold;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private VBox vboxImage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colTitre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));

    }
    @FXML
    private void ajouterReclamation(ActionEvent event) throws SQLException {

    }
    @FXML
    private void modifierReclamation(ActionEvent event) throws SQLException {

    }
    @FXML
    private void supprimerReclamation(ActionEvent event) throws SQLException {

    }
}
