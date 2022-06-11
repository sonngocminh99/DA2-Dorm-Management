/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Account;
import com.mycompany.dormmanagement.Model.ElectricAndWaterBill;
import com.mycompany.dormmanagement.Model.Room;
import com.mycompany.dormmanagement.Model.Student;
import connect.DataConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class StudentPaneController implements Initializable {
    private Student student;
    private Room room;
    @FXML
    private TableView dataTableView;
    @FXML
    private Label totalLabel;
    @FXML
    private TableColumn idCol, nameCol, genderCol, statusCol, universityCol, sYearCol, eYearCol, toolCol, idRoomCol;
    @FXML
    private CheckBox allBox,doneBox,unDoneBox;
    @FXML
    private HBox box;
    @FXML
    private Button showAllBtn;
    @FXML
    private TextField searchText;
    
    @FXML
    void checkBoxHandles(ActionEvent event){
      String keyWord = searchText.getText();
      if(event.getSource()== allBox){
            doneBox.setSelected(false);
            unDoneBox.setSelected(false);
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,1);
            else dataTableView.getItems().addAll(student.getSearchStudent(1, keyWord));
      }
      if(event.getSource()== doneBox){
            allBox.setSelected(false);
            unDoneBox.setSelected(false);
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,2);
            else dataTableView.getItems().addAll(student.getSearchStudent(2, keyWord));
      }
      if(event.getSource()==unDoneBox){
            doneBox.setSelected(false);
            allBox.setSelected(false);
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,3);
            else dataTableView.getItems().addAll(student.getSearchStudent(3, keyWord));
      } 
    }
    
    @FXML
    void textChange(KeyEvent event){
       tableSearch();
       showAllBtn.setVisible(true);
       
    }
    
    @FXML
    void showAll(ActionEvent event){
          dataTableView.getItems().clear();
          if(allBox.isSelected()) addDataToTable(dataTableView, 1);
          else if(doneBox.isSelected()) addDataToTable(dataTableView, 2);
          else addDataToTable(dataTableView, 3);
          searchText.clear();
          showAllBtn.setVisible(false);
    }
    @FXML
    void roomArrangementHandleClicked(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/roomArrangementForm.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Arrangement room");
        RoomArrangementFormController roomArrangementFormController = loader.getController();
        roomArrangementFormController.receiveData(this);
        stage.setScene(new Scene(root));
        stage.show();   
        } catch (IOException e) {
            System.out.print(e);
        }
    }
    
    @FXML
    private void autoRoomArrangementHandleClicked(ActionEvent event) {
        autoArrangement();
    }
    
    @FXML
    void addStudentHandleClicked(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/addStudent.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add new student");
        AddStudentController addStudentController = loader.getController();
        addStudentController.receiveData(this);
        stage.setScene(new Scene(root));
        stage.show();   
        } catch (IOException e) {
            System.out.print(e);
        }
    }
    
    
    
    private void setTextTotal() {
        student = new Student();
        totalLabel.setText(Integer.toString(student.getTotalStudents()));
    }
    
    private void tableSearch(){
       String keyWord = searchText.getText();
       dataTableView.getItems().clear();
       student = new Student();
       setTextTotal();
       if(allBox.isSelected()) { dataTableView.getItems().addAll(student.getSearchStudent(1, keyWord));
       } else if(doneBox.isSelected()){ dataTableView.getItems().addAll(student.getSearchStudent(2, keyWord));
       } else { dataTableView.getItems().addAll(student.getSearchStudent(3, keyWord));
       }
    }
    
    private void DrawUI(){
        allBox.setSelected(true);
        initTable(dataTableView);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DrawUI();
        
    }      
    
    private void initTable(TableView table) {
        idCol.setCellValueFactory(new MapValueFactory<>("id"));
        nameCol.setCellValueFactory(new MapValueFactory<>("name"));
        genderCol.setCellValueFactory(new MapValueFactory<>("gender"));
        statusCol.setCellValueFactory(new MapValueFactory<>("status"));
        universityCol.setCellValueFactory(new MapValueFactory<>("university"));
        sYearCol.setCellValueFactory(new MapValueFactory<>("sYear"));
        eYearCol.setCellValueFactory(new MapValueFactory<>("eYear"));
        idRoomCol.setCellValueFactory(new MapValueFactory<>("idRoom"));
        
        idCol.prefWidthProperty().bind(table.widthProperty().multiply(0.05));  
        nameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.16));
        genderCol.prefWidthProperty().bind(table.widthProperty().multiply(0.11)); 
        statusCol.prefWidthProperty().bind(table.widthProperty().multiply(0.12)); 
        universityCol.prefWidthProperty().bind(table.widthProperty().multiply(0.14)); 
        sYearCol.prefWidthProperty().bind(table.widthProperty().multiply(0.11));
        eYearCol.prefWidthProperty().bind(table.widthProperty().multiply(0.11));
        toolCol.prefWidthProperty().bind(table.widthProperty().multiply(0.12));
        idRoomCol.prefWidthProperty().bind(table.widthProperty().multiply(0.08));
        addDataToTable(table,1);
    }
    
    private void addDataToTable(TableView table,int option){      
        student = new Student(); 
        setTextTotal();
        switch (option) {
                case 1:
                    table.getItems().addAll(student.getStudent(option));
                    addButtonToTable();
                    break;
                case 2:
                    table.getItems().addAll(student.getStudent(option));
                    addButtonToTable();
                    break;
                case 3:
                    table.getItems().addAll(student.getStudent(option));
                    addButtonToTable();
                    break;
                default:
                    table.getItems().addAll(student.getStudent(1));
                    addButtonToTable();
                    break;
            }
    }
    
    public void refreshTable(){
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
    
    private void addButtonToTable() {     
        Callback<TableColumn<Object, String>, TableCell<Object, String>> cellFactory = (TableColumn<Object, String> param) -> {
            // make cell containing buttons
            final TableCell<Object, String> cell = new TableCell<Object, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        Button btnDetail = new Button("", new ImageView("/Image/viewdetails.png"));
                        btnDetail.setStyle("-fx-background-color: transparent;");
                        Button btnEdit = new Button("",new ImageView("/Image/edit.png"));
                        btnEdit.setStyle("-fx-background-color: transparent;");
                        Button btnDelete = new Button("",new ImageView("/Image/delete.png"));
                        btnDelete.setStyle("-fx-background-color: transparent;");
                        btnDetail.setOnAction((ActionEvent event) -> {
                            int index = getIndex();
                            String data = (String) idCol.getCellObservableValue(index).getValue();
                            changeStudentDetail(data);
                        });
                        btnEdit.setOnAction((ActionEvent event) -> {
                            int index = getIndex();
                            String data = (String) idCol.getCellObservableValue(index).getValue();
                            changeStudentEdit(data);
                        });
                        btnDelete.setOnAction((ActionEvent event) -> {
                            int index = getIndex();
                            String idStudent = (String) idCol.getCellObservableValue(index).getValue();
                            String idRoom = (String) idRoomCol.getCellObservableValue(index).getValue();
                            if (idRoom != null)
                                changeRoomDelete(idRoom);
                            changeStudentDelete(idStudent);
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
    
    public void changeStudentDetail(String data)
    {
        try{
   
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/detailStudent.fxml"));
        Parent studentdetial = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Chi tiết thông tin sinh viên");
        DetailStudentController detailStudentController = loader.getController();
        detailStudentController.setDetailStudent(data);
        stage.setScene(new Scene(studentdetial));
        stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
        
    }
    
    private void changeStudentEdit(String data){
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/editStudent.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Chỉnh sửa thông tin sinh viên");
        EditStudentController editStudentController = loader.getController();
        editStudentController.setDetailStudent(data);
        EditStudentController controller= loader.getController();
        controller.receiveData(this);
        editStudentController.receiveData(this);
        stage.setScene(new Scene(root));
        stage.show();   
        } catch (IOException e) {
            System.out.println(e);
        }
        
    }
    
    private void changeStudentDelete(String data){
        try {
            student.deleteData(data);     
        } catch (Exception e) {
            showtification("Có lỗi xảy ra. Xóa không thành công.");
        }
        showtification("Xoa thanh cong.");
        refreshTable();
        
    }
    
    private void changeRoomDelete(String data){
        room = new Room();
        try {
            room.getInfo(data);
            room.removeStudentFromRoom();
        } catch (Exception e) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    private void showtification(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
	alert.setContentText(msg);
	alert.showAndWait();
    }
    
    private void autoArrangement() {
        student = new Student();
        room = new Room();
        ArrayList<String> listStudentID = student.getIDEmptyStudent();
        try {
            for(var item : listStudentID){
               System.out.println(item);
            student.getInfoByID(item);     
            room.getInfo(room.getRoomAvailableWithGender(student.getGender()));
            student.updateRoom(room.getRoomID());
            room.addStudentToRoom();
        }
        showtification("Tự động xếp phòng thành công");
        refreshTable(); 
        } catch (Exception e) {
            showtification("Tự động xếp phòng không thành công");
        }
           
        
        
    }
        
        
    

    
}
