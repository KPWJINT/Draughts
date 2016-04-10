package graphs;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	public final int sizeOfBoard;
	public BoardPanel(int sizeOfBoard) {
		setPreferredSize(new Dimension(sizeOfBoard*25+50, sizeOfBoard*25+50));
		this.sizeOfBoard=sizeOfBoard;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// prostokat
		g2d.drawRect(10, 10, sizeOfBoard*25+10, sizeOfBoard*25+10);
		for(int i=0; i<sizeOfBoard/2; ++i)
		{
			for(int j=0;j<sizeOfBoard/2;++j)
			{
				g2d.fillRect(15+2*j*25, 15+2*i*25, 25, 25);
				g2d.drawRect(15+(2*j+1)*25, 15+2*i*25, 25, 25);
			}
			for(int j=0;j<sizeOfBoard/2;++j)
			{
				g2d.fillRect(15+(2*j+1)*25, 15+(2*i+1)*25, 25, 25);
				g2d.drawRect(15+2*j*25, 15+(2*i+1)*25, 25, 25);
			}
		}



		
	}
}
