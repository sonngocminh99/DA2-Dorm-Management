/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Account;
import com.mycompany.dormmanagement.Model.Apartment;
import com.mycompany.dormmanagement.Model.ElectricAndWaterBill;
import com.mycompany.dormmanagement.Model.Employee;
import com.mycompany.dormmanagement.Model.Room;
import com.mycompany.dormmanagement.Model.Student;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;



/**
 * FXML Controller class
 *
 * @author Admin
 */
public class RoomArrangementFormController implements Initializable {

    /**
     * Initializes the controller class.
     */
//    private TextFields;
    private StudentPaneController studentPaneController;
    private Student student;
    private Apartment apartment;
    private Room room;
    @FXML
    private ComboBox apartmentComboBox, roomComboBox;
    @FXML
    private TextField nameText, genderText;
    @FXML
    private Button cancleBtn, addBtn;
    //xử lý việc chọn combobox của người dùng
    @FXML
    void selectHandle(ActionEvent event){
        room = new Room();
        ObservableList<String> items = FXCollections.<String>observableArrayList();
        //kiểm tra nếu giá trị combobox khác null thì thêm vào items
        if (apartmentComboBox.getValue() != null) {
        for(var item : room.getRoomAvailable(apartmentComboBox.getValue().toString())){
            items.add(item);
        }
        // lấy dữ liệu các phòng dựa vào giá trị của tòa
        roomComboBox.getItems().clear();
        roomComboBox.getItems().addAll(items);
        roomComboBox.getSelectionModel().select(0);
        }
    }
    // xử lý nút quay trởi lại
    @FXML
    void backbtn(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    //xử lý sự kiện thay đổi văn bản
    @FXML
    void TextChange(KeyEvent event){
        String name = nameText.getText();
        if(name.isEmpty()){
            genderText.setText("Nam");
        } else {
            
            student = new Student();
            student.getInfo(name);
            genderText.setText(student.getGender());
        }
        String gender = genderText.getText();
        //kiểm tra nếu gander khác rỗng thì render lại apartmentCombobox và roomCombobox
        if (!gender.isEmpty()) {
            try {
                addApartmentToCombobox(apartmentComboBox);
                apartmentComboBox.getSelectionModel().select(0);
                addRoomToCombobox(roomComboBox);
                roomComboBox.getSelectionModel().select(0);
            } catch(NullPointerException ex) {
                Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
    //xử lý nút thêm sinh viên
    @FXML
    void insertData(ActionEvent event){
        //lấy dữ liệu từ các text box
        String name = nameText.getText();
        String apartmentName = apartmentComboBox.getValue().toString();
        String roomName = roomComboBox.getValue().toString();
        apartment = new Apartment();
        apartment.getInfo(apartmentName);
        room = new Room();
        room.getInfo(roomName);
        student = new Student();
        student.getInfo(name);
        //gọi hàm addStudent và UpdateRoom
        try {
            student.updateRoom(room.getRoomID());
            room.addStudentToRoom();
        } catch (Exception e) {
            showNotification("Có lỗi xảy ra. Thêm không thành công.");
            closeStage(event);
        }
        //render lại table sau khi thêm 
        studentPaneController.refreshTable();
        showNotification("Thêm thành công.");
        closeStage(event);
        
        
    }
    public void receiveData (StudentPaneController parentController)
    {
        studentPaneController = parentController;
    }
    // hàm hiển thị thông báo
    private void showNotification(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
	alert.setContentText(msg);
	alert.showAndWait();
    }
    //hàm tự động điền tên sinh viên
    private void autoCompleteText(TextField textField) {
        student = new Student();
        ObservableList<String> items = FXCollections.<String>observableArrayList();
        //lấy tất cả các sinh viên chưa xếp phòng và có điều kiện hợp lệ
        for(var item : student.getAllEmptyStudent()){
            items.add(item);
        }
        
        TextFields.bindAutoCompletion(textField, items);
    }
    //hàm thêm dữ liệu tòa vào combobox
    private void addApartmentToCombobox(ComboBox comboBox){
        comboBox.getItems().clear();
        apartment = new Apartment();
        String gender = genderText.getText();
        System.out.println("gender: " + gender);
        ObservableList<String> items = FXCollections.<String>observableArrayList();
        for(var item : apartment.getApartmentWithGender(gender)){
            items.add(item);
        }
        comboBox.getItems().addAll(items);
        
        
    }
    //hàm thêm dữ liệu phòng vào combobox
    private void addRoomToCombobox(ComboBox comboBox){
        comboBox.getItems().clear();
        String crApartment = apartmentComboBox.getValue().toString();
        room = new Room();
        ObservableList<String> items = FXCollections.<String>observableArrayList();
        for(var item : room.getRoomAvailable(crApartment)){
            items.add(item);
        }
        comboBox.getItems().addAll(items);
        
    }
    
    private void closeStage(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    
    
    private void DrawUI(){
        genderText.setText("Nam");
        addApartmentToCombobox(apartmentComboBox);
        apartmentComboBox.getSelectionModel().select(0);
//      
        addRoomToCombobox(roomComboBox);
        roomComboBox.getSelectionModel().select(0); 
        autoCompleteText(nameText);
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DrawUI();
    }    
    
}
