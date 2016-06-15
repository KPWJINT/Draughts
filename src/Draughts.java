import java.awt.EventQueue;

public class Draughts {

	public static void main(String[] args)
	{
		int size = 8;					//size of the board must be even
		Board board = new Board(size);
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Frame(board);
			}
		});
	}
}
