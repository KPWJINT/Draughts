package graphs;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class DraughtsFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	final static int sizeOfBoard=4;
	BoardPanel panel;

	public DraughtsFrame(int sizeOfBoard)
	{		
		super("Draughts");
		JPanel panel = new BoardPanel(sizeOfBoard);
		setSize(sizeOfBoard*25+50, sizeOfBoard*25+70);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = getContentPane();
		container.add(panel);
		setVisible(true);
	}
}

