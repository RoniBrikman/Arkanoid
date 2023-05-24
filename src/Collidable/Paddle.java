//323871723 Roni Brikman
package Collidable;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import biuoop.DrawSurface;
import Game.Game;
import Sprite.Sprite;
import java.awt.Color;
import Sprite.Ball;

/**
 * The type src.Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    /**
     * The constant SPEED.
     */
    public static final double SPEED = 7;
    /**
     * The constant THRESHOLD.
     */
    public static final double THRESHOLD = 0.0001;

    /**
     * Instantiates a new src.Paddle.
     *
     * @param keyboard the keyboard
     * @param rec      the rec
     * @param color    the color
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rec, Color color) {
        this.keyboard = keyboard;
        this.rectangle = rec;
        this.rectangle.setColor(color);
    }

    /**
     * Set upper left.
     *
     * @param p the p
     */
    public void setUpperLeft(Point p) {
        this.rectangle = new Rectangle(p, this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * Move the paddle left.
     */
    public void moveLeft() {
        this.setUpperLeft(new Point(this.rectangle.getUpperLeft().getX() - SPEED,
                this.rectangle.getUpperLeft().getY()));
    }

    /**
     * Move the paddle right.
     */
    public void moveRight() {
        this.setUpperLeft(new Point(this.rectangle.getUpperLeft().getX() + SPEED,
                this.rectangle.getUpperLeft().getY()));
    }

    // src.Sprite
    @Override
    public void timePassed() {
        if (keyboard.isPressed(this.keyboard.RIGHT_KEY)) {
            if (this.rectangle.getUpperLeft().getX() + SPEED < Game.SCREEN_WIDTH - Game.BORDER_HEIGHT
                    - this.rectangle.getWidth()) {
                this.moveRight();
            }
        } else if (keyboard.isPressed(this.keyboard.LEFT_KEY)) {
            if (this.rectangle.getUpperLeft().getX() - SPEED > Game.BORDER_HEIGHT) {
                this.moveLeft();
            }
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d);
    }
    
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double x = collisionPoint.getX();
        double curSpeed = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        double regionSize = (double) this.rectangle.getWidth() / 5;
        double startX = this.rectangle.getUpperLeft().getX();
        //checking where the ball is, and changing velocity according to sections
        // section 1
        if (x - THRESHOLD >= (startX) && x <= startX + regionSize + THRESHOLD) {
            return Velocity.fromAngleAndSpeed(300, curSpeed);
        }
        //section 2
        if (x >= (startX + regionSize - THRESHOLD) && x < startX + (2 * regionSize) + THRESHOLD) {
            return Velocity.fromAngleAndSpeed(330, curSpeed);
        }
        //section 3
        if (x >= (startX + (2 * regionSize) - THRESHOLD) && x < startX + (3 * regionSize) + THRESHOLD) {
            return new Velocity(dx, -dy);
        }
        //section 4
        if (x >= (startX + (3 * regionSize) - THRESHOLD) && x < startX + (4 * regionSize) + THRESHOLD) {
            return Velocity.fromAngleAndSpeed(30, curSpeed);
        }
        //section 5
        return Velocity.fromAngleAndSpeed(60, curSpeed);
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}