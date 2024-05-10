package pane;

import inputs.KeyboardInputs;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import main.Game;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePane extends Pane {
    private Game game;
    public GamePane(Game game) {
        this.setMinSize(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        this.setPrefSize(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        this.setMaxSize(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        this.setFocusTraversable(true);
        this.game = game;
        new KeyboardInputs(this);
    }

    public void updateGame() {

    }

    public Game getGame() {
        return game;
    }
}