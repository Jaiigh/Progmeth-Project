package pane;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GamePane extends GridPane {
    public GamePane() {
        this.setPrefHeight(200);
        this.setPrefWidth(400);
//        this.getColumnConstraints().add(new ColumnConstraints(100));
//        this.getRowConstraints().add(new RowConstraints(100));
//        this.setAlignment(Pos.CENTER);
        createEmptyPlaceholder();

        Text start = new Text("GameStartGrid");
        start.setFill(Color.WHITE);
        this.add(start, 0, 0);
        GridPane.setHalignment(start, HPos.CENTER);
        GridPane.setValignment(start, VPos.CENTER);

        Text end = new Text("GameEndGrid");
        end.setFill(Color.WHITE);
        this.add(end, 13, 13);
        GridPane.setHalignment(end, HPos.CENTER);
        GridPane.setValignment(end, VPos.CENTER);
    }

    private void createEmptyPlaceholder() {
        for (int row = 0; row < 40; row++) {
            for (int col = 0; col < 20; col++) {
                Region placeholder = new Region();  // Create a new instance of Region for each cell
                placeholder.setPrefSize(10, 10);
                this.add(placeholder, col, row);
            }
        }
    }
}
