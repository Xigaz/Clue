package herm;

import java.util.ArrayList;
import java.util.Collections;

public class Game
{
	private ArrayList<Player> players;
	private final int NUM_PLAYERS = 5;
	private ArrayList<Card> confidentialCards;
	private Board gameBoard;

	public Game(String n, Suspect s)
	{
		gameBoard = new Board();
		System.out.println(gameBoard);
		confidentialCards = new ArrayList<>();
		players = new ArrayList<>();
		Player.resetNameOptions();
		Player.resetSuspectOptions();


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
			players.add(new Player(newHand));
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

	}

}
