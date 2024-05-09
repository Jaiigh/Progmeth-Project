package pane;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GamePane extends Pane {
    private double xPo = 100, yPo = 100;
    public GamePane() {
        addObject();
        this.setFocusTraversable(true);

        this.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case KeyCode.W:
                    this.yPo += -5;
                    break;
                case KeyCode.A:
                    this.xPo += -5;
                    break;
                case KeyCode.S:
                    this.yPo += 5;
                    break;
                case KeyCode.D:
                    this.xPo += 5;
                    break;
            }
            addObject();
        });

        this.setOnMouseClicked(e -> {
            System.out.println("click");
        });

        this.setOnMouseMoved(e -> {
            this.xPo = e.getX();
            this.yPo = e.getY();
            addObject();
        });
    }

    public void addObject() {
        Rectangle rectangle = new Rectangle(200, 50, Color.BLACK); //size and color
        rectangle.setX(xPo);
        rectangle.setY(yPo);
        this.getChildren().clear();
        this.getChildren().add(rectangle);
    }
}
