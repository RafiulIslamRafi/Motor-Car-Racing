package my_game_manager;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import my_game_display.Display;
import my_game_enemies.EnemyMotor;
import my_game_graphics.loadImage;
import my_game_motor.Motor;
import my_game_world.world;

public class gameManager {
	private Motor motor;
	private world world;
	
	private long time = System.nanoTime();
	private long delay;
	private int health;
	
	private ArrayList<EnemyMotor> eMotor;
	
	
	public gameManager() {
		motor = new Motor();
		world = new world(motor); 
		eMotor = new ArrayList<EnemyMotor>();
		
		delay = 2000;
		health = 3;
	}
	public void init() {
		loadImage.init();
		motor.init();
	}
	public void tick() {
		motor.tick();
		Random rand = new Random();
		int randx = rand.nextInt(338);
		while(randx<124)
		{
			randx = rand.nextInt(338);
		}
		int randy = rand.nextInt(Display.height);
		
		
		for(int i=0;i<eMotor.size();i++)
		{
			//player
			int px = motor.getX();
			int py = motor.getY();
			
			int ex = eMotor.get(i).getX();
			int ey = eMotor.get(i).getY();
			
			if(px < ex + 40 && px + 40 > ex   &&   py < ey + 40 && py + 40 > ey)
			{
				eMotor.remove(i);
				i--;
				health--;
				motor.setHealth(health);
				motor.setSpeed(0);
			}
			
		}
		
		long elapsed = (System.nanoTime() - time)/1000000;
		if(elapsed>delay)
		{
			if(motor.getSpeed()>=3) {
				eMotor.add(new EnemyMotor(motor,randx,-randy+motor.getOfset()));
			}
			time = System.nanoTime();
		}
		
		
		//enemy Motor.
		for(int i=0;i<eMotor.size();i++) {
			eMotor.get(i).tick();
		}
		
	}
	public void render(Graphics g) {
		world.render(g);
		motor.render(g);
		for(int i=0;i<eMotor.size();i++) {
			eMotor.get(i).render(g);;
		}
	}
}
