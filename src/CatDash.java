import java.awt.event.ActionEvent;
import java.util.Queue;

public class CatDash {
    private CatDashViewer window;
    private Queue<Obstacle> onScreenObstacles;
    private Obstacle[][] allObstacles;

    // Constructor
    public CatDash(){

    }

    public void actionPerformed(ActionEvent e){

    }

    public Obstacle[][] getAllObstacles() {
        return allObstacles;
    }

    public Queue<Obstacle> getOnScreenObstacles() {
        return onScreenObstacles;
    }

    public static void main(String[] args){

    }
}
