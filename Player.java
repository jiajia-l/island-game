import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * A small demo of a jumping movement.
 * 
 * @author M Kolling
 * @version 1.0
 * 
 * Features Added, Updated and Improved by Jordan Cohen
 * Tweaked by Jiajia Li
 */
public class Player extends Mover
{

    private int health = 0;

    // "physics" variables
    private int speed = 20;      // running speed (sideways)
    private int vSpeed = 0;
    private int acceleration = 1;
    private int jumpStrength = 12;
    // Important game state info
    private boolean jumping = false;
    private boolean dead = false;
    // Bottom of screen, used for death
    private int floor;
    // Variables for reducing CPU load oprivate in enemy collision detection
    private int reduceCollisionDetection = 3;
    private int currCollDetection = 0;
    //animation
    private int counter = 0;
    private int imageCount = 4; // num of images in animation
    private int actsPerImage;//act cycles per animation
    private int animationActCounter = 0;//act counter for animation
    private int frame;
    private boolean walking;
    private boolean facingRight;
    //eating sound courtesy of Koops on freesound:
    //https://www.freesound.org/people/Koops/sounds/20280/
    private GreenfootSound eating = new GreenfootSound("eating.wav");
    private final GreenfootImage rwalk0 = new GreenfootImage("walk0.png");
    private final GreenfootImage rwalk1 = new GreenfootImage("walk1.png");
    private final GreenfootImage rwalk2 = new GreenfootImage("walk2.png");
    private final GreenfootImage rwalk3 = new GreenfootImage("walk3.png");
    private final GreenfootImage rwalk4 = new GreenfootImage("walk4.png");
    private final GreenfootImage rwalk5 = new GreenfootImage("walk5.png");
    private final GreenfootImage rstand = new GreenfootImage("stand.png");  
    private final GreenfootImage lwalk0 = new GreenfootImage(rwalk0);
    private final GreenfootImage lwalk1 = new GreenfootImage(rwalk1);
    private final GreenfootImage lwalk2 = new GreenfootImage(rwalk2);
    private final GreenfootImage lwalk3 = new GreenfootImage(rwalk3);
    private final GreenfootImage lwalk4 = new GreenfootImage(rwalk4);
    private final GreenfootImage lwalk5 = new GreenfootImage(rwalk5);
    private final GreenfootImage lstand = new GreenfootImage(rstand);
    int imageNumber;
    
    
    public Player()
    {
        setImage(rwalk1);
        walking = false;
        facingRight = true;
        floor = 475;
        this.health = 100;
        
        lwalk0.mirrorHorizontally();
        lwalk1.mirrorHorizontally();
        lwalk2.mirrorHorizontally();
        lwalk3.mirrorHorizontally();
        lwalk4.mirrorHorizontally();
        lwalk5.mirrorHorizontally();
    }
    
    /**
     * Act - do whatever the Player wants to do. This method  is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     *
     */
    public void act() 
    {
        checkKeys();
        checkFall();
        checkNextScene();
        eat();

    }
    
    private void attack()
    {
        if(Greenfoot.mouseClicked(null))
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouse.getButton()==1)
            {
                
            }
        }
    }

    private void checkKeys(){
    
        if (Greenfoot.isKeyDown("up") && jumping == false)
        {
             jump();
        }
        
        if (Greenfoot.isKeyDown("down"))
        {
            setLocation(getX(), getY()+3);
        }
      
        if (Greenfoot.isKeyDown("left"))
        {
            if (!checkLeft() || vSpeed < 0)
                moveLeft();
        }
        
        if (Greenfoot.isKeyDown("right"))
        {
            if (!checkRight() || vSpeed < 0)
                moveRight();
        }
        
        if(getY() >= floor)
        {
            death();
        }
    }
    
    /**
     * Improved onGround method avoids falling through floors due to high fall speed. Looks
     * up to vSpeed pixes below P for a platform. 
     */
    public boolean onGround()
    {
        Surface under = null;
        int counter = 1;
        int max = vSpeed;
        //int variance;
        while (counter <= max && under == null)
        {
            under = (Surface)getOneObjectAtOffset ( 0, getImage().getHeight() / 2 + counter, Surface.class);
            counter++;
        }
        // If there is a platform, correct the player's height so that he always lands right on the platform
        // This avoids a wierd floating effect that was present otherwise
        if (under != null)
        {
            int newY;
            if (under instanceof UpDown)
            {
                newY = under.getNewHeight();
                newY -= this.getImage().getHeight() / 2 + 2;
                System.out.println("FALL DETECTED");
            }
            else
            {
                newY = under.getY() - (under.getImage().getHeight()/2) - ((getImage().getHeight() / 2))-1;
                
                //System.out.println(under.getY() + " " + under.getImage().getHeight()/2 + " " + newY + " "  + getY());
            }
            setLocation(getX(), newY);
        }
        return under != null;
    }
    
    
    /** Actor getSurface() method
     * Returns a surface that the player is on (or null). This is used by MyWorld to do automatic
     * movement if the player is standing on a moving platform
     */
    public Actor getSurface()
    {
        Actor under = getOneObjectAtOffset ( 0, getImage().getHeight() / 2, Surface.class);
        int counter = -1;
        int max = vSpeed + 2;
        while (counter <= max && under == null)
        {
            under = getOneObjectAtOffset ( 0, getImage().getHeight() / 2 + counter, Surface.class);
            counter++;
        }
        return under;
    }
    
    
    /**
     * Provided jump() method. Only addition is jumping = true which is part of a system which prevents
     * jumping while already mid-jump
     */
    public void jump()
    {
        jumping = true;
        vSpeed = -jumpStrength;
        fall();
    }

    /**
     * Provided checkFall() method. Updated to set jumping to false if the player is on the ground,
     * thus allowing the player to jump again.
     */
    public void checkFall()
    {
        if(onGround()) {
            vSpeed = 0;
            jumping = false;
        }
        else {
            fall();
        }
    }
    
     /** fall()
     * Exactly as provided by sample.
     */
    public void fall()
    {
        setLocation ( getX(), getY() + vSpeed);
        vSpeed = vSpeed + acceleration;
    }
    
 
    /* Check whether we should go to the next Scene, and if yes, start the next Scene.
     */
    private void checkNextScene()
    {
       String key=Greenfoot.getKey();
       
       if (key!=null && key.equals("up") ){
           if(isTouching(Door.class))
              ((IslandWorld)getWorld()).switchWorld(); //Object Polymorphism 
       }
    }
  
    /**
     * verticalMove moves the player when standing on an up-down elevator
     */
    public void verticalMove(int dist)
    {
        setLocation(getX(), getY() + dist);
    }

 
    
    public void moveRight()
    {  
       counter++;
       if (counter % 6 == 0) {
        walking = true;
        facingRight = true;
        frame ++;
        if(frame ==1) {
            setImage(rwalk0);
        }
        else if (frame == 2){
            setImage(rwalk1);
        }
        else if (frame == 3){
            setImage(rwalk2);
        }
        else if (frame == 4){
            setImage(rwalk3);
            frame = 1;
            return;
        }
        setLocation ( getX() + speed, getY() );
    }

   }

    public void moveLeft()
    {
    counter++;
    if (counter % 6 == 0) {
        walking = true;
        facingRight = false;
        frame++;
        if(frame ==1) {
            setImage(lwalk0);
        }
        else if (frame == 2){
            setImage(lwalk1);
        }
        else if (frame == 3){
            setImage(lwalk2);
        }
        else if (frame == 4){
            setImage(lwalk3);
            frame = 1;
            return;
        }
        setLocation ( getX() - speed, getY() );}
   }
    
     /**
     * Left and then Right facing collision detection.
     * Uses a loop to check for an offset object to either side up to
     * 1/2 the height of the the playerin
     */
    public boolean checkLeft()
    {
        Actor bumper = null;
        int counter = 0;
        int max = (int)(getImage().getHeight() / 2);
        while (counter < max && bumper == null)
        {
            bumper = getOneObjectAtOffset (-1*( getImage().getWidth() / 2), max - counter, Surface.class);
            counter++;
        }
        return bumper != null;
    }

    public boolean checkRight()
    {
        Actor bumper = null;
        int counter = 0;
        int max = (int)(getImage().getHeight() / 2);
        while (counter < max && bumper == null)
        {
            bumper = getOneObjectAtOffset ( getImage().getWidth() / 2, max - counter , Surface.class);
            counter++;
        }
        return bumper != null;
    }
    
      /**
     * Enemy getEnemy()
     * This method checks collision with Enemy objects.
     */
    public Enemy getEnemy()
    {
        // This loop avoids checking for close enemies on every act() to avoid performance issues
        // Instead, it does it once every reduceCollisionDetection times (3 at time of writing)
        if (currCollDetection == reduceCollisionDetection)
        {
            Actor temp = getOneIntersectingObject(Enemy.class);
            Enemy enemy = (Enemy)temp;
            currCollDetection = 0;
            return enemy;
        }
        else
        {
            currCollDetection++;
        }
        return null;
    }
    
    public boolean eat()
    {
        Actor food = getOneObjectAtOffset(0, 0, Food.class);
        boolean var = false;
        if (food != null)
        {
            World world = getWorld();
            eating.setVolume(80);
            eating.play();
            world.removeObject(food);
            if (health <= 90) {
                health += 10;
                var = true;
            }
            
        }
        return var;
    }
    
    /**
     * tryMove(int)
     * This method is called by MyWorld for the purposes of moving the player when the
     * player is standing on a moving platform.Gets a positive distance for rightwards
     * movement or a negative distance for left
     * movement. Checks for collisions with the side of surfaces, and makes sure the player
     * is allowed to move. If so, this method performs the automatic (not player controlled) 
     * moving.
     */
    public void tryMove(int dist)
    {
        if (!(checkRight()) && dist > 0 )
            setLocation(getX() + dist, getY());
        if (!(checkLeft()) && dist < 0 )
            setLocation(getX() + dist, getY());
    }
   
    public void death()
    {
        setRotation(90);
        dead = true;       
        Greenfoot.setWorld(new EndScreenLose());
    }
 
    public boolean checkDeath()
    {
        return dead;
    }
   
    public void setHealth(int value)
    {
        health += value;
    }
    
    //For the HealthBarFace
    public int getHealth()
    {
        return health;
    }
}
