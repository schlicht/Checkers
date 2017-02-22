import java.util.*;

public class AiPlayer extends Player {

	final String COLOR;
	static Board board;
    final int MAXDEPTH = 3;

	public AiPlayer(String color, Board board) {
		this.COLOR  = color;
		this.board = board;
	}

    public void move() {
        System.out.println("Player 2 moves");
        int[][] move = getMove();
        board.move(move);
    }

    public int[][] getMove() {
        int minScore = Integer.MAX_VALUE;
        int[][] minMove = null;
        ArrayList<int[][]> validMoves = board.getValidMoves("black");
        for(int[][] move : validMoves) {
            Board b = board.copy();
            b.move(move);
            int score = alphaBetaMax(Integer.MIN_VALUE, Integer.MAX_VALUE, MAXDEPTH-1, b);
            if(score < minScore) {
                minScore = score;
                minMove = copyMove(move);
            }
        }

        return minMove;
    }

    public static int[][] copyMove(int[][] move) {
        int[][] copy = new int[][]{{move[0][0], move[0][1]}, {move[1][0], move[1][1]}};
        return copy;
    }

    int alphaBetaMin(int alpha, int beta, int depthLeft, Board b) {
        if(depthLeft == 0) return b.score();
        ArrayList<int[][]> validMoves = b.getValidMoves("black");
        for(int[][] move : validMoves) {
            Board bCopy = b.copy();
            bCopy.move(move);
            int score = alphaBetaMax(alpha, beta, depthLeft - 1, bCopy);
            if(score <= alpha) return alpha;
            if(score < beta) beta = score;
        }
        return beta;
    }

    int alphaBetaMax(int alpha, int beta, int depthLeft, Board b) {
        if(depthLeft == 0) return b.score();
        ArrayList<int[][]> validMoves = b.getValidMoves("red");
        for(int[][] move : validMoves) {
            Board bCopy = b.copy();
            bCopy.move(move);
            int score = alphaBetaMin(alpha, beta, depthLeft - 1, bCopy);
            if(score>=beta) return beta;
            if(score>alpha) alpha=score;
        }
        return alpha;
    }
}
