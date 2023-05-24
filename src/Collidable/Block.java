//323871723 Roni Brikman
package Collidable;

import Geometry.Point;
import Geometry.Rectangle;
import Sprite.Ball;
import Geometry.Velocity;
import biuoop.DrawSurface;
import Sprite.Sprite;
import Game.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Block. All the blocks of the game.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    /**
     * The constant THRESHOLD.
     */
    public static final double THRESHOLD = 0.0001;
    /**
     * The Hit listeners.
     */
    private List<HitListener> hitListeners;
    private Rectangle rectangle;

    /**
     * Instantiates a new Block.
     *
     * @param rec the rec
     */
    public Block(Rectangle rec) {
        this.rectangle = rec;
        this.hitListeners = new ArrayList<HitListener>();
    }


    //Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //notifying on hit
        this.notifyHit(hitter);
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

    /**
     * Remove the block from the game.
     *
     * @param game the game
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
   /**
    * Notifies on a hit to the listeners.
    *
    * @param hitter the hitting ball
     */
    private void notifyHit(Ball hitter) {
        // Making a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
