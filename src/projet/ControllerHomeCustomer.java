package projet;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerHomeCustomer {
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	 @FXML
	    private Button bBEv;

	    @FXML
	    private Button bBSt;

	    @FXML
	    private Button bGFee;

	    @FXML
	    private Button bVVe;

	    @FXML
	    private Button returnB;

	    @FXML
	    void exit(ActionEvent event) throws IOException {
	    	root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }

	    @FXML
	    void openBookEvent(ActionEvent event) throws IOException {
	    	root = FXMLLoader.load(getClass().getResource("BookEvent.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }

	    @FXML
	    void openBookingStatusC(ActionEvent event) throws IOException {
	    	root = FXMLLoader.load(getClass().getResource("BookingStatusC.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }

	    @FXML
	    void openGiveFeedback(ActionEvent event) throws IOException {
	    	root = FXMLLoader.load(getClass().getResource("GiveFeedback.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }

	    @FXML
	    void openViewVenue(ActionEvent event) throws IOException {
	    	root = FXMLLoader.load(getClass().getResource("ViewVenue.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }
}
