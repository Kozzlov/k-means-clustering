package nai_4;

public class Range {
	private double max;
	private double min;
	
	Range(){
		this.max = Double.MIN_VALUE;
		this.min = Double.MAX_VALUE;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}
	
}
