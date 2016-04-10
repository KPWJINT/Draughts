import java.awt.Image;
import java.util.ArrayList;

public class Board 
{
	int pawn_white;
	int pawn_black;
	
	boolean condition;
	OWNER  player;
	
	
	private Squares[][] board;
	
	
	private ArrayList<Piece> p_white;
	private ArrayList<Piece> p_black;
	
	public Squares[][] getTable()
	{
		return board;
	}
	public int getPawnWhiteNumbers()
	{
		return pawn_white;
	}
	public int getPawnBlackNumbers()
	{
		return pawn_black;
	}
	
	public Board(int number, Image image, int height, int width )
	{
		
		player=OWNER.WHITE;
		
		board=new Squares[number][number];

		
		p_white=new ArrayList<Piece>();
		p_black=new ArrayList<Piece>();
		
		pawn_white= number*(number-2)/4;
		pawn_black= pawn_white;
	
		for(int i=0;i<pawn_white;i++)
		{
			p_white.add(new Piece(PIECE_TYPE.WARRIOR,OWNER.WHITE, i));
			p_black.add(new Piece(PIECE_TYPE.WARRIOR, OWNER.BLACK, i));
		}

		int id=0;
		
		for (int i =0; i<board.length; i++)
		{
			for(int j=0; j<board[i].length; j++)
			{
				board[i][j]=new Squares(id);
				id++;
			}
		}
		//there has to be some mistake
		int m=0;
		for (int i=0; i<board.length; i++)
		{
			for(int j=0; j<board[i].length; j++)
			{
				if(i%2==0  && j%2!=0 && m<p_white.size()|| i%2!=0  && j%2==0 && m<p_white.size())
				{
					board[i][j].setPiece(p_white.get(m));
					m++;
				}
			}
		}
		//or here!
		int a=0;
		for (int i=board.length-1; i>-1; i--)
		{
			for(int j=board[i].length-1; j>-1; j--)
			{
				if(i%2==0  && j%2!=0 && a<p_black.size() || i%2!=0 && j%2==0 && a<p_black.size())
				{
					board[i][j].setPiece(p_black.get(a));
					a++;
				}
			}
		}	
	}
	
	/**
	
	//just wrote that- probably it has to be changed-DON'T LOOK AT THIS
	
	public boolean check_first(Square s1)
	{
		
		for (int i =0; i<rows; i++)
		{
			for(int j=0; j<columns; j++)
			{
				if(board[i][j].getID()==s1.getID())
				{
					if(board[i][j].getPawn()!=null)
					{
						 condition=true;
					}
					else
						condition=false;
				}
			}
		}
		return condition;
	}
	
	public boolean check_pawn(Pawn pawn)
	{
		if(pawn.getPlayer().equals(player))
		{
			condition=true;
		}
		else
			condition=false;

		return condition;
	}
	
	public boolean check_second(Square s2)
	{
		//empty for now
		
		return condition;
	}
	
	
	public void move(Square s1,Square s2, Pawn pawn)
	{
		//some conditions to write

		check_first(s1);
		check_pawn(pawn);
		check_second(s2);
		
		
		if(condition==true)
		{
			for (int i =0; i<rows; i++)
			{
				for(int j=0; j<columns; j++)
				{
					if(board[i][j].getID()==s1.getID())
					{
						board[i][j].setPawn(null);
						
					}
					if(board[i][j].getID()==s2.getID())
					{
						board[i][j].setPawn(pawn);
						
					}
				}
			}
		}
		
		
		
		
	}

	public void display() 
	{
		for (int i =0; i<columns; i++)
		{   
			System.out.println("        ");
			
			for(int j=0; j<rows; j++)
			{
				System.out.print(board[i][j].getPawn()+" ");
			}
		}
	}
	
	
	
	
	public static void main(String[] args)
	{
		
		
	
		
	}
	
	
	
	**/
	

}
