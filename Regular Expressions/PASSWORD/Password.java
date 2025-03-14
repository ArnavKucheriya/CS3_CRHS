//Name - Arnav Kucheriya
//Date - September 14, 2022
//Class - PB MAD COMP SCI K
//Lab  - Password Validation (Regex 1-6)


// X*?  X, Zero or more times


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password
{
	private String pass;
	private boolean b;

	public void Password(String str)
	{
		pass = str;
	}

	public void Hatches(String pass){

		Pattern p = Pattern.compile("^(?=.*[0-9])"+"(?=.*[a-z])(?=.*[A-Z])"+"(?=.*[@#$%])"+"(?=\\S+$).{6,20}$");
		Matcher m;
		m = p.matcher(pass);

		b=m.matches();


	}



	public String toString()
	{
		String output ="";
		if (b){
			output = "Valid";
		}
		else {output="Invalid";}

		return output;
	}
}