package com.willy.smith.chart;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SmithChart extends Parent {

	private static final double radio = 120 * 1.8;
	private static final double origenX = 0;
	private static final double origenY = 0;
	private static final Font FONT = Font.font("Comic Sans", FontWeight.BOLD, 30);
	private static final Font FONT2 = Font.font("Comic Sans", FontWeight.MEDIUM, 12);
	private static final Font FONT3 = Font.font("Comic Sans", FontWeight.NORMAL, 14);
	private static final Font FONT4 = Font.font("Comic Sans", FontWeight.MEDIUM, 18);

	private ArrayList<Circle> resCircles = new ArrayList<>();
	private List<Arc> reacCircles = new ArrayList<Arc>();
	private List<Circle> intersections = new ArrayList<Circle>();
	private List<Label> labels = new ArrayList<Label>();

	Text text = new Text("Características de la línea de transmisión");
	Label lbz0 = new Label("Zo = ");
	Label rl = new Label("Rl = ");
	Label xl = new Label("Xl = ");
	TextField tfz0 = new TextField();
	TextField tfrl = new TextField();
	TextField tfxl = new TextField();
	Button agregar = new Button("agregar");
	TextArea taOp = new TextArea();

	private Group chart = new Group();

	public SmithChart() {
		Separator separator = new Separator();
		separator.setOrientation(Orientation.VERTICAL);
		HBox hbox = new HBox(50);
		hbox.getChildren().addAll(configureLeftPanel(), separator, configureRightPanel());
		this.getChildren().addAll(hbox);
//		printElements(resCircles, reacCircles, intersections, labels);
		agregar.setOnAction(clickButton);
	}
	EventHandler<ActionEvent> clickButton = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			double xl = Double.parseDouble(tfxl.getText());
			double rl = Double.parseDouble(tfrl.getText());
			String sgn = (xl > 0) ? "+" : "";
			taOp.appendText("Zl = " + rl + sgn + xl + "j\n");
			
			drawSmithCircle(rl, xl, Color.RED, Color.RED, Color.RED);
			int last = intersections.size() - 1;
			Circle temp = intersections.get(last);
			Circle res = resCircles.get(resCircles.size()-1);
			Arc rea= reacCircles.get(reacCircles.size()-1);
			
			Label lb = labels.get(last);
			temp.setOnMouseEntered(mouseEntered);
			temp.setOnMouseExited(mouseExited);
			lb.setOnMouseEntered(mouseEntered);
			lb.setOnMouseExited(mouseExited);
			lb.setTextFill(Color.CRIMSON);
			res.setStroke(Color.CRIMSON);
			rea.setStroke(Color.CRIMSON);
			
			chart.getChildren().addAll(temp, lb, rea, res);
		}
	};

	private Node configureRightPanel() {
		VBox vbox = new VBox(10);
		GridPane grid = new GridPane();

		taOp.setPrefHeight(340);
		taOp.setPrefWidth(320);
		taOp.setWrapText(true);
		taOp.setEditable(false);
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(20);
		grid.setHgap(5);
		text.setFont(FONT4);
		lbz0.setFont(FONT3);
		tfz0.setFont(FONT3);
		rl.setFont(FONT3);
		tfrl.setFont(FONT3);
		tfxl.setFont(FONT3);
		xl.setFont(FONT3);
		rl.setFont(FONT3);
		agregar.setFont(FONT3);
		tfz0.setPromptText("Impedancia característica");
		tfz0.setPrefColumnCount(12);
		tfrl.setPromptText("Resistencia");
		tfxl.setPromptText("Reactancia");

		GridPane.setConstraints(lbz0, 0, 0);
		GridPane.setConstraints(tfz0, 1, 0);
		GridPane.setConstraints(rl, 0, 1);
		GridPane.setConstraints(tfrl, 1, 1);
		GridPane.setConstraints(xl, 0, 2);
		GridPane.setConstraints(tfxl, 1, 2);
		GridPane.setConstraints(agregar, 2, 2);
//		grid.getChildren().add(lbz0);
//		grid.getChildren().add(tfz0);
		grid.getChildren().add(rl);
		grid.getChildren().add(tfrl);
		grid.getChildren().add(xl);
		grid.getChildren().add(tfxl);
		grid.getChildren().add(agregar);

		vbox.getChildren().addAll(text, grid, taOp);
		return vbox;
	}

	private Node configureLeftPanel() {
		Dial mainDial = new Dial(radio + radio * 0.05, false, 12, 60, Color.RED, false);
		Dial dial2 = new Dial(radio + radio * 0.15, false, 12, 60, Color.RED, false);
		Circle degrees = new Circle(origenX, origenY, radio + radio * 0.05, Color.TRANSPARENT);
		Circle separator = new Circle(origenX, origenY, radio + radio * 0.1, Color.TRANSPARENT);
		Circle lambda = new Circle(origenX, origenY, radio + radio * 0.15, Color.TRANSPARENT);
		Circle stroke = new Circle(origenX, origenY, radio + radio * 0.2, Color.TRANSPARENT);
		
		degrees.setStroke(Color.BLACK);
		degrees.setStrokeWidth(1);
		separator.setStroke(Color.BLACK);
		separator.setStrokeWidth(1);
		lambda.setStroke(Color.BLACK);
		lambda.setStrokeWidth(1);
		stroke.setStroke(Color.BLACK);
		stroke.setStrokeWidth(1);
		
		VBox vbox = new VBox(10);
		Group group = new Group();
		Text text = new Text("La carta de Smith");

		text.setFill(Color.BLACK);
		text.setFont(FONT);
		text.setTranslateX(80);
		
//		drawSmithCircle(0.05, -5, Color.RED, Color.RED, Color.RED);

		plotSmithChart(1, 2);
		plotSmithChart(-1, 2);
		removeEquals();
		chart = drawSmithChart();
		chart.getChildren().addAll(degrees, mainDial, dial2, separator, lambda, stroke);
		addIntersections();
		vbox.getChildren().addAll(text, chart);
		group.getChildren().add(vbox);
		return group;
	}

	private void plotSmithChart(int sgn, int k) {
		for (int i = 0; i < 10; i+=k) {
			for (int j = 0; j < 10; j+=k) {
				drawSmithCircle(j * 0.1, sgn*(i * 0.1), Color.DARKGRAY, Color.DARKGRAY, Color.DARKGRAY);
			}
		}
		for (int i = 0; i < 10; i+=k) {
			for (int j = 0; j < 10; j+=k) {
				drawSmithCircle(j * 0.1, sgn*(1 + i * 0.1), Color.DARKGRAY, Color.DARKGRAY, Color.DARKGRAY);
			}
		}
		for (int i = 3; i < 6; i++) {
			for (int j = 0; j < 10; j+=k) {
				drawSmithCircle(j * 0.1, sgn*(i), Color.DARKGRAY, Color.DARKGRAY, Color.DARKGRAY);
			}
		}
		for (int i = 10; i < 60; i+=10) {
			for (int j = 0; j < 10; j+=k) {
				drawSmithCircle(j * 0.1, sgn*(i), Color.DARKGRAY, Color.DARKGRAY, Color.DARKGRAY);
			}
		}
		for (int i = 10; i < 60; i+=10) {
			for (int j = 0; j < 10; j+=k) {
				drawSmithCircle(1 + j * 0.1, sgn*(i), Color.DARKGRAY, Color.DARKGRAY, Color.DARKGRAY);
			}
		}
		for (int i = 3; i < 6; i++) {
			for (int j = 0; j < 10; j+=k) {
				drawSmithCircle(1 + j * 0.1, sgn*(i), Color.DARKGRAY, Color.DARKGRAY, Color.DARKGRAY);
			}
		}
		for (int i = 0; i < 10; i+=k) {
			for (int j = 0; j < 10; j+=k) {
				drawSmithCircle(1 + j * 0.1, sgn*(i * 0.1), Color.DARKGRAY, Color.DARKGRAY, Color.DARKGRAY);
			}
		}
		for (int i = 0; i < 10; i+=k) {
			for (int j = 0; j < 10; j+=k) {
				drawSmithCircle(1 + j * 0.1, sgn*(1 + i * 0.1), Color.DARKGRAY, Color.DARKGRAY, Color.DARKGRAY);
			}
		}
		for (int i = 0; i < 10; i+=k) {
			for (int j = 3; j < 6; j++) {
				drawSmithCircle(j, sgn*(i * 0.1), Color.DARKGRAY, Color.DARKGRAY, Color.DARKGRAY);
			}
		}
		for (int i = 0; i < 10; i+=k) {
			for (int j = 3; j < 6; j++) {
				drawSmithCircle(j, sgn*(1 + i * 0.1), Color.DARKGRAY, Color.DARKGRAY, Color.DARKGRAY);
			}
		}
		for (int i = 3; i < 6; i++) {
			for (int j = 3; j < 6; j++) {
				drawSmithCircle(j, sgn*(i), Color.DARKGRAY, Color.DARKGRAY, Color.DARKGRAY);
			}
		}
		for (int i = 10; i < 60; i+=10) {
			for (int j = 3; j < 6; j++) {
				drawSmithCircle(j, sgn*(i), Color.DARKGRAY, Color.DARKGRAY, Color.DARKGRAY);
			}
		}
	}

	private void addIntersections() {
		for (Circle obj : this.intersections) {
			Circle cross = new Circle();
			cross.setFill(obj.getFill());
			cross.setOnMouseEntered(mouseEntered);
			cross.setOnMouseExited(mouseExited);
			cross.setCenterX(obj.getCenterX());
			cross.setCenterY(obj.getCenterY());
			cross.setRadius(obj.getRadius());
			chart.getChildren().add(cross);
		}

		for (Label obj : this.labels) {
			chart.getChildren().add(obj);
		}
	}

	private void removeEquals() {
		ArrayList<Circle> cRep = new ArrayList<>();
		ArrayList<Circle> pRep = new ArrayList<>();
		ArrayList<Arc> aRep = new ArrayList<>();

		for (int i = 0; i < this.resCircles.size(); i++) {
			for (int j = i + 1; j < this.resCircles.size(); j++) {
				if (resCircles.get(i).getRadius() == resCircles.get(j).getRadius()) {
					cRep.add(resCircles.get(j));
				}
			}
		}
		for (int i = 0; i < this.reacCircles.size(); i++) {
			for (int j = i + 1; j < this.reacCircles.size(); j++) {
				if (reacCircles.get(i).getCenterX() == reacCircles.get(j).getCenterX() && reacCircles.get(i).getCenterY() == reacCircles.get(j).getCenterY()) {
					aRep.add(reacCircles.get(j));
				}
			}
		}
		for (int i = 0; i < this.intersections.size(); i++) {
			for (int j = i + 1; j < this.intersections.size(); j++) {
				if (intersections.get(i).getCenterX() == intersections.get(j).getCenterX() && intersections.get(i).getCenterY() == intersections.get(j).getCenterY()) {
					pRep.add(intersections.get(j));
				}
			}
		}
		for (Object elements : cRep) {
			this.resCircles.remove(elements);
		}
		for (Object elements : aRep) {
			this.reacCircles.remove(elements);
		}
		for (Object elements : pRep) {
			this.intersections.remove(elements);
		}
	}

	EventHandler<MouseEvent> mouseEntered = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent me) {
			Circle test = (Circle) me.getSource();
			for (Label o : labels) {
				if (o.getLayoutX() == test.getCenterX() + radio * 0.05 && 
					o.getLayoutY() == test.getCenterY()) {
					o.setVisible(true);
				}
			}
			System.out.println("mouse entered!" + test.getRadius());
		}
	};

	EventHandler<MouseEvent> mouseExited = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent me) {
			Circle test = (Circle) me.getSource();
			for (Label o : labels) {
				if (o.getLayoutX() == test.getCenterX() + radio * 0.05) {
					o.setVisible(false);
				}
			}
			System.out.println("mouse exited!");
		}
	};

	private Group drawSmithChart() {
		Group group = new Group();
		for (Circle obj : this.resCircles) {
			group.getChildren().add(obj);
		}
		for (Arc obj : this.reacCircles) {
			group.getChildren().add(obj);
		}
		return group;
	}

	@SuppressWarnings("unused")
	private void printElements(List<Circle> res, List<Arc> react, List<Circle> inter, List<Label> lab) {
		System.out.println("size: " + res.size());
		for (Object o : res) {
			System.out.println(o.toString());
		}
		System.out.println("size: " + react.size());
		for (Object o : react) {
			System.out.println(o.toString());
		}
		System.out.println("size: " + inter.size());
		for (Object o : inter) {
			System.out.println(o.toString());
		}
		System.out.println("size: " + lab.size());
		for (Object o : lab) {
			System.out.println(o.toString());
		}
		System.out.println("...");
	}

	private void drawSmithCircle(double rl, double xl, Color resColor, Color reactColor, Color crossCol) {

		drawResistance(rl, resColor);
		if (xl > 0) {
			drawPositiveReactance(rl, xl, reactColor, crossCol);
		} else if (xl < 0) {
			drawNegativeReactance(rl, xl, reactColor, crossCol);
		}
	}

	private void drawResistance(double rl, Color resColor) {
		double xc = rl / (1 + rl);
		double yc = 0;
		double ratio = 1 / (1 + rl);

		Circle res = new Circle(origenX + xc * radio, origenY + yc, ratio * radio, Color.TRANSPARENT);
		res.setStroke(resColor);
		res.setStrokeWidth(1);
		this.resCircles.add(res);
	}

	@SuppressWarnings("unused")
	private Circle drawCircleResistence(double rl, Color color) {
		double xc = rl / (1 + rl);
		double yc = 0;
		double ratio = 1 / (1 + rl);
	
		Circle c1 = new Circle(origenX + xc * radio, origenY + yc, ratio * radio, Color.TRANSPARENT);
		c1.setStroke(color);
		c1.setStrokeWidth(1);
		return c1;
	}

	private void drawPositiveReactance(double rl, double xl, Color reactColor, Color crossCol) {
		double x = 1;
		double y;
		double yp = 2 * xl / (1 + xl * xl);
		double xp = 1 - yp / xl;
		double bpangle;
		double ratio = 1 / xl;
		y = 1 / xl;
	
		double xi = (-2 * (1 + rl) / (Math.pow(1 + rl, 2) + Math.pow(xl, 2))) + 1;
		double yi = xl * (xi - 1) / (1 + rl);
	
		Circle positiveReactance = new Circle(origenX + x * radio, origenY - y * radio, ratio * radio, Color.TRANSPARENT);
		Circle bluepoint = new Circle(origenX + xp * radio, origenY - yp * radio, 4, Color.BLUE);
		Circle cross = new Circle(origenX + xi * radio, origenY + yi * radio, 2, crossCol);
		rl = rl * 100;
		rl = (int) rl;
		rl = rl / 100;
		xl = xl * 100;
		xl = (int) xl;
		xl = xl / 100;
		Label label = new Label("(" + rl + ", " + xl + ")");
		label.setLayoutX(origenX + xi * radio + radio * 0.05);
		label.setLayoutY(origenY + yi * radio);
		label.setVisible(false);
		label.setTextFill(Color.BLACK);
		label.setFont(FONT2);
		this.intersections.add(cross);
		this.labels.add(label);
	
		double bpx = Math.acos((bluepoint.getCenterX() - positiveReactance.getCenterX()) / positiveReactance.getRadius());
		bpangle = (xl > 1) ? (180 * (bpx)) / Math.PI : 360 - (180 * (bpx)) / Math.PI;
		double lenght = 270 - bpangle;
	
		Arc react = new Arc();
		react.setCenterX(positiveReactance.getCenterX());
		react.setCenterY(positiveReactance.getCenterY());
		react.setRadiusX(positiveReactance.getRadius());
		react.setRadiusY(positiveReactance.getRadius());
		react.setStartAngle(bpangle);
		react.setLength(lenght);
		react.setFill(Color.TRANSPARENT);
		react.setStroke(reactColor);
		react.setType(ArcType.OPEN);
	
		this.reacCircles.add(react);
	}

	private void drawNegativeReactance(double rl, double xl, Color reactColor, Color crossCol) {
		double x = 1;
		double y;
		double ratio;
		double yp = 2 * xl / (1 + xl * xl);
		double xp = 1 - yp / xl;

		y = 1 / -xl;
		ratio = 1 / -xl;
		
		double xi = (-2 * (1 + rl) / (Math.pow(1 + rl, 2) + Math.pow(xl, 2))) + 1;
		double yi = xl * (xi - 1) / (1 + rl);

		Circle negativeReactance = new Circle(origenX + x * radio, origenY + y * radio, ratio * radio, Color.TRANSPARENT);
		Circle cross = new Circle(origenX + xi * radio, origenY + yi * radio, 2, crossCol);
		Circle greenpoint = new Circle(origenX + radio, origenY, 4, Color.GREEN);
		Circle redpoint = new Circle(origenX + xp * radio, origenY - yp * radio, 4, Color.RED);
		negativeReactance.setStroke(Color.BLACK);
		negativeReactance.setStrokeWidth(2);
		
		rl = rl * 100;
		rl = (int) rl;
		rl = rl / 100;
		xl = xl * 100;
		xl = (int) xl;
		xl = xl / 100;
		Label label = new Label("(" + rl + ", " + xl + ")");
		label.setLayoutX(origenX + xi * radio + radio * 0.05);
		label.setLayoutY(origenY + yi * radio);
		label.setVisible(false);
		label.setTextFill(Color.BLACK);
		label.setFont(FONT2);
		this.intersections.add(cross);
		this.labels.add(label);

		double gpxDown = (greenpoint.getCenterX() - negativeReactance.getCenterX()) / radio;
		double rpx = Math.acos((redpoint.getCenterX() - negativeReactance.getCenterX()) / negativeReactance.getRadius());

		double gpangleDown = (180 * Math.acos(gpxDown)) / Math.PI;
		double rpangle = (Math.abs(xl) > 1) ? 360 - (180 * (rpx)) / Math.PI : (180 * (rpx)) / Math.PI;
		double lenght = rpangle - gpangleDown;

		Arc arc = new Arc();
		arc.setCenterX(negativeReactance.getCenterX());
		arc.setCenterY(negativeReactance.getCenterY());
		arc.setRadiusX(negativeReactance.getRadius());
		arc.setRadiusY(negativeReactance.getRadius());
		arc.setStartAngle(gpangleDown);
		arc.setLength(lenght);
		arc.setFill(Color.TRANSPARENT);
		arc.setStroke(reactColor);
		arc.setType(ArcType.OPEN);
		this.reacCircles.add(arc);
	}

}
