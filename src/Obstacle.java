// By Kate Little
// 5/12/23
import javax.swing.*;
import java.awt.*;

public class Obstacle {
    private Image[] mushroom;
    private int x;
    private static final int GROUND = 520;
    private static final int SCREEN_WIDTH = 1200;
    private int dx;
    private static final int SIZE = 80;
    private CatDashViewer v;

    // Constructor
    public Obstacle(CatDashViewer v){
        this.v = v;
        mushroom = new Image[2];
        mushroom[0] = new ImageIcon("Resources/mushroomBlueF.png").getImage();
        mushroom[1] = new ImageIcon("Resources/mushroomF.png").getImage();
        dx = -30;
        x = SCREEN_WIDTH;
    }

    // Checks to see if the obstacle is visible on the screen
    public boolean isOnScreen() {
        if (x + SIZE < 0 || x > SCREEN_WIDTH){
            return false;
        }
        return true;
    }

    // Shifts the obstacle to the left
    public void move(){
        x += dx;
    }

    // Places obstacle at starting position
    public void restart(){
        x = SCREEN_WIDTH + 20;
    }

    // Draws the obstacle
    // The mushroom color depends on its position on the screen
    public void draw(Graphics g, int i){
        i = i % 2;
        g.drawImage(mushroom[i], x, GROUND, SIZE, SIZE, v);
    }

    public int getX() {
        return x;
    }
}