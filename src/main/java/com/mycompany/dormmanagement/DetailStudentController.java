/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Student;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class DetailStudentController implements Initializable {

    @FXML
    private Label idStudentLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label birthLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label idCardLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label universityLabel;
    @FXML
    private Label gradeLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label sYearLabel;
    @FXML
    private Label eYearLabel;
    @FXML
    private Label idRoomLabel;
    @FXML
    private ImageView image;
    
    private Image imageFile;

    /**
     * Initializes the controller class.
     */
    private Student student;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    //xử lý nút quay lại
    @FXML
    private void back(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    public void setDetailStudent (String data) throws IOException{
        loadData(data);
        
    }
    
    //lấy dữ liệu từ database và hiển thị lên màn hình
    public void loadData(String studentID) throws FileNotFoundException, IOException{
        
        student = new Student();
        student.getInfoByID(studentID);
        System.out.println("id: "+studentID + "name: " +student.getFullName());
        idStudentLabel.setText(student.getStudentID());
        nameLabel.setText(student.getFullName());
        idCardLabel.setText(student.getIDCard());
        birthLabel.setText(student.getBirthday().toString());
        genderLabel.setText(student.getGender());
        phoneLabel.setText(student.getPhoneNum());
        universityLabel.setText(student.getUniversity());
        gradeLabel.setText(student.getGrade());
        statusLabel.setText(student.getStatus());
        sYearLabel.setText(student.getSyear());
        eYearLabel.setText(student.getEyear());
        if (student.getIdRoom() != null) {
            idRoomLabel.setText(student.getIdRoom());
        } else {
            idRoomLabel.setText("Chưa có phòng ở");
        }
        try {
            InputStream is = student.getImage();
        OutputStream os = new FileOutputStream(new File("photo.jpg"));
        byte[] content = new byte[1024];
        int size = 0;
        while((size = is.read(content)) != -1) {
            os.write(content, 0, size);
        }
        os.close();
        is.close();
        
        imageFile = new Image("file:photo.jpg", 175, 225,true, true);
        image.setImage(imageFile);
        image.setFitWidth(175);
        image.setFitHeight(225);
        image.setPreserveRatio(true);
        } catch (Exception e) {
            
        }
        
    }
    
}
