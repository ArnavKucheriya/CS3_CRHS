import java.util.*;
import java.awt.*;
import java.io.*;

public class BruteForce{
	public static void main(String[] args) throws IOException{
		Scanner file = new Scanner(new File("point.txt"));
		int size = file.nextInt();
		Point[]ary = new Point[size];
		for(int i=0;i<size;i++){
			ary[i] = new Point(file.nextInt(), file.nextInt());
		}

		// find shortest pair by nested loop
		ArrayList<Point>list = new ArrayList<Point>();
		double smallest = Integer.MAX_VALUE;
		Point one = new Point();
		Point two = new Point();
		for(int i=0;i<ary.length;i++){
			for(int j=i+1; j<ary.length;j++){
				if( ary[i].distance(ary[j]) < smallest){
					smallest = ary[i].distance(ary[j]);
					list.clear();
					list.add(ary[i]);
					list.add(ary[j]);
				}
				else if( Math.abs(ary[i].distance(ary[j]) - smallest) <.0000000001){
					list.add(ary[i]);
					list.add(ary[j]);
				}
			}
		}
		System.out.println(smallest);
		for(int i=0;i<list.size();i+=2){
			System.out.println(list.get(i)+ " " + list.get(i+1));
		}
	}
}
