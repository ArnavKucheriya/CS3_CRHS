//� A+ Computer Science  -  www.apluscompsci.com
//Name - Arnav Kucheriya
//Date -  September 19, 2022
//Class - COMP SCI 3 K
//Lab  - SETS

import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.Scanner;
import static java.lang.System.*;

public class OddEvenSets
{
	private Set<Integer> odds;
	private Set<Integer> evens;

	public OddEvenSets()
	{
		odds = new TreeSet<Integer>();
		evens = new TreeSet<Integer>();
	}

	public OddEvenSets(String line)
	{
		odds = new TreeSet<Integer>();
		evens = new TreeSet<Integer>();
		String[] set = line.split(" ");
		for(int i=0;i<set.length;i++){
			int no = Integer.parseInt(set[i]);
			if(no %2 ==0)
				evens.add(no);
			else
				odds.add(no);
		}
	}

	public String toString()
	{
		return "ODDS : " + odds + "\nEVENS : " + evens + "\n\n";
	}
}

