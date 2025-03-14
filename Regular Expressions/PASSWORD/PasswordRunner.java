//Name - Arnav Kucheriya
//Date - September 14, 2022
//Class - PB MAD COMP SCI K
//Lab  - Pasword Validation (Regex 1-6)

import java.util.Scanner;
import java.lang.System.*;

public class PasswordRunner
{
	public static void main ( String[] args ) {


		Scanner input = new Scanner(System.in);
		System.out.println("Enter a password. A valid password will be 6-20 characters long with at least one upper case letter, one lower case letter, a digit and a special symbol (Ex - @,#,$,%)");


		String str = input.next();

		Password test = new Password();
		test.Hatches(str);

		System.out.println(test);


	}
}