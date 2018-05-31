package code;

import java.awt.*;

public class Animation {
	
	Image [] image;
	
	int current = 0;
	
	int delay;
	
	int duration;
									//how many images
	public Animation(String file, int count,int duration){
		
		image = new Image[count];
		
		for(int i = 0; i < count; i++){
			image [i] = Toolkit.getDefaultToolkit().getImage("C:/Users/Tanvir Bashar/Desktop/GroupProject/src/blueCar/"+file+i+".png");
			
			System.out.println(file+i+".png");
			
		}
		
		
		System.out.println("/---------/");
		
		this.duration = duration;
		delay = duration;
		
	}
	
	public Image stillImage(){
		return image[0];
	}
	
	
	public Image nextImage(){
		
		if(delay == 0){
			current++;
		
			if(current == image.length){
				current = 1;
			}
			
			delay = duration;
		}
		else{
		
			delay--;
		}
		
		return image[current];
	}

}
