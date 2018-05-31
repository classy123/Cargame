package code;

import java.awt.*;

public class BlueCar extends Sprite {
	
static String [] action = {"forward","left","right","reverse"};


	
	public BlueCar(int x,int y) {
		super(x,y,"g_",action,2,5);
	}
	
	public void moveUpBy(int dy){   
        y -= dy;
        if(y < 99)
            y = 99;
        
        
        moving = true;
        pose = FORWARD;
    }
	
	public void moveDownBy(int dy){
        
        y += dy;
        if(y > 500)
            y = 500;
        moving = true;
        pose = DOWN;
        
    }


    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,90,130);
    }
	
	

}
