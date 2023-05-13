// By Kate Little
// 5/12/23

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CatDash implements ActionListener {
    private CatDashViewer window;
    private ObstacleArray manager;
    private static final int SLEEP_TIME = 110;
    private boolean isStarted;
    private boolean isEnded;
    private static final int NUM_OBSTACLES = 10;
    private Icon player;
    private Timer obstacleTimer;
    private static long startTime;

    // Constructor
    public CatDash(){
        isStarted = false;
        // Initializes viewer + obstacle manager
        manager = new ObstacleArray();
        window = new CatDashViewer(this, manager);
        // Initializes off-screen obstacles
        for (int i = 0; i < NUM_OBSTACLES; i++){
            manager.getOffScreenObstacles().add(new Obstacle(window));
        }

        // Initializes timer
        obstacleTimer = new Timer(3000, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                manager.addOnScreen();
                int difference = (int) (System.currentTimeMillis() - startTime);
                // Sets the time/distance between mushrooms depending on the duration the user has been playing
                // If the player survives for longer, the mushrooms come faster
                if (difference < 20000){
                    obstacleTimer.setDelay(4500);
                }
                else if (difference >= 20000 && difference < 30000){
                    obstacleTimer.setDelay(3000);
                }
                else if (difference >= 30000 && difference < 40000){
                    obstacleTimer.setDelay(2500);
                }
                else if (difference >= 40000 && difference < 60000){
                    obstacleTimer.setDelay((int) (Math.random() * 700) + 1350);
                }
                else if (difference >= 60000){
                    obstacleTimer.setDelay((int) (Math.random() * 600) + 1230);
                }
            }
        });
    }

    // Is responsible for all recurring motion/changes to the screen
    public void actionPerformed(ActionEvent e){
        // If player hits 1st obstacle/dies:
        if (manager.getOnScreenObstacles().size() == 1 && (player.checkDead(manager.getOnScreenObstacles().get(0)))){
            player.setDead(true);
            obstacleTimer.stop();
        }
        // If player hits 1st or 2nd obstacle/dies:
        else if(manager.getOnScreenObstacles().size() > 1 && ((player.checkDead(manager.getOnScreenObstacles().get(0)) ||
                (player.checkDead(manager.getOnScreenObstacles().get(1)))))){
            player.setDead(true);
            obstacleTimer.stop();
        }
        else{
            if (player != null){
                player.gravity();
            }
            // Moves obstacles on screen if there are obstacles
            if (!manager.getOnScreenObstacles().isEmpty()){
                manager.moveAll();
                manager.removeOffScreen();
            }
        }
        window.repaint();
    }

    // Begins the game
    public void setStarted(boolean started) {
        isStarted = started;
        player = window.getPlayer();
        obstacleTimer.start();
    }

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public static void main(String[] args) {
        CatDash c = new CatDash();
        Timer clock = new Timer(SLEEP_TIME, c);
        clock.start();
        startTime = System.currentTimeMillis();
    }
}
