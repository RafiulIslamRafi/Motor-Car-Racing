package my_game_world;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import my_game_display.Display;
import my_game_motor.Motor;
import my_game_tile.Tile;

public class world {
	private int width,height;
	private Motor motor;
	int[][] tile;
	
	public world(Motor motor)
	{
		loadWorld("res/world.txt");
		this.motor = motor;
	}
	
	public String loadFile(String path) {
		StringBuilder sr = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line;
			while((line=reader.readLine())!=null) {
				sr.append(line + "\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sr.toString();
	}	
	
	private int parseInt(String number) {
		return Integer.parseInt(number);
	}
	
	private void loadWorld(String path) {
		String file = loadFile(path);
		String[] space = file.split("\\s+");
		width = parseInt(space[0]);
		height = parseInt(space[1]);
		tile = new int[width][height];
		for(int i=0;i<width; i++) {
			for(int j=0;j<height; j++) {
				tile[i][j] = parseInt(space[2+(i+j * width)]);
			}
		}
	}
	
	
	public void render(Graphics g)
	{
		int start = Math.max(0, (motor.getOfset())/Tile.tileHeight);
		int end = Math.min(height, (motor.getOfset() + Display.height + 20) / Tile.tileHeight);
		for(int i=0;i<width;i++)
		{
			for(int j=start;j<end;j++)
			{
				Tile t = Tile.tiles[tile[i][j]];
				t.render(g, i*Tile.tilewidth, (j*Tile.tileHeight) - motor.getOfset());
			}
		}
	}
}
