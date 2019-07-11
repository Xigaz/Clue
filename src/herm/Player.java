package herm;

import java.util.ArrayList;

public class Player
{
    private String name;
    private ArrayList<Card> hand;
    private Notebook myNotebook;
    private Suspect suspect;
    private int currentLocX, currentLocY;
    private boolean canGuess = false;

	public Player (String n, Suspect s, ArrayList<Card> h)
	{
		name = n;
		hand = h;
		suspect = s;
		AIPlayer.removeSuspect(s);
        currentLocX = suspect.getStartingLoc()[1];
        currentLocY = suspect.getStartingLoc()[0];
        myNotebook = new Notebook(hand, suspect);
        System.out.println("Hello!");
	}

	public boolean isCanGuess()
	{
		return canGuess;
	}

	public void setCanGuess(boolean canGuess)
	{
		this.canGuess = canGuess;
	}

	public ArrayList<Card> getHand()
	{
		return hand;
	}

	public String getName()
	{
		return name;
	}

	public Suspect getSuspect()
    {
        return suspect;
    }

    public void addToNotebook(Card card, Suspect shower)
    {
        if(card.getCardType() == CardType.Weapon)
            myNotebook.addKnownWeapon((Weapon)card.getTitle(), shower);
        else if(card.getCardType() == CardType.Location)
            myNotebook.addKnownRooms((Room) card.getTitle(), shower);
        else if(card.getCardType() == CardType.Person)
            myNotebook.addKnownSuspects((Suspect) card.getTitle(), shower);
    }

    public Suspect[] getNotebookSuspect()
    {
        return myNotebook.getKnownSuspects();
    }

    public Weapon[] getNotebookWeapon()
    {
        return myNotebook.getKnownWeapons();
    }

    public Room[] getNotebookRoom()
    {
        return myNotebook.getKnownRooms();
    }

    public double notebookComplete()
    {
        return myNotebook.percentComplete();
    }

    public String toString()
	{
		return String.format("%s (%s)\tHand: %s\n", suspect, name, hand);
	}

}
