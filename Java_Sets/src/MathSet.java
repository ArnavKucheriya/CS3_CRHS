//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -  
//Lab  -  

import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import static java.lang.System.*;

public class MathSet
{
	private Set<Integer> one;
	private Set<Integer> two;

	public MathSet()
	{
		one = new TreeSet<Integer>();
		two = new TreeSet<Integer>();
	}

	public MathSet(String o, String t)
	{
		one = new TreeSet<Integer>();
		two = new TreeSet<Integer>();
		String[] yes = o.split(" ");
		for(int i=0;i<yes.length;i++)
			one.add(Integer.parseInt(yes[i]));
		yes = t.split(" ");
		for(int i=0;i<yes.length;i++)
			two.add(Integer.parseInt(yes[i]));
	}

	public Set<Integer> union()
	{
		Set<Integer> yes = new TreeSet<Integer>();
		yes.addAll(one);
		yes.addAll(two);
		return yes;
	}

	public Set<Integer> intersection()
	{
		Set<Integer> yes = new TreeSet<Integer>();
		yes.addAll(one);
		Set<Integer> no = new TreeSet<Integer>();
		no.addAll(two);
		yes.retainAll(no);
		return yes;
	}

	public Set<Integer> differenceAMinusB()
	{
		Set<Integer> yes = new TreeSet<Integer>();
		yes.addAll(one);
		Set<Integer> no = new TreeSet<Integer>();
		no.addAll(two);
		yes.removeAll(no);
		return yes;
	}

	public Set<Integer> differenceBMinusA()
	{
		Set<Integer> yes = new TreeSet<Integer>();
		yes.addAll(one);
		Set<Integer> no = new TreeSet<Integer>();
		no.addAll(two);
		no.removeAll(yes);
		return no;
	}

	public Set<Integer> symmetricDifference()
	{
		Set<Integer> yes = differenceAMinusB();
		yes.addAll(differenceBMinusA());
		return yes;
	}

	public String toString()
	{
		return "Set one " + one + "\n" +    "Set two " + two +  "\n";
	}

}

