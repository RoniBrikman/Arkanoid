//323871723 Roni Brikman
package Sprite;
import java.awt.Color;

import Collidable.CollisionInfo;
import Game.GameEnvironment;
import Geometry.Line;
import Geometry.Point;
import Geometry.Velocity;
import biuoop.DrawSurface;
import Game.Game;


/**
 * The type ball.
 *
 * @author Roni Brikman < ronibrikman@gmail.com >
 * @version 1
 * @since 2023 -04-07
 */
public class Ball implements Sprite {
    /**
     * The constant THRESHOLD.
     */
    public static final double THRESHOLD = 0.0001;
    private GameEnvironment game;
    private Point center;
    private int r;
    private Color color;
    private Velocity velocity;

    /**
     * Instantiates a new src.Ball using x and y values for center, radius and color.
     *
     * @param x     the x value of center
     * @param y     the y value of center
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.game = null;
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * This method checks if the ball is in the screen, and if not it will change the center according to screen.
     *
     * @param screenMinX the screen minimum point x value
     * @param screenMinY the screen minimum point y value
     * @param screenH    the screen height
     * @param screenW    the screen width
     */
    public void boundaries(int screenMinX, int screenMinY, int screenH, int screenW) {
        int flag = 0;
        double x = this.center.getX();
        double y = this.center.getY();
        //checks if the ball is off limits, if so it will enter it to the screen
        if (x - this.r < screenMinX) {
            x = screenMinX + this.r;
            flag = -1;
        } else if (x + this.r > screenW) {
            x = screenW - this.r;
            flag = -1;
        }
        if (y - this.r < screenMinY) {
            y = screenMinY + this.r;
            flag = -1;
        } else if (y + this.r > screenH) {
            y = screenH - this.r;
            flag = -1;
        }
        this.center = new Point(x, y);
        if (flag != 0) {
            System.out.println("The center point values you entered does not fit the screen limits"
                    + " and has been changed according to them");
        }
    }

    /**
     * Sets the ball's environment.
     *
     * @param environment the environment
     */
    public void setEnvironment(GameEnvironment environment) {
        this.game = environment;
    }

    /**
     * Gets the x value of the center point.
     *
     * @return the x
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets the y value of the center point.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size (the radius) of the ball.
     *
     * @return the size
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets the center point of the ball.
     *
     * @return the center
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Sets velocity of the ball using dx and dy.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets the velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Sets the center point.
     *
     * @param x the x
     * @param y the y
     */
    public void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }


    /**
     * Move the ball one step on the screen.
     */
    public void moveOneStep() {
        Point p = new Point(this.center.getX() + this.velocity.getDx(), this.center.getY() + this.velocity.getDy());
        Line l = new Line(this.center, p);
        CollisionInfo collision = this.game.getClosestCollision(l);
        if (collision != null) {
            this.velocity = collision.collisionObject().hit(collision.collisionPoint(), this.velocity);
            if (this.center.getX() - this.r >= collision.collisionPoint().getX() - THRESHOLD) {
                this.setCenter(collision.collisionPoint().getX() + this.r, this.getY());
            } else if (this.center.getX() + this.r <= collision.collisionPoint().getX() + THRESHOLD) {
                this.setCenter(collision.collisionPoint().getX() - this.r, this.getY());
            }
            if (this.center.getY() - this.r >= collision.collisionPoint().getY() - THRESHOLD) {
                this.setCenter(this.getX(), collision.collisionPoint().getY() + this.r);
            } else if (this.center.getY() + this.r <= collision.collisionPoint().getY() + THRESHOLD) {
                this.setCenter(this.getX(), collision.collisionPoint().getY() - this.r);
            }
        } else {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
//        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Add the ball to the game.
     *
     * @param g the g
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    //src.Sprite
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    @Override
    public void drawOn(DrawSurface surface) {
        int x = this.getX();
        int y = this.getY();
        int r = this.r;
        surface.setColor(this.getColor());
        surface.fillCircle(x, y, r);
    }
}