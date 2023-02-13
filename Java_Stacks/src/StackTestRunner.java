//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class -
//Lab  -

import static java.lang.System.*;

public class StackTestRunner
{
	public static void main ( String[] args )
	{
		StackTester test = new StackTester("a b c d e f g h i j k l m n o p q r s t u v w x y z");
		test.popEmAll();

		test = new StackTester("1 2 3 4 5 6 7 8 9 0");
		test.popEmAll();

		test = new StackTester("< _ > ");
		test.popEmAll();
	}
}
