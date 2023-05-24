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
import Collidable.HitListener;
import Sprite.Sprite;
import Collidable.BlockRemover;
import java.awt.Color;
import Sprite.BallRemover;


/**
 * The type Game, where the game happens.
 */
public class Game {
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter score;
    private SpriteCollection sprites;
    private final GameEnvironment environment;
    private GUI gui;
    public static final int SCREEN_HEIGHT = 600;
    public static final int SCREEN_WIDTH = 800;
    public static final int BORDER_HEIGHT = 17;

    /**
     * Instantiates a new Game.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter(57);
        this.ballsCounter = new Counter(3);
        this.score = new Counter(0);
    }

    /**
     * Add collidable to the game.
     *
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite to the game.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Gets the counter of the blocks.
     *
     * @return the counter
     */
    public Counter getBlocksCounter() {
        return this.blocksCounter;
    }

    /**
     * Gets the balls counter.
     *
     * @return the balls counter
     */
    public Counter getBallsCounter() {
        return this.ballsCounter;
    }

    /**
     * Remove collidable from the game.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite from the game.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Draws the borders of the screen.
     *
     * @param p the p
     */
    public void addBorders(HitListener p) {
        //the top
        Block top = new Block(new Rectangle(new Point(0, 20), SCREEN_WIDTH, BORDER_HEIGHT));
        top.getCollisionRectangle().setColor(Color.darkGray);
        top.addToGame(this);
        //the bottom - death block
        Block bottom = new Block(new Rectangle(new Point(0, SCREEN_HEIGHT),
                SCREEN_WIDTH, BORDER_HEIGHT));
        bottom.getCollisionRectangle().setColor(Color.darkGray);
        bottom.addToGame(this);
        bottom.addHitListener(p);
        // the left
        Block left = new Block(new Rectangle(new Point(0, 20), BORDER_HEIGHT, SCREEN_HEIGHT));
        left.getCollisionRectangle().setColor(Color.darkGray);
        left.addToGame(this);
        //the right
        Block right = new Block(new Rectangle(new Point(SCREEN_WIDTH - BORDER_HEIGHT, 20),
                BORDER_HEIGHT, SCREEN_HEIGHT));
        right.getCollisionRectangle().setColor(Color.darkGray);
        right.addToGame(this);
    }

    /**
     * Draws the blocks on the screen.
     *
     * @param p     the Hit listener
     * @param score the score
     */
    public void addBlocks(HitListener p,  ScoreTrackingListener score) {
        int blockWidth = SCREEN_WIDTH / 16;
        int blockHeight = SCREEN_HEIGHT / 25;
        for (int i = 0, j = SCREEN_WIDTH - BORDER_HEIGHT; i < 13; i++, j = j - blockWidth) {
            Block block = new Block(new Rectangle(new Point(j, 100), blockWidth, blockHeight));
            block.getCollisionRectangle().setColor(Color.MAGENTA);
            block.addToGame(this);
            block.addHitListener(p);
            block.addHitListener(score);
        }
        for (int i = 0, j = SCREEN_WIDTH - BORDER_HEIGHT; i < 12; i++, j = j - blockWidth) {
            Block block = new Block(new Rectangle(new Point(j, 100 + blockHeight), blockWidth, blockHeight));
            block.getCollisionRectangle().setColor(Color.PINK);
            block.addToGame(this);
            block.addHitListener(p);
            block.addHitListener(score);
        }
        for (int i = 0, j = SCREEN_WIDTH - BORDER_HEIGHT; i < 11; i++, j = j - blockWidth) {
            Block block = new Block(new Rectangle(new Point(j, 100 + 2 * blockHeight), blockWidth, blockHeight));
            block.getCollisionRectangle().setColor(Color.CYAN);
            block.addToGame(this);
            block.addHitListener(p);
            block.addHitListener(score);
        }
        for (int i = 0, j = SCREEN_WIDTH - BORDER_HEIGHT; i < 10; i++, j = j - blockWidth) {
            Block block = new Block(new Rectangle(new Point(j, 100 + 3 * blockHeight), blockWidth, blockHeight));
            block.getCollisionRectangle().setColor(Color.BLUE);
            block.addToGame(this);
            block.addHitListener(p);
            block.addHitListener(score);
        }
        for (int i = 0, j = SCREEN_WIDTH - BORDER_HEIGHT; i < 9; i++, j = j - blockWidth) {
            Block block = new Block(new Rectangle(new Point(j, 100 + 4 * blockHeight), blockWidth, blockHeight));
            block.getCollisionRectangle().setColor(Color.orange);
            block.addToGame(this);
            block.addHitListener(p);
            block.addHitListener(score);
        }
        for (int i = 0, j = SCREEN_WIDTH - BORDER_HEIGHT; i < 8; i++, j = j - blockWidth) {
            Block block = new Block(new Rectangle(new Point(j, 100 + 5 * blockHeight + 0.1), blockWidth, blockHeight));
            block.getCollisionRectangle().setColor(Color.YELLOW);
            block.addToGame(this);
            block.addHitListener(p);
            block.addHitListener(score);
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
        Ball ball3 = new Ball(400, 560, 5, Color.RED);
        ball3.setVelocity(-5, -2);
        ball3.setEnvironment(this.environment);
        ball3.addToGame(this);
    }

    /**
     * Initialize a new game: create the Blocks, Balls and Paddle.
     */
    public void initialize() {
        this.gui = new GUI("Collision test", 800, 600);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        ScoreTrackingListener score = new ScoreTrackingListener(this.score);
        BlockRemover blockRemover = new BlockRemover(this, this.blocksCounter);
        BallRemover ballsRemover = new BallRemover(this, this.ballsCounter);
        //adding the balls
        this.addBalls();
        //adding the blocks
        this.addBlocks(blockRemover, score);
        //adding the borders
        this.addBorders(ballsRemover);
        //adding the paddle
        Paddle paddle = new Paddle(keyboard, new Rectangle(new Point(375, 570), 80, 20), Color.BLACK);
        paddle.addToGame(this);
        //adding the score
        ScoreIndicator scoreShow = new ScoreIndicator(this.score);
        this.addSprite(scoreShow);
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
            //checks if all the blocks are cleared
            if (this.blocksCounter.getValue() == 0) {
                this.score.increase(100);
                gui.close();
                return;
            }
            //checks if all the balls died
            if (this.ballsCounter.getValue() == 0) {
                gui.close();
                return;
            }
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
