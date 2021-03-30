/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.gui;

import com.pidev.models.fos_user;
import com.pidev.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author infoevo
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tfusername;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private Button btnlogin;
    @FXML
    private Button btnsignup;
    private Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws SQLException {
         ServiceUser rt = new ServiceUser();
       String s=rt.validerLogin(tfusername.getText(), tfpassword.getText());
        JOptionPane.showMessageDialog(null, "succés");
    }

    @FXML
    private void signup(ActionEvent event) throws IOException {
        Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("signup.fxml"));;
//////////////////////////////////////////////////////////////////
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }
    
}
