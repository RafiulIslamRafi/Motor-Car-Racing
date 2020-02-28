package my_game_food;

import java.awt.Graphics;

import my_game_graphics.loadImage;

public class Food {
	private int x;
	private int y;
	//private Motor motor;
	public Food(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void tick()
	{
		y += 3;
		//if(motor.getSpeed()!=0.0) y += 3;
	}
	public void render(Graphics g) {
		//System.out.println(y+" - "+motor.getOfset()+" = "+ (y - motor.getOfset()));
		g.drawImage(loadImage.balloon, x, y, 30, 40, null);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
