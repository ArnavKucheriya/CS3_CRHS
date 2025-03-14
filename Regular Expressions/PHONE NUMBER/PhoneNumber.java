//Name - Arnav Kucheriya
//Date - September 19, 2022
//Class - MAD COMP SCI PB K
//Lab  - Phone Number Validation (REGEX 6-10)

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumber
{
	public static void main( String[] args) {
		String regexPattern = "\\d{10}|(?:\\d{3}[\\s]?[.-]*?){2}[\\s]?\\d{4}|\\(\\d{3}\\)[\\s]?\\d{3}[.-]*?[\\s]?\\d{4}";

		String phoneNumber1 = "123-456-7890";
		String phoneNumber2 = "( 123) 456-7890";
		String phoneNumber3 = "123 456 7890";
		String phoneNumber4 = "1234567890";
		String phoneNumber5 = "832-456-7829";
		String phoneNumber6 = "456-7829";
		String phoneNumber7 = "123-4B6-7890";
		String phoneNumber8 = "832.455.5548";

		Pattern p = Pattern.compile(regexPattern);
		Matcher m = p.matcher(phoneNumber1);
		Matcher m1 = p.matcher(phoneNumber2);
		Matcher m2 = p.matcher(phoneNumber3);
		Matcher m3 = p.matcher(phoneNumber4);
		Matcher m4 = p.matcher(phoneNumber5);
		Matcher m5 = p.matcher(phoneNumber6);
		Matcher m6 = p.matcher(phoneNumber7);
		Matcher m7 = p.matcher(phoneNumber8);

		System.out.println(String.format("%-17s%s: %b",phoneNumber1," matches:", m.matches()));
		System.out.println(String.format("%-17s%s: %b",phoneNumber2," matches:", m1.matches()));
		System.out.println(String.format("%-17s%s: %b",phoneNumber3," matches:", m2.matches()));
		System.out.println(String.format("%-17s%s: %b",phoneNumber4," matches:", m3.matches()));
		System.out.println(String.format("%-17s%s: %b",phoneNumber5," matches:", m4.matches()));
		System.out.println(String.format("%-17s%s: %b",phoneNumber6," matches:", m5.matches()));
		System.out.println(String.format("%-17s%s: %b",phoneNumber7," matches:", m6.matches()));
		System.out.println(String.format("%-17s%s: %b",phoneNumber8," matches:", m7.matches()));
	}
}