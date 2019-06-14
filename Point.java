package nai_4;

import java.util.ArrayList;
import java.util.List;

public class Point {
	
	private List<Double> coordinates = new ArrayList<>();
	int type;
	
	public Point(List<Double> coordinates) {
		this.coordinates=coordinates;
	}
	public List<Double>  getVector(){
		return coordinates;
	}
	
	public void setVector(List<Double> coordinates) {
		this.coordinates = coordinates;
	}

	
	public int getLength() {
		return this.coordinates.size();
	}
}
