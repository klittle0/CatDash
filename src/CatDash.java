import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

public class CatDash implements ActionListener {
    private CatDashViewer window;
    private Queue<Obstacle> onScreenObstacles;
    private Obstacle[][] allObstacles;
    private static final int SLEEP_TIME = 110;
    private boolean isStarted;

    // Constructor
    public CatDash(){
        isStarted = false;
        // Initializes viewer
        window = new CatDashViewer(this);
    }

    // Moves each obstacle and icon
    public void actionPerformed(ActionEvent e){
        // Insert icon jump here?
        window.repaint();
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public Obstacle[][] getAllObstacles() {
        return allObstacles;
    }

    public Queue<Obstacle> getOnScreenObstacles() {
        return onScreenObstacles;
    }

    public static void main(String[] args) {
        CatDash c = new CatDash();
        Timer clock = new Timer(SLEEP_TIME, c);
        clock.start();
    }
}
