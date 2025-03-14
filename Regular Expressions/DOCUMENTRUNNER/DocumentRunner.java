package Labs.ReGex.DOCUMENTRUNNER;


import java.util.*;
import java.io.*;
public class DocumentRunner {
    public static void main(String[] args) {

       try
        {
            System.out.println("Sentences, words, and syllables:");
            Scanner file = new Scanner(new File("test_cases.txt"));
            while (file.hasNextLine())
            {

                Document doc = new Document(file.nextLine());
                String result = doc.getNumSentences() + " " + doc.getNumWords() + " " + doc.getNumSyllables() + " ";
                System.out.println(result);
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
