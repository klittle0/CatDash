import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class CatDash implements ActionListener {
    private CatDashViewer window;
    private ObstacleArray manager;
    private ArrayList<Obstacle> onScreenObstacles;
    private Queue<Obstacle> offScreenObstacles;
    private static final int SLEEP_TIME = 110;
    private boolean isStarted;
    private static final int NUM_OBSTACLES = 10;
    private Icon player;
    private Timer obstacleTimer;

    // Constructor
    public CatDash(){
        isStarted = false;
        onScreenObstacles = new ArrayList<Obstacle>();
        offScreenObstacles = new LinkedList<Obstacle>();
        // Initializes viewer
        manager = new ObstacleArray(offScreenObstacles);
        window = new CatDashViewer(this, manager);
        // Initializes off-screen obstacles
        for (int i = 0; i < NUM_OBSTACLES; i++){
            offScreenObstacles.add(new Obstacle(window));
        }

        //Initialize timer
        obstacleTimer = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                manager.addOnScreen();
            }
        });
    }

    // Moves each obstacle and icon
    public void actionPerformed(ActionEvent e){
        if (player != null){
            player.gravity();
        }
        // Moves obstacles on screen
        if (!onScreenObstacles.isEmpty()){
            manager.moveAll();
            manager.removeOffScreen();
            // Adds new obstacle only every second
            if (System.currentTimeMillis() >= 1000){
                manager.addOnScreen();
            }
        }
        // Checks if player has died
        if (!onScreenObstacles.isEmpty() && player.checkDead(onScreenObstacles.get(0))){
            player.setDead(true);
            obstacleTimer.stop();
        }
        window.repaint();
    }

    public void setStarted(boolean started) {
        isStarted = started;
        player = window.getPlayer();
        obstacleTimer.start();
    }

    public boolean isStarted() {
        return isStarted;
    }

    public ArrayList<Obstacle> getOnScreenObstacles() {
        return onScreenObstacles;
    }

    public Queue<Obstacle> getOffScreenObstacles() {
        return offScreenObstacles;
    }

    public static void main(String[] args) {
        CatDash c = new CatDash();
        Timer clock = new Timer(SLEEP_TIME, c);
        clock.start();
    }
}
