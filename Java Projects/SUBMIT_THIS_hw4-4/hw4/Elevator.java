package hw4;
/**
 * An Elevator is a GameElement with two distinctive behaviors.  First, it can be set up to move
 * vertically within a fixed set of boundaries. On reaching a boundary, the y-component of its
 * velocity is reversed. Second, it maintains a list of <em>child</em> elements whose basic motion
 * all occurs relative to the Elevator.
 *
 * @author Varun Advani
 */
public class Elevator extends Platform {

    /**
     * Constructs a new Elevator.  Initially the upper and lower boundaries are
     * <code>Double.NEGATIVE_INFINITY</code> and
     * <code>Double.POSITIVE_INFINITY</code>, respectively.
     *
     * @param x      x-coordinate of initial position of upper left corner
     * @param y      y-coordinate of initial position of upper left corner
     * @param width  element's width
     * @param height element's height
     */
    public Elevator(double x, double y, int width, int height) {
        super(x, y, width, height);
    }
    @Override
    /**
     * Oscillation in the Y direction
     */
	  public void update() {
		   //Move the element
    	   setPosition(getXReal() + getDeltaX(), getYReal() + getDeltaY());
		  //Reach the lower bound
		  if (getYReal() <= getMin()) {
			  //Change the direction and velocity
		  setPosition(getXReal(), getMin());
		  //Reverse the velocity
		  setVelocity(getDeltaX(), Math.abs(getDeltaY()));
		  } 
		  //Reach the Upper bound
		  else if (getYReal() + getHeight() >= getMax()) {
			  //Change the direction and velocity
		  setPosition(getXReal(), getMax() - getHeight());
		  //Reverse the velocity
		  setVelocity(getDeltaX(), -Math.abs(getDeltaY()));
		  }
		  //update each child
		  for (api.GameElement child : getChildren()) {
		  child.update();
		  }
		  }
    
}