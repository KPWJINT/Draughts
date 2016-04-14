import java.awt.Image;
import java.awt.geom.Point2D;

public class Square {
		private final int ID;
		private Piece piece;
		private static Image image = null;
		private Point2D point;
		
		public Square(Piece piece, int ID)
		{
			this.piece=piece;
			this.ID=ID;
		}

		public static void setImage(Image i)
		{
			image=i;
		}
		public Image getImage()
		{
			return image;
		}
		public void setPiece(Piece piece)
		{
			this.piece=piece;
		}
		public Piece getPiece()
		{
			return piece;
		}
		public int getID()
		{
			return ID;
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
