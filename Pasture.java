import greenfoot.*;

/**
 * Write a description of class Pasture here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pasture extends IslandWorld
{


    public Player player; 
    private GreenfootSound pastureSounds = new GreenfootSound("pasture.wav");
   
    
    /**
     * Constructor for objects of class Pasture.
     * 
     */
    public Pasture()
    {
        pastureSounds.playLoop();
       
        addObject(super.play, 503, 40);
        addObject(new Door(), 530, 235);
        
        addObject(new Apple(), 103, 40);
        addObject(new Platform(), 100, 100);
        addObject(new Platform(), 430, 100);
        addObject(new Platform(), 520, 100);   
        
        addObject(new Platform(), 550, 150);
 
        addObject(new Platform(), 240, 160);
        addObject(new Platform(), 280, 180);

        addObject(new Platform(), 580, 200);

        
        addObject(new Apple(), 343, 23);

        addObject(new BoatSpirit(12, 80, 1), 80, 290);
     
        

        addObject(new Brick(50, 150), 170, 240);
     
        addObject(new BoatSpirit(290, 450, 1), 310, 240);
        addObject(new Platform(), 320, 290);
        addObject(new Platform(), 410, 290);
        addObject(new Apple(), 530, 400);
        addObject(new Platform(), 40, 340);
        addObject(new Platform(), 100, 320);
        addObject(new Platform(), 100, 390);
        addObject(new Brick(302,523), 402, 390);
        addObject(new Platform(), 320, 450);
        addObject(new Platform(), 200, 450);   

        addObject(new Brick(400, 680), 500, 550);
    }
    
    public void switchWorld()
    {
        World grove = new Grove();
        pastureSounds.stop();
        Door door = (Door)grove.getObjects(Door.class).get(0);
        Greenfoot.setWorld(grove);
    }
}
