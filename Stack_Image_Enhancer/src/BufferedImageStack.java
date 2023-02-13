// Arnav Kucheriya (AKUC0058)

import java.awt.image.BufferedImage; // to be able to use BufferedImage Wrapper
import java.lang.reflect.Array;
import java.util.*;

public class BufferedImageStack {
    private String lastOne; // this string keeps track of the last operation used
    private int check; // this variable keeps track if push/pop/clear has yet been executed
    private ArrayList<BufferedImage> iStack;



    // This method creates a constructor for the ImageStack class.
    public BufferedImageStack() { // other parameters?
        check = 0;
        lastOne = "";
        iStack = new ArrayList<BufferedImage>();

    }

    // This method adds the data item onto the top of the stack.
    public void push(BufferedImage dataItem) {
        check++;
        iStack.add(dataItem); // adds to the "end" of the list
    }

    // This method returns the top element of the stack, removing it from the Stack.
    // If the stack is empty, this method will throw an exception.
    public BufferedImage pop() {
        check++;
        lastOne = "pop";
        if (isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            return iStack.remove(iStack.size() - 1);
        }
    }

    // This method returns the top element of the stack, but does not
    // alter the stack like the pop method does.
    // If the stack is empty, this method will throw an exception.
    public BufferedImage peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            BufferedImage peekedItem = iStack.remove(iStack.size() - 1);
            iStack.add(peekedItem);
            return peekedItem;
        }
    }

    // This method returns true if there are no elements in the stack,
    // false otherwise.
    public boolean isEmpty() {
        return iStack.isEmpty();
    }

    // This method makes the stack empty by eliminating all of
    // the elements in it.
    public void clear() {
        check++;
        iStack.clear();
    }

    // This method returns true if the last push, pop, or clear operation was a pop and false otherwise. If neither pop, push nor clear has yet been executed, false is returned.
    public boolean popWasLast() {
        return (lastOne.equals("pop") && check > 0);
    }

    // This method returns the number of elements currently in the stack.
    public int getSize() {
        return iStack.size();
    }

    public int getsize(){
        BufferedImage [] result = new BufferedImage[0];
        int size = 0;
         size = result.length;

         return size;
    }
}