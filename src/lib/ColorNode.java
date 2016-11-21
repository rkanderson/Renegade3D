package lib;

import java.awt.Color;

public class ColorNode extends Node {
	public int width, height;
	public double anchorX = 0, anchorY = 0;
	public Color color = Color.RED;
	public ColorNode(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}
}
