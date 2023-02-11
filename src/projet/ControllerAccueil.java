package projet;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerAccueil {

	@FXML
	private Button bAdmin;

	@FXML
	private Button bCustomer;

	@FXML
    private AnchorPane scenePane;

	@FXML
	private Button bExit;

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	public void OpenLogin(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("LoginAdmin.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void OpenCustomer(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("HomeCustomer.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void exit(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit !");
		alert.setHeaderText("You're about to exit !");
		alert.setContentText("Are u sure u want to exit ?");
		if(alert.showAndWait().get()==ButtonType.OK) {
			stage = (Stage) scenePane.getScene().getWindow();
			stage.close();
		}
	}
	
	

}
