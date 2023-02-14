

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class FileInput {
	private static Scanner sInput;
	private static Scanner bInput;
	private static Set<String> commonWords;

	public static void init(){
		File shakespeare = new File("hamlet.txt");
		File bacon = new File("bacon-essays.txt");
		try {
			sInput = new Scanner(shakespeare);
			bInput = new Scanner(bacon);
		} catch (FileNotFoundException e) {
			System.err.println("Make sure hamlet.txt and the-new-atlantis.txt are in their appropriate place");
		}
		
		commonWords = new HashSet<String>();
		String[] words = {"the", "be", "to", "of", "and", "a", "in", "that", "have", "I", "it", "for", "not", "on", 
				"with", "he", "as", "you", "do", "at", "this", "but", "his", "by", "from", "they", "we", "say", 
				"her", "she", "or", "an", "will", "my", "one", "all", "would", "there", "their", "what", "so", 
				"up", "out", "if", "about", "who", "get", "which", "go", "me", "i", "is"};
		addMul(commonWords, words);
		
	}
	
	private static void addMul(Set<String> commonWords, String[] words) {
		for(int i = 0; i < words.length; i++) {
			commonWords.add(words[i]);
		}
	}
	
	private static boolean isCommonWord(String currWord) {
		return commonWords.contains(currWord);
	}
	
	public static String[] readShakespeare(){
		String[] shakes = new String[10000000];
		int i;
		for(i=0;sInput.hasNext();i++){		
			String toInput = sInput.next().replaceAll("[^a-zA-Z ]", "").toLowerCase();
			if (toInput.compareTo("")==0 || isCommonWord(toInput)){
				i--;
				continue;
			} else shakes[i] = toInput;
		}
		String[] s = new String[i];
		i--;
		while(i>=0){
			s[i]=shakes[i];
			i--;
		}
		return s;
	}
	public static String[] readBacon(){
		String[] bac = new String[10000000];
		int i;
		for(i=0;bInput.hasNext();i++){
			String toInput = bInput.next().replaceAll("[^a-zA-Z ]", "").toLowerCase();
			if (toInput.compareTo("")==0 || isCommonWord(toInput)){
				i--;
				continue;
			} else bac[i] = toInput;
		}
		String[] b = new String[i];
		i--;
		while(i>=0){
			b[i]=bac[i];
			i--;
		}
		return b;
	}	
	
	
}
