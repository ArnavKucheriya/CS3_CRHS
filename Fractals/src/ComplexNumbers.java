
public class ComplexNumbers
{

    private double real;

    private double imaginary;


    public ComplexNumbers()
    {
        real = 0.0;
        imaginary = 0.0;
    }



    public ComplexNumbers(double real, double imaginary)
    {
        this.real = real;
        this.imaginary = imaginary;
    }



    public void add(ComplexNumbers complex_number)
    {
        this.real = this.real + complex_number.real;
        this.imaginary = this.imaginary + complex_number.imaginary;
    }



    public ComplexNumbers conjugate()
    {
        return new ComplexNumbers(this.real,-this.imaginary);
    }


    public double mod()
    {
        return Math.sqrt(Math.pow(this.real,2) + Math.pow(this.imaginary,2));
    }



    public ComplexNumbers square()
    {
        double _real = this.real*this.real - this.imaginary*this.imaginary;
        double _imaginary = 2*this.real*this.imaginary;
        return new ComplexNumbers(_real,_imaginary);
    }



    public void multiply(ComplexNumbers complex_number)
    {
        double _real = this.real*complex_number.real - this.imaginary*complex_number.imaginary;
        double _imaginary = this.real*complex_number.imaginary + this.imaginary*complex_number.real;

        this.real = _real;
        this.imaginary = _imaginary;
    }



    public void print()
    {
        System.out.println(this.real+" + "+this.imaginary+"i");
    }
}