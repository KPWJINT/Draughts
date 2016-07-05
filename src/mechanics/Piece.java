package mechanics;

import enums.*;


public class Piece {
	private final int ID;
	private PIECE_TYPE type;
	private final OWNER owner; 
	public Piece(PIECE_TYPE type, OWNER owner, int ID){
		this.type=type;
		this.owner=owner;
		this.ID=ID;
	}//constructor
	public int getID(){return ID;}
	public PIECE_TYPE getType(){return type;}
	public OWNER getOwner(){return owner;}
	public void setType(PIECE_TYPE type){this.type=type;}
	
}
