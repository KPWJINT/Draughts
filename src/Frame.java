import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Draughts game");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Board b = new Board(10, (int)tk.getScreenSize().getWidth(), (int)tk.getScreenSize().getHeight()-200);
		int square_length = (tk.getScreenSize().height-200)/b.getSize();
		//Board_label bl = new Board_label(b, square_length);
		//frame.add(bl);
		
		Piece_label pl = new Piece_label(b, square_length);
		frame.add(pl);
//		Label[] label = new Label[b.getSize()*b.getSize()/2];
//		for(int i = 0; i < label.length; i++) {
//			label[i] = new Label(square_length,b.getSquare(i));
//			label[i].setBounds(b.getSquare(i).getWidth(), b.getSquare(i).getHeight(), square_length, square_length);
//			frame.add(label[i]);
//		}
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
	//	frame.repaint();
	}
}
