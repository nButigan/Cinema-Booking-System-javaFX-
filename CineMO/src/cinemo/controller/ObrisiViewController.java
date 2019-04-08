/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemo.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class ObrisiViewController implements Initializable {
        
        @FXML
        private Label NotifCenterDeleteMovie;

        @FXML
        private TextField unosId;

        @FXML
        private Button ObrisiBtn;
    
        public void handleObrisiBtn(ActionEvent evt) throws SQLException{
        
        String dbURL = "jdbc:mysql://localhost:3306/cinemo_db";
        String username = "root";
        String password = "";
 
    try (Connection konekcija = DriverManager.getConnection(dbURL, username, password)){
    String sql = "DELETE FROM movies WHERE ID=?";
 
    PreparedStatement statement = konekcija.prepareStatement(sql);
    statement.setInt(1,Integer.valueOf(this.unosId.getText()));
 
    int rowsDeleted = statement.executeUpdate();
    if (rowsDeleted > 0) {
        this.NotifCenterDeleteMovie.setText("Uspješno obrisana projekcija!");  
        }
      }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
