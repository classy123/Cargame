package code;

import java.awt.*;

public class Trucks extends Sprite3{

    static String [] action = {"down"};

    public Trucks(int x,int y) {
        super(x,y,"g_",action,6,3);
    }

    public void moveUpBy(int dy){
        y -= dy;
       /* if(y < 99)
            y = 99;
        */

        moving = true;
        pose = DOWN;
    }

    public void moveDownBy(int dy){

        y += dy;
        /*if(y > 500)
            y = 500;

          */

        moving = true;
        pose = DOWN;

    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,45,85);
    }


}
