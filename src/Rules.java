import java.awt.Point;

public abstract class Rules {

	public static boolean moveAvalible(Board board, Square square, Square piece_square ,Piece piece){ //square - square where you want to put piece, piece_square - square where the piece was before, piece - piece which you want to move
		boolean isAvalible = false;
		if(board != null && square != null && piece != null && piece_square != null && square.getPiece() == null){ //all arguments are not null and square is empty
			if((piece.getOwner() == OWNER.WHITE && board.getTurn() == true) || piece.getOwner() == OWNER.BLACK && board.getTurn() == false){ //turn condition
				if(connectedTo(board, square, piece_square, piece))
					isAvalible = true;
				if(piece_capture(board, square, piece_square, piece))
					isAvalible = true;
			}
		}
		
		if(isAvalible == true)
			changeTurn(board);
		return isAvalible;
	}
	
	public static boolean connectedTo(Board board, Square square, Square piece_square, Piece piece){
		boolean connectedTo = false;
		
		if(piece_square != null){
			
			if(piece.getOwner() == OWNER.WHITE){
				if(square.getID()/(board.getSize()/2) % 2 == 0){
					if(piece_square.getID() == square.getID() - 5 || piece_square.getID() == square.getID() - 4)
						connectedTo = true;
				}else{
					if(piece_square.getID() == square.getID() - 4 || piece_square.getID() == square.getID() - 3)
						connectedTo = true;
				}
			}else{
				if(square.getID()/(board.getSize()/2) % 2 == 0){
					if(piece_square.getID() == square.getID() + 3 || piece_square.getID() == square.getID() + 4)
						connectedTo = true;
				}else{
					if(piece_square.getID() == square.getID() + 4 || piece_square.getID() == square.getID() + 5)
						connectedTo = true;
				}
			}
			
//			if(square.getID()/(board.getSize()/2) % 2 == 0){
//				if(piece_square.getID() == square.getID() - 5 || piece_square.getID() == square.getID() - 4 || piece_square.getID() == square.getID() + 3 || piece_square.getID() == square.getID() + 4)
//					connectedTo = true;
//			}else{
//				if(piece_square.getID() == square.getID() - 4 || piece_square.getID() == square.getID() - 3 || piece_square.getID() == square.getID() + 4 || piece_square.getID() == square.getID() + 5)
//					connectedTo = true;
//			}
		}
		return connectedTo;
	}
	
	public static boolean piece_capture(Board board, Square square, Square piece_square, Piece piece){
		boolean isCaptured = false;
		
		if(square.getPiece() == null){
			if(piece_square.getID() == square.getID() - 9){
				if(square.getID()/(board.getSize()/2) % 2 == 0){
					if(board.getSquares()[square.getID()-5].getPiece() != null && board.getSquares()[square.getID()-5].getPiece().getOwner() != piece.getOwner()){
						board.getSquares()[square.getID()-5].setPiece(null);
						isCaptured = true;
						
					}
				}else{
					if(board.getSquares()[square.getID()-4].getPiece() != null && board.getSquares()[square.getID()-4].getPiece().getOwner() != piece.getOwner()){
						board.getSquares()[square.getID()-4].setPiece(null);
						isCaptured = true;
					}
				}
			}
			if(piece_square.getID() == square.getID() - 7){
				if(square.getID()/(board.getSize()/2) % 2 == 0){
					if(board.getSquares()[square.getID()-4].getPiece() != null && board.getSquares()[square.getID()-4].getPiece().getOwner() != piece.getOwner()){
						board.getSquares()[square.getID()-4].setPiece(null);
						isCaptured = true;
						
					}
				}else{
					if(board.getSquares()[square.getID()-3].getPiece() != null && board.getSquares()[square.getID()-3].getPiece().getOwner() != piece.getOwner()){
						board.getSquares()[square.getID()-3].setPiece(null);
						isCaptured = true;
					}	
				}
			}
			if(piece_square.getID() == square.getID() + 7){
				if(square.getID()/(board.getSize()/2) % 2 == 0){
					if(board.getSquares()[square.getID()+3].getPiece() != null && board.getSquares()[square.getID()+3].getPiece().getOwner() != piece.getOwner()){
						board.getSquares()[square.getID()+3].setPiece(null);
						isCaptured = true;
					}	
				}else{
					if(board.getSquares()[square.getID()+4].getPiece() != null && board.getSquares()[square.getID()+4].getPiece().getOwner() != piece.getOwner()){
						board.getSquares()[square.getID()+4].setPiece(null);
						isCaptured = true;
					}
				}
			}
			if(piece_square.getID() == square.getID() + 9){
				if(square.getID()/(board.getSize()/2) % 2 == 0){
					if(board.getSquares()[square.getID()+4].getPiece() != null && board.getSquares()[square.getID()+4].getPiece().getOwner() != piece.getOwner()){
						board.getSquares()[square.getID()+4].setPiece(null);
						isCaptured = true;
					}
				}else{
					if(board.getSquares()[square.getID()+5].getPiece() != null && board.getSquares()[square.getID()+5].getPiece().getOwner() != piece.getOwner()){
						board.getSquares()[square.getID()+5].setPiece(null);
						isCaptured = true;
					}	
				}
			}
		}		
		return isCaptured;
	}
	
	public static void changeTurn(Board board){
		if(board.getTurn())
			board.setTurn(false);
		else
			board.setTurn(true);
	}
}
