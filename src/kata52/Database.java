package kata52;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

class Database {
    private final String connectString;
    Connection con = null;

    public Database(String connectString) {
        this.connectString = connectString;
    }

    void open() {
        try {
            this.con = DriverManager.getConnection(this.connectString);
            System.out.println("Base de datos abierta.");
        } catch (SQLException ex) {
            System.out.println("ERROR: Database::open (SQLEXception)" + ex.getMessage());
        }
        
    }
    void close(){
        if (this.con != null) try {
            this.con.close();
            System.out.println("Base de datos cerrada.");
        } catch (SQLException ex) {
            System.out.println("ERROR: Database::close (SQLEXception)" + ex.getMessage());
        }
    }

    void insert(Person person) {
        String sql = "INSERT INTO PEOPLE (NAME, SURNAME, BIRTHDAY, AGE) VALUES (?,?,?,?)";
        try {
            PreparedStatement prepared = this.con.prepareStatement(sql);
            prepared.setString(1, person.getName());
            prepared.setString(2, person.getSurname());
            prepared.setString(3, person.getBirthday().toString());
            prepared.setInt(4, person.getAge());
            prepared.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: Database::insert (SQLException)" + ex.getMessage());
        }
        
    }

    void select() {
        String sql = "SELECT * FROM PEOPLE";
        try {
            Statement stmt = this.con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            System.out.println("Name\tSurname\tBirthday\tAge");
            while (res.next()){
            System.out.println(res.getString("Name") + "\t" +
                    res.getString("Surname") + "\t" +
                    res.getString("Birthday") + "\t" +
                    res.getInt("Age"));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: Database::select (SQLException)" + ex.getMessage());
        }
        
    }

    void truncate() {
        String sql = "DELETE FROM PEOPLE";
        try {
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
