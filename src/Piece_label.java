import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class Piece_label extends JLabel{
	Image imageBlack;
	Image imageWhite;
	Board b;
	
	public Piece_label(Board b, int square_length) {
		this.b = b;

		try{
			imageBlack = ImageIO.read(new File("C:/Users/Sobczak/KPWJINT/Droughts/Graphics/black.png"));
			imageWhite = ImageIO.read(new File("C:/Users/Sobczak/KPWJINT/Droughts/Graphics/white.png"));
			imageBlack = imageBlack.getScaledInstance(square_length, square_length, Image.SCALE_DEFAULT);
			imageWhite = imageWhite.getScaledInstance(square_length, square_length, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
			for(int i = 0; i < b.getSize()*b.getSize()/2; i++) {
				if(b.getSquare(i).getPiece() != null)
					if(b.getSquare(i).getPiece().getOwner() == OWNER.BLACK)
						g.drawImage(imageBlack, b.getSquare(i).getWidth(), b.getSquare(i).getHeight(), null);
					else
						g.drawImage(imageWhite, b.getSquare(i).getWidth(), b.getSquare(i).getHeight(), null);
			}
		}
}
