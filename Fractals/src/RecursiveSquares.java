/*
 * Lab 02: RecursiveSquares
 * 
 * Description: Describe what your program does. 
 * 
 * Extensions: Describe the extensions you implemented, if any.
 *
 * @author Your Name Here
 *
 ***********************************************************
 * (to be completed by the grader)
 * 
 * Grade (out of 3):
 * 
 * Extension grade: 
 * 
 * Grader Comments: 
 * 
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class RecursiveSquares extends JPanel {
    
    public static final int BOX_WIDTH = 1024;
    public static final int BOX_HEIGHT = 768;
    public static final Color MAMMOTH_PURPLE = new Color(63, 31, 105);
    public static final Color SPRING_LEAF_LIGHT = new Color(147, 210, 143);

    public static final double SQUARE_RATIO = 2.2;
    public static final int DEFAULT_DEPTH = 6;

    private static int maxDepth;

    
    public RecursiveSquares(){
        this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
    }


    // Draw the figure in the upper-left corner (you should add to this description)
    // the figure is centered at (cx, cy), size is the size of the inner square,
    // and depth is the current recursion depth
    private void drawULFigure (Graphics g, int cx, int cy, int size, int depth) {
	
	g.setColor(SPRING_LEAF_LIGHT);
	g.fillRect(cx - size / 2, cy - size / 2, size, size);

	g.setColor(Color.BLACK);
	g.drawRect(cx - size / 2, cy - size / 2, size, size);
    }

    // Draw the figure in the upper-right corner (you should add to this description)
    private void drawURFigure (Graphics g, int cx, int cy, int size, int depth) {
	
    }

    // Draw the figure in the lower left corner (you should add to this description)
    private void drawLLFigure (Graphics g, int cx, int cy, int size, int depth) {
	
    }

    // Draw the figure in the lower right
    private void drawLRFigure (Graphics g, int cx, int cy, int size, int depth) {
	
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

	// make the background
        g.setColor(MAMMOTH_PURPLE);
        g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);

	// draw the upper left figure
	drawULFigure(g, 200, 200, 200, 0);

	// draw the upper right figure
	drawURFigure(g, 580, 200, 200, 0);

	// draw the lower left figure
	drawLLFigure(g, 200, 580, 200, 0);

	// draw the lower right figure
	drawLRFigure(g, 580, 580, 200, 0);
    }
    
    public static void main(String args[]){
	// set default recursion depth
	maxDepth = DEFAULT_DEPTH;
	
	// if an argument is passed, use that value as recursion depth
	if (args.length > 0) {
	    maxDepth = Integer.parseInt(args[0]);
	}
	
        JFrame frame = new JFrame("Recursive Squares");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new RecursiveSquares());
        frame.pack();
        frame.setVisible(true);
    }
}
