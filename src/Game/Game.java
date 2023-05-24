//323871723 Roni Brikman
package Game;
import Collidable.Block;
import Collidable.Paddle;
import Geometry.Point;
import Geometry.Rectangle;
import Sprite.Ball;
import Sprite.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import Collidable.Collidable;
import Sprite.Sprite;
import java.awt.Color;

/**
 * The type src.Game.
 */
public class Game {
    private SpriteCollection sprites;
    private final GameEnvironment environment;
    private GUI gui;
    /**
     * The constant SCREEN_HEIGHT.
     */
    public static final int SCREEN_HEIGHT = 600;
    /**
     * The constant SCREEN_WIDTH.
     */
    public static final int SCREEN_WIDTH = 800;
    /**
     * The constant BLOCK_HEIGHT.
     */
    public static final int BORDER_HEIGHT = 10;

    /**
     * Instantiates a new src.Game.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Draws the borders of the screen.
     */
    public void addBorders() {
        Block top = new Block(new Rectangle(new Point(0, 0), SCREEN_WIDTH, BORDER_HEIGHT));
        top.getCollisionRectangle().setColor(Color.gray);
        top.addToGame(this);
        Block bottom = new Block(new Rectangle(new Point(0, SCREEN_HEIGHT - BORDER_HEIGHT),
                SCREEN_WIDTH, BORDER_HEIGHT));
        bottom.getCollisionRectangle().setColor(Color.gray);
        bottom.addToGame(this);
        Block left = new Block(new Rectangle(new Point(0, 0), BORDER_HEIGHT, SCREEN_HEIGHT));
        left.getCollisionRectangle().setColor(Color.gray);
        left.addToGame(this);
        Block right = new Block(new Rectangle(new Point(SCREEN_WIDTH - BORDER_HEIGHT, 0),
                BORDER_HEIGHT, SCREEN_HEIGHT));
        right.getCollisionRectangle().setColor(Color.gray);
        right.addToGame(this);
    }

    /**
     * Draws the blocks on the screen.
     */
    public void addBlocks() {
        int blockWidth = SCREEN_WIDTH / 16;
        int blockHeight = SCREEN_HEIGHT / 25;
        for (int i = 0, j = SCREEN_WIDTH - BORDER_HEIGHT; i < 13; i++, j = j - blockWidth) {
            Block block = new Block(new Rectangle(new Point(j, 100), blockWidth, blockHeight));
            block.getCollisionRectangle().setColor(Color.MAGENTA);
            block.addToGame(this);
        }
        for (int i = 0, j = SCREEN_WIDTH - BORDER_HEIGHT; i < 12; i++, j = j - blockWidth) {
            Block block = new Block(new Rectangle(new Point(j, 100 + blockHeight), blockWidth, blockHeight));
            block.getCollisionRectangle().setColor(Color.PINK);
            block.addToGame(this);
        }
        for (int i = 0, j = SCREEN_WIDTH - BORDER_HEIGHT; i < 11; i++, j = j - blockWidth) {
            Block block = new Block(new Rectangle(new Point(j, 100 + 2 * blockHeight), blockWidth, blockHeight));
            block.getCollisionRectangle().setColor(Color.CYAN);
            block.addToGame(this);
        }
        for (int i = 0, j = SCREEN_WIDTH - BORDER_HEIGHT; i < 10; i++, j = j - blockWidth) {
            Block block = new Block(new Rectangle(new Point(j, 100 + 3 * blockHeight), blockWidth, blockHeight));
            block.getCollisionRectangle().setColor(Color.BLUE);
            block.addToGame(this);
        }
        for (int i = 0, j = SCREEN_WIDTH - BORDER_HEIGHT; i < 9; i++, j = j - blockWidth) {
            Block block = new Block(new Rectangle(new Point(j, 100 + 4 * blockHeight), blockWidth, blockHeight));
            block.getCollisionRectangle().setColor(Color.orange);
            block.addToGame(this);
        }
        for (int i = 0, j = SCREEN_WIDTH - BORDER_HEIGHT; i < 8; i++, j = j - blockWidth) {
            Block block = new Block(new Rectangle(new Point(j, 100 + 5 * blockHeight + 0.1), blockWidth, blockHeight));
            block.getCollisionRectangle().setColor(Color.YELLOW);
            block.addToGame(this);
        }
    }

    /**
     * Draws the blocks on the screen.
     */
    public void addBalls() {
        Ball ball1 = new Ball(400, 560, 5, Color.RED);
        ball1.setVelocity(5, -3);
        ball1.setEnvironment(this.environment);
        ball1.addToGame(this);
        Ball ball2 = new Ball(400, 560, 5, Color.RED);
        ball2.setVelocity(-5, -3);
        ball2.setEnvironment(this.environment);
        ball2.addToGame(this);

    }

    /**
     * Initialize a new game: create the Blocks, Balls and src.Paddle.
     */
// and add them to the game.
    public void initialize() {
        this.gui = new GUI("Collision test", 800, 600);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        DrawSurface d = gui.getDrawSurface();
        //adding the balls
        this.addBalls();
        //adding the blocks
        this.addBlocks();
        //adding the borders
        this.addBorders();
        //adding the paddle
        Paddle paddle = new Paddle(keyboard, new Rectangle(new Point(375, 570), 80, 20), Color.BLACK);
        paddle.addToGame(this);
    }

    /**
     * Run the game - start the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
   
}
