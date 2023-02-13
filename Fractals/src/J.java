import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.out;

public class J
{
    public static void main(String args[])throws IOException
    {
        // Taking the Image WIDTH and HEIGHT variables. Increasing or decreasing the value will affect computation time.
        double WIDTH = 1600;
        double HEIGHT = 1200;

        // Setting the Saturation of every pixel to maximum
        // This can be played with to get different results
        float Saturation = 1f;

        // Creating a new blank RGB Image to draw the fractal on
        BufferedImage img = new BufferedImage((int)WIDTH, (int)HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Getting the constant ComplexNumber as input from the user for use in the function f(z) = z + c
        out.print("Re(c): ");
        double cReal = Double.parseDouble(reader.readLine());
        out.print("Im(c): ");
        double cImag = Double.parseDouble(reader.readLine());

        // Creating the constant complex number from input real and imaginary values
        ComplexNumbers constant = new ComplexNumbers(cReal,cImag);

        // Setting the maximum iterations to 256. This can be increased if you suspect an escapee set may be found beyond this value.
        // Increasing or decreasing the value will affect computation time.

        int max_iter = 256;

        // Looping through every pixel of image
        for(int X=0; X<WIDTH; X++)
        {
            for(int Y=0; Y<HEIGHT; Y++)
            {
                // Creating an empty complex number to hold the value of last z
                ComplexNumbers oldz = new ComplexNumbers();




                ComplexNumbers newz = new ComplexNumbers(2.0*(X-WIDTH/2)/(WIDTH/2), 1.33*(Y-HEIGHT/2)/(HEIGHT/2) );

                // Iterating till the orbit of z0 escapes the radius 2 or till maximum iterations are completed
                int i;
                for(i=0;i<max_iter; i++)
                {

                    oldz = newz;

                    // Applying the function newz = newz^2 + c, where c is the constant ComplexNumber user input
                    newz = newz.square();
                    newz.add(constant);

                    // Checking if the modulus/magnitude of complex number has exceeded the radius of 2
                    // If yes, the pixel is an escapee and we break the loop
                    if(newz.mod() > 2)
                        break;
                }

                // Checking if the pixel is an escapee
                // If yes, setting the brightness to the maximum
                // If no, setting the brightness to zero since the pixel is a prisoner
                float Brightness = i < max_iter ? 1f : 0;

                // Setting Hue to a function of number of iterations (i) taken to escape the radius 2
                // Hue = (i%256)/255.0f;
                // i%256 to bring i in range [0,255]
                // dividing by 255.0f to bring it in range [0,1] so that we can pass it to Color.getHSBColor(H,S,B) function
                float Hue = (i%256)/255.0f;

                // Creating the color from HSB values and setting the pixel to the computed color
                Color color = Color.getHSBColor(Hue, Saturation, Brightness);
                img.setRGB(X,Y,color.getRGB());
            }
        }
        // Saving the image
        ImageIO.write(img,"PNG", new File("julia.png"));
    }
}

// [-0.4, 0.6]
// [-0.63, 0.0123]
// [0.285, 0.01]
// [-0.835, -0.2321]
// [-0.8, 0.156]
// [-0.62772, 0.42193]
// [0.223, 0.53780]
// [-0.07709787, -0.08545]