//323871723 Roni Brikman
package Collidable;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Sprite.Ball;

/**
 * The interface Collidable. For object we collide with.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * This method notify the object that we collided with it at collisionPoint with
     * a given velocity.
     *
     * @param hitter          the hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
