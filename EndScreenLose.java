import greenfoot.*;
import java.awt.Color;

/**
 * Write a description of class EndScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndScreenLose extends World
{
    //lose courtesy of suntemple on freesound:
    //https://www.freesound.org/people/suntemple/sounds/253174/
    private GreenfootSound lose = new GreenfootSound("lose.wav");
    
    public EndScreenLose()
    {
        super(700, 480, 1);
        lose.play();
        Greenfoot.stop();
    }
    
 
}
