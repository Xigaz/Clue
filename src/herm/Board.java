package herm;

public class Board
{
	public final String ANSI_RESET = "\u001B[0m";
	public final String ANSI_BLACK = "\u001B[30m";
	public final String ANSI_RED = "\u001B[31m";
	public final String ANSI_GREEN = "\u001B[32m";
	public final String ANSI_YELLOW = "\u001B[33m";
	public final String ANSI_BLUE = "\u001B[34m";
	public final String ANSI_PURPLE = "\u001B[35m";
	public final String ANSI_CYAN = "\u001B[36m";
	public final String ANSI_WHITE = "\u001B[37m";

	Node[][] board = new Node[25][24];

	String[][] boardPattern = {
			{"X", "X", "X", "X", "X", "X", "X", " ", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
			{"X", "X", "X", "X", "X", "X", "X", " ", " ", "X", "X", "X", "X", "X", "X", " ", " ", "X", "X", "X", "X", "X", "X", "X"},
			{"X", "X", "X", "X", "X", "X", "X", " ", " ", "X", "X", "X", "X", "X", "X", " ", " ", "X", "X", "X", "X", "X", "X", "X"},
			{"X", "X", "X", "X", "X", "X", "S", " ", " ", "X", "X", "X", "X", "X", "X", " ", " ", "X", "X", "X", "X", "X", "X", "X"},
			{"X", " ", " ", " ", " ", " ", " ", " ", " ", "H", "X", "X", "X", "X", "X", " ", " ", "X", "X", "X", "X", "X", "X", "X"},
			{"X", " ", " ", " ", " ", " ", " ", " ", " ", "X", "X", "X", "X", "X", "X", " ", " ", "L", "X", "X", "X", "X", "X", "X"},
			{"X", "X", "X", "X", "X", "X", " ", " ", " ", "X", "X", "H", "H", "X", "X", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
			{"X", "X", "X", "X", "X", "X", "X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
			{"X", "X", "X", "X", "X", "X", "Y", " ", " ", "X", "X", "X", "X", "X", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
			{"X", "X", "X", "X", "X", "X", "X", " ", " ", "X", "X", "X", "X", "X", " ", " ", "X", "D", "X", "X", "X", "X", "X", "X"},
			{"X", "X", "X", "Y", "X", "X", " ", " ", " ", "X", "X", "X", "X", "X", " ", " ", "X", "X", "X", "X", "X", "X", "X", "X"},
			{"X", " ", " ", " ", " ", " ", " ", " ", " ", "X", "X", "X", "X", "X", " ", " ", "X", "X", "X", "X", "X", "X", "X", "X"},
			{"X", "I", "X", "X", "X", "X", " ", " ", " ", "X", "X", "X", "X", "X", " ", " ", "D", "X", "X", "X", "X", "X", "X", "X"},
			{"X", "X", "X", "X", "X", "X", " ", " ", " ", "X", "X", "X", "X", "X", " ", " ", "X", "X", "X", "X", "X", "X", "X", "X"},
			{"X", "X", "X", "X", "X", "X", " ", " ", " ", "X", "X", "X", "X", "X", " ", " ", "X", "X", "X", "X", "X", "X", "X", "X"},
			{"X", "X", "X", "X", "X", "I", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X", "X", "X", "X", "X"},
			{"X", "X", "X", "X", "X", "X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
			{"X", " ", " ", " ", " ", " ", " ", " ", "X", "A", "X", "X", "X", "X", "A", "X", " ", " ", " ", " ", " ", " ", " ", " "},
			{"X", " ", " ", " ", " ", " ", " ", " ", "X", "X", "X", "X", "X", "X", "X", "X", " ", " ", "X", "K", "X", "X", "X", "X"},
			{"X", "X", "X", "X", "C", " ", " ", " ", "A", "X", "X", "X", "X", "X", "X", "X", " ", " ", "X", "X", "X", "X", "X", "X"},
			{"X", "X", "X", "X", "X", "X", " ", " ", "X", "X", "X", "X", "X", "X", "X", "X", " ", " ", "X", "X", "X", "X", "X", "X"},
			{"X", "X", "X", "X", "X", "X", " ", " ", "X", "X", "X", "X", "X", "X", "X", "X", " ", " ", "X", "X", "X", "X", "X", "X"},
			{"X", "X", "X", "X", "X", "X", " ", " ", "X", "X", "X", "X", "X", "X", "X", "X", " ", " ", "X", "X", "X", "X", "X", "X"},
			{"X", "X", "X", "X", "X", "X", "X", " ", " ", " ", "X", "X", "X", "X", " ", " ", " ", "X", "X", "X", "X", "X", "X", "X"},
			{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"}
	};

	public Board()
	{
		buildBoard();
	}

	private void buildBoard()
	{
		for(int i = 0; i < boardPattern.length; i++)
			for (int j = 0; j < boardPattern[0].length; j++)
			{
				switch(boardPattern[i][j])
				{
					case "X":
						board[i][j] = new Node(false);
						break;
					case " ":
						board[i][j] = new Node();
						break;
					case "S":
						board[i][j] = new Node(Room.STUDY, Weapon.CANDLESTICK);
						break;
					case "H":
						board[i][j] = new Node(Room.HALL, Weapon.KNIFE);
						break;
					case "L":
						board[i][j] = new Node(Room.LOUNGE, Weapon.PIPE);
						break;
					case "Y":
						board[i][j] = new Node(Room.LIBRARY, Weapon.REVOLVER);
						break;
					case "D":
						board[i][j] = new Node(Room.DINING_ROOM, Weapon.ROPE);
						break;
					case "I":
						board[i][j] = new Node(Room.BILLIARD_ROOM, Weapon.WRENCH);
						break;
					case "C":
						board[i][j] = new Node(Room.CONSERVATORY);
						break;
					case "A":
						board[i][j] = new Node(Room.BALLROOM);
						break;
					case "K":
						board[i][j] = new Node(Room.KITCHEN);
						break;
				}
			}
	}


	public String toString()
	{
		StringBuilder returnValue = new StringBuilder("╔══════════════════════╗\n");


		for(int i = 0; i < board.length; i++)
		{
			returnValue.append("║");
			for(int j = 0; j < board[0].length; j++)
			{
				Node checkNode = board[i][j];
				if(i == 0)

				if (checkNode.getOccupants().size() > 0)
				{
					if (checkNode.getOccupants().size() > 1)
						returnValue.append(ANSI_CYAN + "@" + ANSI_RESET);
					else
					{
						switch (checkNode.getOccupants().get(0).getSuspect())
						{
							case MR_GREEN:
								returnValue.append(ANSI_GREEN + "@" + ANSI_RESET);
								break;
							case MRS_WHITE:
								returnValue.append(ANSI_WHITE + "@" + ANSI_RESET);
								break;
							case PROF_PLUM:
								returnValue.append(ANSI_PURPLE + "@" + ANSI_RESET);
								break;
							case COL_MUSTARD:
								returnValue.append(ANSI_YELLOW + "@" + ANSI_RESET);
								break;
							case MRS_PEACOCK:
								returnValue.append(ANSI_BLUE + "@" + ANSI_RESET);
								break;
							case MRS_SCARLET:
								returnValue.append(ANSI_RED + "@" + ANSI_RESET);
								break;
						}
					}
				}
				else if (checkNode.getRoom() == null && checkNode.isAccessible())
				{
					returnValue.append("░");
				}
				else if(!checkNode.isAccessible())
				{
					returnValue.append("█");
//					if (board[0].length == j+1 || j == 0)
//						returnValue.append((char)179);
//					else if (board.length == i+1 || i == 0)
//						returnValue.append((char)194);
//
//					if(board[0].length > j+1 && board[i][j+1].isAccessible() )
//						returnValue.append((char)179);
//					else if (0 <= j-1  && board[i][j-1].isAccessible())
//						returnValue.append((char))
				}
				else if(checkNode.getRoom() != null)
				{
					returnValue.append(checkNode.getRoom().getRepresentChar());
				}

			}
			returnValue.append("║");
			returnValue.append("\n");
		}

		returnValue.append("╚══════════════════════╝\n\n");
		return returnValue.toString();
	}
}
