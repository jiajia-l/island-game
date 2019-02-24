import greenfoot.*;

public class HealthBarFace extends Actor
{
    Player player;
    
    public HealthBarFace(Player play)
    {
        this.player = play;
    }
    
    public void act() 
    {
        
        if (player.getHealth() >= 70) happy();
        else if (player.getHealth() >= 40) meh();
        else if (player.getHealth() >= 1) sad();
    }    
    
    public void happy()
    {
    setImage("happy.png");
    }
    
    public void meh(){
    setImage("meh.png");
    }
    
    public void sad(){
    setImage("sad.png");
    }
}
