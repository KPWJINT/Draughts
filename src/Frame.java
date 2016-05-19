import javax.swing.JFrame;

public class Frame extends JFrame{

	public Frame(Board board){
		super("ramka z kwadratami");
		add(new Panel(board, 100)); //set square size
		
		setSize(board.getSize()*105,board.getSize()*105); // set square size
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}