package Labs.ReGex.USERNAME;
//Name -
//Date -
//Class -
//Lab  -

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Username
{
	private int count;

	public Username()
	{
		count = 0;

	}
public void match(String str)
{
	Pattern p = Pattern.compile("[A-Z0-9_-]{4,16}", Pattern.CASE_INSENSITIVE);
	Matcher m = p.matcher(str);

	if (m.matches()){
		count++;
	}
}





	public String toString()
	{
		return "This list has " + count +" valid user names.";
	}
}