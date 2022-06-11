/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
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
import com.mycompany.dormmanagement.Model.ElectricAndWaterBill;
import java.io.IOException;
import java.io.OutputStream;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.BaseFont;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import javafx.stage.Window;
/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class EwBillPaneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Apartment apartment;
    private ElectricAndWaterBill ewBill;
    @FXML
    private TableView dataTableView;
    @FXML
    private ComboBox apartmentComboBox;
    @FXML
    private TableColumn indexCol, roomCol, electricCol,waterCol,totalCol,statusCol,toolCol;
    @FXML
    private CheckBox allBox,doneBox,unDoneBox;
    @FXML
    private HBox box;
    @FXML
    private TextField searchText;
    @FXML
    private Button showAllBtn;
    
    private YearMonthPicker picker;
    
    
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 22,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 20,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.NORMAL);
    //event of apartmentCombobox
    @FXML
    void selectHandle(ActionEvent event){ 
        refreshTable();
    }
    @FXML
    void checkBoxHandles(ActionEvent event){
        String keyWord = searchText.getText();
        ewBill = new ElectricAndWaterBill();
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
            else dataTableView.getItems().addAll(ewBill.getSearchEWBill(crApartment, 1, keyWord, month, year));
            
      }
      if(event.getSource()== doneBox){
            allBox.setSelected(false);
            unDoneBox.setSelected(false);
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,2);
            else dataTableView.getItems().addAll(ewBill.getSearchEWBill(crApartment, 2, keyWord, month, year));
      }
      if(event.getSource()==unDoneBox){
            doneBox.setSelected(false);
            allBox.setSelected(false);
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,3);
            else dataTableView.getItems().addAll(ewBill.getSearchEWBill(crApartment, 3, keyWord, month, year));
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
    void addEWBill(ActionEvent event){
 
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/addEWbill.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add electricity and water bill");
        AddEWBillController addEWBillController = loader.getController();
        addEWBillController.receiveData(this);
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
       ewBill = new ElectricAndWaterBill();
       String monthYear, month, year;
       monthYear = picker.getValue().toString();
       month = monthYear.substring(5);
       year = monthYear.substring(0,4);
       String crApartment = apartmentComboBox.getValue().toString().substring(4);
       if(allBox.isSelected()) { dataTableView.getItems().addAll(ewBill.getSearchEWBill(crApartment, 1, keyWord, month, year));
       } else if(doneBox.isSelected()){ dataTableView.getItems().addAll(ewBill.getSearchEWBill(crApartment, 2, keyWord, month, year));
       } else { dataTableView.getItems().addAll(ewBill.getSearchEWBill(crApartment, 3, keyWord, month, year));
       }
    }
    private void initTableView(TableView table){
        
        indexCol.setCellValueFactory(new MapValueFactory<>("id"));
        roomCol.setCellValueFactory(new MapValueFactory<>("room"));
        electricCol.setCellValueFactory(new MapValueFactory<>("electric"));
        waterCol.setCellValueFactory(new MapValueFactory<>("water"));
        totalCol.setCellValueFactory(new MapValueFactory<>("total"));
        statusCol.setCellValueFactory(new MapValueFactory<>("status"));
        
        indexCol.prefWidthProperty().bind(table.widthProperty().multiply(0.058));  
        roomCol.prefWidthProperty().bind(table.widthProperty().multiply(0.11));
        electricCol.prefWidthProperty().bind(table.widthProperty().multiply(0.19)); 
        waterCol.prefWidthProperty().bind(table.widthProperty().multiply(0.19)); 
        totalCol.prefWidthProperty().bind(table.widthProperty().multiply(0.19)); 
        statusCol.prefWidthProperty().bind(table.widthProperty().multiply(0.13));
        toolCol.prefWidthProperty().bind(table.widthProperty().multiply(0.13));
        addDataToTable(table,1);
        
        
    }
    private void addDataToTable(TableView table,int option){      
        ewBill = new ElectricAndWaterBill();
        String monthYear, month, year;
        monthYear = picker.getValue().toString();
        month = monthYear.substring(5);
        year = monthYear.substring(0,4);
        String crApartment = apartmentComboBox.getValue().toString().substring(4);    
        switch (option) {
                case 1:
                    table.getItems().addAll(ewBill.getEWBill(crApartment,option, month, year));
                    addButtonToTable();
                    break;
                case 2:
                    table.getItems().addAll(ewBill.getEWBill(crApartment,option, month, year));
                    addButtonToTable();
                    break;
                case 3:
                    table.getItems().addAll(ewBill.getEWBill(crApartment,option, month, year));
                    addButtonToTable();
                    break;
                default:
                    table.getItems().addAll(ewBill.getEWBill(crApartment,1, month, year));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/detailEWBill.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Electricity and water bill detail");
        stage.setScene(new Scene(root));
        DetailEWBillController detailEWBillController = loader.getController(); 
        detailEWBillController.reciveData(data,this);
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
        picker.setPrefSize(93, 45);
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

 
