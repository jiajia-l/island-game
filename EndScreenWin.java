import greenfoot.*;

/**
 * Write a description of class EndScreenWin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndScreenWin extends World
{

    //win theme courtesy of Tuudurt on freesound:
    //https://www.freesound.org/people/Tuudurt/sounds/258142/
    private GreenfootSound win = new GreenfootSound("win.wav");
    /**
     * Constructor for objects of class EndScreenWin.
     * 
     */
    public EndScreenWin()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(700, 480, 1); 
        win.play();
        Greenfoot.stop();
    }
}
