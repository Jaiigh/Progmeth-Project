package Utils;

<<<<<<< HEAD
import pane.GamePane;

public class Constant {

    public static final float GRAVITY = 0.04f * GamePane.SCALE;
    public static final int ANI_SPEED = 25;
=======
public class Constant {
>>>>>>> auming
    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int GROUND = 4;
        public static final int HIT = 5;
        public static final int ATTACK_1 = 6;
        public static final int ATTACK_JUMP_1 = 7;
        public static final int ATTACK_JUMP_2 = 8;

        public static int GetSpriteAmount(int player_action) {
            switch(player_action) {
                case RUNNING:
                    return 6;
                case IDLE:
                    return 5;
                case HIT:
                    return 4;
                case JUMP:
                case ATTACK_1:
                case ATTACK_JUMP_1:
                case ATTACK_JUMP_2:
                    return 3;
                case GROUND:
                    return 2;
                case FALLING:
                default:
                    return 1;
            }
        }
    }
<<<<<<< HEAD

    public static class EnemyConstants {
        public static final int CRABBY = 0;
        public static final int PINKSTAR = 1;
        public static final int SHARK = 2;

        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACK = 2;
        public static final int HIT = 3;
        public static final int DEAD = 4;

        public static final int CRABBY_WIDTH_DEFAULT = 72;
        public static final int CRABBY_HEIGHT_DEFAULT = 32;
        public static final int CRABBY_WIDTH = (int) (CRABBY_WIDTH_DEFAULT * 2f);
        public static final int CRABBY_HEIGHT = (int) (CRABBY_HEIGHT_DEFAULT * 2f);
        public static final int CRABBY_DRAWOFFSET_X = (int) (26 * 2f);
        public static final int CRABBY_DRAWOFFSET_Y = (int) (9 * 2f);

        public static final int PINKSTAR_WIDTH_DEFAULT = 34;
        public static final int PINKSTAR_HEIGHT_DEFAULT = 30;
        public static final int PINKSTAR_WIDTH = (int) (PINKSTAR_WIDTH_DEFAULT * 2f);
        public static final int PINKSTAR_HEIGHT = (int) (PINKSTAR_HEIGHT_DEFAULT * 2f);
        public static final int PINKSTAR_DRAWOFFSET_X = (int) (9 * 2f);
        public static final int PINKSTAR_DRAWOFFSET_Y = (int) (7 * 2f);

        public static final int SHARK_WIDTH_DEFAULT = 34;
        public static final int SHARK_HEIGHT_DEFAULT = 30;
        public static final int SHARK_WIDTH = (int) (SHARK_WIDTH_DEFAULT * 2f);
        public static final int SHARK_HEIGHT = (int) (SHARK_HEIGHT_DEFAULT * 2f);
        public static final int SHARK_DRAWOFFSET_X = (int) (8 * 2f);
        public static final int SHARK_DRAWOFFSET_Y = (int) (6 * 2f);

        public static int GetSpriteAmount(int enemy_type, int enemy_state) {
            switch (enemy_state) {

                case IDLE: {
                    if (enemy_type == CRABBY)
                        return 9;
                    else if (enemy_type == PINKSTAR || enemy_type == SHARK)
                        return 8;
                }
                case RUNNING:
                    return 6;
                case ATTACK:
                    if (enemy_type == SHARK)
                        return 8;
                    return 7;
                case HIT:
                    return 4;
                case DEAD:
                    return 5;
            }

            return 0;

        }

        public static int GetMaxHealth(int enemy_type) {
            switch (enemy_type) {
                case CRABBY:
                    return 50;
                case PINKSTAR, SHARK:
                    return 25;
                default:
                    return 1;
            }
        }

        public static int GetEnemyDmg(int enemy_type) {
            switch (enemy_type) {
                case CRABBY:
                    return 15;
                case PINKSTAR:
                    return 20;
                case SHARK:
                    return 25;
                default:
                    return 0;
            }
        }
    }

    public static class Projectiles {
        public static final int CANNON_BALL_DEFAULT_WIDTH = 15;
        public static final int CANNON_BALL_DEFAULT_HEIGHT = 15;

        public static final int CANNON_BALL_WIDTH = (int) (GamePane.SCALE * CANNON_BALL_DEFAULT_WIDTH);
        public static final int CANNON_BALL_HEIGHT = (int) (GamePane.SCALE * CANNON_BALL_DEFAULT_HEIGHT);
        public static final float SPEED = 0.75f * GamePane.SCALE;
    }
}
=======
}
>>>>>>> auming
