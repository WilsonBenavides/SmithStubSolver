package stub.view.stubpane;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import stub.Main;
import stub.model.DoubleStub;
import stub.model.SmithCircle;

//data[0] = rl ; 

public class DoubleStubSolverController {

	public static final Font FONT_BOLD = Font.font("Comic Sans", FontWeight.BOLD, 15);
	public static final Font FONT = Font.font("Comic Sans", FontWeight.NORMAL, 15);

	private DoubleStub doubleStub;
	String stubType1 = "";
	String stubType2 = "";

	int step = 0;
	@FXML
	private Group mainGroup;
	@FXML
	private Button next;
	@FXML
	private Button back;
	@FXML
	private Button edit;
	@FXML
	private Label step1;
	@FXML
	private Label step2;
	@FXML
	private Label step3;
	@FXML
	private Label step4;
	@FXML
	private Label step5;
	@FXML
	private Label step6;
	@FXML
	private Label step7;
	@FXML
	private Label step8;
	@FXML
	private Label step9;
	@FXML
	private Label step10;
	@FXML
	private Label step11;
	@FXML
	private Label step12;
	@FXML
	private Label step13;
	@FXML
	private Label step14;
	@FXML
	private Label step15;
	@FXML
	private Label step16;
	@FXML
	private Label step17;
	@FXML
	private Label step18;
	@FXML
	private Label step19;
	@FXML
	private Label step20;
	@FXML
	private Label step21;
	@FXML
	private Label step22;
	@FXML
	private Label step23;
	@FXML
	private Label step24;
	@FXML
	private Label step25;
	@FXML
	private Label step26;
	@FXML
	private Label step27;
	@FXML
	private Label step28;
	@FXML
	private Label step29;
	@FXML
	private Label step30;
	@FXML
	private Label step31;

	@FXML
	private void initialize() {
		Main.hidePrimaryStage();

		double zo = Double.parseDouble(Main.getData().get(0));
		double rl = Double.parseDouble(Main.getData().get(1));
		double xl = Double.parseDouble(Main.getData().get(2));
		double d1 = Double.parseDouble(Main.getData().get(3));
		double d2 = Double.parseDouble(Main.getData().get(4));
		double freq = Double.parseDouble(Main.getData().get(5));
		String type1 = Main.getData().get(6);
		String type2 = Main.getData().get(7);

		setStubType1(type1);
		setStubType2(type2);

		SmithCircle load = new SmithCircle(rl, xl, zo, "Impedance", Color.web("#c0392b"), Color.web("#2980b9"));
		doubleStub = new DoubleStub(d1, d2, load, freq, type1);

		if (type1.equals("Serie")) {
			Circle z = doubleStub.getZload().getZl();
			Label lx = doubleStub.getZload().getZlMark();
			mainGroup.getChildren().addAll(z, lx);

		} else if (type1.equals("Paralelo")) {
			Circle y = doubleStub.getZload().getYl();
			Label ly = doubleStub.getZload().getYlMark();
			mainGroup.getChildren().addAll(y, ly);
		}

		double d11rl = SmithCircle.round(doubleStub.getLoad11().getRlNormalized());
		double d11xl = SmithCircle.round(doubleStub.getLoad11().getXlNormalized());
		double d11gl = SmithCircle.round(doubleStub.getLoad11().getGlNormalized());
		double d11bl = SmithCircle.round(doubleStub.getLoad11().getBlNormalized());

		double d11rlp = SmithCircle.round(doubleStub.getLoad11prime().getRlNormalized());
		double d11xlp = SmithCircle.round(doubleStub.getLoad11prime().getXlNormalized());
		double d11glp = SmithCircle.round(doubleStub.getLoad11prime().getGlNormalized());
		double d11blp = SmithCircle.round(doubleStub.getLoad11prime().getBlNormalized());

		double d2xl = SmithCircle.round(doubleStub.getD2load().getXlNormalized());
		double d2rl = SmithCircle.round(doubleStub.getD2load().getRlNormalized());
		double d2gl = SmithCircle.round(doubleStub.getD2load().getGlNormalized());
		double d2bl = SmithCircle.round(doubleStub.getD2load().getBlNormalized());

		double d2xlp = SmithCircle.round(doubleStub.getD2loadprime().getXlNormalized());
		double d2rlp = SmithCircle.round(doubleStub.getD2loadprime().getRlNormalized());
		double d2glp = SmithCircle.round(doubleStub.getD2loadprime().getGlNormalized());
		double d2blp = SmithCircle.round(doubleStub.getD2loadprime().getBlNormalized());

		doubleStub.getD2load().getZlMark().setText("zd2 (" + d2rl + " , " + d2xl + ")");
		doubleStub.getD2load().getYlMark().setText("yd2 (" + d2gl + " , " + d2bl + ")");

		doubleStub.getD2loadprime().getZlMark().setText("zd2' (" + d2rlp + " , " + d2xlp + ")");
		doubleStub.getD2loadprime().getYlMark().setText("yd2' (" + d2glp + " , " + d2blp + ")");

		doubleStub.getLoad11().getZlMark().setText("z11 (" + d11rl + " , " + d11xl + ")");
		doubleStub.getLoad11().getYlMark().setText("y11 (" + d11gl + " , " + d11bl + ")");

		doubleStub.getLoad11prime().getZlMark().setText("z11' (" + d11rlp + " , " + d11xlp + ")");
		doubleStub.getLoad11prime().getYlMark().setText("y11' (" + d11glp + " , " + d11blp + ")");

		// double rll = SmithCircle.round(rl / zo);
		// String xll = (xl > 0) ? " + j" + Math.abs(SmithCircle.round(xl / zo))
		// : " - j" + Math.abs(SmithCircle.round(xl / zo));
		// double gll = SmithCircle.round(doubleStub.getZload().getYlCon() /
		// zo);
		// String bll = (xl > 0) ? " - j" +
		// Math.abs(SmithCircle.round(doubleStub.getZload().getYlSuscep() / zo))
		// : " + j" +
		// Math.abs(SmithCircle.round(doubleStub.getZload().getYlSuscep() /
		// zo));
		// String str = (type1.equals("Serie")) ? "zl = " + rll + xll : "yl = "
		// + gll + bll;

		step1.setText(configureText(doubleStub.getZload()));
		step1.setFont(FONT_BOLD);
		step2.setText("");
		step3.setText("");
		step4.setText("");
		step5.setText("");
		step6.setText("");
		// step3.setText(angleCoef);
		// step4.setText("Longitud de Onda : " +
		// SmithCircle.round(doubleStub.getZload().getLambdaZl()));
		// step5.setText("Coeficiente de Reflexion : " +
		// SmithCircle.round(doubleStub.getZload().getRoZl()));
		// String str = (doubleStub.getTypeStub1().equals("Serie")) ? "zd1 = ("
		// + doubleStub.getZd1().getRlNormalized() + " , " +
		// doubleStub.getZd1().getXlNormalized() : "Paralelo";
		// step6.setText(str);
		// System.out.println("type : " + type);
	}

	@FXML
	private void goNext() {
		step++;
		System.out.println("next, step : " + step);
		if (step == 1)
			step1();

		if (step == 2)
			step2();

		if (step == 3)
			step3();

		if (step == 4)
			step4();

		if (step == 5)
			step5();

		if (step == 6)
			step6();

		if (step == 7)
			step7();

		if (step == 8)
			step8();

		if (step == 9)
			step9();

		if (step == 10)
			step10();

		if (step == 11)
			step11();

		if (step == 12)
			step12();

		if (step == 13)
			step13();

		if (step == 14)
			step14();

		if (step == 15)
			step15();

		if (step == 16)
			step16();

		if (step == 17)
			step17();
	}

	@FXML
	private void goBack() {
		if (step < 0)
			step = 0;

		if (step == 1)
			removeStep1();

		if (step == 2)
			removeStep2();

		if (step == 3)
			removeStep3();

		if (step == 4)
			removeStep4();

		if (step == 5)
			removeStep5();

		if (step == 6)
			removeStep6();

		if (step == 7)
			removeStep7();

		if (step == 8)
			removeStep8();

		if (step == 9)
			removeStep9();

		if (step == 10)
			removeStep10();

		if (step == 11)
			removeStep11();

		if (step == 12)
			removeStep12();

		if (step == 13)
			removeStep13();

		if (step == 14)
			removeStep14();
		
		if (step == 15)
			removeStep15();
		
		if (step == 16)
			removeStep16();
		
		if (step == 17)
			removeStep17();

		step--;
		// System.out.println("back, step : " + step);
	}

	@FXML
	private void goEdit() {
		Main.showAddDoubleStubStage();
		Main.closeDoubleStubSolver();
		System.out.println("edit..!!");
	}

	private void step1() {
		System.out.println("step 1");
		setGrayCircles();
		String angleCoef = "Angulo Coeficiente Reflexion = ";
		String lambda = "Longitud de Onda = ";
		double ro = SmithCircle.round(doubleStub.getZload().getRo());
		double swr = SmithCircle.round(doubleStub.getZload().getSwr());
		String sro = "ro = " + ro;
		String stwr = "SWR = " + swr;

		Circle r = doubleStub.getZload().getResCircle();
		Arc x = doubleStub.getZload().getReactCircle();
		Circle lzst = doubleStub.getZload().getLambdaZStroke();
		Label lzlb = doubleStub.getZload().getLambdaZMark();
		Line lnz = doubleStub.getZload().getLineReflexCoefZl();
		Line lncz = doubleStub.getZload().getZlCompLine();

		Circle g = doubleStub.getZload().getConCircle();
		Arc b = doubleStub.getZload().getSuscepCircle();
		Circle lyst = doubleStub.getZload().getLambdaYStroke();
		Label lylb = doubleStub.getZload().getLambdaYMark();
		Line lny = doubleStub.getZload().getLineReflexCoefYl();
		Line lncy = doubleStub.getZload().getYlCompLine();

		if (stubType1.equals("Serie")) {
			angleCoef = angleCoef + SmithCircle.round(doubleStub.getZload().getAngleZl());
			lambda = lambda + SmithCircle.round(doubleStub.getZload().getLambdaZl());
			mainGroup.getChildren().addAll(r, x, lzst, lzlb, lnz, lncz);

		} else if (stubType1.equals("Paralelo")) {
			angleCoef = angleCoef + SmithCircle.round(doubleStub.getZload().getAngleYl());
			lambda = lambda + SmithCircle.round(doubleStub.getZload().getLambdaYl());
			mainGroup.getChildren().addAll(g, b, lyst, lylb, lny, lncy);
		}
		step2.setText(lambda);
		step3.setText(angleCoef);
		step4.setText(sro);
		step5.setText(stwr);

		step1.setFont(FONT);
		step2.setFont(FONT_BOLD);
		step3.setFont(FONT_BOLD);
		step4.setFont(FONT_BOLD);
		step5.setFont(FONT_BOLD);
	}

	private void step2() {
		System.out.println("step 2");

		setGrayCircles();
		Circle cr = doubleStub.getZload().getReflexCoefConst();
		mainGroup.getChildren().addAll(cr);
	}

	private void step3() {
		System.out.println("step 3");
		setGrayCircles();

		Circle cr = doubleStub.getZload().getReflexCoefConst();
		Arc ar = doubleStub.getLoadD1Arc();
		mainGroup.getChildren().remove(cr);
		mainGroup.getChildren().add(ar);

		double d1xl = SmithCircle.round(doubleStub.getD1load().getXlNormalized());
		double d1rl = SmithCircle.round(doubleStub.getD1load().getRlNormalized());
		double d1gl = SmithCircle.round(doubleStub.getD1load().getGlNormalized());
		double d1bl = SmithCircle.round(doubleStub.getD1load().getBlNormalized());

		doubleStub.getD1load().getZlMark().setText("zd1 (" + d1rl + " , " + d1xl + ")");
		doubleStub.getD1load().getYlMark().setText("yd1 (" + d1gl + " , " + d1bl + ")");

		Circle zld1 = doubleStub.getD1load().getZl();
		Label d1lz = doubleStub.getD1load().getZlMark();

		Circle yld1 = doubleStub.getD1load().getYl();
		Label d1ly = doubleStub.getD1load().getYlMark();

		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().addAll(zld1, d1lz);
		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().addAll(yld1, d1ly);
		}

		step6.setText(configureText(doubleStub.getD1load()));
		step1.setFont(FONT);
		step2.setFont(FONT);
		step3.setFont(FONT);
		step4.setFont(FONT);
		step5.setFont(FONT);
		step6.setFont(FONT_BOLD);
	}

	private void step4() {
		System.out.println("step 4");
		setGrayCircles();

		String angleCoef = "Angulo Coeficiente Reflexion = ";
		String lambda = "Longitud de Onda = ";
		double ro = SmithCircle.round(doubleStub.getD1load().getRo());
		double swr = SmithCircle.round(doubleStub.getD1load().getSwr());
		String sro = "ro = " + ro;
		String stwr = "SWR = " + swr;

		Circle r = doubleStub.getD1load().getResCircle();
		Arc x = doubleStub.getD1load().getReactCircle();
		Circle lzst = doubleStub.getD1load().getLambdaZStroke();
		Label lzlb = doubleStub.getD1load().getLambdaZMark();
		Line lnz = doubleStub.getD1load().getLineReflexCoefZl();
		Line lncz = doubleStub.getD1load().getZlCompLine();

		Line lz = doubleStub.getZload().getLineReflexCoefZl();
		Line lzc = doubleStub.getZload().getZlCompLine();
		Line ly = doubleStub.getZload().getLineReflexCoefYl();
		Line lyc = doubleStub.getZload().getYlCompLine();

		Circle g = doubleStub.getD1load().getConCircle();
		Arc b = doubleStub.getD1load().getSuscepCircle();
		Circle lyst = doubleStub.getD1load().getLambdaYStroke();
		Label lylb = doubleStub.getD1load().getLambdaYMark();
		Line lny = doubleStub.getD1load().getLineReflexCoefYl();
		Line lncy = doubleStub.getD1load().getYlCompLine();

		if (stubType1.equals("Serie")) {
			angleCoef = angleCoef + SmithCircle.round(doubleStub.getD1load().getAngleZl());
			lambda = lambda + SmithCircle.round(doubleStub.getD1load().getLambdaZl());
			mainGroup.getChildren().addAll(r, x, lzst, lzlb, lnz, lncz);
			mainGroup.getChildren().removeAll(lz, lzc);

		} else if (stubType1.equals("Paralelo")) {
			angleCoef = angleCoef + SmithCircle.round(doubleStub.getD1load().getAngleYl());
			lambda = lambda + SmithCircle.round(doubleStub.getD1load().getLambdaYl());
			mainGroup.getChildren().addAll(g, b, lyst, lylb, lny, lncy);
			mainGroup.getChildren().removeAll(ly, lyc);
		}

		step7.setText(lambda);
		step8.setText(angleCoef);
		step9.setText(sro);
		step10.setText(stwr);

		step6.setFont(FONT);
		step7.setFont(FONT_BOLD);
		step8.setFont(FONT_BOLD);
		step9.setFont(FONT_BOLD);
		step10.setFont(FONT_BOLD);
	}

	private void step5() {
		System.out.println("step 5");
		setGrayCircles();

		Line lnz = doubleStub.getD1load().getLineReflexCoefZl();
		Line lncz = doubleStub.getD1load().getZlCompLine();
		Line lny = doubleStub.getD1load().getLineReflexCoefYl();
		Line lncy = doubleStub.getD1load().getYlCompLine();

		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().removeAll(lnz, lncz);

		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().removeAll(lny, lncy);
		}

		Circle crt = doubleStub.getUnitRotated();
		mainGroup.getChildren().add(crt);
	}

	private void step6() {
		System.out.println("step 6");
		setGrayCircles();

		Circle cf = doubleStub.getForbiddenCircle();
		mainGroup.getChildren().add(cf);

		String tmp = "Circulo Prohibido ";
		double fdn = SmithCircle.round(doubleStub.getForbidden());
		if (stubType1.equals("Serie")) {
			tmp = tmp.concat("rp = " + fdn);
		} else if (stubType1.equals("Paralelo")) {
			tmp = tmp.concat("gp = " + fdn);
		}

		step11.setText(tmp);
		step7.setFont(FONT);
		step8.setFont(FONT);
		step9.setFont(FONT);
		step10.setFont(FONT);
		step11.setFont(FONT_BOLD);
	}

	private void step7() {
		System.out.println("step " + step);
		setGrayCircles();

		double xl = SmithCircle.round(doubleStub.getLoad11prime().getXlNormalized());
		double rl = SmithCircle.round(doubleStub.getLoad11prime().getRlNormalized());
		double gl = SmithCircle.round(doubleStub.getLoad11prime().getGlNormalized());
		double bl = SmithCircle.round(doubleStub.getLoad11prime().getBlNormalized());

		doubleStub.getLoad11prime().getZlMark().setText("z11 (" + rl + " , " + xl + ")");
		doubleStub.getLoad11prime().getYlMark().setText("y11 (" + gl + " , " + bl + ")");

		Circle zld1 = doubleStub.getLoad11prime().getZl();
		Label d1lz = doubleStub.getLoad11prime().getZlMark();

		Circle yld1 = doubleStub.getLoad11prime().getYl();
		Label d1ly = doubleStub.getLoad11prime().getYlMark();

		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().addAll(zld1, d1lz);
		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().addAll(yld1, d1ly);
		}

		step12.setText(configureText(doubleStub.getLoad11prime()));
		step11.setFont(FONT);
		step12.setFont(FONT_BOLD);
	}

	private void step8() {
		System.out.println("step 8");
		setGrayCircles();

		String angleCoef = "Angulo Coeficiente Reflexion = ";
		String lambda = "Longitud de Onda = ";
		double ro = SmithCircle.round(doubleStub.getLoad11prime().getRo());
		double swr = SmithCircle.round(doubleStub.getLoad11prime().getSwr());
		String sro = "ro = " + ro;
		String stwr = "SWR = " + swr;

		Circle r = doubleStub.getLoad11prime().getResCircle();
		Arc x = doubleStub.getLoad11prime().getReactCircle();
		Circle lzst = doubleStub.getLoad11prime().getLambdaZStroke();
		Label lzlb = doubleStub.getLoad11prime().getLambdaZMark();
		Line lnz = doubleStub.getLoad11prime().getLineReflexCoefZl();
		Line lncz = doubleStub.getLoad11prime().getZlCompLine();

		Circle g = doubleStub.getLoad11prime().getConCircle();
		Arc b = doubleStub.getLoad11prime().getSuscepCircle();
		Circle lyst = doubleStub.getLoad11prime().getLambdaYStroke();
		Label lylb = doubleStub.getLoad11prime().getLambdaYMark();
		Line lny = doubleStub.getLoad11prime().getLineReflexCoefYl();
		Line lncy = doubleStub.getLoad11prime().getYlCompLine();

		if (stubType1.equals("Serie")) {
			angleCoef = angleCoef + SmithCircle.round(doubleStub.getLoad11prime().getAngleZl());
			lambda = lambda + SmithCircle.round(doubleStub.getLoad11prime().getLambdaZl());
			mainGroup.getChildren().addAll(r, x, lzst, lzlb, lnz, lncz);

		} else if (stubType1.equals("Paralelo")) {
			angleCoef = angleCoef + SmithCircle.round(doubleStub.getLoad11prime().getAngleYl());
			lambda = lambda + SmithCircle.round(doubleStub.getLoad11prime().getLambdaYl());
			mainGroup.getChildren().addAll(g, b, lyst, lylb, lny, lncy);
		}
		step13.setText(lambda);
		step14.setText(angleCoef);
		step15.setText(sro);
		step16.setText(stwr);

		step12.setFont(FONT);
		step13.setFont(FONT_BOLD);
		step14.setFont(FONT_BOLD);
		step15.setFont(FONT_BOLD);
		step16.setFont(FONT_BOLD);
	}

	private void step9() {
		setGrayCircles();

		double xl = SmithCircle.round(doubleStub.getLoad11().getXlNormalized());
		double rl = SmithCircle.round(doubleStub.getLoad11().getRlNormalized());
		double gl = SmithCircle.round(doubleStub.getLoad11().getGlNormalized());
		double bl = SmithCircle.round(doubleStub.getLoad11().getBlNormalized());

		doubleStub.getLoad11().getZlMark().setText("z11' (" + rl + " , " + xl + ")");
		doubleStub.getLoad11().getYlMark().setText("y11' (" + gl + " , " + bl + ")");

		Circle zld1 = doubleStub.getLoad11().getZl();
		Label d1lz = doubleStub.getLoad11().getZlMark();

		Circle yld1 = doubleStub.getLoad11().getYl();
		Label d1ly = doubleStub.getLoad11().getYlMark();

		Line lz = doubleStub.getLoad11prime().getLineReflexCoefZl();
		Line lzc = doubleStub.getLoad11prime().getZlCompLine();
		Line ly = doubleStub.getLoad11prime().getLineReflexCoefYl();
		Line lyc = doubleStub.getLoad11prime().getYlCompLine();

		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().addAll(zld1, d1lz);
			mainGroup.getChildren().removeAll(lz, lzc);
		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().addAll(yld1, d1ly);
			mainGroup.getChildren().removeAll(ly, lyc);
		}

		step13.setFont(FONT);
		step14.setFont(FONT);
		step15.setFont(FONT);
		step16.setFont(FONT);
		step17.setText(configureText(doubleStub.getLoad11()));
		step17.setFont(FONT_BOLD);
	}

	private void step10() {
		System.out.println("step 10");
		setGrayCircles();

		String angleCoef = "Angulo Coeficiente Reflexion = ";
		String lambda = "Longitud de Onda = ";
		double ro = SmithCircle.round(doubleStub.getLoad11().getRo());
		double swr = SmithCircle.round(doubleStub.getLoad11().getSwr());
		String sro = "ro = " + ro;
		String stwr = "SWR = " + swr;

		Circle r = doubleStub.getLoad11().getResCircle();
		Arc x = doubleStub.getLoad11().getReactCircle();
		Circle lzst = doubleStub.getLoad11().getLambdaZStroke();
		Label lzlb = doubleStub.getLoad11().getLambdaZMark();
		Line lnz = doubleStub.getLoad11().getLineReflexCoefZl();
		Line lncz = doubleStub.getLoad11().getZlCompLine();

		Circle g = doubleStub.getLoad11().getConCircle();
		Arc b = doubleStub.getLoad11().getSuscepCircle();
		Circle lyst = doubleStub.getLoad11().getLambdaYStroke();
		Label lylb = doubleStub.getLoad11().getLambdaYMark();
		Line lny = doubleStub.getLoad11().getLineReflexCoefYl();
		Line lncy = doubleStub.getLoad11().getYlCompLine();

		if (stubType1.equals("Serie")) {
			angleCoef = angleCoef + SmithCircle.round(doubleStub.getLoad11().getAngleZl());
			lambda = lambda + SmithCircle.round(doubleStub.getLoad11().getLambdaZl());
			mainGroup.getChildren().addAll(r, x, lzst, lzlb, lnz, lncz);

		} else if (stubType1.equals("Paralelo")) {
			angleCoef = angleCoef + SmithCircle.round(doubleStub.getLoad11().getAngleYl());
			lambda = lambda + SmithCircle.round(doubleStub.getLoad11().getLambdaYl());
			mainGroup.getChildren().addAll(g, b, lyst, lylb, lny, lncy);
		}
		step18.setText(lambda);
		step19.setText(angleCoef);
		step20.setText(sro);
		step21.setText(stwr);

		step17.setFont(FONT);
		step18.setFont(FONT_BOLD);
		step19.setFont(FONT_BOLD);
		step20.setFont(FONT_BOLD);
		step21.setFont(FONT_BOLD);
	}

	private void step11() {
		System.out.println("step 11");

		setGrayCircles();
		Circle un = doubleStub.getZload().getUnit();
		mainGroup.getChildren().addAll(un);

		Line lz = doubleStub.getLoad11().getLineReflexCoefZl();
		Line lzc = doubleStub.getLoad11().getZlCompLine();
		Line ly = doubleStub.getLoad11().getLineReflexCoefYl();
		Line lyc = doubleStub.getLoad11().getYlCompLine();

		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().removeAll(lz, lzc);
		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().removeAll(ly, lyc);
		}
	}

	private void step12() {
		System.out.println("step 12");

		setGrayCircles();
		Circle cr = doubleStub.getLoad11prime().getReflexCoefConst();
		mainGroup.getChildren().addAll(cr);
	}

	private void step13() {
		System.out.println("step 13");
		setGrayCircles();

		Circle cr = doubleStub.getLoad11prime().getReflexCoefConst();
		Arc ar = doubleStub.getLoadD2Arc();
		mainGroup.getChildren().remove(cr);
		mainGroup.getChildren().add(ar);

		double xl = SmithCircle.round(doubleStub.getD2loadprime().getXlNormalized());
		double rl = SmithCircle.round(doubleStub.getD2loadprime().getRlNormalized());
		double gl = SmithCircle.round(doubleStub.getD2loadprime().getGlNormalized());
		double bl = SmithCircle.round(doubleStub.getD2loadprime().getBlNormalized());

		doubleStub.getD2loadprime().getZlMark().setText("zd2 (" + rl + " , " + xl + ")");
		doubleStub.getD2loadprime().getYlMark().setText("yd2 (" + gl + " , " + bl + ")");

		Circle zld1 = doubleStub.getD2loadprime().getZl();
		Label d1lz = doubleStub.getD2loadprime().getZlMark();

		Circle yld1 = doubleStub.getD2loadprime().getYl();
		Label d1ly = doubleStub.getD2loadprime().getYlMark();

		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().addAll(zld1, d1lz);
		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().addAll(yld1, d1ly);
		}

		step22.setText(configureText(doubleStub.getD2loadprime()));
		step17.setFont(FONT);
		step18.setFont(FONT);
		step19.setFont(FONT);
		step20.setFont(FONT);
		step21.setFont(FONT);
		step22.setFont(FONT_BOLD);
	}

	private void step14() {
		System.out.println("step 14");
		setGrayCircles();

		String angleCoef = "Angulo Coeficiente Reflexion = ";
		String lambda = "Longitud de Onda = ";
		double ro = SmithCircle.round(doubleStub.getD2loadprime().getRo());
		double swr = SmithCircle.round(doubleStub.getD2loadprime().getSwr());
		String sro = "ro = " + ro;
		String stwr = "SWR = " + swr;

		Circle r = doubleStub.getD2loadprime().getResCircle();
		Arc x = doubleStub.getD2loadprime().getReactCircle();
		Circle lzst = doubleStub.getD2loadprime().getLambdaZStroke();
		Label lzlb = doubleStub.getD2loadprime().getLambdaZMark();
		Line lnz = doubleStub.getD2loadprime().getLineReflexCoefZl();
		Line lncz = doubleStub.getD2loadprime().getZlCompLine();

		Circle g = doubleStub.getD2loadprime().getConCircle();
		Arc b = doubleStub.getD2loadprime().getSuscepCircle();
		Circle lyst = doubleStub.getD2loadprime().getLambdaYStroke();
		Label lylb = doubleStub.getD2loadprime().getLambdaYMark();
		Line lny = doubleStub.getD2loadprime().getLineReflexCoefYl();
		Line lncy = doubleStub.getD2loadprime().getYlCompLine();

		if (stubType1.equals("Serie")) {
			angleCoef = angleCoef + SmithCircle.round(doubleStub.getD2loadprime().getAngleZl());
			lambda = lambda + SmithCircle.round(doubleStub.getD2loadprime().getLambdaZl());
			mainGroup.getChildren().addAll(r, x, lzst, lzlb, lnz, lncz);

		} else if (stubType1.equals("Paralelo")) {
			angleCoef = angleCoef + SmithCircle.round(doubleStub.getD2loadprime().getAngleYl());
			lambda = lambda + SmithCircle.round(doubleStub.getD2loadprime().getLambdaYl());
			mainGroup.getChildren().addAll(g, b, lyst, lylb, lny, lncy);
		}
		step23.setText(lambda);
		step24.setText(angleCoef);
		step25.setText(sro);
		step26.setText(stwr);

		step22.setFont(FONT);
		step23.setFont(FONT_BOLD);
		step24.setFont(FONT_BOLD);
		step25.setFont(FONT_BOLD);
		step26.setFont(FONT_BOLD);
	}

	private void step15() {
		System.out.println("step 15");

		Circle ur = doubleStub.getZload().getUnit();
		Circle cr = doubleStub.getLoad11().getReflexCoefConst();
		mainGroup.getChildren().remove(ur);
		setGrayCircles();
		ur.setStroke(Color.web("#f1c40f"));
		mainGroup.getChildren().addAll(cr, ur);

		Line lz = doubleStub.getD2loadprime().getLineReflexCoefZl();
		Line lzc = doubleStub.getD2loadprime().getZlCompLine();
		Line ly = doubleStub.getD2loadprime().getLineReflexCoefYl();
		Line lyc = doubleStub.getD2loadprime().getYlCompLine();

		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().removeAll(lz, lzc);
		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().removeAll(ly, lyc);
		}
	}

	private void step16() {
		System.out.println("step 16");
		setGrayCircles();

		Circle cr = doubleStub.getLoad11().getReflexCoefConst();
		Arc ar = doubleStub.getLoadD2primeArc();
		mainGroup.getChildren().remove(cr);
		mainGroup.getChildren().add(ar);

		double xl = SmithCircle.round(doubleStub.getD2load().getXlNormalized());
		double rl = SmithCircle.round(doubleStub.getD2load().getRlNormalized());
		double gl = SmithCircle.round(doubleStub.getD2load().getGlNormalized());
		double bl = SmithCircle.round(doubleStub.getD2load().getBlNormalized());

		doubleStub.getD2load().getZlMark().setText("zd2' (" + rl + " , " + xl + ")");
		doubleStub.getD2load().getYlMark().setText("yd2' (" + gl + " , " + bl + ")");

		Circle zld1 = doubleStub.getD2load().getZl();
		Label d1lz = doubleStub.getD2load().getZlMark();

		Circle yld1 = doubleStub.getD2load().getYl();
		Label d1ly = doubleStub.getD2load().getYlMark();

		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().addAll(zld1, d1lz);
		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().addAll(yld1, d1ly);
		}

		step27.setText(configureText(doubleStub.getD2load()));
		step22.setFont(FONT);
		step23.setFont(FONT);
		step24.setFont(FONT);
		step25.setFont(FONT);
		step26.setFont(FONT);
		step27.setFont(FONT_BOLD);
	}

	private void step17() {
		System.out.println("step 17");
		setGrayCircles();

		String angleCoef = "Angulo Coeficiente Reflexion = ";
		String lambda = "Longitud de Onda = ";
		double ro = SmithCircle.round(doubleStub.getD2load().getRo());
		double swr = SmithCircle.round(doubleStub.getD2load().getSwr());
		String sro = "ro = " + ro;
		String stwr = "SWR = " + swr;

		Circle r = doubleStub.getD2load().getResCircle();
		Arc x = doubleStub.getD2load().getReactCircle();
		Circle lzst = doubleStub.getD2load().getLambdaZStroke();
		Label lzlb = doubleStub.getD2load().getLambdaZMark();
		Line lnz = doubleStub.getD2load().getLineReflexCoefZl();
		Line lncz = doubleStub.getD2load().getZlCompLine();

		Circle g = doubleStub.getD2load().getConCircle();
		Arc b = doubleStub.getD2load().getSuscepCircle();
		Circle lyst = doubleStub.getD2load().getLambdaYStroke();
		Label lylb = doubleStub.getD2load().getLambdaYMark();
		Line lny = doubleStub.getD2load().getLineReflexCoefYl();
		Line lncy = doubleStub.getD2load().getYlCompLine();

		if (stubType1.equals("Serie")) {
			angleCoef = angleCoef + SmithCircle.round(doubleStub.getD2load().getAngleZl());
			lambda = lambda + SmithCircle.round(doubleStub.getD2load().getLambdaZl());
			mainGroup.getChildren().addAll(r, x, lzst, lzlb, lnz, lncz);

		} else if (stubType1.equals("Paralelo")) {
			angleCoef = angleCoef + SmithCircle.round(doubleStub.getD2load().getAngleYl());
			lambda = lambda + SmithCircle.round(doubleStub.getD2load().getLambdaYl());
			mainGroup.getChildren().addAll(g, b, lyst, lylb, lny, lncy);
		}
		step28.setText(lambda);
		step29.setText(angleCoef);
		step30.setText(sro);
		step31.setText(stwr);

		step27.setFont(FONT);
		step28.setFont(FONT_BOLD);
		step29.setFont(FONT_BOLD);
		step30.setFont(FONT_BOLD);
		step31.setFont(FONT_BOLD);
	}

	private void removeStep1() {
		System.out.println("step 1");

		Circle r = doubleStub.getZload().getResCircle();
		Arc x = doubleStub.getZload().getReactCircle();
		Circle lzst = doubleStub.getZload().getLambdaZStroke();
		Label lzlb = doubleStub.getZload().getLambdaZMark();
		Line lnz = doubleStub.getZload().getLineReflexCoefZl();
		Line lncz = doubleStub.getZload().getZlCompLine();

		Circle g = doubleStub.getZload().getConCircle();
		Arc b = doubleStub.getZload().getSuscepCircle();
		Circle lyst = doubleStub.getZload().getLambdaYStroke();
		Label lylb = doubleStub.getZload().getLambdaYMark();
		Line lny = doubleStub.getZload().getLineReflexCoefYl();
		Line lncy = doubleStub.getZload().getYlCompLine();

		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().removeAll(r, x, lzst, lzlb, lnz, lncz);

		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().removeAll(g, b, lyst, lylb, lny, lncy);
		}
		step2.setText("");
		step3.setText("");
		step4.setText("");
		step5.setText("");
	}

	private void removeStep2() {
		System.out.println("step 2");

		Circle lzst = doubleStub.getZload().getLambdaZStroke();
		Label lzlb = doubleStub.getZload().getLambdaZMark();
		Line lnz = doubleStub.getZload().getLineReflexCoefZl();
		Line lncz = doubleStub.getZload().getZlCompLine();

		Circle lyst = doubleStub.getZload().getLambdaYStroke();
		Label lylb = doubleStub.getZload().getLambdaYMark();
		Line lny = doubleStub.getZload().getLineReflexCoefYl();
		Line lncy = doubleStub.getZload().getYlCompLine();

		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().removeAll(lzst, lzlb, lnz, lncz);

		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().removeAll(lyst, lylb, lny, lncy);
		}

	}

	private void removeStep3() {
		System.out.println("step 3");

		Arc ar = doubleStub.getLoadD1Arc();
		mainGroup.getChildren().remove(ar);

		double d1xl = SmithCircle.round(doubleStub.getD1load().getXlNormalized());
		double d1rl = SmithCircle.round(doubleStub.getD1load().getRlNormalized());
		double d1gl = SmithCircle.round(doubleStub.getD1load().getGlNormalized());
		double d1bl = SmithCircle.round(doubleStub.getD1load().getBlNormalized());

		doubleStub.getD1load().getZlMark().setText("zd1 (" + d1rl + " , " + d1xl + ")");
		doubleStub.getD1load().getYlMark().setText("yd1 (" + d1gl + " , " + d1bl + ")");

		Circle zld1 = doubleStub.getD1load().getZl();
		Label d1lz = doubleStub.getD1load().getZlMark();

		Circle yld1 = doubleStub.getD1load().getYl();
		Label d1ly = doubleStub.getD1load().getYlMark();

		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().removeAll(zld1, d1lz);
		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().removeAll(yld1, d1ly);
		}

		step6.setText("");
	}

	private void removeStep4() {
		System.out.println("step 4");

		Circle r = doubleStub.getD1load().getResCircle();
		Arc x = doubleStub.getD1load().getReactCircle();
		Circle lzst = doubleStub.getD1load().getLambdaZStroke();
		Label lzlb = doubleStub.getD1load().getLambdaZMark();
		Line lnz = doubleStub.getD1load().getLineReflexCoefZl();
		Line lncz = doubleStub.getD1load().getZlCompLine();

		Circle g = doubleStub.getD1load().getConCircle();
		Arc b = doubleStub.getD1load().getSuscepCircle();
		Circle lyst = doubleStub.getD1load().getLambdaYStroke();
		Label lylb = doubleStub.getD1load().getLambdaYMark();
		Line lny = doubleStub.getD1load().getLineReflexCoefYl();
		Line lncy = doubleStub.getD1load().getYlCompLine();

		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().removeAll(r, x, lzst, lzlb, lnz, lncz);

		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().removeAll(g, b, lyst, lylb, lny, lncy);
		}

		step7.setText("");
		step8.setText("");
		step9.setText("");
		step10.setText("");
	}

	private void removeStep5() {
		System.out.println("step 5");

		Circle crt = doubleStub.getUnitRotated();
		mainGroup.getChildren().remove(crt);
	}

	private void removeStep6() {
		System.out.println("step 6");
		setGrayCircles();

		Circle cf = doubleStub.getForbiddenCircle();
		mainGroup.getChildren().remove(cf);

		step11.setText("");
	}

	private void removeStep7() {
		System.out.println("step 7");

		Circle zld1 = doubleStub.getLoad11prime().getZl();
		Label d1lz = doubleStub.getLoad11prime().getZlMark();

		Circle yld1 = doubleStub.getLoad11prime().getYl();
		Label d1ly = doubleStub.getLoad11prime().getYlMark();

		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().removeAll(zld1, d1lz);
		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().removeAll(yld1, d1ly);
		}

		step12.setText("");
	}

	private void removeStep8() {
		System.out.println("step 8");

		Circle r = doubleStub.getLoad11prime().getResCircle();
		Arc x = doubleStub.getLoad11prime().getReactCircle();
		Circle lzst = doubleStub.getLoad11prime().getLambdaZStroke();
		Label lzlb = doubleStub.getLoad11prime().getLambdaZMark();
		Line lnz = doubleStub.getLoad11prime().getLineReflexCoefZl();
		Line lncz = doubleStub.getLoad11prime().getZlCompLine();

		Circle g = doubleStub.getLoad11prime().getConCircle();
		Arc b = doubleStub.getLoad11prime().getSuscepCircle();
		Circle lyst = doubleStub.getLoad11prime().getLambdaYStroke();
		Label lylb = doubleStub.getLoad11prime().getLambdaYMark();
		Line lny = doubleStub.getLoad11prime().getLineReflexCoefYl();
		Line lncy = doubleStub.getLoad11prime().getYlCompLine();

		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().removeAll(r, x, lzst, lzlb, lnz, lncz);

		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().removeAll(g, b, lyst, lylb, lny, lncy);
		}
		step13.setText("");
		step14.setText("");
		step15.setText("");
		step16.setText("");
	}

	private void removeStep9() {
		System.out.println("step 9");

		Circle zld1 = doubleStub.getLoad11().getZl();
		Label d1lz = doubleStub.getLoad11().getZlMark();

		Circle yld1 = doubleStub.getLoad11().getYl();
		Label d1ly = doubleStub.getLoad11().getYlMark();

		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().removeAll(zld1, d1lz);
		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().removeAll(yld1, d1ly);
		}
		step17.setText("");
	}

	private void removeStep10() {
		System.out.println("step 10");

		Circle r = doubleStub.getLoad11().getResCircle();
		Arc x = doubleStub.getLoad11().getReactCircle();
		Circle lzst = doubleStub.getLoad11().getLambdaZStroke();
		Label lzlb = doubleStub.getLoad11().getLambdaZMark();
		Line lnz = doubleStub.getLoad11().getLineReflexCoefZl();
		Line lncz = doubleStub.getLoad11().getZlCompLine();

		Circle g = doubleStub.getLoad11().getConCircle();
		Arc b = doubleStub.getLoad11().getSuscepCircle();
		Circle lyst = doubleStub.getLoad11().getLambdaYStroke();
		Label lylb = doubleStub.getLoad11().getLambdaYMark();
		Line lny = doubleStub.getLoad11().getLineReflexCoefYl();
		Line lncy = doubleStub.getLoad11().getYlCompLine();

		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().removeAll(r, x, lzst, lzlb, lnz, lncz);

		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().removeAll(g, b, lyst, lylb, lny, lncy);
		}
		step18.setText("");
		step19.setText("");
		step20.setText("");
		step21.setText("");
	}

	private void removeStep11() {
		System.out.println("step 11");

		Circle un = doubleStub.getZload().getUnit();
		mainGroup.getChildren().removeAll(un);
	}

	private void removeStep12() {
		System.out.println("Step 12");

		Circle cr = doubleStub.getLoad11prime().getReflexCoefConst();
		mainGroup.getChildren().removeAll(cr);
	}

	private void removeStep13() {
		System.out.println("step 13");

		Arc ar = doubleStub.getLoadD2Arc();
		mainGroup.getChildren().remove(ar);

		Circle zld1 = doubleStub.getD2loadprime().getZl();
		Label d1lz = doubleStub.getD2loadprime().getZlMark();

		Circle yld1 = doubleStub.getD2loadprime().getYl();
		Label d1ly = doubleStub.getD2loadprime().getYlMark();

		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().removeAll(zld1, d1lz);
		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().removeAll(yld1, d1ly);
		}

		step22.setText("");
	}

	private void removeStep14() {
		System.out.println("step 14");
		setGrayCircles();

		Circle r = doubleStub.getD2loadprime().getResCircle();
		Arc x = doubleStub.getD2loadprime().getReactCircle();
		Circle lzst = doubleStub.getD2loadprime().getLambdaZStroke();
		Label lzlb = doubleStub.getD2loadprime().getLambdaZMark();
		Line lnz = doubleStub.getD2loadprime().getLineReflexCoefZl();
		Line lncz = doubleStub.getD2loadprime().getZlCompLine();

		Circle g = doubleStub.getD2loadprime().getConCircle();
		Arc b = doubleStub.getD2loadprime().getSuscepCircle();
		Circle lyst = doubleStub.getD2loadprime().getLambdaYStroke();
		Label lylb = doubleStub.getD2loadprime().getLambdaYMark();
		Line lny = doubleStub.getD2loadprime().getLineReflexCoefYl();
		Line lncy = doubleStub.getD2loadprime().getYlCompLine();

		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().removeAll(r, x, lzst, lzlb, lnz, lncz);

		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().removeAll(g, b, lyst, lylb, lny, lncy);
		}
		step23.setText("");
		step24.setText("");
		step25.setText("");
		step26.setText("");
	}
	
	private void removeStep15() {
		System.out.println("step 15");

		Circle ur = doubleStub.getZload().getUnit();
		Circle cr = doubleStub.getLoad11().getReflexCoefConst();
		mainGroup.getChildren().removeAll(cr, ur);
	}

	private void removeStep16() {
		System.out.println("step 16");
	
		Arc ar = doubleStub.getLoadD2primeArc();
		mainGroup.getChildren().remove(ar);
	
		Circle zld1 = doubleStub.getD2load().getZl();
		Label d1lz = doubleStub.getD2load().getZlMark();
	
		Circle yld1 = doubleStub.getD2load().getYl();
		Label d1ly = doubleStub.getD2load().getYlMark();
	
		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().removeAll(zld1, d1lz);
		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().removeAll(yld1, d1ly);
		}
	
		step27.setText("");
	}
	
	private void removeStep17() {
		System.out.println("step 17");

		Circle r = doubleStub.getD2load().getResCircle();
		Arc x = doubleStub.getD2load().getReactCircle();
		Circle lzst = doubleStub.getD2load().getLambdaZStroke();
		Label lzlb = doubleStub.getD2load().getLambdaZMark();
		Line lnz = doubleStub.getD2load().getLineReflexCoefZl();
		Line lncz = doubleStub.getD2load().getZlCompLine();

		Circle g = doubleStub.getD2load().getConCircle();
		Arc b = doubleStub.getD2load().getSuscepCircle();
		Circle lyst = doubleStub.getD2load().getLambdaYStroke();
		Label lylb = doubleStub.getD2load().getLambdaYMark();
		Line lny = doubleStub.getD2load().getLineReflexCoefYl();
		Line lncy = doubleStub.getD2load().getYlCompLine();

		if (stubType1.equals("Serie")) {
			mainGroup.getChildren().removeAll(r, x, lzst, lzlb, lnz, lncz);

		} else if (stubType1.equals("Paralelo")) {
			mainGroup.getChildren().removeAll(g, b, lyst, lylb, lny, lncy);
		}
		step28.setText("");
		step29.setText("");
		step30.setText("");
		step31.setText("");
	}

	private void setGrayCircles() {
		@SuppressWarnings("unused")
		int count = 0;
		for (Node index : mainGroup.getChildren()) {
			if (index instanceof Circle) {
				((Circle) index).setStroke(Color.DARKGRAY);
				double r = ((Circle) index).getRadius();
				if (r == 5)
					((Circle) index).setFill(Color.DARKGRAY);
				count++;
			}

			if (index instanceof Arc) {
				((Arc) index).setStroke(Color.DARKGRAY);
				count++;
			}
		}
		// System.out.println("total circles : " + count);
	}
	
	private String configureText(SmithCircle load) {
		double rl = SmithCircle.round(load.getRlNormalized());
		double xl = SmithCircle.round(load.getXlNormalized());
		double gl = SmithCircle.round(load.getGlNormalized());
		double bl = SmithCircle.round(load.getBlNormalized());

		int index = load.getZlMark().getText().indexOf("(") - 1;
		String zl = load.getZlMark().getText().substring(0, index);
		String yl = load.getYlMark().getText().substring(0, index);

		String xll = (xl > 0) ? " + j" + Math.abs(SmithCircle.round(xl)) : " - j" + Math.abs(SmithCircle.round(xl));
		String bll = (xl > 0) ? " - j" + Math.abs(SmithCircle.round(bl)) : " + j" + Math.abs(SmithCircle.round(bl));
		String str = (stubType1.equals("Serie")) ? zl + " = " + rl + xll : yl + " = " + gl + bll;
		return str;
	}

	public void setStubType1(String stubType1) {
		this.stubType1 = stubType1;
	}

	public void setStubType2(String stubType2) {
		this.stubType2 = stubType2;
	}
}
