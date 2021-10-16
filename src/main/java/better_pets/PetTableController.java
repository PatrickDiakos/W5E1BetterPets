package better_pets;

import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PetTableController {

    Database database = new Database();
    
    @FXML
    TableView<Pet> petTbl;
    
    @FXML
    TableColumn<Pet, String> nameCol;
    
    @FXML
    TableColumn<Pet, String> speciesCol;
    
    @FXML
    TableColumn<Pet, String> colourCol;
    
    @FXML
    TableColumn<Pet, String> ownerCol;
    
    
    @FXML
    public void initialize() throws SQLException {
        // Get a list of all of pets in the database
        Database databaseConnection = new Database();
        // Set this list into the TableView
        petTbl.setItems(databaseConnection.getPets());
        System.out.println("PETTABLECONTROLLER INITIALISED");
        // Set all the columns in to tableview columns
        nameCol.setCellValueFactory(new PropertyValueFactory <Pet, String>("name"));
        speciesCol.setCellValueFactory(new PropertyValueFactory <Pet, String>("species"));
        colourCol.setCellValueFactory(new PropertyValueFactory <Pet, String>("colour"));
        ownerCol.setCellValueFactory(new PropertyValueFactory <Pet, String>("owner"));
    }
}