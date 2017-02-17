public class CheckersGame {

	static Board board = Board.getBoard();
	static HumanPlayer player1 = new HumanPlayer("red", board);
	static HumanPlayer player2 = new HumanPlayer("black", board);
	static boolean player1Turn;

	static void nextMove() {
		if(player1Turn) {
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

	public static void main(String[] args) {
		player1Turn = true;
		board.initializeBoard();
		do {
			board.print();
			nextMove();
			player1Turn = !player1Turn;
		} while(!isGameOver());
		board.print();
		System.out.println("Game Over");
		
	}
}
