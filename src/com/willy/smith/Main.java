package com.willy.smith;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * @author Wilson Benavides - benavidesb@unicauca.edu.co
 * @version 0.0
 *
 */

public class Main extends Application {

	private static final String TITLE = "Smith Stub Solver";
	private static final double WIDTH = 100;
	private static final double HEIGHT = WIDTH * 0.618;
	private static final double SCALE = 8;

	private SmithChart smithChart = new SmithChart();

	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle(TITLE);
			primaryStage.setScene(createScene(root, WIDTH * SCALE, HEIGHT * SCALE));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Scene createScene(Pane root, double cWidth, double cHeight) {

		root.getChildren().add(smithChart);
		Scene scene = new Scene(root, cWidth, cHeight);
		return scene;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static double getWidth() {
		return WIDTH * SCALE;
	}

	public static double getHeight() {
		return HEIGHT * SCALE;
	}
}
