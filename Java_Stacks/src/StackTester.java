//© A+ Computer Science  -  www.apluscompsci.com
//Name -  
//Date -
//Class -
//Lab  -

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import static java.lang.System.*;

public class StackTester
{
	private Stack<String>stack;

	public StackTester()
	{
		stack = new Stack<>();
		setStack("");
	}

	public StackTester(String line)
	{
		stack = new Stack<>();
		stack.addAll(List.of(line.split("\s")));
	}
	
	public void setStack(String line)
	{
	}

	public void popEmAll()
	{
		while(!stack.isEmpty()) {
			out.print(stack.pop()+"\s");
		}
		out.println();
	}

	//add a toString
}