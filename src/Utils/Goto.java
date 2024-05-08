package Utils;

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import pane.GamePane;
import pane.RootPane;

public class Goto {
    private static RootPane rootPane;

    public static void setRootPane(RootPane rootPane) {
        Goto.rootPane = rootPane;
    }

    public static void clear() {
        if (rootPane.getChildren().size() <= 1) {
            return;
        }
        rootPane.getChildren().remove(1, rootPane.getChildren().size());
    }

    public static void GamePage() {
        clear();
        GamePane gamePane = new GamePane();
        rootPane.getChildren().addAll(gamePane);
        rootPane.setAlignment(gamePane, Pos.CENTER);
    }

    public static void gamepage() {

    }
}
