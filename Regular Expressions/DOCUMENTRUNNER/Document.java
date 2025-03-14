package Labs.ReGex.DOCUMENTRUNNER;
//Name -
//Date -
//Class -
//Lab  -

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Document
{
	private String text;

	//@param text The full text of the Document.
	public Document(String text)
	{
		this.text = text;
	}

	/** Returns the tokens that match the regex pattern from the document
	 * text string.
	 * @param pattern A regular expression string specifying the
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex
	 *   pattern
	 */
	protected List<String> getTokens(String pattern)
	{
		ArrayList<String> tokens = new ArrayList<String>();

		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);

		while (m.find()) {
			tokens.add(m.group());
		}

		return tokens;
	}

	// This is a helper function that returns the number of syllables
	// in a word.  You should write this and use it.

	public int countSyllables(String word)
	{
		// TODO: Implement this method

		int numSyllables = 0;
		boolean newSyllable = true;
		String vowels = "aeiouy";
		char[] cArray = word.toCharArray();

		for (int i = 0; i < cArray.length; i++) {

			// dealing with lone 'e's and 'e's in the end of the word.
			if (i == cArray.length-1 && Character.toLowerCase(cArray[i]) == 'e' &&
					newSyllable && numSyllables > 0) {
				numSyllables--;
			}
			// if the syllable's character is a vowel. Then it stops and count as a syllable.
			if (newSyllable && vowels.indexOf(Character.toLowerCase(cArray[i])) >= 0) {
				newSyllable = false;
				numSyllables++;
			}
			// if the current character is NOT a vowel, starts the new syllable.
			else if (vowels.indexOf(Character.toLowerCase(cArray[i])) < 0) {
				newSyllable = true;
			}
		}
		return numSyllables;



	}

	/**
	 * Get the number of words in the document.
	 * "Words" are defined as contiguous strings of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z
	 *
	 * @return The number of words in the document.
	 */

	public int getNumWords()
	{
		ArrayList<String> tokens = (ArrayList<String>) getTokens("[A-Za-z]+");



		return tokens.size();
	}

	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of
	 * characters in the document, even if they don't end with a punctuation mark.
	 *
	 * @return The number of sentences in the document.
	 */

	public int getNumSentences()
	{
		ArrayList<String> tokens = (ArrayList<String>) getTokens("[.!?]+|[^.?!]$");

	    //TODO: Implement this method.


    	return tokens.size();
	}

	/**
	 * Get the number of sentences in the document.
	 * Words are defined as above.  Syllables are defined as:
	 * a contiguous sequence of vowels, except for an "e" at the
	 * end of a word if the word has another set of contiguous vowels,
	 * makes up one syllable.   y is considered a vowel.
	 * @return The number of syllables in the document.
	 */

	public int getNumSyllables()
	{
	    //TODO: Implement this method.
		List<String> tokens = getTokens("[a-zA-Z]+");
		int totalSyllables = 0;
		for (String word : tokens) {
			totalSyllables += countSyllables(word);
		}
		return totalSyllables;
	}

	public String getText(){
		return text;
	}

	/** A method for testing
	 *
	 * @param doc The Document object to test
	 * @param syllables The expected number of syllables
	 * @param words The expected number of words
	 * @param sentences The expected number of sentences
	 * @return true if the test case passed.  False otherwise.
	 */
	public static boolean testCase(Document doc, int syllables, int words, int sentences)
	{
		System.out.println("Testing text: ");
		System.out.print(doc.getText() + "\n....");
		boolean passed = true;
		int syllFound = doc.getNumSyllables();
		int wordsFound = doc.getNumWords();
		int sentFound = doc.getNumSentences();
		if (syllFound != syllables) {
			System.out.println("\nIncorrect number of syllables.  Found " + syllFound
					+ ", expected " + syllables);
			passed = false;
		}
		if (wordsFound != words) {
			System.out.println("\nIncorrect number of words.  Found " + wordsFound
					+ ", expected " + words);
			passed = false;
		}
		if (sentFound != sentences) {
			System.out.println("\nIncorrect number of sentences.  Found " + sentFound
					+ ", expected " + sentences);
			passed = false;
		}

		if (passed) {
			System.out.println("passed.\n");
		}
		else {
			System.out.println("FAILED.\n");
		}
		return passed;
	}


	/* The main method for testing this class.
	 * You are encouraged to add your own tests.  */
	public static void main(String[] args)
	{
		testCase(new Document("This is a test.  How many???  "
		        + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
				16, 13, 5);
		testCase(new Document(""), 0, 0, 0);
		testCase(new Document("sentence, with, lots, of, commas.!  "
		        + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
		testCase(new Document("many???  Senteeeeeeeeeences are"), 6, 3, 2);
	}

}
