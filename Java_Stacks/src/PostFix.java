

import java.util.Stack;
import java.util.Scanner;
import static java.lang.System.*;
import java.text.DecimalFormat;
import java.text.ParseException;

public class PostFix
{
	private Stack<Double> stack;
	private String expression;

	public PostFix()
	{
		expression = "";
		stack = new Stack<>();
	}

	public PostFix(String exp)
	{
		setExpression(exp);
		stack = new Stack<>();
	}

	public void setExpression(String exp)
	{
		expression = exp;
	}

	public double calc(double one, double two, char op)
	{
		switch(op){
			case '+': return two + one;
			case '-': return two - one;
			case '/': return two/one;
			case '*': return two * one;
		}
		return 0.0;
	}

	public void solve()
	{
		String[] listNumsandOps = expression.split("\s");
		for(int i=0;i<listNumsandOps.length;i++){
			switch(listNumsandOps[i]){
				case "+": stack.push(calc(stack.pop(),stack.pop(),'+')); continue;
				case "-": stack.push(calc(stack.pop(),stack.pop(),'-')); continue;
				case "/": stack.push(calc(stack.pop(),stack.pop(),'/')); continue;
				case "*": stack.push(calc(stack.pop(),stack.pop(),'*')); continue;
			}
			try {
				double l = DecimalFormat.getNumberInstance().parse(listNumsandOps[i]).doubleValue();
				stack.push(l);
			} catch (ParseException e) {
				throw new IllegalArgumentException();
			}
		}
	}
	public String toString(){
		return expression +" = "  + stack.pop();
	}
	//add a toString
}


