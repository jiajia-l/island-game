import greenfoot.*;

/**
 * Write a description of class Beach here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Beach extends IslandWorld
{



        protected GreenfootSound beachSounds = new GreenfootSound("beach.wav");
   
    /**
     * Constructor for objects of class Beach.
     * 
     */
    public Beach()
    {
       
        beachSounds.setVolume(100);
       
        beachSounds.playLoop();
  
        addObject(super.play, 342, 343);

        addObject(new Door(), 590, 180);       
        addObject(new BeachGround(), 450,460);
        
        //Platform setup
        
        addObject(new Platform(), 200, 100);
        addObject(new Platform(), 370,100);

        addObject(new Platform(), 60, 110);
        addObject(new Platform(), 100, 170);
                
        addObject(new Platform(), 530, 242);
        addObject(new Platform(), 320, 324);
        addObject(new Platform(), 380, 324);
        
        addObject(new Platform(), 200, 285);
        addObject(new Platform(), 170, 240);


        addObject(new Apple(), 70, 130);
       
        addObject(new Platform(), 300, 200);


        addObject(new Platform(), 350, 400);
        
        addObject(new Apple(), 70, 410);
        addObject(new Platform(), 80, 450);
        //setup enemies
        addObject(new Mosquito(), 12, 23);
        addObject(new Mosquito(), 500, 321);
        

      
    }
    
    
    public void switchWorld(){
        World pasture = new Pasture();
        beachSounds.stop();
        Door door = (Door)pasture.getObjects(Door.class).get(0);
        Greenfoot.setWorld(pasture);
    }
  
    
}