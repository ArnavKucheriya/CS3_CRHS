import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Texting {
    public Texting() throws IOException {
    	run();
    }

	public void run() throws IOException{

		Scanner file = new Scanner(new File("text.in"));
		int size = file.nextInt();
		String s ="";
		String str="";
		int i = 0;

		while ((str = file.nextLine())!=null){

		}






			file = new Scanner(new File("text.out"));
		String temp = "";
		while(file.hasNextLine()){
			temp += file.nextLine()+"\n";
		}
		System.out.println( temp.equals(s)?"\nIt matches!":"\nkeep trying");
	}

    public static void main(String[] args)throws IOException {
        new Texting();
    }
}

