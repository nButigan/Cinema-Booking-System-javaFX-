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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;


public class IzmijeniViewController implements Initializable {
    
     @FXML
    private FlowPane prikaz;
    
    @FXML
    private TextField unosImena;
    
    @FXML
    private TextField unosZanra;
    
    @FXML
    private TextField unosOFilmu;
    
    @FXML
    private TextField unosTrajanja;
    
    @FXML
    private TextField unosDvorane;
    
    @FXML
    private TextField unosBrSjedala;
    
    @FXML
    private TextField unosDatumaIvremena;
    
    @FXML
    private TextField unosCijene;
    
    @FXML
    private Button IzmijeniBtn;
    
    @FXML
    private Label NotifCenterChangeMovie;
    
    @FXML
    private TextField unosId;


    public void handleIzmijeniBtn(ActionEvent evt){
        String dbURL = "jdbc:mysql://localhost:3306/cinemo_db";
        String username = "root";
        String password = "";
 
    try (Connection konekcija = DriverManager.getConnection(dbURL, username, password)){
    String sql = "UPDATE movies SET name=?, genre=?, about=?, length=?, hall=?, numofseats=?, dateandtime=?, price=? WHERE ID=?";
 
            PreparedStatement statement = konekcija.prepareStatement(sql);
            statement.setString(1, this.unosImena.getText());
            statement.setString(2, this.unosZanra.getText());
            statement.setString(3, this.unosOFilmu.getText());
            statement.setInt(4, Integer.valueOf(this.unosTrajanja.getText()));
            statement.setString(5,this.unosDvorane.getText() );
            statement.setInt(6, Integer.valueOf(this.unosBrSjedala.getText()));
            statement.setString(7,this.unosDatumaIvremena.getText());
            statement.setString(8, this.unosCijene.getText());
            statement.setInt(9, Integer.valueOf(this.unosId.getText()));
 
    int rowsUpdated = statement.executeUpdate();
    if (rowsUpdated > 0) {
        this.NotifCenterChangeMovie.setText("Uspješno dodan film!");
        }
    }   catch (SQLException ex) {
            Logger.getLogger(IzmijeniViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

