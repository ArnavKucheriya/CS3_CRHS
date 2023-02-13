import java.awt.*;

/**
 * The Turtle class describes Turtle objects which
 * represent the drawing turtle in a turtle graphics
 * application. Each turtle has a position on the
 * screen, a color and an angle indicating the direction
 * in which the turtle is traveling.  Also, a turtle's 
 * pen may either be down (drawing) or up (not drawing).
 */
public class Turtle {
    
    private int x;
    private int y;
    private int angle;
    private Color penColor;
    private boolean penPosition;

    /**
     * Constant indicating a Turtle's pen is down.
     */
    public static boolean PEN_DOWN = true;

    /**
     * Constant indicating that a Turtle's pen is up.
     */
    public static boolean PEN_UP = false;

    /**
     * Construct a new default Turtle.  The new Turtle will
     * appear at position 50,50, will have an angle of 0,
     * its pen will be black and will be up.
     */
    public Turtle() {	
	x = 50;
	y = 50;
	angle = 0;
	penColor = Color.black;
	penPosition = PEN_UP;
    }
    
    /**
     * Construct a new Turtle with the specified parameters. The
     * new Turtle's pen will be up.
     *
     * @param initX the x coordinate for the new Turtle.
     * @param initY the y coordinate for the new Turtle.
     * @param initAngle the angle for the new Turtle.
     * @param initColor the color of the new Turtle's pen.
     */
    public Turtle(int initX, int initY, int initAngle, Color initColor) {
	x=initX;
	y=initY;
	angle=initAngle;
	penColor = initColor;
	penPosition = PEN_UP;
    }

    /**
     * Move this Turtle forward by the specified number of
     * screen pixels.
     *
     * @param pixels the number of screen pixes by which
     *               to move this Turtle forward. 
     */
    public void moveForward(int pixels) {
	double radAngle = Math.toRadians(angle);
	x = x + (int)Math.round(Math.cos(radAngle) * pixels);
	y = y - (int)Math.round(Math.sin(radAngle) * pixels);
    }

    /**
     * Rotate this Turtle counter-clockwise by the specified 
     * number of degrees.
     *
     * @param degrees the number of degrees by which to rotate
     *                this Turtle.
     */
    public void rotate(int degrees) {
	int newAngle = angle + degrees;
	angle = newAngle % 360;
    }

    /**
     * Put this Turtle's pen down. When this Turtle's pen is
     * down it will draw a line in its color as it moves
     * forward.
     */
    public void putPenDown() {
	penPosition = PEN_DOWN;
    }

    /**
     * Pick this Turtle's pen up. When this Turtle's pen is
     * up it will not draw a line as it moves forward.
     */
    public void pickPenUp() {
	penPosition = PEN_UP;
    }

    /**
     * Ask this Turtle if it's pen is up or down. The value
     * returned will be either PEN_UP or PEN_DOWN.
     * 
     * @return PEN_DOWN if this Turtle's pen is up or
     *         PEN_UP if this Turtle's pen is up.
     */
    public boolean getPenPosition() {
	return penPosition;
    }

    /**
     * Get the x coordinate of this Turtle.
     *
     * @return the x coordinate of this Turtle.
     */
    public int getX() {
	return x;
    }

    /**
     * Get the y coordinate of this Turtle.
     *
     * @return the y coordinate of this Turtle.
     */
    public int getY() {
	return y;
    }

    /**
     * Get the color of this Turtle's pen. The color is 
     * returned as a reference to a Color object.
     *
     * @return a reference to a Color object representing 
     *         the Color of this Turtle's pen.
     */
    public Color getColor() {
	return penColor;
    }

    /**
     * Get the the angle to which this Turtle is turned.  
     * The angle of the Turtle is measured counter-clockwise
     * from horizontal.
     *
     * @return the angle to which this Turtle is turned. 
     */
    public int getAngle() {
	return angle;
    }
}