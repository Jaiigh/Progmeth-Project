package utilz;

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
        Game game = new Game();
        GamePane gamePane = new GamePane(game);
        Game.initClasses(game);
        Game.startGameLoop(gamePane);
        rootPane.getChildren().addAll(gamePane);
    }
}