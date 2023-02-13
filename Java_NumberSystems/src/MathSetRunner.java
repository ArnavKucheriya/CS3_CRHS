//� A+ Computer Science  -  www.apluscompsci.com
//Name - Arnav Kucheriya
//Date - September 19
//Class - COMP SCI 3 K
//Lab  - SETS

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class MathSetRunner
{
	public static void main(String args[]) throws IOException
	{
		//add test cases
		Scanner amongus = new Scanner(System.in);
		System.out.println("FILE YES: ");
		File no = new File(amongus.nextLine());
		Scanner kotlin = new Scanner(no);
		while (kotlin.hasNextLine()){
			MathSet maybe = new MathSet(kotlin.nextLine(),kotlin.nextLine());
			System.out.println(maybe);
			System.out.println("Union - " + maybe.union());
			System.out.println("Intersection - " + maybe.intersection());
			System.out.println("Difference A-B - " + maybe.differenceAMinusB());
			System.out.println("Difference B-A - " + maybe.differenceBMinusA());
			System.out.println("Symmetric Difference - " + maybe.symmetricDifference());
			System.out.println("\n");
		}
	}
}


