/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemo.controller;

import cinemo.controller.GuestViewController;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class KupiFilmViewController implements Initializable {
    @FXML
    private TextField unosId;
    
    @FXML
    private TextField amount;
    
    @FXML
    private Button RezervirajBtn;
    
    @FXML
    private Label podacioFilmu;
    
    @FXML
    private Label NotifCenterBookMovie;
    
    
    public void transferMessage(String message) {
        //Display the message
        podacioFilmu.setText(message);
    }
    public void handleRezervirajBtn(ActionEvent evt) throws SQLException{
        
        String dbURL = "jdbc:mysql://localhost:3306/cinemo_db";
        String username = "root";
        String password = "";
        
 
    try (Connection konekcija = DriverManager.getConnection(dbURL, username, password)) {
     
    
            String sql = "INSERT INTO reservations (imeIprezime, kolicina, oFilmu) VALUES (?, ?, ?)";
            
            
            PreparedStatement statement = konekcija.prepareStatement(sql);
            statement.setString(1, this.unosId.getText());
            statement.setString(2, this.amount.getText());
            statement.setString(3, this.podacioFilmu.getText());
            
            int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            this.NotifCenterBookMovie.setText("Uspješno rezervirano !");  
        }
 
    }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
