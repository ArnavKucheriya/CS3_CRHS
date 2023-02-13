
import java.io.*;     // for File, FileNotFoundException
import java.util.*;   // for Scanner, List, Set, Collections

public class GrammarMain {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to the CS random sentence generator!");
        System.out.println();

        // open grammar file
        System.out.print("What is the name of the grammar file? ");
        Scanner console = new Scanner(System.in);
        String fileName = console.nextLine();
        List<String> lines = readLines(fileName);

        // construct grammar solver and begin user input loop
        GrammarSolver solver = new GrammarSolver(Collections.unmodifiableList(lines));

        // repeatedly prompt for symbols to generate, and generate them
        String symbol = getSymbol(console, solver);
        while (symbol.length() > 0) {
            if (solver.contains(symbol)) {
                doGenerate(console, solver, symbol);
            } else {
                System.out.println("Illegal symbol.");
            }

            symbol = getSymbol(console, solver);
        }
    }

    // Displays all non-terminal symbols, prompts for a symbol to generate
    // and returns the symbol as a string.
    public static String getSymbol(Scanner console, GrammarSolver solver) {
        System.out.println();
        System.out.println("Available symbols to generate are:");
        Set<String> symbols = solver.getSymbols();
        System.out.println(symbols);

        System.out.print("What do you want to generate (Enter to quit)? ");
        String target = console.nextLine().trim();
        return target;
    }

    // Prompts user for a number of phrases to generate from the given symbol,
    // generates that many using the provided GrammarSolver and displays them to the
    // console.
    public static void doGenerate(Scanner console, GrammarSolver solver, String symbol) {
        System.out.print("How many do you want me to generate? ");
        if (console.hasNextInt()) {
            int number = console.nextInt();
            if (number < 0) {
                System.out.println("No negatives allowed.");
            } else {
                System.out.println();
                for (int i = 0; i < number; i++) {
                    String result = solver.generate(symbol);
                    System.out.println(result);
                }
            }
        } else {
            System.out.println("That is not a valid integer.");
        }
        console.nextLine();   // to position to next line
    }

    // Reads text from the file with the given name and returns as a List.
    // Strips empty lines and trims leading/trailing whitespace from each line.
    // pre: a file with the given name exists, throws FileNotFoundException otherwise
    public static List<String> readLines(String fileName) throws FileNotFoundException {
        List<String> lines = new ArrayList<String>();
        Scanner input = new Scanner(new File(fileName));
        while (input.hasNextLine()) {
            String line = input.nextLine().trim();
            if (line.length() > 0) {
                lines.add(line);
            }
        }
        return lines;
    }
}