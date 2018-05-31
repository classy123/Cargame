package code;

import java.awt.*;

public class Sprite3 extends Rect{


    Animation3 [] animation;

    boolean selected = false;

    boolean moving = false;

    static final int  DOWN = 0;



    int pose = DOWN;

    public Sprite3(int x, int y,String file,String [] action,int count,int duration){
        super(x,y,100,130);


        animation = new Animation3[action.length];


        for(int i = 0; i < action.length; i++){
            animation[i] = new Animation3(file + action[i]+"_",count,duration);
        }
    }





    public void moveUpBy(int dy){

        y -= dy;
        moving = true;
        pose = DOWN;
    }

    public void moveDownBy(int dy){

        y += dy;
        moving = true;
        pose = DOWN;
    }
    public void moveLeftBy(int dx){
        x -= dx;
        moving = true;
        pose = DOWN;
    }
    public void moveRightBy(int dx){
        x += dx;
        moving = true;
        pose = DOWN;
    }

	/*public void rotateLeft(String[]action) {
		for(int i = 0; i < action.length; i++){
			animation[i] = new Animation("g_" + action[i]+"_",3,2);
		}
	}
	*/

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,100,130);
    }


    public void draw(Graphics g){

        if(moving)
            g.drawImage(animation[pose].nextImage(),(int)x,(int)y,100,130, null);
            //g.drawImage(animation[pose].nextImage(),x,y, null);


        else
            g.drawImage(animation[pose].stillImage(),(int)x,(int)y,100,130,null);
        //g.drawImage(animation[pose].stillImage(),x,y,null);
        moving = false;

        //g.setColor(Color.BLACK);
        //if(selected)
        //super.draw(g);
        //g.drawLine((int)x+8, (int)y,(int)x+(int)w-8, (int)y);
        //g.setColor(Color.BLACK);



    }
}
