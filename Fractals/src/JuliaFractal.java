import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class JuliaFractal {

    // The width and height of the window.
    private static final int WIDTH = 1080;
    private static final int HEIGHT = 1200;
    // The ComplexNumber to base the Julia Set off of.
    private static ComplexNumber c = new ComplexNumber(-0.223, 0.745);

    // Array of booleans stating which pixels make up the fractal.
    private boolean[][] values = null;

    // The bounds of the Complex Plane to graph.
    private double minX = -1.5;
    private double maxX = 1.5;
    private double minY = -1.5;
    private double maxY = 1.5;

    // The image to draw on.
    private BufferedImage image = null;

    // The maximum Magnitude of the ComplexNumer to be allowed in the Set.
    private double threshold = 1;
    // The number of times that the algorithm recurses.
    private int iterations = 50;


    public JuliaFractal() {
        // Create a BufferedImage to paint on.
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        // Fill the booean[][].
        getValues();
        // Go through the array and set the pixel color on the BufferedImage
        // according to the values in the array.
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                // If the point is in the Set, color it White, else, color it Black.
                if (values[i][j]) image.setRGB(i, j, Color.WHITE.getRGB());
                if (!values[i][j]) image.setRGB(i, j, Color.BLACK.getRGB());
            }
        }

        // Create a Frame to display the Fractal.
        JFrame f = new JFrame("Julia Fractal") {
            // Override the paint method (for simplicity)
            @Override
            public void paint(Graphics g) {
                g.drawImage(image, 0, 0, null);
            }
        };
        // Set other Frame settings.
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(WIDTH, HEIGHT);
        f.repaint();
        f.setVisible(true);
    }


    private void getValues() {
        values = new boolean[WIDTH][HEIGHT];
        // Go through each pixel.
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {

                double a = (double) i * (maxX - minX) / (double) WIDTH + minX;
                double b = (double) j * (maxY - minY) / (double) HEIGHT + minY;
                // fill the boolean array.
                values[i][j] = isInSet(new ComplexNumber(a, b));
            }
        }
    }


    private boolean isInSet(ComplexNumber cn) {
        for (int i = 0; i < iterations; i++) {
            // The basic Julia Set Algorithm.
            // Other Algorithms can be found online.
            cn = cn.square().add(c);
        }
        // If the threshold^2 is larger than the magnitude, return true.
        return cn.magnitude() < threshold * threshold;
    }


    public static void main(String[] args) {
        new J();
    }
}
