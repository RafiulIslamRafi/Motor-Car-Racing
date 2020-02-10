package my_game_manager;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import my_game_enemies.EnemyMotor;
import my_game_graphics.loadImage;
import my_game_motor.Motor;
import my_game_world.world;

public class gameManager {
	private Motor motor;
	private world world; 
	private ArrayList<EnemyMotor> eMotor;
	
	
	public gameManager() {
		motor = new Motor();
		world = new world(motor); 
		eMotor = new ArrayList<EnemyMotor>();
	}
	public void init() {
		loadImage.init();
		motor.init();
	}
	public void tick() {
		Random rand = new Random();
		int randx = rand.nextInt(338);
		while(randx<124)
		{
			randx = rand.nextInt(338);
		}
		int randy = rand.nextInt(1 + motor.getOfset());
		
		eMotor.add(new EnemyMotor(motor,randx,randy));
		
		motor.tick();
		
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
