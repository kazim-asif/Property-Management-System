package application;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import application.Controller.DatabaseConnection;
import application.Controller.PropertyController;
import application.Controller.UserController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {

	FXMLLoader loader = new FXMLLoader();

	@Override
	public void start(Stage primaryStage) {
		try {
			
			UserController.populateUserList();
			PropertyController.populatePropertyList();

			Parent root = FXMLLoader.load(getClass().getResource("View/Login.fxml"));
			Scene scene = new Scene(root, 535, 315);
			primaryStage.setTitle("Login");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		try {
			DatabaseConnection.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UserController.populateUserList();
		PropertyController.populatePropertyList();

		launch(args);
	}

	public void userCRUD(Stage currentStage) throws IOException {

		try {
			InputStream fxmlStream = getClass().getResourceAsStream("View/User.fxml");
			AnchorPane root = (AnchorPane) loader.load(fxmlStream);

			currentStage.setTitle("Admin Module");

			Scene scene = new Scene(root);
			currentStage.setResizable(false);
			currentStage.setScene(scene);
			currentStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void propertyCRUD(Stage currentStage) throws IOException {

		try {

			InputStream fxmlStream = getClass().getResourceAsStream("View/Property.fxml");
			AnchorPane root = (AnchorPane) loader.load(fxmlStream);

			currentStage.setTitle("User Module");

			Scene scene = new Scene(root);
			currentStage.setResizable(false);
			currentStage.setScene(scene);
			currentStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void signUP(Stage currentStage) throws IOException {

		try {

			InputStream fxmlStream = getClass().getResourceAsStream("View/SignUP.fxml");
			AnchorPane root = (AnchorPane) loader.load(fxmlStream);

			currentStage.setTitle("Sign Up");

			Scene scene = new Scene(root);
			currentStage.setResizable(false);
			currentStage.setScene(scene);
			currentStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
