/**
 * @author Steve Tanimoto	<tanimoto@uw.edu>
 *
 * An instance of this class will be returned whenever a get or a put operation
 * is performed in a ColorHash hash table.
 * All the data members are public so that they can be easily accessed in
 * other files without the use of getters and setters.
 * Although a get operation in a hash table usually returns only the value for
 * the given key, we return an object called a ResponseItem that wraps up the value
 * with some additional information that tells what happened during the operation.
 * Similarly, we return a ResponseItem for a put operation, instead of just returning
 * nothing, so that the caller can find out what happened in terms of collisions, etc.
 */
public class ResponseItem {
	public long value; // meaningful only for get operations (colorHashGet),
	         //where the value associated with the key is returned.
	public int nCollisions; // the number of collisions involved in this operation.
	public boolean didRehash; // true if this operation caused a rehash due to either exceeding
	         //the load factor threshold or running out of space 
			// (this can happen with quadratic collision resolution).
	        // This can only happen when inserting.
	public boolean didUpdate; // true if this operation caused the value associated with
	         //an existing key to be overwritten (possibly changed but possibly
			//just rewritten with the same value).
	        // If the put operation inserted a new key, then false should be returned.


	/**
	 * Constructor -- nothing unusual here.
	 */
	public ResponseItem(long v, int nc, boolean dr, boolean du) {
		value = v;
		nCollisions = nc;
		didRehash = dr;
		didUpdate = du;
	}
	public String toString() {
		return "ResponseItem[value:"+value+", nCollisions:"+nCollisions+", didRehash:"+didRehash+", didUpdate:"+didUpdate+"]";
	}

	/**
	 * The main method is here for some early testing, and it's no longer needed.
	 */
	public static void main(String[] args) {
		ResponseItem ri = new ResponseItem(17, 3, true, false);
		System.out.println("A sample ResponseItem: " + ri);
	}

}
