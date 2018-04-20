package herm;

import java.util.ArrayList;

public class Player
{
	private String name;
	private ArrayList<Card> hand;
	private Notebook myNotebook;

	public Player (String n, ArrayList<Card> h)
	{
		name = n;
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
