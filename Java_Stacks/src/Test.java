import java.util.Stack;
import java.lang.*;
public class Test {
    public static void main(String[] args) {
        Stack<Integer> stack;
        stack = new Stack<Integer>();
        stack.push(15);
        stack.push(32);
        stack.push(12);
        stack.pop();
        System.out.println(stack.size()+"\n"+stack.peek());
        stack.push(34);
        stack.push(3);

        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
        stack.push(123);
        System.out.println(stack.peek());

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }

        stack.push(1);
        stack.push(6);



        stack.push(stack.pop()+stack.pop());



        System.out.println(stack);

        Stack<Integer>stack1 = new Stack<>();
        Stack<Integer>stack2 = new Stack<>();

        stack1.push(12);
        stack1.push(23);

        stack2.push(stack1.pop());

        System.out.println(stack2);
    }
}
