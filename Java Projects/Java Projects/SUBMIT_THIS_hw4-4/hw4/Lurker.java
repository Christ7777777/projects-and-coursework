package hw4;
/**
 * Moving element associated with a Platform or Elevator.  Optionally, a Lurker can be given a
 * nonzero horizontal velocity and it will oscillate between the left and right edges of the
 * Platform or Elevator.
 *
 * @author Varun Advani
 */

public class Lurker extends Xoscillator {

    /**
     * Starting point relative to the parent(in x-direction)
     */
    private int initialOffset;

    /**
     * Constructs a new Lurker.  Before being added to a parent element such as a Platform or
     * Elevator, the x and y coordinates are zero. When a parent element is set, the initial
     * x-coordinate becomes the parent's x-coordinate, plus the given offset, and the y-coordinate
     * becomes the parent's y-coordinate, minus this element's height.
     *
     * @param width         element's width
     * @param height        element's height
     * @param initialOffset when a parent is set, this amount is added to the parent's x-coordinate
     *                      to calculate this element's initial x-coordinate
     */
    public Lurker(int width, int height, int initialOffset) {
        super(0, 0, width, height);
        this.initialOffset = initialOffset;
    }

    @Override
    public void setParent(api.GameElement parent) {
    	//set the parent
        super.setParent(parent);
        //set the new bounds of the parent
        setBounds(parent.getXReal(), parent.getXReal() + parent.getWidth());
        //set the new position of the parent
        setPosition(parent.getXReal() + initialOffset, parent.getYReal() - getHeight());
        
    }

    @Override
    public void update() {
    	//set the new offset
    	initialOffset += getDeltaX();
        // Update to the new bounds of the lurker with respect to the platform
        setBounds(getParent().getXReal(), getParent().getXReal() + getParent().getWidth());
        // Move along with parent
        setPosition(getParent().getXReal() + initialOffset, getParent().getYReal() - getHeight());
        //Oscillation in the X direction
        super.update();
}
}
