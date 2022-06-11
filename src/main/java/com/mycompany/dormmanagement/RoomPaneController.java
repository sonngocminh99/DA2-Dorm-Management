/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import com.mycompany.dormmanagement.Model.Apartment;
import com.mycompany.dormmanagement.Model.Room;
import com.mycompany.dormmanagement.Model.Student;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import se.alipsa.ymp.*;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class RoomPaneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Apartment apartment;
    private Room room;
    @FXML
    private TableView dataTableView;
    @FXML
    private ComboBox apartmentComboBox;
    @FXML
    private TableColumn indexCol, nostudentCol,statusCol,typeCol,toolCol;
    @FXML
    private CheckBox allBox,doneBox,unDoneBox;
    @FXML
    private HBox box;
    @FXML
    private TextField searchText;
    @FXML
    private Button showAllBtn;
    //xử lý khi chọn giá trị combobox
    @FXML
    void selectHandle(ActionEvent event){
        if(searchText.getText().isEmpty()){
        dataTableView.getItems().clear();
        if(allBox.isSelected()){
        dataTableView.getItems().clear();
        addDataToTable(dataTableView,1);
        }
        if(doneBox.isSelected()){
        dataTableView.getItems().clear();
        addDataToTable(dataTableView,2);
        }
        if(unDoneBox.isSelected()){
        dataTableView.getItems().clear();
        addDataToTable(dataTableView,3);
        }
        }else tableSearch();
    }
    //xử lý khi chọn các checkbox
    @FXML
    void checkBoxHandles(ActionEvent event){
        String keyWord = searchText.getText();
        room = new Room();
        String crApartment = apartmentComboBox.getValue().toString().substring(4);
        if(event.getSource()== allBox){
            doneBox.setSelected(false);
            unDoneBox.setSelected(false);
            //clear table sau đó thêm lại dữ liệu mới theo giá trị checkbox
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,1);
            else dataTableView.getItems().addAll(room.getSearchRoom(crApartment, 1,keyWord));
        }
        if(event.getSource()== doneBox){
            allBox.setSelected(false);
            unDoneBox.setSelected(false);
            //clear table sau đó thêm lại dữ liệu mới theo giá trị checkbox
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,2);
            else dataTableView.getItems().addAll(room.getSearchRoom(crApartment, 2,keyWord));
        }
        if(event.getSource()==unDoneBox){
            doneBox.setSelected(false);
            allBox.setSelected(false);
            //clear table sau đó thêm lại dữ liệu mới theo giá trị checkbox
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,3);
            else dataTableView.getItems().addAll(room.getSearchRoom(crApartment, 3, keyWord));
        } 
    }
    //xử lý khi văn bản search thay đổi
    @FXML
    void textChange(KeyEvent event){
       tableSearch();
       showAllBtn.setVisible(true);
       
    }
    //hàm lấy dữ liệu tất cả sinh viên
    @FXML
    void showAll(ActionEvent event){
          dataTableView.getItems().clear();
          if(allBox.isSelected()) addDataToTable(dataTableView, 1);
          else if(doneBox.isSelected()) addDataToTable(dataTableView, 2);
          else addDataToTable(dataTableView, 3);
          searchText.clear();
          showAllBtn.setVisible(false);
    }
    //xử lý khi nhấn nút thêm phòng mới
    @FXML
    void addRoom(ActionEvent event){
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/addRoomPane.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Thêm phòng mới");
        AddRoomPaneController controller= loader.getController();
        controller.receiveData(this);
        stage.setScene(new Scene(root));
        stage.show();   
        } catch (IOException e) {
        }
        
    }
    
    private void showNotification(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
	alert.setContentText(msg);
	alert.showAndWait();
    }
    
    //hàm render lại table
    public void refreshTable(){
        //kiểm tra giá trị các checkbox và lấy dữ liệu theo giá trị đó
        if(searchText.getText().isEmpty()){
            dataTableView.getItems().clear();
        if(allBox.isSelected()){       
            addDataToTable(dataTableView,1);
        }
        if(doneBox.isSelected()){     
            addDataToTable(dataTableView,2);
        }
        if(unDoneBox.isSelected()){      
            addDataToTable(dataTableView,3);
        }
        }else tableSearch();   
    }
    
    //hàm xử lý khi tìm kiếm bằng thanh search
    private void tableSearch(){
       String keyWord = searchText.getText();
       dataTableView.getItems().clear();
       room = new Room();
       //lấy dữ liệu tòa từ combobox
       String crApartment = apartmentComboBox.getValue().toString().substring(4);
       //kiểm tra giá trị các checkbox và lấy dữ liệu dựa trên thanh search, checkbox và giá trị tòa
       if(allBox.isSelected()) { dataTableView.getItems().addAll(room.getSearchRoom(crApartment, 1, keyWord));
       } else if(doneBox.isSelected()){ dataTableView.getItems().addAll(room.getSearchRoom(crApartment, 2, keyWord));
       } else { dataTableView.getItems().addAll(room.getSearchRoom(crApartment, 3, keyWord));
       }
    }
    //hàm tạo table
    private void initTableView(TableView table){
        
        indexCol.setCellValueFactory(new MapValueFactory<>("idroom"));
        nostudentCol.setCellValueFactory(new MapValueFactory<>("nostudent"));
        statusCol.setCellValueFactory(new MapValueFactory<>("status"));
        typeCol.setCellValueFactory(new MapValueFactory<>("type"));
        
        indexCol.prefWidthProperty().bind(table.widthProperty().multiply(0.17));  
        nostudentCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        statusCol.prefWidthProperty().bind(table.widthProperty().multiply(0.3));
        typeCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2)); 
        toolCol.prefWidthProperty().bind(table.widthProperty().multiply(0.13));
        addDataToTable(table,1);
        
        
    }
    
    //hàm thêm dữ liệu vào table (option là giá trị tương ứng của checkbox)
    private void addDataToTable(TableView table,int option){      
        room = new Room();
        String crApartment = apartmentComboBox.getValue().toString().substring(4);   
        // lấy dữ liệu tùy vào các giá trị của checkbox
        switch (option) {
                case 1:
                    table.getItems().addAll(room.getRoom(crApartment,option));
                    addButtonToTable();
                    break;
                case 2:
                    table.getItems().addAll(room.getRoom(crApartment,option));
                    addButtonToTable();
                    break;
                case 3:
                    table.getItems().addAll(room.getRoom(crApartment,option));
                    addButtonToTable();
                    break;
                default:
                    table.getItems().addAll(room.getRoom(crApartment,1));
                    addButtonToTable();
                    break;
            }
    }
    
    //hàm thêm các nút vào từng hàng trên table
    private void addButtonToTable() {     
        Callback<TableColumn<Object, String>, TableCell<Object, String>> cellFactory = (TableColumn<Object, String> param) -> {
            // tạo ra hàng chứa các nút
            final TableCell<Object, String> cell = new TableCell<Object, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //kiểm tra item có null hay không nếu không thì thêm các nút
                    if (empty) {
                        setGraphic(null);
                    } else {
                        //tạo các nút bâm với các hình ảnh icon và background color
                        Button btnDetail = new Button("", new ImageView("/Image/viewdetails.png"));
                        btnDetail.setStyle("-fx-background-color: transparent;");
                        Button btnEdit = new Button("",new ImageView("/Image/edit.png"));
                        btnEdit.setStyle("-fx-background-color: transparent;");
                        Button btnDelete = new Button("",new ImageView("/Image/delete.png"));
                        btnDelete.setStyle("-fx-background-color: transparent;");
                        //cài sự kiện khi nhấn nút cho từng nút bấm
                        btnDetail.setOnAction((ActionEvent event) -> {
                            int index = getIndex();
                            String data = (String) indexCol.getCellObservableValue(index).getValue();
                            changeRoomDetail(data);
                        });
                        btnEdit.setOnAction((ActionEvent event) -> {
                            int index = getIndex();
                            String data = (String) indexCol.getCellObservableValue(index).getValue();
                            changeRoomEdit(data);
                            
                        });
                        btnDelete.setOnAction((ActionEvent event) -> {
                            int index = getIndex();
                            String data = (String) indexCol.getCellObservableValue(index).getValue();
                                changeRoomDelete(data);
                        });
                        HBox btnManage = new HBox(btnDetail, btnEdit, btnDelete);
                        btnManage.setStyle("-fx-alignment:center");                   
                        setGraphic(btnManage);
                    }
                }
            };
            return cell;         
        };

        toolCol.setCellFactory(cellFactory);
    }
    
    //lấy dữ liệu thêm vào combobox
    private void addDataToCombobox(ComboBox comboBox){
        apartment = new Apartment();
        ObservableList<String> items = FXCollections.<String>observableArrayList();
        //lấy dữ liệu tất cả các tòa rồi thêm vào combobox
        for(var item : apartment.getAllApartment(1)){
            items.add("Tòa "+ item);
        }
        comboBox.getItems().addAll(items);
        
    }
    
    //xử lý sự kiện khi bấm vào nút detail của từng item
    public void changeRoomDetail(String data)
    {
        try{
        //mở một màn hình thông tin mới
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/detailRoomPane.fxml"));
        Parent roomdetial = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Chi tiết thông tin phòng");
        //set Controller cho màn hình mới
        DetailRoomPaneController detailRoomPaneController = loader.getController();
        detailRoomPaneController.setDetailRoom(data,this);
        stage.setScene(new Scene(roomdetial));
        stage.show();
        } catch (IOException e) {
            System.out.println(e);
        } 
    }
    
    //xử lý sự kiện khi bấm vào nút add của từng item
    private void changeRoomEdit(String data){
        try {
        //mở một màn hình thông tin mới
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/editRoomPane.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Chỉnh sửa thông tin phòng");
        EditRoomPaneController editRoomPaneController = loader.getController();
        editRoomPaneController.setDetailRoom(data);
        //set Controller cho màn hình mới
        EditRoomPaneController controller= loader.getController();
        controller.receiveData(this);
        editRoomPaneController.receiveData(this);
        stage.setScene(new Scene(root));
        stage.show();   
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    //xử lý sự kiện khi bấm vào nút delete của từng item
    private void changeRoomDelete(String data){
         Student student = new Student();
            student.updateStudentRemoveFromRoom(data);
        try {
            room.deleteData(data);
            showNotification("Xóa thành công");
            refreshTable();
        } catch (Exception e) {
            showtification("Có lỗi xảy ra. Thêm không thành công.");
        }
        
        System.out.println("success");
    }
    
    //hàm hiển thị thông báo
    private void showtification(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
	alert.setContentText(msg);
	alert.showAndWait();
    }
    private void DrawUI(){
        allBox.setSelected(true);
        addDataToCombobox(apartmentComboBox);
        apartmentComboBox.getSelectionModel().select(0);
        initTableView(dataTableView);
        
          
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DrawUI();
    }    
    
}
