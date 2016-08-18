import java.util.ArrayList;

//to do:
//jeeli podcza bicia przechodzisz przez pole zmiany rodzaju piona to nie zmieniasz rodzaju piona bo to bicie
//bicie wielokrotne

//Methods:
//moveAvalible
//avalibleSquares
//man_move_possible
//man_capture_possible
//man capture
//king_move_possible
//king_capture_possible
//king_capture
//Methods which returns square at the diagonal in the indicated direction (returns null if square out of board)
//change turn method
//achieve last pool method

//availablePiecesMove
//availablePiecesCapture
//availablePiecesStep

public abstract class Rules {
	
	static Board board = null;
	//if multiCapture, this piece contains the piece which is capturing
	static Piece piece_saved = null;
	
	//square - square where you want to put piece, piece_square - square where the piece was before, piece - piece which you want to move
	public static void move(Square square, Square piece_square ,Piece piece){
		
		if(board != null && piece != null && square != null && piece_square != null){
			if(availablePiecesMove().contains(piece))
				board.getSquares()[square.getID()].setPiece(piece);
			else
				board.getSquares()[piece_square.getID()].setPiece(piece);
			
			//promotion and change turn conditions
			if(piece_saved == null){
				if(achieveLastPool(square, piece))
					piece.setType(PIECE_TYPE.KING);
				changeTurn();
			}	
		}				
	}
	
	//square - square where you want to put piece, piece_square - square where the piece was before, piece - piece which you want to move
	public static boolean moveAvailable(Square square, Square piece_square ,Piece piece){ 		
		boolean isAvalible = false;

		{						
			if((piece.getOwner() == OWNER.WHITE && board.getTurn() == true) || piece.getOwner() == OWNER.BLACK && board.getTurn() == false){ 	//turn condition
				if(capture(square, piece_square, piece))
					isAvalible = true;
				if(move_possible(square, piece_square, piece))
					isAvalible = true;
			}
		}
			
		return isAvalible;
	}
	
	
	//this method return list of pieces that can move at this moment
	public static ArrayList<Square> availablePiecesMove(){
		ArrayList<Square> availablePiecesMoveList = new ArrayList<Square>();
		
		availablePiecesMoveList = availablePiecesCapture();
		if(availablePiecesMoveList.isEmpty()){
			availablePiecesMoveList = availablePiecesMove();
			if(availablePiecesMoveList.isEmpty())
				System.out.println("End of the game! Need to set end");
		}else{
			//if(!multiCapturePossible) nic, po prostu zwraca listę
			if(multi_capture_possible){
				// ustala listę pionków o największej ilości bić i zwraca, lecz trzeba pamiętać że tutaj będzie inaczej bo będzie zapamiętywać pionek
			}
		}
		return availablePiecesMoveList;
	}
	
	//this method return list of pieces that can capture at this moment
	public static ArrayList<Square> availablePiecesCapture(){
		ArrayList<Square> availablePiecesCaptureList = new ArrayList<Square>();
		
		for(int i = 0; i < board.getSquares().length; i++)
			if(board.getSquares()[i].getPiece() != null && capture_possible(board.getSquares()[i].getPiece()))
				availablePiecesCaptureList.add(board.getSquares()[i]);
		
		return availablePiecesCaptureList;
	}
	
	//this method return list of pieces that can step at this moment
		public static ArrayList<Square> availablePiecesStep(){
			ArrayList<Square> availablePiecesStepList = new ArrayList<Square>();
			
			for(int i = 0; i < board.getSquares().length; i++)
				if(board.getSquares()[i].getPiece() != null && step_possible(board.getSquares()[i].getPiece()))
					availablePiecesStepList.add(board.getSquares()[i]);
			
			return availablePiecesStepList;
		}
	
	//this method returns list of squares which are available to reach by piece "piece" at the "piece_square" according to the rules
	public static ArrayList<Square> availableSquares(Square piece_square, Piece piece){
		ArrayList<Square> availableSquares = new ArrayList<Square>();
		boolean isCapturePossible = false;
		
		
		if(board != null && piece_square != null && piece != null && ((piece.getOwner() == OWNER.WHITE) && board.getTurn() == true)||(piece.getOwner() == OWNER.BLACK) && (board.getTurn() == false)){
			
			//ta funkcja sprawdza tylko możliwości dla jednego pionka, trzeba napisać funkcję która sprawdza wszystkie możliwe opcje na planszy
			
			// at first we need to check if there is any possibility of capture
			if(piece.getType() == PIECE_TYPE.MAN){
				for(int i = 0; i < board.getSquares().length; i++)
					if(man_capture_possible(board.getSquares()[i], piece_square, piece)){
						availableSquares.add(board.getSquares()[i]);
						isCapturePossible = true;
					}		
			}else if(piece.getType() == PIECE_TYPE.KING){
				for(int i = 0; i < board.getSquares().length; i++)
					if(king_capture_possible(board.getSquares()[i], piece_square, piece)){
						availableSquares.add(board.getSquares()[i]);
						isCapturePossible = true;
					}		
			}
			
			//if there is no possibility of capture then check normal moves
			if(isCapturePossible == false){
				if(piece.getType() == PIECE_TYPE.MAN){
					for(int i = 0; i < board.getSquares().length; i++)
						if(man_move_possible(board.getSquares()[i], piece_square, piece))
							availableSquares.add(board.getSquares()[i]);
				}else if(piece.getType() == PIECE_TYPE.KING){
					for(int i = 0; i < board.getSquares().length; i++)
						if(king_move_possible(board.getSquares()[i], piece_square, piece))
							availableSquares.add(board.getSquares()[i]);
				}
			}
		}
		return availableSquares;
	}
	
	//returns true if the "piece" can move from "piece_square" to the "square"
	private static boolean move_possible(Square square, Square piece_square, Piece piece){
		boolean isPossible = false;
		
		ArrayList<Square> availableSquares = availableSquares(piece_square, piece);
		if(availableSquares.contains(square))
			isPossible = true;
		
//		if(!capture_possible(board, piece_square, piece) && !capture_possible(board, piece)){ 					// if there is no possibility to capture anywhere
//			if(piece.getType() == PIECE_TYPE.MAN)
//				if(man_move_possible(board, square, piece_square, piece))
//					isPossible = true;
//			if(piece.getType() == PIECE_TYPE.KING)
//				if(king_move_possible(board, square, piece_square, piece))
//					isPossible = true;
//		}
		
		return isPossible;
	}
	
	//returns true if at the "board" there is any possibility to capture by piece.getOwner() Player (The piece_trace is not taken into consideration)
	private static boolean capture_possible(Piece piece){
		boolean isPossible = false;
		
			for(int i = 0; i < board.getSquares().length && !isPossible; i++){
				for(int j = 0; j < board.getSquares().length; j++){
					if(board.getSquares()[i].getPiece() != null && board.getSquares()[i].getPiece().getOwner() == piece.getOwner()){
						if(capture_possible(board.getSquares()[j], board.getSquares()[i], board.getSquares()[i].getPiece()))
							isPossible = true;
					}	
				}		
			}	
		return isPossible;
	}
	
	//returns true if the "piece" from "piece_squre" can capture anywhere
	private static boolean capture_possible(Square piece_square, Piece piece){
		boolean isPossible = false;
		
		for(int i = 0; i < board.getSquares().length && !isPossible; i++)
			if(capture_possible(board.getSquares()[i], piece_square, piece))
				isPossible = true;
		
		return isPossible;
	}
	
	// returns true if the "piece" can capture from "piece_square" to the "square"
	private static boolean capture_possible(Square square, Square piece_square, Piece piece){
		boolean isPossible = false;
		
		if(piece.getType() == PIECE_TYPE.MAN)
			if(man_capture_possible(square, piece_square, piece))
				isPossible = true;
		if(piece.getType() == PIECE_TYPE.KING)
			if(king_capture_possible(square, piece_square, piece))
				isPossible = true;
		
		return isPossible;
	}
	
	// returns true if the "piece" can capture from "piece_square" to the "square"
		private static boolean capture(Square square, Square piece_square, Piece piece){
			boolean isPossible = false;
			
			if(piece.getType() == PIECE_TYPE.MAN)
				if(man_capture(square, piece_square, piece))
					isPossible = true;
			if(piece.getType() == PIECE_TYPE.KING)
				if(king_capture(square, piece_square, piece))
					isPossible = true;
			
			return isPossible;
		}
	
	
	//This method returns true if move from piece_square to the square is possible
	private static boolean man_move_possible(Square square, Square piece_square, Piece piece){
		boolean movePossible = false;
		
		if(square.getPiece() == null){
			if(piece.getOwner() == OWNER.WHITE){
				if(square == field_down_left(piece_square) || square == field_down_right(piece_square))
					movePossible = true;
			}
			if(piece.getOwner() == OWNER.BLACK){
				if(square == field_up_left(piece_square) || square == field_up_right(piece_square))
					movePossible = true;
			}
		}
		return movePossible;
	}
	
	private static boolean man_capture_possible(Square square, Square piece_square, Piece piece){
		boolean capturePossible = false;
		
		if(square.getPiece() == null){
			Square fdl = field_down_left(piece_square);
			Square fdr = field_down_right(piece_square);
			Square ful = field_up_left(piece_square);
			Square fur = field_up_right(piece_square);
			
			if(fdl != null && fdl.getPiece() != null && fdl.getPiece().getOwner() != piece.getOwner())
				if(square == field_down_left(fdl))
					capturePossible = true;
			if(fdr != null && fdr.getPiece() != null && fdr.getPiece().getOwner() != piece.getOwner())
				if(square == field_down_right(fdr))
					capturePossible = true;
			if(ful != null && ful.getPiece() != null && ful.getPiece().getOwner() != piece.getOwner())
				if(square == field_up_left(ful))
					capturePossible = true;
			if(fur != null && fur.getPiece() != null && fur.getPiece().getOwner() != piece.getOwner())
				if(square == field_up_right(fur))
					capturePossible = true;
		}
		return capturePossible;
	}
	
	private static boolean man_capture(Square square, Square piece_square, Piece piece){
		boolean isCaptured = false;
		
		if(square.getPiece() == null){
			Square fdl = field_down_left(piece_square);
			Square fdr = field_down_right(piece_square);
			Square ful = field_up_left(piece_square);
			Square fur = field_up_right(piece_square);
			
			if(fdl != null && fdl.getPiece() != null && fdl.getPiece().getOwner() != piece.getOwner())
				if(square == field_down_left(fdl)){
					fdl.setPiece(null);
					isCaptured = true;
				}
			if(fdr != null && fdr.getPiece() != null && fdr.getPiece().getOwner() != piece.getOwner())
				if(square == field_down_right(fdr)){
					fdr.setPiece(null);
					isCaptured = true;
				}
			if(ful != null && ful.getPiece() != null && ful.getPiece().getOwner() != piece.getOwner())
				if(square == field_up_left(ful)){
					ful.setPiece(null);
					isCaptured = true;
				}
			if(fur != null && fur.getPiece() != null && fur.getPiece().getOwner() != piece.getOwner())
				if(square == field_up_right(fur)){
					fur.setPiece(null);
					isCaptured = true;
				}		
		}
		return isCaptured;
	}
	
	//this method returns true if "piece(king type)" at the "piece_square" can move to the "square"
	private static boolean king_move_possible(Square square, Square piece_square, Piece piece){
		boolean movePossible = false;
		
		// check fields at up_right diagonal
		Square tmp = field_up_right(piece_square);
		
		for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){	//do while square is not out of board and field is empty
			if(square == tmp)
				movePossible = true;
			tmp = field_up_right(tmp);
		}
		
		// check fields at up_left diagonal
		tmp = field_up_left(piece_square);
		
		for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){	//do while square is not out of board and field is empty
			if(square == tmp)
				movePossible = true;
			tmp = field_up_left(tmp);
		}
		
		// check fields at down_right diagonal
		tmp = field_down_right(piece_square);
		
		for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){	//do while square is not out of board and field is empty
			if(square == tmp)
				movePossible = true;
			tmp = field_down_right(tmp);
		}
		
		// check fields at down_left diagonal
		tmp = field_down_left(piece_square);
		
		for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){	//do while square is not out of board and field is empty
			if(square == tmp)
				movePossible = true;
			tmp = field_down_left(tmp);
		}

		return movePossible;
	}
	
	//returns true if capture move from "piece_square" to "square" by "piece" is possible
	private static boolean king_capture_possible(Square square, Square piece_square, Piece piece){
		boolean capturePossible = false;
		
		if(square.getPiece() == null){
			
			// check fields at up_right diagonal
			Square tmp = field_up_right(piece_square);
			
			for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++)							// set tmp at the first square not empty
				tmp = field_up_right(tmp);
			
			if(tmp != null && tmp.getPiece().getOwner() != piece.getOwner()){											// check if not empty square exists at this diagonal and check if the piece's OWNER at the not empty square is different than OWNER of the "piece"
				tmp = field_up_right(tmp);																		// every empty square between 2 not empty squares are square where capture is possible
				for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){
					if(square == tmp)
						capturePossible = true;
					tmp = field_up_right(tmp);
				}	
			}
					
			// check fields at up_left diagonal
			tmp = field_up_left(piece_square);
			
			for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++)	
				tmp = field_up_left(tmp);
			
			if(tmp != null && tmp.getPiece().getOwner() != piece.getOwner()){																	
				tmp = field_up_left(tmp);												
				for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){
					if(square == tmp)
						capturePossible = true;
					tmp = field_up_left(tmp);
				}	
			}		
			
			// check fields at down_right diagonal
			tmp = field_down_right(piece_square);
			
			for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++)	// set tmp at the first field not empty
				tmp = field_down_right(tmp);
			
			if(tmp != null && tmp.getPiece().getOwner() != piece.getOwner()){																	
				tmp = field_down_right(tmp);												
				for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){
					if(square == tmp)
						capturePossible = true;
					tmp = field_down_right(tmp);
				}	
			}		
			
			// check fields at down_left diagonal
			tmp = field_down_left(piece_square);
			
			for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++)	// set tmp at the first field not empty
				tmp = field_down_left(tmp);
			
			if(tmp != null && tmp.getPiece().getOwner() != piece.getOwner()){																	
				tmp = field_down_left(tmp);												
				for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){
					if(square == tmp)
						capturePossible = true;
					tmp = field_down_left(tmp);
				}	
			}		
		}
		return capturePossible;
	}
	
	//This metod execute capturing the piece by king
	private static boolean king_capture(Square square, Square piece_square, Piece piece){
		boolean isCaptured = false;
		
			if(square.getPiece() == null){
				
				// check fields at up_right diagonal
				Square tmp = field_up_right(piece_square);
				Square remove_square = null;
				
				for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++)							// set tmp at the first square not empty
					tmp = field_up_right(tmp);
				
				if(tmp != null && tmp.getPiece().getOwner() != piece.getOwner()){											// check if not empty square exists at this diagonal and check if the piece's OWNER at the not empty square is different than OWNER of the "piece"
					remove_square = tmp;
					tmp = field_up_right(tmp);																		// every empty square between 2 not empty squares are square where capture is possible
					for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){
						if(square == tmp){
							board.getSquares()[remove_square.getID()].setPiece(null);
							isCaptured = true;
						}	
						tmp = field_up_right(tmp);
					}	
			}

			
				// check fields at up_left diagonal
				tmp = field_up_left(piece_square);
				
				for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++)	
					tmp = field_up_left(tmp);
				
				if(tmp != null && tmp.getPiece().getOwner() != piece.getOwner()){																	
					remove_square = tmp;
					tmp = field_up_left(tmp);												
					for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){
						if(square == tmp){
							board.getSquares()[remove_square.getID()].setPiece(null);
							isCaptured = true;
						}
						tmp = field_up_left(tmp);
					}	
				}		
			
				// check fields at down_right diagonal
				tmp = field_down_right(piece_square);
				
				for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++)	// set tmp at the first field not empty
					tmp = field_down_right(tmp);
				
				if(tmp != null && tmp.getPiece().getOwner() != piece.getOwner()){																	
					remove_square = tmp;
					tmp = field_down_right(tmp);												
					for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){
						if(square == tmp){
							board.getSquares()[remove_square.getID()].setPiece(null);
							isCaptured = true;
						}
						tmp = field_down_right(tmp);
					}	
				}		
				
				// check fields at down_left diagonal
				tmp = field_down_left(piece_square);
				
				for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++)	// set tmp at the first field not empty
					tmp = field_down_left(tmp);
				
				if(tmp != null && tmp.getPiece().getOwner() != piece.getOwner()){																	
					remove_square = tmp;
					tmp = field_down_left(tmp);												
					for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){
						if(square == tmp){
							board.getSquares()[remove_square.getID()].setPiece(null);
							isCaptured = true;
						}
						tmp = field_down_left(tmp);
					}	
				}		
			}
		return isCaptured;
	}
	
	// Methods which returns square at the diagonal in the indicated direction (returns null if square out of board)
	private static Square field_up_right(Square square){
		Square field_up_right = null;
		
		if(square != null && square.getID() >= board.getSize()/2 && square.getID() % board.getSize() != board.getSize()-1)	// square exists and it is not at the first row and at the last column
			if(square.getID()/(board.getSize()/2) % 2 == 0)																	// even row
				field_up_right = board.getSquares()[square.getID()- (board.getSize()/2)];
			else																											//odd row
				field_up_right = board.getSquares()[square.getID()- (board.getSize()/2) + 1];
						
		return field_up_right;
	}
	
	private static Square field_up_left(Square square){
		Square field_up_left = null;
		
		if(square != null && square.getID() >= board.getSize()/2 && square.getID() % board.getSize() != 0)					// square exists and it is not at the first row and at the first column
			if(square.getID()/(board.getSize()/2) % 2 == 0)																	// even row
				field_up_left = board.getSquares()[square.getID()-(board.getSize()/2) - 1];
			else																											//odd row
				field_up_left = board.getSquares()[square.getID()-(board.getSize()/2)];
						
		return field_up_left;
	}
	
	private static Square field_down_right(Square square){
		Square field_up_left = null;
		
		if(square != null && square.getID() < (board.getSize()-1)*(board.getSize()/2) && square.getID() % board.getSize() != board.getSize()-1)		// square exists and it is not at the last row and at the last column
			if(square.getID()/(board.getSize()/2) % 2 == 0)																							// even row
				field_up_left = board.getSquares()[square.getID()+(board.getSize()/2)];
			else																																	//odd row
				field_up_left = board.getSquares()[square.getID()+(board.getSize()/2)+1];
						
		return field_up_left;
	}
	
	private static Square field_down_left(Square square){
		Square field_up_left = null;
		
		if(square != null && square.getID() < (board.getSize()-1)*(board.getSize()/2) && square.getID() % board.getSize() != 0)						// square exists and it is not at the last row and at the first column
			if(square.getID()/(board.getSize()/2) % 2 == 0)																							// even row
				field_up_left = board.getSquares()[square.getID()+(board.getSize()/2)-1];
			else																																	//odd row
				field_up_left = board.getSquares()[square.getID()+(board.getSize()/2)];
						
		return field_up_left;
	}
	// End of methods which returns square at the diagonal in the indicated direction
	
	
	public static void changeTurn(){
		if(board.getTurn())
			board.setTurn(false);
		else
			board.setTurn(true);
	}
	//This method change type of the Piece from MAN to the KING if he achieve end of the board
	public static boolean achieveLastPool(Square square, Piece piece){
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
	
	public static boolean is_end_of_the_game(){
		int white_pieces = 0;
		int black_pieces = 0;
		boolean isEND = false;
		
		for(int i = 0; i < board.getSquares().length; i++){
			if(board.getSquares()[i].getPiece() != null){
				if(board.getSquares()[i].getPiece().getOwner() == OWNER.WHITE)
					white_pieces++;
				if(board.getSquares()[i].getPiece().getOwner() == OWNER.BLACK)
					black_pieces++;
			}
		}
		if(white_pieces == 0 || black_pieces == 0)
			isEND = true;
		return isEND;
	}
}