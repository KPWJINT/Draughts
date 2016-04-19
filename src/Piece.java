import java.awt.Image;
import java.awt.geom.Point2D;

public class Piece {
	private final int ID;
	private final OWNER owner;
	private PIECE_TYPE type;
	//private Image image;
	private Point2D point;
	
	public Piece(int ID, OWNER owner, PIECE_TYPE type, Point2D point) {
			this.ID = ID;
			this.owner = owner;
			this.type = type;
			this.point = point;
		} // constructor
	
	public int getID() { return ID; }
	public OWNER getOwner() { return owner; }
	public PIECE_TYPE getType() { return type; }
	public void setType(PIECE_TYPE type){ this.type = type; }
	public Point2D getPoint(){ return point; }
	public void setPoint(Point2D point){ this.point = point; }
}