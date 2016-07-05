package mechanics;

public class Square {
	private Piece piece;
	private final int squareID;
	public Square(int squareID, Piece piece)
	{
		this.piece= piece;
		this.squareID=squareID;
	}
	
	public Piece getPiece(){return piece;}
	public int getSquareID(){return squareID;}
	
	public void setPiece(Piece piece){this.piece=piece;}
}
