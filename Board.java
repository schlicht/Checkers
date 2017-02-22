import java.util.ArrayList;

public class Board {
	Piece[][] Pieces;
	boolean player1Turn;
	public Board(boolean player1Turn) {
		Pieces = new Piece[8][8];
		this.player1Turn = player1Turn;
	}


	public void initializeBoard() {
		for(int row = 0; row < 4; row++) {
			Pieces[0][2*row] = new Piece("red");
			Pieces[1][2*row+1] = new Piece("red");
			Pieces[2][2*row] = new Piece("red");

			Pieces[5][2*row+1] = new Piece("black");
			Pieces[6][2*row] = new Piece("black");
			Pieces[7][2*row+1] = new Piece("black");
		}
	}

	public void print() {
		for(int col = 7; col >= 0; col--) {
			System.out.println("  ________________");
			System.out.print(col+1);
			for(int row = 0; row < 8; row++) {
				System.out.print("|");
				if(Pieces[col][row] != null) {
					System.out.print(Pieces[col][row].colorToString());
				} else {
					System.out.print(" ");
				}
			}
			System.out.println("|");
		}
		System.out.println("  1 2 3 4 5 6 7 8");
		System.out.println();
	}

	public boolean isValidMove(int[][] move, String color) {
		if(!isOnBoard(move[0])) return false;
		if(!isOnBoard(move[1])) return false;
		if(Pieces[move[1][0]][move[1][1]] != null) return false;
		Piece piece = Pieces[move[0][0]][move[0][1]];
		if(piece == null) return false;
		String oppColor;
		if((piece.COLOR).equals("red")) {
			oppColor = "black";
		} else {
			oppColor = "red";
		}
		if(color != piece.COLOR) return false;

		if(piece.isKing || piece.COLOR == "red") {
			if((move[1][0] == move[0][0] + 1) && (Math.abs(move[1][1] - move[0][1]) == 1) 
				&& isVacant(move[1])) return true;
			if((move[1][0] == move[0][0] + 2) && (Math.abs(move[1][1] - move[0][1]) == 2) 
				&& Pieces[move[0][0]+1][(move[0][1]+move[1][1])/2] != null 
				&& Pieces[move[0][0]+1][(move[0][1]+move[1][1])/2].COLOR.equals(oppColor)) {
			return true;
		}
	}
		if(piece.isKing || piece.COLOR == "black") {
			if((move[1][0] == move[0][0] - 1) && (Math.abs(move[1][1] - move[0][1]) == 1) 
				&& isVacant(move[1])) return true;
			if((move[1][0] == move[0][0] -2) && (Math.abs(move[1][1] - move[0][1]) == 2) 
				&& Pieces[move[0][0]-1][(move[0][1]+move[1][1])/2] != null 
				&& Pieces[move[0][0]-1][(move[0][1]+move[1][1])/2].COLOR.equals(oppColor)) {

			return true;
		}
		}
		return false;
	}

	public boolean isVacant(int[] location) {
		return Pieces[location[0]][location[1]] == null;
	}

	public boolean isOnBoard(int[] location) {
		if(location[0] < 0 || location[0] > 7) return false;
		if(location[1] < 0 || location[1] > 7) return false;
		return true;
	}

	public void move(int[][] move) {
		if(move == null) return;
		Pieces[move[1][0]][move[1][1]] = Pieces[move[0][0]][move[0][1]];
		if(move[1][0] == 0 || move[1][0] == 7) Pieces[move[1][0]][move[1][1]].promote();
		if(Math.abs(move[0][0] - move[1][0])==2) {
			Pieces[(move[0][0]+move[1][0])/2][(move[0][1]+move[1][1])/2] = null;
			this.player1Turn = !this.player1Turn;
		}
		remove(move[0]);
	}

	public void remove(int[] at) {
		Pieces[at[0]][at[1]] = null;
	}

	public boolean areBlackPieces() {
		for(int col = 0; col < 8; col++) {
			for(int row = 0; row < 8; row++) {
				if(Pieces[col][row] != null && Pieces[col][row].COLOR == "black") {
					return true;
				}
			}
		}
		return false;
	}

	public boolean areRedPieces() {
		for(int col = 0; col < 8; col++) {
			for(int row = 0; row < 8; row++) {
				if(Pieces[col][row] != null && Pieces[col][row].COLOR == "red") {
					return true;
				}
			}
		}
		return false;
	}



	public Board copy() {
		Board b = new Board(this.player1Turn);
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				if(Pieces[row][col] != null) {
					b.Pieces[row][col]= new Piece(this.Pieces[row][col].COLOR); 
					b.Pieces[row][col].isKing = this.Pieces[row][col].isKing;
				}
			}
		}
		return b;
	}

	public ArrayList<int[][]> getValidMoves(String color) {
		ArrayList<int[][]> moves = new ArrayList<int[][]>();
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				Piece piece = Pieces[row][col];
				if(piece != null) {
					if(piece.COLOR.equals(color)) {
						//int[][] move;
						for(int dist = 1; dist <=2; dist++) {
							int[][] move1 = new int[][]{{row, col}, {row + dist, col + dist}};
							if(this.isValidMove(move1, color)) {
								moves.add(move1);
							}
							int[][] move2 = new int[][]{{row, col}, {row - dist, col + dist}};
							if(this.isValidMove(move2, color)) {
								moves.add(move2);
							}
							int[][] move3 = new int[][]{{row, col}, {row + dist, col - dist}};
							if(this.isValidMove(move3, color)) {
								moves.add(move3);
							}
							int[][] move4 = new int[][]{{row, col}, {row - dist, col - dist}};
							if(this.isValidMove(move4, color)) {
								moves.add(move4);
							}
						}
					}
				}
			}
		}
		return moves;
	}

	final int GAMEWON = 100;
	final int PIECE = 2;
	final int KING = 5;
	final int ONEDGE = 1;
	final int ONBACKROW = 5;

	public int score() {
		int score = 0;
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				Piece piece = Pieces[row][col];
					if(piece != null) {
					int colorSign;
					if(piece.COLOR == "red") {
						colorSign = 1;
					} else {
						colorSign = -1;
					}
					score += colorSign*PIECE;
					if(piece.isKing) score += colorSign*KING;
					if(col == 0 || col == 8) score+= colorSign*ONEDGE;
					if((piece.COLOR.equals("red") && row == 0) || (piece.COLOR.equals("black") && row == 8)) {
						score += colorSign*ONBACKROW;
					}
				}
			}
		}
		if(!areBlackPieces()) {
			score += GAMEWON;
		} else if(!areRedPieces()) {
			score -= GAMEWON;
		}
		return score;
	}

}
