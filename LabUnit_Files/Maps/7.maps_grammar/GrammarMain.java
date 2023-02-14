// CSE 143, Homework 4 (GrammarSolver Solver)
// Instructor-provided client program that prompts a user for the name of a
// grammar file and then gives the user the opportunity to generate random
// versions of various elements of the grammar.

import java.io.*;     // for File, FileNotFoundException
import java.util.*;   // for Scanner, List, Set, Collections

public class GrammarMain {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to the CSE 143 Random Sentence Generator!");
        System.out.println();

        Scanner console = new Scanner(System.in);

        // Open Grammar File
        List<String> bnf;
        do {
            System.out.print("What is the name of the grammar file? ");
            bnf = readLines(console.nextLine());
        
            if (bnf == null) {
                System.out.println("That's not a valid file!");
            }
        } while (bnf == null);

        // Construct the grammar and begin user input loop
        Grammar grammar = new Grammar(bnf);

        // Repeatedly prompt for symbols to generate, and generate them
        String symbol = getSymbol(console, grammar);
        while (symbol == null || symbol.length() > 0) {
            if (grammar.isNonTerminal(symbol)) {
                generateNonTerminal(console, grammar, symbol);
            } else if (symbol.length() > 0) {
                System.out.println("Illegal Non-Terminal.");
            }
            symbol = getSymbol(console, grammar);
        }
    }

    // Displays all non-terminal symbols, prompts for a symbol to generate 
    // and returns the symbol as a string.
    public static String getSymbol(Scanner console, Grammar grammar) {
        System.out.println();
        System.out.println("Available non-terminals are:\n" + grammar);
        System.out.print("Which non-terminal do you want to generate " +
                         "(Enter to quit)? ");
        return console.nextLine().trim();
    }


    // Prompts user for a number of phrases to generate from the given symbol,
    // generates that many using the provided Grammar and displays them to the 
    // console.
    public static void generateNonTerminal(
            Scanner console, Grammar grammar, String nonTerminal) {
        System.out.print("How many do you want me to generate? ");
        if (console.hasNextInt()) {
            int number = console.nextInt();
            if (number <= 0) {
                System.out.println("You must provide a number >= 1.");
            } 
            else {
                System.out.println();
                List<String> result = grammar.getRandom(number, nonTerminal);
                for (String sentence : result) {
                    System.out.println(sentence);
                }
            }
        } 
        else {
            System.out.println("That isn't a valid integer!");
        }
        console.nextLine();   // to position to next line
    }

    // Reads text from the file with the given name and returns as a List.
    // Strips empty lines and trims leading/trailing whitespace from each line.
    // post: returns null if no such filename exists 
    public static List<String> readLines(String fileName)  {
        List<String> lines = new ArrayList<String>();
        try {
            Scanner input = new Scanner(new File(fileName));
            while (input.hasNextLine()) {
                String line = input.nextLine().trim();
                if (line.length() > 0) {
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException ex) {
            return null;
        }
        return lines;
    }
}
