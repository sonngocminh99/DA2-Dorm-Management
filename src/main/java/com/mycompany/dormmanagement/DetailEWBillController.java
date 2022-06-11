/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.dormmanagement.Model.ElectricAndWaterBill;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class DetailEWBillController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private static NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
     private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 22,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 20,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.NORMAL);
    private EwBillPaneController eWBillController;
    private ElectricAndWaterBill ewBill;
    @FXML
    private Label ID, apartment, room, eStart, eEnd, eNum, eFee, wStart, wEnd, wNum, wFee, total,date;
    @FXML
    private Button markAsDone, exportButton;
    @FXML
    void back(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
    }
    @FXML
    void updateStatus(ActionEvent event){
        ewBill=new ElectricAndWaterBill();
        ewBill.updateStatusEWBill(ID.getText());
        markAsDone.setDisable(true);
        eWBillController.refreshTable();
        
    }
    @FXML
    void exportFilePDF() {
        ExportPDF(ID.getText());
    } 
    public void reciveData(String data, EwBillPaneController parentController){
        loadData(data);
        eWBillController = parentController;
        
        
    }
    
    public void loadData(String billID){
        
        ewBill = new ElectricAndWaterBill();
        ewBill.getInfoBaseID(billID);
        if(ewBill.getStatus().equals("Đã thu")) markAsDone.setDisable(true);
        ID.setText(ewBill.getBillID());
        apartment.setText(ewBill.getApartment().getIDApartment());
        room.setText(ewBill.getRoom().getRoomID());
        eStart.setText(nf.format(ewBill.getChiSoDauDien()));
        eEnd.setText(nf.format(ewBill.getChiSoCuoiDien()));
        eNum.setText(nf.format(ewBill.getChiSoCuoiDien()-ewBill.getChiSoDauDien()));
        eFee.setText(nf.format(ewBill.calElectricFee(ewBill.getChiSoDauDien(),ewBill.getChiSoCuoiDien())) + " VND");
        wStart.setText(nf.format(ewBill.getChiSoDauNuoc()));
        wEnd.setText(nf.format(ewBill.getChiSoCuoiNuoc()));
        wNum.setText(nf.format(ewBill.getChiSoCuoiNuoc()-ewBill.getChiSoDauNuoc()));
        wFee.setText(nf.format(ewBill.calWaterFee(ewBill.getChiSoDauNuoc(), ewBill.getChiSoCuoiNuoc())) + " VND");
        total.setText(ewBill.getTotal() + " VND");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        date.setText(formatter.format(ewBill.getCreateDay()));
    }
    
    private void ExportPDF(String billID) {
        ewBill = new ElectricAndWaterBill();
        ewBill.getInfoBaseID(billID);
        try {
       	//Create Document instance.
	Document document = new Document();
 
	//Create OutputStream instance.
	OutputStream outputStream = 
		new FileOutputStream(new File("D:\\HoaDonDienNuoc.pdf"));
 
	//Create PDFWriter instance.
        PdfWriter.getInstance(document, outputStream);
 
        //Open the document.
        document.open();
 
        //Add content to the document.
        
        addMetaData(document);
        addTitlePage(document, ewBill.getRoom().getRoomID(), ewBill.getApartment().getIDApartment(), ewBill.getChiSoDauDien(), ewBill.getChiSoCuoiDien(), ewBill.getChiSoCuoiDien()-ewBill.getChiSoDauDien(),ewBill.calElectricFee(ewBill.getChiSoDauDien(),ewBill.getChiSoCuoiDien()),ewBill.getChiSoDauNuoc(), ewBill.getChiSoCuoiNuoc(), ewBill.getChiSoCuoiNuoc()-ewBill.getChiSoDauNuoc(),ewBill.calElectricFee(ewBill.getChiSoDauNuoc(),ewBill.getChiSoCuoiNuoc()), ewBill.getTotal() );
        
        
 
        //Close document and outputStream.
        document.close();
        outputStream.close();
        
        showtification("Tạo file pdf thành công.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        openFile();
    }
    
    private static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }
    
    private static void addTitlePage(Document document, String roomID, String apartmentID, Double eStart, Double eEnd, Double eNum, Double eFee,Double wStart, Double wEnd, Double wNum, Double wFee,String total)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("                            Thong tin chi tiet hoa don", catFont));

        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "               Thong tin duoc tao boi: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(
                "             Hoa don tien dien nuoc phong: " + roomID,
                smallBold));
        preface.add(new Paragraph(
                "             Toa: " + apartmentID,
                smallBold));
        preface.add(new Paragraph(
                "               Dien: ",
                subFont));
        preface.add(new Paragraph(
                "                Chi so dau dien: " + eStart,
                smallBold));
        preface.add(new Paragraph(
                "                Chi so cuoi dien: " + eEnd,
                smallBold));
        preface.add(new Paragraph(
                "                So dien: " + eNum,
                smallBold));
        preface.add(new Paragraph(
                "                Tien dien: " + nf.format(eFee) + " VND",
                smallBold));
        preface.add(new Paragraph(
                "               Nuoc: ",
                subFont));
        preface.add(new Paragraph(
                "                Chi so dau nuoc: " + wStart,
                smallBold));
        preface.add(new Paragraph(
                "                Chi so cuoi nuoc: " + wEnd,
                smallBold));
        preface.add(new Paragraph(
                "                So nuoc: " + wNum,
                smallBold));
        preface.add(new Paragraph(
                "                Tien nuoc: " + nf.format(wFee) + " VND",
                smallBold));
        preface.add(new Paragraph(
                "          ---------------------------------------------",
                smallBold));
        preface.add(new Paragraph(
                "                Tong cong: " + total + " VND",
                smallBold));
        document.add(preface);
        // Start a new page
        document.newPage();
    }
    
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    
    private void showtification(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
	alert.setContentText(msg);
	alert.showAndWait();
    }
    
    private void openFile() {
        try {

		if ((new File("D:\\HoaDonDienNuoc.pdf")).exists()) {

			Process p = Runtime
			   .getRuntime()
			   .exec("rundll32 url.dll,FileProtocolHandler D:\\HoaDonDienNuoc.pdf");
			p.waitFor();
				
		} else {

			System.out.println("File is not exists");

		}

		System.out.println("Done");

  	  } catch (Exception ex) {
		ex.printStackTrace();
	  }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
