import java.awt.*;

public class Obstacle {
    private Image[] possibleImages;
    private int x;
    private int y;
    private int dx;
    private int dy;
    private boolean isOnScreen;
    private boolean isDangerous;
    private CatDashViewer game;

    // Constructor
    public Obstacle(CatDashViewer game, boolean isDangerous){
        this.game = game;
        this.isDangerous = isDangerous;

    }
    public boolean isOnScreen() {
        return isOnScreen;
    }

    public boolean isDangerous() {return isDangerous; }

    private void move(){

    }

}

// NOTES:
// If the obstacle is a spike, I set it to dangerous. For dangerous objects, any time it is touched, the user dies
// For non-dangerous objects, they are only dangerous when hit (Top or bottom left corners touched)

//How do I establish the location of each on-screen obstacle?

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

//Include a jumping