import Graphics.*;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Draughts game");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Board b = new Board(10, (int)tk.getScreenSize().getWidth(), (int)tk.getScreenSize().getHeight());
//		Panel[] panel = new Panel[b.getSize()*b.getSize()];
		JLabel[] label = new JLabel[b.getSize()*b.getSize()/2];
		int square_length = tk.getScreenSize().height/b.getSize();
		for(int i = 0; i < label.length; i++) {
			label[i] = new JLabel();
			label[i].setIcon(new ImageIcon("C:/Users/Sobczak/KPWJINT/Droughts/Graphics/example.png"));
			label[i].setBounds(b.getSquare(i).getWidth(), b.getSquare(i).getHeight(), square_length, square_length);
			frame.add(label[i]);
		}
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);

	}
}
