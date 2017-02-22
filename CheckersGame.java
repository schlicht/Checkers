public class CheckersGame {

	static Board board = new Board(true);
	static HumanPlayer player1 = new HumanPlayer("red", board);
	static AiPlayer player2 = new AiPlayer("black", board);
	static boolean isGameOver = false;
	static void nextMove() {
		System.out.println("Next Move");
		if(board.player1Turn) {
			player1.move();
		} else {
			player2.move();
		}
	}

	static boolean isGameOver() {
		boolean isGameOver = false;
		if(!board.areRedPieces()) {
			isGameOver = true;
			System.out.println("Balck Wins!");
		} else if(!board.areBlackPieces()) {
			isGameOver = true;
			System.out.println("Red Wins!");
		}
		return isGameOver;
	}

	static void GameOver() {
		board.print();
		System.out.println("Game Over");
	}

	public static void main(String[] args) {
		board.initializeBoard();
		do {
			board.print();
			nextMove();
			board.player1Turn = !board.player1Turn;
			isGameOver = isGameOver();
		} while(!isGameOver);
		GameOver();
	}
}
