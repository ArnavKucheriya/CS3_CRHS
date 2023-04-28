import java.util.*;
import java.awt.Point;
import java.awt.geom.Point2D;

public class ConnectingPoints {
    public static double minimumDistance(int[] x, int[] y) {
        double result = 0d;

        PriorityQueue<EdgePoints> pq = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();

        int curr = 0;
        while (visited.size() < x.length) {
            for (int adj = 0; adj < x.length; adj++)
                pq.add(new EdgePoints(x[curr], y[curr], x[adj], y[adj], adj));

            EdgePoints edge;
            do
                edge = pq.poll();
            while (visited.contains(edge.adjIndex));

            result += edge.dist;
            curr = edge.adjIndex;

            visited.add(curr);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }
}

class EdgePoints implements Comparable<EdgePoints> {
    int adjIndex;
    double dist;

    public EdgePoints(int x1, int y1, int x2, int y2, int adjIndex) {
        this.adjIndex = adjIndex;
        this.dist = Point2D.distance(x1, y1, x2, y2);
    }

    @Override
    public int compareTo(EdgePoints o) {
        return Double.compare(this.dist, o.dist);
    }
}