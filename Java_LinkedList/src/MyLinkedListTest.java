
import java.util.LinkedList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MyLinkedListTest {

    private static final int LONG_LIST_LENGTH = 10;

    MyLinkedList<String> shortList;
    MyLinkedList<Integer> emptyList;
    MyLinkedList<Integer> longerList;
    MyLinkedList<Integer> list1;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        // Feel free to use these lists, or add your own
        shortList = new MyLinkedList<>();
        shortList.add("A");
        shortList.add("B");
        emptyList = new MyLinkedList<>();
        longerList = new MyLinkedList<>();
        for (int i = 0; i < LONG_LIST_LENGTH; i++) {
            longerList.add(i);
        }
        list1 = new MyLinkedList<>();
        list1.add(65);
        list1.add(21);
        list1.add(42);

    }

    public String printListForwards(MyLinkedList<Integer> lst) {
        LLNode<Integer> curr;
        String ret = "";
        if (lst.head != null && lst.head.data == null) {
            curr = lst.head.next;
        } else {
            curr = lst.head;
        }

        while (curr != null && curr.data != null) {
            ret += curr.data;
            curr = curr.next;
        }
        return ret;
    }

    public String printListBackwards(MyLinkedList<Integer> lst) {
        LLNode<Integer> curr;
        String ret = "";
        if (lst.head != null && lst.tail.data == null) {
            curr = lst.tail.prev;
        } else {
            curr = lst.tail;
        }
        while (curr != null && curr.data != null) {
            ret += curr.data;
            curr = curr.prev;
        }
        return ret;
    }

    /**
     * Test if the get method is working correctly.
     */
    /*You should not need to add much to this method.
     * We provide it as an example of a thorough test. */
    @Test
    public void testGet() {
        //test empty list, get should throw an exception
        try {
            emptyList.get(0);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }
        // System.out.println("OK for exception");
        // test short list, first contents, then out of bounds
        assertEquals("Check first", "A", shortList.get(0));
        assertEquals("Check second", "B", shortList.get(1));

        try {
            shortList.get(-1);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            shortList.get(2);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }
        // test longer list contents
        for (int i = 0; i < LONG_LIST_LENGTH; i++) {
            assertEquals("Check " + i + " element", (Integer) i, longerList.get(i));
        }

        // test off the end of the longer array
        try {
            longerList.get(-1);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            longerList.get(LONG_LIST_LENGTH);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {
        }

    }

    /**
     * Test removing an element from the list. We've included the example from
     * the concept challenge. You will want to add more tests.
     */
    @Test
    public void testRemove() {
        try {
            shortList.remove(2);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            longerList.remove(-1);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }

        int a = list1.remove(0);
        assertEquals("Remove: check a is correct ", 65, a);
        assertEquals("Remove: check element 0 is correct ", (Integer) 21, list1.get(0));
        assertEquals("Remove: check size is correct ", 2, list1.size());

        for (int i = 0; i < LONG_LIST_LENGTH; i++) {
            int b = longerList.remove(0);
            assertEquals("Remove: check b is correct ", i, b);
            assertEquals("Remove: check size is correct ", LONG_LIST_LENGTH - i - 1, longerList.size());
        }
    }

    /**
     * Test adding an element into the end of the list, specifically public
     * boolean add(E element)
     *
     */
    @Test
    public void testAddEnd() {
        // TODO: implement this test
//        try {
//            shortList.add(null);
//            fail("Check invalid element");
//        } catch (NullPointerException e) {
//
//        }

        boolean state = emptyList.add(0);
        assertEquals("AddEnd: check state is correct ", true, state);
        assertEquals("AddEnd: check value is correct", (Integer) 0, emptyList.get(0));
        assertEquals("AddEnd: check size is correct", 1, emptyList.size());

        state = emptyList.add(1);
        assertEquals("AddEnd: check state is correct ", true, state);
        assertEquals("AddEnd: check value is correct", (Integer) 1, emptyList.get(1));
        assertEquals("AddEnd: check size is correct", 2, emptyList.size());
    }

    /**
     * Test adding an element into the end of the list, specifically public
     * boolean add(E element)
     */
    @Test
    public void testAddEnd2() {
        try {
            list1.add(-1, 33);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            list1.add(33, 33);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }

        list1.add(33);
        assertEquals((Integer) 33, list1.get(3));
        assertEquals((Integer) 65, list1.get(0));
        assertEquals((Integer) 21, list1.get(1));
        assertEquals((Integer) 42, list1.get(2));
        assertEquals(4, list1.size());

        shortList.add("C");
        assertEquals("A", shortList.get(0));
        assertEquals("B", shortList.get(1));
        assertEquals("C", shortList.get(2));
        assertEquals(3, shortList.size());

//        try {
//            shortList.add(0, null);
//            fail("Expected NullPointerException when adding a null element");
//        } catch (NullPointerException e) {
//        }
    }

    /**
     * Test the size of the list
     */
    @Test
    public void testSize() {
        // TODO: implement this test
        assertEquals("Size: empty list ", 0, emptyList.size());
        assertEquals("Size: short list ", 2, shortList.size());
        assertEquals("Size: longer list ", 10, longerList.size());
        assertEquals("Size: list1 ", 3, list1.size());
    }

    /**
     * Test adding an element into the list at a specified index, specifically:
     * public void add(int index, E element)
     *
     */
    @Test
    public void testAddAtIndex() {
        // TODO: implement this test
//        try {
//            shortList.add(0, null);
//            fail("Check invalid element");
//        } catch (NullPointerException e) {
//
//        }
        try {
            shortList.add(3, "C");
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            longerList.add(-1, 10);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }

        longerList.add(2, 10);
        assertEquals("AddAtIndex: check value is correct ", (Integer) 10, longerList.get(2));
        assertEquals("AddAtIndex: check size is correct", 11, longerList.size());

        shortList.add(1, "C");
        assertEquals("AddAtIndex: check value is correct ", (String) "C", shortList.get(1));
        assertEquals("AddAtIndex: check size is correct", 3, shortList.size());
    }

    /**
     * Test setting an element in the list
     */
    @Test
    public void testSet() {
        // TODO: implement this test
//        try {
//            shortList.set(0, null);
//            fail("Check invalid element");
//        } catch (NullPointerException e) {
//
//        }

        try {
            shortList.set(2, "C");
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            longerList.set(-1, 10);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }

        longerList.set(2, 10);

        assertEquals("AddAtIndex: check value is correct ", (Integer) 10, longerList.get(2));
        assertEquals("AddAtIndex: check size is correct", 10, longerList.size());

        shortList.set(1, "C");
        assertEquals("AddAtIndex: check value is correct ", (String) "C", shortList.get(1));
        assertEquals("AddAtIndex: check size is correct", 2, shortList.size());

    }

    @Test
    public void testAdd1000() {
        MyLinkedList<String> list = new MyLinkedList<>();
        LinkedList<String> ll = new LinkedList();
        for (int i = 0; i < 1000; i++) {
            int spot = (int) (Math.random() * i);
            //System.out.println(spot + " " + i + " " + list.size);
            list.add(spot, "" + i);
            ll.add(spot, "" + i);
        }
        // forwards
        for (int i = 0; i < ll.size(); i++) {
            assertEquals(list.get(i), ll.get(i));
        }
        // and backwards
        for (int i = ll.size() - 1; i >= 0; i--) {
            assertEquals(list.get(i), ll.get(i));
        }
        assertEquals(list.size, ll.size());
        while (!ll.isEmpty()) {
            int spot = (int) (Math.random() * ll.size());
            ll.remove(spot);
            list.remove(spot);
            assertTrue(list.toString().equals(ll.toString()));
        }

    }

    @Test
    public void everything() {
        MyLinkedList<String> list = new MyLinkedList<>();
        LinkedList<String> ll = new LinkedList();
        for (int i = 0; i < 1000; i++) {
            if (i < 500) {
                list.add(0, "" + i);
                ll.add(0, "" + i);
            } else {
                list.add(list.size(), "" + i);
                ll.add(ll.size(), "" + i);
            }
        }
        assertTrue(list.toString().equals(ll.toString()));
        for (int i = 0; i < 1000; i += 50) {
            list.set(i, list.get(i) + (i * 3));
            ll.set(i, ll.get(i) + (i * 3));
        }
        assertTrue(list.toString().equals(ll.toString()));
        for (int i = 0; i < 1000; i += 9) {
            list.add(i, list.get(i) + (i * 2));
            ll.add(i, ll.get(i) + (i * 2));
        }
        assertTrue(list.toString().equals(ll.toString()));
        // forwards
        for (int i = 0; i < ll.size(); i++) {
            assertEquals(list.get(i), ll.get(i));
        }
        assertTrue(list.toString().equals(ll.toString()));
        // and backwards
        for (int i = ll.size() - 1; i >= 0; i--) {
            assertEquals(list.get(i), ll.get(i));
        }
        assertTrue(list.toString().equals(ll.toString()));
        assertEquals(list.size, ll.size());
        for (int i = 1; i < ll.size(); i += 67) {
            ll.remove(i);
            list.remove(i);
        }
        assertTrue(list.toString().equals(ll.toString()));
        for (int i = 0; i < ll.size(); i += 44) {
            assertTrue(ll.contains("" + i) == list.has("" + i));
        }
    }

    @Test
    public void forward() {
        MyLinkedList<Integer> lst = new MyLinkedList<>();
        int nums[] = {1, 2, 3, 4, 5};
        System.out.println("** Test #1: Adding to end of list...");
        for (int i : nums) {
            lst.add(i);
        }
        assertEquals(printListForwards(lst), "12345");
    }

    @Test
    public void backwards() {
        MyLinkedList<Integer> lst = new MyLinkedList<>();
        int nums[] = {1, 2, 3, 4, 5};
        System.out.println("** Test #2: Adding to end of list...");
        for (int i : nums) {
            lst.add(i);
        }
        assertEquals(printListBackwards(lst), "54321");
    }

    @Test
    public void middle() {
        MyLinkedList<Integer> lst = new MyLinkedList<>();
        int nums[] = {1, 2, 3, 4, 5};
        System.out.println("** Test #3: Adding to the middle of list...");
        for (int i : nums) {
            lst.add(i);
        }
        lst.add(2, 6);
        assertEquals(printListForwards(lst), "126345");
    }

    @Test
    public void middleGet() {
        MyLinkedList<Integer> lst = new MyLinkedList<>();
        int nums[] = {1, 2, 3, 4, 5};
        System.out.println("** Test #4: getting from the middle of list...");
        for (int i : nums) {
            lst.add(i);
        }
        assertEquals(lst.get(3), (Integer) 4);
    }

    @Test
    public void testSizeAgain() {
        MyLinkedList<Integer> lst = new MyLinkedList<>();
        System.out.println("** Test #5: size of list...");
        for (int i = 0; i < 10001; i++) {
            lst.add(i);
        }
        assertEquals(lst.size(), 10001);
    }

    @Test
    public void removeMiddle() {
        MyLinkedList<Integer> lst = new MyLinkedList<>();
        System.out.println("** Test #6: remove in middle of list...");
        for (int i = 0; i < 20; i++) {
            lst.add(i);
        }
        assertEquals(lst.remove(10), (Integer) 10);
        assertEquals(printListForwards(lst), "0123456789111213141516171819");
        assertEquals(printListBackwards(lst), "1918171615141312119876543210");
        assertEquals(lst.size(), 19);

    }

    @Test
    public void removeFrontwSize5() {
        MyLinkedList<Integer> lst = new MyLinkedList<>();
        System.out.println("** Test #7: remove front of list w/ 5 elements...");
        for (int i = 0; i < 5; i++) {
            lst.add(i);
        }
        assertEquals(lst.remove(0), (Integer) 0);
        assertEquals(printListForwards(lst), "1234");
        assertEquals(printListBackwards(lst), "4321");
        assertEquals(lst.size(), 4);

    }

    @Test
    public void removeFrontwSize2() {
        MyLinkedList<Integer> lst = new MyLinkedList<>();
        System.out.println("** Test #8: remove front of list w/ 2 elements...");
        for (int i = 0; i < 2; i++) {
            lst.add(i);
        }
        assertEquals(lst.remove(0), (Integer) 0);
        assertEquals(printListForwards(lst), "1");
        assertEquals(printListBackwards(lst), "1");
        assertEquals(lst.size(), 1);

    }

    @Test
    public void removeFrontwSize1() {
        MyLinkedList<Integer> lst = new MyLinkedList<>();
        System.out.println("** Test #9: remove front of list w/ 1 element...");
        for (int i = 0; i < 1; i++) {
            lst.add(i);
        }
        assertEquals(lst.remove(0), (Integer) 0);
        assertEquals(printListForwards(lst), "");
        assertEquals(printListBackwards(lst), "");
        assertEquals(lst.size(), 0);

    }

    @Test
    public void removeBackwSize5() {
        MyLinkedList<Integer> lst = new MyLinkedList<>();
        System.out.println("** Test #10: remove back of list w/ 5 elements...");
        for (int i = 0; i < 5; i++) {
            lst.add(i);
        }
        assertEquals(lst.remove(4), (Integer) 4);
        assertEquals(printListForwards(lst), "0123");
        assertEquals(printListBackwards(lst), "3210");
        assertEquals(lst.size(), 4);

    }

    @Test
    public void removeBackwSize2() {
        MyLinkedList<Integer> lst = new MyLinkedList<>();
        System.out.println("** Test #11: remove back of list w/ 2 elements...");
        for (int i = 0; i < 2; i++) {
            lst.add(i);
        }
        assertEquals(lst.remove(1), (Integer) 1);
        assertEquals(printListForwards(lst), "0");
        assertEquals(printListBackwards(lst), "0");
        assertEquals(lst.size(), 1);

    }

    @Test
    public void removeBackwSize1() {
        MyLinkedList<Integer> lst = new MyLinkedList<>();
        System.out.println("** Test #12: remove front of list w/ 1 element...");
        for (int i = 0; i < 1; i++) {
            lst.add(i);
        }
        assertEquals(lst.remove(0), (Integer) 0);
        assertEquals(printListForwards(lst), "");
        assertEquals(printListBackwards(lst), "");
        assertEquals(lst.size(), 0);

    }

    @Test
    public void hasTest() {
        MyLinkedList<String> lst = new MyLinkedList<>();
        System.out.println("** Test #13: check has...");
        for (int i = 0; i < 100; i++) {
            lst.add(new String("" + i));
        }
        assertTrue(lst.has("0"));
        assertTrue(lst.has("99"));
        assertTrue(lst.has("45"));
        assertTrue(!lst.has("100"));
    }

    @Test
    public void testToString() {
        shortList = new MyLinkedList<>();
        shortList.add("A");
        shortList.add("B");
        assertEquals("[A, B]", shortList.toString());
        list1 = new MyLinkedList<>();
        list1.add(65);
        list1.add(21);
        list1.add(42);
        assertEquals("[65, 21, 42]", list1.toString());
    }
}