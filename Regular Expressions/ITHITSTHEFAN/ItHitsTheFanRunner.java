package Labs.ReGex.ITHITSTHEFAN;//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;
import java.util.Scanner;
import java.io.*;
import java.io.File;

public class ItHitsTheFanRunner
{
	public static void main ( String[] args ) throws FileNotFoundException {

		ItHitsTheFan Obj = new ItHitsTheFan();

		File text = new File("ItHitsTheFan.txt");
		Scanner input = new Scanner (text);

		while (input.hasNext()){
			String str = new String (input.nextLine());
			Obj.countLine(str);
		}
		System.out.println(Obj);





	}
}