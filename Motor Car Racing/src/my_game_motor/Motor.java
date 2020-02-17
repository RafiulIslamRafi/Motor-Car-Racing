package my_game_motor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import my_game_display.Display;
import my_game_graphics.loadImage;
import my_game_tile.Tile;

public class Motor implements KeyListener{
	private int x,y;
	private boolean left,right,up,down;
	private int ofset;
	
	private double speed;
	
	private int health,score;
	private int gare;
	
	public Motor() {
		x = Display.width/2-10;
		y = Tile.tileHeight*1000000 - 60;
		ofset = 0;
		speed = 0.3f;
		health = 3;
		gare = 0;
		score = 0;
	}
	public void init() {
		Display.frame.addKeyListener(this);
	}
	public void tick() {
		//System.out.println(x);
		if(health > 0)
		{
			ofset = y - (Display.height - 100);
			if(right && x<=338)
				x += 2;
			if(left && x >= 124)
				x -= 2;
			if(up)
			{
				speed += 0.03f;
				if(speed >= 10) speed = 10;
			}
			y -= speed;
			if(down)
			{
				speed -= 0.030f;
				if(speed<=0) speed =0; 
			}
		}
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public double getSpeed() {
		return speed;
	}
	public int getOfset()
	{
		return ofset;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
	public void setScore(int score)
	{
		this.score = score;
	}
	public void drawBoard(Graphics g)
	{
		int speedd = (int) speed;
		switch(speedd) {
		case 0: gare = 0; break;
		case 2: gare = 1; break;
		case 4: gare = 2; break;
		case 6: gare = 3; break;
		case 8: gare = 4; break;
		case 10: gare = 5; break;
		}
		
		g.setColor(Color.white);
		g.fillRect(10, 10, 150, 120);
		
		//draw gare and health..
		
		g.setColor(Color.black);
		String gareDisplay = Integer.toString(gare);
		g.setFont(new Font("arial",Font.BOLD,30));
		g.drawString("Gare  : "+gareDisplay, 20, 40);
		g.drawString("Car    : "+health, 20, 80);
		g.drawString("Score: "+score, 20, 120);
	}
	
	public void gameOver(Graphics g)
	{
		g.setColor(Color.red);
		g.setFont(new Font("arial",Font.BOLD,40));
		g.drawString("Game Over", Display.width/3-20, Display.height/2);
		
		//highScore write er code korbo...
		
	}
	
	public void render(Graphics g) {
		if(health  > 0)
		{
			g.setColor(Color.red);
			//g.fillRect(x, y - ofset, 40, 60);
			g.drawImage(loadImage.motorP,x,y-ofset,40,60,null);
		}
		else gameOver(g);
		drawBoard(g);
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyPressed(KeyEvent e) {   
		int sourse  = e.getKeyCode();
		if(sourse == KeyEvent.VK_RIGHT)
		{
			right = true;
		}
		if(sourse == KeyEvent.VK_LEFT)
		{
			left = true;
		}
		if(sourse == KeyEvent.VK_DOWN)
		{
			down = true;
		}
		if(sourse == KeyEvent.VK_UP)
		{
			up = true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		int sourse  = e.getKeyCode();
		if(sourse == KeyEvent.VK_RIGHT)
		{
			right = false;
		}
		if(sourse == KeyEvent.VK_LEFT)
		{
			left = false;
		}
		if(sourse == KeyEvent.VK_DOWN)
		{
			down = false;
		}
		if(sourse == KeyEvent.VK_UP)
		{
			up = false;
		}
	}
}
