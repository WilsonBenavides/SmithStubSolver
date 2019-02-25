package stub.model;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SmithCircle {

	public static final double radio = 250;
	public static final Font FONT = Font.font("Comic Sans", FontWeight.BOLD, 14);

	private double zlRes;
	private double zlReact;
	private double ylCon;
	private double ylSuscep;
	private double rlNormalized;
	private double xlNormalized;
	private double glNormalized;
	private double blNormalized;
	private double zo;
	private double angleZl;
	private double angleYl;
	private double lambdaZl;
	private double lambdaYl;
	private double ro;
	private double swr;
	
	private String type;

	private Circle resCircle;
	private Arc reactCircle;
	private Circle conCircle;
	private Arc suscepCircle;
	private Circle unit;

	private Circle reflexCoefConst;
	private Circle zlStroke;
	private Circle lambdaZStroke;
	private Circle lambdaYStroke;
	private Circle ylStroke;
	private Circle zl;
	private Circle yl;

	private Line lineReflexCoefZl;
	private Line lineReflexCoefYl;
	private Line zlCompLine;
	private Line ylCompLine;

	private Label zlMark;
	private Label ylMark;
	private Label lambdaZMark;
	private Label lambdaYMark;
	//
	private Group mainGroup;

	public SmithCircle(double res, double reac, double zo, String type, Color zlCol, Color ylCol) {
		this.setType(type);
		this.setZo(zo);
		if (type.equals("Impedance")) {
			double rl = res / zo;
			double xl = reac / zo;
			double sgn = -1 * (xl / Math.abs(xl));
			this.setZlRes(res);
			this.setZlReact(reac);

			this.setRlNormalized(rl);
			this.setXlNormalized(xl);

			this.setGlNormalized(rl / (Math.pow(rl, 2) + Math.pow(xl, 2)));
			this.setBlNormalized(sgn * (Math.abs(xl) / (Math.pow(rl, 2) + Math.pow(xl, 2))));

			this.setYlCon(rl * zo);
			this.setYlSuscep(xl * zo * sgn);
		}

		if (type.equals("Admitance")) {
			double gl = res / zo;
			double bl = reac / zo;
			double sgn = -1 * (bl / Math.abs(bl));
			this.setYlCon(res);
			this.setYlSuscep(reac);

			this.setGlNormalized(gl);
			this.setBlNormalized(bl);

			this.setRlNormalized(gl / (Math.pow(gl, 2) + Math.pow(bl, 2)));
			this.setXlNormalized(sgn * (Math.abs(bl) / (Math.pow(gl, 2) + Math.pow(bl, 2))));

			this.setZlRes(gl * zo);
			this.setZlReact(bl * zo * sgn);
		}

//		System.out.println("Tipo de stub : " + type + " rl : " + round(getRlNormalized()) + " xl : " + round(getXlNormalized()) + " gl : " + round(getGlNormalized()) + " bl : " + round(getBlNormalized()));
		this.setUnit(SmithCircle.drawResistance(1, Color.web("#f1c40f"), 2));

		drawSmithCircle(zlCol, ylCol);
	}

	public Group drawSmithCircle(Color zlCol, Color ylCol) {
		mainGroup = new Group();
		Circle center = new Circle(0, 0, 5, Color.BLACK);

		double xl = getXlNormalized();
		double rl = getRlNormalized();

		double gl = getGlNormalized();
		double bl = getBlNormalized();

		this.setResCircle(drawResistance(rl, zlCol, 1));
		this.setConCircle(drawResistance(gl, ylCol, 1));

		this.setZl(configureLoad(rl, xl, zlCol));
		this.setYl(configureLoad(gl, bl, ylCol));

		this.setReactCircle(drawReactance(rl, xl, zlCol, 1));
		this.setSuscepCircle(drawReactance(gl, bl, ylCol, 1));

		this.setZlStroke(configureLoadStroke(rl, xl, 5, zlCol));
		this.setYlStroke(configureLoadStroke(gl, bl, 5, ylCol));

		this.setAngleZl(configureAngle(zl, center));
		this.setAngleYl(configureAngle(yl, center));

		this.setLambdaZl(configureLambda(angleZl));
		this.setLambdaYl(configureLambda(angleYl));

		this.setZlMark(configureLabelMark(zl, "zl", rl, xl));
		this.setYlMark(configureLabelMark(yl, "yl", gl, bl));

		this.setReflexCoefConst(configureCoeficientReflexion(zl));

		this.setLambdaZStroke(configureStroke(angleZl, zlCol));
		this.setLambdaYStroke(configureStroke(angleYl, ylCol));

		this.setLambdaZMark(configureLambdaMark(lambdaZl, lambdaZStroke));
		this.setLambdaYMark(configureLambdaMark(lambdaYl, lambdaYStroke));

		this.setLineReflexCoefZl(configureLine(center, zl, Color.web("#2c3e50")));
		this.setLineReflexCoefYl(configureLine(center, yl, Color.web("#2c3e50")));

		this.setZlCompLine(configureLine(zl, lambdaZStroke, Color.web("#2c3e50")));
		this.setYlCompLine(configureLine(yl, lambdaYStroke, Color.web("#2c3e50")));

		// System.out.println("angle zl : " + getAngleZl() + " angle yl : " +
		// getAngleYl());
		// System.out.println("lambda zl : " + getLambdaZl() + " lambda yl : " +
		// getLambdaYl());
		return mainGroup;
	}

	private Circle configureLoadStroke(double rl, double xl, double radius, Color fill) {
		double yp = 2 * xl / (1 + Math.pow(xl, 2));
		double xp = 1 - yp / xl;

		Circle reactStroke = new Circle();
		reactStroke.setCenterX(xp * radio);
		reactStroke.setCenterY(-yp * radio);
		reactStroke.setRadius(radius);
		reactStroke.setFill(fill);
		return reactStroke;
	}

	private Arc drawReactance(double rl, double xl, Color zlCol, double strokeWidth) {
		Arc react = (xl > 0) ? configurePositiveArc(xl, zlCol, strokeWidth) : configureNegativeArc(xl, zlCol, strokeWidth);
		return react;
	}

	private Circle configureLoad(double rl, double xl, Color zlCol) {
		double xi = (-2 * (1 + rl) / (Math.pow(1 + rl, 2) + Math.pow(xl, 2))) + 1;
		double yi = xl * (xi - 1) / (1 + rl);
		Circle tmp = new Circle(xi * radio, yi * radio, 5, zlCol);
		return tmp;
	}

	private Label configureLambdaMark(double lambda, Circle c) {
		Label tmp = new Label("lambda = " + round(lambda));
		tmp.setFont(FONT);
		tmp.setLayoutX(c.getCenterX() + 5);
		tmp.setLayoutY(c.getCenterY());
		tmp.setTextFill(c.getFill());
		return tmp;
	}

	private Label configureLabelMark(Circle load, String str, double r, double x) {
		double rl = round(r);
		double xl = round(x);

		Label lb = new Label(str + " (" + rl + " , " + xl + ")");
		lb.setLayoutX(load.getCenterX() + 10);
		lb.setLayoutY(load.getCenterY());
		lb.setTextFill(load.getFill());
		// System.out.println("configure label method !!!!!" + lb.getLayoutX());
		lb.setFont(FONT);

		return lb;
	}

	public static double round(double in) {
		return Math.round(in * 1000.0) / 1000.0;
	}

	private Arc configureNegativeArc(double xl, Color reactCol, double strokeWidth) {
		double x = 1;
		double y = 1 / -xl;
		;
		double ratio = 1 / -xl;

		double yp = 2 * xl / (1 + Math.pow(xl, 2));
		double xp = 1 - yp / xl;

		Circle reactStroke = new Circle();
		reactStroke.setCenterX(xp * radio);
		reactStroke.setCenterY(-yp * radio);
		reactStroke.setRadius(4);
		reactStroke.setFill(Color.BLUE);
		// this.setZlStroke(reactStroke);

		Circle nReactCircle = new Circle();
		nReactCircle.setCenterX(x * radio);
		nReactCircle.setCenterY(y * radio);
		nReactCircle.setRadius(ratio * radio);
		nReactCircle.setFill(Color.TRANSPARENT);

		Circle greenpoint = new Circle(radio, 0, 4, Color.GREEN);

		double gpxDown = (greenpoint.getCenterX() - nReactCircle.getCenterX()) / radio;
		double rpx = Math.toDegrees(Math.acos((reactStroke.getCenterX() - nReactCircle.getCenterX()) / nReactCircle.getRadius()));

		double start = Math.toDegrees(Math.acos(gpxDown));
		double rpangle = (Math.abs(xl) > 1) ? 360 - rpx : rpx;
		double length = rpangle - start;

		Arc react = new Arc();
		react.setCenterX(nReactCircle.getCenterX());
		react.setCenterY(nReactCircle.getCenterY());
		react.setRadiusX(nReactCircle.getRadius());
		react.setRadiusY(nReactCircle.getRadius());
		react.setStartAngle(start);
		react.setLength(length);
		react.setFill(Color.TRANSPARENT);
		react.setStroke(reactCol);
		react.setStrokeWidth(strokeWidth);
		react.setType(ArcType.OPEN);
		return react;
	}

	private Arc configurePositiveArc(double xl, Color reactCol, double strokeWidth) {
		double x = 1;
		double y = 1 / xl;
		double ratio = 1 / xl;

		double yp = 2 * xl / (1 + Math.pow(xl, 2));
		double xp = 1 - yp / xl;

		Circle reactStroke = new Circle();
		reactStroke.setCenterX(xp * radio);
		reactStroke.setCenterY(-yp * radio);
		reactStroke.setRadius(4);
		reactStroke.setFill(Color.BLUE);

		Circle pReactCircle = new Circle();
		pReactCircle.setCenterX(x * radio);
		pReactCircle.setCenterY(-y * radio);
		pReactCircle.setRadius(ratio * radio);
		pReactCircle.setFill(Color.TRANSPARENT);

		double bpx = Math.acos((reactStroke.getCenterX() - pReactCircle.getCenterX()) / pReactCircle.getRadius());
		double start = (xl > 1) ? Math.toDegrees(bpx) : 360 - Math.toDegrees(bpx);
		double length = 270 - start;
		Arc react = new Arc();
		react.setCenterX(pReactCircle.getCenterX());
		react.setCenterY(pReactCircle.getCenterY());
		react.setRadiusX(pReactCircle.getRadius());
		react.setRadiusY(pReactCircle.getRadius());
		react.setStartAngle(start);
		react.setLength(length);
		react.setFill(Color.TRANSPARENT);
		react.setStroke(reactCol);
		react.setStrokeWidth(strokeWidth);
		react.setType(ArcType.OPEN);
		return react;
	}

	public static double configureBl(Circle yl) {
		double x = yl.getCenterX() / radio;
		double y = -yl.getCenterY() / radio;
		double cxl = 2 * y / (Math.pow((1 - x), 2) + Math.pow(y, 2));
		return cxl;
	}

	public static double configureGl(Circle yl) {
		double x = yl.getCenterX() / radio;
		double y = -yl.getCenterY() / radio;
		double crl = 2 * (1 - x) / (Math.pow(1 - x, 2) + Math.pow(y, 2)) - 1;
		return crl;
	}

	private Circle configureCoeficientReflexion(Circle load) {
		double tx = Math.pow((load.getCenterX()), 2);
		double ty = Math.pow((load.getCenterY()), 2);
		double r = Math.sqrt(ty + tx);
		double ro = r / SmithCircle.radio; 
		double swr = (1 + ro) / (1 - ro);
		
		this.setRo(ro);
		this.setSwr(swr);
		
		Circle reflexConst = new Circle(0, 0, r, Color.TRANSPARENT);
		reflexConst.setStroke(Color.web("#d35400"));
		reflexConst.setStrokeWidth(2);
		return reflexConst;
	}

	private Circle configureStroke(double angle, Color zlCol) {
		double xx = radio * Math.cos(Math.toRadians(angle));
		double yy = -radio * Math.sin(Math.toRadians(angle));
		Circle temp = new Circle(xx, yy, 5, zlCol);
		return temp;
	}

	private Line configureLine(Circle start, Circle end, Color color) {
		Line reflex = new Line();
		reflex.setStartX(start.getCenterX());
		reflex.setStartY(start.getCenterY());
		reflex.setEndX(end.getCenterX());
		reflex.setEndY(end.getCenterY());
		reflex.setFill(color);
		reflex.setStroke(color);
		reflex.setStrokeWidth(2);
		return reflex;
	}

	private double configureLambda(double angle) {
		double lambda = 0;
		if (angle > 0 && angle < 180) {
			double aux = 180 - angle;
			lambda = aux * 0.5 / 360;
		} else {
			double aux = 360 - (angle - 180);
			lambda = aux * 0.5 / 360;
		}
		return lambda;
	}

	public static Circle drawResistance(double rl, Color resCol, double strokeWidth) {
		double xc = rl / (1 + rl);
		double yc = 0;
		double ratio = 1 / (1 + rl);

		Circle res = new Circle();
		res.setCenterX(xc * radio);
		res.setCenterY(yc * radio);
		res.setRadius(radio * ratio);
		res.setFill(Color.TRANSPARENT);
		res.setStroke(resCol);
		res.setStrokeWidth(strokeWidth);

		return res;
	}

	public static double configureAngle(Circle circle, Circle center) {
		double angle = 0;
		double num = (center.getCenterY() - circle.getCenterY());
		double den = (circle.getCenterX() - center.getCenterX());
		double m = num / den;
		if (num > 0 && den > 0) {
			angle = Math.toDegrees(Math.atan(m));
		} else if (num > 0 && den < 0) {
			angle = 180 + Math.toDegrees(Math.atan(m));
		} else if (num < 0 && den > 0) {
			angle = 360 + Math.toDegrees(Math.atan(m));
		} else {
			angle = 180 + Math.toDegrees(Math.atan(m));
		}
		return angle;
	}

	public static Circle calculateRlXl(Circle temp) {
		double x = temp.getCenterX() / radio;
		double y = -temp.getCenterY() / radio;
		
		double crl = 2 * (1 - x) / (Math.pow(1 - x, 2) + Math.pow(y, 2)) - 1;
		double cxl = 2 * y / (Math.pow((1 - x), 2) + Math.pow(y, 2));
		
		Circle aux = new Circle();
		aux.setCenterX(crl);
		aux.setCenterY(cxl);
		return aux;
	}

	public double getZlRes() {
		return zlRes;
	}

	public void setZlRes(double zlRes) {
		this.zlRes = zlRes;
	}

	public double getZlReact() {
		return zlReact;
	}

	public void setZlReact(double zlReact) {
		this.zlReact = zlReact;
	}

	public double getYlCon() {
		return ylCon;
	}

	public void setYlCon(double ylCon) {
		this.ylCon = ylCon;
	}

	public double getYlSuscep() {
		return ylSuscep;
	}

	public void setYlSuscep(double ylSuscep) {
		this.ylSuscep = ylSuscep;
	}

	public double getRlNormalized() {
		return rlNormalized;
	}

	public void setRlNormalized(double rlNormalized) {
		this.rlNormalized = rlNormalized;
	}

	public double getXlNormalized() {
		return xlNormalized;
	}

	public void setXlNormalized(double xlNormalized) {
		this.xlNormalized = xlNormalized;
	}

	public double getGlNormalized() {
		return glNormalized;
	}

	public void setGlNormalized(double glNormalized) {
		this.glNormalized = glNormalized;
	}

	public double getBlNormalized() {
		return blNormalized;
	}

	public void setBlNormalized(double blNormalized) {
		this.blNormalized = blNormalized;
	}

	public double getZo() {
		return zo;
	}

	public void setZo(double zo) {
		this.zo = zo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Circle getResCircle() {
		return resCircle;
	}

	public void setResCircle(Circle resCircle) {
		this.resCircle = resCircle;
	}

	public Arc getReactCircle() {
		return reactCircle;
	}

	public void setReactCircle(Arc reactCircle) {
		this.reactCircle = reactCircle;
	}

	public Circle getConCircle() {
		return conCircle;
	}

	public void setConCircle(Circle conCircle) {
		this.conCircle = conCircle;
	}

	public Arc getSuscepCircle() {
		return suscepCircle;
	}

	public void setSuscepCircle(Arc suscepCircle) {
		this.suscepCircle = suscepCircle;
	}

	public Circle getUnit() {
		return unit;
	}

	public void setUnit(Circle unit) {
		this.unit = unit;
	}

	public Circle getZlStroke() {
		return zlStroke;
	}

	public void setZlStroke(Circle zlStroke) {
		this.zlStroke = zlStroke;
	}

	public Circle getYlStroke() {
		return ylStroke;
	}

	public void setYlStroke(Circle ylStroke) {
		this.ylStroke = ylStroke;
	}

	public Circle getZl() {
		return zl;
	}

	public void setZl(Circle zl) {
		this.zl = zl;
	}

	public Circle getYl() {
		return yl;
	}

	public void setYl(Circle yl) {
		this.yl = yl;
	}

	public Group getMainGroup() {
		return mainGroup;
	}

	public void setMainGroup(Group mainGroup) {
		this.mainGroup = mainGroup;
	}

	public double getAngleZl() {
		return angleZl;
	}

	public void setAngleZl(double angleZl) {
		this.angleZl = angleZl;
	}

	public double getAngleYl() {
		return angleYl;
	}

	public void setAngleYl(double angleYl) {
		this.angleYl = angleYl;
	}

	public double getLambdaZl() {
		return lambdaZl;
	}

	public void setLambdaZl(double lambdaZl) {
		this.lambdaZl = lambdaZl;
	}

	public double getLambdaYl() {
		return lambdaYl;
	}

	public void setLambdaYl(double lambdaYl) {
		this.lambdaYl = lambdaYl;
	}

	public double getRo() {
		return ro;
	}

	public void setRo(double roZl) {
		this.ro = roZl;
	}

	public Label getZlMark() {
		return zlMark;
	}

	public void setZlMark(Label zlMark) {
		this.zlMark = zlMark;
	}

	public Label getYlMark() {
		return ylMark;
	}

	public void setYlMark(Label ylMark) {
		this.ylMark = ylMark;
	}

	public Label getLambdaZMark() {
		return lambdaZMark;
	}

	public void setLambdaZMark(Label lambdaZMark) {
		this.lambdaZMark = lambdaZMark;
	}

	public Label getLambdaYMark() {
		return lambdaYMark;
	}

	public void setLambdaYMark(Label lambdaYMark) {
		this.lambdaYMark = lambdaYMark;
	}

	public Circle getReflexCoefConst() {
		return reflexCoefConst;
	}

	public void setReflexCoefConst(Circle reflexCoefConst) {
		this.reflexCoefConst = reflexCoefConst;
	}

	public Line getLineReflexCoefZl() {
		return lineReflexCoefZl;
	}

	public void setLineReflexCoefZl(Line lineReflexCoefZl) {
		this.lineReflexCoefZl = lineReflexCoefZl;
	}

	public Line getLineReflexCoefYl() {
		return lineReflexCoefYl;
	}

	public void setLineReflexCoefYl(Line lineReflexCoefYl) {
		this.lineReflexCoefYl = lineReflexCoefYl;
	}

	public Line getZlCompLine() {
		return zlCompLine;
	}

	public void setZlCompLine(Line zlCompLine) {
		this.zlCompLine = zlCompLine;
	}

	public Line getYlCompLine() {
		return ylCompLine;
	}

	public void setYlCompLine(Line ylCompLine) {
		this.ylCompLine = ylCompLine;
	}

	public Circle getLambdaZStroke() {
		return lambdaZStroke;
	}

	public void setLambdaZStroke(Circle lambdaZStroke) {
		this.lambdaZStroke = lambdaZStroke;
	}

	public Circle getLambdaYStroke() {
		return lambdaYStroke;
	}

	public void setLambdaYStroke(Circle lambdaYStroke) {
		this.lambdaYStroke = lambdaYStroke;
	}

	public double getSwr() {
		return swr;
	}

	public void setSwr(double swr) {
		this.swr = swr;
	}


}
