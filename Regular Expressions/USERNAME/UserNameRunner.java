package Labs.ReGex.USERNAME;//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;
import java.util.Scanner;
import java.io.*;

public class UserNameRunner
{
	public static void main ( String[] args ) throws FileNotFoundException
	{
		Username Obj = new Username();

		File text = new File("names.txt");
		Scanner input = new Scanner(text);

		while (input.hasNext()){

			Obj.match(input.nextLine());

		}
		System.out.println(Obj);




	}
}