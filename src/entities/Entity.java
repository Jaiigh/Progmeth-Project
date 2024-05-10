package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Entity {

    protected float x, y;
    protected int width, height;
    protected Rectangle2D hitbox;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void drawHitbox(GraphicsContext gc) {
        // for debugging the hitbox
        gc.setStroke(Color.PINK);
        gc.strokeRect((int) hitbox.getMinX(), (int) hitbox.getMinY(), (int) hitbox.getWidth(), (int) hitbox.getHeight());
    }

    protected void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D(x, y, width, height);
    }

//    protected void updateHitbox() {
//        hitbox.setX((int) x);
//        hitbox.setY((int) y);
//    }

    public Rectangle2D getHitbox() {
        return hitbox;
    }

}