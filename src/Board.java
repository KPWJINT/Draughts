public class Board{
	
	private Square[] squares;
	private Piece[] player_white;
	private Piece[] player_black;
	private final int size;
	private boolean turn;	//if white player turn = true, if black player turn = false
	
	//create initial board for draughts
	public Board(int size){
		this.size = size;
		this.turn = true;
		
		squares = new Square[this.size*this.size/2]; // here it would be good to add exception to avoid odd number
		player_white = new Piece[((this.size/2)-1)*(this.size/2)];
		player_black = new Piece[((this.size/2)-1)*(this.size/2)]; // player_white and second Player arrays need to have the same size
		
		for(int i = 0; i < player_white.length; i++) { 
			player_white[i] = new Piece(i, OWNER.WHITE, PIECE_TYPE.MAN);
			player_black[i] = new Piece(i, OWNER.BLACK, PIECE_TYPE.MAN);
			
			squares[i] = new Square(i, player_white[i]);
			squares[squares.length-i-1] = new Square(squares.length-i-1, player_black[i]);
		} // create arrays of pieces and crate start squares for this pieces
		
		for(int i = player_white.length; i < (player_white.length + this.size); i++) { 
			squares[i] = new Square(i, null);
		} // create empty middle squares of squares
		
	} // constructor
	
	public int getSize(){return size;}
	public boolean getTurn(){return turn;}
	public void setTurn(boolean turn){this.turn = turn;}
	public Square[] getSquares(){return squares;}
	public Piece[] getPlayer_white(){return player_white;}
	public Piece[] getPlayer_black(){return player_black;}
	public Board clone(){
		Board b = new Board(size);
		
		for(int i = 0; i < squares.length; i++)
			b.squares[i] = new Square(squares[i]);
		for(int i = 0; i < player_white.length; i++)
			b.player_white[i] = new Piece(player_white[i]);
		for(int i = 0; i < player_black.length; i++)
			b.player_black[i] = new Piece(player_black[i]);
		b.turn = turn;
		
		return b;
	}
}

