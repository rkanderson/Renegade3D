package lib;

public class PolygonNode3D extends PolygonNode {

	public double[] zpoints;
	
	public PolygonNode3D(int[] xpoints, int[] ypoints, double[] zpoints, int npoints) {
		super(xpoints, ypoints, npoints);
		this.zpoints = zpoints;
	}
	

}
