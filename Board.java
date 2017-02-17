public class Board {
	private static Board instance = null;
	Piece[][] Pieces;
	
	private Board() {
		Pieces = new Piece[8][8];
	}

	public static Board getBoard() {
		if(instance == null) {
			instance = new Board();
		}
		return instance;
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
			System.out.println(" ________________");
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
			Pieces[move[0][0]+1][(move[0][1]+move[1][1])/2] = null;
			CheckersGame.player1Turn = !CheckersGame.player1Turn;
			return true;
		}
	}
		if(piece.isKing || piece.COLOR == "black") {
			if((move[1][0] == move[0][0] - 1) && (Math.abs(move[1][1] - move[0][1]) == 1) 
				&& isVacant(move[1])) return true;
			if((move[1][0] == move[0][0] -2) && (Math.abs(move[1][1] - move[0][1]) == 2) 
				&& Pieces[move[0][0]-1][(move[0][1]+move[1][1])/2] != null 
				&& Pieces[move[0][0]-1][(move[0][1]+move[1][1])/2].COLOR.equals(oppColor)) {
			Pieces[move[0][0]-1][(move[0][1]+move[1][1])/2] = null;
			CheckersGame.player1Turn = !CheckersGame.player1Turn;
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
		Pieces[move[1][0]][move[1][1]] = Pieces[move[0][0]][move[0][1]];
		if(move[1][0] == 0 || move[1][0] == 7) Pieces[move[1][0]][move[1][1]].promote();
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

	public int score() {
		int score = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(Pieces[i][j].COLOR == "red") {
					score += 1;
				} else if(Pieces[i][j].COLOR == "black") {
					score -= 1;
				}
			}
		}
		return score;
	}

}
