import greenfoot.*;

/**
 * Write a description of class StartScreen here.
 * 
 * @author Jiajia Li
 * @version (a version number or a date)
 */
public class StartScreen extends World
{

    /**
     * Constructor for objects of class StartScreen.
     * 
     */
    public StartScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
         super(700, 480, 1);
         
    }
   
    public void act()
    {
        if(Greenfoot.mouseClicked(this)) {
           //Greenfoot.setWorld(new EndScreenLose());
          Greenfoot.setWorld(new Beach());
        }
    }
}
