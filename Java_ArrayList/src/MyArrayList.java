
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements Iterable<E> {

    private int size;       // the number of elements stored
    E[] ary;                // access modifier is package protected for testing purposes

    public MyArrayList() {    // start with a threshold/capacity of 10
        ary = (E[]) new Object[10];
    }

    public boolean isEmpty() { // is the list empty?
        // todo
        return size <=0;
    }

    public int size() {         // the number of elements in the list
        // todo
        return size;
    }

    // add the item to the end unless it's null and throw a NoSuchElementException
    public void add(E item) {
        // todo work on throws
        if (item == null) throw new NoSuchElementException();
        if (size() + 1 >= ary.length) {
            E[] newAry = (E[]) new Object[(int) (size() + 1)];
            for (int i = 0; i < ary.length; i++)
                newAry[i] = ary[i];
            ary = newAry;
        }
        ary[size()] = item;
        size++;
    }

    // add the item at the specified index
    // throw a NoSuchElementException if item is null
    // throw an IndexOutOfBounds exception if the index is invalid
    public void add(E item, int index) {
        // todo
        if (index > size() + 1 || index < 0) throw new IndexOutOfBoundsException();
        if (item == null) throw new NoSuchElementException();
        if (index == ary.length) add(item);
        int counter =0;
        E[] newAry = (E[]) new Object[size() + 1];
        for (int i=0;i<size() + 1;i++){
            if (i != index) newAry[i] = ary[counter++];
            else  newAry[i] = item;
        }
        ary = newAry;
        size++;
    }

    // remove and return the item at the index
    // throw an IndexOutOfBounds exception if the index is invalid
    public E remove(int index) {
        // todo
        if (index > size() + 1 || index < 0) throw new IndexOutOfBoundsException();
        E hold = null;
        int count =0;
        E[] newAry = (E[]) new Object[size()-1];
        for (int i=0;i<size();i++){
            if (i == index) hold = ary[i];
            else newAry[count++] = ary[i];
        }
        if (newAry.length == 0)
            newAry = (E[]) new Object[2];
        ary = newAry;
        size--;
        return hold;
    }

    public E get(int index) {
        // todo
        return ary[index];
    }

    public void clear() {
        // todo
        size = 0;
        ary = (E[]) new Object[10];
    }
    public String toString()
    {
        String output = "";
        for (int i =0;i<ary.length;i++){
            output += ary[i] + "\n";
        }
        return output;
    }
    @Override
    public Iterator<E> iterator() {         // return an iterator over items in order
        return new Itr();
    }

    private class Itr implements Iterator<E> {


        // implemement this class
        private int nextCounter = 0;
        public boolean hasNext() {
            // todo
            return !(nextCounter >= ary.length);
        }

        @Override
        public void remove() {
            // todo
            MyArrayList.this.remove(nextCounter - 1);
        }

        @Override
        public E next() {
            // todo
            nextCounter ++;
            return ary[nextCounter - 1];
        }

    }

    // basic test cases
    // try adding your own thru JUnit
    public static void main(String[] args) {
        MyArrayList<String> test = new MyArrayList<>();
        test.add("Love");
        test.add("I", 0);
        test.add("Computer");
        test.add("Science");
        System.out.println(test.size());
        System.out.print(test);
        test.remove(test.size() - 1);
        test.remove(2);
        test.remove(0);
        System.out.print(test);
        test.remove(test.size() - 1);
        test.add("Iterators");
        test.add("Rock");
        System.out.print(test);

        test.clear();

        System.out.println("After clearing, size is: " + test.size());
        for (int i = 0; i < 10; i++) {
            test.add("" + i);
        }

        Iterator<String> it = test.iterator();
        for (; it.hasNext();) {
            System.out.print(it.next() + " ");
        }

        try {
            it.next();
        } catch (Exception e) {
            System.out.println("\nNo more elements to iterate");
        }

        test.clear();
        it = test.iterator();
        for (int i = 0; i < 10; i++) {
            test.add("" + i);
        }
        it.next();
        it.remove();
        it.next();
        it.next();
        it.next();
        it.remove();
        System.out.println("" + java.util.Arrays.toString(test.ary));

        long start = System.currentTimeMillis();
        for (int i = 0; i < 2621440; i++) {
            test.add("how fast?");
        }
        for (int i = 0; i < 1310730; i++) {
            test.remove(test.size() - 1);
        }
        for (int j = 0; j < 1E8; j++) {
            for (int i = 0; i < 10; i++) {
                test.add("how fast?");
            }
            for (int i = 0; i < 10; i++) {
                test.remove(test.size() - 1);
            }
        }
        long stop = System.currentTimeMillis();
        System.out.println("My ArrayList: " + (stop - start) / 1000.0);

        java.util.ArrayList<String> list = new java.util.ArrayList<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < 2621440; i++) {
            list.add("how fast?");
        }
        for (int i = 0; i < 1310730; i++) {
            list.remove(list.size() - 1);
        }
        for (int j = 0; j < 1E8; j++) {
            for (int i = 0; i < 10; i++) {
                list.add("how fast?");
            }
            for (int i = 0; i < 10; i++) {
                list.remove(list.size() - 1);
            }
        }
        stop = System.currentTimeMillis();
        System.out.println("Java's ArrayList: " + (stop - start) / 1000.0);


    }
}



