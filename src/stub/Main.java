package stub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Stage primaryStage;
	private static BorderPane mainLayout;
	private static Stage doubleStubStage;
	private static Stage doubleStubSolverStage;
	
	private static List<String> data = new ArrayList<String>();

	@Override
	public void start(Stage primaryStage) throws IOException {
		Main.primaryStage = primaryStage;
		Main.primaryStage.setTitle("Stub Solver App");
		showMainView();
		showMainItems();
	}
	
	private void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainView.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
		primaryStage.show();
	}
	
	public static void showMainItems() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainItems.fxml"));
		BorderPane mainItems = loader.load();
		mainLayout.setCenter(mainItems);
	}
	
	public static void showSimpleStubScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/stubpane/SingleStubPane.fxml"));
		BorderPane electricalDep = loader.load();
		mainLayout.setCenter(electricalDep);
	}
	
	public static void showDoubleStubScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/stubpane/DoubleStubPane.fxml"));
		BorderPane mechanicalDep = loader.load();
		mainLayout.setCenter(mechanicalDep);
	}
	
	public static void showDoubleStubStage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/AddNewDoubleStub.fxml"));
		BorderPane addNewDoubleStub = loader.load();
		
		doubleStubStage = new Stage();
		doubleStubStage.setTitle("Agregar Stub Doble");	
		doubleStubStage.initModality(Modality.WINDOW_MODAL);
		doubleStubStage.resizableProperty().setValue(Boolean.FALSE);
		doubleStubStage.initOwner(primaryStage);
		
		Scene scene = new Scene(addNewDoubleStub);
		doubleStubStage.setScene(scene);
		doubleStubStage.showAndWait();
	}

	public static void showDoubleStubSolverScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/stubpane/DoubleStubSolver.fxml"));
		BorderPane addNewDoubleStubSolver = loader.load();
		
		doubleStubSolverStage = new Stage();
		doubleStubSolverStage.setTitle("Double Stub Solver");	
		doubleStubSolverStage.initOwner(primaryStage);
		
		Scene scene = new Scene(addNewDoubleStubSolver);
		doubleStubSolverStage.setScene(scene);
		doubleStubSolverStage.show();
	}
	
	public static void closeDoubleStubStage() {
		doubleStubStage.close();
	}
	
	public static void hidePrimaryStage() {
		primaryStage.hide();
	}
	
	public static void showAddDoubleStubStage() {
		doubleStubStage.show();
	}
	
	public static void showPrimaryStage() {
		primaryStage.show();
	}
	
	public static void addData(String text) {
		data.add(text);
	}
	
	public static void clearData() {
		data.clear();
	}

	public static List<String> getData() {
		return data;
	}
	
	public static void closeDoubleStubSolver() {
		doubleStubSolverStage.close();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
