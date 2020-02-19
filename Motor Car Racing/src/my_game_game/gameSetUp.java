package my_game_game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import my_game_display.Display;
import my_game_manager.gameManager;

public class gameSetUp implements Runnable {
	
	private Thread thread;
	private String title;
	private int width;
	private int height;
	private BufferStrategy buffer;
	private Graphics g;
	private gameManager manager;
	
	public gameSetUp(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	
	public void init(){
		new Display(title,width,height);
		manager = new gameManager();
		manager.init();
	}
	public synchronized void start() {
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void tick() {
		manager.tick(); 
	}
	public void render() {
		buffer = Display.canvas.getBufferStrategy(); 
		if(buffer == null) {
			Display.canvas.createBufferStrategy(3);
			return; 
		}
		g = buffer.getDrawGraphics();
		
		//ager sob draw clear kore disci.
		g.clearRect(0, 0, width, height+300);
		
		//draw start.
		manager.render(g); 
		
		//next two line now execute on the manager class...
		//g.setColor(Color.red);
		//g.fillRect(12, y, 40, 40);
		
		buffer.show();
		g.dispose();
	}
	
	public void run() {
		init();
		
		int fps = 50;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long current  = System.nanoTime();	
				
		while(true) {
			delta = delta + (System.nanoTime()-current)/timePerTick;
			current = System.nanoTime();
			if(delta>=1) {
				tick(); 	//update korteci..
				render();
				delta--;
			}
		}
	}
}
