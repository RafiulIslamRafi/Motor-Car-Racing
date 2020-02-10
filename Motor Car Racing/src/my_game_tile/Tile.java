package my_game_tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	public static Tile[] tiles = new Tile[24];
	
	public static Tile roadTile = new roadTile(0);
	public static Tile grassTile = new grassTile(1);
	public static Tile footpathTile = new footpathTile(2);
	
	public BufferedImage texure;
	public static final int tilewidth = 63,tileHeight = 63;
	
	public Tile(BufferedImage texure,int id)
	{
		this.texure = texure;
		tiles[id] = this;
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texure, x, y, null);
	}
}
