/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.ElectricAndWaterBill;
import com.mycompany.dormmanagement.Model.Room;
import com.mycompany.dormmanagement.Model.Student;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/*
 * FXML Controller class
 *
 * @author DVHHHTNT
 */
public class DetailRoomPaneController implements Initializable {

    /*
     *
     * Initializes the controller class.
     */
    private RoomPaneController parentController;
    private Room room;
    @FXML
    private TableView dataTableView;
    @FXML
    private TableColumn idstudentCol, nameCol,genderCol,universityCol,toolCol;
    @FXML
    private Label apartmentLable, roomLable, typeLable, nostudentLable, statusLable, rentingpriceLable;
    //xử lý khi nhấn nút quay lại
    @FXML        
    void backbtn(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
  
    public void setDetailRoom (String data,RoomPaneController controller){
        loadData(data);
        parentController = controller;
        
    }
    
    //lấy dữ liệu và hiển thị ra các label
    public void loadData(String billID){
        
        room = new Room();
        room.getInfo(billID);
        apartmentLable.setText(room.getApartment().getIDApartment());
        roomLable.setText(room.getRoomID());
        typeLable.setText(room.getType());
        nostudentLable.setText(room.getNoStudent());
        statusLable.setText(room.getStatus());
        rentingpriceLable.setText(String.valueOf(room.getRentingPrice()));
        initTableView(dataTableView); 
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
                        Button btnLeave = new Button("",new ImageView("/Image/leave.png"));
                        btnLeave.setStyle("-fx-background-color: transparent;");
                        
                        btnLeave.setOnAction((ActionEvent event) -> {
                            int index = getIndex();
                            String idRoom = roomLable.getText();
                            String idStudent = (String) idstudentCol.getCellObservableValue(index).getValue();
                            leaveRoom(idRoom,idStudent);
                            
                        });
                        HBox btnManage = new HBox(btnLeave);
                        btnManage.setStyle("-fx-alignment:center");                   
                        setGraphic(btnManage);
                    }
                }
            };
            return cell;         
        };

        toolCol.setCellFactory(cellFactory);
    }
    private void leaveRoom(String IDRoom,String IDStudent){
        Room crRoom = new Room();
        crRoom.getInfo(IDRoom);
        Student leaveStudent = new Student();
        crRoom.removeStudentFromRoom();
        leaveStudent.RemoveStudent(IDStudent);  
        showtification("Sinh viên rời phòng thành công");
        dataTableView.getItems().clear();
        addDataToTable(dataTableView, 1);
        parentController.refreshTable();
        
    
    }
     private void showtification(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
	alert.setContentText(msg);
	alert.showAndWait();
    }
    //tạo một table
    private void initTableView(TableView table){
        
        idstudentCol.setCellValueFactory(new MapValueFactory<>("IDStudent"));
        nameCol.setCellValueFactory(new MapValueFactory<>("Fullname"));
        genderCol.setCellValueFactory(new MapValueFactory<>("Gender"));
        universityCol.setCellValueFactory(new MapValueFactory<>("University"));
        
        idstudentCol.prefWidthProperty().bind(table.widthProperty().multiply(0.15));  
        nameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.35));
        genderCol.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        universityCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25)); 
        toolCol.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        addDataToTable(table,1); 
        addButtonToTable();
    }
    
    //hàm thêm dữ liệu vào table
    private void addDataToTable(TableView table,int option){      
        room = new Room();
        String roomid = roomLable.getText().toString();
        table.getItems().addAll(room.getStudentRoom(roomid,1));

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
}
