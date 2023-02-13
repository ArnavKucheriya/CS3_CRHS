
import java.io.*;
import java.util.*;

public class AssassinMain {
    public static void main(String[] args) throws FileNotFoundException {
        // prompt for file name
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the CSE143 Assassin Manager");
        System.out.println();
        System.out.print("What name file do you want to use this time? ");
        String fileName = console.nextLine();

        // read names into a list, using a Set to avoid duplicates
        Scanner input = new Scanner(new File(fileName));
        Set<String> names = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        List<String> names2 = new ArrayList<String>();
        while (input.hasNextLine()) {
            String name = input.nextLine().trim();
            if (name.length() > 0 && !names.contains(name)) {
                names.add(name);
                names2.add(name);
            }
        }

        // shuffle if desired
        if (yesTo(console, "Do you want the names shuffled?")) {
            Collections.shuffle(names2);
        }
        // make an immutable version and use it to build an AssassinManager
        List<String> names3 = Collections.unmodifiableList(names2);
        AssassinManager manager = new AssassinManager(names3);
        System.out.println();

        // prompt the user for victims until the game is over
        while (!manager.isGameOver()) {
            oneKill(console, manager);
        }

        // report who won
        System.out.println("Game was won by " + manager.winner());
        System.out.println("Final graveyard is as follows:");
        manager.graveyard();
    }


    public static void oneKill(Scanner console, AssassinManager manager) {
        System.out.println("Current kill ring:");
        System.out.println(manager.killRing());
        System.out.println("Current graveyard:");
        System.out.println(manager.graveyard());
        System.out.println();
        System.out.print("next victim? ");
        String name = console.nextLine().trim();
        if (manager.graveyardContains(name)) {
            System.out.println(name + " is already dead.");
        } else if (!manager.killRingContains(name)) {
            System.out.println("Unknown person.");
        } else {
            manager.kill(name);
        }
        System.out.println();
    }


    public static boolean yesTo(Scanner console, String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }
}