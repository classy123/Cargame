package code;

import java.awt.Graphics;

public class Rect {
	

	double x;
	double y;
	
	int w;
	int h;
	
	double vx = 0;
	double vy = 0;
	
	static final double g = .1;
	
	double ax = 0;
	double ay = g; //remove gravity by setting this to 0
	
	boolean held = false;
	
	public Rect(double x,double y, int w,int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		
		
	}
	
	public void setLocation(double x,double y) {
		this.x = x;
		this.y = y;
	}
	
	
	
	public void grab(){
		held = true;
	}
	
	public void drop(){
		held = false;
	}
	
	public boolean overlaps(Rect r){
		
		double dx=(x+w/2)-(r.x+r.w/2);
	    double dy=(y+h/2)-(r.y+r.h/2);
	    double width=(w+r.w)/2;
	    double height=(h+r.h)/2;
	    double crossWidth=width*dy;
	    double crossHeight=height*dx;
	    
	    // With respect to the car, detection collision side
	    if(Math.abs(dx)<=width && Math.abs(dy)<=height){
	        if(crossWidth>crossHeight){
	            if (crossWidth > (-crossHeight)){
	            	System.out.println("bottom");
	            	y+= r.y+r.h - y;
	            }
	            else{
	            	System.out.println("left");
	            	x-= x+w - r.x;
	            }
	        }else{
	            if(crossWidth>-(crossHeight)){
	            	System.out.println("right");
	                x+= r.x+r.w - x;
	            }
	            else{ 
	            	System.out.println("top");
	            	y-= y+h - r.y;
	            }
	        }
	    }
	    //return(collision);
		
//		boolean leftDetect = (x + w > r.x      );
//		boolean rightDetect = (x     < r.x + r.w);
//		boolean topDetect = (y + h > r.y);
//		boolean bottomDetect = (y     < r.y + r.h);
//		
//		if(leftDetect && topDetect && bottomDetect){
//			System.out.println("Left Collision");
//		}
//		else if(rightDetect && topDetect && bottomDetect){
//			System.out.println("Right Collision");
//		}
//		else if(topDetect && rightDetect && leftDetect){
//			System.out.println("Top Collision");
//		}
//		else if(bottomDetect && rightDetect && leftDetect)
//		{
//			System.out.println("Bottom Collision");
//		}
	
		
		return 	(x     < r.x + r.w) &&
				(x + w > r.x      ) &&
				(y     < r.y + r.h) &&
				(y + h > r.y);
	}
	
	public boolean overlapCoins(Rect r) {
		return 	(x     < r.x + r.w) &&
				(x + w > r.x      ) &&
				(y     < r.y + r.h) &&
				(y + h > r.y);
	}
	
	
	public boolean contains(int mx,int my){
		return (mx > x) && (mx < x+w) && (my > y) && (my < y+h);
	}
	
	
	
	public void draw(Graphics g){
		g.drawRect((int)x,(int)y,w,h);
	}
	
	public void setAcceleration(double ax,double ay) {
		this.ax = ax;
		this.ay = ay;
	}
	
	public void setVelocity(double vx,double vy) {
		this.vx = vx;
		this.vy = vy;
	}
	
	
	
	
	public void move() {
		vx += ax;
		vy += ay;
		
		x += vx;
		y += vy;
	}
	
	public void moveBy(double dx,double dy){
		x += dx;
		y += dy;
	}
	
	public void resizeBy(int dw,int dh){
		
		w += dw;
		h += dh;
		
	}

}
