/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cours.gui;

 
import edu.cours.entities.Reservation;
import static edu.cours.gui.AjouterCoursController.showAlert;
import edu.cours.services.ReservationCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.Properties;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AjouterReservationController implements Initializable {

    @FXML
    private TextField tnumres;
    @FXML
    private TextField tnums;
    @FXML
    private TextField tspec;
    @FXML
    private Button ajouter;
    @FXML
    private DatePicker tdate; 
    @FXML
    private TextField tho;
    @FXML
    private TextField tdu;
      private Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
    }    

    @FXML
    private void ajouterReservation(ActionEvent event) throws IOException {
          
          switch(controleDeSaisi()) {
                case 0 : showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Remplir les champs vides! ");break;
                case 1 : showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le numero de reservation  !");break;
                case 2 : showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le numero de salle! ");break;
                case 3 : showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez la specialite! ");break;
                
              
default :
   
      
       
           ReservationCRUD rt = new ReservationCRUD();
          int i = Integer.parseInt(tnumres.getText());
            int j = Integer.parseInt(tnums.getText());
         
   rt.ajouterReservation(new Reservation(  i,j, tspec.getText(), Date.valueOf(tdate.getValue()),tho.getText(),tdu.getText()));
   sendMail();
  
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Gestion Reservation.fxml"));
// 
//        Parent root1 = (Parent) fxmlLoader.load();
//        Stage stage = new Stage();
//        stage.setTitle("ajout cours");
//        stage.setScene(new Scene(root1));
//        stage.show();
Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("Gestion Reservation.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
        
        
         JOptionPane.showMessageDialog(null,"Reservation Ajoutée");

        
          }
    }

    @FXML
    private void CLOSE(MouseEvent event) {
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void gerer(ActionEvent event) throws IOException {
        Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("Gestion Reservation.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

    private void sendMail(){
        TrayNotification tray = null;
        String to = "hadil.chahba@esprit.tn";
        String from = "hadilchahba@gmail.com";
        String host = "smtp.gmail.com";
        final String username = "hadilchahba@gmail.com";
        final String password ="hadil24162082";
        ////SETUP SERVER
        
        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
      
     
    
try{
    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject("reservation Ajouter");
            String num=tnums.getText();
            Date d=Date.valueOf(tdate.getValue());
            m.setText("Votre reservation est ajoutee avec succes le numero de salle est "+num+" et la date est " +d);

            //send mail

            Transport.send(m);
            //sentBoolValue.setVisible(true);
            System.out.println("Message sent!");

        }   catch (MessagingException e){
            e.printStackTrace();
        }
 tray = new TrayNotification("Cograts", "Mail envoyé avec succces ", NotificationType.SUCCESS);
      
        tray.showAndDismiss(Duration.seconds(6));
    }
   
   
 private int controleDeSaisi() {  
                 
        if (tnumres.getText().isEmpty() || tnums.getText().isEmpty() || tspec.getText().isEmpty() || tho.getText().isEmpty()
                || tdu.getText().isEmpty()  ) {
            
            
            return 0;
        } else 
            
            if (!Pattern.matches("[0-9]*", tnumres.getText())) {
                 showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le numero de reservation! ");
                tnumres.requestFocus();
                tnumres.selectEnd();
                return 1;
            }
            
           else if (!Pattern.matches("[0-9]*", tnums.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le numero de salle! ");
                tnums.requestFocus();
                tnums.selectEnd();
                return 2;}
           else if (!Pattern.matches("[a-zA-Z]*", tspec.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez la specialite! ");
                tspec.requestFocus();
                tspec.selectEnd();
                return 3;
            } 
            
          
            
            else {return 4;
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
