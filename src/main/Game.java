package main;

import entities.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import levels.LevelManager;
import pane.GamePane;
import utilz.LoadSave;

public class Game {
    private GamePane gp;

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

    public Game() {
        this.gp = new GamePane(this);
        initClasses();
        startGameLoop(gp);
    }

    private void initClasses() {
        levelManager = new LevelManager(this);
        player = new Player(200, 200, (int) (64 * SCALE), (int) (40 * SCALE));
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
    }

    public static void render(GamePane gp) {
//        levelManager.draw(gp);
        gp.getGraphicsContext().clearRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        levelManager.draw(gp.getGraphicsContext());
        player.render(gp.getGraphicsContext());
    }

    public static void repaint(GamePane gp) {
        render(gp);
        gp.updateGame();
    }

    public static void update(GamePane gp) {
        player.update(gp);
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
            update(gp);
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
            System.out.println("PlayerPo: X: " + gp.getGame().getPlayer().getX() + " | Y: " + gp.getGame().getPlayer().getY());
            frames = 0;
            updates = 0;
        }
    }

    private static void startGameLoop(GamePane pane) {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                runFps(pane);
            }
        };
        gameLoop.start();
    }

    public Player getPlayer() {
        return player;
    }

    public GamePane getGamePane() {
        return gp;
    }
}