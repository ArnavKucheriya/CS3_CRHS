import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;

public class RecursionTree
{
    // ([PI]/[2, 4, 6, 8, 10])
    private double ANGLE = Math.PI/6;
    private float SHRINK_FACTOR = 1.5f;
    private int MAX_RECURSIONS = 10;

    public void drawBranch(Graphics g,int pointX, int pointY,double directionX,double directionY, float size, int recursion_number)
    {
        // Finding the End Point of the Vector(line) to draw it
        int x2 = (int)(pointX + directionX*size), y2 = (int)(pointY + directionY*size);
        // Setting the Colour of the line
        java.util.Random random = new java.util.Random();
        Color randomColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        g.setColor(randomColor);
        // Drawing the line from initial to final point
        g.drawLine(pointX,pointY,x2,y2);
        // If the maximum recursions are reached we need to stop
        if(recursion_number>=MAX_RECURSIONS)
        {
            return;
        }
        // Finding the size of next branch by shrinking the current branch
        float new_branch_size = size/SHRINK_FACTOR;
        // Finding the Direction Vector of the next branch
        double directionX2  =  Math.sin(ANGLE) * directionY + Math.cos(ANGLE) * directionX;
        double directionY2 = -(Math.sin(ANGLE) * directionX) +  Math.cos(ANGLE) * directionY;
        // Incrementing the recursion_number by 1
        int n2 = recursion_number+1;
        // Drawing the branch
        drawBranch(g,x2,y2,directionX2,directionY2,new_branch_size,n2);
        // Finding the Direction Vector of the corresponding branch on the other side i.e. with angle = -ANGLE
        directionX2  = Math.cos(-ANGLE) * directionX + Math.sin(-ANGLE) * directionY;
        directionY2 = -Math.sin(-ANGLE) * directionX + Math.cos(-ANGLE) * directionY;
        // Drawing the twin branch
        drawBranch(g,x2,y2,directionX2,directionY2,new_branch_size,n2);

    }

    public static void main(String args[])throws IOException
    {
        int IMG_WIDTH = 800;
        int IMG_HEIGHT = 500;
        // Creating a next 800x500 RGB Image
        BufferedImage img = new BufferedImage(IMG_WIDTH,IMG_HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
        // Getting the graphics object to draw on
        Graphics g = img.getGraphics();
        // Calling the drawBranch(Graphics, IntialX, InitialY, DirectionVectorX, DirectionVectorY, Length_Of_First_Line, Recursion_Number)
        new RecursionTree().drawBranch(g,IMG_WIDTH/2,IMG_HEIGHT,0,-1,IMG_HEIGHT/3,0);
        // Saving the Image
        ImageIO.write(img,"PNG",new File("tree.png"));
    }

}