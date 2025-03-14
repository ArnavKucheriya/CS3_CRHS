package Labs.ReGex.SSN;//Name -
//Date -
//Class -
//Lab  -

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SSN
{
	private String social;
	private boolean b=false;

	public SSN(String s)
	{
		social = s;


	}


	// use the Pattern and Matcher classes 000-00-0000
	public void validate()
	{
		Pattern p = Pattern.compile("[1-9][0-9]{2}-?[0-9]{2}-?[1-9][0-9]{3}");
		Matcher m = p.matcher(social);
		b = m.matches();





	}

	// use the String class and matches method. This method can be used for simple validations.
	public void matches()
	{
		validate();

	}

	public String toString()
	{
		String output="";


		if (b){
			output = "Valid";
		}
		else {output = "Invalid";}

		return output;
	}
}