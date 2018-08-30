import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by farmersrice on 8/28/2018.
 */

public class PathStorageManager {
    public static void store(String filename, ArrayList<Path> paths) {
        try {
            PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
            for (Path p : paths) {
                w.println(p.saveString());
            }
            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void read(String filename, ArrayList<Path> paths) {
        try {
            BufferedReader r = new BufferedReader(new FileReader("paths.txt"));
            String line = r.readLine();
            while (line != null && line != "") {
                System.out.println("Input received " + line);
                StringTokenizer st = new StringTokenizer(line);
                Path p = new Path(line);
                if (p.getPoints().size() >= 2) paths.add(p);
                line = r.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
