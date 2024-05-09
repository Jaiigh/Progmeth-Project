package Utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
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
}
