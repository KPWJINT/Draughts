import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

//to do:
//je¿eli podcza bicia przechodzisz przez pole zmiany rodzaju piona to nie zmieniasz rodzaju piona bo to bicie
//warunek zwyciêstwa

//Methods:
//moveAvalible
//avalibleSquares
//move_possible
//capture_possible x3
//capture
//man_move_possible
//man_capture_possible
//man capture
//king_move_possible
//king_capture_possible
//king_capture
//Methods which returns square at the diagonal in the indicated direction (returns null if square out of board)
//change turn method
//achive last pool method

public abstract class Rules {
	
	//square - square where you want to put piece, piece_square - square where the piece was before, piece - piece which you want to move
	public static boolean moveAvailable(Board board, Square square, Square piece_square ,Piece piece){ 		
		boolean isAvalible = false;

//		if(board != null && piece != null && square != null && piece_square != null){			//all arguments are not null and square is empty				
//			if((piece.getOwner() == OWNER.WHITE && board.getTurn() == true) || piece.getOwner() == OWNER.BLACK && board.getTurn() == false){ 	//turn condition
//				if(capture(board, square, piece_square, piece))
//					isAvalible = true;
//				if(move_possible(board, square, piece_square, piece))
//					isAvalible = true;
//			}
//		}
//		
//		if(isAvalible == true){
//			changeTurn(board);
//			if(achieveLastPool(board, square, piece))
//				piece.setType(PIECE_TYPE.KING);
//			System.out.println(board.getTurn());
//		}
			
		return isAvalible;
	}
	
//	//this method returns list of squares which are available to reach by piece "piece" at the "piece_square" according to the rules
//	public static ArrayList<Square> availableSquares(Board board, Square piece_square, Piece piece){
//		ArrayList<Square> availableSquares = new ArrayList<Square>();
//		
//		if(board != null && piece_square != null && piece != null){
//			if(piece.getType() == PIECE_TYPE.MAN){
//				for(int i = 0; i < board.getSquares().length; i++)
//					if(man_move_possible(board, board.getSquares()[i], piece_square, piece) || man_capture_possible(board, board.getSquares()[i], piece_square, piece))
//						availableSquares.add(board.getSquares()[i]);
//			}else if(piece.getType() == PIECE_TYPE.KING){
//				for(int i = 0; i < board.getSquares().length; i++)
//					if(king_move_possible(board, board.getSquares()[i], piece_square, piece) || king_capture_possible(board, board.getSquares()[i], piece_square, piece))
//						availableSquares.add(board.getSquares()[i]);
//			}
//		}
//		return availableSquares;
//	}
//	
//	//returns true if the "piece" can move from "piece_square" to the "square"
//	private static boolean move_possible(Board board, Square square, Square piece_square, Piece piece){
//		boolean isPossible = false;
//		
//		if(capture_possible(board, piece_square, piece).isEmpty() && !capture_possible(board, piece)){ 					// if there is no possibility to capture anywhere
//			if(piece.getType() == PIECE_TYPE.MAN)
//				if(man_move_possible(board, square, piece_square, piece))
//					isPossible = true;
//			if(piece.getType() == PIECE_TYPE.KING)
//				if(king_move_possible(board, square, piece_square, piece))
//					isPossible = true;
//		}
//		
//		return isPossible;
//	}
//	
//	//returns true if at the "board" there is any possibility to capture by piece.getOwner() Player (The piece_trace is not taken into consideration)
//	private static boolean capture_possible(Board board, Piece piece){
//		boolean isPossible = false;
//		
//			for(int i = 0; i < board.getSquares().length && !isPossible; i++){
//				for(int j = 0; j < board.getSquares().length; j++){
//					if(board.getSquares()[i].getPiece() != null && board.getSquares()[i].getPiece().getOwner() == piece.getOwner()){
//						if(capture_possible(board, board.getSquares()[j], board.getSquares()[i], board.getSquares()[i].getPiece()))
//							isPossible = true;
//					}	
//				}		
//			}	
//		return isPossible;
//	}
//	
////	//returns true if the "piece" from "piece_squre" can capture anywhere
////	private static boolean capture_possible(Board board, Square piece_square, Piece piece){
////		boolean isPossible = false;
////		
////		for(int i = 0; i < board.getSquares().length && !isPossible; i++)
////			if(capture_possible(board, board.getSquares()[i], piece_square, piece))
////				isPossible = true;
////		
////		return isPossible;
////	}
//	
//	// returns true if the "piece" can capture from "piece_square" to the "square"
//	private static boolean capture_possible(Board board, Square square, Square piece_square, Piece piece){
//		boolean isPossible = false;
//		
//		if(piece.getType() == PIECE_TYPE.MAN)
//			if(man_capture_possible(board, square, piece_square, piece))
//				isPossible = true;
//		if(piece.getType() == PIECE_TYPE.KING)
//			if(king_capture_possible(board, square, piece_square, piece))
//				isPossible = true;
//		
//		return isPossible;
//	}
//	
////	// returns true if the "piece" can capture from "piece_square" to the "square"
////		private static boolean capture(Board board, Square square, Square piece_square, Piece piece){
////			boolean isPossible = false;
////			
////			if(piece.getType() == PIECE_TYPE.MAN)
////				if(man_capture(board, square, piece_square, piece))
////					isPossible = true;
////			if(piece.getType() == PIECE_TYPE.KING)
////				if(king_capture(board, square, piece_square, piece))
////					isPossible = true;
////			
////			return isPossible;
////		}
//		
//		////////////////////////////////////////////////////////////////////////////////////////////
//		
//	
//	
//		// returns true if the "piece" can capture from "piece_square" to the "square"
//		private static boolean capture(Board board, Square square, Square piece_square, Piece piece){
//			boolean isPossible = false;
//			
//			if(metoda1(board, piece_square, piece).contains(square)){	//jeœli w square zawiera siê w liœcie pól o najwiêkszej liczbie biæ
//				if(piece.getType() == PIECE_TYPE.MAN)
//					if(man_capture(board, square, piece_square, piece))
//						isPossible = true;
//				if(piece.getType() == PIECE_TYPE.KING)
//					if(king_capture(board, square, piece_square, piece))
//						isPossible = true;
//			}
//			
//			return isPossible;
//		}
//		
//		// returns true if the "piece" can capture from "piece_square" to the "square"
//		private static boolean capture_x(Board board, Square square, Square piece_square, Piece piece){
//			boolean isPossible = false;
//			
//				if(piece.getType() == PIECE_TYPE.MAN)
//					if(man_capture(board, square, piece_square, piece))
//						isPossible = true;
//				if(piece.getType() == PIECE_TYPE.KING)
//					if(king_capture(board, square, piece_square, piece))
//						isPossible = true;
//			
//			return isPossible;
//		}
//
//		//metoda zwracaj¹ca pola o najwiêkszej liczbie biæ
//		private static LinkedList<Square> metoda1(Board board, Square piece_square, Piece piece){
//			LinkedList<Square> result = new LinkedList<Square>();
//			
//			result = capture_possible(board, piece_square, piece); // result = mo¿liwe pola na które mo¿na zbiæ
//			metoda2(board, piece_square, piece, result);
//			
//			return result;
//		}
//		
//		//metoda która dostaje i oblicza maksymaln¹ liczbê biæ dla ka¿dego elementu i usuwa z listy wszyskie elementy o mniejszej liczbie biæ
//		private static int metoda2(Board board, Square piece_square, Piece piece, LinkedList<Square> list){
//			int result = 1;
//			Square square = null;
//			if(!list.isEmpty()){
//				Iterator<Square> it = list.iterator();
//				LinkedList<Integer> list2 = new LinkedList<Integer>();
//				
//				//metoda obliczaj¹ca liczbê biæ dla ka¿dego elementu
//				while(it.hasNext()){ 										//dla ka¿dego elementu
//					//oblicz maks liczbê biæ i zapisz do list2
//					Board b = board.clone();
//					square = it.next();
//					
//					capture_x(b, square, piece_square, piece);
//					list2.add(1 + metoda2(b, square, piece, capture_possible(board, square, piece)));
//					
//				}
//				
//				//wy³onienie najwiekszej liczby biæ i zapisanie tej wartoœci w result
//				for(int n : list2)
//					if(n > result)
//						result = n;
//				
//				//usuniêcie elementów o mniejszej liczbie biæ niz result(czyli najwiêkszej)
//				for(int i = 0; i < list.size(); i++){
//					if(list2.get(i) < result){
//						list.remove(i);
//						list2.remove(i);
//						i--;
//					}
//				}
//					
//			}
//			
//					
//			return result;
//		}
//		
//		//zwraca listê dostêpnych pól do bicia
//		private static LinkedList<Square> capture_possible(Board board, Square piece_square, Piece piece){
//			LinkedList<Square> capture_squares = new LinkedList<Square>();	// This list will contain squares where capture is possible??
//			
//			for(int i = 0; i < board.getSquares().length; i++)								//tutaj dodajê do listy wszystkie pola na które mo¿na zbiæ(zarówno dla pionka jak i dla królowej
//				if(capture_possible(board, board.getSquares()[i], piece_square, piece))
//					capture_squares.add(board.getSquares()[i]);			
//			
//			return capture_squares;
//		}
//		
//		///////////////////////////////////////////////////////////////////////////////////////////
//	
//	
//	//This method returns true if move from piece_square to the square is possible
//	private static boolean man_move_possible(Board board, Square square, Square piece_square, Piece piece){
//		boolean movePossible = false;
//		
//		if(square.getPiece() == null){
//			if(piece.getOwner() == OWNER.WHITE){
//				if(square == field_down_left(board, piece_square) || square == field_down_right(board, piece_square))
//					movePossible = true;
//			}
//			if(piece.getOwner() == OWNER.BLACK){
//				if(square == field_up_left(board, piece_square) || square == field_up_right(board, piece_square))
//					movePossible = true;
//			}
//		}
//		return movePossible;
//	}
//	
//	//this method returns true if piece (PIECE_TYPE = MAN) can capture from piece_square to the square
//	private static boolean man_capture_possible(Board board, Square square, Square piece_square, Piece piece){
//		boolean capturePossible = false;
//		
//		if(square.getPiece() == null){
//			Square fdl = field_down_left(board, piece_square);
//			Square fdr = field_down_right(board, piece_square);
//			Square ful = field_up_left(board, piece_square);
//			Square fur = field_up_right(board, piece_square);
//			
//			if(fdl != null && fdl.getPiece() != null && fdl.getPiece().getOwner() != piece.getOwner())
//				if(square == field_down_left(board, fdl))
//					capturePossible = true;
//			if(fdr != null && fdr.getPiece() != null && fdr.getPiece().getOwner() != piece.getOwner())
//				if(square == field_down_right(board, fdr))
//					capturePossible = true;
//			if(ful != null && ful.getPiece() != null && ful.getPiece().getOwner() != piece.getOwner())
//				if(square == field_up_left(board, ful))
//					capturePossible = true;
//			if(fur != null && fur.getPiece() != null && fur.getPiece().getOwner() != piece.getOwner())
//				if(square == field_up_right(board, fur))
//					capturePossible = true;
//		}
//		return capturePossible;
//	}
//	
//	//this method returns true if piece (PIECE_TYPE = MAN) captured from piece_square to the square and execute this capture
//	private static boolean man_capture(Board board, Square square, Square piece_square, Piece piece){
//		boolean isCaptured = false;
//		
//		if(square.getPiece() == null){
//			Square fdl = field_down_left(board, piece_square);
//			Square fdr = field_down_right(board, piece_square);
//			Square ful = field_up_left(board, piece_square);
//			Square fur = field_up_right(board, piece_square);
//			
//			if(fdl != null && fdl.getPiece() != null && fdl.getPiece().getOwner() != piece.getOwner())
//				if(square == field_down_left(board, fdl)){
//					fdl.setPiece(null);
//					isCaptured = true;
//				}
//			if(fdr != null && fdr.getPiece() != null && fdr.getPiece().getOwner() != piece.getOwner())
//				if(square == field_down_right(board, fdr)){
//					fdr.setPiece(null);
//					isCaptured = true;
//				}
//			if(ful != null && ful.getPiece() != null && ful.getPiece().getOwner() != piece.getOwner())
//				if(square == field_up_left(board, ful)){
//					ful.setPiece(null);
//					isCaptured = true;
//				}
//			if(fur != null && fur.getPiece() != null && fur.getPiece().getOwner() != piece.getOwner())
//				if(square == field_up_right(board, fur)){
//					fur.setPiece(null);
//					isCaptured = true;
//				}		
//		}
//		return isCaptured;
//	}
//	
//	//this method returns true if "piece(king type)" at the "piece_square" can move to the "square"
//	private static boolean king_move_possible(Board board, Square square, Square piece_square, Piece piece){
//		boolean movePossible = false;
//		
//		// check fields at up_right diagonal
//		Square tmp = field_up_right(board, piece_square);
//		
//		for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){	//do while square is not out of board and field is empty
//			if(square == tmp)
//				movePossible = true;
//			tmp = field_up_right(board, tmp);
//		}
//		
//		// check fields at up_left diagonal
//		tmp = field_up_left(board, piece_square);
//		
//		for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){	//do while square is not out of board and field is empty
//			if(square == tmp)
//				movePossible = true;
//			tmp = field_up_left(board, tmp);
//		}
//		
//		// check fields at down_right diagonal
//		tmp = field_down_right(board, piece_square);
//		
//		for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){	//do while square is not out of board and field is empty
//			if(square == tmp)
//				movePossible = true;
//			tmp = field_down_right(board, tmp);
//		}
//		
//		// check fields at down_left diagonal
//		tmp = field_down_left(board, piece_square);
//		
//		for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){	//do while square is not out of board and field is empty
//			if(square == tmp)
//				movePossible = true;
//			tmp = field_down_left(board, tmp);
//		}
//
//		return movePossible;
//	}
//	
//	//returns true if capture move from "piece_square" to "square" by "piece" is possible
//	private static boolean king_capture_possible(Board board, Square square, Square piece_square, Piece piece){
//		boolean capturePossible = false;
//		
//		if(square.getPiece() == null){
//			
//			// check fields at up_right diagonal
//			Square tmp = field_up_right(board, piece_square);
//			
//			for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++)							// set tmp at the first square not empty
//				tmp = field_up_right(board, tmp);
//			
//			if(tmp != null && tmp.getPiece().getOwner() != piece.getOwner()){											// check if not empty square exists at this diagonal and check if the piece's OWNER at the not empty square is different than OWNER of the "piece"
//				tmp = field_up_right(board, tmp);																		// every empty square between 2 not empty squares are square where capture is possible
//				for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){
//					if(square == tmp)
//						capturePossible = true;
//					tmp = field_up_right(board, tmp);
//				}	
//			}
//					
//			// check fields at up_left diagonal
//			tmp = field_up_left(board, piece_square);
//			
//			for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++)	
//				tmp = field_up_left(board, tmp);
//			
//			if(tmp != null && tmp.getPiece().getOwner() != piece.getOwner()){																	
//				tmp = field_up_left(board, tmp);												
//				for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){
//					if(square == tmp)
//						capturePossible = true;
//					tmp = field_up_left(board, tmp);
//				}	
//			}		
//			
//			// check fields at down_right diagonal
//			tmp = field_down_right(board, piece_square);
//			
//			for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++)	// set tmp at the first field not empty
//				tmp = field_down_right(board, tmp);
//			
//			if(tmp != null && tmp.getPiece().getOwner() != piece.getOwner()){																	
//				tmp = field_down_right(board, tmp);												
//				for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){
//					if(square == tmp)
//						capturePossible = true;
//					tmp = field_down_right(board, tmp);
//				}	
//			}		
//			
//			// check fields at down_left diagonal
//			tmp = field_down_left(board, piece_square);
//			
//			for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++)	// set tmp at the first field not empty
//				tmp = field_down_left(board, tmp);
//			
//			if(tmp != null && tmp.getPiece().getOwner() != piece.getOwner()){																	
//				tmp = field_down_left(board, tmp);												
//				for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){
//					if(square == tmp)
//						capturePossible = true;
//					tmp = field_down_left(board, tmp);
//				}	
//			}		
//		}
//		return capturePossible;
//	}
//	
//	//This metod execute capturing the piece by king
//	private static boolean king_capture(Board board, Square square, Square piece_square, Piece piece){
//		boolean isCaptured = false;
//		
//			if(square.getPiece() == null){
//				
//				// check fields at up_right diagonal
//				Square tmp = field_up_right(board, piece_square);
//				Square remove_square = null;
//				
//				for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++)							// set tmp at the first square not empty
//					tmp = field_up_right(board, tmp);
//				
//				if(tmp != null && tmp.getPiece().getOwner() != piece.getOwner()){											// check if not empty square exists at this diagonal and check if the piece's OWNER at the not empty square is different than OWNER of the "piece"
//					remove_square = tmp;
//					tmp = field_up_right(board, tmp);																		// every empty square between 2 not empty squares are square where capture is possible
//					for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){
//						if(square == tmp){
//							board.getSquares()[remove_square.getID()].setPiece(null);
//							isCaptured = true;
//						}	
//						tmp = field_up_right(board, tmp);
//					}	
//			}
//
//			
//				// check fields at up_left diagonal
//				tmp = field_up_left(board, piece_square);
//				
//				for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++)	
//					tmp = field_up_left(board, tmp);
//				
//				if(tmp != null && tmp.getPiece().getOwner() != piece.getOwner()){																	
//					remove_square = tmp;
//					tmp = field_up_left(board, tmp);												
//					for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){
//						if(square == tmp){
//							board.getSquares()[remove_square.getID()].setPiece(null);
//							isCaptured = true;
//						}
//						tmp = field_up_left(board, tmp);
//					}	
//				}		
//			
//				// check fields at down_right diagonal
//				tmp = field_down_right(board, piece_square);
//				
//				for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++)	// set tmp at the first field not empty
//					tmp = field_down_right(board, tmp);
//				
//				if(tmp != null && tmp.getPiece().getOwner() != piece.getOwner()){																	
//					remove_square = tmp;
//					tmp = field_down_right(board, tmp);												
//					for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){
//						if(square == tmp){
//							board.getSquares()[remove_square.getID()].setPiece(null);
//							isCaptured = true;
//						}
//						tmp = field_down_right(board, tmp);
//					}	
//				}		
//				
//				// check fields at down_left diagonal
//				tmp = field_down_left(board, piece_square);
//				
//				for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++)	// set tmp at the first field not empty
//					tmp = field_down_left(board, tmp);
//				
//				if(tmp != null && tmp.getPiece().getOwner() != piece.getOwner()){																	
//					remove_square = tmp;
//					tmp = field_down_left(board, tmp);												
//					for(int i = 0; i < board.getSize() && tmp != null && tmp.getPiece() == null; i++){
//						if(square == tmp){
//							board.getSquares()[remove_square.getID()].setPiece(null);
//							isCaptured = true;
//						}
//						tmp = field_down_left(board, tmp);
//					}	
//				}		
//			}
//		return isCaptured;
//	}
//	
//	// Methods which returns square at the diagonal in the indicated direction (returns null if square out of board)
//	private static Square field_up_right(Board board, Square square){
//		Square field_up_right = null;
//		
//		if(square != null && square.getID() >= board.getSize()/2 && square.getID() % board.getSize() != board.getSize()-1)	// square exists and it is not at the first row and at the last column
//			if(square.getID()/(board.getSize()/2) % 2 == 0)																	// even row
//				field_up_right = board.getSquares()[square.getID()- (board.getSize()/2)];
//			else																											//odd row
//				field_up_right = board.getSquares()[square.getID()- (board.getSize()/2) + 1];
//						
//		return field_up_right;
//	}
//	
//	private static Square field_up_left(Board board, Square square){
//		Square field_up_left = null;
//		
//		if(square != null && square.getID() >= board.getSize()/2 && square.getID() % board.getSize() != 0)					// square exists and it is not at the first row and at the first column
//			if(square.getID()/(board.getSize()/2) % 2 == 0)																	// even row
//				field_up_left = board.getSquares()[square.getID()-(board.getSize()/2) - 1];
//			else																											//odd row
//				field_up_left = board.getSquares()[square.getID()-(board.getSize()/2)];
//						
//		return field_up_left;
//	}
//	
//	private static Square field_down_right(Board board, Square square){
//		Square field_up_left = null;
//		
//		if(square != null && square.getID() < (board.getSize()-1)*(board.getSize()/2) && square.getID() % board.getSize() != board.getSize()-1)		// square exists and it is not at the last row and at the last column
//			if(square.getID()/(board.getSize()/2) % 2 == 0)																							// even row
//				field_up_left = board.getSquares()[square.getID()+(board.getSize()/2)];
//			else																																	//odd row
//				field_up_left = board.getSquares()[square.getID()+(board.getSize()/2)+1];
//						
//		return field_up_left;
//	}
//	
//	private static Square field_down_left(Board board, Square square){
//		Square field_up_left = null;
//		
//		if(square != null && square.getID() < (board.getSize()-1)*(board.getSize()/2) && square.getID() % board.getSize() != 0)						// square exists and it is not at the last row and at the first column
//			if(square.getID()/(board.getSize()/2) % 2 == 0)																							// even row
//				field_up_left = board.getSquares()[square.getID()+(board.getSize()/2)-1];
//			else																																	//odd row
//				field_up_left = board.getSquares()[square.getID()+(board.getSize()/2)];
//						
//		return field_up_left;
//	}
//	// End of methods which returns square at the diagonal in the indicated direction
//	
//	
//	public static void changeTurn(Board board){
//		if(board.getTurn())
//			board.setTurn(false);
//		else
//			board.setTurn(true);
//	}
//	//This method change type of the Piece from MAN to the KING if he achieve end of the board
//	public static boolean achieveLastPool(Board board, Square square, Piece piece){
//		boolean achieve = false;
//		
//		if(piece.getOwner() == OWNER.WHITE){
//			for(int i = ((board.getSize()/2)*(board.getSize()))-board.getSize()/2; i < board.getSquares().length; i++){
//				if(board.getSquares()[i].equals(square))
//					achieve = true;
//			}
//		}else if(piece.getOwner() == OWNER.BLACK){
//			for(int i = 0; i < board.getSize()/2; i++){
//				if(board.getSquares()[i].equals(square))
//					achieve = true;
//			}
//		}
//		return achieve;
//	}
//	
//	public static boolean is_end_of_the_game(Board board){
//		int white_pieces = 0;
//		int black_pieces = 0;
//		boolean isEND = false;
//		
//		for(int i = 0; i < board.getSquares().length; i++){
//			if(board.getSquares()[i].getPiece() != null){
//				if(board.getSquares()[i].getPiece().getOwner() == OWNER.WHITE)
//					white_pieces++;
//				if(board.getSquares()[i].getPiece().getOwner() == OWNER.BLACK)
//					black_pieces++;
//			}
//		}
//		if(white_pieces == 0 || black_pieces == 0)
//			isEND = true;
//		return isEND;
//	}
	
}
