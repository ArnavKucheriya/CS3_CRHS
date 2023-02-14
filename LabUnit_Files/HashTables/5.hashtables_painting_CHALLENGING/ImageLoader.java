import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Steve Tanimoto <tanimoto@uw.edu>
 *
 * This class handles the loading of images from files, and it
 * supports accessing the color of any pixel through its method
 * called getColorKey.
 */
public class ImageLoader {
	
	BufferedImage bi;
	int width;
	int height;
	
	public ImageLoader(String filename) {
		try {
			bi = ImageIO.read(new File(filename));
			width = bi.getWidth(null);
			height = bi.getHeight(null);
		} catch (IOException e) {
			System.out.println("Image could not be read: "+filename);
			System.exit(1);
		}
		
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public ColorKey getColorKey(int x, int y, int bitsPerPixel) {
		int rgb = bi.getRGB(x,  y);
		int r = (rgb >> 16) & 255;
		int g = (rgb >> 8) & 255;
		int b = rgb & 255;
		try {
			ColorKey ck = new ColorKey(r, g, b, bitsPerPixel);
			return ck;
		}
		catch(Exception e) {
			System.out.println("In file ImageLoader.java: "+e);
			return null;
		}
	}

	/**
	 * The main routine is only for basic testing of ImageLoader.
	 */
	public static void main(String[] args) {
		ImageLoader il = new ImageLoader("MonaLisa.jpg");
		ColorKey ck = il.getColorKey(0, 0, 6);
		System.out.println(ck);

	}

}
