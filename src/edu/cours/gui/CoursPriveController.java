/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cours.gui;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class CoursPriveController implements Initializable {

    @FXML
    private Button tp;
    @FXML
    private Button tpp;
    @FXML
    private Button tppp;
    @FXML
    private Button tpppp;
    
  private Stage primaryStage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
         
        // TODO
    }    

    @FXML
    private void payer1(ActionEvent event) throws IOException {
        Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("Paiement.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
         
        
        
    }
    
    

    @FXML
    private void payer2(ActionEvent event) throws IOException {
         Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("Paiement.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
        
        
       
        
    }

    @FXML
    private void payer3(ActionEvent event) throws IOException {
         Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("Paiement.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
      
     }

    @FXML
    private void payer4(ActionEvent event) throws IOException {
         Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("Paiement.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

    @FXML
    private void CLOSE(MouseEvent event) {
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
   
  public void invisible( ){
      tp.setVisible(false);
  }
 
 
    
}
