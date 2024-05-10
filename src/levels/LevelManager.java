package levels;

import utilz.LoadSave;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Game;
import pane.GamePane;

public class LevelManager {
    private Game game;
//    private Image levelSprite;
    private ImageView[] levelSprite;

    public LevelManager(Game game) {
        this.game = game;
//        levelSprite = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        importOutsideSprites();
    }

    private void importOutsideSprites() {
        Image img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new ImageView[48];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                int index = j*12 + i;
                levelSprite[index] = new ImageView(img);
                levelSprite[index].setViewport(new Rectangle2D(i*32, j*32, 32, 32));
            }
        }
    }

    public void draw(GamePane gp) {
        ImageView imageView = levelSprite[2];
        imageView.setLayoutX(0);
        imageView.setLayoutY(0);
        if (gp.getChildren().contains(imageView)) {
            gp.getChildren().remove(imageView);
        }
        gp.getChildren().add(imageView);
    }

    public void update() {
    }
}
