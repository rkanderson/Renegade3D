package gameCode;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.event.KeyEvent;

import lib.ColorNode;
import lib.PolygonNode;
import lib.Scene;

public class GameScene extends Scene {

	// Here in this class, we have ultimate power over the game
	// Anything can be done. ANYTHING!!!! >:D
	

	public GameScene(double width, double height) {
		super(width, height);
	}

	ColorNode n;
	PolygonNode pl;
	@Override
	public void initialize() {
//		n = new ColorNode(160, 60);
//		n.x = 50;
//		n.y = 50;
//		this.addChild(n);
//		ColorNode c = new ColorNode(30, 30);
//		c.color = Color.BLACK;
//		c.anchorX = 0.5; c.anchorY = 0.5;
//		n.addChild(c);
//		
//		Polygon p = new Polygon();
//		p.xpoints = new int[]{50, 50, 100};
//		p.ypoints = new int[]{60, 50, 50};
//		p.npoints = 3;
//		pl = new PolygonNode(p);
//		pl.type = PolygonNode.TYPE_OUTLINED;
//		this.addChild(pl);
	}

	@Override
	public void update(double deltaTime) {
//		n.y += 50*deltaTime;
//		
//		pl.x += 40*deltaTime;
//		pl.polygon.ypoints[1] -= 20*deltaTime;
	}

	@Override
	public void keyDown(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
