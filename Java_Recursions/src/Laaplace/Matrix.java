package Laaplace;

public class Matrix {
    private int[][] matrix;

    public Matrix(int n) {
        matrix = new int[n][n];
    }

    public Matrix(int[][] matrix) {
        if (matrix.length != matrix[0].length)
            throw new IllegalStateException();
        this.matrix = matrix;
    }

    static public int determinant(int[][] matrix) {
        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
        }
        else if (matrix.length == 1) {
            return matrix[0][0];
        }
        int result = 0;
        for (int c = 0; c < matrix[0].length; c++) {
            if (c % 2 == 0)
                result += matrix[0][c] * determinant(eliminateEntry(c, matrix));
            else
                result -= matrix[0][c] * determinant(eliminateEntry(c, matrix));
        }
        return result;
    }

    /**
     * from a given matrix and the entry at nth position of the collums, return the matrix from eliminating all the
     * elements in the nth collumn and nth row.
     *
     * @param n
     * @param matrix
     * @return the new matrix has matrix.length-1 rows and matrix[0].length collumns
     */
    static int[][] eliminateEntry(int n, int[][] matrix) {
        int[][] res = new int[matrix.length-1][matrix[0].length-1];
        for (int r = 1; r < matrix.length; r++) {
            for (int c = 0; c < n; c++) {
                res[r - 1][c] = matrix[r][c];
            }
        }
        for (int r = 1; r < matrix.length; r++) {
            for (int c = n+1; c < matrix.length; c++) {
                res[r - 1][c-1] = matrix[r][c];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int res = determinant(new int[][]{{2, 5, 3}, {1, -2, -1}, {1, 3, 4}});
        // should return -20
        System.out.println(res);

        res = determinant(new int[][]{{1, 3}, {2, 5}});
        // should return -1
        System.out.println(res);

        res = determinant(new int[][]{{3}});
        // should return 3
        System.out.println(res);

        res = determinant(new int[][]{{5, 3, 2, 5, 3}, {31, 5, 81, -2, -1}, {0, 3, 1, 3, 4}, {0, 8, 5, 3, 3}, {-1, 3, -8, 3, 3}});
        // should return 9554
        System.out.println(res);

        res = determinant(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        // should return 0(the matrix isn’t invertible and either has no solutions or infinitely many)
        System.out.println(res);

        res = determinant(new int[][]{{5, 3, 2, 5, 3, 1}, {1, 5, 1, -2, -1, 2}, {1, 3, 1, 3, 4, 1}, {0, 8, 5, 3, 3, 0}, {-1, 3, 4, 3, 3, 2}, {4, 3, 1, 3, 3, 2}});
        // should return 621
        System.out.println(res);

    }
}
