import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Created by farmersrice on 8/28/2018.
 */

public class PathManager {
    private ArrayList<Path> paths = new ArrayList<Path>();

    public void store(String filename) {
        store(filename, true);
    }

    public void store(String filename, boolean append) {
        try {
            PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(filename, append)));
            for (Path p : paths) {
                w.println(p.saveString());
            }
            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void read(String filename) {
        try {
            BufferedReader r = new BufferedReader(new FileReader("paths.txt"));
            String line = r.readLine();
            while (line != null && line != "") {
                System.out.println("Input received " + line);
                Path p = new Path(line);
                if (p.getPoints().size() >= 2) paths.add(p);
                line = r.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appendPath(Path p) {
        if (p != null) paths.add(p);
    }

    public Path generate(Point start, Point end) {
        Path p = (Path) paths.get((int)(Math.random() * paths.size())).clone();
        p.adjust(start, end);
        return p;
    }

    public Path generate(Point start, Point end, int maxPoints) {
        return generate(start, end, 2, maxPoints);
    }

    //We could presort the paths, binary search for the endpoints, and return the path in log time.
    //But given how few paths we deal with, it is probably unnecessary. If it is an issue it can be added later.
    public Path generate(Point start, Point end, int minPoints, int maxPoints) {
        ArrayList<Path> withinCriteria = new ArrayList<Path>();
        for (Path p : paths) {
            if (minPoints <= p.size() && p.size() <= maxPoints) {
                withinCriteria.add(p);
            }
        }
        Path p = (Path) withinCriteria.get((int)(Math.random() * withinCriteria.size())).clone();
        p.adjust(start, end);
        return p;
    }
}
