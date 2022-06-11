/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dormmanagement;

import Utils.DataValidation;
import com.mycompany.dormmanagement.Model.Room;
import com.mycompany.dormmanagement.Model.Student;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class EditStudentController implements Initializable {
    private StudentPaneController studentPaneController;
    private Student student;
    @FXML
    private TextField nameText;
    @FXML
    private ComboBox<String> genderComboBox;
    @FXML
    private TextField idStudentText;
    @FXML
    private DatePicker birthdayDatePicker;
    @FXML
    private TextField idCardText;
    @FXML
    private TextField phoneText;
    @FXML
    private TextField universityText;
    @FXML
    private TextField gradeText;
    @FXML
    private TextField sYearText;
    @FXML
    private TextField eYearText;
    @FXML
    private Button updateBtn;
    @FXML
    private TextField statusText;
    @FXML
    private TextField idRoomText;
    @FXML
    private ImageView image;
    @FXML
    private Label nameError, IDCardError, phoneNumError, universityError, yearError,sYearError,eYearError;
    
    private Image imageFile;
    private File file ;
    private FileInputStream fis;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DrawUI();
    }    

    @FXML
    private void getDate(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
        closeStage(event);
    }
    @FXML
    void TextChange(){
    //check name text field null or empty
        if(DataValidation.textFieldIsNull(nameText, nameError, "Vui lòng không để trống")){
            nameError.setVisible(true);
        } //check valid character, name is alphabet?
        else if(!DataValidation.textAlphabet(nameText, nameError, "Tên chứa ký tự không hợp lệ")){
            nameError.setVisible(true);
        } else nameError.setVisible(false);      
        
        //check IDCard text field null or empty
        if(DataValidation.textFieldIsNull(idCardText, IDCardError, "Vui lòng không để trống")){
            IDCardError.setVisible(true);
        } //ID card must be a numberic
        else if(!DataValidation.textNumeric(idCardText, IDCardError , "CMND/CCCD phải là số")){
            IDCardError.setVisible(true);
        } else IDCardError.setVisible(false);
        
        //check phone number null or empty
        if(DataValidation.textFieldIsNull(phoneText, phoneNumError, "Vui lòng không để trống")){
            phoneNumError.setVisible(true);
        } //phone number must be a numberic
        else if(!DataValidation.textNumeric(phoneText, phoneNumError , "Số điện thoại phải là số")){
            phoneNumError.setVisible(true);
        }//phone number larger than 10 character 
        else if(!DataValidation.dataLengthMinMax(phoneText, phoneNumError, "Số điện thoại phải có ít nhất 10 số", "10","50")){
            phoneNumError.setVisible(true);
        } else phoneNumError.setVisible(false);
        
        //check university null or empty
        if(DataValidation.textFieldIsNull(universityText, universityError, "Vui lòng không để trống")){
            universityError.setVisible(true);
        } else universityError.setVisible(false);
        
        //check grade null or empty
        if(DataValidation.textFieldIsNull(gradeText, yearError, "Vui lòng không để trống")){
            yearError.setVisible(true);
        } //grade must be a numberic
        else if(!DataValidation.textNumeric(gradeText, yearError, "Vui lòng nhập năm đang học là số")){
            yearError.setVisible(true);
        } //max year study in VietNam university is 9
        else if(Integer.parseInt(gradeText.getText().trim())> 9) {
            yearError.setText("Năm học tối đa là 9 năm");
            yearError.setVisible(true);
        } else yearError.setVisible(false);
        
        //check start year null or empty
        if(DataValidation.textFieldIsNull(sYearText, sYearError, "Vui lòng không để trống")){
            sYearError.setVisible(true);
        } 
        // check if text field value is number, if not show the error
        else if(!DataValidation.textNumeric(sYearText, sYearError, "Năm bắt đầu học phải phải là số")){
            sYearError.setVisible(true);
        } 
        //check if text field value >0, if not show the error because renting price must larger than 0
        else if(Integer.parseInt(sYearText.getText().trim())<=0){
            sYearError.setText("Năm bắt đầu học phải > 0");
            sYearError.setVisible(true);
        } else sYearError.setVisible(false);
        
        //check end year null or empty
        if(DataValidation.textFieldIsNull(eYearText, eYearError, "Vui lòng không để trống")){
            eYearError.setVisible(true);
        } 
        // check if text field value is number, if not show the error
        else if(!DataValidation.textNumeric(eYearText, eYearError, "Năm bắt đầu học phải phải là số")){
            eYearError.setVisible(true);
        }
        //check if text field value >0, if not show the error because renting price must larger than 0
        else if(Integer.parseInt(eYearText.getText().trim())<=0){
            eYearError.setText("Năm bắt đầu học phải > 0");
            eYearError.setVisible(true);
        } else eYearError.setVisible(false);
        
        //check when start year and end year not null end year must larger than start year 
        if(!sYearText.getText().trim().isEmpty() && !eYearText.getText().trim().isEmpty()){
            int sYear = -1;
            int eYear = -1;
            try {
                sYear = Integer.parseInt(sYearText.getText().trim());
                eYear = Integer.parseInt(eYearText.getText().trim());
                if(sYear >= eYear) {
                eYearError.setText("Năm kết thúc phải lớn hơn năm bắt đầu");
                eYearError.setVisible(true);
                } else eYearError.setVisible(false);
            } catch (NumberFormatException e) {
            }    
        }
        
        if(!nameError.isVisible() && !IDCardError.isVisible() && !phoneNumError.isVisible() && !universityError.isVisible() && !yearError.isVisible() && !sYearError.isVisible() && !eYearError.isVisible()){
            updateBtn.setDisable(false);
        } else {
            updateBtn.setDisable(true);
        }
    
    }
    
    //xử lý khi nhấn nut cập nhập
    @FXML
    private void updateData(ActionEvent event) {
        // lấy dữ liệu từ người dùng nhập vào
        String name = nameText.getText();
        String idStudent = idStudentText.getText();
        Date birthday = java.sql.Date.valueOf(birthdayDatePicker.getValue());
        String idCard = idCardText.getText();
        String phone = phoneText.getText();
        String university = universityText.getText();
        String grade = gradeText.getText();
        String eYear = eYearText.getText();
        String sYear = sYearText.getText();
        String idRoom = idRoomText.getText();
        String gender = genderComboBox.getValue().toString();
        String status = statusText.getText();
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        student = new Student();
        
            try {
                student.updateStudent(idStudent,name,birthday,gender,idCard,phone,university,grade,status,sYear,eYear,idRoom,(InputStream)fis,(int)file.length());
            } catch (Exception e) {
                showNotification("Có lỗi xảy ra. Sửa không thành công.");
                closeStage(event);
            }
            showNotification("Sửa thành công.");
            studentPaneController.refreshTable();
            closeStage(event);
        
    }
    //xử lý nút thêm ảnh
    @FXML
    public void handleAddImage(ActionEvent event) {
        // mở state mới để người dùng chọn ảnh và lưu ảnh đó vào file
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        FileChooser chooser = new FileChooser();
        file = chooser.showOpenDialog(stage);
        System.out.println("file ne: " + file);
        //kiểm tra nếu file khác null thì hiển thị trên ImageView
        if (file != null) {
            Image imageFile = new Image(file.toURI().toString(),175,225,true,true);
            System.out.println("file ne: " + imageFile);
            image.setImage(imageFile);
            image.setFitWidth(175);
            image.setFitHeight(225);
            image.setPreserveRatio(true);
            updateBtn.setDisable(false);
        }
    }
    
    public void setDetailStudent (String  data) throws IOException{
        loadData(data);
    }
    // hiển thị thông báo
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
    
    public void receiveData (StudentPaneController parentController)
    {
        studentPaneController = parentController;
    }
    // thêm data vào gender combobox
    private void addDataToGenderComboBox(){
        ObservableList<String> items = FXCollections.<String>observableArrayList();       
        items.add("Nam");
        items.add("Nữ");
        genderComboBox.setItems(items);
    }
    // thêm data vào status combobox
    private void addDataToStatusComboBox(){
        ObservableList<String> items = FXCollections.<String>observableArrayList();       
        items.add("ĐX");
        items.add("CX");
        genderComboBox.setItems(items);
    }
    //hàm lấy dữ liệu và hiển thị lên màn hình
    public void loadData(String studentID) throws FileNotFoundException, IOException{
        student = new Student();
        student.getInfoByID(studentID);
        nameText.setText(student.getFullName());
        genderComboBox.getSelectionModel().select(student.getGender());
        statusText.setText(student.getStatus());
        idStudentText.setText(student.getStudentID());
        birthdayDatePicker.setValue(student.getBirthday().toLocalDate());
        idCardText.setText(student.getIDCard());
        phoneText.setText(student.getPhoneNum());
        universityText.setText(student.getUniversity());
        gradeText.setText(student.getGrade());
        sYearText.setText(student.getSyear());
        eYearText.setText(student.getEyear());
        idRoomText.setText(student.getIdRoom());
        if (student.getIdRoom() == null) {
            idRoomText.setDisable(true);
        }
        // lấy dữ liệu ảnh tronng database
        try {
            InputStream is = student.getImage();
            //lưu dữ liệu vào một file photo.jpg
            OutputStream os = new FileOutputStream(new File("photo.jpg"));
            byte[] content = new byte[1024];
            int size = 0;
            while((size = is.read(content)) != -1) {
                os.write(content, 0, size);
            }
            os.close();
            is.close();
            //hiển thị file photo.jpg lên imageView
            imageFile = new Image("file:photo.jpg", 175, 225,true, true);
            file = new File("photo.jpg");
            image.setImage(imageFile);
            image.setFitWidth(175);
            image.setFitHeight(225);
            image.setPreserveRatio(true);
            } catch (Exception e) {
        }
        
    }
    
    private void DrawUI(){
        addDataToGenderComboBox();
        addDataToStatusComboBox();
    }
    
}
