package herm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board
{
	// Bold Text
	private final String ANSI_RESET = "\u001B[1;0m";
	private final String ANSI_BLACK = "\u001B[1;30m";
	private final String ANSI_RED = "\u001B[1;31m";
	private final String ANSI_GREEN = "\u001B[1;32m";
	private final String ANSI_YELLOW = "\u001B[1;33m";
	private final String ANSI_BLUE = "\u001B[1;34m";
	private final String ANSI_PURPLE = "\u001B[1;35m";
	private final String ANSI_CYAN = "\u001B[1;36m";
	private final String ANSI_WHITE = "\u001B[1;37m";

	// Bold High Intensity
	private final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
	private final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
	private final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
	private final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
	private final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
	private final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
	private final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
	private final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

	// High Intensity backgrounds
	private final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
	private final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
	private final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
	private final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
	private final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
	private final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
	private final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
	private final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE

	// Reset Background
	private final String WHITE_BACKGROUND = "\033[47m";  // WHITE

	private Node[][] board = new Node[25][24];

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
				switch(boardPattern[i][j]) {
					case "X":
						board[i][j] = new Node(false, j, i);
						break;
					case " ":
						board[i][j] = new Node(j, i);
						break;
					case "S":
						board[i][j] = new Node(Room.STUDY, Weapon.CANDLESTICK, j, i);
						break;
					case "H":
						board[i][j] = new Node(Room.HALL, Weapon.KNIFE, j, i);
						break;
					case "L":
						board[i][j] = new Node(Room.LOUNGE, Weapon.PIPE, j, i);
						break;
					case "Y":
						board[i][j] = new Node(Room.LIBRARY, Weapon.REVOLVER, j, i);
						break;
					case "D":
						board[i][j] = new Node(Room.DINING_ROOM, Weapon.ROPE, j, i);
						break;
					case "I":
						board[i][j] = new Node(Room.BILLIARD_ROOM, Weapon.WRENCH, j, i);
						break;
					case "C":
						board[i][j] = new Node(Room.CONSERVATORY, j, i);
						break;
					case "A":
						board[i][j] = new Node(Room.BALLROOM, j, i);
						break;
					case "K":
						board[i][j] = new Node(Room.KITCHEN, j, i);
						break;
				}
			}
		for(Suspect s : Suspect.values())
		{
			int x = s.getStartingLoc()[0];
			int y = s.getStartingLoc()[1];

			board[y][x].playerMoveIn(s);
		}
	}

	private Node getNode(int x, int y)
	{
		return board[y][x];
	}

	public Node getSuspectLocation(Suspect s)
	{
		for(int i = 0; i < board.length; i++)
			for(int j = 0; j < board[0].length; j++ )
			{
				if(board[i][j].getOccupants().stream().filter(x -> x.name().equals(s.name())).count() > 0)
					return board[i][j];
			}
		return null;
	}

	public ArrayList<Node> getRoomLocations(Room r)
	{
		ArrayList<Node> roomOptions = new ArrayList<>();
		for(int i = 0; i < board.length; i++)
			for(int j = 0; j < board[0].length; j++ )
			{
				if(board[i][j].getRoom() == r)
					roomOptions.add(board[i][j]);
			}
		return roomOptions;
	}

	public Node getClosestRoomLocation(Room r, Suspect s)
	{
		int winner = 0, close = 9999;
		ArrayList<Node> roomNode = getRoomLocations(r);
		Node suspectNode = getSuspectLocation(s);


		for(int i = 0; i < roomNode.size(); i++)
		{
			int dist = Math.abs(roomNode.get(i).getNodeLocX() - suspectNode.getNodeLocX())
					+ Math.abs(roomNode.get(i).getNodeLocY() - suspectNode.getNodeLocY());
			if (close > dist)
			{
				close = dist;
				winner = i;
			}

		}


		return roomNode.get(winner);
	}

	public ArrayList<Node> calcPath(Suspect s, Room r)
	{
		ArrayList<Node> path = new ArrayList<>();
		Node suspectNode = getSuspectLocation(s);
		Node goalNode = getClosestRoomLocation(r, s);

		Map<Node, Node> pathTo = new HashMap<>();
		ArrayList<Node> frontier = new ArrayList<>();
		ArrayList<Node> expored = new ArrayList<>();

		frontier.add(suspectNode);

		while (!frontier.isEmpty())
		{
			Node leafNode = frontier.remove(0);

			if (leafNode == goalNode)
			{
				//System.out.println("yay!");

				Node currentNode = goalNode;

				while(currentNode != null)
				{
					path.add(currentNode);
					currentNode = pathTo.get(currentNode);
				}
				Collections.reverse(path);
				//System.out.printf("\n%s\n", path);
				return path;
			}

			expored.add(leafNode);

			ArrayList<Node> linkers = linkedTo(leafNode);

			for(int i = 0; i < linkers.size(); i++)
			{
				Node connectedNode = linkers.get(i);

				if(!expored.contains(connectedNode) && !frontier.contains(connectedNode))
				{
					pathTo.put(connectedNode, leafNode);
					frontier.add(connectedNode);
				}

			}

		}
		return null;
	}

	public ArrayList<Node> linkedTo(Node leaf)
	{
		ArrayList<Node> linked = new ArrayList<>();

		Node north=null, south=null, east=null, west=null;

		if(leaf.getNodeLocY()-1 >= 0)
			north = board[leaf.getNodeLocY()-1][leaf.getNodeLocX()];
		if(leaf.getNodeLocY()+1 < 25)
			south = board[leaf.getNodeLocY()+1][leaf.getNodeLocX()];
		if(leaf.getNodeLocX()+1 < 24)
			east  = board[leaf.getNodeLocY()][leaf.getNodeLocX()+1];
		if(leaf.getNodeLocX()-1 >= 0)
			west  = board[leaf.getNodeLocY()][leaf.getNodeLocX()-1];

		if (north != null && north.isAccessible())
			linked.add(north);
		if (south != null && south.isAccessible())
			linked.add(south);
		if (east != null && east.isAccessible())
			linked.add(east);
		if (west != null && west.isAccessible())
			linked.add(west);

		return linked;
	}

	public String toString()
	{
		StringBuilder returnValue = new StringBuilder("╔════════════════════════╗\n");


		for(int i = 0; i < board.length; i++)
		{
			returnValue.append("║");
			for(int j = 0; j < board[0].length; j++)
			{
				Node checkNode = board[i][j];

				if (checkNode.getOccupants().size() > 0)
				{
					if (checkNode.getOccupants().size() > 1)
						returnValue.append(CYAN_BOLD_BRIGHT + BLACK_BACKGROUND_BRIGHT + "@" + ANSI_RESET);
					else
					{
						switch (checkNode.getOccupants().get(0))
						{
							case MR_GREEN:
								returnValue.append(GREEN_BOLD_BRIGHT + BLACK_BACKGROUND_BRIGHT + Suspect.MR_GREEN.getRepresentChar() + ANSI_RESET);
								break;
							case MRS_WHITE:
								returnValue.append(WHITE_BOLD_BRIGHT + BLACK_BACKGROUND_BRIGHT + Suspect.MRS_WHITE.getRepresentChar() + ANSI_RESET);
								break;
							case PROF_PLUM:
								returnValue.append(PURPLE_BOLD_BRIGHT + BLACK_BACKGROUND_BRIGHT + Suspect.PROF_PLUM.getRepresentChar() + ANSI_RESET);
								break;
							case COL_MUSTARD:
								returnValue.append(YELLOW_BOLD_BRIGHT + BLACK_BACKGROUND_BRIGHT + Suspect.COL_MUSTARD.getRepresentChar() + ANSI_RESET);
								break;
							case MRS_PEACOCK:
								returnValue.append(CYAN_BOLD_BRIGHT + BLACK_BACKGROUND_BRIGHT + Suspect.MRS_PEACOCK.getRepresentChar() + ANSI_RESET);
								break;
							case MRS_SCARLET:
								returnValue.append(RED_BOLD_BRIGHT + BLACK_BACKGROUND_BRIGHT + Suspect.MRS_SCARLET.getRepresentChar() + ANSI_RESET);
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
					returnValue.append(BLACK_BOLD_BRIGHT + YELLOW_BACKGROUND_BRIGHT + checkNode.getRoom().getRepresentChar() + ANSI_RESET);
				}

			}
			returnValue.append("║");
			returnValue.append("\n");
		}

		returnValue.append("╚════════════════════════╝\n\n");
		return returnValue.toString();
	}
}
