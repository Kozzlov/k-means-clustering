package nai_4;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;


public class Main {	
	private final static String centroidText = "enter a number of clasters you want";
	
	public static int getInput(String title) {
	
		String temp = JOptionPane.showInputDialog(title);
		try {
			int input = Integer.parseInt(temp);
			return input;
		}
		catch(Exception e) {
			return -1;
		}
	}
	
	
	public static double generateRandValue(double rangeMin, double rangeMax) {
		Random r = new Random();
		return rangeMin + (rangeMax - rangeMin) * r.nextDouble();
	}
		
	
	
	public static void main(String[] args) {
	// reading all points(centroids)
		List <Point> points = new ArrayList<Point>();		
		String IrisFile = new String("D:\\Рабочий стол\\train.txt");
		KScanner ks = new KScanner();
		points = ks.readData(IrisFile);
		System.out.println(points.size());
		int numOfCentroids = getInput(centroidText); //here we are asking for a number of centroids	
		if(numOfCentroids < 0) {
			System.err.println("number should be bigger than 0. ");
		}
		else {
			//Now when we know the number of centroinds we are giving them the random location.
			List <Centroid> centroids = new ArrayList<Centroid>();
			int vectorSize = points.get(0).getLength();
			
			//get the ranges:
			List<Range> ranges = new ArrayList<Range>();
			for(int i=0 ; i<vectorSize; i++) {
				ranges.add(new Range());
			}
			for(Point point: points) {
				for(int i=0 ; i<vectorSize; i++) {
					if(ranges.get(i).getMin() > point.getVector().get(i)) {
						ranges.get(i).setMin(point.getVector().get(i));
					}
					if(ranges.get(i).getMax() < point.getVector().get(i)) {
						ranges.get(i).setMax(point.getVector().get(i));
					}
				}
			}
			
			
			//all the centroids have the same size of vector, that's why we are using the first one for get the size.
			for(int i=0; i<numOfCentroids; i++) {
				List<Double> vectors = new ArrayList<Double>();
				for(int j = 0; j<vectorSize; j++) {
					vectors.add(generateRandValue(ranges.get(j).getMin(),ranges.get(j).getMax())); //here we are giving them random location
				}
				centroids.add(new Centroid(vectors));
				//here we add all the centroids
				System.out.println("First position: "+Arrays.toString(centroids.get(centroids.size()-1).getVector().toArray()));
			}
			//we added centroids and points.
			//step 2 identify to which centroids points are beloning.
			
			boolean finished = false;
			//here we created while loop(until centroinds change their positions, we are going through)
			while (!finished) {
				//clear clusters
				//for all centroids we clear all the clusters and new position, because we calculate each iteriation them from 0.
				for(Centroid centroid: centroids) {
					centroid.getCluster().clear();
					centroid.getNewCoordinates().clear();
				}
				
				//for all the points we want to find the best fitting centroid for them.
				for(Point  point : points) {
					Centroid currentBestCentroid = null;
					double currentBestDistance = Double.MAX_VALUE;
					//From the beginning we don't have the best centroid, that's why we set him to null, and currentBestDistance is MAX,
					//because nothing can be bigger than that!!!
					for(Centroid centroid : centroids ) {
						//for each point we are checking distance for all centroinds, and if we find the best fitting one, we are adding this point to centroid.
						double distance = 0;
						List<Double> pVector = point.getVector();
						List<Double> pCentroid = centroid.getVector();
						for(int i=0; i<vectorSize; i++) {
							//distance algorithm Manhattan(check notes)
							distance+=Math.pow(pVector.get(i)-pCentroid.get(i),2);
						}
						if(distance < currentBestDistance){
							currentBestDistance=distance;
							currentBestCentroid = centroid;
						}
					}
					//here we add point to best fitting centroid
					currentBestCentroid.getCluster().add(point);
				}
				//step 3 set new Centroid point
	
				//if we set all the points to the centroids, we have to calculate new position of centroid from the sum of all the points divided by the number of points.
				for(Centroid centroid : centroids) {
					List <Double> cNewPosition  = new ArrayList<Double>();
					List<Point> cluster = centroid.getCluster(); //all the poinnts of centroid
					if(cluster.size() != 0) { //if there is any centroid, we want to set new position
						for(int i = 0; i<vectorSize; i++) { //for each vector dimension
							double sum = 0;//we sum them
							for(Point point : cluster) {
								sum+=point.getVector().get(i);
							}
							sum = sum/cluster.size();
							//and then we divide them by the number of points
							cNewPosition.add(sum);
							//add the end we add the new vector dimension value
						}
						centroid.setNewCoordinates(cNewPosition);
						//after all the new vector(what is in centre of the points) to centroid
					}
				
				}
				//step 4 test if position is the same
				//we test there is old position is the same as the new position, and if all of them not changed. it means that algorithm has ended.
				finished = true;
				for(Centroid centroid : centroids) {
					List<Double> vector = centroid.getVector(); //old vector
					List<Double> nVector = centroid.getNewCoordinates(); //new vector
						if(nVector.size() != 0) { //if we have new vector
						for(int i=0; i<vectorSize; i++) {
							if(!vector.get(i).equals(nVector.get(i))) { //then we check if they are equal
								//if one of them is not equal, it means algorithm is still going!!!
								finished = false;
							}
						}
						//at the end, we set new vector as old vector
						centroid.getVector().clear();
						centroid.getVector().addAll(nVector);
					}
				}
			}
			//step 6 final result
			//and here, after algoritms ends, we show the results
			int index = 0;
			for(Centroid centroid: centroids) {
				System.out.println("Centroid " + index + " Position: " + Arrays.toString(centroid.getVector().toArray()));
				System.out.println("Size " + centroid.getCluster().size());
				System.out.println(centroid.GetClusterArray());
				index++;
			}
		}

	}
}
