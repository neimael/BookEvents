package projet;

import java.io.IOException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ResourceBundle;

public class ControllerViewFeedback implements Initializable{

	private Stage stage;
	private Scene scene;
	private Parent root;
	
    private static Connection conn;
    ResultSet rs = null;


    public static void connect () throws SQLException {
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection("jdbc:mysql://localhost/events","root","");
    	} catch(ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    }
    
    public static ObservableList<FEEDBACK> getTableFeedback () throws SQLException{
		
        connect();
        ObservableList<FEEDBACK>list = FXCollections.observableArrayList();
        try{
			Statement stmt = conn.createStatement();
		      ResultSet rs = stmt.executeQuery("SELECT * FROM feedback");
            while(rs.next()){
                list.add(new FEEDBACK(rs.getString("name"),rs.getString("feedback") ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    

    @FXML
    private TableView<FEEDBACK> TableFeedback;
    
    @FXML
    private TableColumn<FEEDBACK, String> feedback;

    @FXML
    private TableColumn<FEEDBACK, String> name;

	
	@FXML
	private Button returnB;

	@FXML
	void exit(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("HomeAdmin.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void showListe() throws SQLException {
		ObservableList<FEEDBACK>list = getTableFeedback ();
		 name.setCellValueFactory(new PropertyValueFactory<FEEDBACK,String>("Nom"));
		 feedback.setCellValueFactory(new PropertyValueFactory<FEEDBACK,String>("FEED"));
         TableFeedback.setItems(list);
    	
        
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			showListe();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
