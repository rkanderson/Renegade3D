package lib;

import util.*;
import java.util.ArrayList;

public class Node {
	// A generic class to represent any game object. Visible or invisible
	// Should contain basic universal properties such as coordinates, rotation,
	// and scale
	
	public DoublePoint position = new DoublePoint(0, 0);
	public double zPosition = 0;
	public double zRotation = 0; //<-In radians
	// Lacks rotation or scale properties for now
	public ArrayList<Node> children = new ArrayList<Node>();
	public Node parent;
	public Node() {
		
	}
	
	public boolean addChild(Node newNode) {
		if(newNode.parent == null) {
			children.add(newNode);
			newNode.parent = this;
			return true;
		} else {
			System.out.println("Cannot add a child that already has a parent.");
			return false;
		}
	}
}
