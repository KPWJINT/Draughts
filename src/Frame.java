import javax.swing.JFrame;

public class Frame extends JFrame{

	public Frame(Board board){
		super("ramka z kwadratami");
		add(new Panel(board));
		
		//setSize(1000,1000);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}