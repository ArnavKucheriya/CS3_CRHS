import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.JFrame;


public class ComplexNumber{

    private double a, b;
    // Create a Complex Number with the given real numbers.
    public ComplexNumber(double a, double b){
        this.a = a;
        this.b = b;
    }

    // Method for squaring a ComplexNumber
    public ComplexNumber square(){
        return new ComplexNumber(this.a*this.a - this.b*this.b, 2*this.a*this.b);
    }

    // Method for adding 2 complex numbers
    public ComplexNumber add(ComplexNumber cn){
        return new ComplexNumber(this.a+cn.a, this.b+cn.b);
    }

    // Method for calculating magnitude^2 (how close the number is to infinity)
    public double magnitude(){
        return a*a+b*b;
    }
}