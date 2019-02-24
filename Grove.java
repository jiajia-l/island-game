import greenfoot.*;

/**
 * Write a description of class Grove here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Grove extends IslandWorld
{

    Player player = super.play;
    private GreenfootSound groveSounds = new GreenfootSound("grove.wav");
        
    /**
     * Constructor for objects of class Grove.
     * 
     */
    public Grove()
    { 
        groveSounds.setVolume(70);
        groveSounds.playLoop();
        
        addObject(super.play, 342, 343);

        addObject(new Mosquito(), 12, 12);
        addObject(new Mosquito(), 30, 30);        
        addObject(new Mosquito(), 30, 30);
        addObject(new BoatSpirit(600, 650, 2), 650, 90);
        addObject(new Door(), 233, 40);
        
        
    
        addObject(new Apple(), 500, 80); 
        addObject(new Platform(), 500, 124);
        addObject(new Platform(), 540, 124);
        
        addObject(new UpDown(100, 300), 233, 200);

        addObject(new Brick(350, 600), 400, 200);
        addObject(new Platform(), 80, 200);
        addObject(new Platform(), 530, 242);
        addObject(new Platform(), 20, 260);
        addObject(new Platform(), 650, 300);
        addObject(new Platform(), 60, 310);
        addObject(new Brick(400, 600), 450, 350);
        addObject(new Platform(), 200, 365);
                

        addObject(new Platform(), 350, 400);
        
        addObject(new Apple(), 570, 400);

        addObject(new Brick(300, 600), 550, 450);

    }
    
    public void switchWorld()
    {
        World forest = new Forest();
        groveSounds.stop();
        Door door = (Door)forest.getObjects(Door.class).get(0);
        Greenfoot.setWorld(forest);
    }
}
