// By Kate Little
// 5/12/23
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ObstacleArray {
    private ArrayList<Obstacle> onScreenObstacles;
    private Queue<Obstacle> offScreenObstacles;

    // Constructor
    public ObstacleArray(){
        onScreenObstacles = new ArrayList<Obstacle>();
        offScreenObstacles = new LinkedList<Obstacle>();
    }

    // Transfers obstacles from on to off screen
    public void removeOffScreen(){
        Obstacle current = onScreenObstacles.get(0);
        // If entire obstacle is off screen:
        if (!current.isOnScreen()){
            offScreenObstacles.add(current);
            onScreenObstacles.remove(0);
            current.restart();
        }
    }

    // Transfers an obstacle from off to on screen
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

    // Draws all visible obstacles
    public void drawAll(Graphics g) {
        for (int i = 0; i < onScreenObstacles.size(); i++) {
            onScreenObstacles.get(i).draw(g, i);
        }
    }

    public Queue<Obstacle> getOffScreenObstacles() {
        return offScreenObstacles;
    }

    public ArrayList<Obstacle> getOnScreenObstacles() {
        return onScreenObstacles;
    }
}
