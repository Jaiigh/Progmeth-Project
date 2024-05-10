package levels;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import utilz.LoadSave;
import main.Game;
import pane.GamePane;

public class LevelManager {
    private Game game;
//    private Image levelSprite;
    private ImageView[] levelSprite;
    private Level levelOne;

    public LevelManager(Game game) {
        this.game = game;
//        levelSprite = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        importOutsideSprites();
        levelOne = new Level(LoadSave.GetLevelData());
    }

    private void importOutsideSprites() {
        Image img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new ImageView[48];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                int index = j*12 + i;
                ImageView imageView = new ImageView(img);
                imageView.setViewport(new Rectangle2D(i*32, j*32, 32, 32));
                levelSprite[index] = imageView;
            }
        }
    }

    public void draw(GraphicsContext gc) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
                int index = levelOne.getSpriteIndex(i, j);
                ImageView imageView = levelSprite[index];
                gc.drawImage(imageView.getImage(),
                        imageView.getViewport().getMinX(),
                        imageView.getViewport().getMinY(),
                        imageView.getViewport().getWidth(),
                        imageView.getViewport().getHeight(),
                        Game.TILES_SIZE*i,
                        Game.TILES_SIZE*j,
                        Game.TILES_SIZE,
                        Game.TILES_SIZE);
            }
        }
    }

    public void update() {
    }

    public Level getCurrentLevel() {
        return levelOne;
    }
}
