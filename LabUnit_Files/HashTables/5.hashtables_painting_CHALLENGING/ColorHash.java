/**
 * @author (put your name here)
 *
 */
public class ColorHash {
	// Implement this class, including whatever data members you need and all
	// of the public methods below.  You may create any number of private methods
	// if you find them to be helpful.
	
	public ColorHash(int tableSize, int bitsPerPixel, String collisionResolutionMethod, double rehashLoadFactor){}
	
	public ResponseItem colorHashPut(ColorKey key, long value){return null;}
	public ResponseItem increment(ColorKey key){return null;}
	public ResponseItem colorHashGet(ColorKey key)  throws Exception{return null;}
	public long getCount(ColorKey key){return -1L;}
	public ColorKey getKeyAt(long tableIndex){return null;}
	public long getValueAt(long tableIndex){return -1L;}
	public double getLoadFactor(){return -1.0;}
	public int getTableSize(){return -1;}
	public void resize(){}


}
