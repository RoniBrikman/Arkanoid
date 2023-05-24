//323871723 Roni Brikman
package Geometry;
import java.util.Random;

/**
 * The type Point.
 *
 * @author Roni Brikman < ronibrikman@gmail.com >
 * @version 1
 * @since 2023 -04-07
 */
public class Point {
    private double x;
    private double y;

    /**
     * Instantiates a new Point using x and y values.
     *
     * @param x the x value
     * @param y the y value
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Instantiates a new Point Using different point.
     *
     * @param oldPoint the old point
     */
    public Point(Point oldPoint) {
        x = oldPoint.x;
        y = oldPoint.y;
    }

    /**
     * Generate random point.
     *
     * @param maxX the max x
     * @param maxY the max y
     * @return the point
     */
    public static Point generateRandomPoint(double maxX, double maxY) {
        Random rand = new Random();
        return new Point(maxX * rand.nextDouble(),
                maxY * rand.nextDouble());
    }


    /**
     * This method returns the distance between two points.
     *
     * @param other the other Point
     * @return the distance between two Points
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
    }

    /**
     * This method checks if two Points are equal to one another using a threshold.
     *
     * @param other the other Point
     * @return True if they are equal, False if not
     */
    public boolean equals(Point other) {
        double epsilon = 0.000001;
        return ((Math.abs(this.x - other.x) < epsilon) && (Math.abs(this.y - other.y) < epsilon));

    }

    /**
     * This method gets the x value of a Point.
     *
     * @return the x value of a Point
     */
// Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * This method gets the y value of a Point.
     *
     * @return the y value of a Point
     */
    public double getY() {
        return this.y;
    }
}

