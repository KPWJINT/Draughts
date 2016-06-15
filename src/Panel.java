import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel  implements MouseListener, MouseMotionListener{
	Image image_white;
	Image image_black;
	Image image_white_king;
	Image image_black_king;
	Image image_trace;
	Image image_avalible;
	Image image_end;
	
	Board board;
	int picture_size;
	
	ArrayList<Square> available_squares = new ArrayList<Square>();
	Piece piece_trace; // new, piece which is traced
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
			image_white_king = ImageIO.read(new File("Graphics/white_king.png"));
			image_black_king = ImageIO.read(new File("Graphics/black_king.png"));
			image_avalible = ImageIO.read(new File("Graphics/avalible.png"));
			image_end = ImageIO.read(new File("Graphics/END.png"));
			
			image_square = image_square.getScaledInstance(square_length, square_length, Image.SCALE_DEFAULT);
			image_white = image_white.getScaledInstance(square_length, square_length, Image.SCALE_DEFAULT);
			image_black = image_black.getScaledInstance(square_length, square_length, Image.SCALE_DEFAULT);
			image_white_king = image_white_king.getScaledInstance(square_length, square_length, Image.SCALE_DEFAULT);
			image_black_king = image_black_king.getScaledInstance(square_length, square_length, Image.SCALE_DEFAULT);
			image_avalible = image_avalible.getScaledInstance(square_length, square_length, Image.SCALE_DEFAULT);
			image_end = image_end.getScaledInstance(square_length*board.getSize(), square_length*board.getSize(), Image.SCALE_DEFAULT);
			
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
		
		//draw board and pieces
		for(int i = 0; i < board.getSquares().length; i++){
			g2.drawImage(Square.getImage() , (int)board.getSquares()[i].getPoint().getX(),  (int)board.getSquares()[i].getPoint().getY(), this);
			if(board.getSquares()[i].getPiece() != null){
				if(board.getSquares()[i].getPiece().getOwner() == OWNER.WHITE)
					if(board.getSquares()[i].getPiece().getType() == PIECE_TYPE.MAN)
						g2.drawImage(image_white , (int)board.getSquares()[i].getPoint().getX(),  (int)board.getSquares()[i].getPoint().getY(), this);
					else
						g2.drawImage(image_white_king , (int)board.getSquares()[i].getPoint().getX(),  (int)board.getSquares()[i].getPoint().getY(), this);
				else
					if(board.getSquares()[i].getPiece().getType() == PIECE_TYPE.MAN)
						g2.drawImage(image_black , (int)board.getSquares()[i].getPoint().getX(),  (int)board.getSquares()[i].getPoint().getY(), this);
					else
						g2.drawImage(image_black_king , (int)board.getSquares()[i].getPoint().getX(),  (int)board.getSquares()[i].getPoint().getY(), this);
			}	
		}
		
		//avalible squares
		for(int i = 0; i < available_squares.size(); i++)
			g2.drawImage(image_avalible,(int)available_squares.get(i).getPoint().getX(), (int)available_squares.get(i).getPoint().getY(), this);
			
		//image trace
		if(piece_trace != null)
				g2.drawImage(image_trace,(int)x_trace, (int)y_trace, this);
		
		if(Rules.is_end_of_the_game(board) && piece_trace == null)
			g2.drawImage(image_end, 0, 0, this);						//set different location?
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
	
	public Piece removePiece(Point2D p){
		int x = getXlocation(p.getX());
		int y = getYlocation(p.getY());
		
		Point2D point = new Point2D.Double(x, y);
		Piece piece = null;
		
		for(int i = 0; i < board.getSquares().length; i++){
			if(board.getSquares()[i] != null && board.getSquares()[i].getPoint().equals(point)){
				piece = board.getSquares()[i].getPiece();
				board.getSquares()[i].setPiece(null);
			}	
		}
		return piece;
	}
	
	public boolean putPiece(Point2D p){
		int x = getXlocation(p.getX());
		int y = getYlocation(p.getY());
		boolean isDone = false;
		
		Point2D point = new Point2D.Double(x, y);
		
		for(int i = 0; i < board.getSquares().length; i++){
			if(board.getSquares()[i] != null && board.getSquares()[i].getPoint().equals(point) && board.getSquares()[i].getPiece() == null){
				board.getSquares()[i].setPiece(piece_trace);
				isDone = true;
			}
		}
		return isDone;
	} //set trace_piece at the square at the specified Point2D
	
	public void tracePiece(Point2D p){
		x_trace = p.getX() - picture_size/2;
		y_trace = p.getY() - picture_size/2;
		if(piece_trace.getOwner() == OWNER.BLACK)
			if(piece_trace.getType() == PIECE_TYPE.MAN)
				image_trace = image_black;
			else
				image_trace = image_black_king;
		else if(piece_trace.getOwner() == OWNER.WHITE)
			if(piece_trace.getType() == PIECE_TYPE.MAN)
				image_trace = image_white;
			else
				image_trace = image_white_king;
			
		repaint();
	}
	
	public Square getSquare(Point2D p){
		Square square = null;
		int x = getXlocation(p.getX());
		int y = getYlocation(p.getY());
		
		Point2D point = new Point2D.Double(x, y);
		for(int i = 0; i < board.getSquares().length; i++){
			if(board.getSquares()[i] != null && board.getSquares()[i].getPoint().equals(point))
				square = board.getSquares()[i];
		}
		return square;
	}
	
	@Override
	public void mouseClicked(MouseEvent e){}


	@Override
	public void mousePressed(MouseEvent e) {
		piece_trace = removePiece(e.getPoint());
		
		if(piece_trace != null){
			piece_trace.setPoint(e.getPoint());		//remember last place
			available_squares.addAll(Rules.availableSquares(board, getSquare(piece_trace.getPoint()), piece_trace));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e){
		if(piece_trace != null && Rules.moveAvalible(board, getSquare(e.getPoint()),getSquare(piece_trace.getPoint()) ,piece_trace)){
			if(!putPiece(e.getPoint()) && piece_trace != null)
				putPiece(piece_trace.getPoint());
		}else if(piece_trace != null)
			putPiece(piece_trace.getPoint());
				
			piece_trace = null;
			available_squares.removeAll(available_squares);
		repaint();
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
		if(piece_trace != null)
		tracePiece(e.getPoint());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}