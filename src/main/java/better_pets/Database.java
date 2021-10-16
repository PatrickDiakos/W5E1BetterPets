/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package better_pets;

/**
 *
 * @author Rjian
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Database {
    
    
    final private String database = "jdbc:sqlite:PetDatabase.db";
    
    public void initialize() throws SQLException {
        setupDatabase();
    }
    
    public void setupDatabase() throws SQLException {
        System.out.println("HELLO");
        Connection conn = DriverManager.getConnection(database);
        Statement st = conn.createStatement();
        
        String createStatementUsers = "CREATE TABLE IF NOT EXISTS Users "
                + "(ID INTEGER PRIMARY KEY autoincrement, "
                + "USERNAME TEXT NOT NULL, "
                + "PASSWORD TEXT NOT NULL "
                + ");";
        String createStatementPets = "CREATE TABLE IF NOT EXISTS PETS "
                + "(ID INTEGER PRIMARY KEY autoincrement, "
                + "NAME TEXT NOT NULL, "
                + "SPECIES TEXT NOT NULL, "
                + "COLOUR TEXT NOT NULL, "
                + "OWNER TEXT NOT NULL "
                + ");";
        
        st.execute(createStatementUsers);
        st.execute(createStatementPets);
        
        String insertQuery = "INSERT OR IGNORE INTO Users (ID, USERNAME, PASSWORD) "
                + "VALUES (2, 'pdiakos', 'diakos#1');";
        String insertQuery2 = "INSERT OR IGNORE INTO PETS (ID, NAME, SPECIES, COLOUR, OWNER) "
                + "VALUES (99, 'TESTER', 'TESTER', 'TESTER', 'TESTER');";        
        System.out.println("FLAG1");
        st.execute(insertQuery);
        st.execute(insertQuery2);
        System.out.println("FLAG2");
        st.close();
        conn.close();
        
        // Connect to Database 
       
        // Create Users table, with id, username, and password fields
        
        // Insert a user account
        
        // Create Pets Table, with id, name, species, colour, and owner fields
        
        // Insert some pets into this table
        
        // Close connections and statements
    }
    
    public void insertPets() throws SQLException{
        //create connection
        Connection conn = DriverManager.getConnection(database);
        //create statement	
        Statement st = conn.createStatement();

        //write the SQL query and the java code to insert all four pets
        PreparedStatement pSt = conn.prepareStatement(
            "INSERT OR IGNORE INTO PETS (ID, NAME, SPECIES, COLOUR, OWNER) VALUES (?,?,?,?,?)"
        );

        // Data to insert
        String[] names = {"Kitty", "Blair", "Mimi", "QuackyMooMoo"};
        String[] species = {"cat", "cat", "frog", "dog"};
        String[] colour = {"grey", "white", "green", "brown"};
        String[] owner = {"Andrew", "Yenni", "Hatherine", "Phoebe"};

        // Loop to insert via sanitised prepared statements
        for (int i = 0; i < 4; i++) {
            pSt.setInt(1, i);
            pSt.setString(2, names[i]);
            pSt.setString(3, species[i]);
            pSt.setString(4, colour[i]);
            pSt.setString(5, owner[i]);
            pSt.executeUpdate();
        }
        
        st.close();
        conn.close();
    }
    
    public boolean login(String username, String password) throws SQLException {
        Connection conn = DriverManager.getConnection(database);
        PreparedStatement pst = conn.prepareStatement(
            "SELECT COUNT(1) FROM Users WHERE USERNAME = ? AND PASSWORD = ?"
        );
        pst.setString(1, username);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        rs.next();
        System.out.println(rs.getInt(1));
        boolean result = rs.getInt(1) == 1;
        return result; 
        // Check if user exists - if so return true, else return false
    }
    
    public ObservableList<Pet> getPets() throws SQLException {
        // Get ResultSet of all pets that exist in the database
        System.out.println("GETTING PETS");
        Connection conn = DriverManager.getConnection(database);
        Statement st = conn.createStatement();
        String query = "SELECT ID, NAME, SPECIES, COLOUR, OWNER FROM PETS";
        ResultSet rs = st.executeQuery(query);
        
        ObservableList<Pet> petsList = FXCollections.observableArrayList();
        // Add each row in the ResultSet to the petsList
        while (rs.next()) {
            petsList.add(new Pet((int) rs.getObject(1), rs.getObject(2).toString(), rs.getObject(3).toString(), rs.getObject(4).toString(), rs.getObject(5).toString()));
        }
        System.out.println(petsList);
        // Close 
        st.close();
        conn.close();
        return petsList;
    }
    

}
