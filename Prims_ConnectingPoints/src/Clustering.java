import java.util.*;

public class Clustering {
    public static double clustering(int[] x, int[] y, int k) {
        PriorityQueue<EdgeCluster> edges = edges(x, y);
        WeightedQuickUnionPathCompressionUF uf = new WeightedQuickUnionPathCompressionUF(x.length);

        if (k >= x.length)
            return edges.peek().dist;
        else if (k <= 1)
            return 0;

        while (uf.count() > k) {
            EdgeCluster e = edges.poll();
            if (uf.find(e.u) != uf.find(e.v))
                uf.union(e.u, e.v);
        }

        EdgeCluster minD;
        do {
            minD = edges.poll();
        } while (uf.find(minD.u) == uf.find(minD.v));

        return minD.dist;
    }

    private static PriorityQueue<EdgeCluster> edges(int[] x, int[] y) {
        PriorityQueue<EdgeCluster> edges = new PriorityQueue<>();
        for (int i = 0; i < x.length; i++)
            for (int j = i + 1; j < x.length; j++)
                edges.add(new EdgeCluster(x[i], y[i], x[j], y[j], i, j));
        return edges;
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
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }
}

class EdgeCluster implements Comparable<EdgeCluster> {
    int u; // index of point u
    int v; // index of point v
    double dist;

    public EdgeCluster(int x1, int y1, int x2, int y2, int u, int v) {
        this.u = u;
        this.v = v;
        this.dist = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    @Override
    public int compareTo(EdgeCluster o) {
        return Double.compare(this.dist, o.dist);
    }
}

class WeightedQuickUnionPathCompressionUF {
    private int[] parent;  // parent[i] = parent of i
    private int[] size;    // size[i] = number of sites in tree rooted at i
    // Note: not necessarily correct if i is not a root node
    private int count;     // number of components

    /**
     * Initializes an empty union-find data structure with
     * {@code n} elements {@code 0} through {@code n-1}.
     * Initially, each element is in its own set.
     *
     * @param  n the number of elements
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public WeightedQuickUnionPathCompressionUF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Returns the number of sets.
     *
     * @return the number of sets (between {@code 1} and {@code n})
     */
    public int count() {
        return count;
    }


    /**
     * Returns the canonical element of the set containing element {@code p}.
     *
     * @param  p an element
     * @return the canonical element of the set containing {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        validate(p);
        int root = p;
        while (root != parent[root])
            root = parent[root];
        while (p != root) {
            int newp = parent[p];
            parent[p] = root;
            p = newp;
        }
        return root;
    }

    /**
     * Returns true if the two elements are in the same set.
     *
     * @param  p one element
     * @param  q the other element
     * @return {@code true} if {@code p} and {@code q} are in the same set;
     *         {@code false} otherwise
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     * @deprecated Replace with two calls to {@link #find(int)}.
     */
    @Deprecated
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    /**
     * Merges the set containing element {@code p} with the set
     * containing element {@code q}.
     *
     * @param  p one element
     * @param  q the other element
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }
}