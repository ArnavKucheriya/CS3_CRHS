import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * 
 * UnitTestColorHash.java  (Version 0.51).
 * 
 * Assorted tests for ColorHash tables, using JUnit, version 4.
 * 
 * This version provides a test for quadratic probing and a test
 * for response items. 
 * To "turn off" any test, put double slashes ( "//" ) in front of the
 * "@Test" that precedes that method.   For example... "//@Test  ".
 * Note that some of the methods mentioned here such as setDebugging and showWholeTable
 * are not required to be supported by the ColorHash class, but you might wish
 * to create methods to help you with debugging.
 *
 * @author Steve Tanimoto
 * @version 0.51  (supercedes versions 0.02 and 0.50). 
 * 
 * Oct. 27, 2016
 * 
 */
public class UnitTestColorHash {
	ColorKey blackKey=null, whiteKey=null, redKey=null, greenKey=null, blueKey=null;
	ColorKey blackKey1=null, blackKey2=null, blackKey3=null, blackKey4=null;
	ColorKey blackKey5=null, blackKey6=null, blackKey7=null, blackKey8=null;
	ColorHash testHT;
	
	void makeSomeKeys() {
		try {
			blackKey = new ColorKey(0, 15);
			whiteKey = new ColorKey(255, 255, 255, 15);
			redKey   = new ColorKey(255, 0, 0, 15);
			greenKey = new ColorKey(0, 255, 0, 15);
			blueKey  = new ColorKey(0, 0, 255, 15);
		}
		catch(Exception e) {}
	}
	void makeCollidingKeys() {
		// For tableSize=13, these keys all collide.
		// We use these below in testing quadratic probing.
		try {
			blackKey1 = new ColorKey(0, 15);
			blackKey2 = new ColorKey(13, 15);
			blackKey3   = new ColorKey(26, 15);
			blackKey4 = new ColorKey(39, 15);
			blackKey5  = new ColorKey(52, 15);
			blackKey6  = new ColorKey(65, 15);
			blackKey7  = new ColorKey(78, 15);
			blackKey8  = new ColorKey(91, 15);
		}
		catch(Exception e) {
			System.out.println("A problem while making colliding keys");}
	}
	
	@Test
    public void testPutAndGetAt() {
		makeSomeKeys();
    	// Instantiate ColorHash.
    	testHT = new ColorHash(3, 6, "Linear Probing", 0.9);
    	assertEquals(3, testHT.getTableSize());
    	ResponseItem ri = testHT.colorHashPut(blackKey, 5);
    	assertEquals(ri.nCollisions, 0);
    	assertEquals(ri.didRehash, false);
    	assertEquals(ri.didUpdate, false);
    	ColorKey k = testHT.getKeyAt(0);
    	assertEquals(k, blackKey);
    	ri = testHT.colorHashPut(whiteKey, 5);
    	ri = testHT.colorHashPut(redKey, 5);
    	assertEquals(ri.didRehash, true);
    	assertEquals(ri.didUpdate, false);
    	ri = testHT.colorHashPut(redKey, 23);
    	assertEquals(ri.didRehash, false);
    	assertEquals(ri.didUpdate, true);
    	ColorKey k2 = testHT.getKeyAt(6);
    	assertEquals(k2, redKey);
    	long v = testHT.getValueAt(6);
    	assertEquals(v, 23);
    }
    @Test
    public void testPutAndGet() {
		makeSomeKeys();
    	// Instantiates ColorHash.
    	testHT = new ColorHash(3, 6, "Linear Probing", 0.9);
    	assertEquals(3, testHT.getTableSize());
    	//testHT.setDebugging(true);
    	System.out.println(testHT.colorHashPut(blackKey, 5));
    	try {
    		long v = testHT.colorHashGet(blackKey).value;
    		assertEquals(v, 5);
    		testHT.colorHashPut(whiteKey, 5);
    		testHT.colorHashPut(redKey, 5);
    		testHT.colorHashPut(redKey, 23);
    		v = testHT.colorHashGet(redKey).value;
    		assertEquals(v, 23);
    	}
    	catch(Exception e) {};
    }

	@Test
    public void testIncrementing() {
		makeSomeKeys();
    	// Instantiates ColorHash.
    	testHT = new ColorHash(3, 6, "Linear Probing", 0.9);
    	//assertEquals(3, testHT.getTableSize());
    	//testHT.setDebugging(true);
    	System.out.println(testHT.increment(blackKey));
    	//System.out.println(testHT.showWholeTable());
    	System.out.println(testHT.increment(whiteKey));
    	//System.out.println(testHT.showWholeTable());
    	System.out.println(testHT.increment(blackKey));
    	//System.out.println(testHT.showWholeTable());
    	System.out.println(testHT.increment(redKey));
    	//System.out.println(testHT.showWholeTable());
    }
	@Test
	public void testIncrementAndGet() {
		makeSomeKeys();
    	testHT = new ColorHash(3, 6, "Linear Probing", 0.9);
    	System.out.println(testHT.increment(redKey));
    	System.out.println(testHT.increment(redKey));
    	try {
    		ResponseItem ri = testHT.colorHashGet(redKey);
    		assertEquals(ri.value, 2);
    		System.out.println(ri);
    	}
    	catch(Exception e){}
	}
	@Test
    public void testQuadraticProbing() {
		makeCollidingKeys();
    	// Instantiates ColorHash.
    	testHT = new ColorHash(13, 6, "Quadratic Probing", 0.49);
    	//testHT.setDebugging(true);
    	System.out.println(testHT.colorHashPut(blackKey1, 1L));
    	//System.out.println(testHT.showWholeTable());
    	System.out.println(testHT.colorHashPut(blackKey2, 2L));
    	//System.out.println(testHT.showWholeTable());
    	System.out.println(testHT.colorHashPut(blackKey3, 3L));
    	//System.out.println(testHT.showWholeTable());
    	long v = testHT.getValueAt(4);
    	assertEquals(v, 3L);
    	System.out.println(testHT.colorHashPut(blackKey4, 4L));
    	//System.out.println(testHT.showWholeTable());
    	v = testHT.getValueAt(9);
    	assertEquals(v, 4L);
    	System.out.println(testHT.colorHashPut(blackKey5, 5L));
    	//System.out.println(testHT.showWholeTable());
    	v = testHT.getValueAt(3);
    	assertEquals(v, 5L);
    	System.out.println(testHT.colorHashPut(blackKey6, 6L));
    	//System.out.println(testHT.showWholeTable());
    	v = testHT.getValueAt(12);
    	assertEquals(v, 6L);
    	
    }
	@Test
	public void testQuadraticProbingAndResizing() {
		testQuadraticProbing(); // Sets up the table to just before resizing.
		long v;
		try {
    	v = testHT.getValueAt(7);
		    	assertEquals(v, -1L); // This fails if you rehash too soon. 
		}
		catch(RuntimeException e) {}

    	System.out.println(testHT.colorHashPut(blackKey6, 999L)); // This is an update -- do not resize now.
    	try {
    	v = testHT.getValueAt(7);
    	assertEquals(v, -1L); // Again, this fails if you rehash too soon. 
    	}
    	catch(RuntimeException e) {}
    	// The next operation should force a resize from 13 to 29.
    	System.out.println(testHT.colorHashPut(blackKey7, 7L));
    	//System.out.println(testHT.showWholeTable());
    	v = testHT.getValueAt(20);
    	assertEquals(v, 7L);
    	System.out.println(testHT.colorHashPut(blackKey8, 8L));
    	//System.out.println(testHT.showWholeTable());
    	v = testHT.getValueAt(4);
    	assertEquals(v, 8L);
	}
	@Test
	public void testResponseItemsForQuadraticProbingAndResizing() {
		testQuadraticProbing(); // Sets up the table to just before resizing.
		

    	ResponseItem ri = testHT.colorHashPut(blackKey6, 999L); // This is an update -- do not resize now.
    	System.out.println("ResponseItem after update in testResponseItemsforQuadraticProbingAndResizing: "+ri);
    	assertEquals(5, ri.nCollisions);
    	assertEquals(false, ri.didRehash);
    	assertEquals(true, ri.didUpdate);

		try {
			ri = testHT.colorHashGet(blackKey6);
			assertEquals(999L, ri.value);
			assertEquals(5, ri.nCollisions);
		   	assertEquals(false, ri.didRehash);
	    	assertEquals(false, ri.didUpdate);
		}
		catch(Exception e) { 
			System.out.println("Key blackKey6 not found in table.");
			assertEquals(true, false); // force a failure of this test.
			}

    	// The next operation should force a resize from 13 to 29.
    	ri = testHT.colorHashPut(blackKey7, 7L);
    	System.out.println("ResponseItem after insertion in testResponseItemsforQuadraticProbingAndResizing: "+ri);
    	//System.out.println(testHT.showWholeTable());
    	assertEquals(29, testHT.getTableSize());
    	assertEquals(6, ri.nCollisions);
    	assertEquals(true, ri.didRehash);
    	assertEquals(false, ri.didUpdate);
    	
	}
}
