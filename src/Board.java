
public class Board {
	
	private Squares[] board;
	private Piece[] firstPlayer;
	private Piece[] secondPlayer;
	private final int sizeOfBoard;
	
	public Board(int sizeOfBoard, int resolution_width, int resolution_height) {

		this.sizeOfBoard = sizeOfBoard;
		int square_length = resolution_height/sizeOfBoard;
		int zero_width = (resolution_width - resolution_height)/2;
		int width = zero_width;
		int height = 0;
		
		board = new Squares[this.sizeOfBoard*this.sizeOfBoard/2]; // here it would be good to add exception to avoid odd number
		firstPlayer = new Piece[((this.sizeOfBoard/2)-1)*(this.sizeOfBoard/2)];
		secondPlayer = new Piece[((this.sizeOfBoard/2)-1)*(this.sizeOfBoard/2)]; // firstPlayer and second Player arrays need to have the same size
		

		for(int i = 0; i < sizeOfBoard; i++) {
			if(i % 2 == 0)
				width = zero_width;
			else
				width = zero_width + square_length;
			
			for(int j = 0; j < sizeOfBoard/2; j++) {
				board[j + i*(sizeOfBoard/2)] = new Squares(j + i*(sizeOfBoard/2), null, width, height);
				width += square_length*2;
			}
			height += square_length;
		} // create squares without pieces
		
		for(int i = 0; i < firstPlayer.length; i++) { 
			firstPlayer[i] = new Piece(i, OWNER.WHITE, PIECE_TYPE.NORMAL_PIECE);
			secondPlayer[i] = new Piece(i, OWNER.BLACK, PIECE_TYPE.NORMAL_PIECE);
			
			board[i].setPiece(firstPlayer[i]);
			board[board.length-i-1].setPiece(secondPlayer[i]);
		} // create arrays of pieces and crate start squares for this pieces
		
	} // constructor
	
	public int getSize() {return sizeOfBoard;}
	public Squares getSquare(int index) {return board[index];}
	
}
