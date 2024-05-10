package pane;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import main.Game;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePane extends Pane {
    private Game game;
    private Canvas canvas;
    private GraphicsContext gc;
    public GamePane(Game game) {
        this.game = game;
        this.setMinSize(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        this.setPrefSize(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        this.setMaxSize(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        this.setFocusTraversable(true);

        canvas = new Canvas(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);

        new KeyboardInputs(this);
        new MouseInputs(this);
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        // Resize canvas to match the size of the pane
        double width = getWidth();
        double height = getHeight();
        if (canvas.getWidth() != width || canvas.getHeight() != height) {
            canvas.setWidth(width);
            canvas.setHeight(height);
            // Redraw the game when the canvas size changes
//            game.redraw(gc);
        }
    }

    public void updateGame() {

    }

    public Game getGame() {
        return game;
    }
    public GraphicsContext getGraphicsContext() {
        return gc;
    }
}