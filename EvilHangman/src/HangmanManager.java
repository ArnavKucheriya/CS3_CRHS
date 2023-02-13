//Arnav Kucheriya


import java.util.*;

public class HangmanManager {
	private Set<String> wordList;
	private SortedSet<Character> guesses;
	private int Gleft;


	public HangmanManager(List<String> dictionary, int length, int max) {
		if (length < 1 || max < 0)
			throw new IllegalArgumentException();

		Gleft = max;
		guesses = new TreeSet<Character>();
		wordList = new TreeSet<String>();

		for (String word : dictionary) {
			if (word.length() == length)
				wordList.add(word);
		}
	}


	public Set<String> words() {
		return wordList;
	}

	public int guessesLeft() {
		return Gleft;
	}


	public SortedSet<Character> guesses() {
		return guesses;
	}

	public String pattern() {
		if (wordList.isEmpty())
			throw new IllegalStateException();
		return pattern(wordList.iterator().next());
	}


	public int record(char guess) {
		if (wordList.isEmpty() || Gleft == 0)
			throw new IllegalStateException();
		else if (!wordList.isEmpty() && guesses.contains(guess))
			throw new IllegalArgumentException();

		Map<String, Set<String>> fam = new TreeMap<String, Set<String>>();
		String initialPattern = this.pattern();
		guesses.add(guess);
		updateWordList(fam);

		if (this.pattern().equals(initialPattern))
			Gleft--;
		return countMatches(this.pattern(), guess);
	}


	private int countMatches (String pattern, char guess) {
		int matches = 0;
		for (int i = 0; i < pattern.length(); i++) {
			if (pattern.charAt(i) == guess)
				matches++;
		}
		return matches;
	}


	private void updateWordList(Map<String, Set<String>> fam) {
		for (String word : wordList) {
			String currentPattern = pattern(word);
			if (!fam.containsKey(currentPattern))
				fam.put(currentPattern, new TreeSet<String>());
			fam.get(currentPattern).add(word);
		}
		wordList = fam.get(getLargestKey(fam));
	}


	private String getLargestKey(Map<String, Set<String>> fam) {
		int maxLength = 0;
		String maxKey = "";
		for (String key : fam.keySet()) {
			if (fam.get(key).size() > maxLength) {
				maxLength = fam.get(key).size();
				maxKey = key;
			}
		}
		return maxKey;
	}

	private String pattern(String word) {
		String builder = "";
		for (int i = 0; i < word.length(); i++) {
			if (guesses.contains(word.charAt(i)))
				builder += word.substring(i, i + 1);
			else
				builder += "-";
		}
		return builder;
	}
}