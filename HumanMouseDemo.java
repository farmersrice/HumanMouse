import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by farmersrice on 8/28/2018.
 */

public class HumanMouseDemo extends JPanel implements MouseListener {

    boolean halfDone = false;
    PathManager manager;
    RecordingArea recordingArea;
    Point start;
    Point end;

    public HumanMouseDemo() {
        super(new GridLayout(0,1));
        recordingArea = new RecordingArea(Color.WHITE);
        add(recordingArea);

        recordingArea.addMouseListener(this);

        setPreferredSize(new Dimension(850, 1100));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (halfDone) {
            end = new Point(e.getX(), e.getY());

            Path p = manager.generate(start, end);

            recordingArea.setCurrentPath(p);
            recordingArea.repaint();
        } else {
            start = new Point(e.getX(), e.getY());
        }
        halfDone = !halfDone;
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
        JFrame frame = new JFrame("HumanMouseDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        HumanMouseDemo component = new HumanMouseDemo();
        component.setOpaque(true);
        frame.setContentPane(component);

        frame.pack();
        frame.setVisible(true);

        component.manager = new PathManager();
        component.manager.read("paths.txt");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> setupGUI());
    }

}
