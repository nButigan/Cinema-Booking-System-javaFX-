/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemo.controller;

import java.io.FileNotFoundException;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class DodajFilmViewController implements Initializable {
    
    
    
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
    private Button PotvrdiBtn;
    
    @FXML
    private Label NotifCenterAddMovie;
    
    /*public void handleDodajFilm(ActionEvent evt) throws IOException{
    
         FXMLLoader  fxmlLoader = new FXMLLoader(getClass().getResource("/cinemo/view/DodajFilmView.fxml"));
                Parent root2 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Dodavanje filma..."); 
                 stage.setScene(new Scene(root2));
                 stage.show();
                
       
    }*/
    public void handlePotvrdiBtn(ActionEvent evt) throws SQLException{
    String dbURL = "jdbc:mysql://localhost:3306/cinemo_db";
        String username = "root";
        String password = "";
 
    try (Connection konekcija = DriverManager.getConnection(dbURL, username, password)){
        String sql = "INSERT INTO movies (name, genre, about, length, hall, numofseats, dateandtime, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
 
            PreparedStatement statement = konekcija.prepareStatement(sql);
            statement.setString(1, this.unosImena.getText());
            statement.setString(2, this.unosZanra.getText());
            statement.setString(3, this.unosOFilmu.getText());
            statement.setInt(4, Integer.valueOf(this.unosTrajanja.getText()));
            statement.setString(5,this.unosDvorane.getText() );
            statement.setInt(6, Integer.valueOf(this.unosBrSjedala.getText()));
            statement.setString(7,this.unosDatumaIvremena.getText());
            statement.setString(8, this.unosCijene.getText());
 
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            this.NotifCenterAddMovie.setText("Uspješno dodan film!");  
        }}
    }
    
            
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
