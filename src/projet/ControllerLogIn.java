package projet;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerLogIn {
	@FXML
    private AnchorPane Login;

	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private Button bLogIn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    
    @FXML
    private Button bReturn;

	@FXML
	void OpenHomeAdmin(ActionEvent event) {
		if (username.getText().equals("admin") && password.getText().equals("admin12")) {
			try {
				root = FXMLLoader.load(getClass().getResource("HomeAdmin.fxml"));
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} 
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur !");
			alert.setHeaderText("Incorrect Information !");
			alert.setContentText("Username Or Password Is Incorrect !");
			if(alert.showAndWait().get()==ButtonType.OK) {
				stage.close();
			}
		}
	}

    @FXML
    void exitLogin(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
}
