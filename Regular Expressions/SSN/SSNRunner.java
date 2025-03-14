package Labs.ReGex.SSN;//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;

public class SSNRunner
{
	public static void main ( String[] args )
	{



	  	SSN test = new SSN("462551254");
	  	test.matches();
		out.println(test);

		test = new SSN("462-551254");
	  	test.matches();
		out.println(test);

		test = new SSN("2475243");
		test.matches();
		out.println(test);

		test = new SSN("818-45-3698");
		test.validate();
		out.println(test);

		test = new SSN("444-444-4868");
		test.validate();
		out.println(test);

		test = new SSN("$123456789");
		test.validate();
		out.println(test);

		test = new SSN("123-45-6789");
		test.validate();
		out.println(test);

		test = new SSN("my social is 444-55-1234");
		test.validate();
		out.println(test);
	}
}