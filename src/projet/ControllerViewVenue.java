package projet;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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

public class ControllerViewVenue implements Initializable{

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
    private TableColumn<LIEU, String> adr;

    @FXML
    private TableColumn<LIEU, Integer> capa;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private TableColumn<LIEU, Integer> id;

    @FXML
    private TableColumn<LIEU, String> name;

    @FXML
    private TableColumn<LIEU, String> pho;

    @FXML
    private Button returnB;

    @FXML
    private Button searchB;

    @FXML
    private TableView<LIEU> tableLieu;

    @FXML
    private TableColumn<LIEU, String> typeEv;

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
    void search(ActionEvent event) throws SQLException {
	 	showListe();
    }

 public ObservableList<LIEU> getTableLIEU () throws SQLException{
		
        connect();
        ObservableList<LIEU>list = FXCollections.observableArrayList();
        try{
			Statement stmt = conn.createStatement();
			String choix = combo.getValue();
		      ResultSet rs = stmt.executeQuery("SELECT * FROM lieu WHERE prefered_for ='" + choix+"'");
		      
            while(rs.next()){
                list.add(new LIEU(Integer.parseInt(rs.getString("venue_id")),rs.getString("name"),rs.getString("adress"),rs.getString("phone_number"),Integer.parseInt(rs.getString("capacity")),rs.getString("prefered_for") ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    

 public void showListe() throws SQLException {
	ObservableList<LIEU>list = getTableLIEU ();
	 id.setCellValueFactory(new PropertyValueFactory<LIEU,Integer>("ID"));
	 adr.setCellValueFactory(new PropertyValueFactory<LIEU,String>("ADRESS"));
	 pho.setCellValueFactory(new PropertyValueFactory<LIEU,String>("PhoneNUM"));
	 capa.setCellValueFactory(new PropertyValueFactory<LIEU,Integer>("CAPACITY"));
	 typeEv.setCellValueFactory(new PropertyValueFactory<LIEU,String>("EVt"));
	 name.setCellValueFactory(new PropertyValueFactory<LIEU,String>("NAME"));
     tableLieu.setItems(list);
     }
	    @FXML
	    void exit(ActionEvent event) throws IOException {
	    	root = FXMLLoader.load(getClass().getResource("HomeCustomer.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			ObservableList<String> list = FXCollections.observableArrayList("Wedding Party","Birthday Party","Welcome Baby Party","Graduation Party","Halloween Party","New Year","Dance Party","Christmas Party","Friendship Day","Game party","Divorce party");
			combo.setItems(list);			
		}

}
