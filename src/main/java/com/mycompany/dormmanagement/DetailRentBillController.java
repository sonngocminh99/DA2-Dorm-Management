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
import com.mycompany.dormmanagement.Model.RentBill;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author DVHHHTNT
 */
public class DetailRentBillController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 22,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 20,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.NORMAL);
    private RentBillPaneController rentBillPaneController;
    private RentBill rentbill;
    @FXML
    private Label ID, apartment, room, idemployee, nameemployee, idstudent, namestudent,date, total,status;
    @FXML
    private Button markAsDonebtn, exportButton;
    @FXML
    void backbtn(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
    }
    @FXML
    void updateStatus(ActionEvent event){
        rentbill = new RentBill();
        rentbill.updateStatusRentBill(ID.getText());
        markAsDonebtn.setDisable(true);
        rentBillPaneController.refreshTable();
        
    }
    @FXML
    void exportFilePDF(){
         ExportPDF(ID.getText());
    }
    
    public void reciveData(String data, RentBillPaneController RentBillController){
        loadData(data);
        rentBillPaneController = RentBillController;
        
        
    }
    
    public void loadData(String billID){
        
        rentbill = new RentBill();
        rentbill.getInfoBaseIDRentBill(billID);
        if(rentbill.getStatus().equals("Đã thu")) markAsDonebtn.setDisable(true);
        ID.setText(rentbill.getBillID());
        apartment.setText(rentbill.getApartment().getIDApartment());
        room.setText(rentbill.getRoom().getRoomID());
        idemployee.setText(rentbill.getEmployee().getEmployeeID());
        nameemployee.setText(rentbill.getEmployee().getFullname());
        idstudent.setText(rentbill.getStudent().getStudentID());
        namestudent.setText(rentbill.getStudent().getFullName());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        date.setText(formatter.format(rentbill.getCreateDay()));
        total.setText(rentbill.getTotal());
        status.setText(rentbill.getStatus());
    }
    
    private void ExportPDF(String billID) {
        rentbill = new RentBill();
        rentbill.getInfoBaseIDRentBill(billID);
        try {
       	//Create Document instance.
	Document document = new Document();
 
	//Create OutputStream instance.
	OutputStream outputStream = 
		new FileOutputStream(new File("D:\\HoaDonThuePhong.pdf"));
 
	//Create PDFWriter instance.
        PdfWriter.getInstance(document, outputStream);
 
        //Open the document.
        document.open();
 
        //Add content to the document.
        
        addMetaData(document);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        addTitlePage(document, rentbill.getRoom().getRoomID(), rentbill.getApartment().getIDApartment(), rentbill.getEmployee().getEmployeeID(), rentbill.getEmployee().getFullname(), rentbill.getStudent().getStudentID(),rentbill.getStudent().getFullName(), formatter.format(rentbill.getCreateDay()),rentbill.getStatus(), rentbill.getTotal());
        
        
 
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
    
    private static void addTitlePage(Document document, String roomID, String apartmentID, String idEmployee, String nameEmployee, String idStdent,String nameStdent, String createDate, String Status, String total)
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
                "                ID nhan vien: " + idEmployee,
                smallBold));
        preface.add(new Paragraph(
                "                Ho va ten nhan vien: " + nameEmployee,
                smallBold));
        preface.add(new Paragraph(
                "                ID sinh vien: " + idStdent,
                smallBold));
        preface.add(new Paragraph(
                "                Ho va ten sinh vien: " + nameStdent,
                smallBold));
        preface.add(new Paragraph(
                "                Ngay lap tien phong: " + createDate,
                smallBold));
        if(Status.equals("Đã thu")) 
            Status = "Da thu";
        if(Status.equals("Chưa thu")) 
            Status = "Chua thu";
        preface.add(new Paragraph(
                "                Trang thai: " + Status,
                smallBold));
        
        preface.add(new Paragraph(
                "          ---------------------------------------------",
                smallBold));
        preface.add(new Paragraph(
                "                Tong cong: " + total,
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
