package main;

import entities.Player;
import javafx.animation.AnimationTimer;
import levels.LevelManager;
import pane.GamePane;

public class Game {
    //constant
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    //fps
    private static final int FPS_SET = 120;
    private static final int UPS_SET = 200;
    private static long previousTime = System.nanoTime();
    private static int frames = 0;
    private static int updates = 0;
    private static long lastCheck = System.currentTimeMillis();
    private static double deltaU = 0;
    private static double deltaF = 0;

    //object
    private static Player player;
    private static LevelManager levelManager;

    public static void initClasses(Game instance) {
        player = new Player();
        levelManager = new LevelManager(instance);
    }

    public static void render(GamePane gp) {
        //player.render(g);
        levelManager.draw(gp);
    }

    public static void repaint(GamePane gp) {
        render(gp);
        gp.UpdateGame();
    }

    public static void update() {
        //player.update();
        levelManager.update();
    }

    private static void runFps(GamePane gp) {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long currentTime = System.nanoTime();
        deltaU += (currentTime - previousTime) / timePerUpdate;
        deltaF += (currentTime - previousTime) / timePerFrame;
        previousTime = currentTime;

        if (deltaU >= 1) {
            update();
            updates++;
            deltaU--;
        }
        if (deltaF >= 1) {
            repaint(gp);
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