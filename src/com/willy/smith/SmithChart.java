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
	private static final double originX = Main.getWidth() / 2;
	private static final double originY = Main.getHeight() / 2;

	public SmithChart() {
		Circle cPrincipal = drawCircleResistence(0, Color.DARKGREEN);
		this.getChildren().addAll(cPrincipal);

		Group test = drawArcReactance(0.5, Color.MEDIUMPURPLE);
		this.getChildren().addAll(test);
	}

	/**
	 * 
	 * @param xl    It represents normalized load reactance
	 * @param color reactance circle color
	 * @return Arc on screen with reactance xl and color
	 */
	private Group drawArcReactance(double xl, Color color) {
		Group main = new Group();

		double x = 1;
		double y;
		double ratio;
		double yp = 2 * xl / (1 + xl * xl);
		double xp = 1 - yp / xl;

		y = 1 / xl;
		ratio = 1 / xl;

		Circle reactanceCircleDown = new Circle(originX + x * RADIO, originY + y * RADIO, ratio * RADIO, Color.TRANSPARENT);
		reactanceCircleDown.setStroke(Color.BLACK);
		reactanceCircleDown.setStrokeWidth(2);

		Circle reactanceCircleUp = new Circle(originX + x * RADIO, originY - y * RADIO, ratio * RADIO, Color.TRANSPARENT);
		reactanceCircleUp.setStroke(Color.BLACK);
		reactanceCircleUp.setStrokeWidth(2);

		Circle referencePoint = new Circle(originX + RADIO, originY, 4, Color.GREEN);
		Circle crossUnitResisReactUp = new Circle(originX + xp * RADIO, originY - yp * RADIO, 4, Color.BLUE);
		Circle crossUnitResisReactDown = new Circle(originX + xp * RADIO, originY + yp * RADIO, 4, Color.RED);
//		Circle centerpoint = new Circle(reactanceCircleDown.getCenterX(), reactanceCircleDown.getCenterY(), 4, Color.AQUA);

		Line line = new Line(crossUnitResisReactDown.getCenterX(), crossUnitResisReactDown.getCenterY(), referencePoint.getCenterX(), referencePoint.getCenterY());
		line.setStroke(Color.BLUE);
		line.setStrokeWidth(2);

		Line line2 = new Line(crossUnitResisReactUp.getCenterX(), crossUnitResisReactUp.getCenterY(), referencePoint.getCenterX(), referencePoint.getCenterY());
		line2.setStroke(Color.AQUAMARINE);
		line2.setStrokeWidth(2);

		double gpxDown = (referencePoint.getCenterX() - reactanceCircleDown.getCenterX()) / RADIO;
//		double gpxUp = (greenpoint.getCenterX() - reactanceCircleUp.getCenterX()) / RADIO;
		double rpx = Math.acos((crossUnitResisReactDown.getCenterX() - reactanceCircleDown.getCenterX()) / reactanceCircleDown.getRadius());
		double bpx = Math.acos((crossUnitResisReactUp.getCenterX() - reactanceCircleUp.getCenterX()) / reactanceCircleUp.getRadius());

		double rpangle, bpangle;

		double gpangle = (180 * Math.acos(gpxDown)) / Math.PI;
//		double gpangleUp = (180 * Math.acos(gpxUp)) / Math.PI;
		rpangle = (xl > 1) ? 360 - (180 * (rpx)) / Math.PI : (180 * (rpx)) / Math.PI;
		bpangle = (xl > 1) ? (180 * (bpx)) / Math.PI : 360 - (180 * (bpx)) / Math.PI;
		double lenght = rpangle - gpangle;

		Arc positiveReactArc = new Arc();
		positiveReactArc.setCenterX(reactanceCircleDown.getCenterX());
		positiveReactArc.setCenterY(reactanceCircleDown.getCenterY());
		positiveReactArc.setRadiusX(reactanceCircleDown.getRadius());
		positiveReactArc.setRadiusY(reactanceCircleDown.getRadius());
		positiveReactArc.setStartAngle(gpangle);
		positiveReactArc.setLength(lenght);
		positiveReactArc.setFill(Color.TRANSPARENT);
		positiveReactArc.setStroke(color);
		positiveReactArc.setType(ArcType.OPEN);

		lenght = 270 - bpangle;
		Arc negativeReactArc = new Arc();
		negativeReactArc.setCenterX(reactanceCircleUp.getCenterX());
		negativeReactArc.setCenterY(reactanceCircleUp.getCenterY());
		negativeReactArc.setRadiusX(reactanceCircleUp.getRadius());
		negativeReactArc.setRadiusY(reactanceCircleUp.getRadius());
		negativeReactArc.setStartAngle(bpangle);
		negativeReactArc.setLength(lenght);
		negativeReactArc.setFill(Color.TRANSPARENT);
		negativeReactArc.setStroke(color);
		negativeReactArc.setType(ArcType.OPEN);

		main.getChildren().addAll(positiveReactArc, negativeReactArc, referencePoint, crossUnitResisReactUp, crossUnitResisReactDown);
		return main;
	}

	/**
	 * 
	 * @param rl    It represents normalized load resistance
	 * @param color resistance circle color
	 * @return Circle on screen with resistance rl and color
	 */
	private Circle drawCircleResistence(double rl, Color color) {

		double xc = rl / (1 + rl);
		double yc = 0;
		double ratio = 1 / (1 + rl);

		Circle c1 = new Circle(originX + xc * RADIO, originY + yc, ratio * RADIO, Color.TRANSPARENT);
		c1.setStroke(color);
		c1.setStrokeWidth(1);
		return c1;
	}
}
