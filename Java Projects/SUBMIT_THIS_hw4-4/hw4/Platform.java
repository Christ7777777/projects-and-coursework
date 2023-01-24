package hw4;

import java.util.ArrayList;

/**
 * A Platform is a GameElement with two distinctive behaviors.  First, it can be set up to move
 * horizontally within a fixed set of boundaries. On reaching a boundary, the x-component of its
 * velocity is reversed. Second, it maintains a list of <em>child</em> elements whose basic motion
 * all occurs relative to the Platform.
 *
 * @author Varun Advani
 */
public class Platform extends Xoscillator {
	
	 /**
     * Container for it's children
     */
    private ArrayList <api.GameElement> children = new ArrayList<>();
    
    /**
     * Constructs a new Platform. Initially the left and right boundaries are
     * <code>Double.NEGATIVE_INFINITY</code> and
     * <code>Double.POSITIVE_INFINITY</code>, respectively.
     *
     * @param x      x-coordinate of initial position of upper left corner
     * @param y      y-coordinate of initial position of upper left corner
     * @param width  object's width
     * @param height object's height
     */
    public Platform(double x, double y, int width, int height) {
        super(x, y, width, height);
    }
    
    /**
     * Oscillation in the X direction
     */
    @Override
    public void update() {
    	//Move the element
    	setPosition(getXReal() + getDeltaX(), getYReal() + getDeltaY());
    	//Oscillation in the X direction
        super.update();
         //Update each child element
        for (api.GameElement child : getChildren()) {
  		  child.update();
  		  }	  
    }
    /**
     * Add a child
     *
     * @param charm
     * Add child element charm to the list
     */
    public void addChild(Charm charm) {
        children.add(charm);
        charm.setParent(this);
        
    }
    /**
     * Add a child
     *
     * @param lurker
     * Add child element charm to the list
     */
    public void addChild(Lurker lurker) {
    	children.add(lurker);
        lurker.setParent(this);
    }
    
    /**
     * Check each of the children, remove if it's marked for deletion
     */
    public void deleteMarkedChildren() {
    	for(int i = 0; i< children.size();i++) {
    		if(children.get(i).isMarked()) {
    			children.remove(i);
    		}
    	}
    }

    /**
     * Getter for children
     *
     * @return An ArrayList of Basic Element containing children element
     */
    public ArrayList<api.GameElement> getChildren() {
        return children;
    }
    
}