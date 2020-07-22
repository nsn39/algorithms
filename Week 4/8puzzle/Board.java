/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private final int[][] numTiles;
    private final int dim;
    private int zeroX, zeroY;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        dim = tiles.length;
        numTiles = new int[dim][dim];

        copy(tiles, numTiles);

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (numTiles[i][j] == 0) {
                    zeroX = i;
                    zeroY = j;
                    return;
                }
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder value = new StringBuilder();
        value.append(dim);
        value.append("\n");
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                value.append(numTiles[i][j]);
                value.append(" ");
            }
            value.append("\n");
        }
        return value.toString();
    }

    // board dimension n
    public int dimension() {
        return dim;
    }

    // number of tiles out of place
    public int hamming() {
        int ans = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dim; j++) {
                if (numTiles[i][j] != 0 && numTiles[i][j] != (i * dim) + j + 1) ans++;
            }
        }
        return ans;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int total = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                int numOnTrial = numTiles[i][j];
                if (numOnTrial != 0) {
                    int xInd;
                    int yInd;
                    if (numOnTrial % dim == 0) {
                        yInd = dim - 1;
                        xInd = (numOnTrial / dim) - 1;
                    }
                    else {
                        xInd = numOnTrial / dim;
                        yInd = (numOnTrial % dim) - 1;
                    }
                    total += Math.abs(i - xInd) + Math.abs(j - yInd);
                }
            }
        }
        return total;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return (hamming() == 0);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        // self check
        if (y == this) {
            return true;
        }

        // Null check
        if (y == null) {
            return false;
        }

        // Type check and cast
        if (getClass() != y.getClass()) {
            return false;
        }

        Board that = (Board) y;
        if (dim != that.dimension()) return false;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (numTiles[i][j] != that.numTiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void copy(int[][] orig, int[][] dup) {
        for (int i = 0; i < dim; i++) {
            dup[i] = Arrays.copyOf(orig[i], dim);
        }
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbourList = new ArrayList<>();

        // Checking for possible neighbours and adding them to the list
        if (zeroX - 1 >= 0) {
            int[][] copyTiles = new int[dim][dim];
            copy(numTiles, copyTiles);
            swap(copyTiles, zeroX, zeroY, zeroX - 1, zeroY);
            neighbourList.add(new Board(copyTiles));
        }

        if (zeroX + 1 < dim) {
            int[][] copyTiles = new int[dim][dim];
            copy(numTiles, copyTiles);
            swap(copyTiles, zeroX, zeroY, zeroX + 1, zeroY);
            neighbourList.add(new Board(copyTiles));
        }

        if (zeroY - 1 >= 0) {
            int[][] copyTiles = new int[dim][dim];
            copy(numTiles, copyTiles);
            swap(copyTiles, zeroX, zeroY, zeroX, zeroY - 1);
            neighbourList.add(new Board(copyTiles));
        }

        if (zeroY + 1 < dim) {
            int[][] copyTiles = new int[dim][dim];
            copy(numTiles, copyTiles);
            swap(copyTiles, zeroX, zeroY, zeroX, zeroY + 1);
            neighbourList.add(new Board(copyTiles));
        }
        return neighbourList;
    }

    private void swap(int[][] a, int a1, int b1, int a2, int b2) {
        int temp = a[a1][b1];
        a[a1][b1] = a[a2][b2];
        a[a2][b2] = temp;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] newTiles = new int[dim][dim];
        copy(numTiles, newTiles);

        if (zeroX == 0) {
            swap(newTiles, 1, 0, 1, 1);
        }
        else {
            swap(newTiles, 0, 0, 0, 1);
        }
        return new Board(newTiles);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        /*        Unit testing on board not required.*/

        int[][] tiles = {
                { 1, 3, 5 },
                { 4, 0, 6 },
                { 7, 8, 2 },
                };
        int[][] tiles2 = {
                { 1, 3, 5 },
                { 4, 0, 6 },
                { 7, 8, 2 },
                };
        Board first = new Board(tiles);
        Board second = new Board(tiles2);
        StdOut.println(first.equals(second));

    }
}
