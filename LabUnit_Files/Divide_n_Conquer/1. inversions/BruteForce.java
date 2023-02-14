import java.io.*;
import java.util.*;
public class BruteForce {

	private Comparable[] list;
	private long count;
    public BruteForce() throws IOException {

    	list = new Comparable[]{90,40,20,30,10,67};					// Set 1
    	count=0;
    	for(int i=0;i<list.length;i++)
    		for(int j=i+1;j<list.length;j++)
    			if(list[i].compareTo(list[j])>-1)
    				count++;
    	System.out.println(count + " inversions");

    	list = new Comparable[]{3,15,61,11,7,9,2};					// Set 2
    	count=0;
    	for(int i=0;i<list.length;i++)
    		for(int j=i+1;j<list.length;j++)
    			if(list[i].compareTo(list[j])>-1)
    				count++;
    	System.out.println(count + " inversions");

    	Scanner file = new Scanner(new File("IntegerArray.txt"));	// Set 3
		list = new Comparable[100000];
		for(int i=0; i<list.length;i++){
			list[i] = file.nextInt();
		}
		long start = System.currentTimeMillis();
		count=0;
    	for(int i=0;i<list.length;i++)
    		for(int j=i+1;j<list.length;j++)
    			if(list[i].compareTo(list[j])>-1)
    				count++;
    	long stop = System.currentTimeMillis();
    	System.out.println("count is: " + count + " in :" + ((stop-start)/1000) +" seconds");
    }

    public static void main(String[] args)throws IOException {
       new BruteForce();
    }
}
