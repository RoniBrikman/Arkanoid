//323871723 Roni Brikman
package Collidable;

import Sprite.Ball;

/**
 * The interface Hit listener. It listens to the hits in the game.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}
