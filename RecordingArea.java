import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Created by farmersrice on 8/28/2018.
 */

public class RecordingArea extends JLabel {
    Dimension size = new Dimension(800, 800);
    private Path currentPath;

    public RecordingArea(Color color) {
        setBackground(color);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getMinimumSize() {
        return size;
    }

    public Dimension getPreferredSize() {
        return size;
    }

    public void setCurrentPath(Path p) {
        currentPath = p;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        System.out.println("Path is " + currentPath);

        g.setColor(Color.BLACK);
        if (currentPath != null) for (Point p : currentPath.getPoints()) {
            g.fillRect(p.getX(), p.getY(), 3, 3);
        }
    }
}