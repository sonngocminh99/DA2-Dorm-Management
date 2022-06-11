/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import com.mycompany.dormmanagement.Model.Account;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class LoginFormController implements Initializable {
    private Account account;
    public static Account currentUser;
    /**
     * Initializes the controller class.
     */
    @FXML
    private PasswordField passwordText;
    @FXML
    private ImageView logoImageView,usernameIcon,passwordIcon;
    @FXML
    private Button loginBtn;
    @FXML
    private TextField usernameText;
    @FXML
    private Label usernameErrorLabel, passwordErrorLabel;
    @FXML
    void mouseClick(MouseEvent event){     
                   String username = usernameText.getText().trim();
       String password = passwordText.getText().trim();
       account = new Account();
       account.GetDataByUsername(username);
       if(username.isEmpty() || password.isEmpty()){
           showtification("Vui lòng không để trống bất kì thông tin nào.");
       }
       else if(!username.equals(account.getUsername())) usernameErrorLabel.setVisible(true);
       else if (!password.equals(account.getPassword()) ) passwordErrorLabel.setVisible(true);
       else {
           try {
           currentUser = account;
           App.setRoot("/View/dashboard.fxml");
       } catch (IOException ex) {
           Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
       }
       }  
    }  
    @FXML
    void mouseEnter(MouseEvent event){
        loginBtn.setStyle("-fx-background-color: #2893CB");
    }
    @FXML
    void mouseExit(MouseEvent event){
        loginBtn.setStyle("-fx-background-color: #2CA8E9;");
    }
    @FXML
    void action(MouseEvent event){
        usernameErrorLabel.setVisible(false);
        passwordErrorLabel.setVisible(false);
    }
    
    private void showtification(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
	alert.setContentText(msg);
	alert.showAndWait();
    }
    private void DrawUi(){
        logoImageView.setImage(new Image(getClass().getResourceAsStream("/Image/logo.png")));
        usernameIcon.setImage(new Image(getClass().getResourceAsStream("/Image/usernameicon.png")));
        passwordIcon.setImage(new Image(getClass().getResourceAsStream("/Image/passwordicon.png")));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DrawUi();
    }    
    
}
