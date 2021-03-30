/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cours.gui;

import edu.cours.entities.Cours;
import static edu.cours.gui.PaiementController.showAlert;
import edu.cours.services.CoursCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AjouterCoursController implements Initializable {

    @FXML
    private TextField tnumc;
    @FXML
    private ComboBox<String> tcombo;
    @FXML
    private TextField tnumr;
    @FXML
    private TextField tnomc;
    @FXML
    private TextField tnomco;
    @FXML
    private Button ajouter;
         private Stage primaryStage;
    @FXML
    private Button gerer;
    @FXML
    private TextField rand;
    @FXML
    private Label tfcode;
    @FXML
    private TextField tprix;
     
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             
             tcombo.getItems().addAll("","Prive ","publique");
             randomcaptchacode();
             tcombo.setValue("");
    }    

    @FXML
    private void ajouterCours(ActionEvent event) throws IOException {
        
        
        switch(controleDeSaisi()) {
                case 0 : showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Remplir les champs vides! ");break;
                case 1 : showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le numero de cours!  !");break;
                case 2 : showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le numero de reservation! ");break;
                case 3 : showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez la nom de cours! ");break;
                case 4 : showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le nom de coach ! ");break;
                case 5 : showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le type ! ");break;
              
                case 6 : showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le prix! ");break;
default :
          if (tfcode.getText().matches(rand.getText()) == true){
                          CoursCRUD C = new CoursCRUD();
       int i =Integer.parseInt(tnumc.getText());
         int j=Integer.parseInt(tnumr.getText());
         int l=Integer.parseInt(tprix.getText());
      
        C.ajouterCours(new Cours(i,j,tnomc.getText(),tnomco.getText(),tcombo.getValue(),l));
        
        
        
        
         

//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Gestion Des cours.fxml"));
// 
//        Parent root1 = (Parent) fxmlLoader.load();
//        Stage stage = new Stage();
//        stage.setTitle("ajout cours");
//        stage.setScene(new Scene(root1));
//        stage.show();
 Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("Gestion Des cours.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();}
    
      else  {Alert a2 = new Alert(Alert.AlertType.ERROR);
                a2.setHeaderText(null);
                a2.setContentText("Captcha Invalide");
                a2.showAndWait();
                randomcaptchacode(); }
    } 
                 JOptionPane.showMessageDialog(null,"Cours Ajouté");

    }

    @FXML
    private void CLOSE(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void gerer(ActionEvent event) throws IOException {
         Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("Gestion Des cours.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }
    
    
    @FXML
    public void randomcaptchacode() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        tfcode.setText(generatedString);
    }
    
    private int controleDeSaisi() {  
                 
        if (tnumc.getText().isEmpty() || tnumr.getText().isEmpty() || tnomc.getText().isEmpty() || tnomco.getText().isEmpty()
                || tcombo.getValue().isEmpty()  ) {
            
            
            return 0;
        } else 
            
            if (!Pattern.matches("[0-9]*", tnumc.getText())) {
                 showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le numero de cours! ");
                tnomc.requestFocus();
                tnomc.selectEnd();
                return 1;
            }
            
           else if (!Pattern.matches("[0-9]*", tnumr.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le numero de reservation! ");
                tnumr.requestFocus();
                tnumr.selectEnd();
                return 2;
            } else if (!Pattern.matches("[a-zA-Z]*", tnomc.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez la nom de reservation! ");
                tnomc.requestFocus();
                tnomc.selectEnd();
                return 3;
            } else if (!Pattern.matches("[a-zA-Z]*", tnomc.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le nom de coach ! ");
                tnomco.requestFocus();
                tnomco.selectEnd();
                 return 4;
            }
             else if (tcombo.getValue().toString().contentEquals("")) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le type ! ");
                
                 return 5;
            }
          
            else if (!Pattern.matches("[0-9]*", tprix.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le prix! ");
                tnomco.requestFocus();
                tnomco.selectEnd();
                 return 6;
          
            }
            else {return 7;
            }  
        
        
    } 
    
       public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    } 
   
}
    

