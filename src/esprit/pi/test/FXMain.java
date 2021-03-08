/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pi.test;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Firas
 */
public class FXMain extends Application {

    private Stage primaryStage;
    private Parent parentPage;

    @Override
    public void start(Stage primaryStage) throws IOException {

        try {
            this.primaryStage = primaryStage;

            parentPage = FXMLLoader.load(getClass().getResource("/gui/gestion des sponsors.fxml"));
            Scene scene = new Scene(parentPage);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /**
         * @param args the command line arguments
         */
    }

    public static void main(String[] args) {
        launch(args);
    }

}
