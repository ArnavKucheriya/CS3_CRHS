import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Inversions {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("IntegerArray.txt"));
        List<Integer> list1 = Arrays.asList(90, 40, 20, 30, 10, 67);
        List<Integer> list2 = Arrays.asList(3, 15, 61, 11, 7, 9, 2);
        List<Integer> list3 = scanner.tokens().map(Integer::parseInt).collect(Collectors.toList());
        long count1 = countInversions(list1);
        long count2 = countInversions(list2);
        long count3 = countInversions(list3);
        System.out.println(list1);
        System.out.println(count1);
        System.out.println(list2);
        System.out.println(count2);
        System.out.println(count3);
    }

    static <T extends Comparable<T>> long countInversions(List<T> list) {
        int count = 0;
        if (list.size() == 0 || list.size() == 1)
            return 0;

        List<T> left = list.subList(0, list.size() / 2);
        List<T> right = list.subList(list.size()/2, list.size());
        count += countInversions(left);
        count += countInversions(right);
        return count + mergeSorted(left, right, list);
    }

    static <T extends Comparable<T>> long getNumberOfInversions(T[] list) {
        return countInversions(Arrays.asList(list));
    }

    static <T extends Comparable<T>> long mergeSorted(List<T> left, List<T> right, List<T> list) {
        List<T> tmpLeft = new ArrayList<>(left);
        List<T> tmpRight = new ArrayList<>(right);
        int k = 0, i = 0, j = 0;
        int partition = list.size() / 2;
        long count = 0;
        while (i < tmpLeft.size() && j < tmpRight.size()) {
            if (tmpLeft.get(i).compareTo(tmpRight.get(j)) <= 0) {
                list.set(k, tmpLeft.get(i++));
            } else {
                list.set(k, tmpRight.get(j));
                count += partition + j++ - k;
            }
            k++;
        }

        while (i < tmpLeft.size()) {
            list.set(k++, tmpLeft.get(i++));
        }

        while (j < tmpRight.size()) {
            list.set(k++, tmpRight.get(j++));
        }
        return count;
    }

    static long mergeSorted(int[] ary){
        List<Integer> list = Arrays.stream(ary).boxed().collect(Collectors.toList());
        List<Integer> listLeft = list.subList(0, list.size() / 2);
        List<Integer> listRight = list.subList(list.size()/2, list.size());
        return mergeSorted(listLeft, listRight, list);
    }
}