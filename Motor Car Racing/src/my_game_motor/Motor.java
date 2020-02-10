package my_game_motor;

import java.awt.Color;
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
	
	
	public Motor() {
		x = Display.width/2-10;
		y = Tile.tileHeight*120;
		ofset = 0;
		speed = 0.3f;
	}
	public void init() {
		Display.frame.addKeyListener(this);;
	}
	public void tick() {
		ofset = y - (Display.height - 100);
		if(right)
			x+=1;
		if(left) 
			x-=1;
		if(up)
		{
			speed += 0.03f;
			if(speed>=7) speed=7;
		}
		y -= speed;
		if(down)
		{
			speed -= 0.030f;
			if(speed<=0) speed =0; 
		}
	}
	
	public int getOfset()
	{
		return ofset;
	}
	public void render(Graphics g) {
		g.setColor(Color.red);
		//g.fillRect(x, y - ofset, 40, 60);
		g.drawImage(loadImage.motorP,x,y-ofset,40,60,null);
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
