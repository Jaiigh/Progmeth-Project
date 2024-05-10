package inputs;

import javafx.scene.input.MouseButton;
import pane.GamePane;

public class MouseInputs {
    public MouseInputs(GamePane gp) {
        gp.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                gp.getGame().getPlayer().setAttacking(true);
            }
        });
    }
}
