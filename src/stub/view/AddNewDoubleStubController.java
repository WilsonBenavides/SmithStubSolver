package stub.view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import stub.Main;

public class AddNewDoubleStubController {

	ObservableList<String> typeStubList = FXCollections.observableArrayList("Serie - Serie", "Paralelo - Paralelo");

	@FXML
	private TextField zoField;
	@FXML
	private TextField zosField;
	@FXML
	private TextField rlField;
	@FXML
	private TextField xlField;
	@FXML
	private TextField d1Field;
	@FXML
	private TextField d2Field;
	@FXML
	private TextField freqField;
	@FXML
	private TextField fvField;
	@FXML
	private TextField fvsField;
	@FXML
	private Button okBtn;
	@FXML
	private Button cancelBtn;
	@FXML
	private ComboBox<String> typeStub;

	@FXML
	private void initialize() {
		typeStub.setValue("Paralelo - Paralelo");
		typeStub.setItems(typeStubList);

		zoField.setText("50");
		zosField.setText("75");
		rlField.setText("30");
		xlField.setText("-40");
		d1Field.setText("0.280");
		d2Field.setText("0.380");
		freqField.setText("345");
		fvField.setText("0.8");
		fvsField.setText("0.9");
	}

	@FXML
	private void handleOkBtn() throws IOException {
		String tmp = typeStub.getValue();
		Main.clearData();
		Main.addData(zoField.getText());
		Main.addData(rlField.getText());
		Main.addData(xlField.getText());
		Main.addData(d1Field.getText());
		Main.addData(d2Field.getText());
		Main.addData(freqField.getText());

		int sep = tmp.indexOf("-");
		int last = tmp.length();
		String s1 = tmp.substring(0, sep).trim();
		String s2 = tmp.substring(sep + 1, last).trim();

		Main.addData(s1);
		Main.addData(s2);
		Main.closeDoubleStubStage();
		Main.showDoubleStubSolverScene();
	}

	@FXML
	private void handleCancelBtn() {
		Main.closeDoubleStubStage();
	}
}
