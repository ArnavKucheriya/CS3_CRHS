/**
 * @author	Steve Tanimoto <tanimoto@uw.edu>
 * @version	1.0
 * ColorKey is a class to represent colors at various number of bits per pixel.
 * <p>
 * A ColorKey object can be used as a hashing key.
 */
public class ColorKey {
	private int bitsPerPixel;
	private int bits;
	
/**
 * The constructor for a ColorKey object.
 * <p>
 * If the value of bpp is 24, then the 3 color components are simply packed into
 * one long int.  Otherwise, the color components get divided by a power of 2
 * by shifting them to the right some number of bit positions.
 * This cuts the number of bits per color component to a third of the bpp.
 * Then the 3 reduced color components are packed into the low-order bpp bits of "bits".
 * 
 * @param r	The red value of the input color. An int between 0 and 255.
 * @param g	The green value.
 * @param b	The blue value.
 * @param bpp	The number of bits per pixel: one of 3, 6, 9, 12, 15, 18, 21, or 24.
 * @throws Exception
 */
	public ColorKey(int r, int g, int b, int bpp) throws Exception {
		bitsPerPixel = bpp;
		if ((bpp % 3)==0) {
			int leftShift = bpp/3;
			int rightShift = 8 - leftShift;
			int mask = (1 << leftShift) - 1;
			//System.out.println("leftShift = "+leftShift);
			//System.out.println("rightShift = "+rightShift);
			//System.out.println("mask = "+mask);
			//System.out.println("leftShift = "+leftShift);
			r >>= rightShift; r &= mask;
			g >>= rightShift; g &= mask;
			b >>= rightShift; b &= mask;
			//System.out.println("r = "+r);
			//System.out.println("g = "+g);
			//System.out.println("b = "+b);
			bits = (((r << leftShift)+ g)<<leftShift) + b;
		}
		else {
			throw new Exception("Unsupported number of bits per pixel; use a multiple of 3 between 3 and 24.");
		}
	}
	/**
	 * Alternative constructor used to create keys when getting all the data out of the hash table.
	 */
	public ColorKey(int directIndex, int bpp) throws Exception {
		bitsPerPixel = bpp;
		if (bpp %3 != 0) {
			throw new Exception("Unsupported number of bits per pixel; use a multiple of 3 between 3 and 24.");
		}
		bits = directIndex;
	}

	public boolean equals(ColorKey other) {
		return bits==other.bits;
	}
	public int hashCode() {
		return (int) bits;
	}
	public String toString() {
		return "ColorKey[bits per pixel:"+bitsPerPixel+", bits:"+bits+"]";
	}
/**
 * The main method is here only for testing the constructor.  It is not used in 
 * normal use of the class.
 */
	public static void main(String[] argv) {
		System.out.println("Testing the ColorKey constructor...");
		try {
			ColorKey black = new ColorKey(0, 0, 0, 6);
			System.out.println("black's color key is: "+black);
			ColorKey white = new ColorKey(255, 255, 255, 6);
			System.out.println("white color key is: "+white);
			ColorKey green = new ColorKey(0, 255, 0, 6);
			System.out.println("green color key is: "+green);
			ColorKey number17 = new ColorKey(17, 9); // Call to the alternative constructor
			System.out.println("number17 color key is: "+number17);
			ColorKey blah = new ColorKey(255, 255, 255, 10);
			System.out.println("blah color key is: "+blah);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
