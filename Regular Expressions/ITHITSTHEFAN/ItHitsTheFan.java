package Labs.ReGex.ITHITSTHEFAN;//Name - Arnav Kucheriya
//Date -
//Class - Computer Science 4
//Lab  - ReGex Lab 2

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItHitsTheFan
{
	public int count;

	public ItHitsTheFan()
	{
		count = 0;

	}


	// use the Pattern and Matcher classes
	public void countLine(String str)
	{
		Pattern p = Pattern.compile("\sit\s");
		Matcher m = p.matcher(str);

		while (m.find()){
			count++;
		}
	}

	public String toString()
	{

		return "" + count + " times";
	}
}