
import java.util.Arrays;

public class KthElement {

    public static void main(String[] args) {
        KthElement test = new KthElement();
        Comparable c = test.kth(new Comparable[]{9, 5, 3, 2, 1}, 2);
        System.out.println(c);

        c = test.kth(new Comparable[]{19, 52, 3, 2, 7, 21}, 0);
	System.out.println(c);

        c = test.kth(new Comparable[]{68, 66, 11, 2, 42, 31}, 5);
	System.out.println(c);

        c = test.kth(new Comparable[]{90, 40, 20, 30, 10, 67, 100}, 3);
	System.out.println(c);

        c = test.kth(new Comparable[]{3, 15, 61, 11, 7, 9, 2}, 3);
	System.out.println(c);
    }

    public KthElement(){};

    public Comparable kth(Comparable[] list, int k) {
	
        return null;
    }


    private int partition(Comparable[] list, int low, int high) {
        Comparable pivot = list[(int) (Math.random() * (high - low + 1) + low)];
        int bot = low - 1;
        int top = high + 1;

        while (bot < top) {
            while (list[--top].compareTo(pivot) > 0);
            while (list[++bot].compareTo(pivot) < 0);
            if (bot >= top) {
                return top;
            }
            Comparable temp = list[bot];
            list[bot] = list[top];
            list[top] = temp;
        }
        return 0;
    }
}
