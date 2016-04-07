
public class Squares {
	
	private final int ID;
	private Piece piece;
	private int width;
	private int height;
	
	public Squares(int ID, Piece piece, int width, int height) {
		this.ID = ID;
		this.piece = piece;
		this.width = width;
		this.height = height;
	} //constructor
	
	public int getID(){ return ID; }
	public Piece getPiece(){ return piece; }
	public void setPiece(Piece piece){ this.piece=piece; }
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	
}
