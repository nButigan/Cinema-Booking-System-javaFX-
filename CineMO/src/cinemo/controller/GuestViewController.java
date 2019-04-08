/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemo.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import cinemo.controller.KupiFilmViewController;


public class GuestViewController implements Initializable {

   
    
    @FXML
    private Button ProjekcijeBtn;
    
    @FXML
    private Button IzlazBtn;
    
    @FXML
    private FlowPane prikaz;
    
    @FXML
    private Button kupiFilm;
    
    @FXML
    private AnchorPane kupiFilmPanel;
    
     @FXML
    private Button OcistiPanelBtn;
    
    
    
    
    public void handleCloseButtonAction(ActionEvent event)
    {
        Stage stage = (Stage) IzlazBtn.getScene().getWindow();
        stage.close();
    }
    //public void handleKosarica(ActionEvent evt) {}
    public void handleProjekcije(ActionEvent evt) {
     String dbURL = "jdbc:mysql://localhost:3306/cinemo_db";
        String username = "root";
        String password = "";
 
    try (Connection konekcija = DriverManager.getConnection(dbURL, username, password)) {
     
    // code to execute SQL queries goes here...
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
                       
                    //System.out.format("%d, %s, %s, %s, %d, %s, %d, %s, %s\n", id, name, genre, about, length,hall, numofseats, dateandtime, price);
                    Button kupiFilm = new Button("Kupi");
                    kupiFilm.setPrefWidth(this.prikaz.getWidth());
          
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
                    this.prikaz.getChildren().add(kupiFilm);
                    String oFilmu = name + " " + hall + "  " + dateandtime + "  " + price;
                    kupiFilm.setOnAction(action -> {
                                        
                                         
                                       FXMLLoader  fxmlLoader = new FXMLLoader(getClass().getResource("/cinemo/view/kupiFilmView.fxml"));
                                        Parent root8;
                        try {
                                        root8 = (Parent) fxmlLoader.load();
                                        Stage stage = new Stage();
                                        stage.setTitle("Rezervacija ulaznica..."); 
                                        stage.setScene(new Scene(root8));
                                        stage.show();
                                        KupiFilmViewController kupifilmviewController = fxmlLoader.getController();
                                        kupifilmviewController.transferMessage(oFilmu);
                        } catch (IOException ex) {
                            Logger.getLogger(GuestViewController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                                  
                                        
                                        
                            });
                    
                    
                }       } catch (SQLException ex) {
                                Logger.getLogger(AdminViewController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
     
    }
    public void handleOcistiPanel(ActionEvent evt) throws IOException{
    FXMLLoader  fxmlLoader = new FXMLLoader(getClass().getResource("/cinemo/view/GuestView.fxml"));
                Parent root11 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Gost"); 
                 stage.setScene(new Scene(root11));
                 stage.show();
                ActionEvent event11 = evt.copyFor(stage, stage);
                handleCloseButtonAction(event11);
    }
    
    public void handleIzlaz(ActionEvent evt) throws IOException {
         FXMLLoader  fxmlLoader = new FXMLLoader(getClass().getResource("/cinemo/view/HomePage.fxml"));
                Parent root10 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Dobrodošli !"); 
                 stage.setScene(new Scene(root10));
                 stage.show();
                ActionEvent event10 = evt.copyFor(stage, stage);
                handleCloseButtonAction(event10);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
