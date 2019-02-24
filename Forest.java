import greenfoot.*;

/**
 * Write a description of class Forest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Forest extends IslandWorld
{

    private GreenfootSound forestSounds = new GreenfootSound("forest.wav");
    
    /**
     * Constructor for objects of class Forest.
     * 
     */
    public Forest()
    {
        forestSounds.setVolume(70);
        forestSounds.playLoop();
       
        addObject(super.play, 450, 433);
        addObject(new Door(), 132,80);
   
        addObject(new Platform(), 530, 242);

       
        addObject(new UpDown(300, 450), 400, 400);

       
        addObject(new Brick(304, 420), 80, 200);

        addObject(new Platform(), 300, 180);

        addObject(new Platform(), 100, 200);
      
        addObject(new Brick(40, 250), 260, 385); 
        addObject(new Platform(), 290, 324);
        addObject(new Platform(), 350, 400);
        addObject(new Apple(), 200, 350);
         addObject(new UpDown(23, 131), 200, 385);
        addObject(new Platform(), 450, 500);
        
        
        addObject(new Mosquito(), 300, 400);
        addObject(new Mosquito(), 600, 10);
        addObject(new Mosquito(), 12, 12);
        addObject(new Mosquito(), 30, 30);
        addObject(new Mosquito(), 23, 133);
        addObject(new Mosquito(), 130, 10);
        addObject(new Mosquito(), 23, 133);
        addObject(new Mosquito(), 130, 10);
       
    }
   
    public void switchWorld(){
        forestSounds.stop();
        music = false;
        endGame();
    }
    
    public void endGame(){
         World win = new EndScreenWin();
         Greenfoot.setWorld(win); 
    }
}
