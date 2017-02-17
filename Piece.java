public class Piece {
	String COLOR;
	boolean isKing;
	
	public Piece(String color) {
		COLOR = color;
	}
	public void promote() {
		isKing = true;
	} 
    public String colorToString() {
        if(COLOR == "red") {
            return "R";
        } else {
            return "B";
        }
    }
}
