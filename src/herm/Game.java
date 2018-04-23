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

	public Game(String n, Suspect s)
	{
		gameBoard = new Board();
		System.out.println(gameBoard);
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
				//takeTurn(p);
			}

		}
	}

	private void takeTurn(Player p)
	{

		boolean canGuess = false, canSecretPassage = false;
		Scanner input = new Scanner(System.in);

		Node loc = gameBoard.getSuspectLocation(p.getSuspect());


		canSecretPassage = loc.getRoom().getPassageExit() != null;
		canGuess = p.isCanGuess();
		int choice = 1;

		//AIPlayer bob = p instanceof AIPlayer ? ((AIPlayer) p) : null;

		while(choice >= 1 && choice <= 4) {
			System.out.printf("\n%s, What would you like to do?", p.getSuspect());
			System.out.printf("1) %s\n", "Move");
			System.out.printf("%s2) %s%s\n", canSecretPassage ? "" : RED_BACKGROUND_BRIGHT, "Take Secret Passage", ANSI_RESET);
			System.out.printf("%s3) %s%s\n", canGuess ? "" : RED_BACKGROUND_BRIGHT, "Make a Suggestion", ANSI_RESET);
			System.out.printf("%s4) %s%s\n", canGuess ? "" : RED_BACKGROUND_BRIGHT, "Make an Accusation", ANSI_RESET);
			System.out.print("> ");
			choice = input.nextInt();
			input.nextLine();

			switch (choice) {
				case 1:
					movePlayer(p);
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
			}
		}





		if (loc.getRoom() != null )
		{
			System.out.printf("%d) %s", counter++, "Make a Suggestion");
			System.out.printf("%d) %s", counter++, "Make a Guess");

		}


	}

	private void movePlayer(Player p)
	{
		Scanner input = new Scanner(System.in);
		Random rand = new Random();

		int roll = rand.nextInt(12000) % 12;

		System.out.printf("You rolled a..%d!\n", roll);
		System.out.printf("Where would you like to go?");
		int counter = 1;
		for(Room r : Room.values())
		{
			System.out.printf("%d) %s (%s)\n", counter++, r.toString(), gameBoard.calcDistance(p.getSuspect(), r) + " spaces away");
		}
		System.out.print(">");
		int choice = input.nextInt();
		input.nextLine();


	}
}
