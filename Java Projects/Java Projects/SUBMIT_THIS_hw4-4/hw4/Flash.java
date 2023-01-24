package hw4;
/**
 * Implementation of GameElement that does not move. Instead, it is intended to appear on the screen
 * for a fixed number of frames.
 *
 * @author Varun Advani
 */

public class Flash extends BasicElement {

    /**
     * Remaining time on screen.
     */
    private int life;

    /**
     * Constructs a new Flash.
     *
     * @param x           x-coordinate of upper left corner
     * @param y           y-coordinate of upper left corner
     * @param width       element's width
     * @param height      element's height
     * @param initialLife the number of frames until this element marks itself for deletion
     */
    public Flash(double x, double y, int width, int height, int initialLife) {
        super(x, y, width, height);
        life = initialLife;
    }

    @Override
    public void update() {
    	//update the frame count
        super.update();
        life--;
        //mark for deletion if frames expire
        if(life == 0) {
            markForDeletion();
        }
    }
    public int getRemainingLife() {
    	return life;
    }
}