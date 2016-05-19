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

public class Panel extends JPanel  implements MouseListener, MouseMotionListener{
	Image image_white;
	Image image_black;
	Image image_trace;
	
	Board board;
	int picture_size;
	
	double x_trace;
	double y_trace;
	
	public Panel(Board board, int square_length){
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
		
		this.board = board;
		this.picture_size = square_length;
		try
		{
			Image image_square = ImageIO.read(new File("Graphics/square.png"));
			image_white = ImageIO.read(new File("Graphics/white.png"));
			image_black = ImageIO.read(new File("Graphics/black.png"));
			
			image_square = image_square.getScaledInstance(square_length, square_length, Image.SCALE_DEFAULT);
			image_white = image_white.getScaledInstance(square_length, square_length, Image.SCALE_DEFAULT);
			image_black = image_black.getScaledInstance(square_length, square_length, Image.SCALE_DEFAULT);
			
			Square.setImage(image_square);		
		}
		catch (IOException e)
		{
			System.err.println("Picture not found 404");
			e.printStackTrace();
		}
		
		// set squares points2D
		double width = 0;
		double height = 0;
		for(int i = 0; i < board.getSize(); i++){
			if(i % 2 == 0)
				width = 0;
			else
				width = 0 + square_length;
			
			for(int j = 0; j < board.getSize()/2; j++){
				board.getSquares()[j + i*(board.getSize()/2)].setPoint(new Point2D.Double(width, height));
				width += (double)square_length*2;
			}
			height += (double)square_length;
		}
		
		// set white pieces points2D
		height = 0;
		for(int i = 0; i < (board.getSize()/2)-1; i++){
			if(i % 2 == 0)
				width = 0;
			else
				width = 0 + square_length;
			
			for(int j = 0; j < board.getSize()/2; j++){
				board.getPlayer_white()[j + i*(board.getSize()/2)].setPoint(new Point2D.Double(width, height));
				width += (double)square_length*2;
			}
			height += (double)square_length;
		}
		
		// set black pieces points2D
		height = (board.getSize()/2+1)*square_length;
		for(int i = board.getSize()/2; i < board.getSize()-1; i++){
			if(i % 2 == 0)
				width = square_length;
			else
				width = 0;
			
			for(int j = 0; j < board.getSize()/2; j++){
				board.getPlayer_black()[j + (i - (board.getSize()/2))*(board.getSize()/2)].setPoint(new Point2D.Double(width, height));
				width += (double)square_length*2;
			}
			height += (double)square_length;
		}
	}
	
	public void paintComponent(java.awt.Graphics g){		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		//draw board
		for(int i = 0; i < board.getSquares().length; i++)
			g2.drawImage(Square.getImage() , (int)board.getSquares()[i].getPoint().getX(),  (int)board.getSquares()[i].getPoint().getY(), this);
		
		//draw white player pieces
		for(int i = 0; i < board.getPlayer_white().length; i++)
			g2.drawImage(image_white , (int)board.getPlayer_white()[i].getPoint().getX(),  (int)board.getPlayer_white()[i].getPoint().getY(), this);
		
		//draw black player pieces
				for(int i = 0; i < board.getPlayer_black().length; i++)
					g2.drawImage(image_black , (int)board.getPlayer_black()[i].getPoint().getX(),  (int)board.getPlayer_black()[i].getPoint().getY(), this);
	}

	
	public int getXlocation(double xlocation){
		int x = 0;
		for(int i=0; i<board.getSize(); i++)
			if(xlocation>(i*picture_size))
				x=i*picture_size;
		return x;
	}
	
	public int getYlocation(double ylocation){
		int y = 0;
		for(int i=0; i<board.getSize(); i++)
			if(ylocation>(i*picture_size))
				y=i*picture_size;
		return y;
	}

	//public Piece findPiece(Point2D p){}
	
	//public void putPiece(Point2D p){}
	
	//public void tracePiece(Point2D p){}
	
	@Override
	public void mouseClicked(MouseEvent e){}


	@Override
	public void mousePressed(MouseEvent e) {
	//	findPiece(e.getPoint());	
	}

	@Override
	public void mouseReleased(MouseEvent e){
	//	image_trace=null;
	//	putPiece(e.getPoint());
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
	public void mouseDragged(MouseEvent e) {
	//	tracePiece(e.getPoint());	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}