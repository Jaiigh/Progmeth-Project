package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static utilz.HelpMethods.*;

import main.Game;
import pane.GamePane;
import utilz.LoadSave;
import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.CanMoveHere;

public class Player extends Entity {
    private ImageView[][] animations;
    private ImageView playerImage = new ImageView();
    private int aniTick, aniIndex, aniSpeed = 10;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down, jump;
    private float playerSpeed = 2.0f;
    private int[][] lvlData;
    private float xDrawOffSet = 21 * Game.SCALE;
    private float yDrawOffSet = 4 * Game.SCALE;

    // jumping & gravity
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x, y, 20*Game.SCALE, 28*Game.SCALE);

    }

    public void update(GamePane gp) {
        render(gp.getGraphicsContext());
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(GraphicsContext gc) {
        playerImage = animations[playerAction][aniIndex];
        gc.drawImage(playerImage.getImage(),
                playerImage.getViewport().getMinX(),
                playerImage.getViewport().getMinY(),
                playerImage.getViewport().getWidth(),
                playerImage.getViewport().getHeight(),
                (int) (hitbox.getMinX() - xDrawOffSet),
                (int) (hitbox.getMinY() - yDrawOffSet),
                width, height);
        drawHitbox(gc);
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

        if (inAir) {
            if (airSpeed < 0) {
                playerAction = JUMP;
            } else {
                playerAction = FALLING;
            }
        }

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

        if (jump) {
            jump();
        }
        if (!left && !right && !up && !down) {
            return;
        }

        float xSpeed = 0;

        if (left) {
            xSpeed -= playerSpeed;
        } else if (right) {
            xSpeed += playerSpeed;
        }

        if (!inAir) {
            if (!IsEntityOnFloor(hitbox, lvlData)) {
                inAir = true;
            }
        }

        if (inAir) {
            if (CanMoveHere((float) hitbox.getMinX(), (float) hitbox.getMinY() + airSpeed, (float) hitbox.getWidth() , (float) hitbox.getHeight(), lvlData)) {
                double newY = hitbox.getMinY() + airSpeed;
                hitbox = new Rectangle2D(hitbox.getMinX(), newY, hitbox.getWidth(), hitbox.getHeight());
                airSpeed += gravity;
                updateXPos(xSpeed);


            } else {
                double newY = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);

                hitbox = new Rectangle2D(hitbox.getMinX(), newY, hitbox.getWidth(), hitbox.getHeight());

                if (airSpeed > 0) {
                    resetInAir();
                } else {
                    airSpeed = fallSpeedAfterCollision;
                }

                updateXPos(xSpeed);
            }
        } else {
            updateXPos(xSpeed);
        }

        moving = true;

//        if (CanMoveHere(x+xSpeed, y+ySpeed, width, height, lvlData)) {
//            this.x += xSpeed;
//            this.y += ySpeed;
//            moving = true;
//        }

//        if (CanMoveHere((float) (hitbox.getMinX()+xSpeed), (float) (hitbox.getMinY()+ySpeed), (float) hitbox.getWidth(), (float) hitbox.getHeight(), lvlData)) {
//            double newX = hitbox.getMinX() + xSpeed;
//            double newY = hitbox.getMinY() + ySpeed;
//
//            hitbox = new Rectangle2D(newX, newY, hitbox.getWidth(), hitbox.getHeight());
//            moving = true;
//        }
    }

    private void jump() {
        if (inAir) {
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(Float xSpeed) {
        if (CanMoveHere((float) (hitbox.getMinX()+xSpeed), (float) (hitbox.getMinY()), (float) hitbox.getWidth(), (float) hitbox.getHeight(), lvlData)) {
            double newX = hitbox.getMinX() + xSpeed;
            hitbox = new Rectangle2D(newX, hitbox.getMinY(), hitbox.getWidth(), hitbox.getHeight());
        } else {
            double newX = GetEntityXPosNextToWall(hitbox, xSpeed);
            hitbox = new Rectangle2D(newX, hitbox.getMaxY(), hitbox.getWidth(), hitbox.getHeight());
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

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
        if (!IsEntityOnFloor(hitbox, lvlData)) {
            inAir = true;
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

    public void setJump(boolean jump) {
        this.jump = jump;
    }
}