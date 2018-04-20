package herm;

import java.util.ArrayList;
import java.util.Random;

public class Player
{
	String[] nameOptions = {
			"Phineas",
			"Ferb",
			"Dr. Doof",
			"Candace",
			"Perry",
			"Baljeet",
			"Carl",
			"Isabella",
			"Buford",
			"Jeremy",
			"Stacy",
			"Vanessa"
	};

	private String name;
	private ArrayList<Card> hand;
	private Notebook myNotebook;

	public Player (String n, ArrayList<Card> h)
	{
		name = n;
		hand = h;
	}

	public Player (ArrayList<Card> h)
	{
		name = nameOptions[(new Random()).nextInt(nameOptions.length)];
		hand = h;
	}

	public ArrayList<Card> getHand()
	{
		return hand;
	}

	public String getName()
	{
		return name;
	}

	//TODO: Handle printing Notebook
	//TODO:

}
