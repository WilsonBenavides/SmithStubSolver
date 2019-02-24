package com.willy.smith;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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
