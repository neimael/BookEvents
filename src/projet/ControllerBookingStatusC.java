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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerBookingStatusC implements Initializable{
	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private Button returnB;

	@FXML
    private  ComboBox<String>combobox;
	 @FXML
	private TableColumn<BOOKING_HISTORY, Date> da;


    @FXML
    private TableColumn<BOOKING_HISTORY, String> evT;

    @FXML
    private TableColumn<BOOKING_HISTORY, String> foodt;

    @FXML
    private TableColumn<BOOKING_HISTORY, Integer> id;

    @FXML
    private TableColumn<BOOKING_HISTORY, Integer> idv;

    @FXML
    private TableColumn<BOOKING_HISTORY, Integer> numg;

    @FXML
    private TableColumn<BOOKING_HISTORY, String> place;
    
    @FXML
    private TableView<BOOKING_HISTORY> tableV;
    
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
    
	@FXML
	void exit(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("HomeCustomer.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
    void search(ActionEvent event) throws SQLException {
	 	showListe();
    }

 public ObservableList<BOOKING_HISTORY> getTableBookingSC () throws SQLException{
		
        connect();
        ObservableList<BOOKING_HISTORY>list = FXCollections.observableArrayList();
        try{
			Statement stmt = conn.createStatement();
			String choix = combobox.getValue();
		      ResultSet rs = stmt.executeQuery("SELECT * FROM book_event WHERE event_type ='" + choix+"'");
		      
            while(rs.next()){
                list.add(new BOOKING_HISTORY(Integer.parseInt(rs.getString("booking_id")),rs.getString("event_type"),rs.getString("place"),Integer.parseInt(rs.getString("num_guest")),rs.getDate("date"),Integer.parseInt(rs.getString("venue_id")),rs.getString("food_type") ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    

 public void showListe() throws SQLException {
	ObservableList<BOOKING_HISTORY>list = getTableBookingSC ();
	 id.setCellValueFactory(new PropertyValueFactory<BOOKING_HISTORY,Integer>("ID"));
	 da.setCellValueFactory(new PropertyValueFactory<BOOKING_HISTORY,Date>("date"));
	 evT.setCellValueFactory(new PropertyValueFactory<BOOKING_HISTORY,String>("EVtype"));
	 place.setCellValueFactory(new PropertyValueFactory<BOOKING_HISTORY,String>("Place"));
	 numg.setCellValueFactory(new PropertyValueFactory<BOOKING_HISTORY,Integer>("NumG"));
	 idv.setCellValueFactory(new PropertyValueFactory<BOOKING_HISTORY,Integer>("IDv"));
	 foodt.setCellValueFactory(new PropertyValueFactory<BOOKING_HISTORY,String>("FoodT"));
     tableV.setItems(list);
     }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> list = FXCollections.observableArrayList("Wedding Party","Birthday Party","Welcome Baby Party","Graduation Party","Halloween Party","New Year","Dance Party","Christmas Party","Friendship Day","Game party","Divorce party");
		combobox.setItems(list);		
	}
}
