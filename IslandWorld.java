import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * IslandWorld provides all the hook methods for the various scenes on the Island.
 * 
 * @author Greenfoot team, Jordan Cohen
 * @author Jiajia Li
 * @version 0.5
 * 
 */
public class IslandWorld extends World
{
    protected int currentRoom = 0;
    protected int overriderX = -1;
    protected int overrideY = -1;
    protected boolean music = false;
    
    protected Player play = new Player();
    protected int points;
    protected HealthBar bar = new HealthBar("", "", 100, 100);
    protected HealthBarFace face = new HealthBarFace(play);

    
    //ouch sound courtesy of Under7dude on fressound:
    //https://www.freesound.org/people/Under7dude/sounds/163441/
    private GreenfootSound ouch = new GreenfootSound("ouch.wav");

    
    /**
     * Constructor for objects of class IslandWorld, including the health bar a
     * and its corresponding face.
     */
    public IslandWorld()
    {    

        super(700, 480, 1); 
        bar.setMinimumValue(0);
        addObject(bar, 120, 33);
        addObject(face, 40, 33);
        
    }
       
    public void act()
    {
        
        // Forced movement results from standing on a moving platform
        checkForcedMovement();
        checkEnemyHit();    
        checkHealth();
        // Check for death, level completion and game completion:
        if (checkDeath() && play.checkDeath())
        {
            endGameLose();
        }
     
    }    
    
    /**
     * Method that will be implemented by each of the subclasses to switch world.
     */
    public void switchWorld(){
        
    }
  
    
    /**
     * Checks the player's health with each turn and updates the health bar and 
     * face to reflect number.
     */
    public void checkHealth()
    {
        if (bar.getValue() == bar.getMinimumValue())
        {
            if (getObjects(EndScreenLose.class).isEmpty()) endGameLose();
            return;
        }
        bar.setValue(play.getHealth());
    }
    
    /**
     * Calls getSurface() from the Pengu class. If the player is standing on
     * a moving platform, and nothing is blocking their path, this method
     * will move the player.
     */
    public void checkForcedMovement()
    {
        Actor temp = play.getSurface();
        if (temp != null)
        {
            int currX = play.getX();
            int currY = play.getY();
            if (temp instanceof Brick)
            {
                Brick surfaceUnderMe = (Brick)temp;  
                int move = surfaceUnderMe.getMove();
                //play.setLocation(currX + move, currY);
                play.tryMove(move);
            }
            else if (temp instanceof UpDown)
            {
                UpDown surfaceUnderMe = (UpDown)temp;
                int move = surfaceUnderMe.getMove();
                play.verticalMove(move);
            }
        }   
    }

    /**
     * This method checks for an intersecting enemy
     * If it finds either an edible enemy (good) or a killer enemy (bad)
     * it acts appropriately - either adding a point, or killing the player
     */
    public void checkEnemyHit()
    {
        Enemy hit = play.getEnemy();
        if (hit != null)
        {
            play.setHealth(-hit.getPoints());
            ouch.setVolume(60);
                        ouch.play();

            removeObject(hit);
        }
    }

    public boolean checkDeath()
    {
        return play.checkDeath();
    }

    
    /**
     * Triggers the losing screen - tell player how many bees they were successful
     * in eating.
     */
    public void endGameLose()
    {
        Greenfoot.setWorld(new EndScreenLose());
        Greenfoot.stop();
    }
      
    private void endGameWin()
    {
        Greenfoot.setWorld(new EndScreenWin());
        Greenfoot.stop();
    }
  
}
