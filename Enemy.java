import greenfoot.*;

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Mover
{
    private int X;
    private int direction = -1;
    private static final int SPEED = 3;
    private int maxX, minX;
   
    protected int maxDamage = 0;
    protected int points = 0;
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        followPlayer();
    }    
  
    
    /**
     * Moves around the game screen randomly. For mosquitoes.
     */
    public void moveRandom()
    {
        move(2);
        if(Greenfoot.getRandomNumber(100) < 10)
        {
            turn(Greenfoot.getRandomNumber(90)-45);
        }
        if(getX() <= 5 || getX() >= getWorld().getWidth() - 5)
        {
            turn(180);
        }
        if(getY() <= 5 || getY() >= getWorld().getHeight() - 5)
        {
            turn(180);
        }
    }
    
    public void moveLR(int minX, int maxX)
    {
        this.maxX = maxX;
        this.minX = minX;
        
           X = getX();
        if (direction == 1)
        {
            //if (X < getWorld().getWidth()-1)
            if (X < maxX)
              setLocation (X + SPEED, getY());
            else
              direction = -1;
        }
        else if (direction == -1)
        {
            if (X >= minX)
                setLocation (X - SPEED, getY());
            else
                direction = 1;
             
        }
     }
    
    public void followPlayer()
    {
        int dist = 100;
        Actor closest = null;
         
        if(!getObjectsInRange(dist, Player.class).isEmpty())
        {
        for (Object obj: getObjectsInRange(dist, Player.class))
        {
            Actor guy = (Actor) obj;
            int guyDist = (int) Math.hypot(guy.getX() - getX(), guy.getY() - getY());
            if (closest == null || guyDist< dist)
            {
                    closest = guy;
                    dist = guyDist;
            }
        }
        turnTowards(closest.getX(),closest.getY());
        }   
    }
    
    public int getPoints()
    {
        return points;
    }
}
