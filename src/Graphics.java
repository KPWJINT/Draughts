
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Graphics extends JPanel  implements MouseListener
{
	Image image;
	Image image_black;
	Image image_white;
	Piece piece;
	Board board;
	int quantity;
	int size;
	Point2D point;
	
	int x;
	int y;
	
	public Graphics(int quantity)
	{
		super();
		addMouseListener(this);
		
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
					}
					if(board.getTable()[i][j].getPiece().getPlayer().equals(OWNER.BLACK))
					{
						g2.drawImage(image_black, i*(int)size,  j*(int)size,  (int)size, (int)size, this);	
						board.getTable()[i][j].getPiece().setLocation(new Point2D.Double(i*(int)size, j*(int)size));
					}

				}
			}
		}
	}

	
	public int getXlocation(int xlocation)
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
	
	public int getYlocation(int ylocation)
	{
		for(int i=0; i<quantity; i++)
		{
			if(ylocation>(i*size))
				y=i*size;
		}
		return y;
	}

	public Piece findPawn(Point2D p)
	{
		int x=getXlocation((int)p.getX());
		int y=getYlocation((int)p.getY());

		Point2D point=new Point2D.Double(x, y);
		System.out.println(point);
		
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
	
	
	public void putPawn(Point2D p)
	{
		int x2=getXlocation((int)p.getX());
		int y2=getYlocation((int)p.getY());

		Point2D point=new Point2D.Double(x2, y2);
		System.out.println(point);
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
	

	@Override
	public void mouseClicked(MouseEvent e) 
	{

	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		findPawn(e.getPoint());	
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		putPawn(e.getPoint());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
		
}
/**
if(board.getTable()[i][j].getPawn()!=null && board.getTable()[i][j].getPawn().getPlayer()==Player.WHITE)
				{
					g2.drawImage(image_white, i*(int)size,  j*(int)size,  (int)size, (int)size, this);
					board.getTable()[i][j].getPawn().setLocation(new Point2D.Double(j*(int)size, i*(int)size));
				}
				if(board.getTable()[i][j].getPawn()!=null && board.getTable()[i][j].getPawn().getPlayer()==Player.BLACK)
				{
					g2.drawImage(image_black, i*(int)size,  j*(int)size,  (int)size, (int)size, this);
					board.getTable()[i][j].getPawn().setLocation(new Point2D.Double(j*(int)size, i*(int)size));
				}
				else if(board.getTable()[i][j].getPawn()==null)
					board.getTable()[i][j].setPawn(null);
**/