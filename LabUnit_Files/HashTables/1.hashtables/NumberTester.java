//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class - 
//Lab  -

public class NumberTester
{
	public static void main(String[] args)
	{
		Number one = new Number(45);
		System.out.println(one.hashCode());
		
		Number two = new Number(107);
		System.out.println(two.hashCode());
		
		Number three = new Number(213);
		System.out.println(three.hashCode());				
	}
}