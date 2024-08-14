package application.Controller;

import application.Storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import application.Main;
import application.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SignUpController {

	static Connection connection;

	@SuppressWarnings("static-access")
	public SignUpController() {
		try {
			this.connection = DatabaseConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Main m = new Main();

	@FXML
	private Label error;

	@FXML
	private Label loginHeading;

	@FXML
	private Label passwordLabel;

	@FXML
	private Label passwordLabel1;

	@FXML
	private PasswordField phoneField;

	@FXML
	private Button signUpButton;

	@FXML
	private Button backButton;

	@FXML
	private TextField usernameField;

	@FXML
	private TextField passwordField;

	@FXML
	private Label usernameLabel;

	@FXML
	void signUpButton(ActionEvent event) {

		// int userId, String username, String password, String phone, String role
		User user = new User(0, usernameField.getText(), passwordField.getText(), phoneField.getText(), "user");
		boolean b = populateUserTable(user);

		if(b == true) 
		{
			Storage.u.add(user);
			
			Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
			m.start(s);
		}
		
	}

	@FXML
	void backButton(ActionEvent event) {
		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		m.start(s);
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

}
