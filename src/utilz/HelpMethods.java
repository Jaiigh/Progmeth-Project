package utilz;

import javafx.geometry.Rectangle2D;
import main.Game;

public class HelpMethods {
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        if (!IsSolid(x, y, lvlData)) {
            if (!IsSolid(x+width, y+height, lvlData)) {
                if(!IsSolid(x+width, y, lvlData)) {
                    if(!IsSolid(x, y+height, lvlData)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean IsSolid(float x, float y, int[][] lvlData) {
        if (x < 0 || x >= Game.GAME_WIDTH) {
            return true;
        }
        if (y < 0 || y >= Game.GAME_HEIGHT) {
            return true;
        }

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        if (value != 11) { // value >= 48 || value < 0 || value != 11
            return true;
        }
        return false;
    }

    public static float GetEntityXPosNextToWall(Rectangle2D hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.getMinX() / Game.TILES_SIZE);
        if (xSpeed > 0) {
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int) (Game.TILES_SIZE - hitbox.getWidth());
            return tileXPos + xOffset - 1;
        } else {
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.getMinY() / Game.TILES_SIZE);
        if (airSpeed > 0) {
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitbox.getHeight());
            return tileYPos + yOffset - 1;
        } else {
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static boolean IsEntityOnFloor(Rectangle2D hitbox, int[][] lvlData) {
        if (!IsSolid((float) hitbox.getMinX(), (float) (hitbox.getMinY() + hitbox.getHeight() + 1), lvlData)) {
            if (!IsSolid((float) (hitbox.getMinX() + hitbox.getWidth()), (float) (hitbox.getMinY() + hitbox.getHeight() + 1), lvlData)) {
                return false;
            }
        }
        return true;
    }
}
