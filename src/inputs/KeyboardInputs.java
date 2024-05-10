package inputs;

import javafx.scene.input.KeyCode;
import pane.GamePane;

public class KeyboardInputs {
    public KeyboardInputs(GamePane gp) {
        gp.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case KeyCode.W:
                    gp.getGame().getPlayer().setUp(true);
                    break;
                case KeyCode.A:
                    gp.getGame().getPlayer().setLeft(true);
                    break;
                case KeyCode.S:
                    gp.getGame().getPlayer().setDown(true);
                    break;
                case KeyCode.D:
                    gp.getGame().getPlayer().setRight(true);
                    break;
            }
        });
        gp.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case KeyCode.W:
                    gp.getGame().getPlayer().setUp(false);
                    break;
                case KeyCode.A:
                    gp.getGame().getPlayer().setLeft(false);
                    break;
                case KeyCode.S:
                    gp.getGame().getPlayer().setDown(false);
                    break;
                case KeyCode.D:
                    gp.getGame().getPlayer().setRight(false);
                    break;
            }
        });
    }
}
