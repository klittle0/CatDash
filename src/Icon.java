import javax.swing.*;
import java.awt.*;

public class Icon {
    private Image gameImage;
    private Image deathImage;
    private int x;
    private int y;
    private CatDashViewer v;
    private boolean isDead;
    private int dy;
    private static final int GROUND = 470;

    // Constructor
    public Icon(String type, CatDashViewer v){
        this.v = v;
        isDead = false;
        if (type.equals("Pusheen")){
            gameImage = new ImageIcon("Resources/pusheen running.png").getImage();
            deathImage = new ImageIcon("Resources/pusheenHit.png").getImage();
        }
        else {
            gameImage = new ImageIcon("Resources/pip running.png").getImage();
            deathImage = new ImageIcon("Resources/pip hit.png").getImage();
        }
        // Replace once background is there
        x = 200;
        y = GROUND;
    }

    //
    public void jump(){
        dy = -30;
    }

    public void gravity(){
        if (!isOnGround() || (dy < 0 && isOnGround())){
            y+=dy;
            dy += 6;
        }
        if (isOnGround()){
            y = GROUND;
        }
    }
    // Draws the character's icon differently based on
    public void draw(Graphics g, CatDashViewer v){
        if (!isDead){
            g.drawImage(gameImage, x, y, 200, 200, v);
        }
        else{
            g.drawImage(deathImage, x, y, 200, 200, v);
        }
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean checkDead(Obstacle current){
        // If right of icon hits obstacle and icon is not highter than obstacle
        if ((x + 200) == current.getX() && (y + 200) >= current.getHeight()){
            isDead = true;
        }
        isDead = false;
        return isDead;
    }

    public int getDy() {
        return dy;
    }

    public int getY() {
        return y;
    }

    public boolean isOnGround(){
        if (y >= GROUND){
            return true;
        }
        return false;
    }

}
