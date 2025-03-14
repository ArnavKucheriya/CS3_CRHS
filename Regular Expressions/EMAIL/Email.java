//Name - Arnav Kucheriya
//Date - September 19, 2022
//Class - PB COMP SCI MAD K
//Lab  - Email Validation (REGEX 6-10)

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email
{
	private String email;
	private boolean b;
	private boolean a;
	private static final String regex = "[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-])+(\\.[a-zA-Z]{2,3})*+$";

	public Email(String str)
	{
		this.email = str;
    }

	private boolean validate(boolean a){

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(this.email);
		b=m.matches();
		this.a=b;

		return b;

	}

	public String toString()
	{
		String out = "Email is ";
		out+=validate(a)?"valid : ":"invalid : ";
		return out + email;
	}
}