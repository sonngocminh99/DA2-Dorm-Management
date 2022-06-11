/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Apartment;
import com.mycompany.dormmanagement.Model.Employee;
import com.mycompany.dormmanagement.Model.Room;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import Utils.DataValidation;
/**
 * FXML Controller class
 *
 * @author DVHHHTNT
 */
public class AddRoomPaneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Apartment apartment;
    private Room room;
    private RoomPaneController roomPaneController;
    @FXML 
    private Button insertBtn;
    @FXML
    private Label cEndError;
    @FXML
    private TextField roomText,nostudentText,rentingpriceText,statusText;
    
    
    @FXML
    private ComboBox apartmentComboBox, typeComboBox;
    //hàm xử lý nút bấm quay lại
    @FXML
    void backbtn(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    //hàm xử lý khi văn bản thay đổi
    @FXML
    void TextChange(KeyEvent event){
        String cEndText;
        
        cEndText = rentingpriceText.getText().trim();
        //check if text field null notified error, if not invisible label error
        if(DataValidation.textFieldIsNull(rentingpriceText, cEndError, "Vui lòng không để trống")){
            cEndError.setVisible(true);
        } 
        // check if text field value is number, if not show the error
        else if(!DataValidation.textNumeric(rentingpriceText, cEndError, "Tiền thuê phòng phải là số")){
                cEndError.setVisible(true);
            }
            //check if text field value >0, if not show the error because renting price must larger than 0
            else if(Integer.parseInt(cEndText)<=0){
                    cEndError.setText("Tiền phòng phải > 0");
                    cEndError.setVisible(true);
                } else cEndError.setVisible(false);
        // if not have any error enable button "Thêm"
        if(cEndError.visibleProperty().get()==false){    
            insertBtn.setDisable(false);       
        } else {
            insertBtn.setDisable(true);
        }   
    }
    
    //hàm xử lý thêm phòng mới khi nhấn nút thêm mới
    @FXML
    void insertdata(ActionEvent event){
        //lấy dữ liệu từ các textfield người dùng nhập
        String apartmentName = apartmentComboBox.getValue().toString();
        String RoomText = roomText.getText();
        String NostudentText = nostudentText.getText();
        String roomstatus = statusText.getText();
        String roomtype = typeComboBox.getValue().toString();
        int RentingpriceText = (int)Double.parseDouble(rentingpriceText.getText()); 
        //xử lý thêm dữ liệu vào database
        apartment = new Apartment();
        apartment.getInfo(apartmentName);
        String noRoom = Integer.toString(Integer.parseInt(apartment.getNoRoom()) + 1);
        room = new Room(RoomText,apartment,NostudentText,roomstatus,roomtype,RentingpriceText);
        //int index = room.getLastRoomIndex()+ 1;
//        room.setRoomID(apartmentName+ index);
        try {
            room.insertRoomdata();
            apartment.updateNoRoom(apartmentName, noRoom);
           
        } catch (Exception e) {
            showNotification("Có lỗi xảy ra. Thêm không thành công.");
            closeStage(event);
        }
        showNotification("Thêm thành công.");
        roomPaneController.refreshTable();
        closeStage(event);   
    }
    
    //hàm xử lý khi chọn giá trị combobox
    @FXML
    void selectApartment(ActionEvent event){
        addDataIDRomText();
    }
    
    
    public void receiveData (RoomPaneController parentController)
    {
        roomPaneController = parentController;
    }
    //hảm hiển thị thông báo
    private void showNotification(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
	alert.setContentText(msg);
	alert.showAndWait();
    }
    
    //thêm dữ liệu vào combobox Type
    private void addDataTypeCombobox(){ 
        ObservableList<String> items = FXCollections.<String>observableArrayList("2","4","6","8");
        typeComboBox.getItems().addAll(items);
    }
    //thêm dữ liệu vào combobox
    private void addDataToCombobox(){
        apartment = new Apartment();
        ObservableList<String> items = FXCollections.<String>observableArrayList();       
        for(var item : apartment.getAllApartment(1)){
            items.add(item);
        }
        apartmentComboBox.setItems(items);
        apartmentComboBox.getSelectionModel().select(0);
        addDataIDRomText();
    }
    
    
    private void addDataIDRomText()  
    {
        apartment = new Apartment();
        room = new Room();  
        ObservableList<String> items = FXCollections.<String>observableArrayList();       
        String apartmentName = apartmentComboBox.getValue().toString();
        int index = room.getLastRoomIndex(apartmentName)+ 1;
        for(var item : apartment.getRoomNameBaseApartment(apartmentName)){
            items.add(item);
        }
        roomText.setText(apartmentName+index);
    }
    private void addDataLable()  
    {    
        nostudentText.setText("0");
        statusText.setText("Còn chỗ");
    }
    private void DrawUI(){
        addDataToCombobox();
        addDataTypeCombobox();
        addDataLable();
        typeComboBox.getSelectionModel().selectFirst();
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
