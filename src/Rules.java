import java.util.ArrayList;

//to do:
//trzeba biæ a jak nie bijesz to tracisz ¿ycie/nie mo¿esz siê ruszyæ
//czy mo¿na wybraæ ile zbiæ?
//ruchy dla królowej
//warunek zwyciêstwa

public abstract class Rules {
	
	//square - square where you want to put piece, piece_square - square where the piece was before, piece - piece which you want to move
	public static boolean moveAvalible(Board board, Square square, Square piece_square ,Piece piece){ 		boolean isAvalible = false;
		
		if(board != null && square != null && piece != null && piece_square != null && square.getPiece() == null){ 								//all arguments are not null and square is empty
			if((piece.getOwner() == OWNER.WHITE && board.getTurn() == true) || piece.getOwner() == OWNER.BLACK && board.getTurn() == false){ 	//turn condition
				if(piece.getType() == PIECE_TYPE.MAN){
					if(man_move_possible(board, square, piece_square, piece))
						isAvalible = true;
					if(piece_capture(board, square, piece_square, piece))
						isAvalible = true;
				}else{
//					if(king_move_possible(board, square, piece_square, piece))
//						isAvalible = true;
				}
				
			}
		}
		
		
		if(isAvalible == true){
			changeTurn(board);
			if(achieveLastPool(board, square, piece))
				piece.setType(PIECE_TYPE.KING);
		}
			
		return isAvalible;
	}
	
//	private static boolean king_move_possible(Board board, Square square, Square piece_square, Piece piece){
//		boolean movePossible = false;
//		
//		if(square.getID()/(board.getSize()/2) % 2 == 0){
//			for(int i = 0; i < 7; i++){
//				if(i % 2 == 0 && (piece_square.getID() == square.getID() - 4*i || piece_square.getID() == square.getID() - 3*i || piece_square.getID() == square.getID() + 4*i || piece_square.getID() == square.getID() + 5*i)){
//					movePossible = true;
//				} else if(i % 2 == 0 && (piece_square.getID() == square.getID() - 5*i || piece_square.getID() == square.getID() - 4*i || piece_square.getID() == square.getID() + 3*i || piece_square.getID() == square.getID() + 4*i)){
//					movePossible = true;
//				}
//			}
//		}else{
//			for(int i = 0; i < 7; i++){
//				if(i % 2 == 0 && (piece_square.getID() == square.getID() - 5*i || piece_square.getID() == square.getID() - 4*i || piece_square.getID() == square.getID() + 3*i || piece_square.getID() == square.getID() + 4*i)){
//					movePossible = true;
//				} else if(i % 2 == 0 && (piece_square.getID() == square.getID() - 4*i || piece_square.getID() == square.getID() - 3*i || piece_square.getID() == square.getID() + 4*i || piece_square.getID() == square.getID() + 5*i)){
//					movePossible = true;
//				}
//			}
//		}
//		
//		// 7 najd³u¿sza linia, id zmienia siê co 3 lub 4 lub co 5 na przek¹tnych
//		
//		return movePossible;
//	}
	
	private static boolean man_move_possible(Board board, Square square, Square piece_square, Piece piece){
		boolean movePossible = false;
		
		if(square.getPiece() == null){
			if(piece.getOwner() == OWNER.WHITE){
				if(square.getID()/(board.getSize()/2) % 2 == 0){
					if(piece_square.getID() == square.getID() - 5 || piece_square.getID() == square.getID() - 4)
						movePossible = true;
				}else{
					if(piece_square.getID() == square.getID() - 4 || piece_square.getID() == square.getID() - 3)
						movePossible = true;
				}
			}else if(piece.getOwner() == OWNER.BLACK){
				if(square.getID()/(board.getSize()/2) % 2 == 0){
					if(piece_square.getID() == square.getID() + 3 || piece_square.getID() == square.getID() + 4)
						movePossible = true;
				}else{
					if(piece_square.getID() == square.getID() + 4 || piece_square.getID() == square.getID() + 5)
						movePossible = true;
				}
			}
		}
		return movePossible;
	}
	
//	public static boolean connectedTo(Board board, Square square, Square piece_square, Piece piece){
//		boolean connectedTo = false;
//			
//			if(piece.getOwner() == OWNER.WHITE){
//				if(square.getID()/(board.getSize()/2) % 2 == 0){
//					if(piece_square.getID() == square.getID() - 5 || piece_square.getID() == square.getID() - 4)
//						connectedTo = true;
//				}else{
//					if(piece_square.getID() == square.getID() - 4 || piece_square.getID() == square.getID() - 3)
//						connectedTo = true;
//				}
//			}else if(piece.getOwner() == OWNER.BLACK){
//				if(square.getID()/(board.getSize()/2) % 2 == 0){
//					if(piece_square.getID() == square.getID() + 3 || piece_square.getID() == square.getID() + 4)
//						connectedTo = true;
//				}else{
//					if(piece_square.getID() == square.getID() + 4 || piece_square.getID() == square.getID() + 5)
//						connectedTo = true;
//				}
//			}
//		
//		return connectedTo;
//	}
	
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
	
	public static boolean piece_capture_possible(Board board, Square square, Square piece_square, Piece piece){
		boolean isCaptured = false;
		
		if(square.getPiece() == null){
			if(piece_square.getID() == square.getID() - 9){
				if(square.getID()/(board.getSize()/2) % 2 == 0){
					if(board.getSquares()[square.getID()-5].getPiece() != null && board.getSquares()[square.getID()-5].getPiece().getOwner() != piece.getOwner()){
						isCaptured = true;
						
					}
				}else{
					if(board.getSquares()[square.getID()-4].getPiece() != null && board.getSquares()[square.getID()-4].getPiece().getOwner() != piece.getOwner()){
						
						isCaptured = true;
					}
				}
			}
			if(piece_square.getID() == square.getID() - 7){
				if(square.getID()/(board.getSize()/2) % 2 == 0){
					if(board.getSquares()[square.getID()-4].getPiece() != null && board.getSquares()[square.getID()-4].getPiece().getOwner() != piece.getOwner()){
						
						isCaptured = true;
						
					}
				}else{
					if(board.getSquares()[square.getID()-3].getPiece() != null && board.getSquares()[square.getID()-3].getPiece().getOwner() != piece.getOwner()){
						
						isCaptured = true;
					}	
				}
			}
			if(piece_square.getID() == square.getID() + 7){
				if(square.getID()/(board.getSize()/2) % 2 == 0){
					if(board.getSquares()[square.getID()+3].getPiece() != null && board.getSquares()[square.getID()+3].getPiece().getOwner() != piece.getOwner()){
						
						isCaptured = true;
					}	
				}else{
					if(board.getSquares()[square.getID()+4].getPiece() != null && board.getSquares()[square.getID()+4].getPiece().getOwner() != piece.getOwner()){
						
						isCaptured = true;
					}
				}
			}
			if(piece_square.getID() == square.getID() + 9){
				if(square.getID()/(board.getSize()/2) % 2 == 0){
					if(board.getSquares()[square.getID()+4].getPiece() != null && board.getSquares()[square.getID()+4].getPiece().getOwner() != piece.getOwner()){
						
						isCaptured = true;
					}
				}else{
					if(board.getSquares()[square.getID()+5].getPiece() != null && board.getSquares()[square.getID()+5].getPiece().getOwner() != piece.getOwner()){
						
						isCaptured = true;
					}	
				}
			}
		}		
		return isCaptured;
	}
	
	
	public static ArrayList<Square> availableSquares(Board board, Square piece_square, Piece piece){
		ArrayList<Square> availableSquares = new ArrayList<Square>();
		
		if(board != null && piece_square != null && piece != null){
			if(piece.getType() == PIECE_TYPE.MAN){
				for(int i = 0; i < board.getSquares().length; i++)
					if(man_move_possible(board, board.getSquares()[i], piece_square, piece) || piece_capture_possible(board, board.getSquares()[i], piece_square, piece))
						availableSquares.add(board.getSquares()[i]);
			}
		}
			
		return availableSquares;
	}
	
	public static void changeTurn(Board board){
		if(board.getTurn())
			board.setTurn(false);
		else
			board.setTurn(true);
	}
	
	public static boolean achieveLastPool(Board board, Square square, Piece piece){
		boolean achieve = false;
		
		if(piece.getOwner() == OWNER.WHITE){
			for(int i = ((board.getSize()/2)*(board.getSize()))-board.getSize()/2; i < board.getSquares().length; i++){
				if(board.getSquares()[i].equals(square))
					achieve = true;
			}
		}else if(piece.getOwner() == OWNER.BLACK){
			for(int i = 0; i < board.getSize()/2; i++){
				if(board.getSquares()[i].equals(square))
					achieve = true;
			}
		}
		return achieve;
	}
}
