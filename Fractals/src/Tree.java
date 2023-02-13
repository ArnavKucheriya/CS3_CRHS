
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class Tree extends JPanel {
    public Graphics2D g1; //creates 2D graphics, contained in JPanel, called g1



    public static final int maxAngle = 360;
    public static final int startX = 500;
    public static final int startY = 700;
    public static final int numOfRecursions = 9;
    public static final int startAngle = 0;
    public static final double treeSize = 2;
    public static final int Detail = 2;
    public static final int randFact = 30;
    public static final int[] constFact = {-60, 05, -50, 45};


    public static int[] red =   {0, 0, 0, 0, 7, 15, 23, 31, 39, 47, 55, 43};
    public static int[] green = {171, 159, 147, 135, 123, 111, 99, 87, 75, 63, 51, 43};
    public static int[] blue =  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};



    public static double degToRad(int deg) {
        return deg * Math.PI / 180;
    }

    public static void drawFractal(Graphics2D g1, int x, int y, int n, int angle) {

        if (n == Detail) return;
        int len = (int) Math.round(Math.pow(treeSize, n - 1)); //Generates an integer which is 2 to the power of n - 1, and since n is decrementing by 1 during each recursion, the length of each branch will be halved each time


        int xn1 = (int) Math.round(x - (2 * len * Math.sin(degToRad(angle))));
        int yn1 = (int) Math.round(y - (2 * len * Math.cos(degToRad(angle))));


        int mid1x = (x + xn1) / 2;
        int mid1y = (y + yn1) / 2;
        int mid2x = (mid1x + xn1) / 2;
        int mid2y = (mid1y + yn1) / 2;
        int mid3x = (x + mid1x) / 2;
        int mid3y = (y + mid1y) / 2;
        int mid4x = (mid3x + mid1x) / 2;
        int mid4y = (mid3y + mid1y) / 2;

        java.util.Random r = new java.util.Random(); //creates a random floating point between 0 and 1 excluding 1


        drawFractal(g1, mid1x, mid1y, n - 1, (angle + r.nextInt(randFact) + constFact[0]) % maxAngle);
        drawFractal(g1, mid2x, mid2y, n - 1, (angle + r.nextInt(randFact) + constFact[1]) % maxAngle);
        drawFractal(g1, mid3x, mid3y, n - 1, (angle + r.nextInt(randFact) + constFact[2]) % maxAngle);
        drawFractal(g1, mid4x, mid4y, n - 1, (angle + r.nextInt(randFact) + constFact[3]) % maxAngle);


        Color c = new Color(red[(r.nextInt() % 3) + n], green[(r.nextInt() % 3) + n], blue[(r.nextInt() % 3) + n]);
        g1.setColor(c); //uses the value generated above to set the color of g1, which is the 2D graphics used to draw the line below


        Line2D L1 = new Line2D.Double(x, y, xn1, yn1);
        g1.draw(L1); // Draws to the screen the line which is created above
        return; //ends the method
    }


    public void paint(final Graphics g) {
        g1 = (Graphics2D) g; //the graphics g is converted into 2D graphics by casting (Graphics2D), and is assigned to g1 which is used as one of the arguments of drawFractal
        drawFractal(g1, startX, startY, numOfRecursions, startAngle);
    }

    public static void main(String args[]) {
        JFrame FF = new JFrame("Drawing a recursive tree"); //creates a JFrame which is basically the window which displays the Fractal Tree. This JFrame is titled "Drawing a recursive tree"
        FF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes the JFrame when the user clicks the exit button
        Tree F = new Tree(); //Creates a new Fractal Tree
        FF.setBackground(Color.BLACK); //Sets the background color of the display screen to black
        FF.add(F); //The JFrame implements the FractalTree F into its interface
        FF.pack();
        FF.setVisible(true); //the boolean true is assigned to make the JFrame visible so that the window actually appears
        FF.setSize(1200, 1000); //Sets the size of the screen to 1200 x 1000
    }
}

//   public void drawSierpinskiCarpet (x, y, order) {
//        if (order == 0){
//            drawFilledSquare(x, y, BASE_SIZE)}
//        else
//        {for (row = 0;row++; row <= 2)
//        for (col = 0 to col = 2;)
//        if (col != 1 || row != 1):
//        x_i = newX(x, y, row, col)
//        y_i = newY(x, y, row, col)
//        drawSierpinskiCarpet(x_i, y_i, order - 1)
//    }https://cs.smu.ca/~porter/csc/465/code/deitel/examples/ch15/fig15_23_24/Fractal.java2html