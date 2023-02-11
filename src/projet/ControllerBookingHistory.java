package projet;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

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

public class ControllerBookingHistory implements Initializable{

	private Stage stage;
	private Scene scene;
	private Parent root;


    @FXML
    private TableColumn<BOOKING_HISTORY, Integer> IdBooking;

    @FXML
    private TableColumn<BOOKING_HISTORY, Integer> IdVenue;

    @FXML
    private TableColumn<BOOKING_HISTORY, Date> date;

    @FXML
    private TableColumn<BOOKING_HISTORY, String> eventtype;

    @FXML
    private TableColumn<BOOKING_HISTORY, String> foodt;

    @FXML
    private TableColumn<BOOKING_HISTORY, Integer> numberguest;

    @FXML
    private TableColumn<BOOKING_HISTORY, String> place;

    @FXML
    private TableView<BOOKING_HISTORY> tabl;

	
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
	 public static ObservableList<BOOKING_HISTORY> getTableBookingH () throws SQLException{
			
	        connect();
	        ObservableList<BOOKING_HISTORY>list = FXCollections.observableArrayList();
	        try{
				Statement stmt = conn.createStatement();
			      ResultSet rs = stmt.executeQuery("SELECT * FROM book_event");
	            while(rs.next()){
	                list.add(new BOOKING_HISTORY(Integer.parseInt(rs.getString("booking_id")),rs.getString("event_type"),rs.getString("place"),Integer.parseInt(rs.getString("num_guest")),rs.getDate("date"),Integer.parseInt(rs.getString("venue_id")),rs.getString("food_type") ));
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }
	    
	
	public void showListe() throws SQLException {
		ObservableList<BOOKING_HISTORY>list = getTableBookingH ();
		 IdBooking.setCellValueFactory(new PropertyValueFactory<BOOKING_HISTORY,Integer>("ID"));
		 date.setCellValueFactory(new PropertyValueFactory<BOOKING_HISTORY,Date>("date"));
		 eventtype.setCellValueFactory(new PropertyValueFactory<BOOKING_HISTORY,String>("EVtype"));
		 place.setCellValueFactory(new PropertyValueFactory<BOOKING_HISTORY,String>("Place"));
		 numberguest.setCellValueFactory(new PropertyValueFactory<BOOKING_HISTORY,Integer>("NumG"));
		 IdVenue.setCellValueFactory(new PropertyValueFactory<BOOKING_HISTORY,Integer>("IDv"));
		 foodt.setCellValueFactory(new PropertyValueFactory<BOOKING_HISTORY,String>("FoodT"));
         tabl.setItems(list);
    	
        
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
