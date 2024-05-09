package Utils;

import javafx.animation.AnimationTimer;
import pane.GamePane;

public class GameLoop {
    private static final int FPS_SET = 120;
    private static final int UPS_SET = 200;
    private static long previousTime = System.nanoTime();
    private static int frames = 0;
    private static int updates = 0;
    private static long lastCheck = System.currentTimeMillis();
    private static double deltaU = 0;
    private static double deltaF = 0;

    public static void update(GamePane gp) {
        gp.UpdateGame();
    }

    private static void runFps(GamePane gp) {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long currentTime = System.nanoTime();
        deltaU += (currentTime - previousTime) / timePerUpdate;
        deltaF += (currentTime - previousTime) / timePerFrame;
        previousTime = currentTime;

        if (deltaU >= 1) {
            update(gp);
            updates++;
            deltaU--;
        }
        if (deltaF >= 1) {
            update(gp);
            frames++;
            deltaF--;
        }

        if (System.currentTimeMillis() - lastCheck >= 1000) {
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS: " + frames + " | UPS: " + updates);
            frames = 0;
            updates = 0;
        }
    }

    public static void startGameLoop(GamePane pane) {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                runFps(pane);
            }
        };
        gameLoop.start();
    }
}
