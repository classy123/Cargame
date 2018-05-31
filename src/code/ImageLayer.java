package code;

import java.awt.*;
import java.util.LinkedList;


public class ImageLayer {
	
	LinkedList<Integer> list = new LinkedList<Integer>() ;

	
	Image image;
	
	int x;
	int y;
	int z;
	int w;
	int h;
	
	public void LinkedList() {
		
		
		list.add(-500);
		list.add(-250);
		list.add(0);
		list.add(250);
		list.add(500);
		
	
		
		
		
	}
	
	
	
	public ImageLayer(String file, int x,int y,int w,int h, int z) {
		image = Toolkit.getDefaultToolkit().getImage(file);
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		this.h = h;
		
		LinkedList();
	}
	
	public void moveLeftBy(int dx) {
		x-=dx;
	}
	
	public void moveRightBy(int dx) {
		x+=dx;
	}
	
	public void moveDownBy(int dy) {
	
		for(int i=0; i<list.size(); ++i)
			list.set(i,list.get(i)+dy);
		
		if(list.peekLast() > 768) {
			list.removeLast();
			int first = list.peekFirst();
			
			list.addFirst(first-250);
		}
		
			
	}
	public void moveUpBy(int dy) {
		y-=dy;
	}
	
	
	public void draw(Graphics g) {
		
		for(int i = 0;i<list.size();i++) {
			g.drawImage(image,x,list.get(i)/z,w,h,null);
			
		}
	}

}
