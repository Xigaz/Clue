package herm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Game
{
	// High Intensity backgrounds
	private final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
	private final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
	private final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
	private final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
	private final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
	private final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
	private final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
	private final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
	private final String ANSI_RESET = "\u001B[1;0m";


	private ArrayList<Player> players;
	private final int NUM_PLAYERS = 5;
	private ArrayList<Card> confidentialCards;
	private Board gameBoard;
	private boolean isGameOver = false;
	private Player currentPlayer;

	public Game(String n, Suspect s)
	{
		gameBoard = new Board();
		System.out.println(gameBoard);
//		for(Suspect su : Suspect.values())
//		{
//			Node loc = gameBoard.getSuspectLocation(su);
//			System.out.printf("%s is at (%d, %d)\n", su, loc.getNodeLocX(), loc.getNodeLocY());
//		}
//		System.out.printf("");

		confidentialCards = new ArrayList<>();
		players = new ArrayList<>();
		AIPlayer.resetNameOptions();
		AIPlayer.resetSuspectOptions();


		ArrayList<Card> initialDeck = buildDeck();
		makeConfidentialCards(initialDeck);
		int handSize = initialDeck.size() / NUM_PLAYERS;
		int extraCards = initialDeck.size() % NUM_PLAYERS;

		players.add(new Player(n, s, (new ArrayList<>(initialDeck.subList(0, handSize + (extraCards-- > 0 ? 1 : 0 ))))));
		initialDeck.removeAll(players.get(players.size()-1).getHand());

		for(int i = 0; i < NUM_PLAYERS - 1; i++)
		{
			ArrayList<Card> newHand = (new ArrayList<>(initialDeck.subList(0, handSize + (extraCards-- > 0 ? 1 : 0 ))));
			initialDeck.removeAll(newHand);
			players.add(new AIPlayer(newHand));
		}
		System.out.println(players);
		//System.out.println(initialDeck);

		//gameBoard.calcPath(Suspect.COL_MUSTARD, Room.BALLROOM);
	}

	private void makeConfidentialCards(ArrayList<Card> cards)
	{
		confidentialCards.add(cards.stream().filter(x -> x.getCardType() == CardType.Location).findFirst().get());
		confidentialCards.add(cards.stream().filter(x -> x.getCardType() == CardType.Person).findFirst().get());
		confidentialCards.add(cards.stream().filter(x -> x.getCardType() == CardType.Weapon).findFirst().get());
		cards.removeAll(confidentialCards);
		System.out.println("Confidential Cards: " + confidentialCards);
	}

	private ArrayList<Card> buildDeck()
	{
		ArrayList<Card> deck = new ArrayList<>();
		for(CardType x : CardType.values())
		{
			switch(x)
			{
				case Person:
					for(Suspect s : Suspect.values())
					{
						deck.add(new Card(x, s));
					}
					break;
				case Location:
					for(Room s : Room.values())
					{
						deck.add(new Card(x, s));
					}
					break;
				case Weapon:
					for(Weapon s : Weapon.values())
					{
						deck.add(new Card(x, s));
					}
					break;
			}

		}
		Collections.shuffle(deck);
		return deck;
	}

	public void play()
	{
		while(!isGameOver)
		{
			for(Player p : players)
			{
				currentPlayer = p;
				takeTurn();
				System.out.println(gameBoard);
				//gameBoard.getClosestRoomLocation(Room.BALLROOM, Suspect.MR_GREEN);
				isGameOver = true;
			}

		}
	}

	private void takeTurn()
	{

		boolean canGuess = false, canSecretPassage = false;
		Scanner input = new Scanner(System.in);

		Node loc = gameBoard.getSuspectLocation(currentPlayer.getSuspect());


		canSecretPassage = loc.getRoom() != null && loc.getRoom().getPassageExit() != null;
		canGuess = currentPlayer.isCanGuess();
		int choice = 0;

		//AIPlayer bob = currentPlayer instanceof AIPlayer ? ((AIPlayer) p) : null;

		while(choice < 1 || choice > 5) {
			System.out.printf("\n%s, What would you like to do?\n", currentPlayer.getSuspect());
			System.out.printf("1) %s\n", "Move");
			System.out.printf("%s2) %s%s\n", canSecretPassage ? "" : RED_BACKGROUND_BRIGHT, "Take Secret Passage", ANSI_RESET);
			System.out.printf("%s3) %s%s\n", canGuess ? "" : RED_BACKGROUND_BRIGHT, "Make a Suggestion", ANSI_RESET);
			System.out.printf("%s4) %s%s\n", canGuess ? "" : RED_BACKGROUND_BRIGHT, "Make an Accusation", ANSI_RESET);
			System.out.printf("%s5) %s%s\n", loc.getRoom() != null ? "" : RED_BACKGROUND_BRIGHT, "Wait In The Hall", ANSI_RESET);
			System.out.print("> ");
			choice = input.nextInt();
			input.nextLine();

			switch (choice) {
				case 1:
					movePlayer();
					break;
				case 2:
					if (canSecretPassage)
						moveSecretPassage();
					else
						System.out.println("Uhh.. Try that again.");
					break;
				case 3:
					if (canGuess)
						makeSuggestion();
					else
						System.out.println("Why don't you try that again.");
					break;
				case 4:
					if (canGuess)
						makeAccusation();
					else
						System.out.println("You wish. Try Again.");
					break;
				case 5:
					if(loc.getRoom() != null)
						waitInHall();
					else
						System.out.println("You shouldn't lurk in doorways. It's rude.");
					break;
				default:
					System.out.println("Just pick one of the options.");
			}
		}

		if (gameBoard.getSuspectLocation(currentPlayer.getSuspect()).getRoom() != null)
		{
			int guessChoice = 0;
			while (guessChoice < 1 || guessChoice > 3)
			{
				System.out.println("You made it to a room. Would you like to do anything else?");
				System.out.printf("%d) %s\n", 1, "Make a Suggestion");
				System.out.printf("%d) %s\n", 2, "Make an Accusation");
				System.out.printf("%d) %s\n", 3, "Just End My Turn");
				System.out.print("> ");
				guessChoice = input.nextInt();
				input.nextLine();

				switch (guessChoice)
				{
					case 1:
						makeSuggestion();
						break;
					case 2:
						makeAccusation();
						break;
					default:
						System.out.println("Just pick one of the options.");
				}
			}

		}
	}

	private void movePlayer()
	{
		Scanner input = new Scanner(System.in);
		Random rand = new Random();

		int roll = 7;// rand.nextInt(12000) % 12 + 1;

		System.out.printf("You rolled a..%d!\n", roll);
		System.out.println("Where would you like to go?");
		int counter = 1;
		for(Room r : Room.values())
		{
			System.out.printf("%d) %s (%s)\n", counter++, r.toString(), gameBoard.calcPath(currentPlayer.getSuspect(), r).size() - 1 + " spaces away");
		}
		System.out.print(">");
		int choice = input.nextInt();
		input.nextLine();

		ArrayList<Node> path = gameBoard.calcPath(currentPlayer.getSuspect(), Room.values()[choice-1]);
		path.get(0).playerMoveOut(currentPlayer.getSuspect());

		if (roll < path.size())
			path.get(roll).playerMoveIn(currentPlayer.getSuspect());
		else
			path.get(path.size()-1).playerMoveIn(currentPlayer.getSuspect());

	}

	private void moveSecretPassage()
	{
		gameBoard.getClosestRoomLocation(gameBoard.getSuspectLocation(currentPlayer.getSuspect()).getRoom().getPassageExit(), currentPlayer.getSuspect()).playerMoveIn(currentPlayer.getSuspect());
		gameBoard.getSuspectLocation(currentPlayer.getSuspect()).playerMoveOut(currentPlayer.getSuspect());
	}

	private void makeSuggestion()
	{
		Guess suggestion = buildAGuess();




	}

	private void makeAccusation()
	{

	}

	private Guess buildAGuess()
	{
		Scanner input = new Scanner(System.in);
		Room guessRoom = gameBoard.getSuspectLocation(currentPlayer.getSuspect()).getRoom();

		int counter = 1;
		for(Weapon x : Weapon.values())
		{
			System.out.printf("%d) %s\n", counter, x);
			counter++;

		}
		System.out.print("> ");
		Weapon guessWeapon = Weapon.values()[input.nextInt()-1];
		input.nextLine();

		counter = 1;
		for(Suspect x : Suspect.values())
		{
			System.out.printf("%d) %s\n", counter, x);
			counter++;

		}
		System.out.print("> ");
		Suspect guessSuspect = Suspect.values()[input.nextInt()-1];
		input.nextLine();

		for(Player p : players)
		{
			if (p.getSuspect() == guessSuspect)
			{
				p.setCanGuess(true);
			}
		}

		return new Guess(guessSuspect, guessWeapon, guessRoom);
	}

	private void waitInHall()
	{
		ArrayList<Node> adjacent = gameBoard.linkedTo(gameBoard.getSuspectLocation(currentPlayer.getSuspect()));
		gameBoard.getSuspectLocation(currentPlayer.getSuspect()).playerMoveOut(currentPlayer.getSuspect());
		adjacent.get(0).playerMoveIn(currentPlayer.getSuspect());
	}
}
