//323871723 Roni Brikman
package Geometry;
/**
 * The type src.Velocity. src.Velocity specifies the change in position on the `x` and the `y` axes.
 *
 * @author Roni Brikman < ronibrikman@gmail.com >
 * @version 1
 * @since 2023 -04-07
 */

public class Velocity {
    private double dx;
    private double dy;

    /**
     * This method instantiates a new src.Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This method Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p the old point
     * @return the new point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * This method gets the dx.
     *
     * @return the dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * This method gets the dy.
     *
     * @return the dy
     */
    public double getDy() {
        return this.dy;
    }


    /**
     * This method gets speed and angle and converts them (using math) to new src.Velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx =  Math.sin(Math.toRadians(angle)) * speed;
        double dy = -Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }
}
