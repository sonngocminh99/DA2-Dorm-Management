/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dormmanagement;

import Utils.DataValidation;
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

/**
 * FXML Controller class
 *
 * @author DVHHHTNT
 */
public class EditRoomPaneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Room room;
    private RoomPaneController roomPaneController;
    @FXML
    private Label apartmentLabel, roomLabel, statusLable,nostudentLable,rentPriceError;
    @FXML
    private TextField  rentingpriceText; 
    @FXML
    private ComboBox typeComboBox;
    @FXML
    private Button updateBtn;
    //xử lý nút bấm quay lại
    @FXML
    void backbtn(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    //xử lý lấy dữ liệu
    public void setDetailRoom (String  data){
        loadData(data);
    }
    public void receiveData (RoomPaneController parentController)
    {
        roomPaneController = parentController;
    }
    
    //lấy dữ liệu phòng bằng ID
    public void loadData(String billID){
        room = new Room();
        room.getInfo(billID);
        apartmentLabel.setText(room.getApartment().getIDApartment());
        roomLabel.setText(room.getRoomID());
        nostudentLable.setText(room.getNoStudent());
        statusLable.setText(room.getStatus());
        typeComboBox.setValue(room.getType());
        rentingpriceText.setText(String.valueOf(room.getRentingPrice()));
    }
    
    //xử lý nút cập nhập
    @FXML
    public void updatedata(ActionEvent event)
    {
        String roomtype = typeComboBox.getValue().toString();
        int RentingpriceText = (int)Double.parseDouble(rentingpriceText.getText());
        
        try {
            room.insertdatatoType(room.getRoomID(),roomtype,RentingpriceText);
        } catch (Exception e) {
            showNotification("Có lỗi xảy ra. Sửa không thành công.");
            closeStage(event);
        }
        showNotification("Sửa thành công.");
        roomPaneController.refreshTable();
        closeStage(event);
    }
    
    //xử lý sự kiện văn bản thay đổi
     @FXML
    void TextChange(KeyEvent event){
        String cEndText;
        
        cEndText = rentingpriceText.getText().trim();
        //check if text field null notified error, if not invisible label error
        if(DataValidation.textFieldIsNull(rentingpriceText, rentPriceError, "Vui lòng không để trống")){
            rentPriceError.setVisible(true);
        } 
        // check if text field value is number, if not show the error
        else if(!DataValidation.textNumeric(rentingpriceText, rentPriceError, "Tiền thuê phòng phải là số")){
                rentPriceError.setVisible(true);
            }
            //check if text field value >0, if not show the error because renting price must larger than 0
            else if(Integer.parseInt(cEndText)<=0){
                    rentPriceError.setText("Tiền phòng phải > 0");
                    rentPriceError.setVisible(true);
                } else rentPriceError.setVisible(false);
        // if not have any error enable button "Thêm"
        if(rentPriceError.visibleProperty().get()==false){    
            updateBtn.setDisable(false);       
        } else {
            updateBtn.setDisable(true);
        }   
    }
    
    //hiển thị thông báo
    private void showNotification(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
	alert.setContentText(msg);
	alert.showAndWait();
    }
    private void closeStage(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    //hàm xử lý thêm dữ liệu vào combobox
    private void addDataTypeCombobox(){ 
        ObservableList<String> items = FXCollections.<String>observableArrayList("2","4","6","8");
        typeComboBox.getItems().addAll(items);
    }
    private void DrawUI(){
        addDataTypeCombobox();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DrawUI();
    }    
    
}
