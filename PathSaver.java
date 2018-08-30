import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by farmersrice on 8/28/2018.
 */

public class PathSaver extends JPanel implements MouseMotionListener, MouseListener {

    boolean recording = false;
    ArrayList<Path> paths = new ArrayList<Path>();
    RecordingArea recordingArea;

    public PathSaver() {
        super(new GridLayout(0,1));
        recordingArea = new RecordingArea(Color.WHITE);
        add(recordingArea);

        recordingArea.addMouseMotionListener(this);
        recordingArea.addMouseListener(this);

        setPreferredSize(new Dimension(850, 1100));
        //setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (recording) {
            paths.get(paths.size() - 1).add(new Point(e.getX(), e.getY()));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!recording) {
            paths.add(new Path());
        } else {
            recordingArea.setCurrentPath(paths.get(paths.size() - 1));
            recordingArea.repaint();
        }
        recording = !recording;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private static void setupGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("PathSaver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PathSaver saver = new PathSaver();
        saver.setOpaque(true);
        frame.setContentPane(saver);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                PathStorageManager.store("paths.txt", saver.getPaths());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> setupGUI());
    }

}
