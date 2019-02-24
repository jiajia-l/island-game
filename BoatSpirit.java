import greenfoot.*;

/**
 * Write a description of class BoatSpirit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BoatSpirit extends Enemy
{
    private int X;
    private int direction = -1;
    private int speed = 0; 
    int minX = 0;
    int maxX = 0;
    
    public BoatSpirit(int minX, int maxX, int speed){
        this.minX = minX;
        this.maxX = maxX;
        this.speed = speed;
    }
    
   
    int points = 25;
    
    /**
     * Act - do whatever the BoatSpirit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
           X = getX();
        if (direction == 1)
        {
            //if (X < getWorld().getWidth()-1)
            if (X < maxX)
              setLocation (X + speed, getY());
            else
              direction = -1;
        }
        else if (direction == -1)
        {
            if (X >= minX)
                setLocation (X - speed, getY());
            else
                direction = 1;
             
        }
    }    
    
    public int getPoints()
    {
        return points;
    }
}
