package lib;

import java.awt.Color;
import java.awt.Polygon;

public class PolygonNode extends Node {
	public static int TYPE_FILLED = 1;
	public static int TYPE_OUTLINED = 2;
	public Polygon polygon;
//	public int[] xpoints, ypoints[];
	public Color color = Color.RED;
	public int type = TYPE_FILLED;
	public PolygonNode(int[] xpoints, int[] ypoints, int npoints) {
		super();
		polygon = new Polygon();
		polygon.xpoints = xpoints;
		polygon.ypoints = ypoints;
		polygon.npoints = npoints;
	}
}
