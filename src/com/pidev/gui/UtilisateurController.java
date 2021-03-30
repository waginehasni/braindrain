/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.gui;
import com.pidev.models.rating;
import com.pidev.services.ServiceRating;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author infoevo
 */
public class UtilisateurController implements Initializable {

    
    
    
    @FXML
    private Rating rating;
    @FXML
    private Label labelrating;
    @FXML
    private TextField rating_choix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       

		
		rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number t, Number t1) {
                
                
              labelrating.setText(t1.toString());
            }
    });
                        }


    @FXML
    private void envoyer(ActionEvent event) {
         rating r = new rating();
 ServiceRating sr = new ServiceRating();
 r.setNom(rating_choix.getText());
        r.setRating(labelrating.getText());
        
       sr.Ajouterrating(r);
        
    }
    
    
    
}