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
        // Replace once background is there
        x = 200;
        y = GROUND;
        dy = 0;
    }

    //
    public void jump(){
        if (isOnGround()){
            dy = -50;
        }
    }

    public void gravity(){
        if (!isOnGround() || (dy < 0 && isOnGround())){
            y+=dy;
            dy += 9;
        }
        if (isOnGround()){
            y = GROUND;
        }
    }
    // Draws the character's icon differently based on
    public void draw(Graphics g, CatDashViewer v){
        g.drawImage(gameImage, x, y, v);
    }
    // Do I need this method at all?
    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean checkDead(Obstacle current){
        int left = x;
        int right = x + 200;
        int top = y;
        int bottom = y + 110;
        int obstacleLeft = current.getX();
        int obstacleRight = obstacleLeft + OBSTACLE_SIZE;
        int obstacleTop = 520 + OBSTACLE_SIZE;
        int obstacleBottom = 520;
        // Checks to see if the player + obstacle are overlapping
        if (left < obstacleRight && right > obstacleLeft && bottom > obstacleTop && top < obstacleBottom){
            isDead = true;
        }
        else{
            isDead = false;
        }
        return isDead;
    }

    public boolean isOnGround(){
        if (y >= GROUND){
            return true;
        }
        return false;
    }

}
