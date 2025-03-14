package Labs.ReGex.SUBSTITUTION;//Name -
//Date -
//Class -
//Lab  -

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Substitution
{
	public Substitution()
	{
		String orig = "This is a list of students: studentID=1234, studentID=4865, studentID=7894, studentID=7744, studentID=231233.";
		orig+="\nIt's not comprehensive.";
		System.out.println(orig);
		StringBuffer sb = new StringBuffer();

		Pattern p;

		p = Pattern.compile("(?<=studentID=)[0-9]*");
		Matcher m = p.matcher(orig);



		while (m.find())
		{
			System.out.println("\"masked\" :" + m.group());

			m.appendReplacement(sb,   "masked"  );



		}

		m.appendTail((sb));

		System.out.println(sb.toString());

	}
	public static void main(String[] args){
		new Substitution();
	}
}