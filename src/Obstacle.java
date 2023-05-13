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
    private CatDashViewer game;

    // Constructor
    public Obstacle(CatDashViewer v){
        this.v = v;
        mushroom = new Image[2];
        mushroom[0] = new ImageIcon("Resources/mushroomBlueF.png").getImage();
        mushroom[1] = new ImageIcon("Resources/mushroomF.png").getImage();
        dx = -30;
        // IS THIS A MAGIC NUMBER?
        x = 1200;
    }
    public boolean isOnScreen() {
        if (x + SIZE < 0 || x > SCREEN_WIDTH){
            return false;
        }
        return true;
    }

    public void move(){
        x += dx;
    }

    // Places obstacle at starting position
    public void restart(){
        x = 1220;
    }

    // Draws a given obstacle
    public void draw(Graphics g, int i){
        i = i % 2;
        g.drawImage(mushroom[i], x, GROUND, SIZE, SIZE, v);
    }

    public int getX() {
        return x;
    }

}

// NOTES:

// Each "set" of obstacles + pillars consists of multiple images. Some images are dangerous, others aren't.
// Each set will take up a row of a jagged array. For example, if there are 10 different sets, there will be ten rows
// In the viewer class, I will randomly generate a number between 1 - 10. Then, I will display the corresponding set
// How can I properly paint every obstacle/pillar? Do I have to categorize them in some way?
//For example, the floating stairs could simply be images that stretch to the bottom of the screen, but the "dangerous"
// Bottom left corner is calculated to be top left corner - height of step.

// For instances where
// For dangerous objects, utilize inheritance and have many different specific types of obstacles. Downward facing that go on top
// Of previous pillar, etc...

//As the game progresses, the distance between each set decreases? Or maybe they simply move across the screen more quickly
