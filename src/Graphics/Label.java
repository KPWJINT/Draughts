package Graphics;
import java.io.File;

import javax.swing.*;

public class Label {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Some text");
		JLabel label = new JLabel();
		//label.setText("hi hi hi");
		//label.setfont
		label.setIcon(new ImageIcon("C:/Users/Sobczak/KPWJINT/Droughts/Graphics/example.png"));
		frame.add(label);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}

}
