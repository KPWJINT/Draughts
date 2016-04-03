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
			board[i]=new Squares(i,whitePlayer[i]);
			board[this.sizeOfBoard*this.sizeOfBoard/2-i-1]=new Squares(this.sizeOfBoard*this.sizeOfBoard/2-i-1,blackPlayer[this.sizeOfBoard*this.sizeOfBoard/2-i]);						
		}

		for(int i=this.sizeOfBoard*this.sizeOfBoard/4-1;i<this.sizeOfBoard-(this.sizeOfBoard*this.sizeOfBoard)/4;++i)
			board[i]= new Squares(i,null);		
	}
	public int[] connectedTo(int squareID)
	{
		return this.connectedTo(squareID, sizeOfBoard, board);
	}
	
	

}