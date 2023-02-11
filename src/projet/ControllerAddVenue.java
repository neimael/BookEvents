package projet;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.*;

public class ControllerAddVenue implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private Button returnB;

	@FXML
	private Button Breset;

	@FXML
	private Button Bsave;

	@FXML
	private TableColumn<LieuV, String> EvTypeC;

	@FXML
	private ComboBox<String> TypeCombo;

	@FXML
	private TextField adressTxt;

	@FXML
	private TableColumn<LieuV, String> adrssC;

	@FXML
	private TableColumn<LieuV, Integer> capacityC;

	@FXML
	private TextField capacityTxt;

	@FXML
	private TableColumn<LieuV, String> nameC;

	@FXML
	private TextField nameTxt;

	@FXML
	private TextField numTxt;

	@FXML
	private TableColumn<LieuV, String> phoneC;

	@FXML
	private TableView<LieuV> tableview;

	Connection conn;
	PreparedStatement pst;

	public void connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/events", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<LieuV> getTableLIEU() throws SQLException {

		connect();
		ObservableList<LieuV> list = FXCollections.observableArrayList();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM lieu ");

			while (rs.next()) {
				list.add(new LieuV(rs.getString("name"), rs.getString("adress"), rs.getString("phone_number"),
						Integer.parseInt(rs.getString("capacity")), rs.getString("prefered_for")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@FXML
	void Save(ActionEvent event) throws SQLException {
		connect();
		String name = nameTxt.getText();
		String adress = adressTxt.getText();
		String phone = numTxt.getText();
		String capacity = capacityTxt.getText();
		String TypeEv = (String) TypeCombo.getValue();

		try {
			pst = conn.prepareStatement(
					"insert into lieu (name,adress,phone_number,capacity,prefered_for)values(?,?,?,?,?)");
			pst.setString(1, name);
			pst.setString(2, adress);
			pst.setString(3, phone);
			pst.setString(4, capacity);
			pst.setString(5, TypeEv);

			int status = pst.executeUpdate();
			if (status == 1) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText("Lieu");
				alert.setContentText("Venue Added Successfylly !");
				alert.showAndWait();

				nameTxt.setText("");
				adressTxt.setText("");
				numTxt.setText("");
				capacityTxt.setText("");
				TypeCombo.setValue("");
				nameTxt.requestFocus();

				showListe();
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Fail");
				alert.setHeaderText("Lieu");
				alert.setContentText("Venue Added Failed !");
				alert.showAndWait();
			}
		} catch (SQLException e) {

		}
	}

	int index = -1;

	@FXML
	void getSelected(MouseEvent event) {
		index = tableview.getSelectionModel().getSelectedIndex();
		if (index <= -1) {
			return;
		}
		nameTxt.setText(nameC.getCellData(index).toString());
		adressTxt.setText(adrssC.getCellData(index).toString());
		numTxt.setText(phoneC.getCellData(index).toString());
		capacityTxt.setText(capacityC.getCellData(index).toString());
		TypeCombo.setValue(EvTypeC.getCellData(index).toString());
	}

	public void showListe() throws SQLException {
		ObservableList<LieuV> list = getTableLIEU();
		adrssC.setCellValueFactory(new PropertyValueFactory<LieuV, String>("ADRESS"));
		phoneC.setCellValueFactory(new PropertyValueFactory<LieuV, String>("PhoneNUM"));
		capacityC.setCellValueFactory(new PropertyValueFactory<LieuV, Integer>("CAPACITY"));
		EvTypeC.setCellValueFactory(new PropertyValueFactory<LieuV, String>("EVt"));
		nameC.setCellValueFactory(new PropertyValueFactory<LieuV, String>("NAME"));
		tableview.setItems(list);
	}

	@FXML
	void exit(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("HomeAdmin.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void Supprimer(ActionEvent event) throws SQLException, IOException {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirm deletion");
		alert.setContentText("This Venue will be permanently deleted.\nDo you want to continue?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			delete();
		}
	}

	public void delete() throws SQLException, IOException {
		connect();
		String name = nameTxt.getText();
		String adress = adressTxt.getText();
		String phone = numTxt.getText();
		String capacity = capacityTxt.getText();
		String TypeEv = (String) TypeCombo.getValue();

		String sql = "delete from lieu where name = ? and adress = ? and phone_number = ? and capacity = ? and prefered_for = ?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, name);
		pst.setString(2, adress);
		pst.setString(3, phone);
		pst.setString(4, capacity);
		pst.setString(5, TypeEv);
		pst.execute();

		showListe();

		nameTxt.setText("");
		adressTxt.setText("");
		numTxt.setText("");
		capacityTxt.setText("");
		TypeCombo.setValue("");
		nameTxt.requestFocus();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ObservableList<String> list = FXCollections.observableArrayList("Wedding Party", "Birthday Party",
				"Welcome Baby Party", "Graduation Party", "Halloween Party", "New Year", "Dance Party",
				"Christmas Party", "Friendship Day", "Game party", "Divorce party");
		TypeCombo.setItems(list);
	}
}
