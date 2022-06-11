/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Apartment;
import com.mycompany.dormmanagement.Model.ElectricAndWaterBill;
import com.mycompany.dormmanagement.Model.Employee;
import com.mycompany.dormmanagement.Model.Room;
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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import Utils.DataValidation;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class AddEWBillController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    private EwBillPaneController eWBillController;
    private ElectricAndWaterBill ewBill;
    @FXML
    private Button insertBtn;
    @FXML
    private Label eEndError,wEndError;
    @FXML
    private TextField eStart,eEnd,wStart,wEnd,total;
    private Apartment apartment;
    private Room room;
    private Employee employee;
    @FXML
    private ComboBox apartmentComboBox,roomComboBox;
    @FXML
    void back(ActionEvent event){
        closeStage(event);
    }
   
    @FXML
    void TextChange(KeyEvent event){
        String eEndText,wEndText;
        double eEndNum = 0,wEndNum = 0,eStartNum = 0,wStartNum = 0;
        eEndText = eEnd.getText().trim();
        wEndText = wEnd.getText().trim();
        try {
            eStartNum = Double.parseDouble(eStart.getText());
            wStartNum = Double.parseDouble(wStart.getText()); 
            eEndNum = Double.parseDouble(eEndText);
            wEndNum = Double.parseDouble(wEndText);
        
        } catch (NumberFormatException e) {
           
        }
        
        
        if(DataValidation.textFieldIsNull(eEnd, eEndError, "Vui lòng không để trống")){
            eEndError.setVisible(true);
        }else if(!DataValidation.textNumeric(eEnd, eEndError, "Chỉ số điện phải là số")){
            eEndError.setVisible(true);
        } else if(eEndNum<eStartNum){
            eEndError.setText("Số điện cuối phải lớn hơn hoặc bằng số điện đầu");
            eEndError.setVisible(true);
        } else eEndError.setVisible(false);
        if(DataValidation.textFieldIsNull(wEnd, wEndError, "Vui lòng không để trống")){
            wEndError.setVisible(true);
        } else if(!DataValidation.textNumeric(wEnd, wEndError, "Chỉ số nước phải là số")){
            wEndError.setVisible(true);
        } else if(wEndNum<wStartNum){
            wEndError.setText("Số điện cuối phải lớn hơn hoặc bằng số điện đầu");
            wEndError.setVisible(true);
        } else wEndError.setVisible(false);
        
        if(eEndError.visibleProperty().get()==false && wEndError.visibleProperty().get()==false){
            double totalFee = autoFillTotalFee();
            if(totalFee==-1) {
                total.setText("");
                insertBtn.setDisable(true);
            }
            else {
                total.setText(Double.toString(totalFee));
                insertBtn.setDisable(false);
            }
        } else {
            total.setText("");
            insertBtn.setDisable(true);
        }
        
        
    }
    @FXML
    void insertData(ActionEvent event){
        String eEndText = eEnd.getText().trim();
        String wEndText = wEnd.getText().trim();
        double eStartNum = Double.parseDouble(eStart.getText());
        double wStartNum = Double.parseDouble(wStart.getText()); 
        double eEndNum = Double.parseDouble(eEndText);
        double wEndNum = Double.parseDouble(wEndText);
        String apartmentName = apartmentComboBox.getValue().toString().substring(4);
        String roomName = roomComboBox.getValue().toString();
        apartment = new Apartment();
        apartment.getInfo(apartmentName);
        room = new Room();
        room.getInfo(roomName);
        Date createDay = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String totalFee = total.getText();
        employee = new Employee();
        employee.getInfoBaseAccountID(LoginFormController.currentUser.getIDAccount());
        ewBill = new ElectricAndWaterBill(eStartNum, eEndNum, wStartNum, wEndNum, "", employee, apartment, room, createDay, totalFee, "Chưa thu");
        int index = ewBill.getLastBillIDIndex() + 1;
        ewBill.setBillID("EW"+ index);
        try {
            ewBill.insertNewEWBill();
        } catch (Exception e) {
            showNotification("Có lỗi xảy ra. Thêm không thành công.");
            closeStage(event);
        }
        eWBillController.refreshTable();
        showNotification("Thêm thành công.");
        closeStage(event);
        
        
    }
    @FXML
    void selectApartment(ActionEvent event){
        addDataToRoomComboBox();
    }
    @FXML
    void selectRoom(ActionEvent event){
        try {
            autoFillEStartAndWStart(); 
            double totalFee = autoFillTotalFee();
            if(totalFee==-1) {
                total.setText("");
                insertBtn.setDisable(true);
            }
            else {
                total.setText(Double.toString(totalFee));
                insertBtn.setDisable(false);
            }
        } catch (Exception e) {
        }
        
    }
    private void closeStage(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    private void showNotification(String msg){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
	alert.setContentText(msg);
	alert.showAndWait();
    }
    public void receiveData(EwBillPaneController receiveController){
        eWBillController = receiveController;
    }
    private void addDataToComboBox(){
        apartment = new Apartment();
        ObservableList<String> items = FXCollections.<String>observableArrayList();       
        for(var item : apartment.getAllApartment(1)){
            items.add("Tòa "+ item);
        }
        apartmentComboBox.setItems(items);
        apartmentComboBox.getSelectionModel().select(0);
        addDataToRoomComboBox();
                    
    }
    private void addDataToRoomComboBox(){
        ObservableList<String> items = FXCollections.<String>observableArrayList();
        String apartmentName = apartmentComboBox.getValue().toString().substring(4);
        for(var item : apartment.getRoomNameBaseApartment(apartmentName)){
            items.add(item);
        }
        roomComboBox.setItems(items);
        roomComboBox.getSelectionModel().select(0);  
    
    }
    private void autoFillEStartAndWStart(){
        ewBill = new ElectricAndWaterBill();
        String roomName = roomComboBox.getValue().toString();
        String lastEStart = Double.toString(ewBill.getLastEValueOfRoom(roomName));
        String lastWStart = Double.toString(ewBill.getLastWValueOfRoom(roomName));
        eStart.setText(lastEStart);
        wStart.setText(lastWStart);
        
        
    }
    
    private double autoFillTotalFee(){
        ewBill = new ElectricAndWaterBill();
        double eNumber = Double.parseDouble(eEnd.getText()) - Double.parseDouble(eStart.getText());
        double wNumber = Double.parseDouble(wEnd.getText()) - Double.parseDouble(wStart.getText());
        if(eNumber<0 || wNumber<0) return -1;
        else {
            double totalFee = ewBill.totalFee(ewBill.calElectricFee(eNumber), ewBill.calWaterFee(wNumber));
            return totalFee;
        }      
    }
    private void DrawUI(){
        addDataToComboBox();
        autoFillEStartAndWStart();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DrawUI();
    }    
    
}
