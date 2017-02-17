
import java.util.Scanner;

public class HumanPlayer extends Player {

	static Board board;	
	final String COLOR;

	public HumanPlayer(String color, Board board) {
		this.COLOR = color;
		this.board = board;
	}
	
	public void move() {
		int[][] move;
		do {
			move = this.getMove();
		} while(!board.isValidMove(move, COLOR));
		board.move(move);
	}
	
	public int[][] getMove() {
		System.out.println("Move from?");
		Scanner moveReader = new Scanner(System.in);
		int[] from = new int[2];
		from[0] = moveReader.nextInt() - 1;
		from[1] = moveReader.nextInt() - 1;
		System.out.println("Move to?");
		int[] to = new int[2];
		to[0] = moveReader.nextInt() - 1;
		to[1] = moveReader.nextInt() - 1;
		int[][] move = {{from[0], from[1]}, {to[0], to[1]}};
		return move;
	}
}
