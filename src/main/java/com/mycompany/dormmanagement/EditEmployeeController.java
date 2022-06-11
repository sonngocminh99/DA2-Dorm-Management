/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import Utils.DataValidation;
import com.mycompany.dormmanagement.Model.Account;
import com.mycompany.dormmanagement.Model.Employee;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class EditEmployeeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML 
    private Button employeeUpdateBtn,accountUpdateBtn;
    @FXML
    private Label IDText,nameError,birthdayError,addressError,phoneError,passError;
    @FXML
    private TextField nameText;
    @FXML
    private DatePicker birthdayPicker;
    @FXML
    private TextField addressText;
    @FXML
    private ComboBox positionCombo;
    @FXML
    private TextField phoneText;
    @FXML
    private Label usernameText;
    @FXML
    private TextField passwordText;
    @FXML
    private ComboBox permissionCombo;
    private Account account = new Account();
    private Employee employee = new Employee();
    private AccountPaneController accountPaneController;
    @FXML
    void updateEmployee(){
        String idEmployee,name,birthday,address,position,phoneNum;
        idEmployee = IDText.getText();
        name = nameText.getText().trim();
        address = addressText.getText().trim();
        birthday = birthdayPicker.getValue().toString();
        position = positionCombo.getSelectionModel().getSelectedItem().toString();
        phoneNum = phoneText.getText().trim();       
        try {
            employee.update(idEmployee, name, birthday, address, position, phoneNum);
            accountPaneController.refreshTable();  
            showNotification("Cập nhật thành công");
        } catch (Exception e) {
            showNotification("Cập nhật không thành công");
        }
    }
    @FXML
    void TextChange(){
    if(DataValidation.textFieldIsNull(nameText, nameError, "Vui lòng không để trống")) {
        nameError.setVisible(true);
    } else if(!DataValidation.textAlphabet(nameText, nameError, "Tên chứa ký tự không hợp lệ")){
        nameError.setVisible(true);
    }
    else nameError.setVisible(false);
    
    if(birthdayPicker.getValue()==null){
        birthdayError.setVisible(true);
    }
    else birthdayError.setVisible(false);
    
    if(DataValidation.textFieldIsNull(addressText, addressError, "Vui lòng không để trống")) {
        addressError.setVisible(true);    
    }
    else addressError.setVisible(false);
    
    if(DataValidation.textFieldIsNull(phoneText, phoneError, "vui lòng không để trống")) {
        phoneError.setVisible(true);
    } else if(!DataValidation.textNumeric(phoneText, phoneError, "Số điện thoại phải là số")){
        phoneError.setVisible(true);
    } else if(!DataValidation.dataLengthMinMax(phoneText, phoneError, "Số điện thoại phải có ít nhất 10 chữ số","10","50")){
        phoneError.setVisible(true);
    }
    else phoneError.setVisible(false);
    
    if(DataValidation.textFieldIsNull(passwordText, passError, "Vui lòng không để trống")) {
        passError.setVisible(true);   
    }
    else passError.setVisible(false);
    
    if(!nameError.isVisible()&&!birthdayError.isVisible()&&!addressError.isVisible()&&!phoneError.isVisible()){
    employeeUpdateBtn.setDisable(false);
    }
    else{
    employeeUpdateBtn.setDisable(true);
    }
    if(!passError.isVisible()){
    accountUpdateBtn.setDisable(false);
    } else accountUpdateBtn.setDisable(true);
    
    }
    @FXML
    void updateAccount(){
        String username,pass,permission;
        username = usernameText.getText();
        account.GetDataByUsername(username);
        if(passwordText.disableProperty().get()!=true)
        {
            account.setPassword(passwordText.getText().trim());     
        }
        account.setPermission(permissionCombo.getSelectionModel().getSelectedItem().toString());
        try {
            account.update(account);
            showNotification("Cập nhật thành công");
            accountPaneController.refreshTable();
        } catch (Exception e) {
            showNotification("Cập nhật không thành công");
            System.out.println(e);
        }
    }
    @FXML
    void back(ActionEvent event)
    {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    public void receiveData(String idEmployee,String username, AccountPaneController parentController){
        showData(idEmployee, username);
        accountPaneController = parentController;
    }
    private void showNotification(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
	alert.setContentText(msg);
	alert.showAndWait();
    }
    private void showData(String idEmployee,String username){
        
        
        account.GetDataByUsername(username);
        employee.getInfoBaseID(idEmployee);
        IDText.setText(employee.getEmployeeID());
        nameText.setText(employee.getFullname());
        String dateArr[] = employee.getBirthday().split("-");
        int year = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]);
        int date = Integer.parseInt(dateArr[2]);
        birthdayPicker.setValue(LocalDate.of(year, month, date));
        positionCombo.getItems().addAll("Người quản trị","Trưởng nhà","Nhân viên");
        positionCombo.getSelectionModel().select(employee.getPosition());
        addressText.setText(employee.getAddress());
        phoneText.setText(employee.getPhoneNumber());
        usernameText.setText(account.getUsername());
        String crUserPermission = LoginFormController.currentUser.getPermission();
        Employee crEmployee = new Employee();
        crEmployee.getInfoBaseAccountID(LoginFormController.currentUser.getIDAccount());
        String crPosition = crEmployee.getPosition();
       
        if((LoginFormController.currentUser.getPermission().equals("admin") && crPosition.equals("Người quản trị")) || LoginFormController.currentUser.getUsername().equals(account.getUsername())){
            passwordText.setText(account.getPassword());
         }else {
            String pass = account.getPassword();
            String convertPass = "";
            for(int i=0;i<pass.length();i++){
            convertPass = convertPass+"*";
            }
            passwordText.setText(convertPass);
            passwordText.setDisable(true);
        }
        permissionCombo.getItems().addAll("admin","user");
        permissionCombo.getSelectionModel().select(account.getPermission());
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
