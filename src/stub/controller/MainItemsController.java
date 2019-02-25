package stub.controller;

import java.io.IOException;

import stub.Main;
import javafx.fxml.FXML;

public class MainItemsController {

	// private Main main;

	@FXML
	private void goSingleStub() throws IOException {
		Main.showSimpleStubScene();
		// System.out.println("click electrical!");
	}

	@FXML
	private void goDoubleStub() throws IOException {
		Main.showDoubleStubScene();
		// System.out.println("click mechanical!");
	}
}
