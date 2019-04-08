package cinemo.model;;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 


public class ConnectionToDB {
    private String host;
    private String korisnik;
    private String lozinka;
    private String baza;

    protected Connection konekcija;

    public ConnectionToDB () {
        this.host = "localhost";
        this.korisnik = "root";
        this.lozinka = "";
        this.baza = "cinemo_db";
        this.spoji();
    }

    public ConnectionToDB (String host, String korisnik, String lozinka, String baza) {
        this.host = host;
        this.korisnik = korisnik;
        this.lozinka = lozinka;
        this.baza = baza;
        this.spoji();
    }

    public void spoji () {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.konekcija = DriverManager.getConnection("jdbc:mysql://"+this.host+"/"+this.baza+"?"
                    + "user="+this.korisnik+"&password="+this.lozinka);
        } catch (ClassNotFoundException e) {
            System.out.println ("Sustav nije uspio pronaći klasu za konekciju na MYSQL...");
        } catch (SQLException e) {
            System.out.println ("Sustav nije se mogao spojiti na bazu podataka...");
        }
    }

    public void odspoji () {
        try {
            this.konekcija.close();
        } catch (SQLException e) {
            System.out.println ("Sustav nije uspio zatvoriti konekciju...");
        }
    }

}
