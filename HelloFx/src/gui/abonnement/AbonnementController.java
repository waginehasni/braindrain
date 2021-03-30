/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.abonnement;
import com.itextpdf.text.BaseColor;
import static com.itextpdf.text.BaseColor.BLACK;
import static com.itextpdf.text.BaseColor.BLUE;
import static com.itextpdf.text.BaseColor.RED;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Abonnement;
import java.io.FileOutputStream;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import services.AbonnementCRUD;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class AbonnementController implements Initializable {

    @FXML
    private TableView<Abonnement> tableAbonnement;
    @FXML
    private TableColumn<Abonnement, String> colDate;
    @FXML
    private TableColumn<Abonnement, String> colType;
    @FXML
    private DatePicker dpDate;
    @FXML
    private ComboBox cbType;
    private Stage primaryStage;
    
 private ObservableList<Abonnement> listA;
    private AbonnementCRUD ac = new AbonnementCRUD();
    private Abonnement abonnement = new Abonnement();
    @FXML
    private DatePicker dpChercher;
    ObservableList<String> l = FXCollections.observableArrayList("Mensuelle","Annuelle");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbType.setItems(l);
        cbType.setValue(l.get(0));
        updateTable();
        tableAbonnement.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 1) {
        onEdit();
    }
});
    }
public void onEdit() {
    // check the table's selected item and get selected item
    if (tableAbonnement.getSelectionModel().getSelectedItem() != null) {
        Abonnement selectedRec = tableAbonnement.getSelectionModel().getSelectedItem();
        dpDate.setValue(LocalDate.parse(selectedRec.getDateExpiration().toString()));
        cbType.setValue(selectedRec.getType());
        this.abonnement = selectedRec;
    }
}    
public Date conversion (LocalDate ld){
   //default time zone
	ZoneId defaultZoneId = ZoneId.systemDefault();         
        //local date + atStartOfDay() + default time zone + toInstant() = Date
        Date date = Date.from(ld.atStartOfDay(defaultZoneId).toInstant());
        return date ;
   
   }

    @FXML
    private void ajouterAbonnement(ActionEvent event) {
        if(dpDate.getValue()==null){
            JOptionPane.showMessageDialog(null,"Veuillez selectionner la date");
        }else {
            LocalDate p= dpDate.getValue();
        Date date =conversion(p);
        abonnement = new Abonnement(cbType.getValue().toString(),new Date(),date,12);
        ac.ajouterAbonnement(abonnement); 
        updateTable();
        abonnement = new Abonnement();
        viderChamps();
        JOptionPane.showMessageDialog(null,"Vous ete abonnées maintenant");   
       
        }
        
    }

    @FXML
    private void modifierAbonnement(ActionEvent event) {
        if (abonnement.getId()<=0){
            JOptionPane.showMessageDialog(null,"Veuillez selectionner un abonnement");
        }else {
            LocalDate p= dpDate.getValue();
        Date date =conversion(p);
        abonnement.setType(cbType.getValue().toString());
        abonnement.setDateExpiration(date);
        ac.updateAbonnement(abonnement);
        updateTable();
        abonnement = new Abonnement();
        viderChamps();
        JOptionPane.showMessageDialog(null,"Votre abonnement est a jour");
        }
        
    }

    @FXML
    private void supprimerAbonnement(ActionEvent event) {
        if (abonnement.getId()<=0){
            JOptionPane.showMessageDialog(null,"Veuillez selectionner un abonnement");
        }else {
        ac.supprimerAbonnement(abonnement.getId());
        updateTable();
        abonnement = new Abonnement();
        viderChamps();
        JOptionPane.showMessageDialog(null,"Abonnement Supprimée");
        }
        
    }

    public void updateTable(){
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateExpiration"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        listA = AbonnementCRUD.getAll();                 
        tableAbonnement.setItems(listA);
    }

    private void chercher(ActionEvent event) {
        LocalDate p= dpDate.getValue();
        Date date =conversion(p);
            //System.out.println(date);
            listA = ac.chercherAbonnement(date);
            tableAbonnement.setItems(listA);
    }

    /*LocalDate p= dpChercher.getValue();
        Date date =conversion(p);
            System.out.println(date);
            listA = ac.chercherAbonnement(date);
            tableAbonnement.setItems(listA);*/
    @FXML
    private void BPDF(ActionEvent event) {
          Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\aymen\\Desktop/TableAbonnements.pdf"));
            document.open();
            PdfPTable table = createPDFTable();
            Paragraph firstTitleParagraph = new Paragraph("Date Expiration\n\n");
            PdfPCell firstTitleCell = createCell(firstTitleParagraph, BLUE);
            table.addCell(firstTitleCell);
            Paragraph secondTitleParagraph = new Paragraph("Type\n\n");
            PdfPCell secondTitleCell = createCell(secondTitleParagraph, BLACK);
            table.addCell(secondTitleCell);
            listA = AbonnementCRUD.getAll();  
            for (Abonnement a :listA){
            firstTitleParagraph = new Paragraph(a.getDateExpiration()+"\n\n");
            firstTitleCell = createCell(firstTitleParagraph, BLUE);
            table.addCell(firstTitleCell);

            secondTitleParagraph = new Paragraph(a.getType()+"\n\n");
            secondTitleCell = createCell(secondTitleParagraph, BLACK);
            table.addCell(secondTitleCell);
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
        PdfPTable table = new PdfPTable(2); // 3 columns.
        table.setWidthPercentage(100); //Width 100%
        table.setSpacingBefore(10f); //Space before table
        table.setSpacingAfter(10f); //Space after table

        //Set Column widths
        float[] columnWidths = {1f, 1f};
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
    private void recherche(ActionEvent event) {
        if(dpChercher.getValue()==null){
            updateTable();
        }else {
            LocalDate p= dpChercher.getValue();
        Date date =conversion(p);
        // System.out.println(date);
            listA = ac.chercherAbonnement(date);
            tableAbonnement.setItems(listA);
        }        
    }
    
    private void viderChamps(){
        dpDate.setValue(null);
    }
            
    
           
    
}
