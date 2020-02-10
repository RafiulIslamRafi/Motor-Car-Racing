package my_game_graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class loadImage {
	
	public static BufferedImage fullImage, road, fullImage2, grass, fullImage3, footpath, fullImage4, fullImage5, motorP,motorE;
	
	public static void init() {
		fullImage = imageLoader("/roadImage.jpg");
		fullImage2 = imageLoader("/grass3.jpg");
		fullImage3 = imageLoader("/footpath.jpg");
		fullImage4 = imageLoader("/mc.png");
		fullImage5 = imageLoader("/ec.png");
		crop();
	}
	public static BufferedImage imageLoader(String path) {
		try {
			return ImageIO.read(loadImage.class.getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	public static void crop()
	{
		road = fullImage.getSubimage(490,460, 100, 100);
		grass = fullImage2.getSubimage(0,100, 100, 100);
		footpath = fullImage3.getSubimage(10,10, 100, 100);
		motorP = fullImage4.getSubimage(0, 0, 158, 320);
		motorE = fullImage5.getSubimage(0, 0, 158, 320);
	}
}
