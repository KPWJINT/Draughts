
public class Squares {
	
	private final int ID;
	private Piece piece;
	
	public Squares(int ID, Piece piece) {
		this.ID = ID;
		this.piece = piece;
	} //constructor
	
	public int getID(){ return ID; }
	public Piece getPiece(){ return piece; }
	public void setPiece(Piece piece){ this.piece=piece; }
	
}
