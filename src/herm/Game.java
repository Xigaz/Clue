package herm;

import java.util.ArrayList;
import java.util.Collections;

public class Game
{
	private ArrayList<Player> players;
	private final int NUM_PLAYERS = 5;

	public Game(String n)
	{
		players = new ArrayList<>();

		ArrayList<Card> initialDeck = buildDeck();
		int handSize = initialDeck.size() / NUM_PLAYERS;
		int extraCards = initialDeck.size() % NUM_PLAYERS;

		for(int i = 0; i < NUM_PLAYERS-1; i++)
		{
			ArrayList<Card> newHand = (new ArrayList<>(initialDeck.subList(0, handSize + (extraCards > 0 ? extraCards-- : 0 ))));
			initialDeck.removeAll(newHand);
			players.add(new Player(newHand));
		}
		players.add(new Player(n, (new ArrayList<>(initialDeck.subList(0, handSize)))));

		//System.out.println(players);
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
