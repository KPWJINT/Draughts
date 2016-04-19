import java.awt.geom.Point2D;

public class Board {
	
	private Square[] squares;
	private Piece[] player_first;
	private Piece[] player_second;
	private final int resolution_width;
	private final int resolution_height;
	private final int size;
	
	public Board(int resolution_width, int resolution_height, int size) {
		this.resolution_width = resolution_width;
		this.resolution_height = resolution_height;
		this.size = size;
		
		int square_length = this.resolution_height/size;
		
		squares = new Square[this.size*this.size/2];	// here it would be good to add exception to avoid odd number
		player_first = new Piece[((this.size/2)-1)*(this.size/2)];
		player_second = new Piece[((this.size/2)-1)*(this.size/2)]; // firstPlayer and second Player arrays need to have the same size
		
		int width;
		int height;
		
		for(int i = 0; i < this.size; i++) {
			if(i % 2 == 0)
				width = 0;
			else
				width = square_length;
			
			for(int j = 0; j < this.size/2; j++) {
				squares[j + i*(this.size/2)] = new Square(j + i*(size/2), null, new Point2D.Double(width, height));
				width += square_length*2;
			}
			height += square_length;
		} // create squares without pieces
		
		for(int i = 0; i < player_first.length; i++) { 
			player_first[i] = new Piece(i, OWNER.WHITE, PIECE_TYPE.MAN);
			player_second[i] = new Piece(i, OWNER.BLACK, PIECE_TYPE.MAN);
			
			board[i].setPiece(firstPlayer[i]);
			board[board.length-i-1].setPiece(secondPlayer[i]);
		} // create arrays of pieces and crate start squares for this pieces
		
	} // constructor
	
	public int getSize() {return sizeOfBoard;}
	public Squares getSquare(int index) {return board[index];}
	public Squares getSquare(Piece piece) {
		for(int i = 0; i < sizeOfBoard/2; i++)
			if(board[i].getPiece() == piece)
				return board[i];
		return null;
	}
	public Piece[] getFirstPlayer(){return firstPlayer;}
	public Piece[] getSecondPlayer(){return secondPlayer;}
}
