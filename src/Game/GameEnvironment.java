//323871723 Roni Brikman
package Game;
import Collidable.CollisionInfo;
import Geometry.Line;
import Geometry.Point;
import java.util.ArrayList;
import java.util.List;
import Collidable.Collidable;

/**
 * The type Game environment. Includes the collidables.
 */
public class GameEnvironment {
    private List<Collidable> collidables;
    public static final double THRESHOLD = 0.0001;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory
     * @return the collision info
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int flag = 0;
        Point closest = null;
        double min = 0;
        //checks if there is an intersection point, if so it is the minimum
        for (Collidable collidable : this.collidables) {
            List<Point> intersections = collidable.getCollisionRectangle().intersectionPoints(trajectory);
            if (!intersections.isEmpty()) {
                flag = 1;
                closest = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
                min = trajectory.start().distance(closest);
                break;
            }
        }
        // if there is no intersection point
        if (flag == 0) {
            return null;
        }
        //finds the minimum of the intersection points
        int where = 0;
        for (int i = 0; i < this.collidables.size(); i++) {
            Point check = trajectory.closestIntersectionToStartOfLine(this.collidables.get(i).getCollisionRectangle());
            if (check != null && trajectory.start().distance(check) < min + THRESHOLD) {
                closest = check;
                min = trajectory.start().distance(check);
                where = i;
            }
        }
        return new CollisionInfo(closest, this.collidables.get(where));
    }
}
