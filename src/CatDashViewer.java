// By Kate Little
// 5/12/23
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

public class CatDashViewer extends JFrame implements MouseListener, MouseMotionListener, KeyListener {
    private final int WINDOW_WIDTH = 1200;
    private final int WINDOW_HEIGHT = 800;
    private CatDash cat;
    private Image[] icons;
    private Image background;
    private Image welcomeTitle;
    private Image lossImage;
    private static final int WIDTH1 = 420;
    private static final int WIDTH2 = 440;
    private static final int HEIGHT = 210;
    private static final int WIDTH = 150;
    private Image victoryImage;
    private ObstacleArray manager;
    private Icon player;

    // Constructor
    public CatDashViewer(CatDash cat, ObstacleArray manager){
        this.cat = cat;
        this.manager = manager;
        icons = new Image[2];
        background = new ImageIcon("Resources/background.png").getImage();
        welcomeTitle = new ImageIcon("Resources/selectCharacter.png").getImage();
        lossImage = new ImageIcon("Resources/youLose.png").getImage();
        // Icon image options:
        icons[0] = new ImageIcon("Resources/pusheenRun.png").getImage();
        icons[1] = new ImageIcon("Resources/pipRun.png").getImage();

        // Sets up the window and the buffer strategy.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Cat Dash");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);
        // For mouse/key listeners
        addMouseListener(this);
        addKeyListener(this);
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
        if (!cat.isStarted()){
            g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT,this);
            g.drawImage(welcomeTitle, 0, -100, WINDOW_WIDTH, WINDOW_HEIGHT,this);
            // Draws icon options
            for (int i = 0; i < 2; i++){
                // Custom width + height since they're too large for screen
                // The position is designed so that they are centered on screen
                g.drawImage(icons[i], (i) * 400 + 150, WIDTH2 - 20, WIDTH2, HEIGHT, this);
            }
        }
        // If player has died:
        if (player != null && player.isDead()){
            g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT,this);
            g.drawImage(lossImage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT,this);
        }
        // Once user has selected their player, begin the game
        else if (cat.isStarted() && !player.isDead() && !cat.isEnded()){
            g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT,this);
            player.draw(g, this);
            // While player is alive, draw the obstacles
            manager.drawAll(g);
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

        int pusheenLeftX = WIDTH * 2;
        int pusheenRightX = WIDTH + WIDTH1;
        int pusheenTopY = WIDTH * 2;
        int pusheenBottomY = WIDTH * 2 + WIDTH2;

        int pipLeftX = 500;
        int pipRightX =  500 + WIDTH1;
        int pipTopY = WIDTH * 2;
        int pipBottomY =  WIDTH * 2 + WIDTH2;
        // If the mouse presses the pusheen character before game start
        if (!cat.isStarted()){
            if (x > pusheenLeftX && x < pusheenRightX && y > pusheenTopY && y < pusheenBottomY){
                player = new Icon("Pusheen", this);
            }
            // If the mouse presses the pip character
            else if (x > pipLeftX && x < pipRightX && y > pipTopY && y < pipBottomY){
                player = new Icon("Pip", this);
            }
        }
        repaint();
        cat.setStarted(true);
    }

    public Icon getPlayer() {
        return player;
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
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            player.jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
