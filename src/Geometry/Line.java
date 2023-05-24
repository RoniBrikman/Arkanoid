//323871723 Roni Brikman
package Geometry;
import java.util.List;

/**
 * The type src.Line.
 *
 * @author Roni Brikman < ronibrikman@gmail.com >
 * @version 1
 * @since 2023 -04-07
 */
public class Line {
    public static final double THRESHOLD = 0.0001;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private Point start;
    private Point end;

    /**
     * Instantiates a new src.Line using two points.
     *
     * @param start the start src.Point
     * @param end   the end src.Point
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.x1 = start.getX();
        this.y1 = start.getY();
        this.x2 = end.getX();
        this.y2 = end.getY();

    }

    /**
     * Instantiates a new src.Line using x values and y values of two points.
     *
     * @param x1 the x value of the first src.Point
     * @param y1 the y value of the first src.Point
     * @param x2 the x value of the second src.Point
     * @param y2 the y value of the second src.Point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);

    }

    /**
     * This method return the lengths of a src.Line using 2 x values and 2 y values of specific src.Line.
     *
     * @return the length
     */
    public double length() {
        return Math.sqrt(((this.x1 - this.x2) * (this.x1 - this.x2)) + ((this.y1 - this.y2) * (this.y1 - this.y2)));
    }

    /**
     * This method returns the middle src.Point of a src.Line.
     *
     * @return the Middle point
     */
    public Point middle() {
        double middleX = (this.x1 + this.x2) / 2;
        double middleY = (this.y1 + this.y2) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * This method returns the start src.Point of a src.Line.
     *
     * @return the point
     */
// Returns the start point of the line
    public Point start() {
        return this.start;
    }

    /**
     * This method returns the end src.Point of a src.Line.
     *
     * @return the point
     */
// Returns the end point of the line
    public Point end() {
        return this.end;
    }

    /**
     * This method checks if a given src.Point is on a given src.Line.
     *
     * @param p the point
     * @return True if the src.Point is on the src.Line, and False otherwise
     */
    public boolean isOnLine(Point p) {
        double epsilon = 0.000001;
        return (Math.abs(this.start().distance(p) + (this.end().distance(p)) - this.length()) < epsilon);
    }

    /**
     * This method returns True if one src.Line is intersecting with other src.Line, and False otherwise.
     *
     * @param other the other src.Line
     * @return the boolean
     */
// Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) != null) {
            return true;
        } else { //if one line is overlapping another line, so there are infinite intersection points
            return (this.isOnLine(other.start())) || (this.isOnLine(other.end()))
                    || (other.isOnLine(this.start)) || (other.isOnLine(this.end));
        }
    }

    /**
     * This method returns the intersection src.Point between two lines, and if it doesn't exist/have infinite src.Point it will
     * return null.
     *
     * @param other the other src.Line
     * @return the intersection point or null
     */
// Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        //checks if the lines are equal
        if (this.equals(other)) {
            //if the lines are equal there are infinite intersection points, return null
            return null;
        }
        Point a = this.start;
        Point b = this.end;
        Point c = other.end();
        Point d = other.start();
        // src.Line AB represented as a1x + b1y = c1
        double a1 = b.getY() - a.getY();
        double b1 = a.getX() - b.getX();
        double c1 = a1 * (a.getX()) + b1 * (a.getY());

        // src.Line CD represented as a2x + b2y = c2
        double a2 = d.getY() - c.getY();
        double b2 = c.getX() - d.getX();
        double c2 = a2 * (c.getX()) + b2 * (c.getY());
        double determinant = a1 * b2 - a2 * b1;

        //if the determinant is 0 they have the same incline
        if (determinant == 0) {
            //checks if they have the same incline *but* one continues the other in one intersection point
            if ((this.start.equals(other.start())) && !(this.end.equals(other.end()))) {
                return this.start;
            } else if ((this.start.equals(other.end())) && !(this.end.equals(other.start()))) {
                return this.start;
            } else if (!(this.start.equals(other.end())) && (this.end.equals(other.start()))) {
                return this.end;
            } else if ((this.end.equals(other.end())) && !(this.start.equals(other.start()))) {
                return this.end;
            }
            // The lines are parallel with no intersection points so return null
            return null;
        } else {
            //calculation of the x and y values of the intersection point
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            Point p = new Point(x, y);
            if (!(this.isOnLine(p) && other.isOnLine(p))) {
                return null;
            }
            return p;
        }

    }

    /**
     * This method checks if two Lines are equal, are the same line.
     *
     * @param other the other src.Line
     * @return True if they are equal, False if not
     */
// equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if ((this.start() == other.start()) && (this.end() == other.end())) {
            return true;
        }
        return (this.start == other.end()) && (this.end == other.start());
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect the rectangle
     * @return the closest src.Point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = rect.intersectionPoints(this);
        if (points.isEmpty()) {
            return null;
        }
        int size = points.size();
        double min = this.start.distance(points.get(0));
        Point closest = points.get(0);
        for (int i = 1; i < size; i++) {
            //compares all the intersection points to check which one is closest
            if (this.start.distance(points.get(i)) < min + THRESHOLD) {
                closest = points.get(i);
            }
        }
        return closest;
    }
}


