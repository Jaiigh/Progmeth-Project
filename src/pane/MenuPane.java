package pane;

import Utils.Goto;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MenuPane extends VBox {
    public MenuPane() {
        Button start = new Button("start");
        start.setOnAction(e -> {
            Goto.GamePage();
        });
        Button bruh = new Button("bruh");
        this.getChildren().addAll(start, bruh);
    }
}
