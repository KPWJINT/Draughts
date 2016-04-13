
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Graphics extends JPanel  implements MouseListener, MouseMotionListener
{
	Image image;
	Image image_black;
	Image image_white;
	Image image_trace;
	
	Piece piece;
	Board board;
	int quantity;
	int size;
	Point2D point;
	
	int x;
	int y;
	double x_trace;
	double y_trace;
	
	
	
	public Graphics(int quantity)
	{
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
		
		this.quantity=quantity;
		File file=new File("C:/Users/Anna/Desktop/1.png");
		File file2=new File("C:/Users/Anna/Desktop/warcaby/czarny.png");
		File file3=new File("C:/Users/Anna/Desktop/warcaby/bialy.png");
		try
		{
			image=ImageIO.read(file);
			image_white=ImageIO.read(file3);
			image_black=ImageIO.read(file2);
			size=image.getHeight(null)/2;
			

		}
		catch (IOException e)
		{
			System.err.println("Blad odczytu obrazka");
			e.printStackTrace();
		}
		
		board=new Board(quantity, image, size, size);
	}

	public void paintComponent(java.awt.Graphics g) 
	{		
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D )g;
		
		
		
		for(int i=0; i<board.getTable().length; i++)
		{
			for(int j=0; j<board.getTable()[i].length; j++)
			{
				if(i%2==0 && j%2!=0 || i%2!=0 && j%2==0)
				{
					g2.drawImage(image, i*(int)size,  j*(int)size,  (int)size, (int)size, this);
					board.getTable()[i][j].setImage(image);
					board.getTable()[i][j].setPoint(new Point2D.Double(i*(int)size, j*(int)size));
				}
				else
					board.getTable()[i][j].setPoint(new Point2D.Double(i*(int)size, j*(int)size));
			}
		}

		for(int i=0; i<board.getTable().length; i++)
		{
			for(int j=0; j<board.getTable()[i].length; j++)
			{
				if(board.getTable()[i][j].getPiece()!=null)
				{
					if(board.getTable()[i][j].getPiece().getPlayer().equals(OWNER.WHITE))
					{
						g2.drawImage(image_white, i*(int)size,  j*(int)size,  (int)size, (int)size, this);	
						board.getTable()[i][j].getPiece().setLocation(new Point2D.Double(i*(int)size, j*(int)size));
						board.getTable()[i][j].getPiece().setImage(image_white);
					}
					if(board.getTable()[i][j].getPiece().getPlayer().equals(OWNER.BLACK))
					{
						g2.drawImage(image_black, i*(int)size,  j*(int)size,  (int)size, (int)size, this);	
						board.getTable()[i][j].getPiece().setLocation(new Point2D.Double(i*(int)size, j*(int)size));
						board.getTable()[i][j].getPiece().setImage(image_black);
					}

				}
			}
		}
		g2.drawImage(image_trace, (int)x_trace, (int)y_trace, (int)size, (int)size, this);		
	
	}

	
	public int getXlocation(double xlocation)
	{
		for(int i=0; i<quantity; i++)
		{
			if(xlocation>(i*size))
			{
				x=i*size;
			}	
		}
		return x;
	}
	
	public int getYlocation(double ylocation)
	{
		for(int i=0; i<quantity; i++)
		{
			if(ylocation>(i*size))
				y=i*size;
		}
		return y;
	}

	public Piece findPiece(Point2D p)
	{
		x=getXlocation(p.getX());
		y=getYlocation(p.getY());
		
		Point2D point=new Point2D.Double(x, y);
		
		for(int i=0;i<board.getTable().length; i++)
		{
			for(int j=0; j<board.getTable()[i].length; j++)
			{
				if(board.getTable()[i][j].getPiece()!=null)
				{
					if(board.getTable()[i][j].getPiece().getPoint().equals(point))
					{
						piece=board.getTable()[i][j].getPiece();
						board.getTable()[i][j].setPiece(null);
					} 
				}
			}
		}
		return piece;
	}
	
	
	public void putPiece(Point2D p)
	{
		double x2=getXlocation(p.getX());
		double y2=getYlocation(p.getY());

		Point2D point=new Point2D.Double(x2, y2);
		for(int i=0;i<board.getTable().length; i++)
		{
			for(int j=0; j<board.getTable()[i].length; j++)
			{
				if(board.getTable()[i][j].getPoint().equals(point))
				{
					piece.setLocation(new Point2D.Double(x2, y2));
					board.getTable()[i][j].setPiece(piece);
					repaint();
					piece=null;
				}
			}
		}
		
	}
	
	public void tracePiece(Point2D p)
	{
		x_trace=p.getX()-size/2;
		y_trace=p.getY()-size/2;
		image_trace=piece.getImage();
		
		repaint();
		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) 
	{

	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		
		findPiece(e.getPoint());	
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		image_trace=null;
		putPiece(e.getPoint());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		tracePiece(e.getPoint());
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
		
}
