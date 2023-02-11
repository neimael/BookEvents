package projet;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerHomeAdmin {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	 @FXML
	    private AnchorPane Homedmin;

	    @FXML
	    private Button bADDv;

	    @FXML
	    private Button bBH;

	    @FXML
	    private Button bBs;

	    @FXML
	    private Button bVFe;

	    @FXML
	    private Button returnB;

	    @FXML
	    void OpenAddVenue(ActionEvent event) throws IOException {
	    	root = FXMLLoader.load(getClass().getResource("AddVenue.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }

	    @FXML
	    void OpenBookigHistory(ActionEvent event) throws IOException {
	    	root = FXMLLoader.load(getClass().getResource("BookingHistory.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }

	    @FXML
	    void OpenBookingStatus(ActionEvent event) throws IOException {
	    	root = FXMLLoader.load(getClass().getResource("BookingStatus.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }

	    @FXML
	    void OpenViewFeedback(ActionEvent event) throws IOException {
	    	root = FXMLLoader.load(getClass().getResource("ViewFeedback.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }

	    @FXML
	    void exit(ActionEvent event) throws IOException {
	    	root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }
}
