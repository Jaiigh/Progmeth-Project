package pane;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

public class GamePane extends Pane {
    private double xPo = 100, yPo = 100;
    private int frames = 0;
    private long lastCheck = System.currentTimeMillis();
    private long lastFrame = System.nanoTime();
    public GamePane() {
        this.setMinSize(1280, 800);
        this.setPrefSize(1280, 800);
        this.setMaxSize(1280, 800);
        this.setFocusTraversable(true);

        ImageView imageView = new ImageView("player_sprites.png");
        imageView.setViewport(new Rectangle2D(1*64, 4*80, 64, 40));
        imageView.setLayoutX(xPo);
        imageView.setLayoutY(yPo);
        imageView.setScaleX(2);
        imageView.setScaleY(2);
        this.getChildren().add(imageView);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                GameLoop(now);
            }
        }.start();

//        this.setOnKeyPressed(e -> {
//            switch (e.getCode()) {
//                case KeyCode.W:
//                    this.yPo += -5;
//                    break;
//                case KeyCode.A:
//                    this.xPo += -5;
//                    break;
//                case KeyCode.S:
//                    this.yPo += 5;
//                    break;
//                case KeyCode.D:
//                    this.xPo += 5;
//                    break;
//            }
//        });

        this.setOnMouseMoved(e -> {
            imageView.setLayoutX(e.getX());
            imageView.setLayoutY(e.getY());
        });
    }

    private void GameLoop(long now) {
        double elapsedTime = (now - lastFrame) / 1_000_000_000.0;

        if (elapsedTime >= 1.0 / 120) {
            //update class in game loop
//            updateObject();
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
