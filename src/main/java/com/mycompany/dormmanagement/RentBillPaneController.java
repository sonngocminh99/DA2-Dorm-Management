/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Apartment;
import com.mycompany.dormmanagement.Model.RentBill;
import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import se.alipsa.ymp.YearMonthPicker;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class RentBillPaneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Apartment apartment;
    private RentBill rentbill;
    @FXML
    private TableView dataTableView;
    @FXML
    private ComboBox apartmentComboBox;
    @FXML
    private TableColumn indexCol, roomCol, idstudentCol,dateCol,totalCol,statusCol,toolCol;
    @FXML
    private CheckBox allBox,doneBox,unDoneBox;
    @FXML
    private HBox box;
    @FXML
    private TextField searchText;
//    @FXML
//    private Button showAllBtn;
    
    private YearMonthPicker picker;
    //event of apartmentCombobox
    @FXML
    void selectHandle(ActionEvent event){ 
        refreshTable();
    }
    @FXML
    void checkBoxHandles(ActionEvent event){
        String keyWord = searchText.getText();
        rentbill = new RentBill();
        String monthYear, month, year;
        monthYear = picker.getValue().toString();
        month = monthYear.substring(5);
        year = monthYear.substring(0,4);
        String crApartment = apartmentComboBox.getValue().toString().substring(4);
      if(event.getSource()== allBox){
            doneBox.setSelected(false);
            unDoneBox.setSelected(false);
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,1);
            else dataTableView.getItems().addAll(rentbill.getSearchRentBill(crApartment, 1, keyWord, month, year));
            
      }
      if(event.getSource()== doneBox){
            allBox.setSelected(false);
            unDoneBox.setSelected(false);
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,2);
            else dataTableView.getItems().addAll(rentbill.getSearchRentBill(crApartment, 2, keyWord, month, year));
      }
      if(event.getSource()==unDoneBox){
            doneBox.setSelected(false);
            allBox.setSelected(false);
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,3);
            else dataTableView.getItems().addAll(rentbill.getSearchRentBill(crApartment, 3, keyWord, month, year));
      } 
    }
    
    @FXML
    void textChange(KeyEvent event){
       tableSearch();
      // showAllBtn.setVisible(true);
       
    }
    
    @FXML
    void showAll(ActionEvent event){
          dataTableView.getItems().clear();
          if(allBox.isSelected()) addDataToTable(dataTableView, 1);
          else if(doneBox.isSelected()) addDataToTable(dataTableView, 2);
          else addDataToTable(dataTableView, 3);
          searchText.clear();
          //showAllBtn.setVisible(false);
    }
    
    @FXML
    void addRentBill(ActionEvent event){
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/addRentBill.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Thêm bill tiền thuê phòng ");
        AddRentBillController addRentBillController = loader.getController();
        addRentBillController.receiveData(this);
        stage.setScene(new Scene(root));
        stage.show();   
        } catch (IOException e) {
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
    
    private void tableSearch(){
       String keyWord = searchText.getText();
       dataTableView.getItems().clear();
       rentbill = new RentBill();
       String monthYear, month, year;
       monthYear = picker.getValue().toString();
       month = monthYear.substring(5);
       year = monthYear.substring(0,4);
       String crApartment = apartmentComboBox.getValue().toString().substring(4);
       if(allBox.isSelected()) { dataTableView.getItems().addAll(rentbill.getSearchRentBill(crApartment, 1, keyWord, month, year));
       } else if(doneBox.isSelected()){ dataTableView.getItems().addAll(rentbill.getSearchRentBill(crApartment, 2, keyWord, month, year));
       } else { dataTableView.getItems().addAll(rentbill.getSearchRentBill(crApartment, 3, keyWord, month, year));
       }
    }
    
    private void initTableView(TableView table){
        indexCol.setCellValueFactory(new MapValueFactory<>("id"));
        roomCol.setCellValueFactory(new MapValueFactory<>("room"));
        idstudentCol.setCellValueFactory(new MapValueFactory<>("student"));
        dateCol.setCellValueFactory(new MapValueFactory<>("createDay"));
        totalCol.setCellValueFactory(new MapValueFactory<>("total"));
        statusCol.setCellValueFactory(new MapValueFactory<>("status"));
        
        indexCol.prefWidthProperty().bind(table.widthProperty().multiply(0.058));
        roomCol.prefWidthProperty().bind(table.widthProperty().multiply(0.11));
        idstudentCol.prefWidthProperty().bind(table.widthProperty().multiply(0.19));
        dateCol.prefWidthProperty().bind(table.widthProperty().multiply(0.19));
        totalCol.prefWidthProperty().bind(table.widthProperty().multiply(0.19));
        statusCol.prefWidthProperty().bind(table.widthProperty().multiply(0.13));
        toolCol.prefWidthProperty().bind(table.widthProperty().multiply(0.13));
        addDataToTable(table,1);
    }
    private void addDataToTable(TableView table,int option){      
        rentbill = new RentBill();
        String monthYear, month, year;
        monthYear = picker.getValue().toString();
        month = monthYear.substring(5);
        year = monthYear.substring(0,4);
        String crApartment = apartmentComboBox.getValue().toString().substring(4);    
        switch (option) {
                case 1:
                    table.getItems().addAll(rentbill.getRentBill(crApartment,option, month, year));
                    addButtonToTable();
                    break;
                case 2:
                    table.getItems().addAll(rentbill.getRentBill(crApartment,option, month, year));
                    addButtonToTable();
                    break;
                case 3:
                    table.getItems().addAll(rentbill.getRentBill(crApartment,option, month, year));
                    addButtonToTable();
                    break;
                default:
                    table.getItems().addAll(rentbill.getRentBill(crApartment,1, month, year));
                    addButtonToTable();
                    break;
            }
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
                        btnDetail.setOnAction((ActionEvent event) -> {
                            int index = getIndex();
                            String data = (String) indexCol.getCellObservableValue(index).getValue();
                            sendDetailData(data);
                        });
                        HBox btnManage = new HBox(btnDetail);
                        btnManage.setStyle("-fx-alignment:center");                   
                        setGraphic(btnManage);
                    }
                }
            };
            return cell;         
        };
        toolCol.setCellFactory(cellFactory);
    }
    
    private void sendDetailData(String data){
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/detailRentBill.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Xem thông tin tiền phòng");
        stage.setScene(new Scene(root));
        DetailRentBillController detailRentBillController = loader.getController(); 
        detailRentBillController.reciveData(data,this);
        stage.show(); 
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("success");
    }
    
    private void addDataToCombobox(ComboBox comboBox){
        apartment = new Apartment();
        ObservableList<String> items = FXCollections.<String>observableArrayList();
        for(var item : apartment.getAllApartment(1)){
            items.add("Tòa "+ item);
        }
        comboBox.getItems().addAll(items);
        
    }
    
    private void addYearMonthPicker(){
        picker = new YearMonthPicker(); 
        picker.getStylesheets().add("/styles/yearmonthpicker.css");
        picker.setPrefSize(100, 45);
        picker.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        picker.setId("yearMonthPicker");
        picker.setValue(YearMonth.now());
        picker.setOnAction((ActionEvent event) -> {
          if(searchText.getText().isEmpty()){
          dataTableView.getItems().clear();
          if(allBox.isSelected()) addDataToTable(dataTableView, 1);
          else if(doneBox.isSelected()) addDataToTable(dataTableView, 2);
          else addDataToTable(dataTableView, 3);
          }else{
              tableSearch();
          }
        });
        box.getChildren().add(2, picker);
        
    }
   
    private void DrawUI(){
        allBox.setSelected(true);
        addDataToCombobox(apartmentComboBox);
        apartmentComboBox.getSelectionModel().select(0);
        addYearMonthPicker();
        initTableView(dataTableView); 
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DrawUI();
        
    } 
    
}
