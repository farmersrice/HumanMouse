import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by farmersrice on 8/28/2018.
 */

public class PathSaver extends JPanel implements MouseMotionListener, MouseListener {

    boolean recording = false;
    RecordingArea recordingArea;
    PathManager manager;
    Path current;

    public PathSaver() {
        super(new GridLayout(0,1));
        recordingArea = new RecordingArea(Color.WHITE);
        add(recordingArea);

        recordingArea.addMouseMotionListener(this);
        recordingArea.addMouseListener(this);

        setPreferredSize(new Dimension(850, 1100));
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (recording) {
            current.add(new Point(e.getX(), e.getY()));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!recording) {
            manager.appendPath(current);
            current = new Path();
        } else {
            recordingArea.setCurrentPath(current);
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
        JFrame frame = new JFrame("PathSaver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PathSaver saver = new PathSaver();
        saver.setOpaque(true);
        frame.setContentPane(saver);

        saver.manager = new PathManager();

        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                saver.manager.store("paths.txt");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> setupGUI());
    }

}
