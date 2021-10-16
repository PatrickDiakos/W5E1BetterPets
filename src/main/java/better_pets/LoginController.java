package better_pets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    
    Database database = new Database();
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Button loginButton;
    
    @FXML
    public Label outputLabel;
    
    public void intialize() throws SQLException {
        System.out.println("INITIALISING LOGIN CONTROLLER");
        Database databaseConnection = new Database();
        databaseConnection.setupDatabase();
        databaseConnection.initialize();
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException, SQLException {
        System.out.println("clicked login");
        // Check if username and password exist as a user in the database
        // (Hint: use a function in the Database class)
        if (usernameField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {
            
            Database databaseConnection = new Database();
            boolean result = databaseConnection.login(usernameField.getText(), passwordField.getText());
            if (result == true) {
                switchToPetTable();
            } else {
                outputLabel.setText("Invalid username or password");
            }
            
        } else {
            outputLabel.setText("Please enter a username and password");
        }

        // If the username and password are correct - move to the PetTable screen
        // Otherwise display text "Incorrect username or password"        
    }

    private void switchToPetTable() throws IOException {
        App.setRoot("PetsScreen");
    }
    
}