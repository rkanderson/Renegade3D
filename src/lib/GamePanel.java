package lib;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener {

	private Scene scene;
	
	public GamePanel() {
		super();
		//AffineTransform tform = AffineTransform.getTranslateInstance(0, getHeight());
		//tform.scale( 1, -1);
		//Graphics g2 = (Graphics)getGraphics();
		//g2.setTransform(tform);
		
	}
	
	public void initialize() {
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		topFrame.setResizable(false);
	}
	
	public void setScene(Scene s) {
		scene = s;
		s.initialize();
	}
	
	public void update(double deltaTime) {
		scene.update(deltaTime);
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(0, getHeight());
		g2.scale(1, -1);
		// Draw black background
		g.setColor(scene.backgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// render all the nodes
		if(scene.camera == null){
			renderNode(g, scene, null);
		} else {
			renderNode(g, scene, scene.camera);
			renderNode(g, scene.camera, scene.camera);
		}
		
		
	}
	

	
	double[] getAbsolutePositionOfNode(Node n) {
		double[] absPos = new double[]{n.position.x, n.position.y, n.zPosition};
		if(n.parent != null) {
			double[] parentAbsPos = getAbsolutePositionOfNode(n.parent);
			absPos[0] += parentAbsPos[0];
			absPos[1] += parentAbsPos[1];
			absPos[2] += parentAbsPos[2];
		}
		return absPos;
	}
	
	void renderNode(Graphics g, Node n, CameraNode camera) {
		
		// The node's stored position might be relative to the parent's position.
		// Before rendering, we need the absolute coordinates.
		double[] absPos = getAbsolutePositionOfNode(n); //(x,y,z)

		// To help in rendering simple 2D objects, let's define some variables right here
		// and right now that hold the object's exact rendering position, taking into account the
		// origin of the scene and the camera position
		double renderX = absPos[0] + scene.width*scene.anchorX;
		double renderY = absPos[1] + scene.height*scene.anchorY;
		if(camera==null) {
			//done
		} else {
			renderX -= camera.position.x;
			renderY -= camera.position.y;
		}
		
		
		if(n instanceof ColorNode) {
			ColorNode cnode = (ColorNode)n;
			g.setColor(cnode.color);
			
			g.fillRect((int)(renderX - cnode.width*cnode.anchorX), 
					(int)(renderY - cnode.height*cnode.anchorY),
					cnode.width, cnode.height);
			
		} else if(n instanceof PolygonNode3D) {
			PolygonNode3D p3 = (PolygonNode3D)n;
			int[] renderXPts = p3.polygon.xpoints.clone();
			int[] renderYPts = p3.polygon.ypoints.clone();
			double[] renderZPts = p3.zpoints.clone();

			for(int i=0; i<p3.polygon.npoints; i++) {
				renderXPts[i] += absPos[0] - camera.position.x;
				renderYPts[i] += absPos[1] - camera.position.y;
				
				renderZPts[i] += absPos[2] - camera.zPosition;
				// renderZPts[x] is a measure of z distance from camera. We can use this
				// to transform the renderX and renderY points (still relative to camera)
				renderXPts[i] /= renderZPts[i];
				renderYPts[i] /= renderZPts[i];
				
				// Points successfully transformed relative to camera. Now to take into
				// account the anchor point of the scene, and we're all set.
				renderXPts[i] += scene.width*scene.anchorX;
				renderYPts[i] += scene.height*scene.anchorY;
			}
			g.setColor(p3.color);
			if(p3.type == PolygonNode.TYPE_FILLED) {
				g.fillPolygon(renderXPts, renderYPts, p3.polygon.npoints);
			} else if(p3.type == PolygonNode.TYPE_OUTLINED) {
				g.drawPolygon(renderXPts, renderYPts, p3.polygon.npoints);

			}
			
			
		} else if(n instanceof PolygonNode) {
			PolygonNode pnode = (PolygonNode)n;
			int[] renderXPts = pnode.polygon.xpoints.clone();
			int[] renderYPts = pnode.polygon.ypoints.clone();
			for(int i=0; i<pnode.polygon.npoints; i++) {
				renderXPts[i] += absPos[0] - camera.position.x + scene.width*scene.anchorX;
				renderYPts[i] += absPos[1] - camera.position.y + scene.height*scene.anchorY;
			}
			
			
			g.setColor(pnode.color);
			if(pnode.type == PolygonNode.TYPE_FILLED) {
				g.fillPolygon(renderXPts, renderYPts, pnode.polygon.npoints);
			} else if(pnode.type == PolygonNode.TYPE_OUTLINED) {
				g.drawPolygon(renderXPts, renderYPts, pnode.polygon.npoints);
			}
			

		}
		
		
		if(n.children.size() == 0) {
			// Then there is nothing else to be done!
			return;
		} else {
			// There are children. Render all the children as well. Recursion power!
			for(Node child: n.children) {
				renderNode(g, child, camera);
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	private ArrayList<Integer> keysDown = new ArrayList<Integer>();
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(keysDown.contains(e.getKeyCode())) {
			return;
		} else {
			keysDown.add(e.getKeyCode());
			scene.keyDown(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(keysDown.contains(e.getKeyCode())) {
			scene.keyReleased(e);
			keysDown.remove(new Integer(e.getKeyCode())); 
		}
		
	}

}
