package hw4;

/**
 * Oscillates between the upper and lower bound in the X direction.
 * 
 * @author Varun Advani
 *
 */
public abstract class Xoscillator extends MovingElement {


    /**
     * Initial lower/left bound
     */

    private double minBound = Double.NEGATIVE_INFINITY;
    /**
     * Initial upper/right bound
     */
    private double maxBound = Double.POSITIVE_INFINITY;

    /**
     * Constructor for Xoscillator
     * @param x
     * @param y
     * @param width
     * @param height
     */
    
    public Xoscillator(double x, double y, int width, int height) {
        super(x, y, width, height);
    }
    /**
     * Getter for the min bound value(lower bound/left bound) that the element can't move pass
     *
     * @return lower bound/left bound
     */
    public double getMin() {
        return minBound;
    }

    /**
     * Getter for the max bound value(upper bound/right bound) that the element can't move pass
     *
     * @return upper bound/right bound
     */
    public double getMax() {
        return maxBound;
    }

    /**
     * Set the range that the element can move in a direction.
     *
     * @param min lower bound/left bound
     * @param max upper bound/right bound
     */
    public void setBounds(double min, double max) {
        minBound = min;
        maxBound = max;
    }
    
    
    /**
     * Oscillation in the X direction
     */
    @Override
    public void update() {
        // Left bound passed then reset to left bound and change direction to right
        if (getXReal() <= getMin()) {
            setPosition(getMin(), getYReal());
            //Reverse the velocity
            setVelocity(Math.abs(getDeltaX()), getDeltaY());
        }
        // Right bound passed then reset to right bound and change direction to left
        else if (getXReal() + getWidth() >= getMax()) {
            setPosition(getMax() - getWidth(), getYReal());
            //Reverse the velocity
            setVelocity(-Math.abs(getDeltaX()), getDeltaY());
        }		   
}
}
