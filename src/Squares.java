import java.awt.Image;
import java.awt.geom.Point2D;

public class Squares {
	private Piece piece;
	private int id;
	Image image;
	int height;
	int width;
	Point2D point;
	
	public Squares(int id)
	{
		this.id=id;
	}
	
	public Squares(Image image)
	{
		this.image=image;
	}
	public Squares(int id, int height, int width)
	{
		this.id=id;
		this.height=height;
		this.width=width;
	}
	
	public void setImage(Image image)
	{
		this.image=image;
	}
	public Image getImage()
	{
		return image;
	}
	public Squares(Piece piece, int id)
	{
		this.piece=piece;
		this.id=id;
	}

	public void setPiece(Piece piece)
	{
		this.piece=piece;
	}
	public void setID(int id)
	{
		this.id=id;
	}
	public Piece getPiece()
	{
		return piece;
	}
	public int getID()
	{
		return id;
	}
	public void setPoint(Point2D p)
	{
		point=p;
	}
	public Point2D getPoint()
	{
		return point;
	}

}
