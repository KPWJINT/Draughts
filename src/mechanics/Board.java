package mechanics;
import enums.OWNER;
import enums.PIECE_TYPE;


public class Board implements Rules{
	private Squares[] board;
	private Piece[] whitePlayer;
	private Piece[] blackPlayer;
	private final int sizeOfBoard;
	
	public Board(int sizeOfBoard)
	{
		this.sizeOfBoard= sizeOfBoard;
		board = new Squares[this.sizeOfBoard*this.sizeOfBoard/2];
		whitePlayer= new Piece[(this.sizeOfBoard-2)*this.sizeOfBoard/4];
		blackPlayer= new Piece[(this.sizeOfBoard-2)*this.sizeOfBoard/4];
		for(int i=0;i<whitePlayer.length;++i)
		{
			whitePlayer[i]= new Piece(PIECE_TYPE.MAN,OWNER.WHITE,i);
			blackPlayer[i]= new Piece(PIECE_TYPE.MAN,OWNER.BLACK,i);
		}
		for(int i=0;i<(this.sizeOfBoard-2)*this.sizeOfBoard/4;++i)
			board[i]=new Squares(i,whitePlayer[i]);
		for(int i=this.sizeOfBoard*this.sizeOfBoard/2;i>this.sizeOfBoard*this.sizeOfBoard/2-blackPlayer.length;--i)
			board[i-1]=new Squares(i-1,blackPlayer[this.sizeOfBoard*this.sizeOfBoard/2-i]);
		for(int i=this.sizeOfBoard*this.sizeOfBoard/4-1;i<this.sizeOfBoard-(this.sizeOfBoard*this.sizeOfBoard)/4;++i)
			board[i]= new Squares(i,null);		
	}
	
	public int[] connectedTo(int squareID)//polecam przy analizowaniu zrobi� sobie schemat planszy :)
	{
		int[] result=null;
		if((board[squareID].getPiece()).getType()==PIECE_TYPE.MAN)//dla pionka
		{
			if(squareID==0)
				{int[] temp= {sizeOfBoard/2};result=temp;}//pierwsze pole A1 ma 1 po��czenie
			else if(squareID==sizeOfBoard*sizeOfBoard/2-1) // ostatnie pole H8 ma 1 po��czenie
				{int[] temp= {sizeOfBoard*sizeOfBoard/2-sizeOfBoard-1};result=temp;}
			else if(squareID<sizeOfBoard/2) // pola z rz�du 1(inne ni� A1) maj� 2 po��czenia
				{int[] temp= {squareID+sizeOfBoard/2-1,squareID+sizeOfBoard/2};result=temp;}
			else if(squareID>sizeOfBoard*sizeOfBoard/2-sizeOfBoard-1)// pola z rz�du 8 maj� 2 
				{int[] temp= {squareID-sizeOfBoard/2-1+(squareID/sizeOfBoard)%2,squareID-sizeOfBoard/2+(squareID/sizeOfBoard)%2};result=temp;}
			else if((squareID%(sizeOfBoard/2)==0&&((squareID/sizeOfBoard)%2)==0)//pola z kolumn A(parzyste rz�dy) i H (nieparzyste rz�dy) maj� 2
					||(squareID%(sizeOfBoard/2)==sizeOfBoard-1&&(((squareID-(sizeOfBoard-1))/sizeOfBoard)%2)==1))
				{int[] temp= {squareID-sizeOfBoard,squareID+sizeOfBoard};result=temp;} //pozosta�e pola maj� 4 po�aczenia
			else {int[] temp= {squareID-sizeOfBoard,squareID-sizeOfBoard+1,squareID+sizeOfBoard-1, squareID+sizeOfBoard};result=temp;}	
		}
		else if ((board[squareID].getPiece()).getType()==PIECE_TYPE.KING)//dla kr�l�wki
		{
			if(squareID==0)
			{
				result=new int[sizeOfBoard];
				for(int i=0;i<sizeOfBoard;++i)
					result[i]=squareID+=sizeOfBoard+i%2;
			}//wszystkie pola na przek�tnej; trzeba doda� 1 dla nieparzystych wierzy
			else if(squareID==sizeOfBoard*sizeOfBoard/2-1) 
			{
				result=new int[sizeOfBoard];
				for(int i=sizeOfBoard-1;i==0;--i)
					result[i]=squareID-=sizeOfBoard-i%2;
			}//podobnie jak wy�ej				
			else if(squareID<sizeOfBoard) 
			{
				result=new int[sizeOfBoard];
				for(int i=0;i<squareID%(sizeOfBoard/2);++i)
					result[i]=squareID+(1+i)*(sizeOfBoard-1);
				for(int i=sizeOfBoard;i>sizeOfBoard-squareID%(sizeOfBoard/2);--i)
					result[i]=squareID+sizeOfBoard*(i+1)+i%2;
			}
			
			else if(squareID>sizeOfBoard*sizeOfBoard/2-sizeOfBoard-1)// pola z rz�du H maj� 2 
				{int[] temp= {squareID-sizeOfBoard-1,squareID-sizeOfBoard};result=temp;}
			else if((squareID%(sizeOfBoard/2)==0&&((squareID/sizeOfBoard)%2)==0)//pola z kolumn 1(parzyste rz�dy) i 8 (nieparzyste rz�dy) maj� 2
					||(squareID%(sizeOfBoard/2)==sizeOfBoard-1&&(((squareID-(sizeOfBoard-1))/sizeOfBoard)%2)==1))
				{int[] temp= {squareID-sizeOfBoard,squareID+sizeOfBoard};result=temp;} //pozosta�e pola maj� 4 po�aczenia
			else {int[] temp= {squareID-sizeOfBoard,squareID-sizeOfBoard+1,squareID+sizeOfBoard-1, squareID+sizeOfBoard};result=temp;}	
		}
		return result;
	}

}