import java.util.*;

public class AssassinManager {
    private AssassinNode frontKillRing; // Linked list holds the people in the kill ring
    private AssassinNode currentAssassin; // Linked list holds the current people
    private AssassinNode prevAssassin; // Linked List that holds the previous people
    private AssassinNode frontGraveyard; // Linked list holds the people who are dead

    public AssassinManager(List<String> names) {
        if (names == null ) {
            throw new IllegalArgumentException();
        }
        if (names.isEmpty()){
            throw new IllegalArgumentException();
        }
        for (int i = names.size() - 1; i >= 0; i--) {
            frontKillRing = new AssassinNode(names.get(i), frontKillRing);
        }

    }


    public String killRing() {
        currentAssassin = frontKillRing;
        String str="";
        while (currentAssassin.next != null) {
            str+=("  " + currentAssassin.name + " is stalking "
                    + currentAssassin.next.name)+"\n";
            currentAssassin = currentAssassin.next;
        }
        str+=("  " + currentAssassin.name + " is stalking " + frontKillRing.name+ "\n");
        if (currentAssassin.name.equals(frontKillRing.name)){
            str=currentAssassin.name+" won the game!";
        }
        return str;
    }


    public String graveyard() {
        currentAssassin = frontGraveyard;
        String str="";
        while (currentAssassin != null) {
            str+=("  " + currentAssassin.name + " was killed by "
                    + currentAssassin.killer)+"\n";
            currentAssassin = currentAssassin.next;
        }
        return str;
    }

    public boolean killRingContains(String name) {
        return doesListContains(name, frontKillRing);
    }


    public boolean graveyardContains(String name) {
        return doesListContains(name, frontGraveyard);
    }
    private boolean doesListContains(String name, AssassinNode currentAssassin) {
        boolean doesNotContain = false;
        while (currentAssassin != null) {
            if (currentAssassin.name.equalsIgnoreCase(name)) {
                return !doesNotContain;
            }
            currentAssassin = currentAssassin.next;
        }
        return doesNotContain;
    }


    public boolean isGameOver() {
        return frontKillRing.next == null;
    }


    public String winner() {
        if (!isGameOver()) {
            return null;
        }
        return frontKillRing.name;
    }


    public void kill(String name) {
        currentAssassin = frontKillRing;
        prevAssassin = frontGraveyard;
         if (isGameOver()) {
            throw new IllegalStateException();
        }
         else if (!killRingContains(name)) {
             throw new IllegalArgumentException();
         }
        if (currentAssassin.name.equalsIgnoreCase(name)) {
            prevAssassin = currentAssassin;
            while (currentAssassin.next != null) {
                currentAssassin = currentAssassin.next;
            }
            frontKillRing = frontKillRing.next;
        } else {
            while (!currentAssassin.next.name.equalsIgnoreCase(name)) {
                currentAssassin = currentAssassin.next;
            }
            prevAssassin = currentAssassin.next;
            currentAssassin.next = currentAssassin.next.next;
        }
        prevAssassin.killer = currentAssassin.name;
        prevAssassin.next = frontGraveyard;
        frontGraveyard = prevAssassin;
    }
}