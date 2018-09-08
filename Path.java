import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by farmersrice on 8/28/2018.
 */

public class Path {
    private ArrayList<Point> points = new ArrayList<Point>();

    public ArrayList<Point> getPoints() {
        return points;
    }

    public Path(){}

    public Path(ArrayList<Point> points) {
        for (int i = 0; i < points.size(); i++) {
            this.points.add((Point)points.get(i).clone());
        }
    }

    public int size() {
        return points.size();
    }

    public Path(String input) {
        StringTokenizer st = new StringTokenizer(input);

        int pointsOnPath = Integer.parseInt(st.nextToken());

        for (int i = 0; i < pointsOnPath; i++) {
            points.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }

    public Object clone() {
        return new Path(points);
    }

    public String toString() {
        String value = "";
        for (Point p : points) {
            value += p.toString() + " ";
        }
        return value;
    }

    public String saveString() {
        String value = points.size() + " ";
        for (Point p : points) {
            value += p.getX() + " " + p.getY() + " ";
        }
        return value;
    }

    public void add(Point p) {
        points.add(p);
    }

    //Adjust the current path so that it goes from start to end, smoothly. Changes internal points list
    public void adjust(Point start, Point end) {
        //first translate everything
        Point vec = Point.getVec(points.get(0), start);
        for (Point p : points) {
            p.translate(vec);
        }

        //now rotate the path
        double angleRotated = Point.getAngleRotated(start, points.get(points.size() - 1), end);

        for (Point p : points) {
            p.rotate(start, angleRotated);
        }

        //now scale down/up
        double scaleFactor = Point.getScaleFactor(start, points.get(points.size() - 1), end);

        for (Point p : points) {
            p.scale(start, scaleFactor);
        }

        points.set(points.size() - 1, (Point) end.clone()); //we do not want to modify end point, but want to ensure
        //that it lands on the right point every time
    }
}
