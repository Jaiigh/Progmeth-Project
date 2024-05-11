package inputs;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pane.GamePane;

//public class KeyboardInputs {
//    private GamePane gamePane;
//
//    public KeyboardInputs(GamePane gamePanel) {
//        this.gamePane = gamePanel;
//
//        // Set up key pressed event handler
//        gamePanel.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                handleKeyPressed(event);
//            }
//        });
//
//        // Set up key released event handler
//        gamePanel.setOnKeyReleased(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                handleKeyReleased(event);
//            }
//        });
//    }
//
//    private void handleKeyPressed(KeyEvent event) {
//        KeyCode keyCode = event.getCode();
//        switch (keyCode) {
//            case W:
//                gamePane.getGame().getPlayer().setUp(true);
//                break;
//            case A:
//                gamePane.getGame().getPlayer().setLeft(true);
//                break;
//            case S:
//                gamePane.getGame().getPlayer().setDown(true);
//                break;
//            case D:
//                gamePane.getGame().getPlayer().setRight(true);
//                break;
//            case SPACE:
//                gamePane.getGame().getPlayer().setJump(true);
//                break;
//        }
//    }
//
//    private void handleKeyReleased(KeyEvent event) {
//        KeyCode keyCode = event.getCode();
//        switch (keyCode) {
//            case W:
//                gamePane.getGame().getPlayer().setUp(false);
//                break;
//            case A:
//                gamePane.getGame().getPlayer().setLeft(false);
//                break;
//            case S:
//                gamePane.getGame().getPlayer().setDown(false);
//                break;
//            case D:
//                gamePane.getGame().getPlayer().setRight(false);
//                break;
//            case SPACE:
//                gamePane.getGame().getPlayer().setJump(false);
//                break;
//        }
//    }
//}


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
                case KeyCode.SPACE:
                    gp.getGame().getPlayer().setJump(true);
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
                case KeyCode.SPACE:
                    gp.getGame().getPlayer().setJump(false);
            }
        });
    }
}