package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;

import application.Main;
import application.Storage;
import application.Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class UserController implements Initializable {

	static Connection connection;

	@SuppressWarnings("static-access")
	public UserController() {
		try {
			this.connection = DatabaseConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Main m = new Main();

	static boolean check = false;
	static int index = -1;

	@FXML
	private Button add;

	@FXML
	private Label countrylabel;

	@FXML
	private Button delete;

	@FXML
	private Label heading;

	@FXML
	private TableColumn<User, Integer> idCol;

	@FXML
	private TextField passwordField;

	@FXML
	private TableColumn<User, String> phoneCol;

	@FXML
	private TextField phoneField;

	@FXML
	private TableColumn<User, String> roleCol;

	@FXML
	private TextField roleField;

	@FXML
	private TableView<User> table = new TableView<User>();;

	@FXML
	private Label townLabel;

	@FXML
	private Label typeLabel;

	@FXML
	private Button update;

	@FXML
	private Button logout;

	@FXML
	private Label user;

	@FXML
	private TableColumn<User, String> usernameCol;

	@FXML
	private TextField usernameField;

	@FXML
	private Button viewAllUsers;

	@FXML
	private Button addSelectedRowBTN;

	public static ObservableList<User> p = FXCollections.observableArrayList();
	public static ObservableList<User> list = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		idCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("userId"));
		usernameCol.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<User, String>("phone"));
		roleCol.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
		table.setItems(list);
	}

	@FXML
	void add(ActionEvent event) {

		if (check == false) {

			String username = usernameField.getText();
			if (username.isEmpty()) {
				// Show an error dialog indicating that the type field is empty
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Parsing username");
				alert.setHeaderText(null);
				alert.setContentText("username field is empty.");
				alert.showAndWait();
				// Return to prevent adding the row with missing type
				return;
			}

			String password = passwordField.getText();
			if (password.isEmpty()) {
				// Show an error dialog indicating that the type field is empty
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Parsing password");
				alert.setHeaderText(null);
				alert.setContentText("password field is empty.");
				alert.showAndWait();
				// Return to prevent adding the row with missing type
				return;
			}

			String phone = phoneField.getText();
			if (phone.isEmpty()) {
				// Show an error dialog indicating that the type field is empty
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Parsing phone");
				alert.setHeaderText(null);
				alert.setContentText("phone field is empty.");
				alert.showAndWait();
				// Return to prevent adding the row with missing type
				return;
			}

			String role = roleField.getText();
			if (phone.isEmpty()) {
				// Show an error dialog indicating that the type field is empty
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Parsing role");
				alert.setHeaderText(null);
				alert.setContentText("role field is empty.");
				alert.showAndWait();
				// Return to prevent adding the row with missing type
				return;
			}

			// int userId, String username, String password, String phone, String role
			User object = new User(0, username, password, phone, role);
			boolean b = populateUserTable(object);

			if (b == true) {
				populateUserList();

				// Create an alert indicating that the property has been added
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Property Added");
				alert.setHeaderText(null);
				alert.setContentText("User has been successfully added.");

				// Show the alert
				alert.showAndWait();

				clearRecords();
			}

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
			User selectedItem = table.getSelectionModel().getSelectedItem();

			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(selectedItem)) {
					index = i;
				}
			}

			usernameField.setText(selectedItem.getUsername());
			passwordField.setText(selectedItem.getPassword());
			phoneField.setText(selectedItem.getPhone());
			roleField.setText(selectedItem.getRole());

			check = true;
		} else {
			// Handle case where no row is selected
			System.out.println("No row selected");
		}

	}

	@FXML
	void delete(ActionEvent event) {
		if (index != -1 && check == true) {

			User u = list.get(index);

			boolean b = deleteUser(u);
			System.out.println(b);

			if (b == true) {

				// If at least one row was updated
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("User Deleted");
				alert.setHeaderText(null);
				alert.setContentText("User '" + u.getUsername() + "' has been Deleted.");
				alert.showAndWait();
			} else {

				// If no rows were updated (userId not found)
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("User Not Found");
				alert.setHeaderText(null);
				alert.setContentText("User '" + u.getUsername() + "' not found.");
				alert.showAndWait();
			}

			if (b == true) {
				populateUserList();

//			list.remove(index);
//			p.remove(index);

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Record Deleted");
				alert.setHeaderText(null);
				alert.setContentText("Record deleted successfully.");
				// Show the alert
				alert.showAndWait();

				clearRecords();
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
	void update(ActionEvent event) {

		if (index != -1 && check == true) {

			try {

				User u = list.get(index);
				u.setPassword(passwordField.getText());
				u.setPhone(phoneField.getText());
				u.setRole(roleField.getText());

				boolean b = updateUser(u);

				if (b == true) {

					// If at least one row was updated
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("User Updated");
					alert.setHeaderText(null);
					alert.setContentText("User '" + u.getUsername() + "' has been updated.");
					alert.showAndWait();
				} else {

					// If no rows were updated (userId not found)
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("User Not Found");
					alert.setHeaderText(null);
					alert.setContentText("User '" + u.getUsername() + "' not found.");
					alert.showAndWait();
				}

//				list.set(index, u);
//				p.set(index, u);

				populateUserList();

//				Alert alert = new Alert(AlertType.INFORMATION);
//				alert.setTitle("user Updated");
//				alert.setHeaderText(null);
//				alert.setContentText("User '" + u.getUsername() + "' has been updated.");
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
	void logout(ActionEvent event) throws IOException {

		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		m.start(s);
	}

	public void clearRecords() {
		usernameField.clear();
		passwordField.clear();
		phoneField.clear();
		roleField.clear();
	}

	// Method to populate User table
	public static boolean populateUserTable(User u) {

		try {
			String insertUserQuery = "INSERT INTO User (username, password, phone, role) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insertUserQuery);
			statement.setString(1, u.getUsername()); // Different username for each user
			statement.setString(2, u.getPassword()); // Different password for each user
			statement.setString(3, u.getPhone()); // Different phone number for each user
			statement.setString(4, u.getRole());
			statement.executeUpdate();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Tables Populated");
			alert.setHeaderText(null);
			alert.setContentText("User table have been populated.");
			alert.showAndWait();

			return true;

		} catch (SQLIntegrityConstraintViolationException e) {
			// Handle duplicate username error
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText(
					"Username '" + u.getUsername() + "' already exists. Please choose a different username.");
			alert.showAndWait();

			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Method to update User table
	public static boolean updateUser(User u) {
		try {
			String updateUserQuery = "UPDATE User SET password = ?, phone = ?, role = ? WHERE userID = ?";
			PreparedStatement statement = connection.prepareStatement(updateUserQuery);

			statement.setString(1, u.getPassword()); // Update password
			statement.setString(2, u.getPhone()); // Update phone number
			statement.setString(3, u.getRole()); // Update role
			statement.setInt(4, u.getUserId()); // Condition: userId

			int rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

//	// Method to delete a user from the User table
//	public static boolean deleteUser(int userID) {
//		try {
//			String deleteUserQuery = "DELETE FROM User WHERE userID = ?";
//			PreparedStatement statement = connection.prepareStatement(deleteUserQuery);
//			statement.setInt(1, userID);
//
//			System.out.println(userID);
//			
//			int rowsAffected = statement.executeUpdate();
//
//			if (rowsAffected > 0) {
//				// If at least one row was deleted
//				return true;
//			} else {
//				// If no rows were deleted (userId not found)
//				return false;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}

	// Method to delete a user along with associated records in the Property table
	public static boolean deleteUser(User u) {
		Connection connection = null;
		try {
			connection = DatabaseConnection.getConnection();
			// Start a transaction
			connection.setAutoCommit(false);

			// Delete associated records in the Property table
			String deletePropertyQuery = "DELETE FROM Property WHERE userID = ?";
			PreparedStatement propertyStatement = connection.prepareStatement(deletePropertyQuery);
			propertyStatement.setInt(1, u.getUserId());
			int propertyRowsAffected = propertyStatement.executeUpdate();
			propertyStatement.close(); // Close statement to release resources

			// Delete the user record in the User table
			String deleteUserQuery = "DELETE FROM User WHERE userID = ?";
			PreparedStatement userStatement = connection.prepareStatement(deleteUserQuery);
			userStatement.setInt(1, u.getUserId());
			int userRowsAffected = userStatement.executeUpdate();

			// Commit the transaction if both deletes were successful
			if ((propertyRowsAffected > 0 && userRowsAffected > 0) || userRowsAffected > 0) {
				connection.commit();
				return true;
			} else {
				// If either delete failed, rollback the transaction
				connection.rollback();
				return false;
			}
		} catch (SQLException e) {
			// Rollback the transaction in case of exception
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException rollbackException) {
					rollbackException.printStackTrace();
				}
			}
			e.printStackTrace();
			return false;
		} finally {
			// Close the connection in the finally block
			if (connection != null) {
				try {
					connection.setAutoCommit(true); // Restore auto-commit mode
				} catch (SQLException closeException) {
//	                closeException.printStackTrace();
					System.out.println("Delete User finally block exception");
				}
			}
		}
	}

	public static void populateUserList() {
		try {
			Connection connection = DatabaseConnection.getConnection();
			String selectQuery = "SELECT * FROM User";
			PreparedStatement statement = connection.prepareStatement(selectQuery);
			ResultSet resultSet = statement.executeQuery();

			Storage.u.clear();
			p.clear();
			list.clear();

			while (resultSet.next()) {
//				System.out.println(resultSet.getInt("userId") + "  " + resultSet.getString("username") + "  "
//						+ resultSet.getString("password") + "  " + resultSet.getString("phone") + "  "
//						+ resultSet.getString("role"));

				Storage.u.add(new User(resultSet.getInt("userId"), resultSet.getString("username"),
						resultSet.getString("password"), resultSet.getString("phone"), resultSet.getString("role")));

				p.add(new User(resultSet.getInt("userId"), resultSet.getString("username"),
						resultSet.getString("password"), resultSet.getString("phone"), resultSet.getString("role")));
				list.add(new User(resultSet.getInt("userId"), resultSet.getString("username"),
						resultSet.getString("password"), resultSet.getString("phone"), resultSet.getString("role")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}