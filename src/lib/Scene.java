package lib;

import java.awt.Color;
import java.awt.event.KeyEvent;

abstract public class Scene extends Node {
	
	public double width, height;
	public double anchorX = 0.5, anchorY = 0.5;
	
	public Scene(double width, double height) {
		this.width = width; this.height = height;
	}
	
	public Color backgroundColor = Color.BLACK;
	public CameraNode camera;
	
	abstract public void initialize();
	abstract public void update(double deltaTime);
	abstract public void keyDown(KeyEvent e);
	abstract public void keyReleased(KeyEvent e);
	
}
