package herm;

import java.util.*;
import java.util.concurrent.TimeUnit;

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
		//System.out.println(gameBoard);
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
		//System.out.println(players);
		//System.out.println(initialDeck);

		//gameBoard.calcPath(Suspect.COL_MUSTARD, Room.BALLROOM);
	}

	private void makeConfidentialCards(ArrayList<Card> cards)
	{
		confidentialCards.add(cards.stream().filter(x -> x.getCardType() == CardType.Location).findFirst().get());
		confidentialCards.add(cards.stream().filter(x -> x.getCardType() == CardType.Person).findFirst().get());
		confidentialCards.add(cards.stream().filter(x -> x.getCardType() == CardType.Weapon).findFirst().get());
		cards.removeAll(confidentialCards);
		//System.out.println("Confidential Cards: " + confidentialCards);
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
		Collections.shuffle(deck);
		Collections.shuffle(deck);
		Collections.shuffle(deck);
		Collections.shuffle(deck);
		Collections.shuffle(deck);
		Collections.shuffle(deck);
		Collections.shuffle(deck);
		return deck;
	}

	public void play()
	{
		System.out.println("You are joined today by:\n");
		for(Player p : players)
			if (p instanceof AIPlayer)
				System.out.printf("%s (%s)\n", p.getSuspect(), p.getName());

		while(!isGameOver)
		{
			for(Player p : players)
			{
				currentPlayer = p;

				System.out.printf("%s's Turn: \n", currentPlayer.getName());

				if (p instanceof AIPlayer )
					AITurn();
				else
					takeTurn();

				System.out.println(gameBoard);
				if(isGameOver)
					break;
			}

		}
	}

	private void AITurn()
	{
		boolean canGuess = false, canSecretPassage = false;
		Node loc = gameBoard.getSuspectLocation(currentPlayer.getSuspect());

		canSecretPassage = loc.getRoom() != null && loc.getRoom().getPassageExit() != null;
		canGuess = currentPlayer.isCanGuess();

		if(canGuess)
		{
			makeSuggestion();
			currentPlayer.setCanGuess(false);
			canGuess = false;
		}
		else if (canSecretPassage)
			moveSecretPassage();
		else if (loc.getRoom() != null)
		{
			if ((new Random()).nextBoolean())
				waitInHall();
			else
				moveAIPlayer();
		}
		else
			moveAIPlayer();

		loc = gameBoard.getSuspectLocation(currentPlayer.getSuspect());
		if (canGuess && loc.getRoom() != null && currentPlayer.notebookComplete() > 80)
			finalAccusation(buildAIGuess(), currentPlayer.getSuspect());
		else if (canGuess && loc.getRoom() != null)
			makeSuggestion();
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
			System.out.printf("6) %s\n", "Look at my Notebook");
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
					{
						makeSuggestion();
						currentPlayer.setCanGuess(false);
						canGuess = false;
					}
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
				case 6:
					System.out.println("\nYour Notebook:");
					System.out.println("Rooms: " + Arrays.toString(currentPlayer.getNotebookRoom()));
					System.out.println("Suspect: " + Arrays.toString(currentPlayer.getNotebookSuspect()));
					System.out.println("Weapon: " + Arrays.toString(currentPlayer.getNotebookWeapon()));
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
		currentPlayer.setCanGuess(false);
	}

	private void movePlayer()
	{
		Scanner input = new Scanner(System.in);
		Random rand = new Random();

		int roll =  rand.nextInt(120000) % 12 + 1;
		int choice = 0;

		while (choice < 1 || choice > 9) {
			System.out.printf("You rolled a..%d!\n", roll);
			System.out.println("Where would you like to go?");
			int counter = 1;
			for (Room r : Room.values()) {
				System.out.printf("%d) %s (%s)\n", counter++, r.toString(), gameBoard.calcPath(currentPlayer.getSuspect(), r).size() - 1 + " spaces away");
			}
			System.out.print(">");
			choice = input.nextInt();
			input.nextLine();
		}

		ArrayList<Node> path = gameBoard.calcPath(currentPlayer.getSuspect(), Room.values()[choice-1]);
		path.get(0).playerMoveOut(currentPlayer.getSuspect());

		if (roll < path.size())
			path.get(roll).playerMoveIn(currentPlayer.getSuspect());
		else
			path.get(path.size()-1).playerMoveIn(currentPlayer.getSuspect());

	}

	private void moveAIPlayer()
	{
		Random rand = new Random();

		int roll = rand.nextInt(120000) % 12 + 1;

		Room choice = null;
		for(Room r : Room.values())
		{
			if(gameBoard.calcPath(currentPlayer.getSuspect(), r).size() < roll)
				choice = r;
		}
		if (choice == null)
			choice = Room.values()[rand.nextInt(900000 ) % Room.values().length];

		ArrayList<Node> path = gameBoard.calcPath(currentPlayer.getSuspect(), choice);
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

		Guess suggestion = currentPlayer instanceof AIPlayer ? buildAIGuess() : buildAGuess() ;

		if(!suggestion.getGuessSuspect().toString().equalsIgnoreCase(currentPlayer.getSuspect().toString()))
		for(Player p : players)
		{
			if (p.getSuspect() == suggestion.getGuessSuspect())
			{
				p.setCanGuess(true);
				gameBoard.getSuspectLocation(p.getSuspect()).playerMoveOut(p.getSuspect());
				gameBoard.getSuspectLocation(currentPlayer.getSuspect()).playerMoveIn(p.getSuspect());
			}
		}

		System.out.printf("%s has put forth a Suggestion:\n%s", currentPlayer.getSuspect(), suggestion);

		for(int i = 1; i < players.size(); i++)
		{
			int index = players.indexOf(currentPlayer) + i >= players.size() ? players.indexOf(currentPlayer) + i - players.size() : players.indexOf(currentPlayer) + i;

			Player p = players.get(index);
			ArrayList<Card> cards = p.getHand();
			for(Card c : cards)
			{
				if (c.getTitle() == suggestion.getGuessSuspect() ||
						c.getTitle() == suggestion.getGuessRoom() ||
						c.getTitle() == suggestion.getGuessWeapon())
				{
					currentPlayer.addToNotebook(c, p.getSuspect());
					System.out.printf("%s shows %s\n", p.getSuspect(), currentPlayer instanceof AIPlayer ? "a card" : c);
					try {
						TimeUnit.SECONDS.sleep(2);
					}catch(InterruptedException e)
					{}
					return;
				}

			}
		}
		System.out.println("\nNo Cards were shown!\n");
		try {
			TimeUnit.SECONDS.sleep(2);
		}catch(InterruptedException e)
		{}
	}

	private void makeAccusation()
	{
		Scanner input = new Scanner(System.in);
		Guess finalGuess = buildAGuess();

		System.out.println("Where did the Murder Happen?");
		int counter = 1;
		for(Room rm : Room.values())
		{
			System.out.printf("%d) %s\n", counter++, rm);
		}
		System.out.print("> ");
		finalGuess.setGuessRoom(Room.values()[input.nextInt()-1]);
		input.nextLine();

		finalAccusation(finalGuess, currentPlayer.getSuspect());

	}

	private void finalAccusation(Guess finalGuess, Suspect suspect)
	{
		Room r = null;
		Weapon w = null;
		Suspect s = null;

		for(Card x : confidentialCards)
		{
			CardOptions y = x.getTitle();
			if (y instanceof Room)
				r = (Room) y;
			else if(y instanceof Weapon)
				w = (Weapon) y;
			else
				s = (Suspect) y;
		}

		System.out.printf("%s has made an Accusation!!!\n\n%s", currentPlayer.getSuspect(), finalGuess);
		try {
			TimeUnit.SECONDS.sleep(2);
		}catch(InterruptedException e)
		{}
		if (finalGuess.getGuessSuspect() == s && finalGuess.getGuessRoom() == r && finalGuess.getGuessWeapon() == w)
		{
			System.out.printf("\n\n%s WINS!!!!\n\n", currentPlayer.getName());
			isGameOver = true;
		}
		else
		{
			System.out.printf("\n\n%s IS WRONG!!\n\nYou're out!", currentPlayer.getName());
			players.remove(currentPlayer);
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		}catch(InterruptedException e)
		{}
	}

	private Guess buildAGuess()
	{
		Scanner input = new Scanner(System.in);
		Room guessRoom = gameBoard.getSuspectLocation(currentPlayer.getSuspect()).getRoom();
		Suspect guessSuspect;
		Weapon guessWeapon;

		int wPick = 0;
		while(wPick < 1 || wPick > Weapon.values().length) {
			int counter = 1;

			System.out.println("What was the murder weapon?");
			for (Weapon x : Weapon.values()) {
				System.out.printf("%d) %s\n", counter, x);
				counter++;
			}
			System.out.print("> ");
			wPick = input.nextInt();
			input.nextLine();
		}
		guessWeapon = Weapon.values()[wPick-1];

		int mPick = 0;
		while (mPick < 1 || mPick > Suspect.values().length )
		{
			System.out.println("Who is the murder?");
			int counter = 1;
			for (Suspect x : Suspect.values()) {
				System.out.printf("%d) %s\n", counter, x);
				counter++;
			}
			System.out.print("> ");
			mPick = input.nextInt();
			input.nextLine();
		}
		guessSuspect = Suspect.values()[mPick - 1];

		return new Guess(guessSuspect, guessWeapon, guessRoom);
	}

	private Guess buildAIGuess()
	{
		ArrayList<Suspect> s = new ArrayList<>(Arrays.asList(Suspect.values()));
		ArrayList<Weapon> w = new ArrayList<>(Arrays.asList(Weapon.values()));

		for(Suspect x : currentPlayer.getNotebookSuspect())
		{
			s.remove(x);
		}
		for(Weapon x : currentPlayer.getNotebookWeapon())
		{
			w.remove(x);
		}

		return new Guess( s.get((new Random()).nextInt(60000)%s.size()), w.get((new Random()).nextInt(60000)%w.size()), gameBoard.getSuspectLocation(currentPlayer.getSuspect()).getRoom() );
	}

	private void waitInHall()
	{
		ArrayList<Node> adjacent = gameBoard.linkedTo(gameBoard.getSuspectLocation(currentPlayer.getSuspect()));
		gameBoard.getSuspectLocation(currentPlayer.getSuspect()).playerMoveOut(currentPlayer.getSuspect());
		adjacent.get(0).playerMoveIn(currentPlayer.getSuspect());
	}
}
