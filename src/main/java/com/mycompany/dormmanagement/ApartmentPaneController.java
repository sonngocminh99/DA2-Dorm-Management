/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;


import com.mycompany.dormmanagement.Model.Apartment;
import com.mycompany.dormmanagement.Model.Employee;
import com.mycompany.dormmanagement.Model.Room;
import com.mycompany.dormmanagement.Model.Student;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import Utils.DataValidation;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class ApartmentPaneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Apartment apartment;
    @FXML
    private CheckBox allBox,doneBox,unDoneBox;
    @FXML
    private TableView dataTableView;
    @FXML
    private TextField searchText,idTextField,name;
    @FXML
    private TableColumn IDCol,noRoomCol,genderCol,employeeCol,toolCol;
    @FXML
    private Button showAllBtn,addBtn;
    @FXML
    private ComboBox genderCombobox,employeeCombobox;
    @FXML
    private Label apartmentError;
    
    private void initTableView(TableView table){
        
        IDCol.setCellValueFactory(new MapValueFactory<>("id"));
        noRoomCol.setCellValueFactory(new MapValueFactory<>("room"));
        genderCol.setCellValueFactory(new MapValueFactory<>("gender"));
        employeeCol.setCellValueFactory(new MapValueFactory<>("employee"));
        
        
        IDCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2));  
        noRoomCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        genderCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2)); 
        employeeCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25)); 
        toolCol.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        addDataToTable(table,1);
        
    }
    @FXML
    void clearText(){
    clearAll();
    }
    @FXML
    void apartmentTextChange(){
        if(DataValidation.textFieldIsNull(idTextField, apartmentError, "Vui lòng không để trống")){
            apartmentError.setVisible(true);
            
        }else if(!DataValidation.textAlphabetUppercase(idTextField, apartmentError, "Tên tòa phải là chữ cái in hoa")){
            apartmentError.setVisible(true);
            
        } else apartmentError.setVisible(false);
        
        if(!apartmentError.isVisible()){
            addBtn.setDisable(false);
        } else addBtn.setDisable(true);
    
    }
    @FXML
    void checkHandle(ActionEvent event){
        String keyWord = searchText.getText();
    if(event.getSource()== allBox){
            doneBox.setSelected(false);
            unDoneBox.setSelected(false);
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,1);
            else dataTableView.getItems().addAll(apartment.getSearchApartment(1, keyWord));
            
      }
      if(event.getSource()== doneBox){
            allBox.setSelected(false);
            unDoneBox.setSelected(false);
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,2);
            else dataTableView.getItems().addAll(apartment.getSearchApartment(2, keyWord));
      }
      if(event.getSource()==unDoneBox){
            doneBox.setSelected(false);
            allBox.setSelected(false);
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,3);
            else dataTableView.getItems().addAll(apartment.getSearchApartment(3, keyWord));
      } }
    @FXML
    void textChange(){
        tableSearch(dataTableView);
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
    void addApartment(){
        apartment = new Apartment();
        Employee employee = new Employee();
        String ID = idTextField.getText().trim();
        String gender = genderCombobox.getValue().toString();
        String EmployeeID = employeeCombobox.getValue().toString();
        apartment.getInfo(ID);
        if(apartment.getIDApartment().isEmpty()) 
        {
        apartment.insertNewApartment(ID, gender, EmployeeID);
            showNotification("Thêm thành công");
            refreshTable();
            clearAll();
        }
        else showNotification("Tòa nhà này đã tồn tại");
    }
    @FXML
    void valueChange(){
        Employee employee = new Employee();
        try {
            employee.getInfoBaseID(employeeCombobox.getValue().toString());
            name.setText(employee.getFullname());
        } catch (Exception e) {
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
        }else tableSearch(dataTableView);   
    }
    private void clearAll(){
    idTextField.clear();
    genderCombobox.getSelectionModel().selectFirst();
    employeeCombobox.getSelectionModel().selectFirst();
    valueChange(); 
    apartmentError.setVisible(false);
    }
    private void showNotification(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
	alert.setContentText(msg);
	alert.showAndWait();
    }
    private void addDataToTable(TableView table, int option){
        apartment = new Apartment();
        ObservableList<Map<String, Object>> items =
        FXCollections.<Map<String, Object>>observableArrayList();
        ArrayList<String> listApartment = apartment.getAllApartment(option);
        Map<String,Object> item;
        for (String id : listApartment) {
            item = new HashMap<>();
            Employee employee = new Employee();
            apartment.getInfo(id);
            employee.getInfoBaseID(apartment.getIDEmployee());
            item.put("id", apartment.getIDApartment());
            item.put("room", apartment.getNoRoom());
            item.put("gender", apartment.getGender());
            item.put("employee", employee.getFullname());
            items.add(item);
        }
        table.getItems().addAll(items);
        addButtonToTable();
    
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
                        Button btnDelete = new Button("", new ImageView("/Image/delete.png"));
                        btnDelete.setStyle("-fx-background-color: transparent;");
                        btnDelete.setOnAction((ActionEvent event) -> {
                            int index = getIndex();
                            String apartment = IDCol.getCellObservableValue(index).getValue().toString();
                            try {
                                deleteApartment(apartment);
                                showNotification("Đã xóa");
                                refreshTable();
                            } catch (Exception e) {
                                showNotification("Xóa không thành công");
                            }
                            
                            
                        });
                        HBox btnManage = new HBox(btnDelete);
                        btnManage.setStyle("-fx-alignment:center");                   
                        setGraphic(btnManage);
                    }
                }
            };
            return cell;         
        };

        toolCol.setCellFactory(cellFactory);
    }
    private void deleteApartment(String ID){
        apartment = new Apartment();
        Room room = new Room();
        Student student = new Student();
        for (String item : apartment.getRoomNameBaseApartment(ID)) {
            student.updateStudentRemoveFromRoom(item);
        }
        room.deleteAllRoomInApartment(ID);
        apartment.delete(ID);
        
    
    }
    private void tableSearch(TableView table){
        String keyWord = searchText.getText();
        table.getItems().clear();
        apartment = new Apartment();
        if(allBox.isSelected()) {  table.getItems().addAll(apartment.getSearchApartment(1, keyWord));
       } else if(doneBox.isSelected()){ table.getItems().addAll(apartment.getSearchApartment(2, keyWord));
       } else { table.getItems().addAll(apartment.getSearchApartment(3, keyWord));
       }
    }
    private void addDataToCombobox(){
        Employee employee = new Employee();
        genderCombobox.getItems().addAll("Nam","Nữ");
        employeeCombobox.getItems().addAll(employee.getHeadOfApartment());
    }
    private void DrawUI(){
    allBox.setSelected(true);
    initTableView(dataTableView); 
    addDataToCombobox();
    genderCombobox.getSelectionModel().selectFirst();
    employeeCombobox.getSelectionModel().selectFirst();
    valueChange();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DrawUI();
    }    
    
}
