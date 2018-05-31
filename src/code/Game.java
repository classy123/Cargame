package code;
import java.applet.Applet;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.*;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;




@SuppressWarnings("serial")
public class Game extends Applet implements KeyListener, MouseListener,MouseMotionListener, Runnable {

	//screen size
	Image off_screen;
	Graphics off_g ;



	LinkedList<Coins> randomCoins = new LinkedList<Coins>();
	LinkedList<Trucks> randomTrucks = new LinkedList<Trucks>();

	int score = 0;
	Font font = new Font("Arial", Font.BOLD, 25);
	Font font2 = new Font("Arial", Font.BOLD, 200);
	Font font3 = new Font("TimesRoman",Font.BOLD,100);


	Image start_menu = Toolkit.getDefaultToolkit().getImage("C:/Users/Tanvir Bashar/Desktop/GroupProject/src/startMenu/startMenu2.png");

	ImageLayer road = new ImageLayer("C:/Users/Tanvir Bashar/Desktop/GroupProject/src/images/road.jpg",380,0,550,500,1);
	ImageLayer leftGrass = new ImageLayer("C:/Users/Tanvir Bashar/Desktop/GroupProject/src/images/grass.jpg",0,0,380,500,1);
	ImageLayer rightGrass = new ImageLayer("C:/Users/Tanvir Bashar/Desktop/GroupProject/src/images/grass.jpg",930,0,380,800,1);

	ImageLayer leftSideWalk = new ImageLayer("C:/Users/Tanvir Bashar/Desktop/GroupProject/src/images/sidewalk2.png",190,0,340,800,1);
	ImageLayer rightSideWalk = new ImageLayer("C:/Users/Tanvir Bashar/Desktop/GroupProject/src/images/sidewalk.png",780,0,340,800,1);

	ImageLayer house = new ImageLayer("C:/Users/Tanvir Bashar/Desktop/GroupProject/src/images/house.png",-72,300,400,400,1);
	//ImageLayer house2 = new ImageLayer("/Users/Charlie/Desktop/GroupProject/src/images/house2.png",-5,600,300,300,1);

	//ImageLayer coins = new ImageLayer("/Users/Charlie/Desktop/GroupProject/src/images/coin.gif",390,0,16,16,1);


	Sprite [] blueCar = new Sprite[1];
	Sprite2[] coin = new Sprite2[1];
	Sprite3[] truck = new Sprite3[1];


	Animation anim = new Animation("g_right_",2,4);


	//at class scope
	Rect startBox = new Rect(988,309,500,151);
	Rect dragBox = new Rect(50,50,50,50);


	Rect leftBox = new Rect(0,50,380,800);
	Rect rightBox = new Rect(930,0,380,800);

	//Rect coins = new Rect(550,200,100,100);



	Thread t;

	boolean ltPressed = false;
	boolean rtPressed = false;
	boolean upPressed = false;
	boolean dwnPressed = false;
	boolean space_pressed = false;
	boolean start = false;


	boolean overlap = false;

	boolean paused = false;


	int mx;
	int my;


	//SimpleSoundPlayer test = new SimpleSoundPlayer("/Users/Charlie/Desktop/GroupProject/src/sounds/accelerating.wav");
	//InputStream stream = new ByteArrayInputStream(test.getSamples());


	int randomWithRange(int min, int max)
	{
		int range = (max - min) + 1;
		return (int)(Math.random() * range) + min;
	}

	public void init(){

		setSize(1366,768);

		off_screen = this.createImage(1366, 768);
		off_g = off_screen.getGraphics();

		//screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

		//screen.setFullScreenWindow(a);

		for(int i = 0;i < blueCar.length;i++){
			//soldier[i] = new Sprite(10,400,"g_",action,12,2);
			blueCar[i] = new BlueCar(430,500);
		}

		for(int i = 0;i < coin.length;i++){
			//soldier[i] = new Sprite(10,400,"g_",action,12,2);
			coin[i] = new Coins(430,200);
		}


		requestFocus(); //makes the program request focus from the operating system
		addKeyListener(this);
		//"this" means this program
		//this program is a keyListener itself
		//this == I

		//int rd = (int)Math.random()+5;

		//COINS
		for(int i = 1; i < 100;i++) {
			randomCoins.add(new Coins(430,-500*i*randomWithRange(1,5)));
		}

		for(int i = 1; i < 100;i++) {
			randomCoins.add(new Coins(620,-200*i*randomWithRange(1,5)));
		}
		for(int i = 1; i < 100;i++) {
			randomCoins.add(new Coins(800,-300*i*randomWithRange(1,5)));
		}

		//TRUCKS
		for(int i = 1; i < 100;i++) {
			randomTrucks.add(new Trucks(430,-1000*i*randomWithRange(1,3)));
		}

		for(int i = 2; i < 100;i++) {
			randomTrucks.add(new Trucks(620,-400*i*randomWithRange(1,3)));
		}
		for(int i = 3; i < 100;i++) {
			randomTrucks.add(new Trucks(800,-3000*i*randomWithRange(1,3)));
		}






		addMouseListener(this);
		addMouseMotionListener(this);

		t = new Thread(this);// "This" parameter. This line creates a thread
		t.start();


	}


	@SuppressWarnings("static-access")
	public void run() {

		//game loop
		//make the loop sleep
		//make it try to sleep


		while(!start){


			repaint();

			try{

				t.sleep(15);

			}catch(Exception x){}
		}


		while(start){//dont stop
			checkCollisions();



			for(Coins c: randomCoins){
				c.moveDownBy(5);
			}

			for(Trucks t: randomTrucks){
				t.moveDownBy(8);
			}


			if(paused){


				return;



			}

			for(int i = 0;i<coin.length;i++) {
				//coin[i].moveDownBy(4);
			}





		for(int i = 0; i < blueCar.length;i++) {

			//System.out.println(blueCar[i].y);
				rightGrass.moveDownBy(3);
				leftGrass.moveDownBy(3);
				road.moveDownBy(4);
				house.moveDownBy(3);
				//house2.moveDownBy(3);

				leftSideWalk.moveDownBy(4);
				rightSideWalk.moveDownBy(4);



			if(upPressed){
				blueCar[i].moveUpBy(10);



				if(blueCar[i].y > 99)
					road.moveDownBy(10);
					leftGrass.moveDownBy(5);
					rightGrass.moveDownBy(5);
					leftSideWalk.moveDownBy(10);
					rightSideWalk.moveDownBy(10);
				    house.moveDownBy(5);
				//house2.moveDownBy(3);


			}


			if(dwnPressed){
				blueCar[i].moveDownBy(4);

			}


			if(ltPressed){
				blueCar[i].moveLeftBy(5);

			}

			if(rtPressed){
				blueCar[i].moveRightBy(5);
			}


			repaint();

			try{
				t.sleep(15);
			}catch(Exception x){}


		}




	}


}


	public void update(Graphics g){

		off_g.clearRect(0, 0, 1366, 768);
		paint(off_g);
		g.drawImage(off_screen, 0, 0, null);

	}

	public void checkCollisions(){

		//Rectangle  b1 =  randomCoins.getFirst().getBounds();

		Rectangle car = blueCar[0].getBounds();
		int w = car.width = 83;
		int h = car.height = 130;


		for(int i=0;i < randomCoins.size();i++) {
			if (car.intersects(randomCoins.get(i).getBounds())) {
				randomCoins.remove(i);
				score+=20;

			}

		}

		for(int i=0;i < randomTrucks.size();i++) {
			if (car.intersects(randomTrucks.get(i).getBounds())) {

				paused= true;



/*
				double dx=(car.x+w/2)-(randomTrucks.get(i).x+randomTrucks.get(i).w/2);
				double dy=(car.y+h/2)-(randomTrucks.get(i).y+randomTrucks.get(i).h/2);
				double width=(w+randomTrucks.get(i).w)/2;
				double height=(h+randomTrucks.get(i).h)/2;
				double crossWidth=width*dy;
				double crossHeight=height*dx;

				// With respect to the car, detection collision side
				if(Math.abs(dx)<=width && Math.abs(dy)<=height){
					System.out.println("TRUCK HIT");
					if(crossWidth>crossHeight){
						if (crossWidth > (-crossHeight)){
							System.out.println("bottom");
							car.y+= randomTrucks.get(i).y+randomTrucks.get(i).h - car.y;
						}
						else{
							System.out.println("left");
							car.x-= car.x+ w - randomTrucks.get(i).x;
						}
					}else{
						if(crossWidth>-(crossHeight)){
							System.out.println("right");
							car.x+= randomTrucks.get(i).x+randomTrucks.get(i).w - car.x;
						}
						else{
							System.out.println("top");
							car.y-= car.y + h - randomTrucks.get(i).y;
						}
					}
				}
*/


				}


			}

		}






	public void paint(Graphics g){
		//g.drawImage(jungle, 0, 100, 1366, 500, this);
		if(!start) {
			g.drawImage(start_menu, 0, 0, 1280,768,null);
			g.setFont(font3);
			g.setColor(Color.ORANGE);
			g.drawString("Coin Rush", 380, 180);

			//startBox.draw(g);

		}

		if(start) {

			//r.draw(g);

			//tank.draw(g);
			//soldier.draw(g);

			road.draw(g);
			leftGrass.draw(g);
			rightGrass.draw(g);
			leftSideWalk.draw(g);
			rightSideWalk.draw(g);

			//house2.draw(g);
			house.draw(g);


			//tank2.draw(g);
			dragBox.draw(g);

			g.setFont(font);
			g.drawString("Score: "+score,600,650 );





			//leftBox.draw(g);
			//rightBox.draw(g);
			//coins.draw(g);

            for(int i=0;i<randomCoins.size();i++) {
                //if (!randomCoins.isEmpty()) {
                    randomCoins.get(i).draw(g);
                //}

				/*if(blueCar[i].overlapCoins()){

					System.out.println("overlaps coin");
					coin[i] = new Coins(-100,0);
				}
*/




            }

			for(int i=0;i<randomTrucks.size();i++) {

				randomTrucks.get(i).draw(g);

			}





			for(int i = 0; i<blueCar.length; i++) {
				blueCar[i].draw(g);

				double d = distanceTo(blueCar[i].x, blueCar[i].y,leftBox);


				//g.drawString("Distance= "+d, 20, 400);
				//g.drawString("Coordinates"+blueCar[i].x+","+blueCar[i].y+","+blueCar[i].w+","+blueCar[i].h, 20,500);



				//BlueCar collision with surroundings
				blueCar[i].overlaps(leftBox);
				blueCar[i].overlaps(rightBox);






			}


			if(paused){
				System.out.println("GAME OVER");
				g.setColor(Color.RED);
				g.setFont(font2);
				g.drawString("GAME OVER",30,400);
			}




		}

	}


	private double distanceTo(double x1, double y1, Rect r) {
		double dx = r.x-x1;
		double dy = r.y-y1;
		return Math.sqrt(dx * dx + dy * dy);

	}

	@Override
	public void mouseMoved(MouseEvent e){



	}

	@Override
	public void mouseDragged(MouseEvent e){

		//these are not the same as the ones in mousePressed because these are not at class scope
		int mx = e.getX();
		int my = e.getY();



						//this.mx  and this.my is outside of area
		int dx = mx - this.mx;
		int dy = my - this.my;

		dragBox.resizeBy(dx, dy);



		this.mx = mx;
		this.my = my;



	}

	@Override
	public void mousePressed(MouseEvent e){

		mx = e.getX();
		 my = e.getY();

		dragBox = new Rect(mx,my,0,0);




		 if(startBox.contains(mx, my)) {
			 System.out.println("clicked in square");
			 this.start = true;
			 dragBox = new Rect(mx,my,0,0);
		 }





	}

	@Override
	public void mouseReleased(MouseEvent e){

		//startBox = new Rect(-100,0,0,0);
		dragBox = new Rect(-100,0,0,0);



	}

	public void mouseClicked(MouseEvent e){


	}

	public void mouseEntered(MouseEvent e){

	}

	public void mouseExited(MouseEvent e){

	}







	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();

		if(code == e.VK_A){
			ltPressed = true;

		}
		if(code == e.VK_D){
			rtPressed = true;
		}
		if(code == e.VK_W){
			upPressed = true;
		}
		if(code == e.VK_S){
			dwnPressed = true;
		}
		if(code == e.VK_SPACE) {
			space_pressed = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();

		if(code == e.VK_A){
			ltPressed = false;
		}
		if(code == e.VK_D){
			rtPressed = false;
		}
		if(code == e.VK_W){
			upPressed = false;
		}
		if(code == e.VK_S){
			dwnPressed = false;
		}
		if(code == e.VK_SPACE) {
			space_pressed = false;
		}


	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	public void playSound(int sound) {
	       // open the sound file as a Java input stream
	       String soundFile = "";
	       if (sound == 1) {
	           soundFile = "/Users/Charlie/Desktop/GroupProject/src/sounds/accelerating.wav";
	       } else if (sound == 2) {
	           soundFile = "C:\\Users\\timan\\Documents\\NetBeansProjects\\Game\\src\\sounds\\ugh.wav";
	       } else if (sound == 3) {
	           soundFile = "C:\\Users\\timan\\Documents\\NetBeansProjects\\Game\\src\\sounds\\ugh2.wav";
	       } else if (sound == 4) {
	           soundFile = "C:\\Users\\timan\\Documents\\NetBeansProjects\\Game\\src\\sounds\\powerup2.wav";
	       } else if (sound == 5) {
	           soundFile = "C:\\Users\\timan\\Documents\\NetBeansProjects\\Game\\src\\sounds\\cheer.wav";
	       }

	       InputStream in;
	       try {
	           in = new FileInputStream(soundFile);// create an audiostream from the inputstream
	           //AudioPlayer audioStream = new AudioPlayer(in);// play the audio clip with the audioplayer class
	           //AudioPlayer.player.start(audioStream);
	       } catch (FileNotFoundException ex) {
	           Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
	       } catch (IOException ex) {
	           Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
	       }

	   }
	*/








}
