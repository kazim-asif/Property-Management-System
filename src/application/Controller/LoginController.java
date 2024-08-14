package application.Controller;

import java.io.IOException;

import application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController {

	Main m = new Main();

    @FXML
    private Label roleLabel;
	
	@FXML
	private Label error;

	@FXML
	private Button loginButton;

	@FXML
	private Label loginHeading;

	@FXML
	private Label passwordLabel;

	@FXML
	private TextField usernameField;
	

    @FXML
    private Button signUpButton;

	@FXML
	private TextField passwordFiled;

	@FXML
	private Label usernameLabel;

	@FXML
	private ToggleGroup toggleGroup;

	@FXML
	private RadioButton userRadioButton;

	@FXML
	private RadioButton adminRadioButton;

	@FXML
	private void initialize() {
		// Create a ToggleGroup
		toggleGroup = new ToggleGroup();

		// Associate the radio buttons with the ToggleGroup
		userRadioButton.setToggleGroup(toggleGroup);
		adminRadioButton.setToggleGroup(toggleGroup);

		// Set default selection
		userRadioButton.setSelected(true);
	}

	@FXML
	void loginButton(ActionEvent event) throws IOException {

		String username = usernameField.getText();
		String password = passwordFiled.getText();

		if (adminRadioButton.isSelected()) {
			
			String radioButton = "Admin";
			
			boolean result = false;
			for(int i=0; i<Storage.u.size(); i++)
			{
				if(username.equals(Storage.u.get(i).getUsername()) && password.equals(Storage.u.get(i).getPassword()) && radioButton.equalsIgnoreCase(Storage.u.get(i).getRole())) 
				{
					result = true;
					Storage.loggedInUser = Storage.u.get(i);
				}
			}
			
			if (result == true) {
				Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
				m.userCRUD(s);
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Wrong Credantials");
				alert.setHeaderText(null);
				alert.setContentText("Username or Password is incorrect.");
				alert.showAndWait();
			}
		} else {
			String radioButton = "user";

			boolean result = false;
			for(int i=0; i<Storage.u.size(); i++)
			{
				if(username.equals(Storage.u.get(i).getUsername()) && password.equals(Storage.u.get(i).getPassword()) && radioButton.equalsIgnoreCase(Storage.u.get(i).getRole())) 
				{
					result = true;
					Storage.loggedInUser = Storage.u.get(i);
					System.out.println(Storage.loggedInUser.toString());
				}
			}			
			if (result == true) {
				Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
				m.propertyCRUD(s);
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Wrong Credantials");
				alert.setHeaderText(null);
				alert.setContentText("Username or Password is incorrect.");
				alert.showAndWait();
			}
		}
	}

	@FXML
	void signUpButton(ActionEvent event) throws IOException {

		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		m.signUP(s);
	}

}
