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
	Image image_black;
	Image image_white;
	Image image_trace;
	
	Board board;
	int picture_size;
	
	double x_trace;
	double y_trace;
	
	
	
	public Panel(Board board){
		super();
		addMouseListener(this);
		addMouseMotionListener(this);

		this.board = board;
	
//		File file=new File("C:/Users/Anna/Desktop/1.png");
//		File file2=new File("C:/Users/Anna/Desktop/warcaby/czarny.png");
//		File file3=new File("C:/Users/Anna/Desktop/warcaby/bialy.png");
		try
		{
//			image=ImageIO.read(file);
//			image_white=ImageIO.read(file3);
//			image_black=ImageIO.read(file2);
//			this.picture_size=image.getHeight(null)/2;
		}
		catch (IOException e)
		{
			System.err.println("Picture not found 404");
			e.printStackTrace();
		}
	}

	
	public void paintComponent(java.awt.Graphics g){		
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D )g;
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
