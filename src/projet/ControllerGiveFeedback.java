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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class ControllerGiveFeedback {
	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
    private TextArea feedB;

    @FXML
    private TextField nameB;
	
	@FXML
	private Button returnB;

	Connection conn;
    PreparedStatement pst;

    public void connect () throws SQLException {
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection("jdbc:mysql://localhost/events","root","");
    	} catch(ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    }
    @FXML
    void send(ActionEvent event) throws SQLException {
    	connect();
    	String name = nameB.getText();
    	String feedback = feedB.getText();
    	
    	try {
    		pst = conn.prepareStatement("insert into feedback (name,feedback)values(?,?)");
    		pst.setString(1, name);
    		pst.setString(2, feedback);
    		
    		int status = pst.executeUpdate();
    		if(status==1) {
    			Alert alert = new Alert(Alert.AlertType.INFORMATION);
    			alert.setTitle("Success");
    			alert.setHeaderText("Feedback");
    			alert.setContentText("Ur Point Of View Added Successfylly !");
    			alert.showAndWait();
    			
    			nameB.setText("");
    			feedB.setText("");
    			nameB.requestFocus();
    		}
    		else {
    			Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setTitle("Fail");
    			alert.setHeaderText("Feedback");
    			alert.setContentText("Ur Point Of View Added Failed !");
    			alert.showAndWait();
    		}
    	} catch(SQLException e) {
    		
    	}
    }
    
	@FXML
	void exit(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("HomeCustomer.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
