package herm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game
{
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
		Scanner input = new Scanner(System.in);

		Node loc = gameBoard.getSuspectLocation(p.getSuspect());

		//AIPlayer bob = p instanceof AIPlayer ? ((AIPlayer) p) : null;
		int counter = 1;
		System.out.printf("%s, What would you like to do?", p.getSuspect() );
		System.out.printf("%d) %s\n", counter++, "Move");
		if (loc.getRoom().getPassageExit() != null)
		{
			System.out.printf("%d) %s\n", counter++, "Take Secret Passage");
		}
		if(loc.getRoom() != null && p.isCanGuess())
			System.out.printf("%d) %s\n", counter++, "Make a Suggestion");
		System.out.printf("> ");
		int whatToDo = input.nextInt();
		input.nextLine();

		switch(whatToDo)
		{
			case 1:
		}



		if (loc.getRoom() != null )
		{
			System.out.printf("%d) %s", counter++, "Make a Suggestion");
			System.out.printf("%d) %s", counter++, "Make a Guess");

		}


	}

}
