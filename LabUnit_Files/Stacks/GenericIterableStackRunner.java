//© A+ Computer Science  -  www.apluscompsci.com
//Name -  
//Date -
//Class -
//Lab  -

//ArrayList of ints
//or
//array of ints

import java.util.ArrayList;
import static java.lang.System.*;
import java.util.ListIterator;
import java.util.Stack;

public class GenericIterableStackRunner
{
	public static void main ( String[] args )
	{
		Stack<String>stack = new Stack<>();
		stack.push("comp");
		stack.push("sci");
		stack.push("rocks");
		ListIterator it = stack.listIterator();
		//it.set("ok");	// IllegalStateException
		it.next();
		it.set("COMP");
		it.set("what");
		it.next();
		it.next();
		System.out.println(it.previous());
		System.out.println(it.next());
		
		it.previous();
		it.previous();
		it.remove();
		//it.remove();
		it.add("hello");
		System.out.println(it.previous());
		System.out.println(stack);
		for(String item: stack){
			System.out.println("enhanced: " + item);
			//stack.push("concurrentMod");
		}
			
		//it.previous();	// NoSuchElementException();
		
		/*GenericIterableStack test = new GenericIterableStack();
		test.push(5);
		test.push(7);
		test.push(9);
		for(Object item: test){
			System.out.println((Integer)item);
		}
		System.out.println(test);
		System.out.println(test.isEmpty());
		System.out.println(test.pop());
		System.out.println(test.peek());
		System.out.println(test.pop());
		System.out.println(test.pop());
		System.out.println(test.isEmpty());
		System.out.println(test);*/

		//expand the test cases to test the class more thoroughly
		//GenericIterableStack<String> test2 = new GenericIterableStack<>();
		//test2.push("comp");
		//test2.push("sci");
		//test2.push("rocks");
		//for(String item: test2){
		//	System.out.println(item);
		//}
	/*	ListIterator it = test2.listIterator();
		//while(it.hasNext())
		//	System.out.println(it.next());
		it = test2.listIterator();
		it.next();
		it.set("COMP");
		it.set("what");
		it.next();
		it.next();
		System.out.println(it.previous());
		System.out.println(it.next());
		
		it.previous();
		it.previous();
		it.remove();
		it.remove();
		it.add("hello");
		it.previous();
		
		
		//System.out.println(it.previous());
		System.out.println(test2);
		
		
*/		System.out.println("GenericIterableStack");
		GenericIterableStack<String>stack1 = new GenericIterableStack<>();
		stack1.push("comp");
		stack1.push("sci");
		stack1.push("rocks");
		it = stack1.listIterator();
		//it.set("ok");	// IllegalStateException
		it.next();
		it.set("COMP");
		it.set("what");
		it.next();
		it.next();
		System.out.println(it.previous());
		System.out.println(it.next());
		
		it.previous();
		it.previous();
		it.remove();
		//it.remove();
		it.add("hello");
		System.out.println(it.previous());
		System.out.println(stack1);
		for(String item: stack1){
			System.out.println("enhanced: " + item);
			//stack1.push("concurrentMod");
		}
		
	}
}