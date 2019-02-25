package stub.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;

public class DoubleStub {

	private String typeStub1;
	private double dist1;
	private double dist2;
	private double freq;
	private double r0;
	private double forbidden;

	private SmithCircle zload;
	private SmithCircle d1load;
	private SmithCircle load11;
	private SmithCircle load11prime;

	private Arc loadD1Arc;
	private Arc loadD2Arc;
	private Arc loadD2primeArc;
	private Circle unitRotated;
	private Circle forbiddenCircle;

	private SmithCircle d2load;
	private SmithCircle d2loadprime;

	public DoubleStub(double d1, double d2, SmithCircle smithCircle, double freq, String typeStub1) {
		this.setTypeStub1(typeStub1);
		this.setFreq(freq);
		this.setDist1(d1);
		this.setDist2(d2);
		this.setZload(smithCircle);
		this.setD1load(moveLoadD1Generator(typeStub1));
		this.setLoadD1Arc(configureD1Arc());
		this.setUnitRotated(configureUnitResCircle());
		this.setLoad11(configureCross(d1load, unitRotated, 1));
		this.setLoad11prime(configureCross(d1load, unitRotated, 2));
		this.setForbiddenCircle(configureForbiddenCircle());
		this.setD2load(configureD2load(load11.getReflexCoefConst(), smithCircle.getUnit(), -1));
		this.setD2loadprime(configureD2load(load11prime.getReflexCoefConst(), smithCircle.getUnit(), 1));
		this.setLoadD2Arc(configureD2Arc(load11prime, d2loadprime, 1));
		this.setLoadD2primeArc(configureD2Arc(load11, d2load, 2));

		// this.setLoad11(configureCross(d1load, unitRotated, 0));
		// this.setLoad11prime(configureCross(d1load, unitRotated, 1));
		// this.setLine(configureLine(load11circle, load11primecircle));

		// System.out.println("type : " + typeStub1 + " d1 : " + d1 + " d2 : " +
		// d2);
	}

	private Arc configureD2Arc(SmithCircle load11, SmithCircle d2load, int opcion) {
		double start = (typeStub1.equals("Serie")) ? load11.getAngleZl() : load11.getAngleYl();
		double fin = (typeStub1.equals("Serie")) ? d2load.getAngleZl() : d2load.getAngleYl();
		double rotate = 0;
		
		
		if (opcion == 1) rotate = -(360 - Math.abs(fin - start));
		if (opcion == 2) rotate = -Math.abs(fin - start);
		System.out.println("start : " + start + " stop : " + fin + " rotate : " + rotate);
		
		Arc arc = new Arc();
		Circle c = load11.getReflexCoefConst();

		arc.setCenterX(c.getCenterX());
		arc.setCenterY(c.getCenterY());
		arc.setRadiusX(c.getRadius());
		arc.setRadiusY(c.getRadius());
		arc.setStartAngle(start);
		arc.setLength(rotate);
		arc.setFill(Color.TRANSPARENT);
		arc.setStroke(Color.BLUEVIOLET);
		arc.setStrokeWidth(3);
		arc.setType(ArcType.OPEN);

		return arc;
	}

	private SmithCircle configureD2load(Circle d2, Circle unit, int sgn) {
		Circle i11 = new Circle();
		i11.setRadius(30);
		i11.setFill(Color.RED);

		double d2x = d2.getCenterX();
		double d2r = d2.getRadius();

		double ux = unit.getCenterX();
		double ur = unit.getRadius();

		double d2x2 = Math.pow(d2x, 2);
		double d2r2 = Math.pow(d2r, 2);
		double ux2 = Math.pow(ux, 2);
		double ur2 = Math.pow(ur, 2);

		double t = d2r2 + ux2 - d2x2 - ur2;
		double h = 2 * (ux - d2x);
		double x = t / h;
		double x2 = Math.pow(x, 2);

		double y = sgn * Math.sqrt(d2r2 - x2 + (2 * d2x * x) - d2x2);

		i11.setCenterX(x);
		i11.setCenterY(y);

		Circle loadD2 = SmithCircle.calculateRlXl(i11);
		double rl = loadD2.getCenterX();
		double xl = loadD2.getCenterY();
		String type = "";
		if (typeStub1.equals("Serie"))
			type = "Impedance";
		if (typeStub1.equals("Paralelo"))
			type = "Admitance";

		SmithCircle sc = new SmithCircle(rl, xl, 1, type, Color.CORNFLOWERBLUE, Color.MEDIUMVIOLETRED);
		// System.out.println("d2x : " + d2x + " d2r : " + d2r + "d2x2 : " +
		// d2x2 + " d2r2 : " + d2r2);
		// System.out.println("ux : " + ux + " ur : " + ur + "ux2 : " + ux2 + "
		// ur2 : " + ur2);
		// System.out.println("x : " + x + " y : " + y);
		// System.out.println("t : " + t + " h : " + h);
		return sc;
	}

	private Circle configureForbiddenCircle() {
		double d2 = this.getDist2();
		double beta = 2 * Math.PI * d2;
		double sin2 = Math.pow(Math.sin(beta), 2);
		double csc2 = 1 / sin2;

		Circle tmp = SmithCircle.drawResistance(csc2, Color.DARKRED, 2);
		this.setForbidden(csc2);
		return tmp;
	}

	private SmithCircle configureCross(SmithCircle d1, Circle unit, int sel) {
		Circle i11 = new Circle();
		Circle load11 = new Circle();
		if (d1.getType().equals("Impedance")) {
			System.out.println(d1.getType());
			load11.setCenterX(d1.getResCircle().getCenterX());
			load11.setCenterY(d1.getResCircle().getCenterY());
			load11.setRadius(d1.getResCircle().getRadius());
		} else if (d1.getType().equals("Admitance")) {
			System.out.println(d1.getType());
			load11.setCenterX(d1.getConCircle().getCenterX());
			load11.setCenterY(d1.getConCircle().getCenterY());
			load11.setRadius(d1.getConCircle().getRadius());
		}

		double a = load11.getCenterX();
		double b = load11.getCenterY();
		double c = load11.getRadius();

		double d = unit.getCenterX();
		double e = unit.getCenterY();
		double f = unit.getRadius();

		double a2 = Math.pow(a, 2);
		double b2 = Math.pow(b, 2);
		double c2 = Math.pow(c, 2);
		double d2 = Math.pow(d, 2);
		double e2 = Math.pow(e, 2);
		double f2 = Math.pow(f, 2);

		double k = -a2 - b2 + c2 + d2 + e2 - f2;
		double m = k / (2 * (d - a));
		double n = (e - b) / (d - a);

		double m2 = Math.pow(m, 2);
		double n2 = Math.pow(n, 2);

		double p = 1 + n2;
		double q = 2 * (a * n - m * n - b);
		double r = a2 + b2 - c2 + m2 - 2 * a * m;

		System.out.println("k = " + k + " m = " + m + " n = " + n);

		double disc = Math.pow(q, 2) - 4 * p * r;
		double y1 = (-q + Math.sqrt(disc)) / (2 * p);
		double y2 = (-q - Math.sqrt(disc)) / (2 * p);
		System.out.println("aa = " + p + " bb = " + q + " cc = " + r);
		System.out.println("x1 = " + y1 + " x2 = " + y2);

		double x = 0, y = 0;
		if (sel == 1) {
			y = y1;
			x = m - n * y;
		}
		if (sel == 2) {
			y = y2;
			x = m - n * y;
		}

		i11.setCenterX(x);
		i11.setCenterY(y);
		Circle tmp = SmithCircle.calculateRlXl(i11);
		double rl = tmp.getCenterX();
		double xl = tmp.getCenterY();
		double zo = 1;
		SmithCircle sc = new SmithCircle(rl, xl, zo, d1.getType(), Color.web("#DCC6E0"), Color.web("#BF55EC"));
		return sc;
	}

	private Circle configureUnitResCircle() {
		double rotate = dist2 * (360 / 0.5);
		double r = zload.getUnit().getRadius();

		Circle c = new Circle();
		c.setCenterX(r * Math.cos(Math.toRadians(rotate)));
		c.setCenterY(-r * Math.sin(Math.toRadians(rotate)));
		c.setRadius(r);
		c.setFill(Color.TRANSPARENT);
		c.setStroke(Color.ORANGE);
		c.setStrokeWidth(2);
		return c;
	}

	private Arc configureD1Arc() {
		double start = (typeStub1.equals("Serie")) ? zload.getAngleZl() : zload.getAngleYl();
		double rotate = -dist1 * (360 / 0.5);

		Arc arc = new Arc();
		Circle c = zload.getReflexCoefConst();

		arc.setCenterX(c.getCenterX());
		arc.setCenterY(c.getCenterY());
		arc.setRadiusX(c.getRadius());
		arc.setRadiusY(c.getRadius());
		arc.setStartAngle(start);
		arc.setLength(rotate);
		arc.setFill(Color.TRANSPARENT);
		arc.setStroke(Color.BLUEVIOLET);
		arc.setStrokeWidth(3);
		arc.setType(ArcType.OPEN);

		// System.out.println("Angle d1 arc conf : " + start + " rotate : " +
		// rotate);
		return arc;
	}

	// private Label configureMark() {
	// double r = SmithCircle.round(SmithCircle.configureGl(loadD1));
	// double x = SmithCircle.round(SmithCircle.configureBl(loadD1));
	// Label tmp = (getTypeStub1().equals("Paralelo")) ? new Label("yd1 (" + r +
	// " , " + x + ")") : new Label("zd1 (" + r + " , " + x + ")");
	//// System.out.println("Yd1 : " + loadD1.getCenterX() + " , " +
	// loadD1.getCenterY());
	// tmp.setLayoutX(loadD1.getCenterX());
	// tmp.setLayoutY(loadD1.getCenterY());
	// tmp.setFont(SmithCircle.FONT);
	// return tmp;
	// }

	private SmithCircle moveLoadD1Generator(String type) {
		return (type.equals("Serie")) ? move(zload.getZl(), zload.getAngleZl(), "Impedance") : move(zload.getYl(), zload.getAngleYl(), "Admitance");
	}

	private SmithCircle move(Circle load, double angle, String type) {
		Circle temp = new Circle();
		double xx = Math.pow(load.getCenterX(), 2);
		double yy = Math.pow(load.getCenterY(), 2);
		double r = Math.sqrt(xx + yy);
		double radAngle = Math.toRadians(angle);
		double rotate = Math.toRadians(dist1 * (360 / 0.5));

		temp.setCenterX(r * Math.cos(radAngle - rotate));
		temp.setCenterY(-r * Math.sin(radAngle - rotate));
		temp.setRadius(5);
		temp.setFill(load.getFill());

		Circle d1 = SmithCircle.calculateRlXl(temp);
		double xl = d1.getCenterX();
		double yl = d1.getCenterY();
		SmithCircle loadD1 = new SmithCircle(xl, yl, 1, type, Color.web("#2ecc71"), Color.web("#16a085"));
		// System.out.println("d1 r : " + loadD1.getGlNormalized() + " d1 x : "
		// + loadD1.getBlNormalized());
		return loadD1;
	}

	public String getTypeStub1() {
		return typeStub1;
	}

	public void setTypeStub1(String typeStub1) {
		this.typeStub1 = typeStub1;
	}

	public SmithCircle getZload() {
		return zload;
	}

	public void setZload(SmithCircle zload) {
		this.zload = zload;
	}

	public double getDist1() {
		return dist1;
	}

	public void setDist1(double dist1) {
		this.dist1 = dist1;
	}

	public double getDist2() {
		return dist2;
	}

	public void setDist2(double dist2) {
		this.dist2 = dist2;
	}

	public SmithCircle getD1load() {
		return d1load;
	}

	public void setD1load(SmithCircle d1load) {
		this.d1load = d1load;
	}

	public Arc getLoadD1Arc() {
		return loadD1Arc;
	}

	public void setLoadD1Arc(Arc loadD1Arc) {
		this.loadD1Arc = loadD1Arc;
	}

	public Circle getUnitRotated() {
		return unitRotated;
	}

	public void setUnitRotated(Circle unitRotated) {
		this.unitRotated = unitRotated;
	}

	public SmithCircle getLoad11() {
		return load11;
	}

	public void setLoad11(SmithCircle load11) {
		this.load11 = load11;
	}

	public SmithCircle getLoad11prime() {
		return load11prime;
	}

	public void setLoad11prime(SmithCircle load11prime) {
		this.load11prime = load11prime;
	}

	public Circle getForbiddenCircle() {
		return forbiddenCircle;
	}

	public void setForbiddenCircle(Circle forbiddenCircle) {
		this.forbiddenCircle = forbiddenCircle;
	}

	public double getFreq() {
		return freq;
	}

	public void setFreq(double freq) {
		this.freq = freq;
	}

	public SmithCircle getD2load() {
		return d2load;
	}

	public void setD2load(SmithCircle circle) {
		this.d2load = circle;
	}

	public SmithCircle getD2loadprime() {
		return d2loadprime;
	}

	public void setD2loadprime(SmithCircle d2loadprime) {
		this.d2loadprime = d2loadprime;
	}

	public double getR0() {
		return r0;
	}

	public void setR0(double r0) {
		this.r0 = r0;
	}

	public double getForbidden() {
		return forbidden;
	}

	public void setForbidden(double forbidden) {
		this.forbidden = forbidden;
	}

	public Arc getLoadD2Arc() {
		return loadD2Arc;
	}

	public void setLoadD2Arc(Arc loadD2Arc) {
		this.loadD2Arc = loadD2Arc;
	}

	public Arc getLoadD2primeArc() {
		return loadD2primeArc;
	}

	public void setLoadD2primeArc(Arc loadD2primeArc) {
		this.loadD2primeArc = loadD2primeArc;
	}
}
