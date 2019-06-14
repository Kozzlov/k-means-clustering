package nai_4;


	import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.IOException;
	import java.nio.file.Files;
	import java.nio.file.Paths;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Scanner;
	import static java.nio.charset.StandardCharsets.UTF_8;

	public class KScanner {
		public static List<Point> readData(String fileName) {
		File file = new File(fileName);
		List<Point> readedData = new ArrayList<>();
		    try{
		    	BufferedReader reader = Files.newBufferedReader(Paths.get(file.toURI()), UTF_8);
		    	String line = reader.readLine();
	            do {
	            	String points[] = line.split(",");
	            	List<Double> temp = new ArrayList<Double>();
	            	for (int i = 0; i < points.length-1; ++i) {
	            		try {
	            			temp.add(Double.parseDouble(points[i]));
	            		} catch (NumberFormatException e) {
	            			e.printStackTrace();
	            		}
	            	}
	            	readedData.add(new Point(temp));
	                line = reader.readLine();
	            }   while (line != null);
	            reader.close();
	        }
	        catch (IOException e) {
	            System.out.println("Impossible to read data!");
	            System.exit(-2);
	        }
		    return readedData;
	}
}
