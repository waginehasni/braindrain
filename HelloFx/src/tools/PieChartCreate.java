/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import services.ReclamationCRUD;
import api.QrCodeGenerator;

/**
 *
 * @author aymen
 */
public class PieChartCreate extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        QrCodeGenerator.generate("test");
         Pane root = new Pane();
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

		 //adding pieChart to the root.
                 Button btn = new Button();
                btn.setText("Génerer QrCode");
                btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                String message = "Nombre total des Réclamlations traités: "+ReclamationCRUD.countAllReclamations() + ""
                        + "\n Nombre des clients satisfaits : "+ReclamationCRUD.countSatsifiedClients() + ""
                        + "\n Nombre des clients non satisfaits : " + ReclamationCRUD.countNotSatsifiedClients() ;
                QrCodeGenerator.generate(message);
            }
        });
		 root.getChildren().addAll(pieChart);
                 root.getChildren().add(btn);
		 Scene scene = new Scene(root, 450, 450);
		 primaryStage.setTitle("Pourcentage de satisfaction des clients");
		 primaryStage.setScene(scene);
		 primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
