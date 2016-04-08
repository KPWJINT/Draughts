package mechanics;

import enums.PIECE_TYPE;
import enums.OWNER; 
@SuppressWarnings("unused")

public interface Rules {
	public default int[] connectedTo(int squareID, int sizeOfBoard, Squares[] board)//polecam przy analizowaniu zrobi� sobie schemat planszy :)
	{
		int[] result;
	if(board[squareID].getPiece().getType()==PIECE_TYPE.MAN)
	{
		int[] result;
			if(squareID==0)
				{int[] temp= {sizeOfBoard/2};result=temp;}//pierwsze pole A1 ma 1 po��czenie
			else if(squareID==board.length-1) // ostatnie pole H8 ma 1 po��czenie
				{int[] temp= {squareID-sizeOfBoard-1};result=temp;}
			
			else if(squareID<sizeOfBoard/2) // pola z rz�du 1(inne ni� A1) maj� 2 po��czenia
				{int[] temp= {squareID+sizeOfBoard/2-1,squareID+sizeOfBoard/2};result=temp;}
			else if(squareID>board.length-sizeOfBoard/2-1)// pola z rz�du 8 maj� 2 
				{int[] temp= {squareID-sizeOfBoard/2,squareID-sizeOfBoard/2+1};result=temp;}
			
			else if((squareID%(sizeOfBoard/2)==0&&((squareID/(sizeOfBoard/2))%2)==0)//pola z kolumn A(parzyste rz�dy) i H (nieparzyste rz�dy) maj� 2
					||(squareID%(sizeOfBoard/2)==sizeOfBoard-1&&(((squareID-(sizeOfBoard-1))/(sizeOfBoard/2))%2)==1))
				{int[] temp= {squareID-sizeOfBoard/2,squareID+sizeOfBoard/2};result=temp;}
			//pozosta�e pola maj� 4 po�aczenia
			else {int[] temp= {
					squareID-sizeOfBoard/2-(squareID/(sizeOfBoard/2))%2,
					squareID-sizeOfBoard/2-(squareID/(sizeOfBoard/2))%2+1,
					squareID+sizeOfBoard/2-(squareID/(sizeOfBoard/2))%2,
					squareID+sizeOfBoard/2-(squareID/(sizeOfBoard/2))%2+1};
					result=temp;}	
	}
	else
		
		return result;
	}


}
