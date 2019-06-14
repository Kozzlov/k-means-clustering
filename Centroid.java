package nai_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Centroid extends Point {

	private List<Point> cluster;
	private List<Double> newCoordinates = new ArrayList<>();
	
	public List<Point> getCluster() {
		return cluster;
	}

	public List<Double> getNewCoordinates() {
		return newCoordinates;
	}

	public void setNewCoordinates(List<Double> newCoordinates) {
		this.newCoordinates = newCoordinates;
	}

	public void setCluster(List<Point> cluster) {
		this.cluster = cluster;
	}

	public Centroid(List<Double> coordinates) {
		super(coordinates);
		cluster = new ArrayList<Point>();
	}
	
	public String GetClusterArray() {
		StringBuilder sb = new StringBuilder();
		for(Point p : cluster) {
			sb.append(p.getVector().toString());
		}
		return sb.toString();
	}

}
