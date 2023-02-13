
public class KthElement {

    public static void main(String[] args) {
        KthElement test = new KthElement();
        Comparable c = test.kth(new Comparable[] { 9, 5, 3, 2, 1 }, 2);
        System.out.println(c);

        c = test.kth(new Comparable[] { 19, 52, 3, 2, 7, 21 }, 0);
        System.out.println(c);

        c = test.kth(new Comparable[] { 68, 66, 11, 2, 42, 31 }, 5);
        System.out.println(c);

        c = test.kth(new Comparable[] { 90, 40, 20, 30, 10, 67, 100 }, 3);
        System.out.println(c);

        c = test.kth(new Comparable[] { 3, 15, 61, 11, 7, 9, 2 }, 3);
        System.out.println(c);

    }

    public KthElement() {
    };

    public Comparable kth(Comparable[] list, int k) {
        int low = 0;
        int high = list.length - 1;
        int pivot = partition(list, low, high);
        while (pivot != k) {
            if (pivot > k)
                high = pivot - 1;
            else
                low = pivot + 1;
            pivot = partition(list, low, high);
        }
        return list[k];
    }

    private int partition(Comparable[] list, int low, int high) {
        Comparable pivot = list[high];
        int i = low - 1;
        for (int j = low; j <= high; j++) {
            if (list[j].compareTo(pivot) < 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, high, i+1);
        return i+1;
    }

    static void swap(Comparable[] list, int i, int j) {
        Comparable temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }
}