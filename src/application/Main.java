package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	
	private static final double WIDTH = 100;
	private static final double HEIGHT = 60;
	private static final double SCALE  =8;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			//Scene scene = new Scene(root,400,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//primaryStage.setScene(scene);
			primaryStage.setScene(createScene(root, WIDTH * SCALE, HEIGHT * SCALE));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Scene createScene(Pane root, double cWidth, double cHeight) {
		Button btn = new Button();
		btn.setText("Say Hello World");
		
		root.getChildren().add(btn);
		Scene scene = new Scene(root, cWidth, cHeight);
		return scene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
