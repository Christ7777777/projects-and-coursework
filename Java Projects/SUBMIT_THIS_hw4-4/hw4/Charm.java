
package hw4;


/**
 * Element associated with parent object such as a Platform or Elevator that moves along with the
 * parent and remains a fixed distance above it.
 *
 * @author Varun Advani
 */
public class Charm extends BasicElement {

	  /**
	   * How many pixel away from parent's top right corner in x-direction
	   */
	  private int offset;

	  /**
	   * How many pixel away from parent's top right corner in y-direction
	   */
	  private int hover;

	  /**
	   * Constructs a new Charm.  Before being added to a parent element such as a Platform or Elevator,
	   * the x and y coordinates are zero. When a parent object is set, the initial x-coordinate becomes
	   * the parent's x-coordinate, plus the given offset, and the y-coordinate becomes the parent's
	   * y-coordinate, minus this element's height, minus the hover amount.
	   *
	   * @param width  element's width
	   * @param height element's height
	   * @param offset when a parent is set, this amount is added to the parent's x-coordinate to
	   *               calculate this element's x-coordinate
	   * @param hover  when a parent is set, this element's y-coordinate is the parent's y-coordinate,
	   *               minus this element's height, minus the hover amount
	   */
	  public Charm(int width, int height, int offset, int hover) {
	    super(0, 0, width, height);
	    this.offset = offset;
	    this.hover = hover;
	  }

	  @Override
	  public void update() {
		//update the frame count
	    super.update();
	    //set the charm at a fixed position from the parent
	    setPosition(getParent().getXReal() + offset, getParent().getYReal() - getHeight() - hover);
	  }

	  @Override
	  public void setParent(api.GameElement parent) {
	    super.setParent(parent);
	    //set the charm at a fixed position from the parent
	    setPosition(parent.getXReal() + offset, parent.getYReal() - getHeight() - hover);
	  }
	}
