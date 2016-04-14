
public class Piece {
	private final int ID;
	private final OWNER owner;
	private PIECE_TYPE type;
	
	public Piece(int ID, OWNER owner, PIECE_TYPE type) {
			this.ID = ID;
			this.owner = owner;
			this.type = type;
		} // constructor
	public int getID() { return ID; }
	public OWNER getOwner() { return owner; }
	public PIECE_TYPE getType() { return type; }
	public void setType(PIECE_TYPE type){ this.type = type; }
}