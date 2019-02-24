package com.willy.smith;

import com.willy.smith.chart.SmithChart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static final double width = 200;
	private static final double height = width * 0.618;
	private static final double scale = 5;
	
	private SmithChart smith = new SmithChart();

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		Pane root = new Pane();
		root.setLayoutX(0);
		root.setLayoutY(0);
		primaryStage.setScene(createScene(root, width*scale, height*scale));
		primaryStage.setTitle("Smith Chart Utility");
		primaryStage.show();
	}

	private Scene createScene(Pane root, double cWidth, double cHeight) {
		smith.setLayoutX(30);
		root.getChildren().addAll(smith);
		Scene scene = new Scene(root, cWidth, cHeight);
		return scene;
	}
	
	public static double getWidth() {
		return width*scale;
	}
	
	public static double getHeight() {
		return height*scale;
	}
}
