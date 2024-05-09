package Utils;

import main.Game;
import pane.GamePane;
import pane.MenuPane;
import pane.RootPane;

public class Goto {
    private static RootPane rootPane;

    public static void setRootPane(RootPane rootPane) {
        Goto.rootPane = rootPane;
    }

    public static void clear() {
        rootPane.getChildren().clear();
    }

    public static void HomePage() {
        clear();
        MenuPane menuPane = new MenuPane();
        rootPane.getChildren().addAll(menuPane);
    }

    public static void GamePage() {
        clear();
        GamePane gamePane = new GamePane();
        Game game = new Game();
        Game.initClasses(game);
        Game.startGameLoop(gamePane);
        rootPane.getChildren().addAll(gamePane);
    }
}