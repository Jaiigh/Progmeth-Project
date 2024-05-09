package entities;

import pane.GamePane;
import pane.RootPane;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static Utils.Constant.Directions.DOWN;
import static Utils.Constant.Directions.UP;

public abstract class Entity {
    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;
    protected int aniTick, aniIndex;
    protected int state;
    protected float airSpeed;
    protected boolean inAir = false;
    protected int maxHealth;
    protected int currentHealth;
    protected Rectangle2D.Float attackBox;
    protected float walkSpeed;

    protected int pushBackDir;
    protected float pushDrawOffset;
    protected int pushBackOffsetDir = UP;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void updatePushBackDrawOffset() {
        float speed = 0.93f;
        float limit = -30f;

        if (pushBackOffsetDir == UP) {
            pushDrawOffset -= speed;
            if (pushDrawOffset <= limit) {
                pushBackOffsetDir = DOWN;
            } else {
                pushDrawOffset += speed;
                if (pushDrawOffset >= 0) {
                    pushDrawOffset = 0;
                }
            }
        }
    }

    protected void drawAttackBox(Graphics g, int xLvlOffset) {
        g.setColor(Color.red);
        g.drawRect((int) (attackBox.x - xLvlOffset), (int) attackBox.y, (int) attackBox.width, (int) attackBox.height
        );
    }

    protected void drawHitBox(Graphics g, int xLvlOffset) {
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x,y,(int) (width * GamePane.SCALE), (int) (height * GamePane.SCALE));
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public int getState() {
        return state;
    }

    public int getAniIndex() {
        return aniIndex;
    }

    protected void newState(int state) {
        this.state = state;
        aniTick = 0;
        aniIndex = 0;
    }
}
