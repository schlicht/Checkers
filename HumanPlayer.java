
import java.util.Scanner;

public class HumanPlayer extends Player {

	static Board board;	
	final String COLOR;

	public HumanPlayer(String color, Board board) {
		this.COLOR = color;
		this.board = board;
	}
	
	public void move() {
		System.out.println("Player 1 moves");
		int[][] move;
		do {
			move = this.getMove();
		} while(!board.isValidMove(move, COLOR));
		board.move(move);
	}

	public int[][] getMove() {
		System.out.println("Move?");
		Scanner moveReader = new Scanner(System.in);
		int[] from = new int[2];
		int[] to = new int[2];
		String input = moveReader.nextLine();
		if(input.equals("quit")) {
			CheckersGame.isGameOver =true;
			return null;
		} else {
			from[0] = Character.getNumericValue(input.charAt(0))-1;
			from[1] = Character.getNumericValue(input.charAt(1))-1;

			to[0] = Character.getNumericValue(input.charAt(3))-1;
			to[1] = Character.getNumericValue(input.charAt(4))-1;
			int[][] move = {{from[0], from[1]}, {to[0], to[1]}};
			return move;
		}
	}
}
