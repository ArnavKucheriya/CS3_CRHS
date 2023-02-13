//� A+ Computer Science  -  www.apluscompsci.com
//Name - Arnav Kucheriya
//Date -  September 19, 2022
//Class - COMP SCI 3 K
//Lab  - SETS

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class OddRunner
{
	public static void main( String args[] ) throws IOException
	{
		//more test cases
		Scanner amongus = new Scanner(System.in);
		out.println("FILE NOW: ");
		String yes = amongus.nextLine();
		File no = new File(yes);
		Scanner maybe = new Scanner(no);
		while(maybe.hasNextLine()){
			OddEvenSets so = new OddEvenSets(maybe.nextLine());
			System.out.println(so.toString());
		}
	}
}

