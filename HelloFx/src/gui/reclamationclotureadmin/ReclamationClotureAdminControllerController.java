/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reclamationclotureadmin;

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
import entities.Reclamation;
import gui.reclamation.ReclamationController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import services.ReclamationCRUD;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class ReclamationClotureAdminControllerController implements Initializable {

    @FXML
    private DatePicker dpReclamation;
    @FXML
    private TextField tfEtat;
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
    private DatePicker dpDateRep;
    @FXML
    private TextField tfChercher;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private Button btnSatis;
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
        ObservableList<String> list = FXCollections.observableArrayList("satisfait","non satisfait");
         combo.setItems(list);
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
        tfEtat.setText(selectedRec.getEtat());
        tfSujet.setText(selectedRec.getTitre());
        taDescription.setText(selectedRec.getDescription());
        dpDateRep.setValue(LocalDate.parse(selectedRec.getDateReponse().toString()));
        taReponse.setText(selectedRec.getReponse());
        combo.setValue(selectedRec.getSatisfaction());
        //System.out.println(selectedRec.getImage());        
        vboxImg.getChildren().removeAll(vboxImg.getChildren());
        if(selectedRec.getImage()!=null){
        // System.out.println("here 1");
        getImage();        
        }
    }
}
    
    public void updateTable(){
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        listR = rc.getAllClotureAdmin();                 
        tableReclamation.setItems(listR);
        
        //System.out.println(listR);
        }

    @FXML
    private void chercher(ActionEvent event) {
        if (tfChercher.getText().length()==0){
            updateTable();
        }else{
            listR = rc.chercherReclamationcloture(tfChercher.getText());
           tableReclamation.setItems(listR);
        }
    }

    @FXML
    private void select(ActionEvent event) {
       
    }

    @FXML
    private void envoyer(ActionEvent event) {
        //System.out.println(combo.getSelectionModel().getSelectedItem().toString());
        reclamation.setSatisfaction(combo.getSelectionModel().getSelectedItem().toString());
        rc.updateReclamationSatisfaction(reclamation);        
       JOptionPane.showMessageDialog(null,"Merci pour votre coopération !" );
    }

    @FXML
    private void rechercher(KeyEvent event) {
        if (tfChercher.getText().length()==0){
            updateTable();
        }else{
            listR = rc.chercherReclamationcloture(tfChercher.getText());
           tableReclamation.setItems(listR);
        }
    }

    @FXML
    private void spellDescription(ActionEvent event) {
        if(taDescription.getText().length()!=0)
        ReadText.readOutLoud(taDescription.getText());

    }

    @FXML
    private void spellReponse(ActionEvent event) {  
        if(taReponse.getText().length()!=0)
        ReadText.readOutLoud(taReponse.getText());
    }

    @FXML
    private void exportPDF(ActionEvent event) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\aymen\\Desktop/TableReclamationsCloturés.pdf"));
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
            thirdTitleParagraph = new Paragraph("Date réponse\n\n");
            thirdTitleCell = createCell(thirdTitleParagraph, BLACK);
            table.addCell(thirdTitleCell);
            thirdTitleParagraph = new Paragraph("Etat\n\n");
            thirdTitleCell = createCell(thirdTitleParagraph, BLACK);
            table.addCell(thirdTitleCell);
            thirdTitleParagraph = new Paragraph("Reponse\n\n");
            thirdTitleCell = createCell(thirdTitleParagraph, BLACK);
            table.addCell(thirdTitleCell);
            thirdTitleParagraph = new Paragraph("Satisfaction\n\n");
            thirdTitleCell = createCell(thirdTitleParagraph, BLACK);
            table.addCell(thirdTitleCell);
            listR = rc.getAllCloture(); 
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
            
            thirdTitleParagraph = new Paragraph(r.getDateReponse()+"\n\n");
            thirdTitleCell = createCell(thirdTitleParagraph, BLACK);
            table.addCell(thirdTitleCell);
            
            thirdTitleParagraph = new Paragraph(r.getEtat()+"\n\n");
            thirdTitleCell = createCell(thirdTitleParagraph, BLACK);
            table.addCell(thirdTitleCell);
            
            thirdTitleParagraph = new Paragraph(r.getReponse()+"\n\n");
            thirdTitleCell = createCell(thirdTitleParagraph, BLACK);
            table.addCell(thirdTitleCell);
            
            thirdTitleParagraph = new Paragraph(r.getSatisfaction()+"\n\n");
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
        PdfPTable table = new PdfPTable(7); // 7 columns.
        table.setWidthPercentage(100); //Width 100%
        table.setSpacingBefore(10f); //Space before table
        table.setSpacingAfter(10f); //Space after table

        //Set Column widths
        float[] columnWidths = {1f, 1f, 1f, 1f, 1f, 1f, 1f};
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

    @FXML
    private void satisfactions(ActionEvent event) throws IOException {
        FXMLLoader fXMLLoader = new  FXMLLoader(getClass().getResource("../pieChart/PieChartFXML.fxml"));
        Parent root = (Parent) fXMLLoader.load();
        btnSatis.getScene().setRoot(root);
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
    }
   
}
