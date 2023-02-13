
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

public class AssassinManagerTest {

    final String INPUT_FILENAME = "names.txt";
    final boolean RANDOM = false;
    final int SEED = 42;
    AssassinManager instance;

    public void load() {
        File inputFile = new File(INPUT_FILENAME);
        Scanner input = null;
        try {
            input = new Scanner(inputFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AssassinManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Set<String> names = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        while (input.hasNextLine()) {
            String name = input.nextLine().trim().intern();
            if (name.length() > 0) {
                names.add(name);
            }
        }
        input.close();
        // transfer to an ArrayList, shuffle and build an AssassinManager
        ArrayList<String> nameList = new ArrayList<>(names);
        Random rand = (RANDOM) ? new Random() : new Random(SEED);
        Collections.shuffle(nameList, rand);

        instance = new AssassinManager(nameList);
    }

    /**
     * Test of printKillRing method, of class AssassinManager.
     */
    @Test
    public void testPrintKillRing() {
        System.out.println("printKillRing");
        load();
        String inst = instance.killRing();
        assertEquals(inst, "  Linus Torvalds is stalking Bill Gates\n"
                + "  Bill Gates is stalking Don Knuth\n"
                + "  Don Knuth is stalking Alan Turing\n"
                + "  Alan Turing is stalking Tim Berners-Lee\n"
                + "  Tim Berners-Lee is stalking Alan Kay\n"
                + "  Alan Kay is stalking Charles Babbage\n"
                + "  Charles Babbage is stalking John von Neumann\n"
                + "  John von Neumann is stalking Ada Lovelace\n"
                + "  Ada Lovelace is stalking Alonzo Church\n"
                + "  Alonzo Church is stalking Grace Hopper\n"
                + "  Grace Hopper is stalking Linus Torvalds\n");
    }

    @Test
    public void testPrintKillRing2() {
        System.out.println("printKillRing2");
        load();
        instance.kill("Bill Gates");
        String inst = instance.killRing();
        assertEquals(inst, "  Linus Torvalds is stalking Don Knuth\n"
                + "  Don Knuth is stalking Alan Turing\n"
                + "  Alan Turing is stalking Tim Berners-Lee\n"
                + "  Tim Berners-Lee is stalking Alan Kay\n"
                + "  Alan Kay is stalking Charles Babbage\n"
                + "  Charles Babbage is stalking John von Neumann\n"
                + "  John von Neumann is stalking Ada Lovelace\n"
                + "  Ada Lovelace is stalking Alonzo Church\n"
                + "  Alonzo Church is stalking Grace Hopper\n"
                + "  Grace Hopper is stalking Linus Torvalds\n");
    }

    @Test
    public void testPrintKillRing3() {
        System.out.println("printKillRing3");
        load();
        instance.kill("Bill Gates");
        instance.kill("linus torvalds");
        instance.kill("grace hopper");
        String inst = instance.killRing();
        assertEquals(inst,
                "  Don Knuth is stalking Alan Turing\n"
                + "  Alan Turing is stalking Tim Berners-Lee\n"
                + "  Tim Berners-Lee is stalking Alan Kay\n"
                + "  Alan Kay is stalking Charles Babbage\n"
                + "  Charles Babbage is stalking John von Neumann\n"
                + "  John von Neumann is stalking Ada Lovelace\n"
                + "  Ada Lovelace is stalking Alonzo Church\n"
                + "  Alonzo Church is stalking Don Knuth\n");
    }

    
    
    /**
     * Test of printGraveyard method, of class AssassinManager.
     */
    @Test
    public void testPrintKillRing4() {
        System.out.println("printGraveyard2");
        load();
        instance.kill("Bill Gates");
        instance.kill("Alan Turing");
        instance.kill("Linus Torvalds");
        instance.kill("Alan Kay");
        instance.kill("Grace Hopper");
        instance.kill("Ada Lovelace");
        instance.kill("Alonzo Church");
        instance.kill("john von neumann");
        instance.kill("don knuth");
        instance.kill("Tim Berners-Lee");
        String inst = instance.killRing();
        assertEquals(inst.trim(), "Charles Babbage won the game!".trim());
    }
    
    /**
     * Test of printGraveyard method, of class AssassinManager.
     */
    @Test
    public void testPrintGraveyard() {
        System.out.println("printGraveyard");
        load();
        instance.kill("Bill Gates");
        instance.kill("Alan Turing");
        instance.kill("Linus Torvalds");
        String inst = instance.graveyard();
        assertEquals(inst, "  Linus Torvalds was killed by Grace Hopper\n"
                + "  Alan Turing was killed by Don Knuth\n"
                + "  Bill Gates was killed by Linus Torvalds\n");
    }

    /**
     * Test of printGraveyard method, of class AssassinManager.
     */
    @Test
    public void testPrintGraveyard2() {
        System.out.println("printGraveyard2");
        load();
        instance.kill("Bill Gates");
        instance.kill("Alan Turing");
        instance.kill("Linus Torvalds");
        instance.kill("Alan Kay");
        instance.kill("Grace Hopper");
        instance.kill("Ada Lovelace");
        instance.kill("Alonzo Church");
        instance.kill("john von neumann");
        instance.kill("don knuth");
        instance.kill("Tim Berners-Lee");
        String inst = instance.graveyard();
        assertEquals(inst, "  Tim Berners-Lee was killed by Charles Babbage\n"
                + "  Don Knuth was killed by Charles Babbage\n"
                + "  John von Neumann was killed by Charles Babbage\n"
                + "  Alonzo Church was killed by John von Neumann\n"
                + "  Ada Lovelace was killed by John von Neumann\n"
                + "  Grace Hopper was killed by Alonzo Church\n"
                + "  Alan Kay was killed by Tim Berners-Lee\n"
                + "  Linus Torvalds was killed by Grace Hopper\n"
                + "  Alan Turing was killed by Don Knuth\n"
                + "  Bill Gates was killed by Linus Torvalds\n");
    }

    /**
     * Test of printGraveyard method, of class AssassinManager.
     */
    @Test
    public void testPrintGraveyard3() {
        System.out.println("printGraveyard2");
        load();
        instance.kill("Linus Torvalds");
        instance.kill("Bill Gates");
        instance.kill("don knuth");
        instance.kill("Alonzo Church");
        instance.kill("Grace Hopper");
        instance.kill("john von neumann");
        instance.kill("Alan Turing");
        instance.kill("Alan Kay");
        instance.kill("Ada Lovelace");
        instance.kill("Tim Berners-Lee");
        String inst = instance.graveyard();
        assertEquals(inst, "  Tim Berners-Lee was killed by Charles Babbage\n"
                + "  Ada Lovelace was killed by Charles Babbage\n"
                + "  Alan Kay was killed by Tim Berners-Lee\n"
                + "  Alan Turing was killed by Ada Lovelace\n"
                + "  John von Neumann was killed by Charles Babbage\n"
                + "  Grace Hopper was killed by Ada Lovelace\n"
                + "  Alonzo Church was killed by Ada Lovelace\n"
                + "  Don Knuth was killed by Grace Hopper\n"
                + "  Bill Gates was killed by Grace Hopper\n"
                + "  Linus Torvalds was killed by Grace Hopper\n");
    }

    /**
     * Test of killRingContains method, of class AssassinManager.
     */
    @Test
    public void testKillRingContains() {
        System.out.println("killRingContains");
        String name = "h";
        AssassinManager instance2 = new AssassinManager(new ArrayList<>(Arrays.asList(new String[]{"a", "b", "c", "d", "e"})));
        boolean expResult = false;
        boolean result = instance2.killRingContains(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of killRingContains method, of class AssassinManager.
     */
    @Test
    public void testKillRingContains2() {
        System.out.println("killRingContains2");
        String name = "e";
        AssassinManager instance2 = new AssassinManager(new ArrayList<>(Arrays.asList(new String[]{"a", "b", "c", "d", "e"})));
        boolean expResult = true;
        boolean result = instance2.killRingContains(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of graveyardContains method, of class AssassinManager.
     */
    @Test
    public void testGraveyardContains() {
        System.out.println("graveyardContains");
        String name = "c";
        AssassinManager instance2 = new AssassinManager(new ArrayList<>(Arrays.asList(new String[]{"a", "b", "c", "d", "e"})));
        instance2.kill("d");
        instance2.kill("b");
        instance2.kill("a");
        boolean expResult = false;
        boolean result = instance2.graveyardContains(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of graveyardContains method, of class AssassinManager.
     */
    @Test
    public void testGraveyardContains2() {
        System.out.println("graveyardContains2");
        String name = "c";
        AssassinManager instance2 = new AssassinManager(new ArrayList<>(Arrays.asList(new String[]{"a", "b", "c", "d", "e"})));
        instance2.kill("c");
        instance2.kill("b");
        instance2.kill("a");
        instance2.kill("d");
        boolean expResult = true;
        boolean result = instance2.graveyardContains(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of isGameOver method, of class AssassinManager.
     */
    @Test
    public void testIsGameOver() {
        System.out.println("isGameOver");
        AssassinManager instance2 = new AssassinManager(new ArrayList<>(Arrays.asList(new String[]{"a", "b", "c", "d", "e", "f", "g"})));
        boolean expResult = false;
        boolean result = instance2.isGameOver();
        assertEquals(expResult, result);
    }

    /**
     * Test of isGameOver method, of class AssassinManager.
     */
    @Test
    public void testIsGameOver2() {
        System.out.println("isGameOver2");
        AssassinManager instance2 = new AssassinManager(new ArrayList<>(Arrays.asList(new String[]{"a", "b", "c", "d", "e", "f", "g"})));
        instance2.kill("g");
        instance2.kill("f");
        instance2.kill("a");
        instance2.kill("b");
        instance2.kill("d");
        boolean expResult = false;
        boolean result = instance2.isGameOver();
        assertEquals(expResult, result);
    }

    /**
     * Test of isGameOver method, of class AssassinManager.
     */
    @Test
    public void testIsGameOver3() {
        System.out.println("isGameOver3");
        AssassinManager instance2 = new AssassinManager(new ArrayList<>(Arrays.asList(new String[]{"a", "b", "c", "d", "e", "f", "g"})));
        instance2.kill("g");
        instance2.kill("f");
        instance2.kill("a");
        instance2.kill("b");
        instance2.kill("c");
        instance2.kill("d");
        boolean expResult = true;
        boolean result = instance2.isGameOver();
        assertEquals(expResult, result);
    }

    /**
     * Test of isGameOver method, of class AssassinManager.
     */
    @Test
    public void testIsGameOver4() {
        System.out.println("isGameOver4");
        AssassinManager instance2 = new AssassinManager(new ArrayList<>(Arrays.asList(new String[]{"a"})));
        boolean expResult = true;
        boolean result = instance2.isGameOver();
        assertEquals(expResult, result);
    }

    /**
     * Test of winner method, of class AssassinManager.
     */
    @Test
    public void testWinner() {
        System.out.println("winner");
        AssassinManager instance2 = new AssassinManager(new ArrayList<>(Arrays.asList(new String[]{"a"})));
        String expResult = "a";
        String result = instance2.winner();
        assertEquals(expResult, result);
    }

    /**
     * Test of winner method, of class AssassinManager.
     */
    @Test
    public void testWinner2() {
        System.out.println("winner2");
        AssassinManager instance2 = new AssassinManager(new ArrayList<>(Arrays.asList(new String[]{"a", "b", "c", "d", "e", "f", "g"})));
        String expResult = null;
        String result = instance2.winner();
        assertEquals(expResult, result);
    }

    /**
     * Test of Constructor, of class AssassinManager.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorExceptions() {
        new AssassinManager(null);
    }

    /**
     * Test of Constructor, of class AssassinManager.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorExceptions2() {
        new AssassinManager(new ArrayList<>(Arrays.asList(new String[]{})));
    }

    /**
     * Test of kill, of class AssassinManager.
     */
    @Test
    public void testKillException() {
        AssassinManager instance2 = new AssassinManager(new ArrayList<>(Arrays.asList(new String[]{"a", "b", "c", "d", "e", "f", "g"})));
        try {
            instance2.kill("z");
            fail("My method didn't throw when I expected it to");
        } catch (IllegalArgumentException expectedException) {
        }

    }

    /**
     * Test of kill, of class AssassinManager.
     */
    @Test
    public void testKillException2() {
        AssassinManager instance2 = new AssassinManager(new ArrayList<>(Arrays.asList(new String[]{"a", "b", "c", "d", "e", "f", "g"})));
        instance2.kill("g");
        instance2.kill("f");
        instance2.kill("e");
        instance2.kill("d");
        instance2.kill("c");
        instance2.kill("b");
        try {
            instance2.kill("a");
            fail("My method didn't throw when I expected it to");
        } catch (IllegalStateException expectedException) {
        }
    }

    /**
     * Test of kill, of class AssassinManager.
     */
    @Test
    public void testKillException3() {
        AssassinManager instance2 = new AssassinManager(new ArrayList<>(Arrays.asList(new String[]{"a", "b", "c", "d", "e", "f", "g"})));
        instance2.kill("g");
        instance2.kill("f");
        instance2.kill("e");
        instance2.kill("d");
        instance2.kill("c");
        instance2.kill("b");
        try {
            instance2.kill("z");
            fail("My method didn't throw when I expected it to");
        } catch (IllegalStateException expectedException) {
        }
    }

    /**
     * Test of kill method, of class AssassinManager.
     */
    //@Test
    public void testKill() {
        System.out.println("kill");
        ArrayList<String> list = new ArrayList<>();
        for (int i = 65; i < 3000000; i++) {
            list.add("" + i);
        }
        AssassinManager instance2 = new AssassinManager(list);
        list.remove(0);
        Collections.shuffle(list);
        list.forEach((s) -> {
            instance2.kill(s);
        });
    }

}
