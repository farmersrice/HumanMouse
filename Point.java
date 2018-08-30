/**
 * Created by farmersrice on 8/28/2018.
 */

public class Point {
    private int x, y;

    Point(int a, int b) {
        x = a; y = b;
    }

    public Object clone() {
        return new Point(x, y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void rotate(Point origin, double angle) {
        double sinValue = Math.sin(angle);
        double cosValue = Math.cos(angle);

        int originX = origin.getX();
        int originY = origin.getY();

        x -= originX;
        y -= originY;

        int newX = (int) (x * cosValue - y * sinValue);
        int newY = (int) (x * sinValue + y * cosValue);

        x = newX + originX;
        y = newY + originY;
    }

    public void translate(Point vec) {
        x += vec.getX();
        y += vec.getY();
    }

    public void scale(Point origin, double distance) {
        x = origin.getX() + (int) (distance * (x - origin.getX()));
        y = origin.getY() + (int) (distance * (y - origin.getY()));
    }

    public double distance(Point other) {
        return Math.sqrt((other.getX() - x) * (other.getX() - x) + (other.getY() - y) * (other.getY() - y));
    }

    public double getAngleTo(Point origin) {
        return Math.atan2(y - origin.getY(), x - origin.getX());
    }

    //how many rads do we have to rotate to get from start to end equal angle
    public static double getAngleRotated(Point origin, Point start, Point end) {
        return end.getAngleTo(origin) - start.getAngleTo(origin);
    }

    //distance to start times scale factor = distance to end
    public static double getScaleFactor(Point origin, Point start, Point end) {
        return origin.distance(end) / origin.distance(start);
    }

    public static Point getVec(Point start, Point end) {
        return new Point(end.getX() - start.getX(), end.getY() - start.getY());
    }
}
