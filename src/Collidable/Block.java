//323871723 Roni Brikman
package Collidable;

import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import biuoop.DrawSurface;
import Sprite.Sprite;
import Game.Game;

public class Block implements Collidable, Sprite {
    /**
     * The constant THRESHOLD.
     */
    public static final double THRESHOLD = 0.0001;
    private Rectangle rectangle;

    /**
     * Instantiates a new src.Block.
     *
     * @param rec the rec
     */
    public Block(Rectangle rec) {
        this.rectangle = rec;
    }


    //src.Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        // if the collision is from left/right
        if (collisionPoint.getX() - THRESHOLD <= this.rectangle.getUpperLeft().getX()
                || collisionPoint.getX() + THRESHOLD >= this.rectangle.getTopRightPoint().getX()) {
            dx = -dx;
        } else {
            // if the collision is from up/down
            if (collisionPoint.getY() + THRESHOLD >= this.rectangle.getUpperLeft().getY()
                    || collisionPoint.getY() - THRESHOLD <= this.rectangle.getBottomLeftPoint().getY()) {
                dy = -dy;
            }
        }
        return new Velocity(dx, dy);
    }

    //src.Sprite
    @Override
    public void drawOn(DrawSurface surface) {
        this.rectangle.drawOn(surface);
    }

    @Override
    public void timePassed() {
    }

    /**
     * Draw borders of the block.
     *
     * @param surface the surface
     */
    public void drawBorders(DrawSurface surface) {
        this.rectangle.drawBorders(surface);
    }

    /**
     * Add the block to the game.
     *
     * @param g the g
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
