package herm;

import java.util.ArrayList;
import java.util.Collections;

public class Game
{
	ArrayList<Player> players;


	public Game(String n)
	{
		players = new ArrayList<>();

		ArrayList<Card> initialDeck = buildDeck();


		players.add(new Player(n));
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
