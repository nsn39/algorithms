/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solver {
    // Defining a search Node for our solver
    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int movesTillNow;
        private final SearchNode prev;

        public SearchNode(Board board, int movesTillNow, SearchNode prev) {
            this.board = board;
            this.movesTillNow = movesTillNow;
            this.prev = prev;
        }

        public int compareTo(SearchNode anotherOne) {
            return Integer.compare(manhattanCF(), anotherOne.manhattanCF());
        }

        public int manhattanCF() {
            return board.manhattan() + movesTillNow;
        }
    }

    private SearchNode finalNode;
    private boolean isBoardSolvable;


    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("The argument provided in the constructor is null.");
        }

        Board twinBoard = initial.twin();
        SearchNode firstGameNode = new SearchNode(initial, 0, null);
        SearchNode secondGameNode = new SearchNode(twinBoard, 0, null);
        MinPQ<SearchNode> firstGame = new MinPQ<>();
        MinPQ<SearchNode> secondGame = new MinPQ<>();
        firstGame.insert(firstGameNode);
        secondGame.insert(secondGameNode);

        while (true) {
            SearchNode firstTree = firstGame.delMin();
            SearchNode secondTree = secondGame.delMin();
            if (firstTree.board.isGoal() || secondTree.board.isGoal()) {
                if (firstTree.board.isGoal()) {
                    isBoardSolvable = true;
                    finalNode = firstTree;
                }
                break;
            }

            for (Board child : firstTree.board.neighbors()) {
                if (firstTree.prev != null) {
                    if (!firstTree.prev.board.equals(child)) {
                        // StdOut.println("Added board" + child.toString());
                        firstGame.insert(new SearchNode(child, firstTree.movesTillNow + 1,
                                                        firstTree));
                    }
                }
                else {
                    firstGame.insert(new SearchNode(child, firstTree.movesTillNow + 1,
                                                    firstTree));
                }
            }

            for (Board child : secondTree.board.neighbors()) {
                if (secondTree.prev != null) {
                    if (!secondTree.prev.board.equals(child)) {
                        secondGame.insert(new SearchNode(child, secondTree.movesTillNow + 1,
                                                         secondTree));
                    }
                }
                else {
                    secondGame.insert(new SearchNode(child, secondTree.movesTillNow + 1,
                                                     secondTree));
                }
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isBoardSolvable;
    }


    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) return -1;
        else {
            return finalNode.movesTillNow;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        SearchNode curNode = finalNode;

        List<Board> ans = new ArrayList<>();
        while (true) {
            ans.add(curNode.board);
            if (curNode.prev != null) {
                curNode = curNode.prev;
            }
            else {
                break;
            }
        }
        Collections.reverse(ans);
        return ans;
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
