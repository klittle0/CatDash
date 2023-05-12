import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ObstacleArray {
    private ArrayList<Obstacle> onScreenObstacles;
    private Queue<Obstacle> offScreenObstacles;
    private static final int NUM_OBSTACLES = 10;
    private static final int SCREEN_WIDTH = 1200;


    public ObstacleArray(Queue<Obstacle> offScreenObstacles){
        onScreenObstacles = new ArrayList<Obstacle>();
        this.offScreenObstacles = offScreenObstacles;
    }

    // Manages whether an obstacle is on or off screen/which data structure it belongs in
    public void removeOffScreen(){
        Obstacle current = onScreenObstacles.get(0);
        // If entire obstacle is off screen:
        if (!current.isOnScreen()){
            offScreenObstacles.add(current);
            onScreenObstacles.remove(0);
            current.restart();
        }

    }

    public void addOnScreen(){
        if (!offScreenObstacles.isEmpty()){
            Obstacle current = offScreenObstacles.remove();
            onScreenObstacles.add(current);
        }
    }

    //Moves all on-screen obstacles to the left
    public void moveAll(){
        for (Obstacle each: onScreenObstacles){
            each.move();
        }
    }
    // Draws all obstacles
    public void drawAll(Graphics g) {
        for (Obstacle obstacle : onScreenObstacles) {
            obstacle.draw(g);
        }
    }

    public ArrayList<Obstacle> getOnScreenObstacles() {
        return onScreenObstacles;
    }
}
