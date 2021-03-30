/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.gui;

import com.pidev.models.fos_user;
import com.pidev.services.ServiceUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author infoevo
 */
public class SignupController implements Initializable {

    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfrole;
    @FXML
    private TextField tftelephone;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private Button btnsignup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signup(ActionEvent event) {
         ServiceUser rt = new ServiceUser();
         rt.registerUser(new fos_user(tfusername.getText(), tfemail.getText(),tfpassword.getText(),tfrole.getText(),tftelephone.getText()));
      
        JOptionPane.showMessageDialog(null, "utilisateur ajouté");
    }
    
}
