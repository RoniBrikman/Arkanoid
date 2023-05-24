//323871723 Roni Brikman
package Geometry;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type src.Rectangle.
 *
 * @author Roni Brikman < ronibrikman@gmail.com >
 * @version 1
 * @since 2023 04/05
 */

public class Rectangle {
    private final Point upperLeft;
    private final int width;
    private final int height;
    private Color color;

    /**
     * Instantiates a new src.Rectangle.
     *
     * @param upperLeft the upper left point
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, int width, int height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = Color.black;
    }
    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line the line
     * @return the java . util . list
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> points = new ArrayList<>();
        // checking all the edges of the rectangle if we have an intersection point
        if (line.isIntersecting(getBottomEdge()) && line.intersectionWith(getBottomEdge()) != null) {
            points.add(line.intersectionWith(getBottomEdge()));
        }
        if (line.isIntersecting(getTopEdge()) && line.intersectionWith(getTopEdge()) != null) {
            points.add(line.intersectionWith(getTopEdge()));
        }
        if (line.isIntersecting(getLeftEdge()) && line.intersectionWith(getLeftEdge()) != null) {
            points.add(line.intersectionWith(getLeftEdge()));
        }
        if (line.isIntersecting(getRightEdge()) && line.intersectionWith(getRightEdge()) != null) {
            points.add(line.intersectionWith(getRightEdge()));
        }
        return points;
    }

    /**
     * Gets width.
     *
     * @return the width of the rectangle
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height of the rectangle
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Gets upper left point of the rectangle.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets left edge of the rectangle.
     *
     * @return the left edge
     */
    public Line getLeftEdge() {
        return new Line(this.upperLeft, new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.height));
    }

    /**
     * Gets top edge of the rectangle.
     *
     * @return the top edge
     */
    public Line getTopEdge() {
        return new Line(this.upperLeft, new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY()));
    }

    /**
     * Get bottom edge of the rectangle.
     *
     * @return the line
     */
    public Line getBottomEdge() {
        return new Line(new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY() + this.height), new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.height));
    }

    /**
     * Get right edge of the rectangle.
     *
     * @return the line
     */
    public Line getRightEdge() {
        return new Line(new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY() + this.height), new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY()));
    }

    /**
     * Gets bottom left src.Point.
     *
     * @return the bottom left
     */
    public Point getBottomLeftPoint() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
    }

    /**
     * Gets the bottom right point.
     *
     * @return the top edge
     */
    public Point getBottomRightPoint() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }

    /**
     * Gets the Top Right src.Point.
     *
     * @return the line
     */
    public Point getTopRightPoint() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
    }

    /**
     * Draw borders.
     *
     * @param surface the surface
     */
    public void drawBorders(DrawSurface surface) {
        surface.drawLine((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getTopRightPoint().getX(), (int) this.getTopRightPoint().getY());
        surface.drawLine((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getBottomLeftPoint().getX(), (int) this.getBottomLeftPoint().getY());
        surface.drawLine((int) this.getBottomLeftPoint().getX(), (int) this.getBottomLeftPoint().getY(),
                (int) this.getBottomRightPoint().getX(), (int) this.getBottomRightPoint().getY());
        surface.drawLine((int) this.getBottomRightPoint().getX(), (int) this.getBottomRightPoint().getY(),
                (int) this.getTopRightPoint().getX(), (int) this.getTopRightPoint().getY());
    }

    /**
     * Draws the block and its borders.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        int x = (int) this.getUpperLeft().getX();
        int y = (int) this.getUpperLeft().getY();
        surface.setColor(this.color);
        surface.fillRectangle(x, y, this.getWidth(), this.getHeight());
        surface.setColor(Color.black);
        this.drawBorders(surface);
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
