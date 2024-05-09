package pane;

import Utils.Goto;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MenuPane extends VBox {
    public MenuPane() {
        this.setMinSize(1280, 800);
        this.setPrefSize(1280, 800);
        this.setMaxSize(1280, 800);
        Button start = new Button("start");
        start.setOnAction(e -> {
            Goto.GamePage();
        });
        Button bruh = new Button("bruh");
        this.getChildren().addAll(start, bruh);
    }
}
