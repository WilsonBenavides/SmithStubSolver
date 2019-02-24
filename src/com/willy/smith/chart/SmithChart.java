package com.willy.smith.chart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SmithChart extends Parent {
	
	private static final double radio = 120 * 1.5;
	private static final double origenX = 0;
	private static final double origenY = 0;
//	private static final double origenX = Main.getWidth()/2 + radio;
//	private static final double origenY = Main.getHeight()/2;
	private static final Font FONT = Font.font("Comic Sans", FontWeight.BOLD, 30);
	private Dial mainDial;
	
	private ArrayList<Circle> resCircles = new ArrayList<>();
	private ArrayList<Object> test = new ArrayList<>();
	private List<Arc> reacCircles = new ArrayList<Arc>();
	private List<Circle> intersections = new ArrayList<Circle>();

	public SmithChart() {
		VBox vbox = new VBox(5);
		Group group = new Group();
		
		Text text = new Text("La Carta de Smith");
		text.setFill(Color.BLACK);
		text.setFont(FONT);
		text.setTranslateX(80);
		
		ArrayList<String> list = new ArrayList<>();
		list.add("dog");
		list.add("cat");
		list.add("dog");
		list.add("dog");
		list.add("cat");
		list.add("bird");
		
		HashSet<String> hs = new HashSet<String>();
		hs.addAll(list);
		
//		for (String elements : list) {
////			System.out.println(elements);
//		}
		
		list.clear();
		list.addAll(hs);
//		System.out.println("............");
//		for (String e : list) {
////			System.out.println(e);
//		}
		
		Circle cPrincipal = drawCircleResistence(0, Color.BLACK);
//		Group test = drawArcReactance(0.9, Color.BLACK);
		drawSmithCircle(1, 1, Color.BLACK, Color.BLACK);
		drawSmithCircle(1, 1.2, Color.BLACK, Color.RED);
		drawSmithCircle(1, 1.4, Color.BLACK, Color.BLACK);
		drawSmithCircle(1, 1.6, Color.BLACK, Color.RED);
		drawSmithCircle(1, 1.8, Color.BLACK, Color.BLACK);
		drawSmithCircle(2, 2.0, Color.BLACK, Color.RED);
		drawSmithCircle(1, 2.5, Color.BLACK, Color.BLACK);
//		Group smith8 = drawSmithCircle(1, 3, Color.BLACK, Color.BLACK);
//		Group smith9 = drawSmithCircle(1, 4, Color.BLACK, Color.BLACK);
//		Group smith10 = drawSmithCircle(1, 5, Color.BLACK, Color.BLACK);
//		Group smith11 = drawSmithCircle(1, 10, Color.BLACK, Color.BLACK);
//		Group smith12 = drawSmithCircle(1, 20, Color.BLACK, Color.BLACK);
//		Group smith13 = drawSmithCircle(1, 50, Color.BLACK, Color.BLACK);
//		Group smith14 = drawSmithCircle(1, 0.1, Color.BLACK, Color.BLACK);
//		Group smith15 = drawSmithCircle(1, 0.2, Color.BLACK, Color.RED);
//		Group smith16 = drawSmithCircle(1, 0.3, Color.BLACK, Color.BLACK);
//		Group smith17 = drawSmithCircle(1, 0.4, Color.BLACK, Color.BLACK);
//		Group smith18 = drawSmithCircle(1, 0.5, Color.BLACK, Color.BLACK);
//		Group smith19 = drawSmithCircle(1, 0.6, Color.BLACK, Color.RED);
//		Group smith20 = drawSmithCircle(1, 0.7, Color.BLACK, Color.BLACK);
//		Group smith21 = drawSmithCircle(1, 0.8, Color.BLACK, Color.BLACK);
//		Group smith22 = drawSmithCircle(1, 0.9, Color.BLACK, Color.BLACK);
		
		mainDial = new Dial(radio - radio*0.032, true, 12, 60, Color.RED, false);
		
		ArrayList<Circle> rep = new ArrayList<>();
		
		for (int i = 0; i < this.resCircles.size(); i++) {
			for(int j = i + 1; j < this.resCircles.size(); j++) {
//				System.out.println("---------" + j);
				if (resCircles.get(i).getRadius() ==  resCircles.get(j).getRadius()) {
//					System.out.println("elementos iguales..!!!!" + j);
					rep.add(resCircles.get(j));
//					resCircles.remove(j);
				}
			}
		}
		
		HashSet<Object> hsequals = new HashSet<Object>();
		hsequals.addAll(this.test);
		
		for (Object elements : rep) {
			System.out.println(elements);
			test.remove(elements);
		}
		System.out.println("............");
		
//		this.test.clear();
//		test.addAll(hsequals);
		for (Object e : test) {
			System.out.println("test: " + e);
		}
		
		for (int i = 0; i < rep.size(); i++) {
//			int aa = rep.get(i);
//			System.out.println("________________" + aa + ", " + resCircles.size());
//			resCircles.remove(aa);
		}
		
//		int aa = rep.get(0);
//		resCircles.remove(aa);
		Group chart = drawSmithChart();
		group.getChildren().addAll(cPrincipal, mainDial, chart);
		vbox.getChildren().addAll(text, group);
		this.getChildren().addAll(vbox);

		
		printElements(test, reacCircles, intersections);
//		printElements(reacCircles);
	}

	private Group drawSmithChart() {
		Group group = new Group();
		for (Object obj : test) {
			group.getChildren().add((Circle) obj);
		}
		for (Arc obj : this.reacCircles) {
			group.getChildren().add(obj);
		}
		for (Circle obj : this.intersections) {
			group.getChildren().add(obj);
		}
		return group;
	}

	private void printElements(List<Object> res, List<Arc> react, List<Circle> inter) {
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
		System.out.println("...");
	}

	boolean flag1 = false, flag2 = false;
	private void drawSmithCircle(double rl, double xl, Color resColor, Color reactColor) {
		Group main = new Group();
		
		double xc = rl/(1 + rl);
		double yc = 0;
		double ratio = 1/(1 + rl);
		
		Circle res = new Circle(origenX + xc*radio, origenY + yc, ratio*radio, Color.TRANSPARENT);
		res.setStroke(resColor);
		res.setStrokeWidth(1);
		this.resCircles.add(res);
		this.test.add(res);
		
//		System.out.println(".......");
//		for (Iterator<Circle> iterator = this.resCircles.iterator(); iterator.hasNext(); ) {
//			Circle value = iterator.next();
//			if(value.getCenterX() != res.getCenterX()) {
//				System.out.println("diferente " + value.getCenterX() + ", " + res.getCenterX());
//				flag1 = true;
//			}
//		}
//		
//		if (flag1) {
//			System.out.println("resistencia agregada..");
//			this.resCircles.add(res);
//		}
		
		
		double x = 1;
		double y;
		double yp = 2*xl/(1+xl*xl);
		double xp = 1-yp/xl;
		double bpangle;
		y = 1/xl;
		ratio = 1/xl;
		
		double xi = (-2*(1+rl)/(Math.pow(1+rl,2)+Math.pow(xl, 2))) + 1;
		double yi = xl*(xi-1)/(1+rl);
		
		Circle positiveReactance = new Circle(origenX + x*radio, origenY - y*radio, ratio*radio, Color.TRANSPARENT);
		Circle bluepoint = new Circle(origenX+xp*radio, origenY - yp*radio, 4, Color.BLUE);
		Circle cross = new Circle(origenX + xi*radio, origenY + yi*radio, 2, Color.CHARTREUSE);
		this.intersections.add(cross);
		
		double bpx= Math.acos((bluepoint.getCenterX() - positiveReactance.getCenterX())/positiveReactance.getRadius());
		bpangle = (xl > 1) ? (180*(bpx))/Math.PI : 360 - (180*(bpx))/Math.PI;
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
		
//		this.reacCircles.add(react);
		
		if (this.reacCircles.size() < 1) {
			this.reacCircles.add(react);
		} 
		
		for (Iterator<Arc> iterator = this.reacCircles.iterator(); iterator.hasNext(); ) {
			Arc value = iterator.next();
			if(!(value.getCenterX() == react.getCenterX() && value.getCenterY() == react.getCenterY() && value.getRadiusX() == react.getRadiusX() && value.getRadiusY() == react.getRadiusY())) {
				flag2 = true;
			}
		}
		
		if (flag2) {
			this.reacCircles.add(react);
		}
		
		main.getChildren().addAll();
//		return main;
	}

	private Group drawArcReactance(double xl, Color color) {
		Group main = new Group();
		
		double x = 1;
		double y;
		double ratio;
		double yp = 2*xl/(1+xl*xl);
		double xp = 1-yp/xl;
		
		y = 1/xl;
		ratio = 1/xl;
		
		Circle reactanceDown = new Circle(origenX + x*radio, origenY + y*radio, ratio*radio, Color.TRANSPARENT);
		reactanceDown.setStroke(Color.BLACK);
		reactanceDown.setStrokeWidth(2);

		Circle reactanceUp = new Circle(origenX + x*radio, origenY - y*radio, ratio*radio, Color.TRANSPARENT);
		reactanceUp.setStroke(Color.BLACK);
		reactanceUp.setStrokeWidth(2);
		
		Circle greenpoint = new Circle(origenX + radio, origenY, 4, Color.GREEN);
		Circle redpoint = new Circle(origenX+xp*radio, origenY + yp*radio, 4, Color.RED);
		Circle bluepoint = new Circle(origenX+xp*radio, origenY - yp*radio, 4, Color.BLUE);
//		Circle centerpoint = new Circle(reactanceDown.getCenterX(), reactanceDown.getCenterY(), 4, Color.AQUA);
		
		Line line = new Line(redpoint.getCenterX(), redpoint.getCenterY(), greenpoint.getCenterX(), greenpoint.getCenterY());
		line.setStroke(Color.BLUE);
		line.setStrokeWidth(2);
		
		double gpxDown = (greenpoint.getCenterX()-reactanceDown.getCenterX())/radio;
		double rpx= Math.acos((redpoint.getCenterX() - reactanceDown.getCenterX())/reactanceDown.getRadius());
		double bpx= Math.acos((bluepoint.getCenterX() - reactanceUp.getCenterX())/reactanceUp.getRadius());

		double rpangle, bpangle;
		
		double gpangleDown = (180*Math.acos(gpxDown))/Math.PI;
		rpangle = (xl > 1) ? 360 - (180*(rpx))/Math.PI : (180*(rpx))/Math.PI;
		bpangle = (xl > 1) ? (180*(bpx))/Math.PI : 360 - (180*(bpx))/Math.PI;
		double lenght = rpangle - gpangleDown;
		
		Arc arc = new Arc();
		arc.setCenterX(reactanceDown.getCenterX());
		arc.setCenterY(reactanceDown.getCenterY());
		arc.setRadiusX(reactanceDown.getRadius());
		arc.setRadiusY(reactanceDown.getRadius());
		arc.setStartAngle(gpangleDown);
		arc.setLength(lenght);
		arc.setFill(Color.TRANSPARENT);
		arc.setStroke(color);
		arc.setType(ArcType.OPEN);

		lenght = 270 - bpangle;
		Arc arc2 = new Arc();
		arc2.setCenterX(reactanceUp.getCenterX());
		arc2.setCenterY(reactanceUp.getCenterY());
		arc2.setRadiusX(reactanceUp.getRadius());
		arc2.setRadiusY(reactanceUp.getRadius());
		arc2.setStartAngle(bpangle);
		arc2.setLength(lenght);
		arc2.setFill(Color.TRANSPARENT);
		arc2.setStroke(color);
		arc2.setType(ArcType.OPEN);
		
		main.getChildren().addAll(arc, bluepoint, arc2);
		return main;
	}

	private Circle drawCircleResistence(double rl, Color color) {
		double xc = rl/(1 + rl);
		double yc = 0;
		double ratio = 1/(1 + rl);
		
		Circle c1 = new Circle(origenX + xc*radio, origenY + yc, ratio*radio, Color.TRANSPARENT);
		c1.setStroke(color);
		c1.setStrokeWidth(1);
		return c1;
	}
}
