import java.awt.Image;
import java.awt.geom.Point2D;

public class Piece
{
	private PIECE_TYPE piece_type;
	private OWNER player;
	private int id;
	Image image;
	Point2D point;
	
	private double x_location;
	private double y_location; 
	
	public Piece (PIECE_TYPE piece_type, OWNER player, int id)
	{
		this.piece_type=piece_type;
		this.player=player;
		this.id=id;
	}
	public void setLocation(Point2D p)
	{
		point=p;
	}
	public Point2D getPoint()
	{
		return point;
	}
	
	
	public void setImage(Image image)
	{
		this.image=image;
	}
	public Image getImage()
	{
		return image;
	}
	public void setPiece(PIECE_TYPE piece_type)
	{
		this.piece_type=piece_type;
	}

	public void setPlayer(OWNER player)
	{
		this.player=player;
	}
	public void setID(int id)
	{
		this.id=id;
	}
	public PIECE_TYPE getPieceType()
	{
		return piece_type;
	}
	public OWNER getPlayer()
	{
		return player;
	}
	public int getID()
	{
		return id;
	}

	public String toString()
	{
		return player.toString()+piece_type.toString();
	}
}
