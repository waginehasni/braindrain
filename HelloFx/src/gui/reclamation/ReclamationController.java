/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reclamation;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ReclamationCRUD;
import entities.Reclamation;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import tools.BadWordsDetection;
import api.ReadText;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import tests.JavaFXImageFileChooser;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class ReclamationController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextArea taDescription;
    @FXML
    private TableView<entities.Reclamation> tableReclamation;
    @FXML
    private TableColumn<entities.Reclamation, String> colTitre;
    @FXML
    private TableColumn<entities.Reclamation, String> colDescription;
    @FXML
    private TextField tfRechercher;
    @FXML
    private VBox vboxImg;
    
    private ObservableList<entities.Reclamation> listR;
    private ReclamationCRUD rc = new ReclamationCRUD();
    private ImageView imageView = new ImageView();
    private Image image ;
    FileChooser fileChooser = new FileChooser();
    private Reclamation reclamation = new Reclamation() ;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
        tableReclamation.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 1) {
        onEdit();
    }
});
        BadWordsDetection.loadConfigs();
    } 
    public void onEdit() {
    // check the table's selected item and get selected item
    
    if (tableReclamation.getSelectionModel().getSelectedItem() != null) {
        Reclamation selectedRec = tableReclamation.getSelectionModel().getSelectedItem();        
        reclamation  = selectedRec ;
        tfTitre.setText(selectedRec.getTitre());
        taDescription.setText(selectedRec.getDescription());
        ///.out.println(selectedRec.getImage());
        vboxImg.getChildren().removeAll(vboxImg.getChildren());
        if(selectedRec.getImage()!=null){
        //System.out.println("here 1");
        getImage();        
        }
        //System.out.println(reclamation.toString());
    }
    }

    @FXML
    private void ajouterReclamation(ActionEvent event) {
      
       //Reclamation reclamation = new Reclamation(id,titre,description);
       ArrayList<String> badWordsList = BadWordsDetection.badWordsFound(taDescription.getText());
       //ReadText.readOutLoud(taDescription.getText());
       if(!verifierChamps().equals("ok"))           
           JOptionPane.showMessageDialog(null,verifierChamps());
       else if (badWordsList.size()>0){
           JOptionPane.showMessageDialog(null,"Veuiller enlever les mauvais mots SVP");
       }else {
           reclamation.setId(1);
           reclamation.setTitre(tfTitre.getText());
           reclamation.setDescription(taDescription.getText());
           reclamation.setDateReclamation(new Date());
           //System.out.println(reclamation.getImage());
           rc.ajouterReclamation(reclamation);
           viderChamps();
           reclamation = new Reclamation();           
           JOptionPane.showMessageDialog(null,"Reclamation enregistrée");
       }
       updateTable();
        
    }

    @FXML
    private void modifierReclamation(ActionEvent event) {
        ArrayList<String> badWordsList = BadWordsDetection.badWordsFound(taDescription.getText());
       //ReadText.readOutLoud(taDescription.getText());
       if (reclamation.getId()<=0)           
       JOptionPane.showMessageDialog(null,"Veuilller Selectionner une reclamation " );
       else if(!verifierChamps().equals("ok"))           
           JOptionPane.showMessageDialog(null,verifierChamps());
       else if (badWordsList.size()>0){
           JOptionPane.showMessageDialog(null,"Veuiller enlever les mauvais mots SVP");
       }else {
       reclamation.setTitre(tfTitre.getText());
       reclamation.setDescription(taDescription.getText());
       //reclamation.setImage("imageqsdfqsdf");
       rc.updateReclamation(reclamation);
       viderChamps();
       reclamation = new Reclamation();
       JOptionPane.showMessageDialog(null,"Réclamation Modifié" );
       }
       updateTable();
    }

    @FXML
    private void supprimerReclamation(ActionEvent event) {
        if (reclamation.getId()<=0){            
       JOptionPane.showMessageDialog(null,"Veuilller Selectionner une reclamation " );
        }else{
            rc.supprimerReclamation(reclamation.getId());
            viderChamps();
            reclamation = new Reclamation();                    
            JOptionPane.showMessageDialog(null,"Réclamation Supprimée !" ); 
        }
       updateTable();
    }
    
   
    
    
    public void updateTable(){
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        listR = rc.getAll();                 
        tableReclamation.setItems(listR);
        //System.out.println(listR);
        }

    @FXML
    private void chercherReclamation(ActionEvent event) {
        if (tfRechercher.getText().length()==0){
            updateTable();
        }else{
            listR = rc.chercherReclamationActif(tfRechercher.getText());
           tableReclamation.setItems(listR);
        }
    }
    @FXML
    private void recherche(KeyEvent event) {
        if (tfRechercher.getText().length()==0){
            updateTable();
        }else{
            listR = rc.chercherReclamationActif(tfRechercher.getText());
           tableReclamation.setItems(listR);
        }
    }

    @FXML
    private void browse(ActionEvent event) {
        setExtFilters(fileChooser);
        Stage primaryStage = new Stage();
       File file = fileChooser.showOpenDialog(primaryStage);
       if (file!= null ){
          ///System.out.println(file.getAbsolutePath());
        image = new Image(file.toURI().toString());
       }
        ///System.out.println(file.getAbsolutePath());
        image = new Image(file.toURI().toString());
        file = new File("C:\\Users\\aymen\\Documents\\NetBeansProjects\\HelloFx\\src\\images/"+(int)(Math.random()*10000)+".png");
        reclamation.setImage(file.getAbsolutePath());   
        try {
                        ImageIO.write(SwingFXUtils.fromFXImage(image,
                                null), "png", file);
                    } catch (IOException ex) {
                        Logger.getLogger(
                            JavaFXImageFileChooser.class.getName()).log(Level.SEVERE, null, ex);
                    }
        imageView.setPreserveRatio(true);
        imageView.setImage(image);
        imageView.setSmooth(true);
        imageView.setCache(true);
        vboxImg.setSpacing(10);
        vboxImg.setPadding(new Insets(0, 10, 0, 10));
        vboxImg.getChildren().add(imageView);
       // reclamation.setImage(file.getAbsolutePath());
        
    }
    private void setExtFilters(FileChooser chooser){
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }
    private void getImage(){
        //System.out.println("here 2");
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File(reclamation.getImage()));
            //System.out.println("here 3");
        image = SwingFXUtils.toFXImage(myPicture, null );
                
        imageView.setPreserveRatio(true);
        imageView.setImage(image);
        imageView.setSmooth(true);
        imageView.setCache(true);        
        vboxImg.getChildren().removeAll(vboxImg.getChildren());
        vboxImg.setSpacing(10);
        vboxImg.setPadding(new Insets(0, 10, 0, 10));
        vboxImg.getChildren().add(imageView);
        } catch (IOException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //image = new Image(reclamation.getImage());
        
        
    }
    private String verifierChamps(){
        if(tfTitre.getText().length()==0) 
            return "Veuiller Entrer un titre pour votre Reclamation";
        else if (taDescription.getText().length()==0)
            return "Veuiller remplir les description de votre Reclamation";
        else return "ok";
    }
    private void viderChamps(){
        tfTitre.setText("");
        taDescription.setText("");
        fileChooser = new FileChooser();
        imageView= new ImageView();
        vboxImg.getChildren().removeAll(vboxImg.getChildren());
                
        
    }

    @FXML
    private void spellDescription(ActionEvent event) {
        if(taDescription.getText().length()!=0)
        ReadText.readOutLoud(taDescription.getText());
    }

    
}
