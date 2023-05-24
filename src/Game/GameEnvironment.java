
package Game;
import Collidable.Block;
import Collidable.CollisionInfo;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import Collidable.Collidable;

/**
 * The type src.Game environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    public static final double THRESHOLD = 0.0001;

    /**
     * Instantiates a new src.Game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
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

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Collision test", 500, 500);
        Rectangle rec = new Rectangle(new Point(50, 50), 100, 100);
        Line l1 = new Line(300, 150, 100, 150);
        GameEnvironment g = new GameEnvironment();
        Block b = new Block(rec);
        g.addCollidable(b);
        CollisionInfo info = g.getClosestCollision(l1);
        System.out.println(info.collisionPoint().getX());
        System.out.println(info.collisionPoint().getY());
        DrawSurface d = gui.getDrawSurface();
        d.setColor(Color.BLUE);
        d.drawLine(300, 150, 100, 150);
        d.setColor(Color.black);
        d.fillCircle((int) info.collisionPoint().getX(), (int) info.collisionPoint().getY(), 3);
        b.drawOn(d);
        gui.show(d);
    }
}
