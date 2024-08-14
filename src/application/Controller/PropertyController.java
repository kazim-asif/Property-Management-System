package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import application.Storage;
import application.Model.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PropertyController implements Initializable {

	Main m = new Main();

	static Connection connection;

	@SuppressWarnings("static-access")
	public PropertyController() {
		try {
			this.connection = DatabaseConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static boolean check = false;
	static int index = -1;

	@FXML
	private Button tenantButton;

	@FXML
	private Button add;

	@FXML
	private Button search;

	@FXML
	private Button clearTable;

	@FXML
	private Button viewAllProperty;

	@FXML
	private Button addSelectedRowBTN;

	@FXML
	private TableColumn<Property, Integer> bathCol;

	@FXML
	private TextField bathField;

	@FXML
	private TextField typSearcheField;

	@FXML
	private Label bathLabel;

	@FXML
	private Label typeSearchLabel;

	@FXML
	private TableColumn<Property, Integer> bedCol;

	@FXML
	private TextField bedField;

	@FXML
	private Label bedLabel;

	@FXML
	private Label bedLabel1;

	@FXML
	private TableColumn<Property, String> countryCol;

	@FXML
	private TextField countryField;

	@FXML
	private Label countrylabel;

	@FXML
	private Button delete;

	@FXML
	private Label heading;

	@FXML
	private TableColumn<Property, Double> priceCol;

	@FXML
	private TextField priceField;

	@FXML
	private Label priceLabe;

	@FXML
	private TextField receptionField;

	@FXML
	private TableColumn<Property, Integer> receptionsCol;

	@FXML
	private TableColumn<Property, String> streetCol;

	@FXML
	private TextField streetField;

	@FXML
	private Label streetLabel;

	@FXML
	private TableView<Property> table = new TableView<Property>();

	@FXML
	private TableColumn<Property, String> townCol;

	@FXML
	private TextField townField;

	@FXML
	private Label townLabel;

	@FXML
	private TableColumn<Property, String> typeCol;

	@FXML
	private TableColumn<Property, Integer> userIDCol;

	@FXML
	private TextField typeField;

	@FXML
	private Label typeLabel;

	@FXML
	private Button update;

	@FXML
	private Button clear;

	@FXML
	private Button logout;

//	public ObservableList<PropertyClass> p = FXCollections.observableArrayList();
	public static ObservableList<Property> p = FXCollections.observableArrayList();
	public static ObservableList<Property> list = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		streetCol.setCellValueFactory(new PropertyValueFactory<Property, String>("Street"));
		townCol.setCellValueFactory(new PropertyValueFactory<Property, String>("Town"));
		countryCol.setCellValueFactory(new PropertyValueFactory<Property, String>("Country"));
		bedCol.setCellValueFactory(new PropertyValueFactory<Property, Integer>("Beds"));
		receptionsCol.setCellValueFactory(new PropertyValueFactory<Property, Integer>("Receptions"));
		bathCol.setCellValueFactory(new PropertyValueFactory<Property, Integer>("Baths"));
		typeCol.setCellValueFactory(new PropertyValueFactory<Property, String>("Type"));
		priceCol.setCellValueFactory(new PropertyValueFactory<Property, Double>("Price"));
		userIDCol.setCellValueFactory(new PropertyValueFactory<Property, Integer>("userID"));
		table.setItems(list);
	}

	@FXML
	void add(ActionEvent event) {

		if (check == false) {
			double price;
			try {
				price = Double.parseDouble(priceField.getText());
			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Empty Field");
				alert.setHeaderText(null);
				alert.setContentText("Please fill price field.");

				// Show the dialog
				alert.showAndWait();
				return; // Prevent adding the row with invalid price
			}

			int beds;
			try {
				beds = Integer.parseInt(bedField.getText());
			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Empty Field");
				alert.setHeaderText(null);
				alert.setContentText("Please fill bed field.");

				// Show the dialog
				alert.showAndWait();
				return; // Prevent adding the row with invalid price
			}

			int baths;
			try {
				baths = Integer.parseInt(bathField.getText());
			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Empty Field");
				alert.setHeaderText(null);
				alert.setContentText("Please fill bath field.");

				// Show the dialog
				alert.showAndWait();
				return; // Prevent adding the row with invalid price
			}

			String type = typeField.getText();
			if (type.isEmpty()) {
				// Show an error dialog indicating that the type field is empty
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Parsing type");
				alert.setHeaderText(null);
				alert.setContentText("Type field is empty.");

				// Show the dialog
				alert.showAndWait();

				// Return to prevent adding the row with missing type
				return;
			}

			String country = countryField.getText();
			if (country.isEmpty()) {
				// Show an error dialog indicating that the country field is empty
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Parsing Country");
				alert.setHeaderText(null);
				alert.setContentText("Country field is empty.");

				// Show the dialog
				alert.showAndWait();

				// Return to prevent adding the row with missing type
				return;
			}

			int reception;
			try {
				reception = Integer.parseInt(receptionField.getText());
			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Empty Field");
				alert.setHeaderText(null);
				alert.setContentText("Please fill reception field.");

				// Show the dialog
				alert.showAndWait();
				return; // Prevent adding the row with invalid price
			}

			String town = townField.getText();
			if (town.isEmpty()) {
				// Show an error dialog indicating that the town field is empty
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Parsing town");
				alert.setHeaderText(null);
				alert.setContentText("Town field is empty.");

				// Show the dialog
				alert.showAndWait();

				// Return to prevent adding the row with missing type
				return;
			}

			String street = streetField.getText();
			if (street.isEmpty()) {
				// Show an error dialog indicating that the town field is empty
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Parsing street");
				alert.setHeaderText(null);
				alert.setContentText("Street field is empty.");

				// Show the dialog
				alert.showAndWait();

				// Return to prevent adding the row with missing type
				return;
			}

//			int propertyID, String street, String town, int receptions, String country, String type, int baths, int beds,
//			double price, int userID
			Property object = new Property(0, street, town, reception, country, type.toLowerCase(), baths, beds, price,
					Storage.loggedInUser.getUserId());

			p.add(object);
			list.add(object);

			populatePropertyTable(object);

			// Create an alert indicating that the property has been added
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Property Added");
			alert.setHeaderText(null);
			alert.setContentText("Property has been successfully added.");

			// Show the alert
			alert.showAndWait();

			clearRecords();

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Duplicate");
			alert.setHeaderText(null);
			alert.setContentText("Record already present in List.");

			// Show the dialog
			alert.showAndWait();
		}
	}

	@FXML
	void addSelectedRow(ActionEvent event) {

		// Check if a row is selected
		if (table.getSelectionModel().getSelectedItem() != null) {
			// Get the selected item (assuming YourDataType represents your row data)
			Property selectedItem = table.getSelectionModel().getSelectedItem();

			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(selectedItem)) {
					index = i;
				}
			}

			streetField.setText(selectedItem.getStreet());
			receptionField.setText(String.valueOf(selectedItem.getReceptions()));
			townField.setText(selectedItem.getStreet());
			bedField.setText(String.valueOf(selectedItem.getBeds()));
			countryField.setText(selectedItem.getCountry());
			priceField.setText(String.valueOf(selectedItem.getPrice()));
			typeField.setText(selectedItem.getType());
			bathField.setText(String.valueOf(selectedItem.getBaths()));

			check = true;
		} else {
			// Handle case where no row is selected
			System.out.println("No row selected");
		}

	}

	@FXML
	void delete(ActionEvent event) {
		if (index != -1 && check == true) {

			Property prop = list.get(index);

			boolean b = deleteProperty(prop);

			if (b == true) {

				// If at least one row was updated
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Property Deleted");
				alert.setHeaderText(null);
				alert.setContentText("Property '" + prop.getPropertyID() + "' has been Deleted.");
				alert.showAndWait();
			} else {

				// If no rows were updated (userId not found)
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Property Not Found");
				alert.setHeaderText(null);
				alert.setContentText("Property '" + prop.getPropertyID() + "' has not been Deleted.");
				alert.showAndWait();
			}

			populatePropertyList();

//			list.remove(index);
//			p.remove(index);

//			Alert alert = new Alert(AlertType.INFORMATION);
//			alert.setTitle("Record Deleted");
//			alert.setHeaderText(null);
//			alert.setContentText("Record deleted successfully.");
//			// Show the alert
//			alert.showAndWait();

			clearRecords();

			check = false;
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Records Selected");
			alert.setHeaderText(null);
			alert.setContentText("Select the rocord first");

			// Show the alert
			alert.showAndWait();
		}
	}

	@FXML
	void update(ActionEvent event) {

		if (index != -1 && check == true) {

			try {

				Property prop = list.get(index);
				prop.setPrice(Double.parseDouble(priceField.getText()));
				prop.setBaths(Integer.parseInt(bathField.getText()));
				prop.setBeds(Integer.parseInt(bedField.getText()));

				boolean b = updateProperty(prop);

				if (b == true) {

					// If at least one row was updated
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Property Updated");
					alert.setHeaderText(null);
					alert.setContentText("Property '" + prop.getPropertyID() + "' has been updated.");
					alert.showAndWait();
				} else {

					// If no rows were updated (userId not found)
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Property Not Found");
					alert.setHeaderText(null);
					alert.setContentText("Property '" + prop.getPropertyID() + "' has not been updated.");
					alert.showAndWait();
				}

//				list.set(index, u);
//				p.set(index, u);

				populatePropertyList();

//				list.set(index, prop);
//				p.set(index, prop);

//				Alert alert = new Alert(AlertType.INFORMATION);
//				alert.setTitle("Property Updated");
//				alert.setHeaderText(null);
//				alert.setContentText("Property '" + prop.getPropertyID() + "' has been updated.");
//				// Show the alert
//				alert.showAndWait();

				clearRecords();

			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			check = false;
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Records Selected");
			alert.setHeaderText(null);
			alert.setContentText("Select the rocord first");

			// Show the alert
			alert.showAndWait();
		}

	}

	@FXML
	void clear(ActionEvent event) {
		check = false;
		clearRecords();
	}

	@FXML
	void clearTable(ActionEvent event) {
		table.getItems().clear();
	}

	@FXML
	void viewAll(ActionEvent event) {

		if (!p.isEmpty()) {
			list.clear();
			list.addAll(p);

		}
		if (list.isEmpty()) {
			// Create an alert indicating that no records are available
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("No Records Available");
			alert.setHeaderText(null);
			alert.setContentText("No records are available in the table.");

			// Show the alert
			alert.showAndWait();
		} else {
			// Set the items of the table to the list
			table.setItems(list);
		}
	}

	@FXML
	void search(ActionEvent event) {

		boolean present = false;

		String type = typSearcheField.getText();
		if (type.isEmpty()) {
			// Show an error dialog indicating that the type field is empty
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Parsing type");
			alert.setHeaderText(null);
			alert.setContentText("Search field is empty.");

			// Show the dialog
			alert.showAndWait();

			// Return to prevent adding the row with missing type
			return;
		} else {

			if (p.isEmpty()) {
				// Create an alert indicating that no records are available
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("No Records Available");
				alert.setHeaderText(null);
				alert.setContentText("No records are available in the table.");

				// Show the alert
				alert.showAndWait();
				return;
			} else {

				ObservableList<Property> l = FXCollections.observableArrayList();
				for (int i = 0; i < p.size(); i++) {
					
					if (p.get(i).getType().toLowerCase().equals(type.toLowerCase())) {
						l.add(p.get(i));
						present = true;
					}
				}

				table.setItems(l);
			}

			if (present == false) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("No record found");
				alert.setHeaderText(null);
				alert.setContentText("No record found with search type : " + type);

				// Show the alert
				alert.showAndWait();
				return;
			}
		}
	}

	public void clearRecords() {
		bathField.clear();
		receptionField.clear();
		bedField.clear();
		countryField.clear();
		priceField.clear();
		streetField.clear();
		townField.clear();
		typeField.clear();
	}

	@FXML
	void logout(ActionEvent event) throws IOException {

		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		m.start(s);
	}

	// Method to populate Property table
	private void populatePropertyTable(Property p) {
		try {
			String insertPropertyQuery = "INSERT INTO property (street, town, receptions, country, type, baths, beds, price, userID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insertPropertyQuery);
			statement.setString(1, p.getStreet());
			statement.setString(2, p.getTown());
			statement.setInt(3, p.getReceptions());
			statement.setString(4, p.getCountry());
			statement.setString(5, p.getType());
			statement.setInt(6, p.getBaths());
			statement.setInt(7, p.getBeds());
			statement.setDouble(8, p.getPrice());
			statement.setInt(9, Storage.loggedInUser.getUserId()); // Assuming user ID 1 is already created
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle exceptions
		}
	}

	// Method to update a Property
	public static boolean updateProperty(Property property) {
		try {
			// Check if the logged-in user matches the owner of the property
			if (Storage.loggedInUser.getUserId() == property.getUserID()) {
				Connection connection = DatabaseConnection.getConnection();
				String updatePropertyQuery = "UPDATE Property SET street = ?, town = ?, receptions = ?, "
						+ "country = ?, type = ?, baths = ?, beds = ?, price = ?, " + "userID = ? WHERE propertyID = ?";
				PreparedStatement statement = connection.prepareStatement(updatePropertyQuery);

				// Set values for the update
				statement.setString(1, property.getStreet());
				statement.setString(2, property.getTown());
				statement.setInt(3, property.getReceptions());
				statement.setString(4, property.getCountry());
				statement.setString(5, property.getType());
				statement.setInt(6, property.getBaths());
				statement.setInt(7, property.getBeds());
				statement.setDouble(8, property.getPrice());
				statement.setInt(9, property.getUserID());
				statement.setInt(10, property.getPropertyID()); // Condition: propertyID

				int rowsAffected = statement.executeUpdate();

				if (rowsAffected > 0) {
					return true;
				} else {
					return false;
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Denied");
				alert.setHeaderText(null);
				alert.setContentText("Logged-in user does not match the owner of the property.");
				alert.showAndWait();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Method to delete a Property
	public static boolean deleteProperty(Property property) {
	    try {
	        // Check if the logged-in user matches the owner of the property
	        if (Storage.loggedInUser.getUserId() == property.getUserID()) {
	            Connection connection = DatabaseConnection.getConnection();
	            String deletePropertyQuery = "DELETE FROM Property WHERE propertyID = ?";
	            PreparedStatement statement = connection.prepareStatement(deletePropertyQuery);

	            // Set propertyID as the condition for deletion
	            statement.setInt(1, property.getPropertyID()); // Use propertyID instead of userID

	            int rowsAffected = statement.executeUpdate();

	            if (rowsAffected > 0) {
	                return true;
	            } else {
	                return false;
	            }

	        } else {
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Denied");
	            alert.setHeaderText(null);
	            alert.setContentText("Logged-in user does not match the owner of the property.");
	            alert.showAndWait();
	            return false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	// Method to populate the Property list
	public static void populatePropertyList() {
		try {
			Connection connection = DatabaseConnection.getConnection();
			String selectQuery = "SELECT * FROM Property";
			PreparedStatement statement = connection.prepareStatement(selectQuery);
			ResultSet resultSet = statement.executeQuery();

			// Clear previous data before populating
			Storage.prop.clear();
			// Assuming p and list are ArrayLists of Property type
			p.clear();
			list.clear();

			while (resultSet.next()) {
//				System.out.println(resultSet.getInt("propertyID") + "  " + resultSet.getString("street") + "  "
//						+ resultSet.getString("town") + "  " + resultSet.getInt("receptions") + "  "
//						+ resultSet.getString("country") + "  " + resultSet.getString("type") + "  "
//						+ resultSet.getInt("baths") + "  " + resultSet.getInt("beds") + "  "
//						+ resultSet.getDouble("price") + "  " + resultSet.getInt("userID"));

				Property property = new Property(resultSet.getInt("propertyID"), resultSet.getString("street"),
						resultSet.getString("town"), resultSet.getInt("receptions"), resultSet.getString("country"),
						resultSet.getString("type"), resultSet.getInt("baths"), resultSet.getInt("beds"),
						resultSet.getDouble("price"), resultSet.getInt("userID"));

				Storage.prop.add(property);
				p.add(property);
				list.add(property);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
