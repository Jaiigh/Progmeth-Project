package pane;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Random;

public class GamePane extends Pane {
    private double xPo = 100, yPo = 100;
    private double xDelta = 2, yDelta = 2;
    private Rectangle rectangle;
    private Random random = new Random();
    private int frames = 0;
    private long lastCheck = System.currentTimeMillis();
    private long lastFrame = System.nanoTime();
    public GamePane() {
        addObject();
        this.setFocusTraversable(true);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                GameLoop(now);
            }
        }.start();

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
        });

        this.setOnMouseClicked(e -> {
            System.out.println("click");
        });

        this.setOnMouseMoved(e -> {
            this.xPo = e.getX();
            this.yPo = e.getY();
        });
    }

    public void addObject() {
        rectangle = new Rectangle(200, 50, Color.rgb(150, 20, 90)); //size and color
        rectangle.setX(xPo);
        rectangle.setY(yPo);
        this.getChildren().add(rectangle);
    }

    private void updateObject() {
        xPo += xDelta;
        if (xPo > 400 || xPo < 0) {
            xDelta *= -1;
            rectangle.setFill(getRngColor());
        }
        yPo += yDelta;
        if (yPo > 400 || yPo < 0) {
            yDelta *= -1;
            rectangle.setFill(getRngColor());
        }
        rectangle.setX(xPo);
        rectangle.setY(yPo);
    }

    public Color getRngColor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return Color.rgb(r, g, b);
    }

    private void GameLoop(long now) {
        double elapsedTime = (now - lastFrame) / 1_000_000_000.0;

        if (elapsedTime >= 1.0 / 120) {
            updateObject();
            lastFrame = now;
            frames++;
        }

        if (System.currentTimeMillis() - lastCheck >= 1000) {
            System.out.println("FPS: " + frames);
            frames = 0;
            lastCheck = System.currentTimeMillis();
        }
    }
}
