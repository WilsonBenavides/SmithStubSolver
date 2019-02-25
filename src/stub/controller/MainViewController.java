package stub.controller;

import java.io.IOException;

import stub.Main;
import javafx.fxml.FXML;

public class MainViewController {

	
	@FXML
	private void goHome() throws IOException {
		Main.showMainItems();
//		System.out.println("cick home!");
	}
	
	@FXML
	private void addBtn() throws IOException {
		Main.showDoubleStubStage();
//		System.out.println("cick add!");
	}
}
