import javax.swing.*;
import java.awt.*;

public class Icon {
    private Image gameImage;
    private Image deathImage;
    private int x;
    private int y;
    private CatDashViewer v;
    private boolean isDead;
    private static final int DY = 20;

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
        y = 470;
    }

    public void jump(){
        // Makes the icon move up
        //while (y > 100){
           // y -= DY;
       // }
        // Makes the icon move back down
        //while (y < 600){
          //  y += DY;
       // }
        // Create a parabola for the icon's motion
        // How should I use 9.8 m / s^2?

        y = 100;
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
}
