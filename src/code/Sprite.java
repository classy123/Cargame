package code;

import java.awt.*;

public class Sprite extends Rect{


	Animation [] animation;
	
	boolean selected = false;
	
	boolean moving = false;
	
	static final int  FORWARD = 0;
	static final int  LEFT 	  = 1;
	static final int  RIGHT   = 2;
	static final int  DOWN 	  = 3;


	
	
	int pose = FORWARD;
	
	public Sprite(int x, int y,String file,String [] action,int count,int duration){
		super(x,y,73,130);
		
		
		animation = new Animation[action.length];
		
		
		for(int i = 0; i < action.length; i++){
			animation[i] = new Animation(file + action[i]+"_",count,duration);
		}
	}
	
	
	
	
	
	public void moveUpBy(int dy){
		
		y -= dy;
		moving = true;
		pose = FORWARD;
	}
	
	public void moveDownBy(int dy){
		
		y += dy;
		moving = true;
		pose = DOWN;
	}
	public void moveLeftBy(int dx){
		x -= dx;
		moving = true;
		pose = LEFT;
	}
	public void moveRightBy(int dx){
		x += dx;
		moving = true;
		pose = RIGHT;
	}
	
	/*public void rotateLeft(String[]action) {
		for(int i = 0; i < action.length; i++){
			animation[i] = new Animation("g_" + action[i]+"_",3,2);
		}
	}
	*/

	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,83,130);
	}
	
	
	public void draw(Graphics g){
		
		if(moving)
		g.drawImage(animation[pose].nextImage(),(int)x,(int)y,83,130, null);
		//g.drawImage(animation[pose].nextImage(),x,y, null);
		
		
		else
			g.drawImage(animation[FORWARD].stillImage(),(int)x,(int)y,83,130,null);
		//g.drawImage(animation[pose].stillImage(),x,y,null);
			moving = false;
			
			//g.setColor(Color.BLACK);
			//if(selected)
				//super.draw(g);
				//g.drawLine((int)x+8, (int)y,(int)x+(int)w-8, (int)y);
				//g.setColor(Color.BLACK);
				
		
		
	}
}
