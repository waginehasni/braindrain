/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.pieChart;

import api.QrCodeGenerator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;
import services.ReclamationCRUD;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class PieChartFXMLController implements Initializable {

    @FXML
    private Button btnRetour;
    @FXML
    private BorderPane pane;
    //private PieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
				 new PieChart.Data("Non Satisfaits", ReclamationCRUD.countNotSatsifiedClients()),
				 new PieChart.Data("Satisfaits", ReclamationCRUD.countSatsifiedClients()));
				 PieChart pieChart = new PieChart(valueList);
				 pieChart.setTitle("Satisfaction");
				 pieChart.getData().forEach(data -> {
				 String percentage = String.format("%.2f%%", ((data.getPieValue() / ReclamationCRUD.countAllReclamations())*100));
				 Tooltip toolTip = new Tooltip(percentage);
				 Tooltip.install(data.getNode(), toolTip);
				});
                       //PieChart pieChart = new PieChart(valueList);
                                 pane.setCenter(pieChart);
    }    

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader fXMLLoader = new  FXMLLoader(getClass().getResource("../reclamationCloture/reclamationCloture.fxml"));
        Parent root = (Parent) fXMLLoader.load();
        btnRetour.getScene().setRoot(root);
    }

    @FXML
    private void generateQrCode(ActionEvent event) {
        String message = "Nombre total des Réclamlations traités: "+ReclamationCRUD.countAllReclamations() + ""
                        + "\n Nombre des clients satisfaits : "+ReclamationCRUD.countSatsifiedClients() + ""
                        + "\n Nombre des clients non satisfaits : " + ReclamationCRUD.countNotSatsifiedClients() ;
                QrCodeGenerator.generate(message);
                JOptionPane.showMessageDialog(null,"QrCOde générer et placée sur votre bureau");
    }
    
}
