package my_game_manager;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import my_game_display.Display;
import my_game_enemies.EnemyMotor;
import my_game_food.Food;
import my_game_graphics.loadImage;
import my_game_motor.Motor;
import my_game_world.world;

public class gameManager {
	private Motor motor;
	private world world;
	
	private long time = System.nanoTime();
	private long time2 = System.nanoTime();
	private long delay,delay2;
	private int health,score,hscore; 
	private int oRandx,oRandy;
	
	private ArrayList<EnemyMotor> eMotor;
	private ArrayList<Food> gFood;
	
	public gameManager() {
		motor = new Motor();
		world = new world(motor); 
		eMotor = new ArrayList<EnemyMotor>();
		gFood = new ArrayList<Food>();
		
		delay = 2000;
		delay2 = 60000;
		health = 3;
		oRandx = 0;
		oRandy = 0;
		score = 0;
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
		
		
		//for enemy motor remove and health decrease
		for(int i=0;i<eMotor.size();i++)
		{
			//player
			int px = motor.getX();
			int py = motor.getY();
			
			int ex = eMotor.get(i).getX();
			int ey = eMotor.get(i).getY();
			
			if(px < ex + 40 && px + 40 > ex   &&   py < ey + 68 && py + 68 > ey)
			{
				eMotor.remove(i);
				i--;
				health--;
				motor.setHealth(health);
				motor.setSpeed(0);
			}
			if(py+200<ey) {
				eMotor.remove(i);
			}
			
			if(py + 70 <= ey && ey <= py + 80)
			{
				score++;
				motor.setScore(score);
				
				//for highScore Update.
				hscore = motor.getHScore();
				if(hscore<score)
				{
					//file write korbo hscore er value.
					// path = res/HScore.txt
					PrintWriter writer = null;
					try {
						writer = new PrintWriter("res/HScore.txt", "UTF-8");
					} catch (FileNotFoundException | UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					writer.println(score);
					writer.close();
				}
			}
		}
		
		//for food remove and health increase.
		for(int i=0;i<gFood.size();i++)
		{
			int px = motor.getX();
			int py = 500;
			int fx = gFood.get(i).getX();
			int fy = gFood.get(i).getY();
			
			if(fy>700) {
				gFood.remove(i);
			}
			
			
			if(px < fx + 40 && px + 40 > fx   &&   py < fy + 15 && py + 60 > fy)
			{
				gFood.remove(i);
				i--;
				if(health<5) {
					health++;
					motor.setHealth(health);
				}
			}
		}
		
		//for HighScore read.
		String[] number = world.loadFile("res/HScore.txt").split("\\s+");
		motor.setHighScore(Integer.parseInt(number[0]));
		
		//for EnemyMotor.
		long elapsed = (System.nanoTime() - time)/1000000;
		if(elapsed>delay && ((randy+60<=oRandy || randy>=oRandy+60) || (randx+40<=oRandx || randx>=oRandx+40)))
		{
			if(motor.getSpeed()>=3) {
				eMotor.add(new EnemyMotor(motor,randx,-randy+motor.getOfset()-40));
			}
			time = System.nanoTime();
			oRandx = randx;
			oRandy = randy;
		}
		
		
		//for food..
		long elapsed2 = (System.nanoTime() - time2)/1000000;
		if(elapsed2>delay2)
		{
			if(motor.getSpeed()>=3) {
				gFood.add(new Food(randx,-25));
			}
			time2 = System.nanoTime();
		}
		
		
		//enemy Motor.
		for(int i=0;i<eMotor.size();i++) {
			eMotor.get(i).tick();
		}
		
		//for gassFood.
		for(int i=0;i<gFood.size();i++)
		{
			gFood.get(i).tick();
		}
	}	
	
	public void render(Graphics g) {
		world.render(g);
		motor.render(g);
		for(int i=0;i<gFood.size();i++) {
			gFood.get(i).render(g);
		}
		for(int i=0;i<eMotor.size();i++) {
			eMotor.get(i).render(g);		
		}
	}
}
