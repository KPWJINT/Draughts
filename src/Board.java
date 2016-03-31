
public class Board {
	
	private Squares[] board;
	private Piece[] firstPlayer;
	private Piece[] secondPlayer;
	private final int sizeOfBoard;
	
	public Board(int sizeOfBoard) {

		this.sizeOfBoard = sizeOfBoard;
		
		board = new Squares[this.sizeOfBoard*this.sizeOfBoard/2]; // here it would be good to add exception to avoid odd number
		firstPlayer = new Piece[((this.sizeOfBoard/2)-1)*(this.sizeOfBoard/2)];
		secondPlayer = new Piece[((this.sizeOfBoard/2)-1)*(this.sizeOfBoard/2)]; // firstPlayer and second Player arrays need to have the same size
		
		for(int i = 0; i < firstPlayer.length; i++) { 
			firstPlayer[i] = new Piece(i, OWNER.WHITE, PIECE_TYPE.NORMAL_PIECE);
			secondPlayer[i] = new Piece(i, OWNER.BLACK, PIECE_TYPE.NORMAL_PIECE);
			
			board[i] = new Squares(i, firstPlayer[i]);
			board[board.length-i-1] = new Squares(board.length-i-1, secondPlayer[i]);
		} // create arrays of pieces and crate start squares for this pieces
		
		for(int i = firstPlayer.length; i < (firstPlayer.length + this.sizeOfBoard); i++) { 
			board[i] = new Squares(i, null);
		} // create empty middle squares of board
		
	} // constructor
	
}
