import greenfoot.*;

import java.util.List;
import java.util.ArrayList;

/**
 * Mover. This is the base class for all moving objects. In addition to the standard Actor
 * methods, it provides the ability to move and turn.
 * 
 * @author Pieter Herinckx
 * @version 1.0
 */
public class Mover extends Actor
{
    private static int speed = 4;
    private boolean movingRight = true;
    
    /**
     * Turn 90 degrees to the right (clockwise).
     */
    public void turn()
    {
        turn(90);
    }
    
    /**
     * Turn 'angle' degrees towards the right (clockwise).
     */
    public void turn(int angle)
    {
        setRotation(getRotation() + angle);
    }
    
    /**
     * Makes the actor move automaticly from right to left and from left to right and use a specified image
     */
    public void autoMove(String imageRight, String imageLeft)
    {
        if(movingRight){
            moveRight();
            if(atWorldEdge()){
                movingRight = false;
                setImage(imageLeft);
            }
        }
        else{
            moveLeft();
            if(atWorldEdge()){
                movingRight = true;
                setImage(imageRight);
            }
        }
    }  
    
    /**
     * Set the image for this actor to the specified image.
     */
    public void setImage(String image)
    {
        setImage(image);
    }
    
    /**
     * Check wether a given key is pressed
     */
    public boolean isKeyDown(String key)
    {
        return Greenfoot.isKeyDown(key);
    }

    /**
     * Move to the right at the current speed.
     */    
    public void moveRight()
    {
        setLocation ( getX() + speed, getY() );
        movingRight = true;
    }

    /**
     * Move to the left at the current speed.
     */    
    public void moveLeft()
    {
        setLocation ( getX() - speed, getY() );
        movingRight = false;
    }
    
    /**
     * Move up at the current speed.
     */    
    public void moveUp()
    {
        setLocation ( getX(), getY() - speed );
    }
     
    /**
     * Move down at the current speed.
     */    
    public void moveDown()
    {
        setLocation ( getX(), getY() + speed );
    }
    
    /**
     * Test if we are at one of the edges of the world. Return true if we are.
     */
    public boolean atWorldEdge()
    {
        if(getX() == 0 || getX() == getWorld().getWidth()-1)
            return true;
        if(getY() == 0 || getY() == getWorld().getHeight()-1)
            return true;
        else
            return false;
    }
    
    /**
     * Return true if we touch an object of class 'clss'.
     * False if there is no such object here.
     */
    public boolean touches(Class clss)
    {
        Actor actor = getOneIntersectingObject(clss);
        return actor != null;
    }

    /**
     * Try to remove an object of class 'clss'. This is only successful if we
     * are currently touching such an object. Otherwise this method does
     * nothing.
     */
    public void remove(Class clss)
    {
        Actor actor = getOneIntersectingObject(clss);
        if(actor != null) {
            getWorld().removeObject(actor);
        }
    }    
}