package com.willy.smith;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * 
 * @author Wilson Benavides - benavidesb@unicauca.edu.co
 * @version 0.0
 *
 */


public class SmithChart extends Parent {
	
	private static final double RADIO = 120 * 2;
	private static final double origenX = Main.getWidth()/2;
	private static final double origenY= Main.getHeight()/2;
	
	public SmithChart() {
		Circle cPrincipal = drawCircleResistence(0, Color.DARKGREEN);
		this.getChildren().addAll(cPrincipal);	
		
		Group test = drawArcReactance(0.5, Color.MEDIUMPURPLE);
		this.getChildren().addAll(test);
	}

	/**
	 * 
	 * @param xl It represents normalized load reactance
	 * @param color reactance circle color 
	 * @return Arc on screen with reactance xl and color
	 */
	private Group drawArcReactance(double xl, Color color) {
Group main = new Group();
		
		double x = 1;
		double y;
		double ratio;
		double yp = 2*xl/(1+xl*xl);
		double xp = 1-yp/xl;
		
		y = 1/xl;
		ratio = 1/xl;
		
		Circle reactanceDown = new Circle(origenX + x*RADIO, origenY + y*RADIO, ratio*RADIO, Color.TRANSPARENT);
		reactanceDown.setStroke(Color.BLACK);
		reactanceDown.setStrokeWidth(2);

		Circle reactanceUp = new Circle(origenX + x*RADIO, origenY - y*RADIO, ratio*RADIO, Color.TRANSPARENT);
		reactanceUp.setStroke(Color.BLACK);
		reactanceUp.setStrokeWidth(2);
		
		Circle greenpoint = new Circle(origenX + RADIO, origenY, 4, Color.GREEN);
		Circle redpoint = new Circle(origenX+xp*RADIO, origenY + yp*RADIO, 4, Color.RED);
		Circle bluepoint = new Circle(origenX+xp*RADIO, origenY - yp*RADIO, 4, Color.BLUE);
		Circle centerpoint = new Circle(reactanceDown.getCenterX(), reactanceDown.getCenterY(), 4, Color.AQUA);
		
		Line line = new Line(redpoint.getCenterX(), redpoint.getCenterY(), greenpoint.getCenterX(), greenpoint.getCenterY());
		line.setStroke(Color.BLUE);
		line.setStrokeWidth(2);
		
		Line line2 = new Line(bluepoint.getCenterX(), bluepoint.getCenterY(), greenpoint.getCenterX(), greenpoint.getCenterY());
		line2.setStroke(Color.AQUAMARINE);
		line2.setStrokeWidth(2);
		
		double gpxDown = (greenpoint.getCenterX()-reactanceDown.getCenterX())/RADIO;
		double gpxUp = (greenpoint.getCenterX()-reactanceUp.getCenterX())/RADIO;
		double rpx= Math.acos((redpoint.getCenterX() - reactanceDown.getCenterX())/reactanceDown.getRadius());
		double bpx= Math.acos((bluepoint.getCenterX() - reactanceUp.getCenterX())/reactanceUp.getRadius());

		double rpangle, bpangle;
		
		double gpangleDown = (180*Math.acos(gpxDown))/Math.PI;
		double gpangleUp = (180*Math.acos(gpxUp))/Math.PI;
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
		
		main.getChildren().addAll(arc, bluepoint, arc2, reactanceDown, reactanceUp, line, centerpoint, greenpoint, redpoint, line2);
		return main;
	}

	/**
	 * 
	 * @param rl It represents normalized load resistance
	 * @param color resistance circle color 
	 * @return Circle on screen with resistance rl and color
	 */
	private Circle drawCircleResistence(double rl, Color color) {
		
		double xc = rl/(1 + rl);
		double yc = 0;
		double ratio = 1/(1 + rl);
		
		Circle c1 = new Circle(origenX + xc*RADIO, origenY + yc, ratio*RADIO, Color.TRANSPARENT);
		c1.setStroke(color);
		c1.setStrokeWidth(1);
		return c1;
	}
}
