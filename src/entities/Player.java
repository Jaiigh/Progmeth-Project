package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import main.Game;
import pane.GamePane;
import utilz.LoadSave;
import static utilz.Constants.PlayerConstants.*;

public class Player extends Entity {
    private ImageView[][] animations;
    private ImageView playerImage = new ImageView();
    private int aniTick, aniIndex, aniSpeed = 10;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private float playerSpeed = 2.0f;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
    }

    public void update(GamePane gp) {
        render(gp.getGraphicsContext());
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(GraphicsContext gc) {
//        if (gp.getChildren().contains(playerImage)) {
//            gp.getChildren().remove(playerImage);
//        }
        playerImage = animations[playerAction][aniIndex];
        if (x >= 1150) {
            x = 1150;
        } else if (x <= 0) {
            x = 0;
        }
//        playerImage.setLayoutX(x);
        if (y >= Game.GAME_HEIGHT) {
            y = Game.GAME_HEIGHT;
        } else if (y <= 0) {
            y = 0;
        }
//        playerImage.setLayoutY(y);
//        gp.getChildren().add(playerImage);
//        gc.clearRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        gc.drawImage(playerImage.getImage(),
                playerImage.getViewport().getMinX(),
                playerImage.getViewport().getMinY(),
                playerImage.getViewport().getWidth(),
                playerImage.getViewport().getHeight(),
                x, y, width, height);
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }

        }

    }

    private void setAnimation() {
        int startAni = playerAction;

        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        if (attacking)
            playerAction = ATTACK_1;
        if (startAni != playerAction)
            resetAniTick();
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {
        moving = false;

        if (left && !right) {
            x -= playerSpeed;
            moving = true;
        } else if (right && !left) {
            x += playerSpeed;
            moving = true;
        }

        if (up && !down) {
            y -= playerSpeed;
            moving = true;
        } else if (down && !up) {
            y += playerSpeed;
            moving = true;
        }
    }

    private void loadAnimations() {
        Image img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        animations = new ImageView[9][6];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = new ImageView(img);
                animations[j][i].setViewport(new Rectangle2D(i*64, j*40, 64, 40));
            }
        }
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    //temp
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}