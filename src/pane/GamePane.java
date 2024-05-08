package pane;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GamePane extends Pane {
    public GamePane() {
        addObject();
    }

    public void addObject() {
        Rectangle rectangle = new Rectangle(200, 50, Color.BLACK);
        rectangle.setX(100);
        rectangle.setY(100);
        this.getChildren().add(rectangle);
    }
}
