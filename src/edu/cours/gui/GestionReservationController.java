/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cours.gui;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarFeed;
import edu.cours.entities.Reservation;
import static edu.cours.gui.AjouterReservationController.showAlert;
import edu.cours.services.ReservationCRUD;
import static edu.cours.tools.MyConnection.ConnectDb;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;   
import java.util.Properties;
        
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
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
public class GestionReservationController implements Initializable {

    @FXML
    private DatePicker tdate;
    @FXML
    private TextField tnumr;
    @FXML
    private TextField tnums;
    @FXML
    private TextField tspc;
    @FXML
    private TextField thorraire;
    @FXML
    private TextField tduree;
    @FXML
    private TableView<Reservation> table_reservation;
    @FXML
    private TableColumn<Reservation, Integer> ftnumres;
    @FXML
    private TableColumn<Reservation, Integer> ftnums;
    @FXML
    private TableColumn<Reservation, String> ftspecialite;
    @FXML
    private TableColumn<Reservation, Date> ftdate;
    @FXML
    private TableColumn<Reservation, String> fthorraire;
    @FXML
    private TableColumn<Reservation, String> ftduree;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private TextField trechercher;
    @FXML
    private Button PDF;
    @FXML
    private Button refre;
   ObservableList<Reservation> abList = FXCollections.observableArrayList();
    @FXML
    private Button tri;
    @FXML
    private Button back;
    private Stage primaryStage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AfficherReservation();
        
        
    }    
   public void AfficherReservation() {
        ObservableList<Reservation> abList = FXCollections.observableArrayList();
        ftnumres.setCellValueFactory(new PropertyValueFactory<>("numReservation"));
       ftnums.setCellValueFactory(new PropertyValueFactory<>("numSalles"));
        ftspecialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
      ftdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        fthorraire.setCellValueFactory(new PropertyValueFactory<>("horraire"));
      ftduree.setCellValueFactory(new PropertyValueFactory<>("duree"));

       ReservationCRUD rt = new ReservationCRUD();
        //List old = rt.listAbonnement();
        List old = rt.listReservationbynum();
        abList.addAll(old);
        table_reservation.setItems(abList);
       table_reservation.refresh();
    }
    public void AfficherReservation1() {
        ObservableList<Reservation> abList = FXCollections.observableArrayList();
        
       ftnums.setCellValueFactory(new PropertyValueFactory<>("numSalles"));
        ftspecialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
      ftdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        fthorraire.setCellValueFactory(new PropertyValueFactory<>("horraire"));
      ftduree.setCellValueFactory(new PropertyValueFactory<>("duree"));

       ReservationCRUD rt = new ReservationCRUD();
        //List old = rt.listAbonnement();
        List old = rt.listReservationbynum1();
        abList.addAll(old);
        table_reservation.setItems(abList);
       table_reservation.refresh();
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
          int i = Integer.parseInt(tnumr.getText());
            int j = Integer.parseInt(tnums.getText());
         
   rt.ajouterReservation(new Reservation(  i,j, tspc.getText(), Date.valueOf(tdate.getValue()),thorraire.getText(),tduree.getText()));
   sendMail();
        
        table_reservation.refresh();
           AfficherReservation();
        
    }}

    @FXML
    private void supprimerReservation(ActionEvent event) {
       Reservation test = (Reservation) table_reservation.getSelectionModel().getSelectedItem();
        ReservationCRUD rt = new ReservationCRUD();
        rt.supprimerReservation(test.getNumReservation());
       AfficherReservation();
       
         JOptionPane.showMessageDialog(null,"Reservation Supprimée");
    }

    @FXML
    private void modifierreservation(ActionEvent event) {
        Reservation test = (Reservation) table_reservation.getSelectionModel().getSelectedItem();
        ReservationCRUD rt = new ReservationCRUD();
         int i = Integer.parseInt(tnumr.getText());
            int j = Integer.parseInt(tnums.getText());
        rt.modifierReservation(new Reservation(  i,j, tspc.getText(), Date.valueOf(tdate.getValue()),thorraire.getText(),tduree.getText()));
           JOptionPane.showMessageDialog(null,"Reservation Modifiée");
           AfficherReservation();
          
    }

    @FXML
    private void rechercher(ActionEvent event) throws SQLException {
         Connection conn = ConnectDb();
      
        String value9 = trechercher.getText();
      
            PreparedStatement ps = conn.prepareStatement("select * from reservation where specialite Like'"+value9+"'");
            
            ResultSet rs5 = ps.executeQuery();  
            
            while (rs5.next()){   
                
               abList.add(new Reservation(rs5.getInt(1),rs5.getInt(2),rs5.getString(3),rs5.getDate(4),rs5.getString(5),rs5.getString(6)) );               
            }
        ftnumres.setCellValueFactory(new PropertyValueFactory<>("numReservation"));
        ftnums.setCellValueFactory(new PropertyValueFactory<>("numSalles"));
        ftspecialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        ftdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        fthorraire.setCellValueFactory(new PropertyValueFactory<>("horraire"));
        ftduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
         table_reservation.setItems( abList);
    }

    @FXML
    private void CLOSE(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void BPDF(ActionEvent event) {
         System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
            
    Node root = this.table_reservation;
           job.printPage(root);
           job.endJob();
            
       
    
        
        
    }
    }

    @FXML
    private void udate(ActionEvent event) {
         abList.clear();
        AfficherReservation();
        
       table_reservation.refresh();
    }

    @FXML
    private void trier(ActionEvent event) {
          ObservableList<Reservation> recList = FXCollections.observableArrayList();
      ftnumres.setCellValueFactory(new PropertyValueFactory<>("numReservation"));
        ftnums.setCellValueFactory(new PropertyValueFactory<>("numSalles"));
        ftspecialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        ftdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        fthorraire.setCellValueFactory(new PropertyValueFactory<>("horraire"));
        ftduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        ReservationCRUD rt = new ReservationCRUD();
        List old = rt.trierreservationdate();
        recList.addAll(old);
        table_reservation.setItems(recList);
        table_reservation.refresh();
    }
    

    @FXML
    private void back(ActionEvent event) throws IOException {
//          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
//        AnchorPane page = (AnchorPane) fxmlLoader.load();
//
//        // Criando um Estágio de Diálogo (Stage Dialog)
//        Stage dialogStage = new Stage();
//        dialogStage.setTitle("Gestion reservation");
//        Scene scene = new Scene(page);
//        dialogStage.setScene(scene);
//        dialogStage.showAndWait();
Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

    @FXML
    private void  selection(MouseEvent event) {
       Reservation test = (Reservation) table_reservation.getSelectionModel().getSelectedItem();
    
        tnumr.setText(Integer.toString(test.getNumReservation()));
        tnums.setText(Integer.toString(test.getNumSalles()));
        
    tspc.setText(test.getSpecialite());
   
    //tfdate.getValue(test.getDate_act());
    String d1= test.getDate().toString();
    LocalDate ss = LocalDate.parse(d1);
    tdate.setValue(ss);
    //tfdate.setValue(LocalDate.of(test.getDate_act().getYear(), test.getDate_act().getMonth(), test.getDate_act().getDay()));
   thorraire.setText(test.getHorraire()); 
   tduree.setText(test.getDuree()); 
        
        
        
        
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
                 
        if (tnumr.getText().isEmpty() || tnums.getText().isEmpty() || tspc.getText().isEmpty() || thorraire.getText().isEmpty()
                || tduree.getText().isEmpty()  ) {
            
            
            return 0;
        } else 
            
            if (!Pattern.matches("[0-9]*", tnumr.getText())) {
                 showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le numero de reservation! ");
                tnumr.requestFocus();
                tnumr.selectEnd();
                return 1;
            }
            
           else if (!Pattern.matches("[0-9]*", tnums.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le numero de salle! ");
                tnums.requestFocus();
                tnums.selectEnd();
                return 2;}
           else if (!Pattern.matches("[a-zA-Z]*", tspc.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez la specialite! ");
                tspc.requestFocus();
                tspc.selectEnd();
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