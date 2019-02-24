
package com.willy.smith.chart;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import javafx.scene.transform.Rotate;

public class Dial extends Parent {

    //visual nodes
    private final double radius;
    private final Color FILL_COLOR = Color.web("#0A0A0A");
    private final Font NUMBER_FONT = Font.font("Comic Sans", FontWeight.NORMAL, 14);
    private final Text name = new Text();
    private final Group hand = new Group();
    private final Group handEffectGroup = new Group(hand);
    private final DropShadow handEffect = new DropShadow();
    private int numOfMarks;
    private int numOfMinorMarks;

    public Dial(double radius, boolean hasNumbers, int numOfMarks, int numOfMinorMarks, Color color, boolean hasEffect) {
        this.radius = radius;
        this.numOfMarks = numOfMarks;
        this.numOfMinorMarks = numOfMinorMarks;

        if (hasEffect) {
            configureEffect();
        }
        if (hasNumbers) {
            getChildren().add(createNumbers());
        }
        getChildren().addAll(
                createTickMarks(),
                handEffectGroup);
    }

    public Dial(double radius, boolean hasNumbers, int numOfMarks, int numOfMinorMarks, String name, Color color, boolean hasEffect) {
        this(radius, hasNumbers, numOfMarks, numOfMinorMarks, color, hasEffect);
        configureName(name);
        getChildren().add(this.name);
    }

    private Group createTickMarks() {
        Group group = new Group();

        for (int i = 0; i < numOfMarks; i++) {
            double angle = (360 / numOfMarks) * (i);
            group.getChildren().add(createTic(angle, radius / 15, 1.5));
        }

        for (int i = 0; i < numOfMinorMarks; i++) {
            double angle = (360 / numOfMinorMarks) * i;
            group.getChildren().add(createTic(angle, radius / 30, 1));
        }
        return group;
    }

    private Rectangle createTic(double angle, double width, double height) {
        Rectangle rectangle = new Rectangle(-width / 2, -height / 2, width, height);
        rectangle.setFill(Color.rgb(10, 10, 10));
        rectangle.setRotate(angle);
        rectangle.setLayoutX(radius * Math.cos(Math.toRadians(angle)));
        rectangle.setLayoutY(radius * Math.sin(Math.toRadians(angle)));
        return rectangle;
    }

    private void configureName(String string) {
        Font font = new Font(9);
        name.setText(string);
        name.setBoundsType(TextBoundsType.VISUAL);
        name.setLayoutX(-name.getBoundsInLocal().getWidth() / 2 + 4.8);
        name.setLayoutY(radius * 1 / 2 + 4);
        name.setFill(FILL_COLOR);
        name.setFont(font);
    }

    private Group createNumbers() {
        return new Group(
        		createNumber("0", radius + 10, 5),
                createNumber("90", -4.7, -radius - 10),
                createNumber("180", -radius - 38, 5),
                createNumber("270", -9.5, radius + 16 + 4.5));
    }

    private Text createNumber(String number, double layoutX, double layoutY) {
        Text text = new Text(number);
        text.setLayoutX(layoutX);
        text.setLayoutY(layoutY);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFill(FILL_COLOR);
        text.setFont(NUMBER_FONT);
        return text;
    }

    public void setAngle(double angle) {
        Rotate rotate = new Rotate(angle);
        hand.getTransforms().clear();
        hand.getTransforms().add(rotate);
    }

    private void configureEffect() {
        handEffect.setOffsetX(radius / 40);
        handEffect.setOffsetY(radius / 40);
        handEffect.setRadius(6);
        handEffect.setColor(Color.web("#000000"));

        Lighting lighting = new Lighting();
        Light.Distant light = new Light.Distant();
        light.setAzimuth(225);
        lighting.setLight(light);
        handEffect.setInput(lighting);

        handEffectGroup.setEffect(handEffect);
    }
}