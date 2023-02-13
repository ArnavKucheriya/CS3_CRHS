package Rec1;

public class Permutation
{
	private String orig;
	private String list;

	public Permutation(String word)
	{
		orig=word;
		list="";
	}

	public void permutation()
	{
		System.out.println("\nPERMUTATION OF WORD :: "+orig);
		permutation(orig,"");
	}
	public void set(String yes){
		orig = yes;
	}
	private void permutation(String orig, String sent)
	{
		if (sent.length() == orig.length()) System.out.println(sent);
		else{
			for (int i=0;i<orig.length();i++){
				if (sent.indexOf(orig.charAt(i)) <0)
					permutation(orig,sent+ Character.toString(orig.charAt(i)));
			}
		}
	}


	public String toString()
	{
		return list;
	}
}

















