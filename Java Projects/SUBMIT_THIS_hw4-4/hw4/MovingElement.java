package hw4;
/**
 * Concrete implementation of GameElement in which the <code>update</code> method updates the
 * position each frame according to a <em>velocity</em> vector (deltaX, deltaY).  The units are
 * assumed to be "pixels per frame".
 *
 * @author Varun Advani
 */
public class MovingElement extends BasicElement {
    
	/**
	 * Velocity in the X direction
	 */
    private double DeltaX;
    /**
     * Velocity in the Y direction
     */
    private double DeltaY;
    

    /**
     * Constructs a MovingElement with a default velocity of zero in both directions.
     *
     * @param x      x-coordinate of upper left corner
     * @param y      y-coordinate of upper left corner
     * @param width  object's width
     * @param height object's height
     */
    public MovingElement(double x, double y, int width, int height) {
        super(x, y, width, height);
        DeltaX = 0;
        DeltaY = 0;
    }

    /**
     * Set the current speed of each direction.
     *
     * @param DeltaX Speed in x-direction
     * @param DeltaY Speed in y-direction
     */
    public void setVelocity(double DeltaX, double DeltaY) {
        this.DeltaX = DeltaX;
        this.DeltaY = DeltaY;
    }

    /**
     * Getter for speed in x-direction
     *
     * @return the speed in x-direction
     */
    public double getDeltaX() {
        return DeltaX;
    }

    /**
     * Getter for speed in y-direction
     *
     * @return the speed in y-direction
     */
    public double getDeltaY() {
        return DeltaY;
    }
    /**
     * sets the new position of the element with respect to the speed in the X and Y direction
     */
    @Override
    public void update() {
    	//update the frame count
        super.update();
        //Move the element
        setPosition(getXReal() + DeltaX, getYReal() + DeltaY);
    }
}
