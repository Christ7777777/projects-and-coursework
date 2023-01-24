package hw4;

import api.GameElement;
import java.awt.Rectangle;
/**
 * Minimal concrete extension of GameElement. The <code>update</code> method in this implementation
 * just increments the frame count.
 * 
 * BasicElement is a direct implementation of GameElement
 * 
 * Xoscillator is the abstract class, that exploits the Oscillation of Platform, and Lurker, in the X direction.
 * 
 * Elevator inherits Platform because of the common addChild function. It has its own overriden update(), that oscillates in the Y direction
 * 
 * Charm, MovingElement, and Flash inherit attributes from BasicElement
 * 
 * Xoscillator, and Missile inherit attributes from MovingElement
 * 
 * @author Varun Advani
 */

public class BasicElement extends GameElement {

    /**
     * x and y represent the top left coordinate of the element
     */
    private double x, y;

    /**
     * Width and height of the element
     */
    private int width, height;

    /**
     * Current frame count
     */
    private int frameCount;

    /**
     * Is the element marked for deletion
     */
    private boolean isMarked = false;

    /**
     * Parent of the element
     */
    private GameElement parent;

    /**
     * Constructs a new BasicElement.
     *
     * @param x      x-coordinate of upper left corner
     * @param y      y-coordinate of upper left corner
     * @param width  element's width
     * @param height element's height
     */
    public BasicElement(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getXInt() {
        return (int) Math.round(x);
    }

    @Override
    public int getYInt() {
        return (int) Math.round(y);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(getXInt(), getYInt(), width, height);
    }

    @Override
    public void setPosition(double newX, double newY) {
        x = newX;
        y = newY;
    }

    @Override
    public double getXReal() {
        return x;
    }

    @Override
    public double getYReal() {
        return y;
    }

    @Override
    public void update() {
    	//update the frame count
        frameCount++;
    }

    @Override
    public int getFrameCount() {
        return frameCount;
    }

    @Override
    public boolean isMarked() {
        return isMarked;
    }

    @Override
    public void markForDeletion() {
        isMarked = true;
    }

    @Override
    public boolean collides(GameElement other) {
        return getRect().intersects(other.getRect());
    }

    /**
     * Setter for parent
     * @param parent
     */
    protected void setParent(GameElement parent) {
        this.parent = parent;
    }

    /**
     * Getter for parent
     *
     * @return the element's parent
     */
    protected GameElement getParent() {
        return parent;
    }
}
