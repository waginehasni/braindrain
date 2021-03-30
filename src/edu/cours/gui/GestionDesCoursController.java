/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cours.gui;

import edu.cours.entities.Cours;
import static edu.cours.gui.AjouterCoursController.showAlert;
import edu.cours.services.CoursCRUD;
import edu.cours.tools.MyConnection;
import static edu.cours.tools.MyConnection.ConnectDb;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class GestionDesCoursController implements Initializable {

    @FXML
    private TextField tnumc;
    @FXML
    private TextField tnumr;
    @FXML
    private TextField tnomc;
    @FXML
    private TextField tnomco;
    @FXML
    private ComboBox<String> tcombo;
    @FXML
    private TableView<Cours> table_cours;
    @FXML
    private TableColumn<Cours,Integer> ftnumcours;
    @FXML
    private TableColumn<Cours,Integer> ftnumreservation;
    @FXML
    private TableColumn<Cours,String> ftnomcours;
    @FXML
    private TableColumn<Cours,String> ftnomcoach;
    @FXML
    private TableColumn<Cours,String> fttype;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private TextField trecherher;
    @FXML
    private Button PDF;
 ObservableList<Cours> abList = FXCollections.observableArrayList();
    @FXML
    private Button refre;
    @FXML
    private Button tri;
    @FXML
    private Button back;
       private Stage primaryStage;
    @FXML
    private TableColumn<Cours,Integer> ftprix;
    @FXML
    private TextField tprix;
    @FXML
    private Label tfcode;
    @FXML
    private TextField rand;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         tcombo.getItems().addAll("","Prive ","publique");
             randomcaptchacode();
             tcombo.setValue("");
        // TODO
         tcombo.getItems().addAll("","Prive","Publique");
        AfficherCours();
//         String req =" SELECT numCours,numReservation,nomCours,nomCoach,type from cours ";
//        XYChart.Series< Integer,String> series = new XYChart.Series<Integer,String>();
//         try {
//             PreparedStatement ste =   MyConnection.getInstance().getConnection().prepareStatement(req);
//            ResultSet rs = ste.executeQuery();
//             while (rs.next()){
//                 series.getData().add(new XYChart.Data<>(rs.getInt(1), rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5)));
//             }
//             table_cours.getDate());
//         } catch (SQLException ex) {
//             Logger.getLogger(GestionDesCoursController.class.getName()).log(Level.SEVERE, null, ex);
//     }
    } 
     public void AfficherCours() {
        ObservableList<Cours> abList = FXCollections.observableArrayList();
        ftnumcours.setCellValueFactory(new PropertyValueFactory<>("numCours"));
       ftnumreservation.setCellValueFactory(new PropertyValueFactory<>("numReservation"));
        ftnomcours.setCellValueFactory(new PropertyValueFactory<>("nomCours"));
      ftnomcoach.setCellValueFactory(new PropertyValueFactory<>("nomCoach"));
        fttype.setCellValueFactory(new PropertyValueFactory<>("type"));
          ftprix.setCellValueFactory(new PropertyValueFactory<>("prix"));

       CoursCRUD rt = new CoursCRUD();
        //List old = rt.listAbonnement();
        List old = rt.getAll() ;
        abList.addAll(old);
        table_cours.setItems(abList);
       table_cours.refresh();
    }
     public void AfficherCours1() {
        ObservableList<Cours> abList = FXCollections.observableArrayList();
      
        ftnomcours.setCellValueFactory(new PropertyValueFactory<>("nomCours"));
      ftnomcoach.setCellValueFactory(new PropertyValueFactory<>("nomCoach"));
        fttype.setCellValueFactory(new PropertyValueFactory<>("type"));
          ftprix.setCellValueFactory(new PropertyValueFactory<>("prix"));

       CoursCRUD rt = new CoursCRUD();
        List old = rt. listCoursbynumc1();
        
        abList.addAll(old);
        table_cours.setItems(abList);
       table_cours.refresh();
    }


    @FXML
    private void ajouterCours(ActionEvent event) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ajouter Cours.fxml"));
//        AnchorPane page = (AnchorPane) fxmlLoader.load();
//
//        // Criando um Estágio de Diálogo (Stage Dialog)
//        Stage dialogStage = new Stage();
//        dialogStage.setTitle("Gestion cours");
//        Scene scene = new Scene(page);
//        dialogStage.setScene(scene);
//        dialogStage.showAndWait();
//        
//        table_cours.refresh();
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
          }
              else  {Alert a2 = new Alert(Alert.AlertType.ERROR);
                a2.setHeaderText(null);
                a2.setContentText("Captcha Invalide");
                a2.showAndWait();
                randomcaptchacode(); 
         
        }
      table_cours.refresh();
      AfficherCours();
       
    }
  
    JOptionPane.showMessageDialog(null,"Cours Ajouté");
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
   

    @FXML
    private void supprimercours(ActionEvent event) {
         Cours test = (Cours) table_cours.getSelectionModel().getSelectedItem();
       CoursCRUD rt = new CoursCRUD();
        rt.supprimerCours(test.getNumCours());
       AfficherCours();
        JOptionPane.showMessageDialog(null,"Cours Supprimer");

    }

    @FXML
    private void modifiercours(ActionEvent event) {
          Cours test = (Cours) table_cours.getSelectionModel().getSelectedItem();
       CoursCRUD rt = new CoursCRUD();
         int i = Integer.parseInt(tnumc.getText());
            int j = Integer.parseInt(tnumr.getText());
            int l = Integer.parseInt(tprix.getText());
        rt.modifiercours(new Cours(  i,j, tnomc.getText(),tnomco.getText(),tcombo.getValue(),l));
           AfficherCours();
            JOptionPane.showMessageDialog(null,"Cours Modifié");

    }

    @FXML
    private void rechercher(ActionEvent event) throws SQLException {
         Connection conn = ConnectDb();
      
        String value9 = trecherher.getText();
      
            PreparedStatement ps = conn.prepareStatement("select * from cours where nomCours Like'"+value9+"'");
            
            ResultSet rs5 = ps.executeQuery();  
            
            while (rs5.next()){   
                
               abList.add(new Cours(rs5.getInt(1),rs5.getInt(2),rs5.getString(3),rs5.getString(4),rs5.getString(5),rs5.getInt(6)) );               
            }
        ftnumcours.setCellValueFactory(new PropertyValueFactory<>("numCours"));
        ftnumreservation.setCellValueFactory(new PropertyValueFactory<>("numReservation"));
        ftnomcours.setCellValueFactory(new PropertyValueFactory<>("nomCours"));
        ftnomcoach.setCellValueFactory(new PropertyValueFactory<>("nomCoach"));
        fttype.setCellValueFactory(new PropertyValueFactory<>("type"));
        ftprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
         table_cours.setItems( abList);
         
          
           
    }

    @FXML
    private void CLOSE(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void close(MouseEvent event) {
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
            
    Node root = this.table_cours;
           job.printPage(root);
           job.endJob();
            }
    
}

    @FXML
    private void udate(ActionEvent event) {
        abList.clear();
        AfficherCours();
        
       table_cours.refresh();
        }

    @FXML
    private void trier(ActionEvent event) {
       
                    ObservableList<Cours> recList = FXCollections.observableArrayList();
     ftnumcours.setCellValueFactory(new PropertyValueFactory<>("numCours"));
        ftnumreservation.setCellValueFactory(new PropertyValueFactory<>("numReservation"));
        ftnomcours.setCellValueFactory(new PropertyValueFactory<>("nomCours"));
        ftnomcoach.setCellValueFactory(new PropertyValueFactory<>("nomCoach"));
        fttype.setCellValueFactory(new PropertyValueFactory<>("type"));
        ftprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
       CoursCRUD rt = new CoursCRUD();
        List old = rt. trierCoursparNumCours();
        recList.addAll(old);
        table_cours.setItems(recList);
        table_cours.refresh();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

    @FXML
    private void selection(MouseEvent event) {
          Cours test = (Cours) table_cours.getSelectionModel().getSelectedItem();
     tnumc.setText(Integer.toString(test.getNumCours()));
     tnumr.setText(Integer.toString(test.getNumReservation()));
     tnomc.setText(test.getNomCours());
     tnomco.setText(test.getNomCoach());
     tcombo.setValue(test.getType());
      tprix.setText(Integer.toString(test.getPrix()));

    }
}