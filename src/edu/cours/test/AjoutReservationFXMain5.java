/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cours.test;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author dell
 */
public class AjoutReservationFXMain5 extends Application {
    
   double xOffset, yOffset;
    @Override
    public void start(Stage primaryStage) {
     try {
           FXMLLoader home = new FXMLLoader(getClass().getResource("../Gui/Ajouter Reservation.fxml"));
            //FXMLLoader home1 = new FXMLLoader(getClass().getResource("../Gui/modifierCours.fxml"));

        Parent root = home.load();
        Scene rec = new Scene(root);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
          rec.setFill(Color.TRANSPARENT);
        primaryStage.setScene(rec);
         primaryStage.show();
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					xOffset = event.getSceneX();
					yOffset = event.getSceneY();
				}
			});
			root.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					primaryStage.setX(event.getScreenX() - xOffset);
					primaryStage.setY(event.getScreenY() - yOffset);
				}
			});
        
        
        } catch (IOException e) {
              System.out.println(e.getMessage());
        }
 

    }
        
      public static void main(String[] args) {
        launch(args);
    
}
    
}
