import java.awt.Dimension;


public class TicTacToe {

	/**
	 * @param args
	 */
	private static int squareSize=250;
	private static int[][] board= new int[3][3];
	private static int BLANK_ID=0;
	private static int PLAYER_ID=1;
	private static int CPU_ID=4;
	private static boolean playerStarts=true;
	
	/*
	 * Value of 0 represents blank square
	 * Value of 1 represents square taken by user
	 * value of 2 represents square taken by computer
	 * 
	 * Teal: use for player- (0,255,229)
	 * Purple: use for computer-(191,0,255)
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		drawBoard();
		if(playerStarts) startPlayerTurn();
		else startCPUTurn();
		

	}
	
	public static void drawBoard()	{
		
		Zen.DEFAULT_SIZE= new Dimension(3*squareSize,3*squareSize);
		
		Zen.setColor(0,0,0);
		Zen.fillRect(0, 0, squareSize, squareSize);
		Zen.setColor(255,255,255);
		Zen.fillRect(squareSize, 0, squareSize, squareSize);
		Zen.setColor(0,0,0);
		Zen.fillRect(2*squareSize, 0, squareSize, squareSize);
		
		Zen.setColor(255,255,255);
		Zen.fillRect(0, squareSize, squareSize, squareSize);
		Zen.setColor(0,0,0);
		Zen.fillRect(squareSize, squareSize, squareSize, squareSize);
		Zen.setColor(255,255,255);
		Zen.fillRect(2*squareSize, squareSize, squareSize, squareSize);
		
		Zen.setColor(0,0,0);
		Zen.fillRect(0, 2*squareSize, squareSize, squareSize);
		Zen.setColor(255,255,255);
		Zen.fillRect(squareSize, 2*squareSize, squareSize, squareSize);
		Zen.setColor(0,0,0);
		Zen.fillRect(2*squareSize, 2*squareSize, squareSize, squareSize);
		
	}
	
	public static void startPlayerTurn()	{
		Zen.waitForClick();
		int xClick= Zen.getMouseClickX();
		int yClick= Zen.getMouseClickY();
		
		if(board[xClick/squareSize][yClick/squareSize]==BLANK_ID) board[xClick/squareSize][yClick/squareSize]=PLAYER_ID;
		else startPlayerTurn();
		colorSquare(xClick,yClick,0,255,229);
		if(checkThreeInARow(PLAYER_ID)==PLAYER_ID) {
			finishGame(PLAYER_ID);
			return;
		}
		else startCPUTurn();
	}
	
	public static void startCPUTurn()	{
		if(CPUIsAlmostThere())	{
			finishGame(CPU_ID);
			return;
		} 
		
		if(!CPUEliminatedImminentThreat())	{
			
			if(!tookRandomBlankSquare())	{
				finishGame(BLANK_ID);
				return;
			}
		}if(checkThreeInARow(CPU_ID)==CPU_ID) finishGame(CPU_ID); 
		else startPlayerTurn();	
	}
	
	public static boolean CPUEliminatedImminentThreat()	{
		int top=board[0][0]+board[1][0]+board[2][0];
		int bottom=board[0][2]+board[1][2]+board[2][2];
		int left=board[0][0]+board[0][1]+board[0][2];
		int right=board[2][0]+board[2][1]+board[2][2];
		int diag1=board[0][0]+board[1][1]+board[2][2]; //top left to bottom right
		int diag2=board[2][0]+board[1][1]+board[0][2]; //top right to bottom left
		int horizontal=board[0][1]+board[1][1]+board[2][1];
		int vertical=board[1][0]+board[1][1]+board[1][2];
		int i;
		if(top==2*PLAYER_ID)	{
			for(i=0;i<3;i++)	{
				if(board[i][0]==0)	{
					colorSquare(i*squareSize,0,191,0,255);
					board[i][0]=CPU_ID;
					return true;
				}
			} return false;
		} else if(bottom==2*PLAYER_ID)	{
			for(i=0;i<3;i++)	{
				if(board[i][2]==0)	{
					colorSquare(i*squareSize,2*squareSize,191,0,255);
					board[i][2]=CPU_ID;
					return true;
				}
			} return false;
		} else if(left==2*PLAYER_ID)	{
			for(i=0;i<3;i++)	{
				if(board[0][i]==0)	{
					colorSquare(0,i*squareSize,191,0,255);
					board[0][i]=CPU_ID;
					return true;
				}
			} return false;
		} else if(right==2*PLAYER_ID)	{
			for(i=0;i<3;i++)	{
				if(board[2][i]==0)	{
					colorSquare(2*squareSize,i*squareSize,191,0,255);
					board[2][i]=CPU_ID;
					return true;
				}
			} return false;
		} else if(diag1==2*PLAYER_ID)	{
			for(i=0;i<3;i++)	{
				if(board[i][i]==0)	{
					colorSquare(i*squareSize,i*squareSize,191,0,255);
					board[i][i]=CPU_ID;
					return true;
				}
			} return false;
		} else if(diag2==2*PLAYER_ID)	{
			if(board[0][2]==0)	{
				colorSquare(0,2*squareSize,191,0,255);
				board[0][2]=CPU_ID;
				return true;
			}else if(board[1][1]==0)	{
				colorSquare(squareSize,squareSize,191,0,255);
				board[1][1]=CPU_ID;
				return true;
			}else if(board[2][0]==0)	{
				colorSquare(2*squareSize,0,191,0,255);
				board[2][0]=CPU_ID;
				return true;
			} return false;
		} else if(horizontal==2*PLAYER_ID)	{
			for(i=0;i<3;i++)	{
				if(board[i][1]==0)	{
					board[i][1]=CPU_ID;
					colorSquare(i*squareSize,squareSize,191,0,255);
					return true;
				}
			} return false;
		} else if(vertical== 2*PLAYER_ID)	{
			for(i=0;i<3;i++)	{
				if(board[1][i]==0)	{
					board[1][i]=CPU_ID;
					colorSquare(squareSize,i*squareSize,191,0,255);
					return true;
				}
			} return false;
		} else return false;
		 
	}
	
	public static boolean CPUIsAlmostThere()	{
		int top=board[0][0]+board[1][0]+board[2][0];
		int bottom=board[0][2]+board[1][2]+board[2][2];
		int left=board[0][0]+board[0][1]+board[0][2];
		int right=board[2][0]+board[2][1]+board[2][2];
		int diag1=board[0][0]+board[1][1]+board[2][2]; //top left to bottom right
		int diag2=board[2][0]+board[1][1]+board[0][2]; //top right to bottom left
		int horizontal=board[0][1]+board[1][1]+board[2][1];
		int vertical=board[1][0]+board[1][1]+board[1][2];
		int i;
		if(top==2*CPU_ID)	{
			for(i=0;i<3;i++)	{
				if(board[i][0]==0)	{
					colorSquare(i*squareSize,0,191,0,255);
					board[i][0]=CPU_ID;
					return true;
				}
			} return false;
		} else if(bottom==2*CPU_ID)	{
			for(i=0;i<3;i++)	{
				if(board[i][2]==0)	{
					colorSquare(i*squareSize,2*squareSize,191,0,255);
					board[i][2]=CPU_ID;
					return true;
				}
			} return false;
		} else if(left==2*CPU_ID)	{
			for(i=0;i<3;i++)	{
				if(board[0][i]==0)	{
					colorSquare(0,i*squareSize,191,0,255);
					board[0][i]=CPU_ID;
					return true;
				}
			} return false;
		} else if(right==2*CPU_ID)	{
			for(i=0;i<3;i++)	{
				if(board[2][i]==0)	{
					colorSquare(2*squareSize,i*squareSize,191,0,255);
					board[2][i]=CPU_ID;
					return true;
				}
			} return false;
		} else if(diag1==2*CPU_ID)	{
			for(i=0;i<3;i++)	{
				if(board[i][i]==0)	{
					colorSquare(i*squareSize,i*squareSize,191,0,255);
					board[i][i]=CPU_ID;
					return true;
				}
			} return false;
		} else if(diag2==2*CPU_ID)	{
			if(board[0][2]==0)	{
				colorSquare(0,2*squareSize,191,0,255);
				board[0][2]=CPU_ID;
				return true;
			}else if(board[1][1]==0)	{
				colorSquare(squareSize,squareSize,191,0,255);
				board[1][1]=CPU_ID;
				return true;
			}else if(board[2][0]==0)	{
				colorSquare(2*squareSize,0,191,0,255);
				board[2][0]=CPU_ID;
				return true;
			} return false;
		}  else if(horizontal==2*CPU_ID)	{
			for(i=0;i<3;i++)	{
				if(board[i][1]==0)	{
					board[i][1]=CPU_ID;
					colorSquare(i*squareSize,squareSize,191,0,255);
					return true;
				}
			} return false;
		} else if(vertical== 2*CPU_ID)	{
			for(i=0;i<3;i++)	{
				if(board[1][i]==0)	{
					board[1][i]=CPU_ID;
					colorSquare(squareSize,i*squareSize,191,0,255);
					return true;
				}
			} return false;
		} else return false;
	}
	
	public static boolean tookRandomBlankSquare()	{
		int i=(int) Math.floor(Math.random()*3);
		int a=(int) Math.floor(Math.random()*3);
		boolean[][] booleanBoard= new boolean[3][3];
		boolean notAllChecked=true;
		while(board[i][a] != 0 && notAllChecked)	{
			i=(int) Math.floor(Math.random()*3);
			a=(int) Math.floor(Math.random()*3);
			booleanBoard[i][a]=true;
			notAllChecked= checkBooleanBoard(booleanBoard);
		}
		if(!notAllChecked) return false;
		else	{
		board[i][a]=CPU_ID;
		colorSquare(squareSize*i,squareSize*a,191,0,255);
		return true;
		}
	}
	
	public static boolean checkBooleanBoard(boolean[][] arr)	{ //returns true if there is still a false value in boolean board
		for(int i=0;i<arr.length;i++)	{
			for(int a=0;a<arr[0].length;a++)	{
				if(!arr[i][a]) return true;
			}
		} return false;
	}
	
	public static int checkThreeInARow(int id)	{ //0 if no three in a row, 1 if player wins, 2 if comp wins
		int top=board[0][0]+board[1][0]+board[2][0];
		int bottom=board[0][2]+board[1][2]+board[2][2];
		int left=board[0][0]+board[0][1]+board[0][2];
		int right=board[2][0]+board[2][1]+board[2][2];
		int diag1=board[0][0]+board[1][1]+board[2][2]; //top left to bottom right
		int diag2=board[2][0]+board[1][1]+board[0][2]; //top right to bottom left
		int horizontal=board[0][1]+board[1][1]+board[2][1];
		int vertical=board[1][0]+board[1][1]+board[1][2];
		
		int winValue= 3*id;
		if(top==winValue || bottom==winValue || left==winValue || right==winValue || diag1==winValue || diag2==winValue || horizontal==winValue || vertical==winValue) return id;
		return 0;
	}
	
	
	public static void colorSquare(int x, int y, int r, int g, int b)	{
		
		Zen.setColor(r,g,b);
		int xSquarePos=squareSize*(x/squareSize);
		int ySquarePos=squareSize*(y/squareSize);
		Zen.fillRect(xSquarePos, ySquarePos, squareSize, squareSize);
		
	}
	
	public static void finishGame(int id)	{
		if(id==PLAYER_ID)	{
			Zen.setColor(0, 255, 0);
			Zen.drawText("YOU WIN!", squareSize+(squareSize/3), squareSize+(squareSize/2));
		} else if(id==CPU_ID)	{
			Zen.setColor(255,0,0);
			Zen.drawText("YOU LOST", squareSize+(squareSize/3), squareSize+(squareSize/2));
		} else if(id==0)	{
			Zen.setColor(255, 191, 0);
			Zen.drawText("CATS GAME", squareSize+(squareSize/3), squareSize+(squareSize/2));
		}
		restartPrompt();
	}
	
	public static void restartPrompt()	{
		Zen.drawText("Click Anywhere to Restart", (int) Math.floor(squareSize+(squareSize/9)), (int) Math.floor(squareSize+(squareSize/1.5)));
		String starter;
		if(playerStarts) starter="CPU";
		else starter="You";
		Zen.drawText(starter+" Will Start First", (int) Math.floor(squareSize+(squareSize/9)), (int) Math.floor(squareSize+(squareSize/1.25)));
		Zen.waitForClick();
		board= new int[3][3];
		playerStarts=!playerStarts;
		main(null);
	}
}
