package gameCode;

import java.awt.*;
import java.awt.event.KeyEvent;

import lib.*;

public class MyCool3dScene extends Scene {

	//Lets test some 3D!
	boolean movingRight = false, movingLeft = false,
			movingUp = false, movingDown = false,
			movingForward = false, movingBackward = false;
	PolygonNode3D face1;
	public MyCool3dScene(double width, double height) {
		super(width, height);
		
	}	
	
	@Override
	public void initialize() {
		
		CameraNode cam = new CameraNode();
		this.camera = cam;
		
		
		face1 = new PolygonNode3D(new int[]{100, -100, -100, 100},
				new int[]{-50, -50, -50, -50},
				new double[]{0.5, 0.5, 1.0, 1.0}, 
				4);
		face1.color = Color.RED;
		addChild(face1);
		
		PolygonNode3D face2 = new PolygonNode3D(new int[]{100, -100, -100, 100},
				new int[]{50, 50, 50, 50},
				new double[]{1, 1, 1.2, 1.2}, 
				4);
		face2.color = Color.GREEN;
		addChild(face2);
		
		
		
//		PolygonNode3D face2 = new PolygonNode3D(new int[]{-150, -250, -250, -150},
//				new int[]{-100, -100, -100, -100},
//				new double[]{1, 1, 2, 2}, 
//				4);
//		face2.color = Color.RED;
//		addChild(face2);
		
//		PolygonNode3D face2 = new PolygonNode3D(new int[]{100, 200, 200, 100},
//				new int[]{-200, -200, 50, 50},
//				new double[]{1, 1, 2, 2}, 
//				4);
//		face2.color = Color.RED;
//		addChild(face2);
//		
		
		Node crosshairs = new Node();
		ColorNode ch1 = new ColorNode(3, 80);
		ch1.color = Color.BLACK;
		ch1.anchorX = 0.5; ch1.anchorY = 0.5;
		crosshairs.addChild(ch1);
		ColorNode ch2 = new ColorNode(80, 3);
		ch2.color = Color.BLACK;
		ch2.anchorX = 0.5; ch2.anchorY = 0.5;
		crosshairs.addChild(ch2);
		camera.addChild(crosshairs);
		System.out.println();
		
		
	}

	@Override
	public void update(double deltaTime) {
		if(movingLeft) {
			camera.position.x -= 150*deltaTime;
		}
		if(movingRight) {
			camera.position.x += 150*deltaTime;
		}
		if(movingUp) {
			camera.position.y += 150*deltaTime;
		}
		if(movingDown) {
			camera.position.y -= 150*deltaTime;
		}
		if(movingForward) {
			camera.zPosition += 1*deltaTime;
		}
		if(movingBackward) {
			camera.zPosition -= 1*deltaTime;
		}
		
	}

	@Override
	public void keyDown(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			movingRight = true;
		} else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			movingLeft = true;
		} else if(e.getKeyCode()==KeyEvent.VK_UP) {
			movingUp = true;
		} else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			movingDown = true;
		} else if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			movingForward = true;
		} else if(e.getKeyCode()==KeyEvent.VK_SHIFT) {
			movingBackward = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			movingRight = false;
		} else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			movingLeft = false;
		} else if(e.getKeyCode()==KeyEvent.VK_UP) {
			movingUp = false;
		} else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			movingDown = false;
		} else if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			movingForward = false;
		} else if(e.getKeyCode()==KeyEvent.VK_SHIFT) {
			movingBackward = false;
		}
		
	}


}
