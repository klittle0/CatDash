// By Kate Little
// 5/12/23
import javax.swing.*;
import java.awt.*;

public class Icon {
    private Image gameImage;
    private int x;
    private int y;
    private static final int OBSTACLE_SIZE = 80;
    private CatDashViewer v;
    private boolean isDead;
    private int dy;
    private static final int GROUND = 505;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 110;
    private static final int OBSTACLE_GROUND = 520;

    // Constructor
    public Icon(String type, CatDashViewer v){
        this.v = v;
        isDead = false;
        if (type.equals("Pusheen")){
            gameImage = new ImageIcon("Resources/pusheenRun.png").getImage();
        }
        else {
            gameImage = new ImageIcon("Resources/pipRun.png").getImage();
        }
        x = 200;
        y = GROUND;
        dy = 0;
    }

    // Sets the player's initial speed
    public void jump(){
        if (isOnGround()){
            dy = -50;
        }
    }

    // Slows the player's jump + causes them to fall back to the ground
    public void gravity(){
        if (!isOnGround() || (dy < 0 && isOnGround())){
            y+=dy;
            dy += 9;
        }
        if (isOnGround()){
            y = GROUND;
        }
    }
    // Draws the player's icon
    public void draw(Graphics g, CatDashViewer v){
        g.drawImage(gameImage, x, y, v);
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    // Returns true if the player is dead
    // Changes the value of isDead
    public boolean checkDead(Obstacle current){
        int left = x;
        int right = x + WIDTH;
        int top = y;
        int bottom = y + HEIGHT;
        int obstacleLeft = current.getX();
        int obstacleRight = obstacleLeft + OBSTACLE_SIZE;
        int obstacleTop = OBSTACLE_GROUND + OBSTACLE_SIZE;
        int obstacleBottom = OBSTACLE_GROUND;
        // Checks to see if the player + obstacle are overlapping
        if (left < obstacleRight && right > obstacleLeft && bottom > obstacleTop && top < obstacleBottom){
            isDead = true;
        }
        else{
            isDead = false;
        }
        return isDead;
    }

    // Returns true if player is on the ground
    public boolean isOnGround(){
        if (y >= GROUND){
            return true;
        }
        return false;
    }
}
