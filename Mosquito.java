import greenfoot.*;

/**
 * Write a description of class Mosquito here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mosquito extends Enemy
{
    int points = 10;
    int maxDamage = 10;
    /**
     * Act - do whatever the Mosquito wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        moveRandom();
    }    
    
     public int getPoints()
    {
        return 10;
    }
}
