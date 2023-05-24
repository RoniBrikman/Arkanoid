//323871723 Roni Brikman
package Collidable;

import Geometry.Point;

/**
 * The type Collision info. Holds information on the collision Point and the Collidable.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidable;

    /**
     * Instantiates a new Collision info.
     *
     * @param collisionPoint the collision point
     * @param collidable     the collidable
     */
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collidable = collidable;
    }

    /**
     * This method gets the Collision point.
     *
     * @return the point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * returns the collidable object involved in the collision.
     *
     * @return the collidable
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}
