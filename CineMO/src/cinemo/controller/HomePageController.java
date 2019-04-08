/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemo.controller;

import cinemo.model.Database;
import cinemo.model.LogiraniKorisnikModel;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Butigan
 */
public class HomePageController implements Initializable {

    @FXML
    private Button loginBtn;
    
    @FXML
    private TextField name;
    
    @FXML
    private TextField password;
    
    @FXML
    private Label greska;
    
     @FXML
    private Button pregledProjekcijaBtn;
     
     public void handleCloseButtonAction(ActionEvent event)
    {
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        stage.close();
    }
    
    public void handleLogin (ActionEvent evt) {
        String nameString = this.name.getText();
        String passwordString = this.password.getText();
        if (nameString.equals("") && passwordString.equals("")) {
            greska.setText("Greška: Molimo unesite korisničko ime i lozinku.");
        } else if (nameString.equals("")) {
            greska.setText("Greška: Molimo unesite korisničko ime.");
        } else if (passwordString.equals("")) {
            greska.setText("Greška: Molimo unesite lozinku.");
        } else {
           if (LogiraniKorisnikModel.logiraj(nameString, passwordString)) {
                 try {
                 FXMLLoader  fxmlLoader = new FXMLLoader(getClass().getResource("/cinemo/view/AdminView.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Administrator"); 
                 stage.setScene(new Scene(root1));
                 stage.show();
                ActionEvent event1 = evt.copyFor(stage, stage);
                handleCloseButtonAction(event1);
           
        } catch (Exception e) {
            e.printStackTrace();
                    }
            } else {
                greska.setText("Korisnicki podatci nisu ispravni!");
            }
 
    }
        }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void handlepregledProjekcijaBtn(ActionEvent evt) {
    try {
                 FXMLLoader  fxmlLoader = new FXMLLoader(getClass().getResource("/cinemo/view/GuestView.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Gost"); 
                 stage.setScene(new Scene(root1));
                 stage.show();
                ActionEvent event1 = evt.copyFor(stage, stage);
                handleCloseButtonAction(event1);
           
        } catch (Exception e) {
            e.printStackTrace();
                    }
}
 }
