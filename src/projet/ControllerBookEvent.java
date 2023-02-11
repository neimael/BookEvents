package projet;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerBookEvent implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private ComboBox<String> combo;

	@FXML
	private TableColumn<BOOK, Date> dat;

	@FXML
	private DatePicker datepic;

	@FXML
	private TableColumn<BOOK, String> evt;

	@FXML
	private ComboBox<String> foodt;

	@FXML
	private TableColumn<BOOK, String> foot;

	@FXML
	private TableColumn<BOOK, Integer> id;

	@FXML
	private TextField idv;

	@FXML
	private TextField numG;

	@FXML
	private TableColumn<BOOK, Integer> numg;

	@FXML
	private TableColumn<BOOK, String> pla;

	@FXML
	private TextField place;

	@FXML
	private TableView<BOOK> table;

	@FXML
	private Button saveB;

	@FXML
	private Button Breturn;

	@FXML
	void exit(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("HomeCustomer.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	PreparedStatement pst;
	static Connection conn;

	public static void connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/events", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static ObservableList<BOOK> getTableBookinG() throws SQLException {

		connect();
		ObservableList<BOOK> list = FXCollections.observableArrayList();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM book_event");
			while (rs.next()) {
				list.add(new BOOK(rs.getString("event_type"), rs.getString("place"),
						Integer.parseInt(rs.getString("num_guest")), rs.getDate("date"),
						Integer.parseInt(rs.getString("venue_id")), rs.getString("food_type")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void showListe() throws SQLException {
		ObservableList<BOOK> list = getTableBookinG();
		dat.setCellValueFactory(new PropertyValueFactory<BOOK, Date>("date"));
		evt.setCellValueFactory(new PropertyValueFactory<BOOK, String>("EVtype"));
		pla.setCellValueFactory(new PropertyValueFactory<BOOK, String>("Place"));
		numg.setCellValueFactory(new PropertyValueFactory<BOOK, Integer>("NumG"));
		id.setCellValueFactory(new PropertyValueFactory<BOOK, Integer>("IDv"));
		foot.setCellValueFactory(new PropertyValueFactory<BOOK, String>("FoodT"));
		table.setItems(list);

	}

	@FXML
	void save(ActionEvent event) throws SQLException {
		connect();
		String TypeEv = (String) combo.getValue();
		LocalDate date = datepic.getValue();
		String FoodType = (String) foodt.getValue();
		String IDvenue = idv.getText();
		String Place = place.getText();
		String NumG = numG.getText();

		try {
			pst = conn.prepareStatement(
					"insert into book_event (date,event_type,food_type,num_guest,place,venue_id)values(?,?,?,?,?,?)");
			pst.setDate(1, Date.valueOf(date));
			pst.setString(2, TypeEv);
			pst.setString(3, FoodType);
			pst.setString(4, NumG);
			pst.setString(5, Place);
			pst.setString(6, IDvenue);

			int status = pst.executeUpdate();
			if (status == 1) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText("Book Event");
				alert.setContentText("Event Added Successfylly !");
				alert.showAndWait();

				datepic.setValue(null);
				combo.setValue("");
				foodt.setValue("");
				numG.setText("");
				place.setText("");
				idv.setText("");
				datepic.requestFocus();
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Fail");
				alert.setHeaderText("Book Event");
				alert.setContentText("Event Added Failed !");
				alert.showAndWait();
			}
		} catch (SQLException e) {

		}

		showListe();
	}

	int index = -1;

	@FXML
	void getSelected(MouseEvent event) {
		index = table.getSelectionModel().getSelectedIndex();
		if (index <= -1) {
			return;
		}
		combo.setValue(evt.getCellData(index).toString());
		datepic.setValue(dat.getCellData(index).toLocalDate());
		foodt.setValue(foot.getCellData(index).toString());
		idv.setText(id.getCellData(index).toString());
		place.setText(pla.getCellData(index).toString());
		numG.setText(numg.getCellData(index).toString());

	}

	public void delete() throws SQLException, IOException {
		connect();
		String TypeEv = (String) combo.getValue();
		LocalDate date = datepic.getValue();
		String FoodType = (String) foodt.getValue();
		String IDvenue = idv.getText();
		String Place = place.getText();
		String NumG = numG.getText();

		String sql = "delete from book_event where date = ? and event_type = ? and food_type = ? and num_guest = ? and place = ? and venue_id = ?";
		pst = conn.prepareStatement(sql);
		pst.setDate(1, Date.valueOf(date));
		pst.setString(2, TypeEv);
		pst.setString(3, FoodType);
		pst.setString(4, NumG);
		pst.setString(5, Place);
		pst.setString(6, IDvenue);
		pst.execute();

		datepic.setValue(null);
		combo.setValue("");
		foodt.setValue("");
		numG.setText("");
		place.setText("");
		idv.setText("");
		datepic.requestFocus();

		showListe();

	}

	@FXML
	void supprimer(ActionEvent event) throws SQLException, IOException {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirm deletion");
		alert.setContentText("This event will be permanently deleted.\nDo you want to continue?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			delete();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> list = FXCollections.observableArrayList("Wedding Party", "Birthday Party",
				"Welcome Baby Party", "Graduation Party", "Halloween Party", "New Year", "Dance Party",
				"Christmas Party", "Friendship Day", "Game party", "Divorce party");
		combo.setItems(list);

		ObservableList<String> list2 = FXCollections.observableArrayList("Moroccan", "American", "Mexican", "Indian",
				"Spanish");
		foodt.setItems(list2);
	}
}
