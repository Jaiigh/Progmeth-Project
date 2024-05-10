package utilz;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import main.Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {

    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";
    public static final String MENU_BUTTONS = "button_atlas.png";
    public static final String MENU_BACKGROUND = "menu_background.png";
    public static final String PAUSE_BACKGROUND = "pause_menu.png";
    public static final String SOUND_BUTTONS = "sound_button.png";
    public static final String URM_BUTTONS = "urm_buttons.png";
    public static final String VOLUME_BUTTONS = "volume_buttons.png";
    public static final String MENU_BACKGROUND_IMG = "background_menu.png";
    public static final String PLAYING_BG_IMG = "playing_bg_img.png";
    public static final String BIG_CLOUDS = "big_clouds.png";
    public static final String SMALL_CLOUDS = "small_clouds.png";
    public static final String CRABBY_SPRITE = "crabby_sprite.png";
    public static final String STATUS_BAR = "health_power_bar.png";
    public static final String COMPLETED_IMG = "completed_sprite.png";
    public static final String POTION_ATLAS = "potions_sprites.png";
    public static final String CONTAINER_ATLAS = "objects_sprites.png";
    public static final String TRAP_ATLAS = "trap_atlas.png";
    public static final String CANNON_ATLAS = "cannon_atlas.png";
    public static final String CANNON_BALL = "ball.png";
    public static final String DEATH_SCREEN = "death_screen.png";
    public static final String OPTIONS_MENU = "options_background.png";
    public static final String PINKSTAR_ATLAS = "pinkstar_atlas.png";
    public static final String QUESTION_ATLAS = "question_atlas.png";
    public static final String EXCLAMATION_ATLAS = "exclamation_atlas.png";
    public static final String SHARK_ATLAS = "shark_atlas.png";
    public static final String CREDITS = "credits_list.png";
    public static final String GRASS_ATLAS = "grass_atlas.png";
    public static final String TREE_ONE_ATLAS = "tree_one_atlas.png";
    public static final String TREE_TWO_ATLAS = "tree_two_atlas.png";
    public static final String GAME_COMPLETED = "game_completed.png";
    public static final String RAIN_PARTICLE = "rain_particle.png";
    public static final String WATER_TOP = "water_atlas_animation.png";
    public static final String WATER_BOTTOM = "water.png";
    public static final String SHIP = "ship.png";

    public static Image GetSpriteAtlas(String pictureName) {
        Image img = null;
        try {
            InputStream is = LoadSave.class.getResourceAsStream("/" + pictureName);
            if (is != null) {
                // Read the image using ImageIO
                BufferedImage awtImage = ImageIO.read(is);
                // Convert the AWT image to JavaFX image
                img = SwingFXUtils.toFXImage(awtImage, null);
                System.out.println("load successful");
            } else {
                // Handle case where resource stream is null
                System.err.println("Resource not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

//    public static BufferedImage[] GetAllLevels() {
//        URL url = LoadSave.class.getResource("/lvls");
//        File file = null;
//
//        try {
//            file = new File(url.toURI());
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//
//        File[] files = file.listFiles();
//        File[] filesSorted = new File[files.length];
//
//        for (int i = 0; i < filesSorted.length; i++)
//            for (int j = 0; j < files.length; j++) {
//                if (files[j].getName().equals((i + 1) + ".png"))
//                    filesSorted[i] = files[j];
//
//            }
//
//        BufferedImage[] imgs = new BufferedImage[filesSorted.length];
//
//        for (int i = 0; i < imgs.length; i++)
//            try {
//                imgs[i] = ImageIO.read(filesSorted[i]);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        return imgs;
//    }

    public static int[][] GetLevelData() {
        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        Image img = GetSpriteAtlas(LEVEL_ONE_DATA);

//        for (int j = 0; j < img.getHeight(); j++) {
//            for (int i = 0; i < img.getWidth(); i++) {
//                Color color = img.getPixelReader().getColor(i, j);
//                int value = (int) color.getRed();
//                if (value >= 48) {
//                    value = 0;
//                }
//                lvlData[j][i] = value;
//            }
//        }
        int imageWidth = (int) img.getWidth();
        int imageHeight = (int) img.getHeight();

        PixelReader pixelReader = img.getPixelReader();

        for (int j = 0; j < imageHeight && j < Game.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < imageWidth && i < Game.TILES_IN_WIDTH; i++) {
                Color color = pixelReader.getColor(i, j);
                int value = (int) (color.getRed() * 255); // Scale to 0-255 range
                if (value >= 48) {
                    value = 0;
                }
                lvlData[j][i] = value;
            }
        }

        return lvlData;
    }

}