package code;

import java.awt.Graphics;

public class Sprite2 extends Rect{


	Animation2 [] animation2;
	
	boolean selected = false;
	
	boolean moving = false;
	
	static final int DOWN = 0;

	
	int pose = DOWN;
	
	public Sprite2(int x, int y,String file,String [] action,int count,int duration){
		super(x,y,65,65);
		
		
		animation2 = new Animation2[action.length];
		
		
		for(int i = 0; i < action.length; i++){
			animation2[i] = new Animation2(file + action[i]+"_",count,duration);
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
	
	
	
	
	public void draw(Graphics g){
		
		if(moving)
		g.drawImage(animation2[pose].nextImage(),(int)x,(int)y,65,65, null);
		//g.drawImage(animation[pose].nextImage(),x,y, null);
		
		
		else
			g.drawImage(animation2[pose].nextImage(),(int)x,(int)y,65,65,null);
		//g.drawImage(animation[pose].stillImage(),x,y,null);
			moving = false;
			
			//g.setColor(Color.BLACK);
			//if(selected)
				//super.draw(g);
				//g.drawLine((int)x+8, (int)y,(int)x+(int)w-8, (int)y);
				//g.setColor(Color.BLACK);
				
		
		
	}

}
