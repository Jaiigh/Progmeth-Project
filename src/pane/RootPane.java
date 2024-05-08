package pane;

import Utils.Goto;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class RootPane extends StackPane{

    private static RootPane instance;

    private RootPane() {
        BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, null, null);
        this.setBackground(new Background(backgroundFill));
        this.setAlignment(Pos.CENTER);
        /////////////////////////
        Goto.setRootPane(this);
        // temp
        Goto.GamePage();
    }

    public static RootPane getRootPane() {
        if (instance == null)
            instance = new RootPane();
        return instance;
    }
}
