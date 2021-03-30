/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.gui;

import com.pidev.models.fos_user;
import com.pidev.services.ServiceUser;
import com.pidev.utils.DataSource;
import com.pidev.utils.sms;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.PasswordField;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

/**
import java.util.List;
 * FXML Controller class
 *
 * @author infoevo
 */
public class AjouterutilisateurController implements Initializable {

    @FXML
    private AnchorPane comborole;
    @FXML
    private TableView<fos_user> tab;
    @FXML
    private TableColumn<fos_user, String> colname;
    @FXML
    private TableColumn<fos_user, String> colemail;
    @FXML
    private TableColumn<fos_user, String> colpassword;
    @FXML
    private TableColumn<fos_user, String> colrole;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfmail;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private TextField tfrole;
    @FXML
    private Button btajouter;
    @FXML
    private Button btmodifier;
    @FXML
    private TextField tfrecherche;
    @FXML
    private Button btsupprimer;
    private Stage primaryStage;
    @FXML
    private Button btvalider;
    @FXML
    private Button btactualiser;
    @FXML
    private Button btprint1;
    @FXML
    private TableColumn<fos_user, String> coltelephone;
    @FXML
    private TextField tftelephone;
    @FXML
    private Button btnrelax;
    @FXML
    private Button btnexcel;
    public  static String numTelephone ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        loadDate();
    }   
                Connection conn = DataSource.getInstance().getCnx();

//    @FXML
//    private void selection (MouseEvent event){
//    fos_user test = (fos_user) tab.getSelectionModel().getSelectedItem();
//    tfname.setText(test.getUsername());
//    tfmail.setText(test.getEmail());
//    tfpassword.setText(test.getPassword());
//    tfrole.setText(test.getRoles());
//    tftelephone.setText(test.getTelephone());
//
//}
    @FXML
     private void loadDate() {
        ObservableList<fos_user> abList = FXCollections.observableArrayList();
        colname.setCellValueFactory(new PropertyValueFactory<>("username"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colrole.setCellValueFactory(new PropertyValueFactory<>("roles"));
        coltelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        ServiceUser rt = new ServiceUser();
        //List old = rt.listAbonnement();
        List old = rt.listuserid();
        abList.addAll(old);
        tab.setItems(abList);
        tab.refresh();
    }
   

    @FXML

    private void ajouter(ActionEvent event) throws IOException {
        if(tfname.getText().isEmpty() | tfmail.getText().isEmpty() | tfpassword.getText().isEmpty()|tfrole.getText().isEmpty()|tftelephone.getText().isEmpty()){
            //JOptionPane.showMessageDialog(null, "Remplir les champs vides");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Remplir les champs vides");
            al.showAndWait();
        }else{
        ServiceUser rt = new ServiceUser();
        rt.AjouterUser(new fos_user(tfname.getText(), tfmail.getText(),tfpassword.getText(),tfrole.getText(),tftelephone.getText()));
        JOptionPane.showMessageDialog(null, "utilisateur Ajouté");
         numTelephone =tftelephone.getText();
              sms s = new sms();
              s.send("félicitaion bien incsrit",numTelephone);
        tfname.clear();
        tfmail.clear();
        tfpassword.clear();
        tfrole.clear();
        tftelephone.clear();
        tab.refresh();
        
       
        }
    }


    @FXML
    private void modifier(ActionEvent event) {
         fos_user test = (fos_user) tab.getSelectionModel().getSelectedItem();
        ServiceUser rt = new ServiceUser();
        rt.ModiferUser(test.getId(),new fos_user(test.getId(),tfname.getText(), tfmail.getText(),tfpassword.getText(),tfrole.getText(),tftelephone.getText()));
        JOptionPane.showMessageDialog(null, "utilisateur modifié");
        loadDate();
    }

    @FXML
    private void supprimer(ActionEvent event) {
        fos_user test = (fos_user) tab.getSelectionModel().getSelectedItem();
        ServiceUser rt = new ServiceUser();
        rt.SupprimerUser(test.getId());
        JOptionPane.showMessageDialog(null, "utilisateur supprimé");
        loadDate();
    }
     @FXML
     private void retour(ActionEvent event) throws IOException{
         Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("menu.fxml"));;
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
     }
     @FXML
    private void recherchety(){
                    try {
                   ObservableList<fos_user> ofList = FXCollections.observableArrayList();
       colname.setCellValueFactory(new PropertyValueFactory<>("username"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colrole.setCellValueFactory(new PropertyValueFactory<>("roles")); 
        coltelephone.setCellValueFactory(new PropertyValueFactory<>("telephone")); 
        /////////////////////////////////////////////////////////////////////////////
        ServiceUser rt = new ServiceUser();
        List old = rt.AfficherUser(tfrecherche.getText());
        ofList.addAll(old);
        tab.setItems(ofList);
        tab.refresh();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }   
    }
     @FXML
    private void print(ActionEvent event) {
    System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
            
    Node root = this.tab;
           job.printPage(root);
           job.endJob();
            
       
    
        
        
    }
    
    }

    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
        int index = tab.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    tfname.setText(colname.getCellData(index));
    tfmail.setText(colemail.getCellData(index));
    tfpassword.setText(colpassword.getCellData(index));
    tfrole.setText(colrole.getCellData(index));
    tftelephone.setText(coltelephone.getCellData(index));
    
    }

    @FXML
    private void playmusic(ActionEvent event) throws IOException {
        InputStream music;
        try {
            music = new FileInputStream("a.wav");
            AudioStream audios= new AudioStream(music);
            AudioPlayer.player.start(audios);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AjouterutilisateurController.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, " n'existe pas");
        }
        
        
    }
 private void Excel(File file) throws FileNotFoundException, IOException {

        try {
            //System.out.println("Clicked,waiting to export....");

            FileOutputStream fileOut;
            fileOut = new FileOutputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet workSheet = workbook.createSheet("sheet 0");

            workSheet.setColumnWidth(1, 25);

            HSSFFont fontBold = workbook.createFont();
            fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            HSSFCellStyle styleBold = workbook.createCellStyle();
            styleBold.setFont(fontBold);

            Row row1 = workSheet.createRow((short) 0);
            workSheet.autoSizeColumn(7);
            row1.createCell(0).setCellValue("username");
            row1.createCell(1).setCellValue("email");
            row1.createCell(2).setCellValue("password");
            row1.createCell(3).setCellValue("roles");
            row1.createCell(4).setCellValue("telephone");
            Row row2;

            String req = "SELECT * from fos_user ";
            PreparedStatement ps = conn.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int a = rs.getRow();
                row2 = workSheet.createRow((short) a);

                row2.createCell(0).setCellValue(rs.getString(2));
                row2.createCell(1).setCellValue(rs.getString(3));
                row2.createCell(2).setCellValue(rs.getString(4));
                row2.createCell(3).setCellValue(rs.getString(5));
                row2.createCell(4).setCellValue(rs.getString(6));

            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("controllers.CommandeBack.ExcelAction()");

        }
    }

    @FXML
    private void excel(ActionEvent event)throws IOException {

        FileChooser chooser = new FileChooser();
        // Set extension filter
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel Files(.xls)", ".xls");
        chooser.getExtensionFilters().add(filter);
        // Show save dialog
        File file = chooser.showSaveDialog(btnexcel.getScene().getWindow());
        if (file != null) {
            Excel(file);

        }

    }
     


}
