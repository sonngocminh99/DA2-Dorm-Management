/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Account;
import com.mycompany.dormmanagement.Model.Employee;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Pane view;
    @FXML
    private Pane homePane, aboutPane, homeMainView;
    @FXML
    private Button homeBtn, aboutBtn,roomBtn, studentBtn, ewBillBtn, rentBillBtn, reportBtn,apartmentBtn,accountBtn;
    @FXML
    private Label crUsername, crPermission;
    @FXML
    private ImageView logo;
    @FXML
    private Line line1,line2;
    @FXML
    void logOut(){
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Đăng xuất");
            alert.setContentText("Bạn có chắc muốn đăng xuất?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(yesButton, cancelButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == yesButton) {                
                LoginFormController.currentUser = new Account();
                ObservableList<Stage> stages = FXCollections.observableArrayList();
                Window.getWindows().forEach(w -> {
                if (w instanceof Stage) {
                    stages.add((Stage) w);
                }
                });
                    for (Stage stage : stages) {
                        if(!(stage.getTitle().equals("Dorm Management"))) stage.close();
                    }
                    try {
                        App.setRoot("/View/loginForm.fxml");
                    } catch (IOException ex) {
                        Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                }
            });
    }
    @FXML
    void handleClicks(ActionEvent event){
    if(event.getSource()== homeBtn){
    homePane.toFront();
    homeBtn.setStyle("-fx-opacity: 100%");
    aboutBtn.setStyle("-fx-opacity: 47%");
    
    }
    if(event.getSource()==aboutBtn){
    aboutPane.toFront();
    homeBtn.setStyle("-fx-opacity: 47%");
    aboutBtn.setStyle("-fx-opacity: 100%");
    
    }
    
    if(event.getSource()==roomBtn){
    homeMainView.getChildren().setAll(getPane("/View/roomPane.fxml"));
    roomBtn.setStyle("-fx-text-fill: #2CA8E9");
    studentBtn.setStyle("-fx-text-fill: black");
    ewBillBtn.setStyle("-fx-text-fill: black");
    rentBillBtn.setStyle("-fx-text-fill: black");
    reportBtn.setStyle("-fx-text-fill: black");
    apartmentBtn.setStyle("-fx-text-fill: black");
    accountBtn.setStyle("-fx-text-fill: black");
    }
     if(event.getSource()==studentBtn){
    homeMainView.getChildren().setAll(getPane("/View/studentPane.fxml"));    
    roomBtn.setStyle("-fx-text-fill: black");
    studentBtn.setStyle("-fx-text-fill: #2CA8E9");
    ewBillBtn.setStyle("-fx-text-fill: black");
    rentBillBtn.setStyle("-fx-text-fill: black");
    reportBtn.setStyle("-fx-text-fill: black");
    apartmentBtn.setStyle("-fx-text-fill: black");
    accountBtn.setStyle("-fx-text-fill: black");
    }
     if(event.getSource()==ewBillBtn){
    homeMainView.getChildren().setAll(getPane("/View/ewBillPane.fxml"));
    roomBtn.setStyle("-fx-text-fill: black");
    studentBtn.setStyle("-fx-text-fill: black");
    ewBillBtn.setStyle("-fx-text-fill: #2CA8E9");
    rentBillBtn.setStyle("-fx-text-fill: black");
    reportBtn.setStyle("-fx-text-fill: black");
    apartmentBtn.setStyle("-fx-text-fill: black");
    accountBtn.setStyle("-fx-text-fill: black");
    }
     if(event.getSource()==rentBillBtn){
    homeMainView.getChildren().setAll(getPane("/View/rentBillPane.fxml"));     
    roomBtn.setStyle("-fx-text-fill: black");
    studentBtn.setStyle("-fx-text-fill: black");
    ewBillBtn.setStyle("-fx-text-fill: black");
    rentBillBtn.setStyle("-fx-text-fill: #2CA8E9");
    reportBtn.setStyle("-fx-text-fill: black");
    apartmentBtn.setStyle("-fx-text-fill: black");
    accountBtn.setStyle("-fx-text-fill: black");
    }
     if(event.getSource()==reportBtn){
    homeMainView.getChildren().setAll(getPane("/View/reportPane.fxml"));     
    roomBtn.setStyle("-fx-text-fill: black");
    studentBtn.setStyle("-fx-text-fill: black");
    ewBillBtn.setStyle("-fx-text-fill: black");
    rentBillBtn.setStyle("-fx-text-fill: black");
    reportBtn.setStyle("-fx-text-fill: #2CA8E9");
    apartmentBtn.setStyle("-fx-text-fill: black");
    accountBtn.setStyle("-fx-text-fill: black");
    }
     if(event.getSource()==apartmentBtn){
    homeMainView.getChildren().setAll(getPane("/View/apartmentPane.fxml"));     
    roomBtn.setStyle("-fx-text-fill: black");
    studentBtn.setStyle("-fx-text-fill: black");
    ewBillBtn.setStyle("-fx-text-fill: black");
    rentBillBtn.setStyle("-fx-text-fill: black");
    reportBtn.setStyle("-fx-text-fill: black");
    apartmentBtn.setStyle("-fx-text-fill: #2CA8E9");
    accountBtn.setStyle("-fx-text-fill: black");
    }
     if(event.getSource()==accountBtn){
    homeMainView.getChildren().setAll(getPane("/View/accountPane.fxml"));     
    roomBtn.setStyle("-fx-text-fill: black");
    studentBtn.setStyle("-fx-text-fill: black");
    ewBillBtn.setStyle("-fx-text-fill: black");
    rentBillBtn.setStyle("-fx-text-fill: black");
    reportBtn.setStyle("-fx-text-fill: black");
    apartmentBtn.setStyle("-fx-text-fill: black");
    accountBtn.setStyle("-fx-text-fill: #2CA8E9");
    }
     
    }
    private Pane getPane(String path){
        try {
            view = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
      return view;
    }
    private void DrawUI(){
    logo.setImage(new Image(getClass().getResourceAsStream("/Image/logo.png")));
    crUsername.setText(LoginFormController.currentUser.getUsername());
    crPermission.setText(LoginFormController.currentUser.getPermission());
    if(crPermission.getText().equals("admin")){
        apartmentBtn.setVisible(true);
        line1.setVisible(true);
        line2.setVisible(true);
        accountBtn.setVisible(true);
    }
    homePane.toFront();
    homeMainView.getChildren().setAll(getPane("/View/roomPane.fxml"));
    homeBtn.setStyle("-fx-opacity: 100%");
    roomBtn.setStyle("-fx-text-fill: #2CA8E9");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       DrawUI();
    }    
    
}
