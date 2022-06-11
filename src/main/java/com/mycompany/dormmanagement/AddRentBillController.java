/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Apartment;
import com.mycompany.dormmanagement.Model.RentBill;
import com.mycompany.dormmanagement.Model.Employee;
import com.mycompany.dormmanagement.Model.Room;
import com.mycompany.dormmanagement.Model.Student;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DVHHHTNT
 */
public class AddRentBillController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private Apartment apartment;
    private Room room;
    private RentBillPaneController rentBillPaneController;
    private RentBill rentbill;
    private Employee employee;
    private Student student;
    @FXML
    private TextField roomText,nameText,totalText;
    @FXML
    private ComboBox apartmentComboBox,studentComboBox;
    @FXML
    void backbtn(ActionEvent event){
        closeStage(event);
    }
    
    @FXML
    void selectApartment(ActionEvent event){
        addDataStudentCombobox();
    }
    @FXML
    void selectStudent(ActionEvent event){
        
        String studentName  ="";
        try{
                studentName = studentComboBox.getValue().toString();
                addDataNameStudent(studentName) ;
                addDataIDRomText(studentName);
        }
        catch(Exception e){}
        
        
        
        addDataTotalText();
    }
    @FXML
    void insertdata(ActionEvent event){
        String apartmentName = apartmentComboBox.getValue().toString();
        String roomname = roomText.getText();
        String studentid = studentComboBox.getValue().toString();
        String totaltext = totalText.getText(); 
        apartment = new Apartment();
        apartment.getInfo(apartmentName);
        room = new Room();
        room.getInfo(roomname);
        employee = new Employee();
        employee.getInfoBaseAccountID(LoginFormController.currentUser.getIDAccount());
        student = new Student();
        student.getInfoByID(studentid);
        Date createDay = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        rentbill = new RentBill("",employee,room,apartment,student,createDay,totaltext,"Chưa thu");
        int index = rentbill.getLastBillIDIndex()+ 1;
        rentbill.setBillID("RB"+index);
        try {
            rentbill.insertRentBilldata();
        } catch (Exception e) {
            showNotification("Có lỗi xảy ra. Thêm không thành công.");
            closeStage(event);
        }
        rentBillPaneController.refreshTable();
        showNotification("Thêm thành công.");
        closeStage(event);   
    }
    private void showNotification(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
	alert.setContentText(msg);
	alert.showAndWait();
    }
    public void receiveData (RentBillPaneController parentController)
    {
        rentBillPaneController = parentController;
    }
    
    private void addDataToCombobox(){
        apartment = new Apartment();
        ObservableList<String> items = FXCollections.<String>observableArrayList();       
        for(var item : apartment.getAllApartment(1)){
            items.add(item);
        }
        apartmentComboBox.setItems(items);
        apartmentComboBox.getSelectionModel().select(0);
        addDataStudentCombobox();
        String studentName = studentComboBox.getValue().toString();
        addDataNameStudent(studentName) ;
        addDataIDRomText(studentName);
        addDataTotalText();
    }
    
    private void addDataStudentCombobox(){ 
        ObservableList<String> items = FXCollections.<String>observableArrayList();
        String apartmentName = apartmentComboBox.getValue().toString();
        for(var item : apartment.getStudentNameBaseRentBill(apartmentName)){
            items.add(item);
        }
        studentComboBox.setItems(items);
        studentComboBox.getSelectionModel().select(0);
    }
    private void addDataIDRomText(String studentName)  
    {
        student = new Student();
        
        String roomtext = student.getToIDRoomStudent(studentName);
        roomText.setText(roomtext);
    }
    
    private void addDataTotalText(){ 
        String apartmentName = apartmentComboBox.getValue().toString();
        String studentName = roomText.getText();
        String totaltext = String.valueOf(apartment.getTotalRentBill(apartmentName,studentName));
        totalText.setText(totaltext);
    }
    
    private void addDataNameStudent(String studentName)  
    {
        student = new Student();
        
        String roomtext = student.getToNameStudent(studentName);
        nameText.setText(roomtext);
    }
    
    private void DrawUI(){
        addDataToCombobox();
    }
    private void closeStage(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DrawUI();
    }     
    
}
