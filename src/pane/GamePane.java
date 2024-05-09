package pane;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import static Utils.Constant.PlayerConstants.*;
import static Utils.Constant.Directions.*;

public class GamePane extends Pane {
    private double xPo = 100, yPo = 100;
    private int frames = 0;
    private long lastCheck = System.currentTimeMillis();
    private long lastFrame = System.nanoTime();
    private Image img = new Image("player_sprites.png");
    private ImageView[][] animations;
    private ImageView playerImg = new ImageView();
    private int aniTick, aniIndex, aniSpeed = 8;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean moving = false;
    public GamePane() {
        this.setMinSize(1280, 800);
        this.setPrefSize(1280, 800);
        this.setMaxSize(1280, 800);
        this.setFocusTraversable(true);

        getAnimation();
        getImage();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                GameLoop(now);
            }
        }.start();

        this.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case KeyCode.W:
                    this.setPlayerDirection(UP);
                    break;
                case KeyCode.A:
                    this.setPlayerDirection(LEFT);
                    break;
                case KeyCode.S:
                    this.setPlayerDirection(DOWN);
                    break;
                case KeyCode.D:
                    this.setPlayerDirection(RIGHT);
                    break;
            }
        });
        this.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case KeyCode.W:
                    this.setMoving(false);
                    break;
                case KeyCode.A:
                    this.setMoving(false);
                    break;
                case KeyCode.S:
                    this.setMoving(false);
                    break;
                case KeyCode.D:
                    this.setMoving(false);
                    break;
            }
        });

//        this.setOnMouseMoved(e -> {
//            xPo = e.getX();
//            yPo = e.getY();
//        });
    }

    private void getImage() {
        if (this.getChildren().contains(playerImg)) {
            this.getChildren().remove(playerImg);
        }
        playerImg = animations[playerAction][aniIndex];
        playerImg.setLayoutX(xPo);
        playerImg.setLayoutY(yPo);
        playerImg.setScaleX(4);
        playerImg.setScaleY(4);
        this.getChildren().add(playerImg);
    }

    private void getAnimation() {
        animations = new ImageView[9][6];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = new ImageView(img);
                animations[j][i].setViewport(new Rectangle2D(i*64, j*40, 64, 40));
            }
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }

    public void setPlayerDirection(int direction) {
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void setAnimation() {
        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
    }

    private void updatePo() {
        if (moving) {
            switch (playerDirection) {
                case LEFT:
                    xPo += -5;
                    break;
                case UP:
                    yPo += -5;
                    break;
                case RIGHT:
                    xPo += 5;
                    break;
                case DOWN:
                    yPo += 5;
                    break;
            }
        }
    }

    private void GameLoop(long now) {
        double elapsedTime = (now - lastFrame) / 1_000_000_000.0;

        if (elapsedTime >= 1.0 / 120) {
            //update class in game loop
            updateAnimationTick();
            getImage();
            setAnimation();
            updatePo();
            lastFrame = now;
            frames++;
        }

        if (System.currentTimeMillis() - lastCheck >= 1000) {
            System.out.println("FPS: " + frames);
            System.out.println("XPO: " + playerImg.getLayoutX());
            System.out.println("YPO: " + playerImg.getLayoutY());
            frames = 0;
            lastCheck = System.currentTimeMillis();
        }
    }
}