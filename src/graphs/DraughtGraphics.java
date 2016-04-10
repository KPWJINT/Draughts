package graphs;
import java.awt.EventQueue;

public class DraughtGraphics
{
	final static int sizeOfBoard=8;
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() 
			{
				new DraughtsFrame(sizeOfBoard);
			}
		});
	}
}
