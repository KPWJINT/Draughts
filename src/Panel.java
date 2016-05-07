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
	Image image_black;
	Image image_white;
	Image image_trace;
	Image image;
	Image image_tip;
	
	Piece piece;
	Board board;
	MyRules rules;
	int  picture_size;
	
	double x_trace;
	double y_trace;
	
	ArrayList<Square> available_square;

	
	public Panel(Board board){

		super();
		addMouseListener(this);
		addMouseMotionListener(this);

		this.board = board;
		rules=new MyRules();
		available_square=new ArrayList<Square>();
			
		File file=new File("C:/Users/Anna/Desktop/warcaby/1.png");
		File file2=new File("C:/Users/Anna/Desktop/warcaby/czarny.png");
		File file3=new File("C:/Users/Anna/Desktop/warcaby/bialy.png");
		File file4=new File("C:/Users/Anna/Desktop/warcaby/2.png");
		try{
			image=ImageIO.read(file);
			image_white=ImageIO.read(file3);
			image_black=ImageIO.read(file2);
			image_tip=ImageIO.read(file4);
			this.picture_size=image.getHeight(null)/2;
		}
		catch (IOException e){
			System.err.println("Picture not found 404");
			e.printStackTrace();
		}
		
		/* sets image for each square */
		for(int i=0;i<board.getSquares().length;i++){
			board.getSquares()[i].setImage(image);
		}
		/* sets image for each piece */
		for(int i=0; i<board.getPlayer_white().length;i++){
			board.getPlayer_white()[i].setImage(image_white);
			board.getPlayer_black()[i].setImage(image_black);
		}
	}

	public void paintComponent(java.awt.Graphics g){		
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D )g;
		
		/*draws board*/
		int squares_quantity=0;
		for(int i=0; i<board.getSize(); i++){
			for(int j=0;j<board.getSize();j++){
				if(j%2==0 && i%2!=0 || i%2==0 && j%2!=0){
					g2.drawImage(image, i* picture_size,  j* picture_size,   picture_size,  picture_size, this);
					board.getSquares()[squares_quantity].setPoint(new Point2D.Double(j*picture_size, i*picture_size));
					squares_quantity++;
				}
			}	
		}
			
		/*draws pieces*/
		for(int i=0;i<board.getSquares().length;i++){
			if(board.getSquares()[i].getPiece()!=null){
				Point2D point=board.getSquares()[i].getPoint();
				Image im=board.getSquares()[i].getPiece().getImage();
				g2.drawImage(im, (int)point.getX(), (int)point.getY(),   picture_size,  picture_size, this);
			}	
		}

		//paints image trace
		g2.drawImage(image_trace, (int)x_trace, (int)y_trace, picture_size, picture_size, this);	

		for(int i=0; i<available_square.size() ;i++){
			double x=available_square.get(i).getPoint().getX();
			double y=available_square.get(i).getPoint().getY();
			g2.drawImage(image_tip, (int)x, (int)y, picture_size, picture_size, this);
		}
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

	public Piece findPiece(Point2D p){
		int x=getXlocation(p.getX());
		int y=getYlocation(p.getY());
		Point2D point=new Point2D.Double(x, y);

		for(int i=0;i<board.getSquares().length; i++){
			if(board.getSquares()[i].getPiece()!=null && board.getSquares()[i].getPoint().equals(point)){
				piece=board.getSquares()[i].getPiece();
				board.getSquares()[i].setPiece(null);
				findFields(board.getSquares()[i], piece, i);
			}
		}
	
		return piece;
	}
	
	public ArrayList findFields(Square s, Piece piece, int square_index){
		
		rules.findPotentialField(board.getSquares(), piece,square_index);
		available_square.addAll(rules.square_list);
		rules.square_list.removeAll(available_square);
		repaint();
		return available_square;
	}
	
	public void putPiece(Point2D p){
		int x=getXlocation(p.getX());
		int y=getYlocation(p.getY());
		Point2D point=new Point2D.Double(x, y);
		
		for(int i=0;i<board.getSquares().length; i++){
			if(board.getSquares()[i].getPoint().equals(point)){
				board.getSquares()[i].setPiece(piece);
				repaint();
				piece=null;
			}
		}
	}
	
	public void tracePiece(Point2D p){
		x_trace=p.getX()-picture_size/2;
		y_trace=p.getY()-picture_size/2;
		image_trace=piece.getImage();
		repaint();

	}
	
	@Override
	public void mouseClicked(MouseEvent e){}


	@Override
	public void mousePressed(MouseEvent e){
		
		findPiece(e.getPoint());	
		
	}

	@Override
	public void mouseReleased(MouseEvent e){
		image_trace=null;
		putPiece(e.getPoint());
		available_square.removeAll(available_square);
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
		tracePiece(e.getPoint());	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}