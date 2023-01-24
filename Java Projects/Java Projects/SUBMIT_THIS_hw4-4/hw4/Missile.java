package hw4;
/**
 * Moving element in which the vertical velocity is adjusted each frame by a gravitational constant
 * to simulate gravity. It may also be put into a "ballistic" state in which no other modifications
 * to velocity take place other than gravity.
 *
 * @author Varun Advani
 */
public class Missile extends MovingElement {

    /**
     * State of ballistic
     */
    private boolean isBallistic = false;

    /**
     * Gravity affect deltaY
     */
    private double gravity = 0;

    /**
     * Constructs a new Missile.
     *
     * @param x      x-coordinate of upper left corner
     * @param y      y-coordinate of upper left corner
     * @param width  element's width
     * @param height element's height
     */
    public Missile(double x, double y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void setVelocity(double newDeltaX, double newDeltaY) {
        if(!isBallistic) {
        super.setVelocity(newDeltaX, newDeltaY);
        }
    }

    /**
     * Setter for gravity
     *
     * @param newGravity
     */
    public void setGravity(double newGravity) {
        gravity = newGravity;
    }


    /**
     * Set the state of ballistic. True = only affect by gravity
     *
     * @param isBallistic
     */
    public void setBallistic(boolean isBallistic) {
        this.isBallistic = isBallistic;   
    }

    /**
     * Getter of ballistic state
     *
     * @return state of ballistic
     */
    public boolean isBallistic() {
        return isBallistic;
    }

    @Override
    public void update() {
    	//Move the element
    	super.update();
    	// set new velocity with respect to gravity
        super.setVelocity(getDeltaX(), getDeltaY() + gravity);
       
 }
}
