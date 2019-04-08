/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemo.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.sql.*;
import cinemo.model.ConnectionToDB;
import java.util.logging.Level;
import java.util.logging.Logger;
import cinemo.model.LogiraniKorisnikModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javafx.collections.ObservableSet;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import static javafx.geometry.Pos.TOP_LEFT;
import javafx.print.Printer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javax.swing.JTextArea;

/**
 * FXML Controller class
 *
 * @author Butigan
 */
public class AdminViewController implements Initializable {
    
    @FXML
    private Button DodajFilmBtn;
    
    @FXML
    private Button IzmijeniBtn;
    
    @FXML
    private Button ObrisiBtn;
    
    @FXML
    private Button PregledBtn;
    
    @FXML
    private Button OdjavaBtn;
    
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
    private Button Potvrdi;
    
    @FXML
    private Button ocistiPanelBtn;
    
     public void handleCloseButtonAction(ActionEvent event)
    {
        Stage stage = (Stage) DodajFilmBtn.getScene().getWindow();
        stage.close();
    }
    public void handleDodajFilm(ActionEvent evt) throws IOException{
    
         FXMLLoader  fxmlLoader = new FXMLLoader(getClass().getResource("/cinemo/view/DodajFilmView.fxml"));
                Parent root2 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Dodavanje projekcije..."); 
                 stage.setScene(new Scene(root2));
                 stage.show();
                
    }
    
            
    public void handleIzmijeni(ActionEvent evt) throws IOException{
    FXMLLoader  fxmlLoader = new FXMLLoader(getClass().getResource("/cinemo/view/IzmijeniView.fxml"));
                Parent root4 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Izmjena podataka o projekciji..."); 
                 stage.setScene(new Scene(root4));
                 stage.show();
    }
    
    
    public void handleObrisi(ActionEvent evt) throws IOException{
    FXMLLoader  fxmlLoader = new FXMLLoader(getClass().getResource("/cinemo/view/ObrisiView.fxml"));
                Parent root5 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Brisanje projekcije..."); 
                 stage.setScene(new Scene(root5));
                 stage.show();
    }
    public void handlePregled(ActionEvent evt) throws FileNotFoundException, IOException{
        String dbURL = "jdbc:mysql://localhost:3306/cinemo_db";
        String username = "root";
        String password = "";
 
    try (Connection konekcija = DriverManager.getConnection(dbURL, username, password)) {
     
    
    String sql = "SELECT * FROM movies";
 
    Statement statement = konekcija.createStatement();
    ResultSet rs = statement.executeQuery(sql);
 
    int count = 0;
 
            try {
                while (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String genre = rs.getString("genre");
                    String about = rs.getString("about");
                    int length = rs.getInt("length");
                    String hall = rs.getString("hall");
                    int numofseats = rs.getInt("numofseats");
                    String dateandtime = rs.getString("dateandtime");
                    String price = rs.getString("price");
                       
                    
                    TextArea film = new TextArea();
                    film.setEditable(false);
                    film.setFont(Font.font("System", FontWeight.BOLD, 12));
                    film.setPrefHeight(prikaz.getHeight());
                    film.setPrefWidth(prikaz.getWidth() - 7);
                    film.setWrapText(true);
                    film.setText("Redni broj: " + id + "    " + "Naziv projekcije:  " + name + "    " + "Žanr:  " + genre + "\n" 
                            + "O projekciji: \n" + about + "\n" + "Trajanje u min: " + length + "    " + "Dvorana: " + hall + "\n" + "Broj slobodnih sjedala:"+numofseats 
                            + "\n" +"Datum i vrijeme prikazivanja: " + dateandtime + "    " +"Cijena: "+ price);
                    this.prikaz.getChildren().add(film);
                    
                }       } catch (SQLException ex) {
                                Logger.getLogger(AdminViewController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
     
    }
    public void handleRezervacijeBtn(ActionEvent evt) throws IOException{
         
                  String dbURL = "jdbc:mysql://localhost:3306/cinemo_db";
                  String username = "root";
                  String password = "";
 
    try (Connection konekcija = DriverManager.getConnection(dbURL, username, password)) {
     
                
                 String sql = "SELECT * FROM reservations";
 
                Statement statement = konekcija.createStatement();
                ResultSet rs = statement.executeQuery(sql);
 
                int count = 0;
 
            try {
                while (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("imeIprezime");
                    int amount = rs.getInt("kolicina");
                    String about = rs.getString("oFilmu");
                   
              
                    TextArea film = new TextArea();
                    film.setEditable(false);
                    film.setFont(Font.font("System", FontWeight.BOLD, 12));
                    film.setPrefHeight(prikaz.getHeight());
                    film.setPrefWidth(prikaz.getWidth() - 7);
                    film.setWrapText(true);
                    film.setText("Redni broj: " + id + "    " + "Ime i prezime:  " + name + "    " + "Kolicina:  " + amount + "\n" + "Za: \n" + about);
                    this.prikaz.getChildren().add(film);
                    
                }       } catch (SQLException ex) {
                                Logger.getLogger(AdminViewController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    }
    public void handleOdjava(ActionEvent evt) throws IOException{
        FXMLLoader  fxmlLoader = new FXMLLoader(getClass().getResource("/cinemo/view/HomePage.fxml"));
                Parent root6 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Dobrodošli !"); 
                 stage.setScene(new Scene(root6));
                 stage.show();
                ActionEvent event6 = evt.copyFor(stage, stage);
                handleCloseButtonAction(event6);
    }
    public void handleOcistiPanelBtn(ActionEvent evt) throws IOException{
    FXMLLoader  fxmlLoader = new FXMLLoader(getClass().getResource("/cinemo/view/AdminView.fxml"));
                Parent root7 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Administrator"); 
                 stage.setScene(new Scene(root7));
                 stage.show();
                ActionEvent event7 = evt.copyFor(stage, stage);
                handleCloseButtonAction(event7);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
