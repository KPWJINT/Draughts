import java.util.ArrayList;

public class MyRules {
	
	//list of available squares
	ArrayList<Square> square_list;
	
	boolean player_white=true;
	
	public MyRules(){
		square_list=new ArrayList<Square>();	
	}
	
	//method which finds potential, available fields 
	public ArrayList findPotentialField(Square[] board, Piece piece, int square_index){
		
		findFree(board, piece, square_index);				//first check if we can move diagonally
		
		
		return square_list;
	}
	//counts row which contains piece
	//I don't use it for now
	private int findRow(int square_nr, int board_size){
		int column=board_size/2;	
		int row=0;
		for(int i=0; i<column;i++){
			if(square_nr>column*i)
				row=i+1;
		}
		return row;
	}
	//for now is just for white piece
	private void findFree(Square[] board, Piece piece, int square_index){
		//counts numbers of squares in one row
				int board_size=(int)Math.sqrt(board.length*2);
				int columns=board_size/2;
				int i=square_index;
				int row=findRow(i, board_size);
				System.out.println("row "+row+" "+i);
				if(player_white){
					//if the piece is a man
					if(piece.getType().equals(PIECE_TYPE.MAN)){
						//the most common situation, when we want to 
						//move diagonally 
						if(row%2==0){									//wyeliminuj brzegi!
							if(board[i+columns].getPiece()==null && (i+columns)<=(row+1)*columns && (i+columns)>=(row)*columns)
								square_list.add(board[i+columns]);
							if(board[i+columns-1].getPiece()==null && (i+columns-1)<(row+1)*columns && (i+columns-1)>(row)*columns)
								square_list.add(board[i+columns-1]);
						}
						if(row%2!=0){
							if(board[i+columns].getPiece()==null && (i+columns)<=(row+1)*columns && (i+columns)>=(row)*columns)
								square_list.add(board[i+columns]);
							if(board[i+columns+1].getPiece()==null && (i+columns+1)<(row+1)*columns && (i+columns+1)>(row)*columns)
								square_list.add(board[i+columns+1]);
						}
						
					}
					//if a piece is a king
//					if(piece.getType().equals(PIECE_TYPE.KING)){
//						
//					}
				}
				if(!player_white){
					if(piece.getType().equals(PIECE_TYPE.MAN)){
						
					}

//					if(piece.getType().equals(PIECE_TYPE.KING)){
//						
//					}
	}
	
	}
}
