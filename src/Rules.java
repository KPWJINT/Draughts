import java.util.ArrayList;

//to do:
//trzeba biæ a jak nie bijesz to tracisz ¿ycie/nie mo¿esz siê ruszyæ
//czy mo¿na wybraæ ile zbiæ?
//bicie dla którowej
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
					if(king_move_possible(board, square, piece_square, piece))
						isAvalible = true;
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
	
	//this method returns list of squares which are avalible to reach by piece "piece" at the "piece_square" according to the rules
	public static ArrayList<Square> availableSquares(Board board, Square piece_square, Piece piece){
		ArrayList<Square> availableSquares = new ArrayList<Square>();
		
		if(board != null && piece_square != null && piece != null){
			if(piece.getType() == PIECE_TYPE.MAN){
				for(int i = 0; i < board.getSquares().length; i++)
					if(man_move_possible(board, board.getSquares()[i], piece_square, piece) || man_capture_possible(board, board.getSquares()[i], piece_square, piece))
						availableSquares.add(board.getSquares()[i]);
			}else if(piece.getType() == PIECE_TYPE.KING){
				for(int i = 0; i < board.getSquares().length; i++)
					if(king_move_possible(board, board.getSquares()[i], piece_square, piece) || king_capture_possible(board, board.getSquares()[i], piece_square, piece))
						availableSquares.add(board.getSquares()[i]);
			}
		}
		return availableSquares;
	}
	
	//this method returns true if "piece" at the "piece_square" can move to the "square"
	private static boolean king_move_possible(Board board, Square square, Square piece_square, Piece piece){
		boolean movePossible = false;
		
		// check fields at up_right diagonal
		Square tmp = field_up_right(board, piece_square);
		
		for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){	//do while square is not out of board and field is empty
			if(square == tmp)
				movePossible = true;
			tmp = field_up_right(board, tmp);
		}
		
		// check fields at up_left diagonal
		tmp = field_up_left(board, piece_square);
		
		for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){	//do while square is not out of board and field is empty
			if(square == tmp)
				movePossible = true;
			tmp = field_up_left(board, tmp);
		}
		
		// check fields at down_right diagonal
		tmp = field_down_right(board, piece_square);
		
		for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){	//do while square is not out of board and field is empty
			if(square == tmp)
				movePossible = true;
			tmp = field_down_right(board, tmp);
		}
		
		// check fields at down_left diagonal
		tmp = field_down_left(board, piece_square);
		
		for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){	//do while square is not out of board and field is empty
			if(square == tmp)
				movePossible = true;
			tmp = field_down_left(board, tmp);
		}

		return movePossible;
	}
	
	
	// Methods which returns square at the diagonal in the indicated direction (returns null if square out of board)
	private static Square field_up_right(Board board, Square square){
		Square field_up_right = null;
		
		if(square != null && square.getID() >= board.getSize()/2 && square.getID() % board.getSize() != board.getSize()-1)	// square exists and it is not at the first row and at the last column
			if(square.getID()/(board.getSize()/2) % 2 == 0)																	// even row
				field_up_right = board.getSquares()[square.getID()-4];
			else																											//odd row
				field_up_right = board.getSquares()[square.getID()-3];
						
		return field_up_right;
	}
	
	private static Square field_up_left(Board board, Square square){
		Square field_up_left = null;
		
		if(square != null && square.getID() >= board.getSize()/2 && square.getID() % board.getSize() != 0)					// square exists and it is not at the first row and at the first column
			if(square.getID()/(board.getSize()/2) % 2 == 0)																	// even row
				field_up_left = board.getSquares()[square.getID()-5];
			else																											//odd row
				field_up_left = board.getSquares()[square.getID()-4];
						
		return field_up_left;
	}
	
	private static Square field_down_right(Board board, Square square){
		Square field_up_left = null;
		
		if(square != null && square.getID() < (board.getSize()-1)*(board.getSize()/2) && square.getID() % board.getSize() != board.getSize()-1)		// square exists and it is not at the last row and at the last column
			if(square.getID()/(board.getSize()/2) % 2 == 0)																							// even row
				field_up_left = board.getSquares()[square.getID()+4];
			else																																	//odd row
				field_up_left = board.getSquares()[square.getID()+5];
						
		return field_up_left;
	}
	
	private static Square field_down_left(Board board, Square square){
		Square field_up_left = null;
		
		if(square != null && square.getID() < (board.getSize()-1)*(board.getSize()/2) && square.getID() % board.getSize() != 0)						// square exists and it is not at the last row and at the first column
			if(square.getID()/(board.getSize()/2) % 2 == 0)																							// even row
				field_up_left = board.getSquares()[square.getID()+3];
			else																																	//odd row
				field_up_left = board.getSquares()[square.getID()+4];
						
		return field_up_left;
	}
	// End of methods which returns square at the diagonal in the indicated direction
	
	
	private static boolean man_move_possible(Board board, Square square, Square piece_square, Piece piece){
		boolean movePossible = false;
		
		if(square.getPiece() == null){
			if(piece.getOwner() == OWNER.WHITE){
				if(piece_square.getID()/(board.getSize()/2) % 2 == 0){							//even row
					if(piece_square.getID() % (board.getSize()/2) == 0){						//if piece_square is at the boarder of the board
						if(square.getID() == piece_square.getID() + 4){
							movePossible = true;
						}
					}else{																		//if piece_square is not at the boarder of the map
						if(square.getID() == piece_square.getID() + 3 || square.getID() == piece_square.getID() + 4){
							movePossible = true;
						}
					}
				}else{																			//odd row
					if(piece_square.getID() % (board.getSize()/2) ==(board.getSize()/2)-1){		//if piece_square is at the boarder of the board
						if(square.getID() == piece_square.getID() + 4){
							movePossible = true;
						}
					}else{																		//if piece_square is not at the boarder of the map
						if(square.getID() == piece_square.getID() + 4 || square.getID() == piece_square.getID() + 5){
							movePossible = true;
						}
					}
				}
			}else if(piece.getOwner() == OWNER.BLACK){
				if(piece_square.getID()/(board.getSize()/2) % 2 == 0){							//even row
					if(piece_square.getID() % (board.getSize()/2) == 0){						//if piece_square is at the boarder of the board
						if(square.getID() == piece_square.getID() - 4){
							movePossible = true;
						}
					}else{																		//if piece_square is not at the boarder of the map
						if(square.getID() == piece_square.getID() - 5 || square.getID() == piece_square.getID() - 4){
							movePossible = true;
						}
					}
				}else{																			//odd row
					if(piece_square.getID() % (board.getSize()/2) ==(board.getSize()/2)-1){		//if piece_square is at the boarder of the board
						if(square.getID() == piece_square.getID() - 4){
							movePossible = true;
						}
					}else{																		//if piece_square is not at the boarder of the map
						if(square.getID() == piece_square.getID() - 4 || square.getID() == piece_square.getID() - 3){
							movePossible = true;
						}
					}
				}
			}
		}
		return movePossible;
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
	
	public static boolean man_capture_possible(Board board, Square square, Square piece_square, Piece piece){
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
	
	public static boolean king_capture_possible(Board board, Square square, Square piece_square, Piece piece){
		boolean isCaptured = false;
		
		return isCaptured;
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
