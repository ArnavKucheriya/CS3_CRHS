import java.util.PriorityQueue;
import java.util.Queue;

public class Que {
    public static void main(String[] args) {
        Queue<Double> myPQueue = new PriorityQueue<>();
        myPQueue.offer(1.414);
        myPQueue.offer(1.5);
        myPQueue.offer(3.1415);
        myPQueue.offer(7.2);
        myPQueue.offer(2.7);
        while(!myPQueue.isEmpty())
            System.out.print(myPQueue.remove()+ " ");
    }
}
