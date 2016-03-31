package draughts;
import mechanics.Board;

public class Draughts {
	public static void main(String[] args)
	{
		Board board= new Board(8);
		int[] tablica= board.connectedTo(23);
		if(board!=null)// testuje czy pobieranie polaczen dziala
		{
			for(int i=0; i<tablica.length;++i)
				System.out.println(tablica[i]);
		}//dla pionkow sprawdzone i dziala dla krolowek jeszcze nie...
	}

}
