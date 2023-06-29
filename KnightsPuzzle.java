public class KnightsPuzzle {

    private int boardSize;
    private int[][] board;
    private int[][] moves;
    private int moveCount;

    // constructor
    public KnightsPuzzle(int size) {
        boardSize = size;
        board = new int[boardSize][boardSize];
        // this is every possible knight move in x,y coordinates
        moves = new int[][]{{2, 1}, {1, 2}, {-1, 2}, {-2, 1},
                            {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
        moveCount = 0;
    }

    // call the knightSolver recursive method
    public void solve() {
        if (knightSolver(0, 0, 0)) {
            printSolution();
        } else {
            System.out.println("No solution exists.");
        }
    }

    // check if the move is valid
    private boolean isValidMove(int i, int k) {
        return (i >= 0 && i < boardSize && k >= 0 && k < boardSize && board[i][k] == 0);
    }
        // recursive method to solve the puzzle
        private boolean knightSolver(int i, int k, int count) {
        if (count == boardSize * boardSize) {
            return true; // Found a solution
        }
        // check if the move is valid
        if (isValidMove(i, k)) {
            board[i][k] = ++moveCount; // Place the knight
            for (int[] move : moves) {
                int next_i = i + move[0]; // Calculate next move coordinates
                int next_k = k + move[1];
                // recursively call knightSolver until we have a solution                
                if (knightSolver(next_i, next_k, count + 1)) {
                    return true; // Solution found
                }
            }        
            // backtrack by removing the knight from the board
            board[i][k] = 0; 
            moveCount--;
        }
        // no solution found
        return false;
    }

    // print the solution to console
    private void printSolution() {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.printf("%2d ", cell); // Print the knight's moves
            }
            System.out.println();
        }
    }

    // main method
    public static void main(String[] args) {
        int boardSize = 8; // Set the desired board size here
        // 8 is the max for this PC
        // after 9 the runtime is over 15 seconds and I don't have the patience
        KnightsPuzzle puzzle = new KnightsPuzzle(boardSize);
        puzzle.solve();
    }
}