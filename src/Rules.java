import java.awt.Point;

public abstract class Rules {

	public static boolean moveAvalible(Board board, Square square, Square piece_square ,Piece piece){
		boolean isAvalible = false;
		if(board != null && square != null && piece != null && piece_square != null && square.getPiece() == null){ //all arguments are not null and square is empty
			if(connectedTo(board, square, piece_square))
				isAvalible = true;
		}
		
		//sprawdza jakie pola s¹ obok?
		//sprawdza jeœli pola s¹ puste, jeœli s¹ zajête przez gracza o przeciwnym kol
		// potrzebujê pionek oraz pole.... hmmm... ich po³o¿enia i wtedy bla bla bla
		// co mam w bordzie: wszystkie pionki i pola i ich po³o¿enie??
		
		
				
		return isAvalible;
	}
	
	public static boolean connectedTo(Board board, Square square, Square piece_square){
		boolean connectedTo = false;
		//Square piece_square = getSquare(board, piece);
		
		if(piece_square != null){
			if(square.getID()/(board.getSize()/2) % 2 == 0){
				if(piece_square.getID() == square.getID() - 5 || piece_square.getID() == square.getID() - 4 || piece_square.getID() == square.getID() + 3 || piece_square.getID() == square.getID() + 4)
					connectedTo = true;
			}else{
				if(piece_square.getID() == square.getID() - 4 || piece_square.getID() == square.getID() - 3 || piece_square.getID() == square.getID() + 4 || piece_square.getID() == square.getID() + 5)
					connectedTo = true;
			}
		}
		
		return connectedTo;
	}
	
	public static Square getSquare(Board board, Piece piece){
		Square square = null;
		
		for(int i = 0; i < board.getSquares().length; i++){
			if(board.getSquares()[i] != null && /*board.getSquares()[i].getPiece() != null && */ board.getSquares()[i].getPoint().equals(piece.getPoint()))
				square = board.getSquares()[i];
		}
		return square;
	}
}
