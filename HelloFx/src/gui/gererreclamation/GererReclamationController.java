/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.gererreclamation;

import api.ReadText;
import com.itextpdf.text.BaseColor;
import static com.itextpdf.text.BaseColor.BLACK;
import static com.itextpdf.text.BaseColor.BLUE;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Abonnement;
import entities.Reclamation;
import gui.reclamation.ReclamationController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import services.AbonnementCRUD;
import services.ReclamationCRUD;
import tools.BadWordsDetection;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class GererReclamationController implements Initializable {

    @FXML
    private DatePicker dpReclamation;
    @FXML
    private TextField tfSujet;
    @FXML
    private TextArea taDescription;
    @FXML
    private TextArea taReponse;
    @FXML
    private TableView<Reclamation> tableReclamation;
    @FXML
    private TableColumn<Reclamation, String> colTitre;
    @FXML
    private TableColumn<Reclamation, String> colDescription;
    
    private ObservableList<entities.Reclamation> listR;
    private ReclamationCRUD rc = new ReclamationCRUD();
    private Reclamation reclamation = new Reclamation();
    @FXML
    private TextField tfChercher;
    @FXML
    private VBox vboxImg;
    private ImageView imageView = new ImageView();
    private Image image ;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         updateTable();
         BadWordsDetection.loadConfigs();
        tableReclamation.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 1) {
        onEdit();
    }
});
    }
public void onEdit() {
    // check the table's selected item and get selected item
    if (tableReclamation.getSelectionModel().getSelectedItem() != null) {
        Reclamation selectedRec = tableReclamation.getSelectionModel().getSelectedItem();
        this.reclamation = selectedRec;
        dpReclamation.setValue(LocalDate.parse(selectedRec.getDateReclamation().toString()));
        tfSujet.setText(selectedRec.getTitre());
        taDescription.setText(selectedRec.getDescription());
        vboxImg.getChildren().removeAll(vboxImg.getChildren());
        if(selectedRec.getImage()!=null){
            //System.out.println("here 1");
        getImage();        
        }
    }
}
 /*public Date conversion (LocalDate ld){
   //default time zone
	    ZoneId defaultZoneId = ZoneId.systemDefault();
        //local date + atStartOfDay() + default time zone + toInstant() = Date
        Date date = Date.from(ld.atStartOfDay(defaultZoneId).toInstant());
        return date ;
   }*/
        

    @FXML
    private void repondreReclamation(ActionEvent event) {
        ArrayList<String> badWordsList = BadWordsDetection.badWordsFound(taReponse.getText());
       //ReadText.readOutLoud(taDescription.getText());
       if (reclamation.getId()<=0)           
       JOptionPane.showMessageDialog(null,"Veuilller Selectionner une reclamation " );
       else if(taReponse.getText().length()==0)           
           JOptionPane.showMessageDialog(null,"Entrer votre réponse SVP");
       else if (badWordsList.size()>0){
           JOptionPane.showMessageDialog(null,"Veuiller enlever les mauvais mots SVP");
       }else {
        this.reclamation.setReponse(taReponse.getText());
        reclamation.setDateReponse(new Date());
        reclamation.setEtat("cloturé");
        reclamation.setSatisfaction("satisfait");
        rc.updateReclamationRepondre(reclamation);
        reclamation = new Reclamation();
        viderChamps();
        updateTable();
        JOptionPane.showMessageDialog(null,"Réclamation Cloturé" );
       }
    }
    
    public void updateTable(){
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        listR = rc.getAll();
        tableReclamation.setItems(listR);
        //System.out.println(listR);
        }

    @FXML
    private void chercher(ActionEvent event) {
        if (tfChercher.getText().length()==0){
            updateTable();
        }else{
            listR = rc.chercherReclamationActif(tfChercher.getText());
           tableReclamation.setItems(listR);
        }
        
    }

    @FXML
    private void rechercher(KeyEvent event) {
        if (tfChercher.getText().length()==0){
            updateTable();
        }else{
            listR = rc.chercherReclamationActif(tfChercher.getText());
           tableReclamation.setItems(listR);
        }
    }
    private void getImage(){
       // System.out.println("here 2");
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File(reclamation.getImage()));
           // System.out.println("here 3");
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
    }
    public void viderChamps(){
        dpReclamation.setValue(null);
        tfSujet.setText("");
        taDescription.setText("");
        taReponse.setText("");
    }

    @FXML
    private void spellDescription(ActionEvent event) {
        if(taDescription.getText().length()!=0)
        ReadText.readOutLoud(taDescription.getText());
    }

    @FXML
    private void spellResponse(ActionEvent event) {
        if(taReponse.getText().length()!=0)
        ReadText.readOutLoud(taReponse.getText());
    }

    @FXML
    private void exportPdf(ActionEvent event) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\aymen\\Desktop/TableReclamationsActifs.pdf"));
            document.open();
            PdfPTable table = createPDFTable();
            Paragraph firstTitleParagraph = new Paragraph("Sujet\n\n");
            PdfPCell firstTitleCell = createCell(firstTitleParagraph, BLUE);
            table.addCell(firstTitleCell);
            Paragraph secondTitleParagraph = new Paragraph("Descriptions \n\n");
            PdfPCell secondTitleCell = createCell(secondTitleParagraph, BLACK);
            table.addCell(secondTitleCell);
            Paragraph thirdTitleParagraph = new Paragraph("Date reclamation\n\n");
            PdfPCell thirdTitleCell = createCell(thirdTitleParagraph, BLACK);
            table.addCell(thirdTitleCell);            
            listR = rc.getAll();
            for (Reclamation r :listR){
            firstTitleParagraph = new Paragraph(r.getTitre()+"\n\n");
            firstTitleCell = createCell(firstTitleParagraph, BLUE);
            table.addCell(firstTitleCell);

            secondTitleParagraph = new Paragraph(r.getDescription()+"\n\n");
            secondTitleCell = createCell(secondTitleParagraph, BLACK);
            table.addCell(secondTitleCell);
            
            thirdTitleParagraph = new Paragraph(r.getDateReclamation()+"\n\n");
            thirdTitleCell = createCell(thirdTitleParagraph, BLACK);
            table.addCell(thirdTitleCell);
            }
            document.add(table);
            document.close();
            writer.close();         
            JOptionPane.showMessageDialog(null,"PDF exportée et placée sur votre bureau");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static PdfPTable createPDFTable() throws DocumentException {
        PdfPTable table = new PdfPTable(3); // 3 columns.
        table.setWidthPercentage(100); //Width 100%
        table.setSpacingBefore(10f); //Space before table
        table.setSpacingAfter(10f); //Space after table

        //Set Column widths
        float[] columnWidths = {1f, 1f, 1f};
        table.setWidths(columnWidths);
        return table;
    }

    private static PdfPCell createCell(Paragraph phrase, BaseColor color) {
        PdfPCell cell1 = new PdfPCell(phrase);
        cell1.setBorderColor(color);
        cell1.setPaddingLeft(10);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell1;
    }

}
