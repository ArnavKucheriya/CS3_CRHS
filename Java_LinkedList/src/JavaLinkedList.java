//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import java.util.LinkedList;
import java.util.List;

import static java.lang.System.*;

public class JavaLinkedList
{
	private LinkedList<Integer> list;

	public JavaLinkedList()
	{
		list = new LinkedList<Integer>();
	}

	public JavaLinkedList(int[] nums)
	{
		list = new LinkedList<Integer>();
		for(int num : nums)
		{
			list.add(num);
		}
	}

	public double getSum(  )
	{
		double total=0;
		for (int nums:list){
			total+=nums;
		}
		return total;
	}

	public double getAvg(  )
	{
		double total=0;
		for (int nums:list){
			total+=nums;
		}
		return total;
	}

	public int getLargest()
	{
		int largest=Integer.MIN_VALUE;
		for (int num:list){
			if (largest<num) largest =num;
		}
		return largest;
	}

	public int getSmallest()
	{
		int smallest = smallest=Integer.MAX_VALUE;
		for (int num:list){
			if (smallest>num) smallest=num;
		}
		return smallest;
	}

	public String toString()
	{
		String output="SUM::\s" + getSum() + "\nAverage::\s" + getAvg() + "\nSmallest::\s" + getSmallest() + "\nLargest::\s"+ getLargest();
		return output;
	}
}