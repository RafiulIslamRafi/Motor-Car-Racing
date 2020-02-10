package my_game_display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	private String title;
	public static int width;
	public static int height;
	public static JFrame frame;
	public static Canvas canvas;
	public Display(String title, int width, int height) {
		this.title = title;
		Display.width = width;
		Display.height = height;
		createDisplay();
	}
	public void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width,height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width,height));
		//canvas.setBackground(Color.black);
		canvas.setFocusable(false);
		frame.add(canvas);
		frame.pack();
	}
	
	
	
	
}
