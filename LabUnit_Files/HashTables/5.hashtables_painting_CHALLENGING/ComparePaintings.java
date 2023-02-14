/**
 * @author (your name goes here)
 *
 */
public class ComparePaintings {

	public ComparePaintings(){}; // constructor.
	
	// Load the image, construct the hash table, count the colors.
	ColorHash countColors(String filename, int bitsPerPixel) {
		// Implement this. Go thru rows then cols of images.
		return null; // Change this to return the real result.
	}

//	Starting with two hash tables of color counts, compute a measure of similarity based on the cosine distance of two vectors.
	double compare(ColorHash painting1, ColorHash painting2) {
		// Implement this.
		return 1.0; // Change this to return the actual similarity value.
	}

	//	A basic test for the compare method: S(x,x) should be 1.0, so you should compute the similarity of an image with itself and print out the answer. If it comes out to be 1.0, that is a good sign for your implementation so far.
	void basicTest(String filename) {
		// Implement this.		
	}

	//		Using the three given painting images and a variety of bits-per-pixel values, compute and print out a table of collision counts in the following format:
	void CollisionTests() {
		// Implement this.				
	}
		
// This simply checks that the images can be loaded, so you don't have 
// an issue with missing files or bad paths.
	void imageLoadingTest() {
		ImageLoader mona = new ImageLoader("MonaLisa.jpg");
		ImageLoader starry = new ImageLoader("StarryNight.jpg");
		ImageLoader christina = new ImageLoader("ChristinasWorld.jpg");
		System.out.println("It looks like we have successfully loaded all three test images.");

	}
	/**
	 * This is a basic testing function, and can be changed.
	 */
	public static void main(String[] args) {
		ComparePaintings cp = new ComparePaintings();
		cp.imageLoadingTest();
	}

}
