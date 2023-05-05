import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

public class CatDashViewer extends JFrame implements MouseListener, MouseMotionListener, KeyListener {
    private final int WINDOW_WIDTH = 1200;
    private final int WINDOW_HEIGHT = 800;
    private CatDash c;
    private Image[] icons;
    private Image[] obstacleImages;
    private Image[] towerImages;
    private Image background;
    private Image welcomeTitle;
    private Icon player;

    // Constructor
    public CatDashViewer(CatDash c){
        this.c = c;
        icons = new Image[4];
        background = new ImageIcon("Resources/background.png").getImage();
        welcomeTitle = new ImageIcon("Resources/selectCharacter.png").getImage();
        icons[0] = new ImageIcon("Resources/pusheen running.png").getImage();
        icons[1] = new ImageIcon("Resources/pusheenHit.png").getImage();
        icons[2] = new ImageIcon("Resources/pip running.png").getImage();
        icons[3] = new ImageIcon("Resources/pip hit.png").getImage();
        // Sets up the window and the buffer strategy.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Cat Dash");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);
        // For mouse listener
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public Image[] getIcons() {
        return icons;
    }

    public void paint(Graphics g){
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null)
            return;
        Graphics g2 = null;
        try {
            g2 = bf.getDrawGraphics();
            myPaint(g2);
        }
        finally {
            g2.dispose();
        }
        bf.show();
        Toolkit.getDefaultToolkit().sync();
    }

    // Used in paint to do the actual painting
    public void myPaint(Graphics g){
        // Before game starts:
        if (!c.isStarted()){
            g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT,this);
            g.drawImage(welcomeTitle, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT,this);
            // Draw icon options
            for (int i = 0; i < 4; i+= 2){
                // Custom width + height since they're too large for screen
                g.drawImage(icons[i], (i) * 200 + 100, 300, 420, 440, this);
            }
        }
        // Once user has selected their player, begin the game
        else if (c.isStarted()){
            g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT,this);
            player.draw(g, this);
            // draw all on-screen obstacles
        }
    }

    // MouseListener + MouseMotionListener methods
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int pusheenLeftX = 150;
        int pusheenRightX = 150 + 420;
        int pusheenTopY = 300;
        int pusheenBottomY = 300 + 440;

        int pipLeftX = 500;
        int pipRightX =  500 + 420;
        int pipTopY = 300;
        int pipBottomY =  300 + 440;
        // If the mouse presses the pusheen character
        while (c.isStarted() == false){
            if (x > pusheenLeftX && x < pusheenRightX && y > pusheenTopY && y < pusheenBottomY){
                // CAN I INITIALIZE THE PLAYER HERE? OR SHOULD IT BE IN CATDASH CLASS?
                player = new Icon("Pusheen", this);
            }
            // If the mouse presses the pip character
            else if (x > pipLeftX && x < pipRightX && y > pipTopY && y < pipBottomY){
                player = new Icon("Pip", this);
            }
            c.setStarted(true);
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    // KeyListener methods
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // The keyCode lets you know which key was pressed
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_UP)
        {
            player.jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
